package com.thinkive.android.trade_bz.request;

import android.os.Bundle;

import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.others.constants.Constants;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;


/**
 *  投票申报 提交
 * @author 张雪梅
 * @company Thinkive
 * @date 16/5/23
 */
public class HK301621 extends BaseNormalRequest {

    public static final String BUNDLE_KEY_RESULT = "HK301621";

    public HK301621(HashMap<String, String> paramMap, IRequestAction action) {
        super(action);
        paramMap.put("funcNo", "301621");
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
