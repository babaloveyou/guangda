package com.thinkive.android.trade_bz.a_stock.bll;

import android.content.Context;
import android.os.Bundle;

import com.thinkive.android.trade_bz.a_stock.bean.TodayMoneyBean;
import com.thinkive.android.trade_bz.a_stock.fragment.ToadyMoneyFragment;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.request.Request301516;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 当日资金流水的业务类
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/6/25
 */

public class ToadyMoneyServicesImpl {
    private ToadyMoneyFragment mToadyMoneyFragment = null;

    public ToadyMoneyServicesImpl(ToadyMoneyFragment mFragment) {
        mToadyMoneyFragment = mFragment;
    }


    /**
     * 请求服务器，获取当日资金流水数据
     */
    public void requestTodayMoney() {
        HashMap<String, String> map = new HashMap<String, String>();
        new Request301516(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                ArrayList<TodayMoneyBean> dataList = bundle.getParcelableArrayList(Request301516.BUNDLE_KEY_TODAY_MONEY);
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
