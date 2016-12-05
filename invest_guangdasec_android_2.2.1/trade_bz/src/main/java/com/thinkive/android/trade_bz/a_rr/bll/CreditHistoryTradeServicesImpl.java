package com.thinkive.android.trade_bz.a_rr.bll;

import android.content.Context;
import android.os.Bundle;

import com.thinkive.android.trade_bz.a_rr.bean.RSelectHistoryTradeBean;
import com.thinkive.android.trade_bz.a_rr.fragment.CreditHistoryTradeFragment;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.request.RR303036;
import com.thinkive.android.trade_bz.request.Request301511;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Administrator on 2016/12/2.
 */
public class CreditHistoryTradeServicesImpl {
    private CreditHistoryTradeFragment mHistoryTradeFragment = null;

    public CreditHistoryTradeServicesImpl(CreditHistoryTradeFragment mFragment) {
        mHistoryTradeFragment = mFragment;
    }

    /**
     * 初始化请求到的历史成交数据
     *
     * @param begin
     * @param end
     */
    public void reuqestHistoryTrade(final String begin, final String end) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("begin_time", begin);
        map.put("end_time", end);
        new RR303036(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                ArrayList<RSelectHistoryTradeBean> dataList = bundle.getParcelableArrayList(RR303036.BUNDLE_KEY_RTODAY_HISTORYT);

                //保留小数点后两位
                //                for (HistoryTradeBean bean : dataList) {
                //                    bean.setBusiness_price(TradeUtils.formatDouble2(bean.getBusiness_price()));
                //                    bean.setBusiness_balance(TradeUtils.formatDouble2(bean.getBusiness_balance()));
                //                }
                mHistoryTradeFragment.onGetHistoryTradeData(dataList);
            }

            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(Request301511.ERROR_INFO));
            }
        }).request();
    }
}
