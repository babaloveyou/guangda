package com.thinkive.android.trade_bz.a_hk.bll;

import android.content.Context;
import android.os.Bundle;

import com.thinkive.android.trade_bz.a_hk.bean.HKHoldStockBean;
import com.thinkive.android.trade_bz.a_hk.fragment.HKMyHoldStockFragment;
import com.thinkive.android.trade_bz.a_stock.bll.BasicServiceImpl;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.request.HK301605;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *  港股通 我的持仓
 * @author 张雪梅
 * @version 1.0
 * @corporation
 * @date 2015/11/2
 */
public class HKMyHoldStockServiceImpl extends BasicServiceImpl {

    private HKMyHoldStockFragment mHoldStockFragment =null;

    public HKMyHoldStockServiceImpl(HKMyHoldStockFragment fragment) {
        mHoldStockFragment = fragment;
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onStop() {

    }

    /**
     * 发起请求，获取持仓列表
     */
    public void requestMyHoldStock() {
        HashMap<String, String> map = new HashMap<String, String>();
        new HK301605(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                ArrayList<HKHoldStockBean> dataList = bundle.getParcelableArrayList(HK301605.BUNDLE_KEY_RESULT);
                mHoldStockFragment.onGetStoreData(dataList);
            }

            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(HK301605.ERROR_INFO));
            }
        }).request();
    }
}
