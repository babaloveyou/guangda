package com.thinkive.android.trade_bz.request;

import com.thinkive.android.trade_bz.a_rr.bean.CreditLoginInfo;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.others.tools.TradeLoginManager;

import org.json.JSONObject;

import java.util.HashMap;

/**
 * @Created by   Huangzq
 * @on 2016/6/13 10:30.
 * @des 信用交易的请求基类   主要作用是实现一些个性补充  例如添加公共入参
 */
public class BaseCreditRequest extends BaseRequest{
    /**
     * @param action
     */
    public BaseCreditRequest(IRequestAction action) {
        super(action);
    }

    @Override
    void getJsonDataWithoutError(JSONObject jsonObject) {}

    /**
     * 信用交易都需要添加公共入参
     * @param paramHashMap
     */
    @Override
    public void setPublicParam(HashMap<String, String> paramHashMap) {
        super.setPublicParam(paramHashMap);
        paramHashMap.put("acct_type", TradeLoginManager.sCreditLoginType);
        paramHashMap.put("entrust_way", TradeLoginManager.sCreditUserInfo.getEntrust_way());
        paramHashMap.put("branch_no", TradeLoginManager.sCreditUserInfo.getBranch_no());
        paramHashMap.put("fund_account", TradeLoginManager.sCreditUserInfo.getFund_account());
        paramHashMap.put("password", CreditLoginInfo.getPassword());
        paramHashMap.put("cust_code", TradeLoginManager.sCreditUserInfo.getCust_code());
        paramHashMap.put("op_station", TradeLoginManager.sCreditUserInfo.getOp_station());
        paramHashMap.put("sessionid", TradeLoginManager.sCreditUserInfo.getJsessionid());
    }
}
