package com.thinkive.android.trade_bz.a_rr.bll;

import android.content.Context;
import android.os.Bundle;

import com.thinkive.android.trade_bz.a_rr.bean.RSelectTodayTradeBean;
import com.thinkive.android.trade_bz.a_rr.fragment.RSelectTodayTradeFragment;
import com.thinkive.android.trade_bz.a_stock.bll.BasicServiceImpl;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.request.RR303019;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 融资融券--查询--当日成交
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/8/18
 */

public class RSelectTodayTradeServicesImpl extends BasicServiceImpl {
    private RSelectTodayTradeFragment mFragment =null;

    public RSelectTodayTradeServicesImpl(RSelectTodayTradeFragment mFragment) {
        this.mFragment =mFragment;
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onStop() {

    }

    /**
     * 请求融资融券当日成交数据
     */
    public void requestTodayTrade(){
        HashMap<String, String> map = new HashMap<String, String>();
        new RR303019(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                ArrayList<RSelectTodayTradeBean> dataList = bundle.getParcelableArrayList(RR303019.BUNDLE_KEY_RTODAY_TRADE);
                mFragment.getTodayTradeData(dataList);
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(RR303019.ERROR_INFO));
            }
        }).request();
    }
}
