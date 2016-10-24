package com.thinkive.android.trade_bz.request;

import android.os.Bundle;

import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.others.constants.Constants;

import org.json.JSONObject;

import java.util.HashMap;

/**
 *  退市板块协议签署 -客户权限增加
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/8/4
 */
public class Request300112 extends BaseNormalRequest {

    public static final String BUNDLE_KEY_ACCOUNT_LIMIT = "Request300112";

    public Request300112(HashMap<String, String> paramMap, IRequestAction action) {
        super(action);
        paramMap.put("funcNo", "300112");
        setParamHashMap(paramMap);
        setUrlName(Constants.URL_TRADE);
    }

    @Override
    void getJsonDataWithoutError(JSONObject jsonObject) {
        String revocationResult;
        try {
            revocationResult = jsonObject.optString("error_info");
            Bundle bundle = new Bundle();
            bundle.putString(BUNDLE_KEY_ACCOUNT_LIMIT, revocationResult);
            transferAction(REQUEST_SUCCESS, bundle);
        } catch (Exception je) {
            je.printStackTrace();
        }
    }
}
