package com.thinkive.android.trade_bz.request;

import android.os.Bundle;

import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.others.constants.Constants;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * 港股通买入、卖出委托提交请求。
 * @author 王志鸿
 * @company Thinkive
 * @date 2015/7/7
 */
public class HK301603 extends BaseNormalRequest {

    public static final String BUNDLE_KEY_RESULT = "bundle_key_result";

    public HK301603(HashMap<String, String> paramMap, IRequestAction action) {
        super(action);
        paramMap.put("funcNo", "301603");
        setParamHashMap(paramMap);
        setUrlName(Constants.URL_TRADE);
    }

    @Override
    void getJsonDataWithoutError(JSONObject jsonObject) {
        Bundle bundle = new Bundle();
        try {
            bundle.putString(BUNDLE_KEY_RESULT, jsonObject.getString(ERROR_INFO));
            transferAction(REQUEST_SUCCESS, bundle);
        } catch (JSONException je) {
            je.printStackTrace();
        }
    }
}
