package com.thinkive.android.trade_bz.a_rr.bll;

import android.content.Context;
import android.os.Bundle;

import com.thinkive.android.trade_bz.a_rr.fragment.CreditBottomHolderFragment;
import com.thinkive.android.trade_bz.a_stock.bean.MyStoreStockBean;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.request.RR303003;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Administrator on 2016/10/26.
 */
public class CreditBottomHolderServicesImpl {
    private CreditBottomHolderFragment mCreditBottomHolderFragment;


    public CreditBottomHolderServicesImpl(CreditBottomHolderFragment mFragment) {
        mCreditBottomHolderFragment = mFragment;
    }

    public void getHoldList() {
        HashMap<String, String> paramMap = new HashMap<String, String>();
        new RR303003(paramMap, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                ArrayList<MyStoreStockBean> dataList = bundle.getParcelableArrayList(RR303003.BUNDLE_KEY_ROLLATER);
                mCreditBottomHolderFragment.getStoreData(dataList);
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(RR303003.ERROR_INFO));
            }
        }).request();
    }

}
