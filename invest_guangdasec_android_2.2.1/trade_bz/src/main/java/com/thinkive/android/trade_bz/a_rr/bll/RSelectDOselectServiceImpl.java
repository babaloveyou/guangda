package com.thinkive.android.trade_bz.a_rr.bll;

import android.content.Context;
import android.os.Bundle;

import com.thinkive.android.trade_bz.a_rr.bean.RSelectDOSelectBean;
import com.thinkive.android.trade_bz.a_rr.fragment.RSelectDOselectFragment;
import com.thinkive.android.trade_bz.a_stock.bll.BasicServiceImpl;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.request.RR303027;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 融资融券--查询--交割单（303027）
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/8/19
 */
public class RSelectDOselectServiceImpl extends BasicServiceImpl {

    private RSelectDOselectFragment mFragment;

    public RSelectDOselectServiceImpl(RSelectDOselectFragment fragment) {
        mFragment = fragment;
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onStop() {

    }
    /**
     * 请求交割单数据
     */
    public void requestDeliveryOrderData(String begin, String end) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("begin_time",begin);
        map.put("end_time",end);
        new RR303027(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                ArrayList<RSelectDOSelectBean> dataList = bundle.getParcelableArrayList(RR303027.BUNDLE_KEY_RTODAY_DO);
                mFragment.getDeliveryOrderData(dataList);
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(RR303027.ERROR_INFO));
            }
        }).request();
    }
}
