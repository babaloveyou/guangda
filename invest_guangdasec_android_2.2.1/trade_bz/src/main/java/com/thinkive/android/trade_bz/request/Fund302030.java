package com.thinkive.android.trade_bz.request;

import android.os.Bundle;

import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.others.constants.Constants;

import org.json.JSONObject;

import java.util.HashMap;

/**
 * 基金开户
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/2/25
 */
public class Fund302030 extends BaseNormalRequest {

    public static final String BUNDLE_KEY_OPEN_FUND = "Fund302030";

    public Fund302030(HashMap<String, String> paramMap, IRequestAction action) {
        super(action);
        paramMap.put("funcNo", "302030");
        setParamHashMap(paramMap);
        setUrlName(Constants.URL_TRADE);
    }

    @Override
    void getJsonDataWithoutError(JSONObject jsonObject) {
        String revocationResult;
        try {
            revocationResult = jsonObject.optString("error_info");
            Bundle bundle = new Bundle();
            bundle.putString(BUNDLE_KEY_OPEN_FUND, revocationResult);
            transferAction(REQUEST_SUCCESS, bundle);
        } catch (Exception je) {
            je.printStackTrace();
        }
    }
}
