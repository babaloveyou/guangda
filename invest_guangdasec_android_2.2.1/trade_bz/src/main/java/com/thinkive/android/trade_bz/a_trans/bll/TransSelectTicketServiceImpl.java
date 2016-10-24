package com.thinkive.android.trade_bz.a_trans.bll;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;

import com.thinkive.android.trade_bz.a_stock.bll.BasicServiceImpl;
import com.thinkive.android.trade_bz.a_trans.bean.TransSelectTicketBean;
import com.thinkive.android.trade_bz.a_trans.fragment.TransSelectTicketFragment;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.request.Trans301713;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * 转股交易 挂牌股票查询
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/7/29
 */

public class TransSelectTicketServiceImpl extends BasicServiceImpl {

    private TransSelectTicketFragment mFragment;

    public TransSelectTicketServiceImpl(TransSelectTicketFragment fragment) {
        mFragment = fragment;
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onStop() {


    }
    /**
     * 挂牌股票查询
     */
    public void requestObjectCapital(String code) {
        HashMap<String, String> map = new HashMap<String, String>();
        if(!TextUtils.isEmpty(code)){
            map.put("stock_code",code);
        }
        new Trans301713(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                ArrayList<TransSelectTicketBean> dataList = bundle.getParcelableArrayList(Trans301713.BUNDLE_KEY_HISTORY_TRADE);
                mFragment.getObjectCapitalData(dataList);
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(Trans301713.ERROR_INFO));
            }
        }).request();
    }
}
