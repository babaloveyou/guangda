package com.thinkive.android.trade_bz.a_rr.fragment;

import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.thinkive.framework.compatible.ListenerController;
import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_rr.activity.RStockReturnStockActivity;
import com.thinkive.android.trade_bz.a_rr.bean.RStockToStockLinkBean;
import com.thinkive.android.trade_bz.a_rr.bll.RStockReturnOrderStockServiceImpl;
import com.thinkive.android.trade_bz.a_stock.bean.MyStoreStockBean;
import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;
import com.thinkive.android.trade_bz.a_stock.fragment.AbsBaseFragment;
import com.thinkive.android.trade_bz.others.tools.StockStoreQueryManager;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.util.ArrayList;

/**
 * 融资融券现券还券（直接还券）
 *
 * @author 张雪梅
 * @date 2016/7/18
 */

public class RStockReturnOrderStockFragment extends AbsBaseFragment {
    private RStockReturnStockActivity mActivity;
    private RStockReturnOrderStockController mController;
    private RStockReturnOrderStockServiceImpl mServices;
    private Resources mResources;
    /**
     * 提交委托
     */
    private Button mBtnClickCommit;
    /**
     * 证券编码
     */
    private TextView mTvSelectCode;
    /**
     * 委托数量
     */
    private EditText mEdtInputNum;
    /**
     * 最大归还数量
     */
    private TextView mTvMaxCanReturn;
    /**
     * 点击全部
     */
    private TextView mTvClickAll;
    /**
     * 正在加载
     */
    private LinearLayout mLinLoading;
    /**
     * 股票代码
     */
    private String mStockCode = "";
    /**
     * 我的持仓数据集
     */
    private ArrayList<MyStoreStockBean> mHoldDataList;
    /**
     * 当前证券联动bean
     */
    private RStockToStockLinkBean mStockLinkBean;
    private StockStoreQueryManager mQueryManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mRootView = inflater.inflate(R.layout.fragment_r_stock_return_stock, null);
        findViews(mRootView);
        initData();
        setListeners();
        initViews();
        return mRootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mQueryManager.setPopupwindowWidth(mTvSelectCode.getWidth()*2/3);
        mQueryManager.setPopupWindowReserveWidthReferView(mTvSelectCode);
        mQueryManager.initListViewPopupwindow(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MyStoreStockBean bean = mQueryManager.getSearchStocksAdapter().getItem(position);
                mStockCode = bean.getStock_code();
                mServices.requestStockLink(mStockCode); //联动最大可还
                mTvSelectCode.setText(mStockCode + " " + bean.getStock_name());
                mQueryManager.dismissQueryPopupWindow();
            }
        });
    }

    @Override
    protected void findViews(View view) {
        mLinLoading = (LinearLayout) view.findViewById(R.id.lin_loading_set);
        mBtnClickCommit = (Button) view.findViewById(R.id.btn_r_stock_return_commit);
        mTvSelectCode = (TextView) view.findViewById(R.id.tv_r_stock_code);
        mEdtInputNum = (EditText) view.findViewById(R.id.edt_stock_num);
        mTvMaxCanReturn = (TextView) view.findViewById(R.id.tv_r_return_stock_max_num);
        mTvClickAll = (TextView) view.findViewById(R.id.tv_r_return_stock_all);
    }

    @Override
    protected void initData() {
        mActivity = (RStockReturnStockActivity) getActivity();
        mQueryManager = StockStoreQueryManager.getInstance(mActivity);
        mResources = mActivity.getResources();
        mServices = new RStockReturnOrderStockServiceImpl(this);
        mController = new RStockReturnOrderStockController(this);
    }

    @Override
    protected void setListeners() {
        registerListener(ListenerController.ON_CLICK, mBtnClickCommit, mController);
        registerListener(ListenerController.ON_CLICK, mTvSelectCode, mController);
        registerListener(ListenerController.ON_CLICK, mTvClickAll, mController);
    }

    @Override
    protected void initViews() {
        mServices.getHoldList();
        setTheme();
    }

    @Override
    protected void setTheme() {

    }

    /**
     * 持仓列表数据表
     */
    public void getStoreData(ArrayList<MyStoreStockBean> dataList) {
        mLinLoading.setVisibility(View.GONE);
        if (dataList == null || dataList.size() == 0) {
            mTvSelectCode.setHint(R.string.r_select_not);
        } else {
            mTvSelectCode.setHint(R.string.one_key_hint_select);
            mHoldDataList = dataList;
        }
    }

    /**
     * 点击选择证券
     */
    public void onClickStockCode() {
        mQueryManager.dismissQueryPopupWindow();
        if (mHoldDataList != null && mHoldDataList.size() > 0) {
           mQueryManager.execQueryStoreList(mHoldDataList,mTvSelectCode);
        } else {
            ToastUtils.toast(mActivity, mActivity.getResources().getString(R.string.r_select_not));
        }
    }



    /**
     * 获得联动结果
     */
    public void onGetLinkResult(RStockToStockLinkBean data) {
        mStockLinkBean = data;
        mTvMaxCanReturn.setText(data.getEnable_amount());
    }

    /**
     * 点击全部
     */
    public void onClickAll() {
        if (!TextUtils.isEmpty(mEdtInputNum.getText()) && !"--".equals(mEdtInputNum.getText().toString())) {
            mEdtInputNum.setText(mTvMaxCanReturn.getText().toString());
        }
    }



    /**
     * 提交委托
     */
    public void onClickCommit() {
        String entrustNum = mEdtInputNum.getText().toString().trim();
        if (TextUtils.isEmpty(mStockCode) || mStockLinkBean == null) {
            ToastUtils.toast(mActivity, mResources.getString(R.string.r_property_list4));
            return;
        }
        if (TextUtils.isEmpty(entrustNum)) {
            ToastUtils.toast(mActivity, mResources.getString(R.string.trade_toast_input_amount));
            return;
        }
        mServices.requestCommit(mStockLinkBean, entrustNum);
    }

    /**
     * 清空所有数据
     */
    public void clearAllData() {
        mStockCode = "";
        mStockLinkBean = null;
        mTvSelectCode.setText("");
        mTvMaxCanReturn.setText("");
        mEdtInputNum.setText("");
    }

}

/**
 * 本类控制器
 */
class RStockReturnOrderStockController extends AbsBaseController
        implements View.OnClickListener {

    private RStockReturnOrderStockFragment mFragment;

    public RStockReturnOrderStockController(RStockReturnOrderStockFragment fragment) {
        mFragment = fragment;
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
        if (resId == R.id.btn_r_stock_return_commit) {
            mFragment.onClickCommit();
        }  else if (resId == R.id.tv_r_stock_code) {
            mFragment.onClickStockCode();
        } else if (resId == R.id.tv_r_return_stock_all) {
            mFragment.onClickAll();
        }
    }
}