package com.thinkive.android.trade_bz.a_option.bll;

import android.content.Context;
import android.os.Bundle;

import com.thinkive.android.trade_bz.a_option.bean.OptionTodayEntrustBean;
import com.thinkive.android.trade_bz.a_option.fragment.OptionTodayEntrustFragment;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.request.Option305007;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 个股期权--查询--当日委托
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/6/15
 */

public class OptionTodayEntrustServicesImpl {
    private OptionTodayEntrustFragment mFragment =null;

    public OptionTodayEntrustServicesImpl(OptionTodayEntrustFragment mFragment) {
        this.mFragment =mFragment;
    }

    public void requestTodayEntrust(){
        HashMap<String, String> map = new HashMap<String, String>();
        new Option305007(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                ArrayList<OptionTodayEntrustBean> dataList = bundle.getParcelableArrayList(Option305007.BUNDLE_KEY_OPTION_TODAY);
                mFragment.onGetTodayEntrustData(dataList);
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(Option305007.ERROR_INFO));
            }
        }).request();
    }
}
