package com.thinkive.android.trade_bz.a_option.bll;

import android.content.Context;
import android.os.Bundle;

import com.thinkive.android.trade_bz.a_option.bean.OptionHistoryTradeBean;
import com.thinkive.android.trade_bz.a_option.fragment.OptionHistoryTradeFragment;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.request.Option305010;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 个股期权--查询--历史成交
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/6/15
 */

public class OptionHistoryTradeServicesImpl {
    private OptionHistoryTradeFragment mFragment = null;

    public OptionHistoryTradeServicesImpl(OptionHistoryTradeFragment fragment) {
        mFragment = fragment;
    }

    /**
     * 请求当日委托数据列表
     */
    public void requestHistoryEntrust(String begin,String end) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("begin_date", begin);
        map.put("end_date", end);
        new Option305010(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                ArrayList<OptionHistoryTradeBean> data = bundle.getParcelableArrayList(Option305010.BUNDLE_KEY_OPTION_HISTORY);
                mFragment.onGetHistoryEntrustData(data);
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(Option305010.ERROR_INFO));
            }
        }).request();
    }
}
