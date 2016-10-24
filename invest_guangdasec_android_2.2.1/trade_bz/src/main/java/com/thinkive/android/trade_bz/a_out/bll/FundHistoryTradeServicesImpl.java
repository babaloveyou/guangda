package com.thinkive.android.trade_bz.a_out.bll;

import android.content.Context;
import android.os.Bundle;

import com.thinkive.android.trade_bz.a_out.bean.FundHistoryTradeBean;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.request.Fund302015;
import com.thinkive.android.trade_bz.a_out.fragment.FundHistoryTradeFragment;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *  基金交易--查询--历史成交
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/8/7
 */

public class FundHistoryTradeServicesImpl {
    private FundHistoryTradeFragment mFragment = null;

    public FundHistoryTradeServicesImpl(FundHistoryTradeFragment fragment) {
        mFragment = fragment;
    }

    /**
     * 请求历史成交数据列表
     */
    public void requestHistoryTrade(String begin,String end) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("begin_date", begin);
        map.put("end_date", end);
        new Fund302015(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                ArrayList<FundHistoryTradeBean> data = bundle.getParcelableArrayList(Fund302015.BUNDLE_KEY_FUND_HISTORY);
                mFragment.onGetHistoryTradeData(data);
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(Fund302015.ERROR_INFO));
            }
        }).request();
    }
}
