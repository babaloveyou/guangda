package com.thinkive.android.trade_bz.a_rr.bll;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;

import com.thinkive.android.trade_bz.a_rr.bean.RSelectCollaterSecurityBean;
import com.thinkive.android.trade_bz.a_rr.fragment.SubSecuritiesFragment;
import com.thinkive.android.trade_bz.a_stock.bll.BasicServiceImpl;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.request.RR303002;
import com.thinkive.android.trade_bz.request.RR303006;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Administrator on 2016/11/17.
 */
public class SubSecuritiesServiceImpl extends BasicServiceImpl {

    private SubSecuritiesFragment mFragment;

    public SubSecuritiesServiceImpl(SubSecuritiesFragment fragment) {
        mFragment = fragment;
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onStop() {

    }
    /**
     * 请求担保品证券
     */
    public void requestCollaterSecurity(final String code) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("stock_code",code);
        new RR303006(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                ArrayList<RSelectCollaterSecurityBean> dataList = bundle.getParcelableArrayList(RR303006.BUNDLE_KEY_TICKET);
                mFragment.getCollaterSecurityData(dataList);
                if (TextUtils.isEmpty(code)) {
                    mFragment.saveData(dataList);
                } else {
                    mFragment.clearFocus();
                }
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(RR303002.ERROR_INFO));
            }
        }).request();

    }
}


