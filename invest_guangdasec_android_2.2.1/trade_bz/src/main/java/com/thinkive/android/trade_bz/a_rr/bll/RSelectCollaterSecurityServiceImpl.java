package com.thinkive.android.trade_bz.a_rr.bll;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;

import com.thinkive.android.trade_bz.a_rr.bean.RSelectCollaterSecurityBean;
import com.thinkive.android.trade_bz.a_rr.fragment.RSelectCollaterSecurityFragment;
import com.thinkive.android.trade_bz.a_stock.bll.BasicServiceImpl;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.request.RR303002;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *  融资融券--查询--担保品证券查询（303002）
 * Announcements：
 * @author 张雪梅
 * @company Thinkive
 * @date 15/8/19
 */
public class RSelectCollaterSecurityServiceImpl extends BasicServiceImpl {

    private RSelectCollaterSecurityFragment mHoldFragment;

    public RSelectCollaterSecurityServiceImpl(RSelectCollaterSecurityFragment fragment) {
        mHoldFragment = fragment;
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onStop() {

    }
    /**
     * 请求担保品证券
     */
    public void requestCollaterSecurity(final String code) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("stock_code",code);
            new RR303002(map, new IRequestAction() {
                @Override
                public void onSuccess(Context context, Bundle bundle) {
                    ArrayList<RSelectCollaterSecurityBean> dataList = bundle.getParcelableArrayList(RR303002.BUNDLE_KEY_COLLATER);
                    mHoldFragment.getCollaterSecurityData(dataList);
                    if (TextUtils.isEmpty(code)) {
                        mHoldFragment.saveData(dataList);
                    } else {
                        mHoldFragment.clearFocus();
                    }
                }
                @Override
                public void onFailed(Context context, Bundle bundle) {
                    ToastUtils.toast(context, bundle.getString(RR303002.ERROR_INFO));
                }
            }).request();

    }
}
