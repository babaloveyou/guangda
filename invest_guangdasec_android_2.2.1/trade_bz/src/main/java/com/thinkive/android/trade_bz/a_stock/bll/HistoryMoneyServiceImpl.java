package com.thinkive.android.trade_bz.a_stock.bll;

import android.content.Context;
import android.os.Bundle;

import com.thinkive.android.trade_bz.a_stock.bean.StatementAccountBean;
import com.thinkive.android.trade_bz.a_stock.fragment.HistoryMoneyFragment;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.request.Request301516;
import com.thinkive.android.trade_bz.request.Request301520;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Administrator on 2016/11/14.
 */
public class HistoryMoneyServiceImpl {
    private HistoryMoneyFragment mToadyMoneyFragment = null;

    public HistoryMoneyServiceImpl(HistoryMoneyFragment mFragment) {
        mToadyMoneyFragment = mFragment;
    }


    /**
     * 请求服务器，获取当日资金流水数据
     */
    public void requestHistoryMoney(String begin, String end) {
        HashMap<String, String> map301520 = new HashMap<String, String>();
        map301520.put("begin_time", begin);
        map301520.put("end_time", end);
        new Request301520(map301520, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                ArrayList<StatementAccountBean> dataList = bundle.getParcelableArrayList(Request301520.BUNDLE_KEY_STATEMENT);
                if (dataList != null) {
                    //保留小数点后两位
                    //                    for (TodayMoneyBean bean : dataList) {
                    //                        bean.setOccur_balance(TradeUtils.formatDouble2(bean.getOccur_balance()));
                    //                        bean.setEnable_balance(TradeUtils.formatDouble2(bean.getEnable_balance()));
                    //                        bean.setOrderprice(TradeUtils.formatDouble2(bean.getOrderprice()));
                    //                        bean.setMatchprice(TradeUtils.formatDouble2(bean.getMatchprice()));
                    //                    }
                }
                //将数据传到 ToadyMoneyFragment
                mToadyMoneyFragment.onGetTodayMoneyWater(dataList);
            }

            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(Request301516.ERROR_INFO));
            }
        }).request();
    }
}
