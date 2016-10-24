package com.thinkive.android.trade_bz.a_out.bll;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_out.bean.FundCompanyBean;
import com.thinkive.android.trade_bz.a_out.fragment.FundOpenAccountFragment;
import com.thinkive.android.trade_bz.a_stock.bll.BasicServiceImpl;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.others.tools.TradeLoginManager;
import com.thinkive.android.trade_bz.request.Fund302004;
import com.thinkive.android.trade_bz.request.Fund302030;
import com.thinkive.android.trade_bz.utils.LoadingDialogUtil;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 基金开户
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/5/9
 */

public class FundOpenAccountServicesImpl extends BasicServiceImpl {
    private FundOpenAccountFragment mFragment = null;
    private LoadingDialogUtil loadingDialogUtil;//请求数据状态框 工具类

    public FundOpenAccountServicesImpl(FundOpenAccountFragment fragment) {
        mFragment = fragment;
        loadingDialogUtil = new LoadingDialogUtil(fragment.getContext());
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onStop() {

    }

    /**
     * 请求基金账户数据列表
     */
    public void requestFundAccount(String fundCompany) {
        HashMap<String, String> map = new HashMap<String, String>();
        if(!TextUtils.isEmpty(fundCompany)){
            map.put("company_name",fundCompany);//基金公司名称
        }
        new Fund302004(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                ArrayList<FundCompanyBean> data = bundle.getParcelableArrayList(Fund302004.BUNDLE_KEY_FUND_COMPANY);
                mFragment.onGetFundCompanyData(data);
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(Fund302004.ERROR_INFO));
            }
        }).request();
    }

    /**
     * 请求基金开户
     * @param company
     */
    public void requestOpenAccount(String company) {
        loadingDialogUtil.showLoadingDialog(0);
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("stock_account",TradeLoginManager.sNormalUserInfo_shen_A.getStock_account());
        map.put("fund_company", company);
        new Fund302030(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                loadingDialogUtil.hideDialog();
                ToastUtils.toast(context, context.getResources().getString(R.string.fund_open_account));
                mFragment.onSuccessOpen();
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                loadingDialogUtil.hideDialog();
                ToastUtils.toast(context, bundle.getString(Fund302030.ERROR_INFO));
            }
        }).request();
    }
}
