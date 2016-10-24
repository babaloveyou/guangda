package com.thinkive.android.trade_bz.a_out.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.thinkive.framework.compatible.ListenerControllerAdapter;
import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_out.activity.FundListActivity;
import com.thinkive.android.trade_bz.a_stock.bean.PublicUseBean;
import com.thinkive.android.trade_bz.a_out.bean.FundHoldBean;
import com.thinkive.android.trade_bz.a_out.bll.FundRansomServicesImpl;
import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;
import com.thinkive.android.trade_bz.a_out.activity.FundRansomActivity;
import com.thinkive.android.trade_bz.a_stock.fragment.AbsBaseFragment;
import com.thinkive.android.trade_bz.utils.TradeUtils;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.util.ArrayList;

/**
 * 基金交易----赎回
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/7/6
 */
public class FundRansomFragment extends AbsBaseFragment {
    /**
     * 基金交易的Activity
     */
    private FundRansomActivity mActivity;
    /**
     * 业务类
     */
    private FundRansomServicesImpl mServices;
    /**
     * 该类的控制器
     */
    private FundRansomViewController mController;
    /**
     * 选择基金名称
     */
    private TextView mTvFundName;
    /**
     * 基金净值
     */
    private TextView mTvFundNetValue;
    /**
     *  输入赎回数量
     */
    private EditText mEtRansomCount;
    /**
     * 显示最多赎回数量
     */
    private TextView mTvMaxCount;
    /**
     * 赎回按钮
     */
    private Button mBtClickRansom;
    /**
     * 上限
     */
    public TextView mTvUpLimit;
    /**
     * 点击全部
     */
    private TextView mTvUpLimitClick;
    /**
     * 基金代码
     */
    private String mFundCode = "";
    /**
     * 基金公司
     */
    private String mFundCompany = "";
    /**
     * 可赎回持有基金
     */
    private ArrayList<FundHoldBean> mDataList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_fund_ransom_layout, null);
        findViews(rootView);
        initData();
        setListeners();
        initViews();
        return rootView;
    }

    @Override
    public void onPause() {
        super.onPause();
        TradeUtils.hideSystemKeyBoard(mActivity);
    }


    @Override
    protected void findViews(View view) {
        mTvFundName =(TextView)view.findViewById(R.id.tv_ransom_fundname);
        mTvFundNetValue =(TextView)view.findViewById(R.id.tv_fund_ransom_netvalue);
        mEtRansomCount =(EditText)view.findViewById(R.id.edt_fund_ransom_count);
        mTvMaxCount =(TextView)view.findViewById(R.id.tv_fund_ransom_max);
        mBtClickRansom =(Button)view.findViewById(R.id.btn_fund_ransom);
        mTvUpLimit = (TextView) view.findViewById(R.id.tv_fund_ransom_max_limit);
        mTvUpLimitClick = (TextView) view.findViewById(R.id.tv_fund_click_ransom_max);
    }

    @Override
    protected void setListeners() {
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvFundName, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mBtClickRansom, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvUpLimitClick, mController);
    }

    @Override
    protected void initData() {
        mActivity =(FundRansomActivity)getActivity();
        mServices=new FundRansomServicesImpl(this);
        mController=new FundRansomViewController(this);
    }

    @Override
    protected void initViews() {
        //请求赎回数据列表
        mServices.requestRansomList();
        setTheme();
    }

    @Override
    protected void setTheme() {

    }

    /**
     *得到当前可赎回的数据列表
     */
    public void onGetRansomData(ArrayList<FundHoldBean> dataList) {
        //若没有数据
        if(dataList == null || dataList.size()==0) {
            mTvFundName.setHint(mActivity.getResources().getString(R.string.fund_trade_name_hint_no));
        }else {
            mDataList = dataList;
            mTvFundName.setHint(mActivity.getResources().getString(R.string.fund_input_hint2));
        }
    }

    /**
     * 点击跳转选择基金
     */
    public void onClickSelectFund(){
        if(mDataList == null || mDataList.size() == 0){
            ToastUtils.toast(mActivity,mActivity.getResources().getString(R.string.fund_trade_name_hint_no));
        }else{
            Intent intent = new Intent(mActivity,FundListActivity.class);
            intent.putParcelableArrayListExtra("data_select",mDataList);
            intent.putExtra("useType","fund_hold");
            startActivityForResult(intent,89);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data == null)
            return;
        int position = data.getIntExtra("data_select_result",0);
        FundHoldBean bean =  mDataList.get(position);
        mTvFundName.setText(bean.getFund_name());
        mTvFundNetValue.setText(bean.getNav());
        mTvUpLimit.setText(bean.getEnable_amount());
        mTvMaxCount.setText(bean.getEnable_shares());
        mFundCompany = bean.getFund_company();
        mFundCode = bean.getFund_code();
    }

    /**
     * 点击全部
     */
    public void onClickAll(){
        //把上限数量直接设置到金额输入框
        mEtRansomCount.setText(mTvUpLimit.getText().toString());
    }
    /**
     * 点击赎回按钮所执行的操作
     */
    public void clickBtnRansom(){
        if (TradeUtils.isFastClick()) {
            return;
        }
        String name = mTvFundName.getText().toString();
        String count = mEtRansomCount.getText().toString();
        if(TextUtils.isEmpty(name) || TextUtils.isEmpty(mFundCode)){
            ToastUtils.toast(mActivity, mActivity.getResources().getString(R.string.fund_trade_name_hint));
            return;
        }
        if(TextUtils.isEmpty(count)){
            ToastUtils.toast(mActivity, mActivity.getResources().getString(R.string.fund_trade_ransom_hint));
            return;
        }
        TradeUtils.hideSystemKeyBoard(mActivity);
        mServices.requestRansomResult(mFundCode,count,mFundCompany);
    }

    /**
     * 得到当前赎回的返回结果
     */
    public void getResultForRamsom(PublicUseBean bean){
        ToastUtils.toast(mActivity, mActivity.getResources().getString(R.string.fund_trade_ramson_result) + bean.getSerial_no());
        mServices.requestRansomList();
        clearAllData();
    }

    /**
     * 清除所有数据
     */
    private void clearAllData(){
        mTvFundNetValue.setText("");
        mTvFundName.setText("");
        mTvMaxCount.setText("");
        mTvUpLimit.setText("");
        mEtRansomCount.setText("");
        mFundCode = "";
        mFundCompany = "";
    }
}

class FundRansomViewController extends AbsBaseController implements
        View.OnClickListener {

    private FundRansomFragment mFragment;

    public FundRansomViewController(FundRansomFragment fragment) {
        mFragment = fragment;
    }

    @Override
    public void register(int i, View view) {
        switch (i) {
            case ON_CLICK:
                view.setOnClickListener(this);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        if (TradeUtils.isFastClick()) {
            return;
        }
        int resId = v.getId();
        if (resId == R.id.tv_ransom_fundname) { // 点击选择基金
            mFragment.onClickSelectFund();
        } else if (resId == R.id.btn_fund_ransom) { //按钮
            mFragment.clickBtnRansom();
        } else if (resId == R.id.tv_fund_click_ransom_max) {//点击全部
            mFragment.onClickAll();
        }
    }
}
