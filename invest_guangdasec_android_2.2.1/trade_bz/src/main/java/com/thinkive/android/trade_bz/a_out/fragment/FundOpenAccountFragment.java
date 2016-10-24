package com.thinkive.android.trade_bz.a_out.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_out.activity.FundListActivity;
import com.thinkive.android.trade_bz.a_out.activity.FundOpenAccountActivity;
import com.thinkive.android.trade_bz.a_out.bean.FundCompanyBean;
import com.thinkive.android.trade_bz.a_out.bll.FundOpenAccountServicesImpl;
import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;
import com.thinkive.android.trade_bz.a_stock.fragment.AbsBaseFragment;
import com.thinkive.android.trade_bz.utils.ToastUtils;
import com.thinkive.android.trade_bz.utils.TradeUtils;

import java.util.ArrayList;

/**
 * 基金开户
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/5/9
 */

public class FundOpenAccountFragment extends AbsBaseFragment {
    private FundOpenAccountActivity mActivity;
    private FundOpenAccountController mController;
    private FundOpenAccountServicesImpl mServices;
    private TextView mTvSelectFundCompany;
    private Button mBtnCommit;
    private ArrayList<FundCompanyBean> mDataList;
    private FundCompanyBean mDataBean;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_fund_open_account, null);
        findViews(rootView);
        initData();
        setListeners();
        initViews();
        return rootView;
    }

    @Override
    protected void findViews(View view) {
        mTvSelectFundCompany = (TextView)view.findViewById(R.id.tv_fund_open_account);
        mBtnCommit = (Button)view.findViewById(R.id.btn_fund_open_account);
    }

    @Override
    protected void setListeners() {
        registerListener(AbsBaseController.ON_CLICK,mTvSelectFundCompany,mController);
        registerListener(AbsBaseController.ON_CLICK,mBtnCommit,mController);
    }


    @Override
    protected void initData() {
        mActivity = (FundOpenAccountActivity) getActivity();
        mController = new FundOpenAccountController(this);
        mServices=new FundOpenAccountServicesImpl(this);
    }

    @Override
    protected void initViews() {
        //请求所有可开户的基金公司
        mServices.requestFundAccount("");
        setTheme();
    }

    @Override
    protected void setTheme() {

    }

    /**
     * 获得基金公司数据
     * @param dataList
     */
    public void onGetFundCompanyData(ArrayList<FundCompanyBean> dataList){
        if(dataList == null || dataList.size()==0){
            mTvSelectFundCompany.setHint(mActivity.getResources().getString(R.string.fund_account16));
        }else{
            mTvSelectFundCompany.setHint(mActivity.getResources().getString(R.string.fund_account12));
            mDataList = dataList;
        }
    }

    /**
     * 选择基金公司
     */
    public void onClickTvSelectCompany(){
        if(mDataList == null || mDataList.size() == 0){
            ToastUtils.toast(mActivity,mActivity.getResources().getString(R.string.fund_account16));
        }else{
            Intent intent = new Intent(mActivity,FundListActivity.class);
            intent.putParcelableArrayListExtra("data_select",mDataList);
            intent.putExtra("useType","fund_open");
            startActivityForResult(intent,869);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data == null)
            return;
        int position = data.getIntExtra("data_select_result",0);
        mDataBean = mDataList.get(position);
        mTvSelectFundCompany.setText(mDataBean.getCompany_name());
    }

    /**
     * 提交按钮
     */
    public void onClickBtnCommit(){
        if (TradeUtils.isFastClick()) {
            return;
        }
        if(mDataBean == null){
            ToastUtils.toast(mActivity, R.string.fund_account11);
        }else {
            mServices.requestOpenAccount(mDataBean.getFund_company());
        }
    }

    /**
     * 基金开户成功后，清除界面数据
     */
    public void onSuccessOpen(){
        mTvSelectFundCompany.setText("");
        mDataBean = null;
    }
}

/**
 *  基金账户查询类的控制器
 */
class FundOpenAccountController extends AbsBaseController implements
        View.OnClickListener {

    private FundOpenAccountFragment mFragment = null;

    public FundOpenAccountController(FundOpenAccountFragment Fragment) {
        mFragment = Fragment;
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
        if (v.getId() == R.id.tv_fund_open_account){
            mFragment.onClickTvSelectCompany();
        }else if(v.getId() == R.id.btn_fund_open_account){
            mFragment.onClickBtnCommit();
        }
    }
}
