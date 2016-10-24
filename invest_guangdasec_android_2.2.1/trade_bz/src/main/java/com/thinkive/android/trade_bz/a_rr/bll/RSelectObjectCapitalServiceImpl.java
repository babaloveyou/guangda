package com.thinkive.android.trade_bz.a_rr.bll;

import android.content.Context;
import android.os.Bundle;

import com.thinkive.android.trade_bz.a_rr.bean.RSelectCollaterSecurityBean;
import com.thinkive.android.trade_bz.a_rr.fragment.RSelectObjectCapitalFragment;
import com.thinkive.android.trade_bz.a_stock.bll.BasicServiceImpl;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.request.RR303005;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 融资融券--查询--标的证券--融资标的（303005）
 * Announcements：
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/8/20
 */

public class RSelectObjectCapitalServiceImpl extends BasicServiceImpl {

    private RSelectObjectCapitalFragment mFragment;

    public RSelectObjectCapitalServiceImpl(RSelectObjectCapitalFragment fragment) {
        mFragment = fragment;
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onStop() {


    }
    /**
     * 请求融资标的
     */
    public void requestObjectCapital(String code) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("stock_code",code);
        new RR303005(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                ArrayList<RSelectCollaterSecurityBean> dataList = bundle.getParcelableArrayList(RR303005.BUNDLE_KEY_CAPITAL);
                mFragment.getObjectCapitalData(dataList);
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(RR303005.ERROR_INFO));
            }
        }).request();
    }
}
