package com.thinkive.android.trade_bz.a_hk.bll;

import android.content.Context;
import android.os.Bundle;

import com.thinkive.android.trade_bz.a_hk.bean.HKTodayTradeBean;
import com.thinkive.android.trade_bz.a_hk.fragment.HKTodayTradeFragment;
import com.thinkive.android.trade_bz.a_stock.bll.BasicServiceImpl;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.request.HK301607;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 港股通 今日成交
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/11/2
 */
public class HKTodayTradeServicesImpl extends BasicServiceImpl {
    private HKTodayTradeFragment mFragment = null;

    public HKTodayTradeServicesImpl(HKTodayTradeFragment mFragment) {
        this.mFragment = mFragment;
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onStop() {

    }

    /**
     * 请求今日成交数据
     */
    public void requestTodayTrade() {
        HashMap<String, String> map = new HashMap<String, String>();
        new HK301607(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                ArrayList<HKTodayTradeBean> dataList = bundle.getParcelableArrayList(HK301607.BUNDLE_KEY_HK_TODAY_TRADE);
                mFragment.onGetTodayTradeData(dataList);
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(HK301607.ERROR_INFO));
            }
        }).request();
    }
}
