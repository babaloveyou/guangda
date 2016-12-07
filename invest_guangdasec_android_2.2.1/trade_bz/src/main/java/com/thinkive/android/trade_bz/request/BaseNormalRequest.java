package com.thinkive.android.trade_bz.request;

import com.thinkive.android.trade_bz.a_stock.bean.LoginInfo;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.others.tools.EncryptManager;
import com.thinkive.android.trade_bz.others.tools.TradeLoginManager;

import org.json.JSONObject;

import java.util.HashMap;

/**
 * @Created by   Huangzq
 * @on 2016/6/13 10:30.
 * @des 普通交易的请求基类   主要作用是实现一些个性补充  例如添加公共入参
 */
public class BaseNormalRequest extends BaseRequest {
    /**
     * @param action
     */
    public BaseNormalRequest(IRequestAction action) {
        super(action);
    }

    @Override
    void getJsonDataWithoutError(JSONObject jsonObject) {

    }

    /**
     * 普通交易都需要添加公共入参
     *
     * @param paramHashMap
     */
    @Override
    public void setPublicParam(HashMap<String, String> paramHashMap) {
        super.setPublicParam(paramHashMap);
        paramHashMap.put("acct_type", TradeLoginManager.sNormalLoginType);
        paramHashMap.put("entrust_way", TradeLoginManager.sNormalUserInfo.getEntrust_way());
        paramHashMap.put("branch_no", TradeLoginManager.sNormalUserInfo.getBranch_no());
        paramHashMap.put("account", TradeLoginManager.sNormalUserInfo.getFund_account());
        paramHashMap.put("account_type", "0");
        paramHashMap.put("cust_code", TradeLoginManager.sNormalUserInfo.getCust_code());
        paramHashMap.put("op_station", TradeLoginManager.sNormalUserInfo.getOp_station());
        paramHashMap.put("sessionid", TradeLoginManager.sNormalUserInfo.getJsessionid());
        paramHashMap.put("fund_account", TradeLoginManager.sNormalUserInfo.getFund_account());
        paramHashMap.put("password", EncryptManager.getInstance().getRsaEncryptStr(LoginInfo.getPassword()) );
        paramHashMap.put("stock_account", LoginInfo.getSelectHolderAccount());
        if (TradeLoginManager.LOGIN_TYPE_SHEN_A_ACCOUNT.equals(TradeLoginManager.sNormalLoginType) ||
                TradeLoginManager.LOGIN_TYPE_SHEN_B_ACCOUNT.equals(TradeLoginManager.sNormalLoginType) ||
                TradeLoginManager.LOGIN_TYPE_HU_A_ACCOUNT.equals(TradeLoginManager.sNormalLoginType) ||
                TradeLoginManager.LOGIN_TYPE_HU_B_ACCOUNT.equals(TradeLoginManager.sNormalLoginType)) {
            paramHashMap.put("stock_account", LoginInfo.getSelectHolderAccount());
        }

    }
}
