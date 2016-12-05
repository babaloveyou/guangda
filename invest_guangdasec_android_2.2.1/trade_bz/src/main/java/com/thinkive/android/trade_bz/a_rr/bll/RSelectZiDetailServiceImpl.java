package com.thinkive.android.trade_bz.a_rr.bll;

import android.content.Context;
import android.os.Bundle;

import com.thinkive.android.trade_bz.a_rr.bean.RSelectContractBean;
import com.thinkive.android.trade_bz.a_rr.fragment.RSelectZiDetailFragment;
import com.thinkive.android.trade_bz.a_stock.bll.BasicServiceImpl;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.request.RR303027;
import com.thinkive.android.trade_bz.request.RR303035;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Administrator on 2016/12/2.
 */
public class RSelectZiDetailServiceImpl  extends BasicServiceImpl {

    private RSelectZiDetailFragment mFragment;

    public RSelectZiDetailServiceImpl(RSelectZiDetailFragment fragment) {
        mFragment = fragment;
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onStop() {

    }
    public void requestZiDetail(String begin, String end) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("begin_date",begin);
        map.put("end_date",end);
        map.put("compact_type","0");
        new RR303035(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                ArrayList<RSelectContractBean> dataList = bundle.getParcelableArrayList(RR303035.BUNDLE_KEY_RTODAY_CONTRACT);
                mFragment.getDeliveryOrderData(dataList);
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(RR303027.ERROR_INFO));
            }
        }).request();
    }
}
