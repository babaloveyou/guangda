package com.thinkive.android.trade_bz.a_level.bll;

import android.content.Context;
import android.os.Bundle;

import com.thinkive.android.trade_bz.a_level.bean.LFundTradeDataBean;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.request.Level302062;
import com.thinkive.android.trade_bz.a_level.fragment.LFundHistoryTradeFragment;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * 分级基金历史委托
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/11/12
 */

public class LFundHistoryTradeServicesImpl {

    private LFundHistoryTradeFragment mFragment;

    public LFundHistoryTradeServicesImpl(LFundHistoryTradeFragment fragment) {
        mFragment=fragment;
    }

    /**
     * 请求历史成交数据
     */
    public void requestHistoryTrade(String begin,String end) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("begin_time", begin);
        map.put("end_time", end);
        new Level302062(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                ArrayList<LFundTradeDataBean> dataList = bundle.getParcelableArrayList(Level302062.BUNDLE_KEY_LEVEL_HTRADE);
                mFragment.onGetHistoryTradeDataList(dataList);
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(Level302062.ERROR_INFO));
            }
        }).request();
    }
}
