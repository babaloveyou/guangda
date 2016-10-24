package com.thinkive.android.trade_bz.a_stock.bll;

import android.content.Context;
import android.os.Bundle;

import com.thinkive.android.trade_bz.a_stock.bean.HistoryTradeBean;
import com.thinkive.android.trade_bz.a_stock.fragment.TodayTradeFragment;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.request.Request301509;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *   今日成交的业务类
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/6/23
 */
public class TodayTradeServicesImpl {
    private TodayTradeFragment mTodayTradeFragment = null;

    public TodayTradeServicesImpl(TodayTradeFragment mFragment) {
        mTodayTradeFragment = mFragment;
    }

    //初始化请求到的数据
    public void requestTodayTrade() {
        HashMap<String, String> map = new HashMap<String, String>();
        new Request301509(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                ArrayList<HistoryTradeBean> dataList = bundle.getParcelableArrayList(Request301509.BUNDLE_KEY_TOADY_TRADE);

                //保留小数点后两位
//                for (HistoryTradeBean bean : dataList) {
//                    bean.setBusiness_price(TradeUtils.formatDouble2(bean.getBusiness_price()));
//                }
                //将数据传到TodayTradeFragment
                mTodayTradeFragment.onGetTodayTradeData(dataList);
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(Request301509.ERROR_INFO));
            }
        }).request();
    }
}
