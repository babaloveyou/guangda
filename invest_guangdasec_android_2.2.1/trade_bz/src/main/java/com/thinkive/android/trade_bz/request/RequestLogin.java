package com.thinkive.android.trade_bz.request;

import android.content.Context;
import android.os.Bundle;

import com.android.thinkive.framework.storage.MemoryStorage;
import com.thinkive.android.trade_bz.a_stock.bean.LoginInfo;
import com.thinkive.android.trade_bz.a_stock.bean.SignStockAccountLimitBean;
import com.thinkive.android.trade_bz.a_stock.bean.UserInfo;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.others.constants.Constants;
import com.thinkive.android.trade_bz.others.tools.TradeLoginManager;
import com.thinkive.android.trade_bz.utils.JsonParseUtils;
import com.thinkive.android.trade_bz.utils.ToastUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import static com.thinkive.android.trade_bz.others.tools.TradeLoginManager.sNormalUserInfo;

/**
 * 登录请求类
 * 普通登录：调用功能号300100，融资融券登录：调用功能号1000010
 * @author 王志鸿
 * @company Thinkive
 * @date 2015/7/6
 */
public class RequestLogin extends BaseRequest {
    public static final String BUNDLE_KEY_LOGIN = "Requestlogin_result";

    private String mLoginType;

    public RequestLogin(HashMap<String, String> paramMap, IRequestAction action, String loginType) {
        super(action);
        setParamHashMap(paramMap);
        mLoginType = loginType;
        // 如果是融资融券账户登录
        if (mLoginType.equals(TradeLoginManager.LOGIN_TYPE_CREDIT) || mLoginType.equals(TradeLoginManager.LOGIN_TYPE_NORMAL_IN_CREDIT)) {
            setUrlName(Constants.URL_CREDIT_TRADE);
            // 如果是普通账号登录
        } else if (mLoginType.equals(TradeLoginManager.LOGIN_TYPE_NORMAL)) {
            setUrlName(Constants.URL_TRADE);
            // 如果是普通账号登录
        }else if (mLoginType.equals(TradeLoginManager.LOGIN_TYPE_OPTION)) {
            setUrlName(Constants.URL_TRADE);
        }
    }

    @Override
    void getJsonDataWithoutError(JSONObject jsonObject) {
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_KEY_LOGIN,jsonObject.toString());
        transferAction(REQUEST_SUCCESS, bundle);

        try {
            // 如果是普通账户登录
            if (mLoginType.equals(TradeLoginManager.LOGIN_TYPE_NORMAL)) {
                String resultTarget = jsonObject.getJSONArray("dsName").get(0).toString();
                JSONArray jArray = jsonObject.getJSONArray(resultTarget);
                JSONObject tempJsonObject;
                UserInfo tempUserInfo;
                int jArrayLength = jArray.length();
                for (int i = 0; i < jArrayLength; i++) {
                    tempJsonObject = jArray.getJSONObject(i);
                    MemoryStorage storage = new MemoryStorage();
//                    storage.saveData("stock_userInfo",tempJsonObject.toString());
                    tempUserInfo = JsonParseUtils.createBean(UserInfo.class, tempJsonObject);
                    tempUserInfo.setEntrust_way(TradeLoginManager.ENTRUST_WAY);
                    tempUserInfo.setOp_station(TradeLoginManager.OP_STATION_2);
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
                    sNormalUserInfo = tempUserInfo;
                    TradeLoginManager.sNormalUserInfo_json = tempJsonObject;
                    //请求300115功能接口
                    request300115(sNormalUserInfo, TradeLoginManager.sNormalUserInfo_json );
                }
                // 如果是融资融券
            } else if (mLoginType.equals(TradeLoginManager.LOGIN_TYPE_CREDIT)) {
                String resultTarget = jsonObject.getJSONArray("dsName").get(0).toString();
                JSONArray jArray = jsonObject.getJSONArray(resultTarget);
                JSONObject tempJsonObject;
                UserInfo tempUserInfo;
                for (int i = 0; i < jArray.length(); i++) {
                    tempJsonObject = jArray.getJSONObject(i);
                    MemoryStorage storage1 = new MemoryStorage();
//                    storage1.saveData("credit_userInfo",tempJsonObject.toString());
                    tempUserInfo = JsonParseUtils.createBean(UserInfo.class, tempJsonObject);
                    tempUserInfo.setEntrust_way(TradeLoginManager.ENTRUST_WAY);
                    tempUserInfo.setOp_station(TradeLoginManager.OP_STATION_2);
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
                }
                // 如果是个股期权
            }else if (mLoginType.equals(TradeLoginManager.LOGIN_TYPE_OPTION)) {
                String resultTarget = jsonObject.getJSONArray("dsName").get(0).toString();
                JSONArray jArray = jsonObject.getJSONArray(resultTarget);
                JSONObject tempJsonObject;
                UserInfo tempUserInfo;
                for (int i = 0; i < jArray.length(); i++) {
                    tempJsonObject = jArray.getJSONObject(i);
                    tempUserInfo = JsonParseUtils.createBean(UserInfo.class, tempJsonObject);
                    tempUserInfo.setEntrust_way(TradeLoginManager.ENTRUST_WAY);
                    tempUserInfo.setOp_station(TradeLoginManager.OP_STATION_2);
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
                }
                // 在融资融券中登录普通交易
            }else if (mLoginType.equals(TradeLoginManager.LOGIN_TYPE_NORMAL_IN_CREDIT)) {
                String resultTarget = jsonObject.getJSONArray("dsName").get(0).toString();
                JSONArray jArray = jsonObject.getJSONArray(resultTarget);
                JSONObject tempJsonObject;
                UserInfo tempUserInfo;
                for (int i = 0; i < jArray.length(); i++) {
                    tempJsonObject = jArray.getJSONObject(i);
                    tempUserInfo = JsonParseUtils.createBean(UserInfo.class, tempJsonObject);
                    tempUserInfo.setEntrust_way(TradeLoginManager.ENTRUST_WAY);
                    tempUserInfo.setOp_station(TradeLoginManager.OP_STATION_2);
                    TradeLoginManager.sNormalUserInfo_in_credit = tempUserInfo;
                }
            }
            transferAction(REQUEST_SUCCESS, new Bundle());
        } catch (JSONException je) {
            je.printStackTrace();
        }
    }

    private void request300115(UserInfo normalUserInfo, JSONObject jsonObject) {
        HashMap<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("funcNo", "300115");
        paramMap.put("entrust_way","SJWT");
        paramMap.put("branch_no", jsonObject.optString("branch_no"));
        paramMap.put("client_name", jsonObject.optString("client_name"));
        paramMap.put("cust_code",jsonObject.optString("cust_code"));
        paramMap.put("fund_account", jsonObject.optString("fund_account"));
        paramMap.put("holder_rights", jsonObject.optString("holder_rights"));
        paramMap.put("input_content",jsonObject.optString("input_content") );
        paramMap.put("input_type",jsonObject.optString("input_type") );
        paramMap.put("jsessionid", jsonObject.optString("jsessionid"));
        paramMap.put("trade_flag", jsonObject.optString("trade_flag"));
        paramMap.put("op_station", TradeLoginManager.OP_STATION_2 );
        new Request300115(paramMap, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                ArrayList<SignStockAccountLimitBean> stockLimitBeans = (ArrayList<SignStockAccountLimitBean>) bundle.getSerializable(Request300115.BUNDLE_KEY_STOCK_ACCOUNT);
                LoginInfo.setStockLimitBeans(stockLimitBeans);
                LoginInfo.setSelectHolderAccount(stockLimitBeans);
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                String error = bundle.getString(Request300115.BUNDLE_KEY_STOCK_ACCOUNT);
                ToastUtil.showToast(error);
            }
        }).request();
    }
}
