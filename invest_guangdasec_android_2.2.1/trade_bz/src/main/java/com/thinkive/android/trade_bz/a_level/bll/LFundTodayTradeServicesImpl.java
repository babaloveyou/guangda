package com.thinkive.android.trade_bz.a_level.bll;

import android.content.Context;
import android.os.Bundle;

import com.thinkive.android.trade_bz.a_level.bean.LFundTradeDataBean;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.request.Level302060;
import com.thinkive.android.trade_bz.a_level.fragment.LFundTodayTradeFragment;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * 分级基金 今日成交
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/11/12
 */

public class LFundTodayTradeServicesImpl {

    private LFundTodayTradeFragment mFragment;

    public LFundTodayTradeServicesImpl(LFundTodayTradeFragment fragment) {
        mFragment=fragment;
    }

    /**
     * 请求今日成交数据
     */
    public void requestTodayTrade() {
        HashMap<String, String> map = new HashMap<String, String>();
        new Level302060(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                ArrayList<LFundTradeDataBean> dataList = bundle.getParcelableArrayList(Level302060.BUNDLE_KEY_LEVEL_TTRADE);
                mFragment.onGetTodayTradeDataList(dataList);
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(Level302060.ERROR_INFO));
            }
        }).request();
    }
}
