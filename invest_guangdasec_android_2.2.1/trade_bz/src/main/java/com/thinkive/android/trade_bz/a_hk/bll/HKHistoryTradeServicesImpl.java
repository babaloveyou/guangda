package com.thinkive.android.trade_bz.a_hk.bll;

import android.content.Context;
import android.os.Bundle;

import com.thinkive.android.trade_bz.a_hk.bean.HKHistoryTradeBean;
import com.thinkive.android.trade_bz.a_hk.fragment.HKHistoryTradeFragment;
import com.thinkive.android.trade_bz.a_stock.bll.BasicServiceImpl;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.request.HK301609;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *  港股通历史成交的业务类
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/11/2
 */

public class HKHistoryTradeServicesImpl extends BasicServiceImpl {
    private HKHistoryTradeFragment mFragment =null;

    public HKHistoryTradeServicesImpl(HKHistoryTradeFragment mFragment) {
        this.mFragment =mFragment;
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onStop() {

    }

    /**
     * 请求历史成交数据
     * @param begin
     * @param end
     */
    public void reuqestHistoryTrade(String begin, String end){
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("begin_date", begin);
        map.put("end_date", end);
        new HK301609(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                ArrayList<HKHistoryTradeBean> dataList = bundle.getParcelableArrayList(HK301609.BUNDLE_KEY_HK_HISTORY_TRADE);
                mFragment.onGetHistoryTradeData(dataList);
            }

            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(HK301609.ERROR_INFO));
            }
        }).request();
    }
}
