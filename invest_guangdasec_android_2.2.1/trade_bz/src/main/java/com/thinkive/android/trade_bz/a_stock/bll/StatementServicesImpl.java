package com.thinkive.android.trade_bz.a_stock.bll;

import android.content.Context;
import android.os.Bundle;

import com.thinkive.android.trade_bz.a_stock.bean.StatementAccountBean;
import com.thinkive.android.trade_bz.a_stock.fragment.StatementAccountFragment;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.request.Request301520;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 对账单的业务类
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/6/24
 */

public class StatementServicesImpl  {
    private StatementAccountFragment mStatementAccountFragment=null;

    public StatementServicesImpl(StatementAccountFragment mFragment) {
        mStatementAccountFragment=mFragment;
    }

    /**
     * 初始化请求到的对账单数据
     * @param begin
     * @param end
     */
    public void requestStatement(String begin, String end){
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("begin_date", begin);
        map.put("end_date", end);
        new Request301520(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                ArrayList<StatementAccountBean> dataList = bundle.getParcelableArrayList(Request301520.BUNDLE_KEY_STATEMENT);
                //保留小数点后两位
//                for (StatementAccountBean bean : dataList) {
//                    bean.setBusiness_price(TradeUtils.formatDouble2(bean.getBusiness_price()));
//                    bean.setMatchamt(TradeUtils.formatDouble2(bean.getMatchamt()));
//                    bean.setStkbal(TradeUtils.formatDouble2(bean.getStkbal()));
//                    bean.setOccur_balance(TradeUtils.formatDouble2(bean.getOccur_balance()));
//                    bean.setEnable_balance(TradeUtils.formatDouble2(bean.getEnable_balance()));
//                    bean.setStkbal(TradeUtils.formatDouble2(bean.getStkbal()));
//                }

                mStatementAccountFragment.onGetStatementData(dataList);
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(Request301520.ERROR_INFO));
            }
        }).request();
    }
}
