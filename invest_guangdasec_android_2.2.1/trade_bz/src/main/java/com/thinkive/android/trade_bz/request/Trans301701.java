package com.thinkive.android.trade_bz.request;

import android.os.Bundle;

import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.others.constants.Constants;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * 转股交易委托下单
 * @author 张雪梅
 * @date 2016/1/5
 */
public class Trans301701 extends BaseNormalRequest {

    public static final String BUNDLE_KEY_ORDER_RESULT = "Trans301701";

    public Trans301701(HashMap<String, String> paramMap, IRequestAction action) {
        super(action);
        paramMap.put("funcNo", "301701");
        setParamHashMap(paramMap);
        setUrlName(Constants.URL_TRADE);
    }

    @Override
    void getJsonDataWithoutError(JSONObject jsonObject) {
        Bundle bundle = new Bundle();
        try {
            bundle.putString(BUNDLE_KEY_ORDER_RESULT, jsonObject.getString(ERROR_INFO));
            transferAction(REQUEST_SUCCESS, bundle);
        } catch (JSONException je) {
            je.printStackTrace();
        }
    }
}
