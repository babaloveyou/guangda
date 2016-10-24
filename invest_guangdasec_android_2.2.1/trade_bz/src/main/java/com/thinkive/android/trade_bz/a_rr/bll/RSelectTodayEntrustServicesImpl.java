package com.thinkive.android.trade_bz.a_rr.bll;

import android.content.Context;
import android.os.Bundle;

import com.thinkive.android.trade_bz.a_rr.bean.RSelectTodayEntrustBean;
import com.thinkive.android.trade_bz.a_rr.fragment.RSelectTodayEntrustFragment;
import com.thinkive.android.trade_bz.a_stock.bll.BasicServiceImpl;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.request.RR303016;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 融资融券--查询--当日委托
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/8/18
 */

public class RSelectTodayEntrustServicesImpl extends BasicServiceImpl {
    private RSelectTodayEntrustFragment mFragment =null;

    public RSelectTodayEntrustServicesImpl(RSelectTodayEntrustFragment mFragment) {
        this.mFragment =mFragment;
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onStop() {

    }

    /**
     * 请求融资融券当日委托数据
     */
    public void requestTodayEntrust(){
        HashMap<String, String> map = new HashMap<String, String>();
        new RR303016(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                ArrayList<RSelectTodayEntrustBean> dataList = bundle.getParcelableArrayList(RR303016.BUNDLE_KEY_RTODAY_ENTRUST);
                mFragment.getTodayEntrustData(dataList);
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(RR303016.ERROR_INFO));
            }
        }).request();
    }
}
