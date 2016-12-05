package com.thinkive.android.trade_bz.a_rr.bll;

import android.content.Context;
import android.os.Bundle;

import com.thinkive.android.trade_bz.a_rr.bean.RSelectHistoryWaterMoneyBean;
import com.thinkive.android.trade_bz.a_rr.fragment.CreditTodayMoneyFragment;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.others.constants.Constants;
import com.thinkive.android.trade_bz.request.RR303043;
import com.thinkive.android.trade_bz.request.Request301516;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Administrator on 2016/12/2.
 */
public class CreditTodayMoneyServiceImpl {
    private CreditTodayMoneyFragment mFragment = null;

    public CreditTodayMoneyServiceImpl(CreditTodayMoneyFragment fragment) {
        mFragment = fragment;
    }


    public void requestHistoryMoney() {
        HashMap<String, String> map303043 = new HashMap<String, String>();
        map303043.put("begin_time", Constants.CREDIT_TOTAY_DATE);
        map303043.put("end_time",Constants.CREDIT_TOTAY_DATE);
        new RR303043(map303043, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                ArrayList<RSelectHistoryWaterMoneyBean> dataList = bundle.getParcelableArrayList(RR303043.BUNDLE_KEY_RTODAY_WATER);
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
