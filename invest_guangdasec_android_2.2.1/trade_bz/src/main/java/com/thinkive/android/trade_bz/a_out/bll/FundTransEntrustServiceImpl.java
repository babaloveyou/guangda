package com.thinkive.android.trade_bz.a_out.bll;

import android.content.Context;
import android.os.Bundle;

import com.thinkive.android.trade_bz.request.Fund302058;
import com.thinkive.android.trade_bz.a_stock.bean.PublicUseBean;
import com.thinkive.android.trade_bz.a_out.bean.FundHoldBean;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.request.Fund302012;
import com.thinkive.android.trade_bz.a_out.fragment.FundTransEntrustFragment;
import com.thinkive.android.trade_bz.utils.LoadingDialogUtil;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *  场外基金转托管
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/10/27.
 */
public class FundTransEntrustServiceImpl {

    private FundTransEntrustFragment mFragment;
    private LoadingDialogUtil loadingDialogUtil;//请求数据状态框 工具类

    public FundTransEntrustServiceImpl(FundTransEntrustFragment fragment) {
        mFragment=fragment;
        loadingDialogUtil = new LoadingDialogUtil(fragment.getContext());
    }

    /**
     * 请求转托管
     */
    public void requestFundTransEntrust(String code,String account,String company,String num) {
        loadingDialogUtil.showLoadingDialog(0);
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("fund_code",code);
        map.put("fund_company",company);
        map.put("fund_account",account);
        map.put("balance",num);
        new Fund302058(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                loadingDialogUtil.hideDialog();
                PublicUseBean dataList =(PublicUseBean) bundle.getSerializable(Fund302058.BUNDLE_KEY_RESULT_TRANS);
                mFragment.onGetTransEntrustResult(dataList);
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                loadingDialogUtil.hideDialog();
                ToastUtils.toast(context, bundle.getString(Fund302058.ERROR_INFO));
            }
        }).request();
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
                mFragment.onGetCanTransDataList(dataList);
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(Fund302012.ERROR_INFO));
            }
        }).request();
    }
}
