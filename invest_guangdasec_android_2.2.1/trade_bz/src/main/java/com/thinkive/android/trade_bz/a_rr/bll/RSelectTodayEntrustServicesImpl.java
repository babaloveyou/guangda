package com.thinkive.android.trade_bz.a_rr.bll;

import android.content.Context;
import android.os.Bundle;

import com.thinkive.android.trade_bz.a_rr.bean.RSelectTodayEntrustBean;
import com.thinkive.android.trade_bz.a_rr.fragment.CreditBottomTodayEntrustFragment;
import com.thinkive.android.trade_bz.a_rr.fragment.RSelectTodayEntrustFragment;
import com.thinkive.android.trade_bz.a_stock.bll.BasicServiceImpl;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.request.Request303016;
import com.thinkive.android.trade_bz.request.Request306000;
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
    private RSelectTodayEntrustFragment mRSelectTodayEntrustFragment=null;
    private CreditBottomTodayEntrustFragment mCreditBottomTodayEntrustFragment =null;
    private boolean isBottom;

    public RSelectTodayEntrustServicesImpl(RSelectTodayEntrustFragment mFragment) {
        this.mRSelectTodayEntrustFragment =mFragment;
        isBottom = false;
    }
    public RSelectTodayEntrustServicesImpl(CreditBottomTodayEntrustFragment mFragment) {
        this.mCreditBottomTodayEntrustFragment =mFragment;
        isBottom = true;
    }


    /**
     * 请求融资融券当日委托数据
     */
    public void requestTodayEntrust(){
        HashMap<String, String> map = new HashMap<String, String>();
        new Request306000(new HashMap<String,String>(), new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                String date = bundle.getString(Request306000.BUNDLE_KEY_306000);
                HashMap<String, String> map303016 = new HashMap<>();
                map303016.put("date", date);
                System.out.println("30600 date==" + date);
                new Request303016(map303016, new IRequestAction() {
                    @Override
                    public void onSuccess(Context context, Bundle bundle) {
                        ArrayList<RSelectTodayEntrustBean> dataList = bundle.getParcelableArrayList(Request303016.BUNDLE_KEY_TOADY_ENTRUST);
                        if (isBottom) {
                            mCreditBottomTodayEntrustFragment.onGetTodayEntrustData(dataList);
                        } else {
                            mRSelectTodayEntrustFragment.getTodayEntrustData(dataList);
                        }
                    }
                    @Override
                    public void onFailed(Context context, Bundle bundle) {
                        ToastUtils.toast(context, bundle.getString(Request303016.ERROR_INFO));
                    }
                }).request();
            }

            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(Request303016.ERROR_INFO));
            }
        }).request();

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onStop() {

    }
}
