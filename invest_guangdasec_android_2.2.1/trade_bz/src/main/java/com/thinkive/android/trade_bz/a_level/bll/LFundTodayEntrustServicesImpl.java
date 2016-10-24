package com.thinkive.android.trade_bz.a_level.bll;

import android.content.Context;
import android.os.Bundle;

import com.thinkive.android.trade_bz.a_level.bean.LFundEntrustDataBean;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.request.Level302059;
import com.thinkive.android.trade_bz.a_level.fragment.LFundTodayEntrustFragment;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * 分级基金 今日委托
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/11/12
 */

public class LFundTodayEntrustServicesImpl {

    private LFundTodayEntrustFragment mFragment;

    public LFundTodayEntrustServicesImpl(LFundTodayEntrustFragment fragment) {
        mFragment=fragment;
    }

    /**
     * 请求今日委托数据
     */
    public void requestTodayEntrust() {
        HashMap<String, String> map = new HashMap<String, String>();
        new Level302059(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                ArrayList<LFundEntrustDataBean> dataList = bundle.getParcelableArrayList(Level302059.BUNDLE_KEY_LEVEL_TENTRUST);
                mFragment.onGetTodayEntrustDataList(dataList);
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(Level302059.ERROR_INFO));
            }
        }).request();
    }
}
