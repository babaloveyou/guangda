package com.thinkive.android.trade_bz.a_out.bll;

import android.content.Context;
import android.os.Bundle;

import com.thinkive.android.trade_bz.a_out.bean.FundHistoryEntrustBean;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.request.Fund302014;
import com.thinkive.android.trade_bz.a_out.fragment.FundHistoryEntrustFragment;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *  基金交易--查询--历史委托
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/8/7
 */

public class FundHistoryEntrustServicesImpl {
    private FundHistoryEntrustFragment mFragment = null;

    public FundHistoryEntrustServicesImpl(FundHistoryEntrustFragment fragment) {
        mFragment = fragment;
    }

    /**
     * 请求当日委托数据列表
     */
    public void requestHistoryEntrust(String begin,String end) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("begin_date", begin);
        map.put("end_date", end);
        new Fund302014(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                ArrayList<FundHistoryEntrustBean> data = bundle.getParcelableArrayList(Fund302014.BUNDLE_KEY_FUND_HISTORY);
                mFragment.onGetHistoryEntrustData(data);
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(Fund302014.ERROR_INFO));
            }
        }).request();
    }
}
