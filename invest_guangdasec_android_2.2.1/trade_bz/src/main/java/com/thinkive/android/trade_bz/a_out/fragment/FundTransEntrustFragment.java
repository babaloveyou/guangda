package com.thinkive.android.trade_bz.a_out.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.thinkive.android.trade_bz.a_out.bll.FundTransEntrustServiceImpl;
import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;
import com.thinkive.android.trade_bz.a_out.activity.FundTransferEntrustActivity;
import com.thinkive.android.trade_bz.a_stock.fragment.AbsBaseFragment;
import com.thinkive.android.trade_bz.utils.TradeUtils;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.util.ArrayList;

/**
 * 场外基金转托
 * @author 张雪梅
 * @company Thinkive
 * @date 15/10/26
 */
public class FundTransEntrustFragment extends AbsBaseFragment {
    private FundTransferEntrustActivity mActivity;
    private FundTransController mController;
    private FundTransEntrustServiceImpl mServices;
    /**
     * 转托基金名称
     */
    private TextView mTvTransOutName;
    /**
     * 转托数量
     */
    private EditText mEdtTransNum;
    /**
     * 可转托数量
     */
    private TextView mTvCanTransNum;
    /**
     * 基金净值
     */
    private TextView mTvFundProfit;
    /**
     * 基金公司
     */
    private TextView mTvFundCompany;
    /**
     * 机构代码
     */
    private EditText mEdtOrganCode;
    /**
     * 转托按钮
     */
    private Button mBtnTransEntrust;
    /**
     * 基金代码
     */
    private String mFundCode = "";
    /**
     * 基金账户
     */
    private String mFundAccount = "";
    /**
     * 基金公司
     */
    private String mFundCompany = "";
    /**
     * 持有基金数据集
     */
    private ArrayList<FundHoldBean> mDataList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_fund_transfer_entrust, null);
        findViews(rootView);
        initData();
        setListeners();
        initViews();
        return rootView;
    }

    @Override
    protected void findViews(View view) {
        mTvTransOutName=(TextView)view.findViewById(R.id.tv_out_fund_trans_out);
        mEdtTransNum=(EditText)view.findViewById(R.id.edt_trans_num);
        mTvCanTransNum=(TextView)view.findViewById(R.id.tv_can_trans_num);
        mBtnTransEntrust=(Button)view.findViewById(R.id.btn_fund_trans);
        mTvFundProfit=(TextView)view.findViewById(R.id.tv_out_fund_profit);
        mTvFundCompany=(TextView)view.findViewById(R.id.tv_out_fund_company);
        mEdtOrganCode=(EditText)view.findViewById(R.id.edt_trans_organ_code);
    }

    @Override
    protected void setListeners() {
        registerListener(ListenerControllerAdapter.ON_CLICK,mTvTransOutName,mController);
        registerListener(ListenerControllerAdapter.ON_CLICK,mBtnTransEntrust,mController);
    }

    @Override
    protected void initData() {
        mActivity=(FundTransferEntrustActivity)getActivity();
        mController=new FundTransController(this);
        mServices=new FundTransEntrustServiceImpl(this);
    }

    @Override
    protected void initViews() {
        //请求基金持仓
        mServices.requestHoldStockList();
    }

    @Override
    protected void setTheme() {

    }
    /**
     * 得到可转托基金的数据列表
     * @param dataList
     */
    public void onGetCanTransDataList(ArrayList<FundHoldBean> dataList){
        if(dataList == null || dataList.size() == 0){
            mTvTransOutName.setHint(mActivity.getResources().getString(R.string.fund_trans_no));
        }else{
            mDataList = dataList;
            mTvTransOutName.setHint(mActivity.getResources().getString(R.string.fund_input_hint2));
        }
    }
    /**
     * 点击选择基金
     */
    public void onClickLinForPop(){
        if(mDataList == null || mDataList.size() == 0){
            ToastUtils.toast(mActivity,mActivity.getResources().getString(R.string.fund_trans_no));
        }else{
            Intent intent = new Intent(mActivity,FundListActivity.class);
            intent.putParcelableArrayListExtra("data_select",mDataList);
            intent.putExtra("useType","fund_hold");
            startActivityForResult(intent,88);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data == null)
            return;
        int position = data.getIntExtra("data_select_result",0);
        FundHoldBean bean =  mDataList.get(position);
        mTvTransOutName.setText(bean.getFund_name());
        mTvFundProfit.setText(bean.getNav());
        mTvFundCompany.setText(bean.getFund_company());
        mTvCanTransNum.setText(bean.getEnable_shares());
        mFundCompany = bean.getFund_company();
        mFundCode = bean.getFund_code();
        mFundAccount = bean.getFund_account();
    }

    /**
     * 按钮提交
     */
    public void onClickBtnCommit(){
        if (TradeUtils.isFastClick()) {
            return;
        }
        String name = mTvTransOutName.getText().toString();
        String num = mEdtTransNum.getText().toString();
        String organCode = mEdtOrganCode.getText().toString();
        if(TextUtils.isEmpty(name)){
            ToastUtils.toast(mActivity, mActivity.getResources().getString(R.string.fund_transe3));
            return;
        }
        if(TextUtils.isEmpty(organCode)){
            ToastUtils.toast(mActivity, mActivity.getResources().getString(R.string.fund_transe7));
            return;
        }
        if(TextUtils.isEmpty(num)){
            ToastUtils.toast(mActivity, mActivity.getResources().getString(R.string.fund_trans_num));
            return;
        }
        mServices.requestFundTransEntrust(mFundCode,mFundAccount,mFundCompany,num);
    }

    /**
     * 得到转托结果
     */
    public void onGetTransEntrustResult(PublicUseBean result){
        mServices.requestHoldStockList();
        ToastUtils.toast(mActivity, mActivity.getResources().getString(R.string.fund_trans_ok) + result.getEntrust_no());
        clearAllData();
    }
    /**
     * 清除所有数据
     */
    private void clearAllData(){
        mTvTransOutName.setText("");
        mTvFundProfit.setText("");
        mTvFundCompany.setText("");
        mTvCanTransNum.setText("");
        mEdtOrganCode.setText("");
        mEdtTransNum.setText("");
        mFundAccount = "";
        mFundCode = "";
        mFundCompany = "";
        TradeUtils.hideSystemKeyBoard(mActivity);
    }
}

class FundTransController extends AbsBaseController implements View.OnClickListener {

    private FundTransEntrustFragment mFragment;

    public FundTransController(FundTransEntrustFragment fragment) {
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
        if (resId == R.id.tv_out_fund_trans_out) { // 选择基金
            mFragment.onClickLinForPop();
        } else if (resId == R.id.btn_fund_trans) { //按钮
            mFragment.onClickBtnCommit();
        }
    }
}



