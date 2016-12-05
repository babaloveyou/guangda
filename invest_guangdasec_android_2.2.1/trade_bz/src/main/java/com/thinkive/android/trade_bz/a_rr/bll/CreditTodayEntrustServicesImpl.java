package com.thinkive.android.trade_bz.a_rr.bll;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;

import com.thinkive.android.trade_bz.a_rr.bean.RSelectTodayEntrustBean;
import com.thinkive.android.trade_bz.a_rr.fragment.CreditTodayEntrustFragment;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.others.constants.Constants;
import com.thinkive.android.trade_bz.request.Request301508;
import com.thinkive.android.trade_bz.request.Request303016;
import com.thinkive.android.trade_bz.request.Request306000;
import com.thinkive.android.trade_bz.request.Request306001;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Administrator on 2016/11/28.
 */
public class CreditTodayEntrustServicesImpl {
    private CreditTodayEntrustFragment mCreditTodayEntrustFragment = null;

    public CreditTodayEntrustServicesImpl(CreditTodayEntrustFragment mFragment) {
        mCreditTodayEntrustFragment = mFragment;
    }


    public void requestTodayEntrust() {
        if (!TextUtils.isEmpty(Constants.CREDIT_TOTAY_DATE)) {
            HashMap<String, String> map303016 = new HashMap<>();
            map303016.put("date", Constants.CREDIT_TOTAY_DATE);
            new Request303016(map303016, new IRequestAction() {
                @Override
                public void onSuccess(Context context, Bundle bundle) {
                    ArrayList<RSelectTodayEntrustBean> dataList = bundle.getParcelableArrayList(Request303016.BUNDLE_KEY_TOADY_ENTRUST);
                    mCreditTodayEntrustFragment.onGetTodayEntrustData(dataList);
                }

                @Override
                public void onFailed(Context context, Bundle bundle) {
                    ToastUtils.toast(context, bundle.getString(Request301508.ERROR_INFO));
                }
            }).request();
        } else {
            //今日委托需传入data字段
            HashMap<String, String> map306000 = new HashMap<String, String>();
            new Request306000(map306000, new IRequestAction() {
                @Override
                public void onSuccess(Context context, Bundle bundle) {
                    String date = bundle.getString(Request306000.BUNDLE_KEY_306000);
                    HashMap<String, String> map303016 = new HashMap<>();
                    map303016.put("date", date);
                    new Request303016(map303016, new IRequestAction() {
                        @Override
                        public void onSuccess(Context context, Bundle bundle) {
                            ArrayList<RSelectTodayEntrustBean> dataList = bundle.getParcelableArrayList(Request303016.BUNDLE_KEY_TOADY_ENTRUST);
                            mCreditTodayEntrustFragment.onGetTodayEntrustData(dataList);
                        }

                        @Override
                        public void onFailed(Context context, Bundle bundle) {
                            ToastUtils.toast(context, bundle.getString(Request303016.ERROR_INFO));
                        }
                    }).request();

                }

                @Override
                public void onFailed(Context context, Bundle bundle) {
                    ToastUtils.toast(context, bundle.getString(Request306001.ERROR_INFO));
                }
            }).request();
        }


    }
}

