package com.thinkive.android.trade_bz.a_out.bll;

import android.content.Context;
import android.os.Bundle;

import com.thinkive.android.trade_bz.a_out.bean.FundTodayEntrustBean;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.request.Fund302011;
import com.thinkive.android.trade_bz.a_out.fragment.FundTodayEntrustFragment;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *  基金交易--查询--今日委托
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/8/7
 */

public class FundTodayEntrustServicesImpl {
    private FundTodayEntrustFragment mFragment = null;

    public FundTodayEntrustServicesImpl(FundTodayEntrustFragment fragment) {
        mFragment = fragment;
    }

    /**
     * 请求当日委托数据列表
     */
    public void requestTodayEntrust() {
        HashMap<String, String> map = new HashMap<String, String>();
        new Fund302011(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                ArrayList<FundTodayEntrustBean> data = bundle.getParcelableArrayList(Fund302011.BUNDLE_KEY_FUND_TODAY);
                mFragment.onGetTodayEntrustData(data);
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(Fund302011.ERROR_INFO));
            }
        }).request();
    }
}
