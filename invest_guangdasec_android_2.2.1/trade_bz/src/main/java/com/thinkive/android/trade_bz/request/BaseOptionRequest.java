package com.thinkive.android.trade_bz.request;

import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.others.tools.TradeLoginManager;

import org.json.JSONObject;

import java.util.HashMap;

/**
 * @Created 张雪梅
 * @on 2016/7/13
 * @des 个股期权请求基类
 */
public class BaseOptionRequest extends BaseRequest{
    /**
     * @param action
     */
    public BaseOptionRequest(IRequestAction action) {
        super(action);
    }

    @Override
    void getJsonDataWithoutError(JSONObject jsonObject) {}

    /**
     * 个股期权都需要添加公共入参
     * @param paramHashMap
     */
    @Override
    public void setPublicParam(HashMap<String, String> paramHashMap) {
        super.setPublicParam(paramHashMap);
        paramHashMap.put("acct_type", TradeLoginManager.sOptionLoginType);
        paramHashMap.put("entrust_way", TradeLoginManager.sOptionUserInfo.getEntrust_way());
        paramHashMap.put("branch_no", TradeLoginManager.sOptionUserInfo.getBranch_no());
        paramHashMap.put("fund_account", TradeLoginManager.sOptionUserInfo.getFund_account());
        paramHashMap.put("cust_code", TradeLoginManager.sOptionUserInfo.getCust_code());
        paramHashMap.put("op_station", TradeLoginManager.sOptionUserInfo.getOp_station());
    }
}
