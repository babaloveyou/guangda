package com.thinkive.android.trade_bz.a_rr.bll;

import android.content.Context;
import android.os.Bundle;

import com.thinkive.android.trade_bz.a_rr.bean.RRevocationBean;
import com.thinkive.android.trade_bz.a_rr.fragment.CreditBottomRevocationFragment;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.request.Request303017;
import com.thinkive.android.trade_bz.request.Request306000;
import com.thinkive.android.trade_bz.request.Request306001;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;

import static com.thinkive.android.trade_bz.request.Request301508.BUNDLE_KEY_TOADY_ENTRUST;

/**
 * Created by Administrator on 2016/10/26.
 */
public class CreditBottomRevocationServicesImpl {
    private CreditBottomRevocationFragment mCreditBottomRevocationFragment;


    public CreditBottomRevocationServicesImpl(CreditBottomRevocationFragment mFragment) {
        mCreditBottomRevocationFragment = mFragment;
    }

    public void requestRevocation() {
        //今日委托需传入data字段
        HashMap<String, String> map306000 = new HashMap<String, String>();
        new Request306000(map306000, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                String date = bundle.getString(Request306000.BUNDLE_KEY_306000);
                HashMap<String, String> map303017 = new HashMap<>();
                map303017.put("start_date", date);
                new Request303017(map303017, new IRequestAction() {
                    @Override
                    public void onSuccess(Context context, Bundle bundle) {
                        ArrayList<RRevocationBean> dataList = bundle.getParcelableArrayList(BUNDLE_KEY_TOADY_ENTRUST);
                        mCreditBottomRevocationFragment.onGetRevocationData(dataList);
                    }

                    @Override
                    public void onFailed(Context context, Bundle bundle) {
                        ToastUtils.toast(context, bundle.getString(Request303017.ERROR_INFO));
                    }
                }).request();

            }

            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(Request306001.ERROR_INFO));
            }
        }).request();
    }
}
