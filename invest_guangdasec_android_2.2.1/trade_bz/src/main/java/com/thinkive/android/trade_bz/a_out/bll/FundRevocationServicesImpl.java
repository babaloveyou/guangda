package com.thinkive.android.trade_bz.a_out.bll;

import android.content.Context;
import android.os.Bundle;

import com.thinkive.android.trade_bz.a_out.bean.FundRevocationBean;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.request.Fund302009;
import com.thinkive.android.trade_bz.request.Fund302019;
import com.thinkive.android.trade_bz.a_out.fragment.FundRevocationFragment;
import com.thinkive.android.trade_bz.utils.LoadingDialogUtil;
import com.thinkive.android.trade_bz.utils.ToastUtils;
import com.thinkive.android.trade_bz.utils.TradeUtils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 基金交易--查询--基金撤单
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/8/7
 */

public class FundRevocationServicesImpl {
    private FundRevocationFragment mFragment = null;
    private LoadingDialogUtil loadingDialogUtil;//请求数据状态框 工具类

    public FundRevocationServicesImpl(FundRevocationFragment fragment) {
        mFragment = fragment;
        loadingDialogUtil = new LoadingDialogUtil(fragment.getContext());
    }

    /**
     * 请求基金撤单数据列表
     */
    public void requestRevocation() {
        HashMap<String, String> map = new HashMap<String, String>();
        new Fund302019(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                ArrayList<FundRevocationBean> data = bundle.getParcelableArrayList(Fund302019.BUNDLE_KEY_FUND_REVOCATION);
                for(FundRevocationBean bean : data){
                    bean.setBalance(TradeUtils.formatDouble2(bean.getBalance()));
                    bean.setDeal_balance(TradeUtils.formatDouble2(bean.getDeal_balance()));
                }
                mFragment.onGetRevocationData(data);
            }

            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(Fund302019.ERROR_INFO));
            }
        }).request();
    }

    /**
     * 请求执行基金撤单委托
     *
     * @param fund_code
     *         要撤销委托的基金的代码
     * @param allot_date
     *         委托日期
     * @param entrust_no
     *         委托编号
     */
    public void requestExecRevocation(String fund_code, String allot_date, String entrust_no,String fundCompany) {
        loadingDialogUtil.showLoadingDialog(0);
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("fund_code", fund_code);
        allot_date = allot_date.replaceAll("-", "");
        map.put("allot_date", allot_date);
        map.put("entrust_no", entrust_no);
        map.put("fund_company", fundCompany);
        new Fund302009(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                loadingDialogUtil.hideDialog();
                mFragment.onExecRevocationSuccess(bundle.getString(Fund302009.BUNDLE_KEY_REVOCATION));
            }

            @Override
            public void onFailed(Context context, Bundle bundle) {
                loadingDialogUtil.hideDialog();
                ToastUtils.toast(context, bundle.getString(Fund302009.ERROR_INFO));
            }
        }).request();
    }
}
