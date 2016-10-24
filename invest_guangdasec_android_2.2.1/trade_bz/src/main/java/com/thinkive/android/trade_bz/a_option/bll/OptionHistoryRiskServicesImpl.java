package com.thinkive.android.trade_bz.a_option.bll;


import android.content.Context;
import android.os.Bundle;

import com.thinkive.android.trade_bz.a_option.bean.OptionHistoryRiskBean;
import com.thinkive.android.trade_bz.a_option.fragment.OptionHistoryRiskFragment;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.request.Option305034;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * 个股期权历史风险通知查询
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/7/28
 */

public class OptionHistoryRiskServicesImpl {
    private OptionHistoryRiskFragment mFragment =null;

    public OptionHistoryRiskServicesImpl(OptionHistoryRiskFragment mFragment) {
        this.mFragment =mFragment;
    }
    /**
     * 请求数据列表
     */
    public void requestHistoryRisk(String begin, String end) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("begin_date", begin);
        map.put("end_date", end);
        new Option305034(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                ArrayList<OptionHistoryRiskBean> dataList = bundle.getParcelableArrayList(Option305034.BUNDLE_KEY_OPTION_HISTORY);
                mFragment.onGetHistoryRiskList(dataList);
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(Option305034.ERROR_INFO));
            }
        }).request();
    }
}
