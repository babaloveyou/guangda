package com.thinkive.android.trade_bz.a_rr.bll;

import android.content.Context;
import android.os.Bundle;

import com.thinkive.android.trade_bz.a_rr.bean.RSelectHistoryEntrustBean;
import com.thinkive.android.trade_bz.a_rr.fragment.CreditHistoryEntrustFragment;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.request.RR303020;
import com.thinkive.android.trade_bz.request.Request301510;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Administrator on 2016/12/2.
 */
public class CreditHistoryEntrustServicesImpl {
    private CreditHistoryEntrustFragment mHistoryEntrustFragment = null;

    public CreditHistoryEntrustServicesImpl(CreditHistoryEntrustFragment mFragment) {
        mHistoryEntrustFragment = mFragment;
    }

    /**
     * 初始化请求到的历史委托数据
     *
     * @param begin
     * @param end
     */
    public void requestHistoryEntrust(final String begin, final String end) {
        HashMap<String, String> map = new HashMap<>();
        map.put("begin_time", begin);
        map.put("end_time", end);
        new RR303020(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                ArrayList<RSelectHistoryEntrustBean> dataList = bundle.getParcelableArrayList(RR303020.BUNDLE_KEY_RTODAY_HISTORYT);
                //保留小数点后两位
                //                for (HistoryEntrustBean bean : dataList) {
                //                    bean.setBusiness_price(TradeUtils.formatDouble2(bean.getBusiness_price()));
                //                    bean.setEntrust_price(TradeUtils.formatDouble2(bean.getEntrust_price()));
                //                }
                mHistoryEntrustFragment.onGetHistoryEntrustData(dataList);
            }

            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(Request301510.ERROR_INFO));
            }

        }).request();
    }
}
