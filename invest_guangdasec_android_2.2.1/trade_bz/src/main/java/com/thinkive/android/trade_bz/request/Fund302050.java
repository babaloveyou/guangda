package com.thinkive.android.trade_bz.request;

import android.os.Bundle;

import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.others.constants.Constants;

import org.json.JSONObject;

import java.util.HashMap;

/**
 * 定投终止
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/6/1
 */
public class Fund302050 extends BaseNormalRequest {

    public static final String BUNDLE_KEY_REVOCATION_DIALOG = "Fund302050";

    public Fund302050(HashMap<String, String> paramMap, IRequestAction action) {
        super(action);
        paramMap.put("funcNo", "302050");
        setParamHashMap(paramMap);
        setUrlName(Constants.URL_TRADE);
    }

    @Override
    void getJsonDataWithoutError(JSONObject jsonObject) {
        String revocationResult;
        try {
            revocationResult = jsonObject.optString("error_info");
            Bundle bundle = new Bundle();
            bundle.putString(BUNDLE_KEY_REVOCATION_DIALOG, revocationResult);
            transferAction(REQUEST_SUCCESS, bundle);
        } catch (Exception je) {
            je.printStackTrace();
        }
    }
}
