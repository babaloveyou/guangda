package com.thinkive.android.trade_bz.a_option.bll;

import android.content.Context;
import android.os.Bundle;

import com.thinkive.android.trade_bz.a_option.bean.OptionHoldStockBean;
import com.thinkive.android.trade_bz.a_option.bean.OptionMoneyBean;
import com.thinkive.android.trade_bz.a_option.fragment.OptionHoldFragment;
import com.thinkive.android.trade_bz.a_stock.bll.BasicServiceImpl;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.request.Option305003;
import com.thinkive.android.trade_bz.request.Option305004;
import com.thinkive.android.trade_bz.utils.ToastUtils;
import com.thinkive.android.trade_bz.utils.TradeUtils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 转股交易 我的持仓
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/8/7
 */

public class OptionStockHoldServicesImpl extends BasicServiceImpl {

    private OptionHoldFragment mFragment = null;

    public OptionStockHoldServicesImpl(OptionHoldFragment fragment) {
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
        new Option305003(paramMap, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                ArrayList<OptionHoldStockBean> dataList = bundle.getParcelableArrayList(Option305003.BUNDLE_KEY_OPTION_HOLD);
                for(OptionHoldStockBean bean : dataList){
                    bean.setMarket_value(TradeUtils.formatDouble2(bean.getMarket_value()));
                    bean.setCost_price(TradeUtils.formatDouble2(bean.getCost_price()));
                    bean.setCost_balance(TradeUtils.formatDouble2(bean.getCost_balance()));
                    bean.setLast_price(TradeUtils.formatDouble2(bean.getLast_price()));
                }
                mFragment.getHoldStockData(dataList);
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(Option305003.ERROR_INFO));
            }
        }).request();
    }

    /**
     * 资金账户查询
     */
    public void requestHoldFundData() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("money_type", "0");
        new Option305004(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                OptionMoneyBean data = (OptionMoneyBean)bundle.getSerializable(Option305004.BUNDLE_KEY_MYHOLD_HEAD);
                mFragment.getFundHoldMessage(data);
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(Option305004.ERROR_INFO));
            }
        }).request();
    }
}
