package com.thinkive.android.trade_bz.a_stock.fragment;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.thinkive.framework.compatible.ListenerControllerAdapter;
import com.android.thinkive.framework.compatible.TKFragmentActivity;
import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.adapter.OneKeyAdapter;
import com.thinkive.android.trade_bz.a_stock.bean.OneKeyBean;
import com.thinkive.android.trade_bz.a_stock.bll.OneKeyServicesImpl;
import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.util.ArrayList;

/**
 * 一键归集---一键归集
 *
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/7/14
 */
public class OneKeyFragment extends AbsBaseFragment {
    /**
     * 依托的Activity
     */
    private TKFragmentActivity mActivity;
    /**
     * 该类的控制器
     */
    private OneKeyViewController mController;
    /**
     * 归集账号
     */
    private TextView mTvAccount;
    /**
     * 一键归集按钮
     */
    private Button mBtnClick;
    /**
     * 资金数据的
     */
    private ListView mListView;
    /**
     * 业务类
     */
    private OneKeyServicesImpl mServices;
    /**
     * 下方列表适配器
     */
    private OneKeyAdapter mAdapter;
    /**
     * 正在加载的旋转进度条
     */
    private LinearLayout mLinLoading;
    /**
     * 如果没有数据就显示该图片
     */
    private LinearLayout mLinHaveData;
    /**
     * 这个集合里只会存在主资金账号的信息
     */
    private ArrayList<OneKeyBean> mDataList;
    /**
     * 全局资源
     */
    private Resources mResources;
    /**
     * 当前选中的数据集
     */
    private OneKeyBean mMsgBean;
    private TextView mTvFundUnit;
    private ArrayList<OneKeyBean> mOneKeyBeans=new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_a_one_key, null);
        findViews(rootView);
        initData();
        setListeners();
        initViews();
        return rootView;
    }
    @Override
    public void onResume() {
        super.onResume();
            mServices.requestOneKeyMessage();
    }
    @Override
    protected void findViews(View view) {
        mTvAccount = (TextView) view.findViewById(R.id.tv_onek_account);
        mTvFundUnit = (TextView) view.findViewById(R.id.tv_fund_unit);
        mBtnClick = (Button) view.findViewById(R.id.btn_onek_click);
        mListView = (ListView) view.findViewById(R.id.lv_one_key);
        mLinLoading = (LinearLayout) view.findViewById(R.id.ll_list_loading);
        mLinHaveData = (LinearLayout) view.findViewById(R.id.lin_have_data_set);
    }

    @Override
    protected void setListeners() {
        registerListener(ListenerControllerAdapter.ON_CLICK, mBtnClick, mController);
    }

    @Override
    public void closeFrameworkKeyBroad() {
        super.closeFrameworkKeyBroad();
    }

    @Override
    protected void initData() {
        mActivity = (TKFragmentActivity) getActivity();
        mController = new OneKeyViewController(this);
        mServices = new OneKeyServicesImpl(this);
        mAdapter = new OneKeyAdapter(mActivity);
        mResources = mActivity.getResources();
        mDataList = new ArrayList<OneKeyBean>();

    }


    @Override
    protected void initViews() {
        mListView.addHeaderView(LayoutInflater.from(getActivity()).inflate(R.layout.head_a_one_key, null));
        mListView.setDivider(null);
        setTheme();
    }

    @Override
    protected void setTheme() {

    }

    /**
     * 得到账户归集数据列表
     *
     * @param dataList
     */
    public void onGetMoneySelectList(ArrayList<OneKeyBean> dataList) {
        if (dataList == null || dataList.size() == 0) {
            mLinLoading.setVisibility(View.GONE);
            mLinHaveData.setVisibility(View.VISIBLE);
        } else {
            mOneKeyBeans = dataList;
            mLinLoading.setVisibility(View.GONE);
            mLinHaveData.setVisibility(View.VISIBLE);
            //给下方列表设值
            mAdapter.setListData(dataList);
            mListView.setAdapter(mAdapter);
            //找到主资金账号
            for (OneKeyBean bean : dataList) {
                if (bean.getFundseq().equals("0")) {
                    mDataList.add(bean);
                    mMsgBean = bean;
                }
            }
            // 判断是否取到了主资金账号
            if (mDataList == null || mDataList.size() == 0) {
                mTvAccount.setHint(mResources.getString(R.string.one_key_no_text_no));
            } else {
                OneKeyBean oneKeyBean = mDataList.get(0);
                mTvAccount.setText(mResources.getString(R.string.one_key_zhu2)
                        + oneKeyBean.getFundid() + oneKeyBean.getBank_name());
                mTvFundUnit.setText(oneKeyBean.getMoney_type_name());
            }
        }
    }


    /**
     * 点击 ‘一键归集’按钮
     */
    public void onClickBtnOneKey() {
        if (mMsgBean == null) {
            ToastUtils.toast(mActivity, this.getString(R.string.one_key1));
            return;
        }
        mBtnClick.setClickable(false);
        mServices.requestOneKey(mOneKeyBeans, mMsgBean.getMoney_type(), mMsgBean.getFundid());
    }

    public void onOnKeyResult(String toast) {
        mBtnClick.setClickable(true);
        ToastUtils.toast(getActivity(),toast);
        mServices.requestOneKeyMessage();
    }
}


class OneKeyViewController extends AbsBaseController implements View.OnClickListener {

    private OneKeyFragment mFragment;

    public OneKeyViewController(OneKeyFragment mOneKeyFragment) {
        mFragment = mOneKeyFragment;
    }

    @Override
    public void register(int eventType, View view) {
        switch (eventType) {
            case ON_CLICK:
                view.setOnClickListener(this);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        int resId = v.getId();
        if (resId == R.id.btn_onek_click) {//‘一键归集按钮’
            mFragment.onClickBtnOneKey();
        }
    }
}
