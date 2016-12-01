package com.thinkive.android.trade_bz.a_rr.bll;

import android.content.Context;
import android.os.Bundle;

import com.thinkive.android.trade_bz.a_rr.fragment.TodayMoneyFragment;
import com.thinkive.android.trade_bz.a_stock.bean.TodayMoneyBean;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.request.Request301516;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Administrator on 2016/11/29.
 */
public class TodayMoneyServiceImpl {
    private TodayMoneyFragment mFragment = null;

    public TodayMoneyServiceImpl(TodayMoneyFragment fragment) {
        mFragment = fragment;
    }


    public void requestHistoryMoney() {
        HashMap<String, String> map301516 = new HashMap<String, String>();
        new Request301516(map301516, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                ArrayList<TodayMoneyBean> dataList = bundle.getParcelableArrayList(Request301516.BUNDLE_KEY_TODAY_MONEY);
                if (dataList != null) {
                }
                //将数据传到 ToadyMoneyFragment
                mFragment.onGetTodayMoneyWater(dataList);
            }

            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(Request301516.ERROR_INFO));
            }
        }).request();
    }
}
