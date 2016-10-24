package com.thinkive.android.trade_bz.a_rr.bll;

import android.content.Context;
import android.os.Bundle;

import com.thinkive.android.trade_bz.a_rr.bean.RSelectCollaterSecurityBean;
import com.thinkive.android.trade_bz.a_rr.fragment.RSelectObjectTicketFragment;
import com.thinkive.android.trade_bz.a_stock.bll.BasicServiceImpl;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.request.RR303006;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 融资融券--查询--标的证券--融券标的（303006）
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/8/20
 */

public class RSelectObjectTicketServiceImpl extends BasicServiceImpl {

    private RSelectObjectTicketFragment mFragment;

    public RSelectObjectTicketServiceImpl(RSelectObjectTicketFragment fragment) {
        mFragment = fragment;
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onStop() {

    }
    /**
     * 请求融券标的
     */
    public void requestObjectTicket(String code) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("stock_code",code);
        new RR303006(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                ArrayList<RSelectCollaterSecurityBean> dataList = bundle.getParcelableArrayList(RR303006.BUNDLE_KEY_TICKET);
                mFragment.getObjectTicketData(dataList);
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(RR303006.ERROR_INFO));
            }
        }).request();
    }
}
