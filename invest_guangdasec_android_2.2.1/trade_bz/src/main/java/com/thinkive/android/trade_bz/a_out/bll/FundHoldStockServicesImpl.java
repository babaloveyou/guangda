package com.thinkive.android.trade_bz.a_out.bll;

import android.content.Context;
import android.os.Bundle;

import com.thinkive.android.trade_bz.a_out.bean.FundHoldBean;
import com.thinkive.android.trade_bz.a_stock.bean.MoneySelectBean;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.request.Request301504;
import com.thinkive.android.trade_bz.request.Fund302012;
import com.thinkive.android.trade_bz.a_out.fragment.FundHoldeCountFragment;
import com.thinkive.android.trade_bz.utils.LoadingDialogUtil;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *  基金交易--持仓
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/8/7
 */

public class FundHoldStockServicesImpl {
    private FundHoldeCountFragment mFragment = null;
    private LoadingDialogUtil loadingDialogUtil;//请求数据状态框 工具类

    public FundHoldStockServicesImpl(FundHoldeCountFragment fragment) {
        mFragment = fragment;
        loadingDialogUtil = new LoadingDialogUtil(fragment.getContext());
    }

    /**
     * 请求当持仓基金列表
     */
    public void requestHoldStockList() {
        HashMap<String, String> map = new HashMap<String, String>();
        new Fund302012(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                ArrayList<FundHoldBean> dataList = bundle.getParcelableArrayList(Fund302012.BUNDLE_KEY_FUND_RANSOM);
                mFragment.onGetFundHoldStockData(dataList);
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(Fund302012.ERROR_INFO));
            }
        }).request();
    }

    /**
     * 当前基金持仓数和基金持仓市值
     */
    public void requestHoldFundData() {
        loadingDialogUtil.showLoadingDialog(0);
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("money_type", "0");
        new Request301504(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                loadingDialogUtil.hideDialog();
                MoneySelectBean data = (MoneySelectBean)bundle.getSerializable(Request301504.BUNDLE_KEY_MYHOLD_HEAD);
                mFragment.getFundHoldMessage(data);
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                loadingDialogUtil.hideDialog();
                ToastUtils.toast(context, bundle.getString(Request301504.ERROR_INFO));
            }
        }).request();
    }
}
