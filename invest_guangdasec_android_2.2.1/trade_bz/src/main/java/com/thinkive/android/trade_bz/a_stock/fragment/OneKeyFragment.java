package com.thinkive.android.trade_bz.a_stock.fragment;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.thinkive.framework.compatible.ListenerControllerAdapter;
import com.android.thinkive.framework.compatible.TKFragmentActivity;
import com.android.thinkive.framework.keyboard.BaseKeyboard;
import com.thinkive.android.trade_bz.keyboard.KeyboardManager;
import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.activity.SelectListActivity;
import com.thinkive.android.trade_bz.a_stock.bean.OneKeyBean;
import com.thinkive.android.trade_bz.a_stock.bll.OneKeyServicesImpl;
import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;
import com.thinkive.android.trade_bz.others.tools.TradeTools;
import com.thinkive.android.trade_bz.a_stock.adapter.OneKeyAdapter;
import com.thinkive.android.trade_bz.utils.ToastUtils;
import com.thinkive.android.trade_bz.utils.TradeUtils;

import java.util.ArrayList;

/**
 * 一键归集---一键归集
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
     * 输入资金密码
     */
    private EditText mEdtPwd;
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
    /**
     * 密码键盘
     */
    private KeyboardManager mKeyboardForFundPwd;

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
    protected void findViews(View view) {
        mTvAccount = (TextView) view.findViewById(R.id.tv_onek_account);
        mEdtPwd = (EditText) view.findViewById(R.id.edt_onek_pwd);
        mBtnClick = (Button) view.findViewById(R.id.btn_onek_click);
        mListView = (ListView) view.findViewById(R.id.lv_one_key);
        mLinLoading = (LinearLayout) view.findViewById(R.id.ll_list_loading);
        mLinHaveData = (LinearLayout) view.findViewById(R.id.lin_have_data_set);
    }

    @Override
    protected void setListeners() {
        registerListener(ListenerControllerAdapter.ON_CLICK, mBtnClick, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvAccount, mController);
    }

    @Override
    public void closeFrameworkKeyBroad() {
        super.closeFrameworkKeyBroad();
        mKeyboardForFundPwd.dismiss();
    }

    @Override
    protected void initData() {
        mActivity = (TKFragmentActivity) getActivity();
        mController = new OneKeyViewController(this);
        mServices = new OneKeyServicesImpl(this);
        mAdapter = new OneKeyAdapter(mActivity);
        mResources = mActivity.getResources();
        mDataList = new  ArrayList<OneKeyBean>();
        mServices.requestOneKeyMessage();
    }

    @Override
    protected void initViews() {
        mListView.addHeaderView(LayoutInflater.from(getActivity()).inflate(R.layout.head_a_one_key, null));
        //添加自绘键盘
        mKeyboardForFundPwd = TradeTools.initKeyBoard(mActivity, mEdtPwd,KeyboardManager.KEYBOARD_TYPE_RANDOM_DIGITAL, BaseKeyboard.THEME_LIGHT);
        setTheme();
    }

    @Override
    protected void setTheme() {

    }

    /**
     * 得到账户归集数据列表
     * @param dataList
     */
    public void onGetMoneySelectList(ArrayList<OneKeyBean> dataList) {
        if (dataList == null || dataList.size() == 0) {
            mLinLoading.setVisibility(View.GONE);
            mLinHaveData.setVisibility(View.VISIBLE);
            mTvAccount.setHint(mResources.getString(R.string.one_key_no_text_no));
        } else {
            mLinLoading.setVisibility(View.GONE);
            mLinHaveData.setVisibility(View.VISIBLE);
            //给下方列表设值
            mAdapter.setListData(dataList);
            mListView.setAdapter(mAdapter);
            //找到主资金账号
            for (OneKeyBean bean : dataList) {
                if (bean.getFundseq().equals("0")) {
                    mDataList.add(bean);
                }
            }
            // 判断是否取到了主资金账号
            if (mDataList == null ||mDataList.size() == 0) {
                mTvAccount.setHint(mResources.getString(R.string.one_key_no_text_no));
            } else{
                mTvAccount.setHint(mResources.getString(R.string.one_key_hint_select));
            }
        }
    }
    /**
     * 点击跳转页面选择银行账号
     */
    public void onClickLayOutForPop(){
        if (TradeUtils.isFastClick()) {
            return;
        }
        if(mDataList == null || mDataList.size() == 0){
            ToastUtils.toast(mActivity,mResources.getString(R.string.one_key_no_text_no));
        }else{
            Intent intent = new Intent(mActivity,SelectListActivity.class);
            intent.putParcelableArrayListExtra("data_select",mDataList);
            intent.putExtra("useType","one_key");
            startActivityForResult(intent,80);
        }
    }

    /**
     * 选择账号完成时执行
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data == null)
            return;
        int position = data.getIntExtra("data_select_result",0);
        OneKeyBean bean =  mDataList.get(position);
        mMsgBean = bean;
        mTvAccount.setText(mResources.getString(R.string.one_key_zhu2)+""+bean.getBank_name()
                +"  "+bean.getFund_account()+" "+bean.getMoney_type_name());
    }

    /**
     * 点击 ‘一键归集’按钮
     */
    public void onClickBtnOneKey() {
        if (TradeUtils.isFastClick()) {
            return;
        }
        String pwd = mEdtPwd.getText().toString();
        if(mMsgBean == null){
            ToastUtils.toast(mActivity, this.getString(R.string.one_key1));
            return;
        }
        if (TextUtils.isEmpty(pwd)) {
            ToastUtils.toast(mActivity, this.getString(R.string.one_key_reminder));
            return;
        }
        mKeyboardForFundPwd.dismiss();
        mServices.requestOneKey(pwd,mMsgBean.getMoney_type(),mMsgBean.getFund_account());
    }

    /**
     * 得到一键归集的结果
     * @param data
     */
    public void getOneKeyResult(OneKeyBean data) {
        ToastUtils.toast(mActivity, getActivity().getResources().getString(R.string.one_key_text) + data.getRenum());
        mEdtPwd.setText("");
        mTvAccount.setText("");
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
        }else if (resId == R.id.tv_onek_account) {// 点击选择银行账号
            mFragment.onClickLayOutForPop();
        }
    }
}
