package com.thinkive.android.trade_bz.a_stock.bll;

import android.content.Context;
import android.os.Bundle;

import com.thinkive.android.trade_bz.a_stock.bean.HistoryTradeBean;
import com.thinkive.android.trade_bz.a_stock.fragment.HistoryTradeFragment;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.request.Request301511;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 历史成交的业务类
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/6/23
 */

public class HistoryTradeServicesImpl {
    private HistoryTradeFragment mHistoryTradeFragment=null;

    public HistoryTradeServicesImpl(HistoryTradeFragment mFragment) {
        mHistoryTradeFragment=mFragment;
    }

    /**
     * 初始化请求到的历史成交数据
     * @param begin
     * @param end
     */
    public void reuqestHistoryTrade(String begin, String end){
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("begin_time", begin);
        map.put("end_time", end);
        new Request301511(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                ArrayList<HistoryTradeBean> dataList = bundle.getParcelableArrayList(Request301511.BUNDLE_KEY_HISTORY_TRADE);

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
