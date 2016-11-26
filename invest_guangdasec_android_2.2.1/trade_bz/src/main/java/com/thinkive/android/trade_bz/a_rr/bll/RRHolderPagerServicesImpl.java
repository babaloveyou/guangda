package com.thinkive.android.trade_bz.a_rr.bll;

import android.content.Context;
import android.os.Bundle;

import com.thinkive.android.trade_bz.a_rr.bean.RSelectPropertBean;
import com.thinkive.android.trade_bz.a_rr.fragment.RRHolderPagerFragment;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.request.RR303026;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.util.HashMap;

/**
 * Created by Administrator on 2016/11/26.
 */
public class RRHolderPagerServicesImpl {
    private RRHolderPagerFragment mHoldPagerFragment = null;

    public RRHolderPagerServicesImpl(RRHolderPagerFragment mFragment) {
        mHoldPagerFragment = mFragment;
    }

    /**
     * 初始化请求到的资金账户数据 出入货币类型
     */
    public void requestMyHoldPager() {
        HashMap<String, String> map = new HashMap<String, String>();
        new RR303026(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                RSelectPropertBean data = (RSelectPropertBean)bundle.getSerializable(RR303026.BUNDLE_KEY_SELECT_PROPERT);
                mHoldPagerFragment.getMoneyAccountData(data);
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(RR303026.ERROR_INFO));
            }
        }).request();
    }
}
