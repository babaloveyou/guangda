package com.thinkive.android.trade_bz.a_hk.bll;

import android.content.Context;
import android.os.Bundle;

import com.thinkive.android.trade_bz.a_hk.bean.HkRevocationBean;
import com.thinkive.android.trade_bz.a_hk.fragment.HKRevocationFragment;
import com.thinkive.android.trade_bz.a_stock.bll.BasicServiceImpl;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.others.tools.TradeLoginManager;
import com.thinkive.android.trade_bz.request.HK301604;
import com.thinkive.android.trade_bz.request.HK301610;
import com.thinkive.android.trade_bz.utils.LoadingDialogUtil;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * （1）撤单列表的业务类
 * （2）点击撤单按钮后，返回的撤单结果业务类
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/11/2
 */

public class HKRevocationServicesImpl extends BasicServiceImpl {

    private HKRevocationFragment mRevocationFragment = null;
    private LoadingDialogUtil loadingDialogUtil;//请求数据状态框 工具类
    public HKRevocationServicesImpl(HKRevocationFragment mFragment) {
        mRevocationFragment = mFragment;
        loadingDialogUtil = new LoadingDialogUtil(mFragment.getContext());
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onStop() {

    }

    /**
     * 发起请求，查询撤单列表
     */
    public void requestRevocation() {
        HashMap<String, String> map = new HashMap<String, String>();
        new HK301610(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                ArrayList<HkRevocationBean> dataList = bundle.getParcelableArrayList(HK301610.BUNDLE_KEY_REVOCATION);
                mRevocationFragment.onGetStoreData(dataList);
            }

            @Override
            public void onFailed(Context context, Bundle bundle) {
                String error_info = bundle.getString(HK301610.ERROR_INFO);
                ToastUtils.toast(context, error_info);
                mRevocationFragment.onGetStoreDataError(error_info);
            }
        }).request();
    }

    /**
     * 点击撤单按钮后，返回的撤单结果
     */
    public void execRevocation(String entrustNum, String exchange_type) {
        loadingDialogUtil.showLoadingDialog(0);
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("stock_account", TradeLoginManager.sNormalUserInfo_hk.getStock_account());
        map.put("entrust_no", entrustNum);
        map.put("exchange_type", exchange_type);
        new HK301604(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                loadingDialogUtil.hideDialog();
                //显示撤单结果
                ToastUtils.toast(context, bundle.getString(HK301604.BUNDLE_KEY_REVOCATION_DIALOG));
                //请求成功后刷新数据
                requestRevocation();
                mRevocationFragment.refreshAdapter();
            }

            @Override
            public void onFailed(Context context, Bundle bundle) {
                loadingDialogUtil.hideDialog();
                ToastUtils.toast(context, bundle.getString(HK301604.ERROR_INFO));
            }
        }).request();
    }
}
