package com.thinkive.android.trade_bz.a_trans.bll;

import android.content.Context;
import android.os.Bundle;

import com.thinkive.android.trade_bz.a_stock.bll.BasicServiceImpl;
import com.thinkive.android.trade_bz.a_trans.bean.TransSelectBean;
import com.thinkive.android.trade_bz.a_trans.fragment.TransTodayTradeFragment;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.request.Trans301708;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 转股交易今日成交
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/12/31
 */
public class TransTTradeServicesImpl extends BasicServiceImpl {
    private TransTodayTradeFragment mFragment = null;

    public TransTTradeServicesImpl(TransTodayTradeFragment mFragment) {
        this.mFragment = mFragment;
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onStop() {

    }

    /**
     * 初始化请求到的数据
     */
    public void requestTodayTrade() {
        HashMap<String, String> map = new HashMap<String, String>();
        new Trans301708(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                ArrayList<TransSelectBean> dataList = bundle.getParcelableArrayList(Trans301708.BUNDLE_KEY_TODAY_TRADE);
                mFragment.getTodayTradeData(dataList);
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(Trans301708.ERROR_INFO));
            }
        }).request();
    }
}
