package com.thinkive.android.trade_bz.a_rr.bll;

import android.content.Context;
import android.os.Bundle;

import com.thinkive.android.trade_bz.a_rr.bean.RSelectPropertBean;
import com.thinkive.android.trade_bz.a_rr.fragment.RSelectPropertFragment;
import com.thinkive.android.trade_bz.a_stock.bll.BasicServiceImpl;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.request.RR303026;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.util.HashMap;

/**
 * 融资融券--查询--资产负债
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/8/17
 */
public class RSelectPropertServiceImpl extends BasicServiceImpl {

    private RSelectPropertFragment mFragment =null;

    public RSelectPropertServiceImpl(RSelectPropertFragment fragment) {
        mFragment = fragment;
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onStop() {

    }
    /**
     * 请求总资产
     */
    public void requestAllMoneyData() {
        HashMap<String, String> map = new HashMap<String, String>();
        new RR303026(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                RSelectPropertBean data = (RSelectPropertBean)bundle.getSerializable(RR303026.BUNDLE_KEY_SELECT_PROPERT);
                 mFragment.getAllMoneyData(data);
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(RR303026.ERROR_INFO));
            }
        }).request();
    }
}
