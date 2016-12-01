package com.thinkive.android.trade_bz.a_rr.bll;

import android.content.Context;
import android.os.Bundle;

import com.thinkive.android.trade_bz.a_rr.bean.RSelectTodayTradeBean;
import com.thinkive.android.trade_bz.a_rr.fragment.CreditTodayTradeFragment;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.others.constants.Constants;
import com.thinkive.android.trade_bz.request.RR303019;
import com.thinkive.android.trade_bz.request.Request301509;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Administrator on 2016/11/28.
 */
public class CreditTodayTradeServicesImpl{

        private CreditTodayTradeFragment mTodayTradeFragment = null;

        public CreditTodayTradeServicesImpl(CreditTodayTradeFragment mFragment) {
            mTodayTradeFragment = mFragment;
        }

        //初始化请求到的数据
        public void requestTodayTrade() {
            HashMap<String, String> map = new HashMap<>();
            map.put("date", Constants.TODAY_DATE);
            new RR303019(map, new IRequestAction() {
                @Override
                public void onSuccess(Context context, Bundle bundle) {
                    ArrayList<RSelectTodayTradeBean> dataList = bundle.getParcelableArrayList(RR303019.BUNDLE_KEY_RTODAY_TRADE);
                    mTodayTradeFragment.onGetTodayTradeData(dataList);
                }

                @Override
                public void onFailed(Context context, Bundle bundle) {
                    ToastUtils.toast(context, bundle.getString(Request301509.ERROR_INFO));
                }
            }).request();

        }
}
