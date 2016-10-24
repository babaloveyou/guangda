package com.thinkive.android.trade_bz.request;

import android.os.Bundle;

import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.others.constants.Constants;

import org.json.JSONObject;

import java.util.HashMap;

/**
 * 融资融券——下单——合约展期——选择合约——功能号303055
 * @author 王延龙
 * @company ThinkIve
 * @date 2016/4/19.
 */
public class RR303055 extends BaseCreditRequest {
    public static final String BUNDLE_KEY_CHOOSE_EXTENSION = "Request303055_result";

    public RR303055(HashMap<String, String> paramMap, IRequestAction action) {
        super(action);
        paramMap.put("funcNo", "303055");
        setParamHashMap(paramMap);
        setUrlName(Constants.URL_CREDIT_TRADE);
    }

    @Override
    void getJsonDataWithoutError(JSONObject jsonObject) {
        String result;
        try {
            result = jsonObject.optString("error_info");
            Bundle bundle = new Bundle();
            bundle.putString(BUNDLE_KEY_CHOOSE_EXTENSION, result);
            transferAction(REQUEST_SUCCESS, bundle);
        } catch (Exception je) {
            je.printStackTrace();
        }
    }
}
