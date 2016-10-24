package com.thinkive.android.trade_bz.a_rr.bll;

import android.content.Context;
import android.os.Bundle;

import com.thinkive.android.trade_bz.a_rr.bean.RSelectHistoryEntrustBean;
import com.thinkive.android.trade_bz.a_rr.fragment.RSelectHistoryEntrustFragment;
import com.thinkive.android.trade_bz.a_stock.bll.BasicServiceImpl;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.request.RR303020;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 融资融券--查询--历史委托
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/8/18
 */

public class RSelectHistoryEntrustServicesImpl extends BasicServiceImpl {
    private RSelectHistoryEntrustFragment mFragment =null;

    public RSelectHistoryEntrustServicesImpl(RSelectHistoryEntrustFragment mFragment) {
        this.mFragment =mFragment;
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onStop() {

    }

    /**
     * 请求融资融券历史委托数据
     */
    public void requestHistoryEntrust(String begin, String end){
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("begin_time",begin);
        map.put("end_time",end);
        new RR303020(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                ArrayList<RSelectHistoryEntrustBean> dataList = bundle.getParcelableArrayList(RR303020.BUNDLE_KEY_RTODAY_HISTORYT);
                mFragment.getHistoryEntrustData(dataList);
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(RR303020.ERROR_INFO));
            }
        }).request();
    }
}
