package com.thinkive.android.trade_bz.a_stock.bll;

import android.content.Context;
import android.os.Bundle;

import com.thinkive.android.trade_bz.a_stock.bean.HistoryEntrustBean;
import com.thinkive.android.trade_bz.a_stock.fragment.HistoryEntrustFragment;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.request.Request301510;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 历史委托的业务类
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/6/23
 */

public class HistoryEntrustServicesImpl {
    private HistoryEntrustFragment mHistoryEntrustFragment = null;

    public HistoryEntrustServicesImpl(HistoryEntrustFragment mFragment) {
        mHistoryEntrustFragment = mFragment;
    }

    /**
     * 初始化请求到的历史委托数据
     * @param begin
     * @param end
     */
    public void requestHistoryEntrust(String begin, String end) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("begin_time", begin);
        map.put("end_time", end);
        new Request301510(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                ArrayList<HistoryEntrustBean> dataList = bundle.getParcelableArrayList(Request301510.BUNDLE_KEY_HISTORY_ENTRUST);
                //保留小数点后两位
//                for (HistoryEntrustBean bean : dataList) {
//                    bean.setBusiness_price(TradeUtils.formatDouble2(bean.getBusiness_price()));
//                    bean.setEntrust_price(TradeUtils.formatDouble2(bean.getEntrust_price()));
//                }
                mHistoryEntrustFragment.onGetHistoryEntrustData(dataList);
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(Request301510.ERROR_INFO));
            }

        }).request();
    }
}
