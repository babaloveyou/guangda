package com.thinkive.android.trade_bz.a_rr.bll;

import android.content.Context;
import android.os.Bundle;

import com.thinkive.android.trade_bz.a_rr.bean.RSelectHistoryWaterMoneyBean;
import com.thinkive.android.trade_bz.a_rr.fragment.RSelectWaterMoneyFragment;
import com.thinkive.android.trade_bz.a_stock.bll.BasicServiceImpl;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.request.RR303043;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 融资融券--查询--历史资金流水（303043）
 * 历史资金流失（对账单）
 * Announcements：
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/8/19
 */
public class RSelectWaterMoneyServiceImpl extends BasicServiceImpl {

    private RSelectWaterMoneyFragment mFragment;

    public RSelectWaterMoneyServiceImpl(RSelectWaterMoneyFragment fragment) {
        mFragment = fragment;
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onStop() {

    }
    /**
     * 请求历史资金流水数据
     */
    public void requestWaterMoneyData(String begin, String end) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("begin_date",begin);
        map.put("end_date",end);
        new RR303043(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                ArrayList<RSelectHistoryWaterMoneyBean> dataList = bundle.getParcelableArrayList(RR303043.BUNDLE_KEY_RTODAY_WATER);
                mFragment.getWaterMoneyData(dataList);
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(RR303043.ERROR_INFO));
            }
        }).request();
    }
}
