package com.thinkive.android.trade_bz.a_rr.bll;

import android.content.Context;
import android.os.Bundle;

import com.thinkive.android.trade_bz.a_rr.bean.RSelectContractBean;
import com.thinkive.android.trade_bz.a_rr.fragment.RSelectContractYesFragment;
import com.thinkive.android.trade_bz.a_stock.bll.BasicServiceImpl;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.request.RR303035;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 融资融券--查询--合约查询（303035）
 *    已了结合约
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/8/18
 */
public class RSelectContractYesServiceImpl extends BasicServiceImpl {

    private RSelectContractYesFragment mFragment =null;

    public RSelectContractYesServiceImpl(RSelectContractYesFragment fragment) {
        mFragment = fragment;
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onStop() {

    }
    /**
     * 请求已了结合约
     */
    public void requestContractYesData(String begin, String end) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("begin_date",begin);
        map.put("end_date",end);
//        map.put("query_type","1"); //查询模式(0-未了结，1-当日已了结)
        map.put("status","1"); // 0-未偿还，1-已偿还
        new RR303035(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                ArrayList<RSelectContractBean> dataList = bundle.getParcelableArrayList(RR303035.BUNDLE_KEY_RTODAY_CONTRACT);
                mFragment.getContractYesData(dataList);
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(RR303035.ERROR_INFO));
            }
        }).request();
    }
}
