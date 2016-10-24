package com.thinkive.android.trade_bz.a_rr.bll;

import android.content.Context;
import android.os.Bundle;

import com.thinkive.android.trade_bz.a_rr.bean.RCollaterSelectBean;
import com.thinkive.android.trade_bz.a_rr.fragment.RCollaterSelectFragment;
import com.thinkive.android.trade_bz.a_stock.bll.BasicServiceImpl;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.request.RR303025;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *  融资融券--划转--担保平划转查询
 * Announcements：
 * @author 张雪梅
 * @company Thinkive
 * @date 15/8/13
 */

public class RCollaterSelectServicesImpl extends BasicServiceImpl {
    private RCollaterSelectFragment mFragment =null;

    public RCollaterSelectServicesImpl(RCollaterSelectFragment mFragment) {
        this.mFragment =mFragment;
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onStop() {

    }

    /**
     * 请求划转查询列表
     * @param begin
     * @param end
     */
    public void requestCollaterData(String begin, String end){
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("begin_time", begin);
        map.put("end_time", end);
        new RR303025(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                ArrayList<RCollaterSelectBean> dataList = bundle.getParcelableArrayList(RR303025.BUNDLE_KEY_COLLATER_SELECT);
                mFragment.getCollaterData(dataList);
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(RR303025.ERROR_INFO));
            }
        }).request();
    }
}
