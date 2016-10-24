package com.thinkive.android.trade_bz.a_out.bll;

import android.content.Context;
import android.os.Bundle;

import com.thinkive.android.trade_bz.a_stock.bean.PublicUseBean;
import com.thinkive.android.trade_bz.a_out.bean.FundHoldBean;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.request.Fund302012;
import com.thinkive.android.trade_bz.request.Fund302016;
import com.thinkive.android.trade_bz.a_out.fragment.FundShareSetFragment;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *  场外基金分红设置
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/10/28.
 */

public class FundShareSetServiceImpl {

    private FundShareSetFragment mFragment;

    public FundShareSetServiceImpl(FundShareSetFragment fragment) {
        mFragment=fragment;
    }

    /**
     * 持仓基金列表
     */
    public void requestHoldStockList() {
        HashMap<String, String> map = new HashMap<String, String>();
        new Fund302012(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                ArrayList<FundHoldBean> dataList = bundle.getParcelableArrayList(Fund302012.BUNDLE_KEY_FUND_RANSOM);
                mFragment.onGetFundHoldList(dataList);
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(Fund302012.ERROR_INFO));
            }
        }).request();
    }

    /**
     * 设置分红
     */
    public void requestShareSet(String shareSet,String fundCode,String company) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("dividendmethod",shareSet);
        map.put("fund_code",fundCode);
        map.put("fund_company",company);
        new Fund302016(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                PublicUseBean data = (PublicUseBean)bundle.getSerializable(Fund302016.BUNDLE_KEY_SHARE_SET);
                mFragment.onGetShareSetResult(data);
                requestHoldStockList();
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(Fund302016.ERROR_INFO));
                requestHoldStockList();
            }
        }).request();
    }
}
