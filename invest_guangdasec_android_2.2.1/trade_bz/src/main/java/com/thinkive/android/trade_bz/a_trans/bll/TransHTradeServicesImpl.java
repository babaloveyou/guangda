package com.thinkive.android.trade_bz.a_trans.bll;

import android.content.Context;
import android.os.Bundle;

import com.thinkive.android.trade_bz.a_stock.bll.BasicServiceImpl;
import com.thinkive.android.trade_bz.a_trans.bean.TransSelectBean;
import com.thinkive.android.trade_bz.a_trans.fragment.TransHistoryTradeFragment;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.request.Trans301709;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 转股交易历史成交
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/12/31
 */
public class TransHTradeServicesImpl extends BasicServiceImpl {
    private TransHistoryTradeFragment mFragment =null;

    public TransHTradeServicesImpl(TransHistoryTradeFragment mFragment) {
        this.mFragment =mFragment;
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onStop() {

    }

    public void reuqestHistoryTrade(String begin, String end){
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("begin_time", begin);
        map.put("end_time", end);
        new Trans301709(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                ArrayList<TransSelectBean> dataList = bundle.getParcelableArrayList(Trans301709.BUNDLE_KEY_HISTORY_TRADE);
                mFragment.getHistoryTradeData(dataList);
            }

            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(Trans301709.ERROR_INFO));
            }
        }).request();
    }
}
