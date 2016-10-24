package com.thinkive.android.trade_bz.a_option.bll;

import android.content.Context;
import android.os.Bundle;

import com.thinkive.android.trade_bz.a_option.bean.OptionTodayTradeBean;
import com.thinkive.android.trade_bz.a_option.fragment.OptionTodayTradeFragment;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.request.Option305009;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 个股期权--查询--当日成交
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/6/15
 */

public class OptionTodayTradeServicesImpl {
    private OptionTodayTradeFragment mFragment =null;

    public OptionTodayTradeServicesImpl(OptionTodayTradeFragment mFragment) {
        this.mFragment =mFragment;
    }

    public void requestTodayEntrust(){
        HashMap<String, String> map = new HashMap<String, String>();
        new Option305009(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                ArrayList<OptionTodayTradeBean> dataList = bundle.getParcelableArrayList(Option305009.BUNDLE_KEY_OPTION_TODAY);
                //保留小数点后两位
                for (OptionTodayTradeBean bean : dataList) {
//                    bean.setBusiness_price(TradeUtils.formatDouble2(bean.getBusiness_price()));
//                    bean.setEntrust_price(TradeUtils.formatDouble2(bean.getEntrust_price()));
                }
                mFragment.onGetTodayTradeData(dataList);
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(Option305009.ERROR_INFO));
            }
        }).request();
    }
}
