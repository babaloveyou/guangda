package com.thinkive.android.trade_bz.a_level.bll;

import android.content.Context;
import android.os.Bundle;

import com.thinkive.android.trade_bz.a_level.bean.LFundEntrustDataBean;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.request.Level302061;
import com.thinkive.android.trade_bz.a_level.fragment.LFundHistoryEntrustFragment;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * 分级基金历史委托
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/11/12
 */

public class LFundHistoryEntrustServicesImpl {

    private LFundHistoryEntrustFragment mFragment;

    public LFundHistoryEntrustServicesImpl(LFundHistoryEntrustFragment fragment) {
        mFragment=fragment;
    }
    /**
     * 请求历史委托数据
     */
    public void requestHistoryEntrust(String begin,String end) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("begin_time", begin);
        map.put("end_time", end);
        new Level302061(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                ArrayList <LFundEntrustDataBean> dataList =bundle.getParcelableArrayList(Level302061.BUNDLE_KEY_LEVEL_HENTRUST);
                mFragment.onGetHistoryEntrustDataList(dataList);
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(Level302061.ERROR_INFO));
            }
        }).request();
    }
}
