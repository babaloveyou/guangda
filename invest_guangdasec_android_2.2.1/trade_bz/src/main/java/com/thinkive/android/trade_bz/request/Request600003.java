package com.thinkive.android.trade_bz.request;

import android.os.Bundle;

import com.thinkive.android.trade_bz.a_stock.bean.UserInfo;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.others.constants.Constants;
import com.thinkive.android.trade_bz.others.tools.TradeLoginManager;
import com.thinkive.android.trade_bz.utils.JsonParseUtils;
import com.thinkive.android.trade_bz.utils.LogUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * 模拟登录（普通客户号）
 * @author 王志鸿
 * @company Thinkive
 * @date 2015/8/17
 */
public class Request600003 extends BaseRequest {
    private String mAcctType;
    /**
     * 如果交易不用资金账号登录，建议删除此变量
     */
    private String mAccount;

    public Request600003(String acctType,HashMap<String, String> paramMap, IRequestAction action,
                         String account) {
        super(action);
        mAccount = account;
        mAcctType = acctType;
        paramMap.put("funcNo", "600003");
        setParamHashMap(paramMap);
        setUrlName(Constants.URL_TRADE);
    }

    @Override
    void getErrorInfoWithoutError(String string) {

    }

    @Override
    void getJsonDataWithoutError(JSONObject jsonObject) {
        LogUtil.printLog("d", jsonObject.toString());
        try {
            String resultTarget = jsonObject.getJSONArray("dsName").get(0).toString();
            JSONArray jArray = jsonObject.getJSONArray(resultTarget);
            JSONObject tempJsonObject;
            UserInfo tempUserInfo;
            int jArrayLength = jArray.length();
            for (int i = 0; i < jArrayLength; i++) {
                tempJsonObject = jArray.getJSONObject(i);
                tempUserInfo = JsonParseUtils.createBean(UserInfo.class, tempJsonObject);
                tempUserInfo.setEntrust_way(TradeLoginManager.ENTRUST_WAY);
                tempUserInfo.setOp_station(TradeLoginManager.OP_STATION_2);
                tempUserInfo.setFund_account(mAccount);   //todo:标准版演示环境使用输入的fundAccount当做资金账号  项目中注释掉代码
                switch (mAcctType) {
                    case TradeLoginManager.LOGIN_TYPE_CREDIT_ACCOUNT: //两融
                        switch (tempUserInfo.getExchange_type()) {
                            case "0":// 0：深A，
                            case "1": // 1：深B，
                                TradeLoginManager.sCreditUserInfo_shen = tempUserInfo;
                                break;
                            case "2":// 2：沪A，
                            case "3":// 3：沪B，
                                TradeLoginManager.sCreditUserInfo_hu = tempUserInfo;
                                break;
                        }
                        //无论如何保证信用用户信息有值
                        TradeLoginManager.sCreditUserInfo = tempUserInfo;
                        TradeLoginManager.sCreditUserInfo_json = tempJsonObject;
                        break;
                    case TradeLoginManager.LOGIN_TYPE_OPTION_ACCOUNT: //个股期权
                        switch (tempUserInfo.getExchange_type()) {
                            case "0":// 0：深A，
                            case "1": // 1：深B，
                                TradeLoginManager.sOptionUserInfo_shen = tempUserInfo;
                                break;
                            case "2":// 2：沪A，
                            case "3":// 3：沪B，
                                TradeLoginManager.sOptionUserInfo_hu = tempUserInfo;
                                break;
                        }
                        //无论如何保证信用用户信息有值
                        TradeLoginManager.sOptionUserInfo = tempUserInfo;
                        TradeLoginManager.sOptionUserInfo_json = tempJsonObject;
                        break;
                    default: //资金账号
                        switch (tempUserInfo.getExchange_type()) {
                            case "0":// 0：深A，
                                TradeLoginManager.sNormalUserInfo_shen_A = tempUserInfo;
                                break;
                            case "1": // 1：深B，
                                TradeLoginManager.sNormalUserInfo_shen_B = tempUserInfo;
                                break;
                            case "2":// 2：沪A，
                                TradeLoginManager.sNormalUserInfo_hu_A = tempUserInfo;
                                break;
                            case "3":// 3：沪B，
                                TradeLoginManager.sNormalUserInfo_hu_B = tempUserInfo;
                                break;
                            case "4"://4:三板
                                TradeLoginManager.sNormalUserInfo_three = tempUserInfo;
                                break;
                            case "G":// G:港股
                                TradeLoginManager.sNormalUserInfo_hk = tempUserInfo;
                                break;
                        }
                        //无论如何保证普通用户信息有值
                        TradeLoginManager.sNormalUserInfo = tempUserInfo;
                        TradeLoginManager.sNormalUserInfo_json = tempJsonObject;
                        break;
                }
            }
            transferAction(REQUEST_SUCCESS, new Bundle());
        } catch (JSONException je) {
            je.printStackTrace();
        }
    }
}

