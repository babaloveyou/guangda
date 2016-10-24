package com.thinkive.android.trade_bz.a_option.bll;

import android.content.Context;
import android.os.Bundle;

import com.thinkive.android.trade_bz.a_option.bean.OptionHistoryListBean;
import com.thinkive.android.trade_bz.a_option.fragment.OptionHistoryListFragment;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.request.Option305017;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;
/**
 * 个股期权--查询--历史对账单查询
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/6/15
 */

public class OptionHistoryListServicesImpl {
    private OptionHistoryListFragment mFragment =null;

    public OptionHistoryListServicesImpl(OptionHistoryListFragment mFragment) {
        this.mFragment =mFragment;
    }


    /**
     * 请求数据列表
     */
    public void requestHistoryList(String begin,String end) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("begin_date", begin);
        map.put("end_date", end);
        new Option305017(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                ArrayList<OptionHistoryListBean> dataList = bundle.getParcelableArrayList(Option305017.BUNDLE_KEY_OPTION_HISTORY);
                for(OptionHistoryListBean bean : dataList){
//                    bean.setMarket_value(TradeUtils.formatDouble2(bean.getMarket_value()));
//                    bean.setCost_price(TradeUtils.formatDouble2(bean.getCost_price()));
//                    bean.setCost_balance(TradeUtils.formatDouble2(bean.getCost_balance()));
//                    bean.setLast_price(TradeUtils.formatDouble2(bean.getLast_price()));
                }
                mFragment.onGetHistoryList(dataList);
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(Option305017.ERROR_INFO));
            }
        }).request();
    }
}
