package com.thinkive.android.trade_bz.a_hk.bll;

import android.content.Context;
import android.os.Bundle;

import com.thinkive.android.trade_bz.a_hk.bean.HKTodayEntrustBean;
import com.thinkive.android.trade_bz.a_hk.fragment.HKTodayEntrustFragment;
import com.thinkive.android.trade_bz.a_stock.bll.BasicServiceImpl;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.request.HK301606;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 港股通今日委托
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/11/2
 */

public class HKTodayEntrustServicesImpl extends BasicServiceImpl {

    private HKTodayEntrustFragment mFragment =null;

    public HKTodayEntrustServicesImpl(HKTodayEntrustFragment mFragment) {
        this.mFragment =mFragment;
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onStop() {

    }

    /**
     * 请求今日委托数据
     */
    public void requestTodayEntrust(){
        HashMap<String, String> map = new HashMap<String, String>();
        new HK301606(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                ArrayList<HKTodayEntrustBean> dataList = bundle.getParcelableArrayList(HK301606.BUNDLE_KEY_HK_TODAY_ENTRUST);
                mFragment.onGetTodayEntrustData(dataList);
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(HK301606.ERROR_INFO));
            }
        }).request();
    }
}
