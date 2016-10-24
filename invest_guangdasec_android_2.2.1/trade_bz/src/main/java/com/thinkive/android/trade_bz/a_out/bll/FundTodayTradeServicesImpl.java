package com.thinkive.android.trade_bz.a_out.bll;

import android.content.Context;
import android.os.Bundle;

import com.thinkive.android.trade_bz.a_out.bean.FundTodayTradeBean;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.request.Fund302013;
import com.thinkive.android.trade_bz.a_out.fragment.FundTodayTradeFragment;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *  基金交易--查询--今日成交
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/11/13
 */

public class FundTodayTradeServicesImpl {
    private FundTodayTradeFragment mFragment = null;

    public FundTodayTradeServicesImpl(FundTodayTradeFragment fragment) {
        mFragment = fragment;
    }

    /**
     * 请求当日成交数据列表
     */
    public void requestTodayTrade() {
        HashMap<String, String> map = new HashMap<String, String>();
        new Fund302013(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                ArrayList<FundTodayTradeBean> data = bundle.getParcelableArrayList(Fund302013.BUNDLE_KEY_FUND_TODAY_TRADE);
                mFragment.getTodayTradeData(data);
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(Fund302013.ERROR_INFO));
            }
        }).request();
    }
}
