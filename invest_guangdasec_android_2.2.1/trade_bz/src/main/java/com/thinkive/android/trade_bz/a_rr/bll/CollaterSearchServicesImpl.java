package com.thinkive.android.trade_bz.a_rr.bll;

import android.content.Context;
import android.os.Bundle;

import com.thinkive.android.trade_bz.a_rr.fragment.RCollaterSearchFragment;
import com.thinkive.android.trade_bz.a_stock.bean.RCollaterSearchBean;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.request.Request301510;
import com.thinkive.android.trade_bz.request.Request303025;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Administrator on 2016/11/16.
 */
public class CollaterSearchServicesImpl {
    private RCollaterSearchFragment mFragment = null;

    public CollaterSearchServicesImpl(RCollaterSearchFragment fragment) {
        mFragment = fragment;
    }

    /**
     * 初始化请求到的历史委托数据
     *
     * @param begin
     * @param end
     */
    public void requestCollaterDatas(final String begin, final String end) {
        HashMap<String, String> map = new HashMap<>();
        map.put("begin_time", begin);
        map.put("end_time", end);
        new Request303025(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                ArrayList<RCollaterSearchBean> dataList = bundle.getParcelableArrayList(Request303025.BUNDLE_KEY_COLLATER_SEARCH);
                mFragment.onGetCollaterSearchData(dataList);
            }

            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(Request301510.ERROR_INFO));
            }

        }).request();
    }
}
