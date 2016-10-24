package com.thinkive.android.trade_bz.a_trans.bll;

import android.content.Context;
import android.os.Bundle;

import com.thinkive.android.trade_bz.a_stock.bean.MoneySelectBean;
import com.thinkive.android.trade_bz.a_stock.bll.BasicServiceImpl;
import com.thinkive.android.trade_bz.a_trans.bean.TransHoldStockBean;
import com.thinkive.android.trade_bz.a_trans.fragment.TransStockHoldFragment;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.request.Request301504;
import com.thinkive.android.trade_bz.request.Trans301702;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 转股交易 我的持仓
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/8/7
 */

public class TransStockHoldServicesImpl extends BasicServiceImpl {

    private TransStockHoldFragment mFragment = null;

    public TransStockHoldServicesImpl(TransStockHoldFragment fragment) {
        mFragment = fragment;
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onStop() {

    }


    /**
     * 发起请求，获取持仓列表
     */
    public void getHoldList() {
        HashMap<String, String> paramMap = new HashMap<String, String>();
        new Trans301702(paramMap, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                ArrayList<TransHoldStockBean> dataList = bundle.getParcelableArrayList(Trans301702.BUNDLE_KEY_RESULT);
//                for(TransHoldStockBean bean : dataList){
//                    bean.setMarket_value(TradeUtils.formatDouble2(bean.getMarket_value()));
//                    bean.setCost_price(TradeUtils.formatDouble2(bean.getCost_price()));
//                    bean.setCost_balance(TradeUtils.formatDouble2(bean.getCost_balance()));
//                    bean.setLast_price(TradeUtils.formatDouble2(bean.getLast_price()));
//                }
                mFragment.getFundHoldStockData(dataList);
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(Trans301702.ERROR_INFO));
            }
        }).request();
    }

    /**
     * 资金账户查询
     */
    public void requestHoldFundData() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("money_type", "0");
        new Request301504(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                MoneySelectBean data = (MoneySelectBean)bundle.getSerializable(Request301504.BUNDLE_KEY_MYHOLD_HEAD);
                mFragment.getFundHoldMessage(data);
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(Request301504.ERROR_INFO));
            }
        }).request();
    }
}
