package com.thinkive.android.trade_bz.request;

import android.os.Bundle;

import com.thinkive.android.trade_bz.others.constants.Constants;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * 基金定投（修改）
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/11/5
 */
public class Fund302048 extends BaseNormalRequest {

    public static final String BUNDLE_KEY_DIRECTION = "Fund302048";

    public Fund302048(HashMap<String, String> paramMap, IRequestAction action) {
        super(action);
        paramMap.put("funcNo", "302048");
        setParamHashMap(paramMap);
        setUrlName(Constants.URL_TRADE);
    }

    @Override
    void getJsonDataWithoutError(JSONObject jsonObject) {
        Bundle bundle = new Bundle();
        try {
            bundle.putString(BUNDLE_KEY_DIRECTION, jsonObject.getString(ERROR_INFO));
            transferAction(REQUEST_SUCCESS, bundle);
        } catch (JSONException je) {
            je.printStackTrace();
        }
    }
}
