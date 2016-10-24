package com.thinkive.android.trade_bz.a_option.bll;

import android.content.Context;
import android.os.Bundle;

import com.thinkive.android.trade_bz.a_option.bean.OptionContractBean;
import com.thinkive.android.trade_bz.a_option.fragment.OptionExerciseFragment;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.request.Option305022;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * 个股期权 行权
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/6/17
 */

public class OptionExerciseServicesImpl {
    private OptionExerciseFragment mFragment = null;

    public OptionExerciseServicesImpl(OptionExerciseFragment fragment) {
        mFragment = fragment;
    }

    /**
     * 请求合约
     */
    public void requestOptionContract() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("option_type", ""); // 期权类别
        map.put("option_code", ""); //期权合约编码
        new Option305022(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                ArrayList<OptionContractBean> dataList = bundle.getParcelableArrayList(Option305022.BUNDLE_KEY_OPTION_CONTRACT);
                mFragment.onGetOptionContractList(dataList);
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(Option305022.ERROR_INFO));
            }
        }).request();
    }
}
