package com.thinkive.android.trade_bz.a_option.bll;

import android.content.Context;
import android.os.Bundle;

import com.thinkive.android.trade_bz.a_option.bean.OptionHoldStockBean;
import com.thinkive.android.trade_bz.a_option.fragment.OptionHoldSelectFragment;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.request.Option305003;
import com.thinkive.android.trade_bz.utils.ToastUtils;
import com.thinkive.android.trade_bz.utils.TradeUtils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 个股期权--查询--持仓查询
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/6/15
 */

public class OptionHoldSelectServicesImpl {
    private OptionHoldSelectFragment mFragment =null;

    public OptionHoldSelectServicesImpl(OptionHoldSelectFragment mFragment) {
        this.mFragment =mFragment;
    }

    /**
     * 发起请求，获取持仓列表
     */
    public void requestHoldStock() {
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
                mFragment.onGetHoldDataList(dataList);
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(Option305003.ERROR_INFO));
            }
        }).request();
    }
}
