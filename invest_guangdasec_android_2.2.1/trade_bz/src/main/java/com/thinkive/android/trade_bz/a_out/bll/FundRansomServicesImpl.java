package com.thinkive.android.trade_bz.a_out.bll;

import android.content.Context;
import android.os.Bundle;

import com.thinkive.android.trade_bz.a_stock.bean.PublicUseBean;
import com.thinkive.android.trade_bz.a_out.bean.FundHoldBean;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.request.Fund302008;
import com.thinkive.android.trade_bz.request.Fund302012;
import com.thinkive.android.trade_bz.a_out.fragment.FundRansomFragment;
import com.thinkive.android.trade_bz.utils.LoadingDialogUtil;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *  基金交易--赎回
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/8/7
 */

public class FundRansomServicesImpl {
    private FundRansomFragment mFragment = null;
    private LoadingDialogUtil loadingDialogUtil;//请求数据状态框 工具类

    public FundRansomServicesImpl(FundRansomFragment fragment) {
        mFragment = fragment;
        loadingDialogUtil = new LoadingDialogUtil(fragment.getContext());
    }

    /**
     * 请求当前额赎回基金列表
     */
    public void requestRansomList() {
        HashMap<String, String> map = new HashMap<String, String>();
        new Fund302012(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                ArrayList<FundHoldBean> dataList = bundle.getParcelableArrayList(Fund302012.BUNDLE_KEY_FUND_RANSOM);
                mFragment.onGetRansomData(dataList);
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(Fund302012.ERROR_INFO));
            }
        }).request();
    }

    /**
     * 请求当前赎回返回的结果
     */
    public void requestRansomResult(String code,String amount,String company) {
        loadingDialogUtil.showLoadingDialog(0);
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("fund_code",code);
        map.put("amount",amount);
        map.put("fund_company",company);
        new Fund302008(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                loadingDialogUtil.hideDialog();
                PublicUseBean dataList =(PublicUseBean) bundle.getSerializable(Fund302008.BUNDLE_KEY_RANSOM);
                mFragment.getResultForRamsom(dataList);
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                loadingDialogUtil.hideDialog();
                ToastUtils.toast(context, bundle.getString(Fund302008.ERROR_INFO));
            }
        }).request();
    }
}
