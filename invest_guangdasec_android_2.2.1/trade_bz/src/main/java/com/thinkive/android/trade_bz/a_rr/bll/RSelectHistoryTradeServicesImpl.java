package com.thinkive.android.trade_bz.a_rr.bll;

import android.content.Context;
import android.os.Bundle;

import com.thinkive.android.trade_bz.a_rr.bean.RSelectHistoryTradeBean;
import com.thinkive.android.trade_bz.a_rr.fragment.RSelectHistoryTradeFragment;
import com.thinkive.android.trade_bz.a_stock.bll.BasicServiceImpl;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.request.RR303036;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 融资融券--查询--历史成交
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/8/18
 */

public class RSelectHistoryTradeServicesImpl extends BasicServiceImpl {
    private RSelectHistoryTradeFragment mFragment =null;

    public RSelectHistoryTradeServicesImpl(RSelectHistoryTradeFragment mFragment) {
        this.mFragment =mFragment;
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onStop() {

    }

    /**
     * 请求融资融券历史委托数据
     */
    public void requestHistoryTrade(String begin, String end){
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("begin_time",begin);
        map.put("end_time",end);
        new RR303036(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                ArrayList<RSelectHistoryTradeBean> dataList = bundle.getParcelableArrayList(RR303036.BUNDLE_KEY_RTODAY_HISTORYT);
                mFragment.getHistoryTradeData(dataList);
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(RR303036.ERROR_INFO));
            }
        }).request();
    }
}
