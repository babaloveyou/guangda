package com.thinkive.android.trade_bz.a_out.bll;

import android.content.Context;
import android.os.Bundle;

import com.thinkive.android.trade_bz.a_out.bean.FundAccountBean;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.request.Fund302001;
import com.thinkive.android.trade_bz.a_out.fragment.FundAccountFragment;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *  基金交易--查询--基金账户查询
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/8/7
 */

public class FundAccountServicesImpl {
    private FundAccountFragment mFragment = null;

    public FundAccountServicesImpl(FundAccountFragment fragment) {
        mFragment = fragment;
    }

    /**
     * 请求基金账户数据列表
     */
    public void requestFundAccount() {
        HashMap<String, String> map = new HashMap<String, String>();
        new Fund302001(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                ArrayList<FundAccountBean> data = bundle.getParcelableArrayList(Fund302001.BUNDLE_KEY_FUND_ACCOUNT);
                mFragment.onGetFundAccountData(data);
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(Fund302001.ERROR_INFO));
            }
        }).request();
    }
}
