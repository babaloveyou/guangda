package com.thinkive.android.trade_bz.request;

import android.os.Bundle;

import com.thinkive.android.trade_bz.others.constants.Constants;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.utils.LogUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * 请求RSA加密入参，公共密匙（public_exponent）和模数（modulus）
 * @author 王志鸿
 * @company Thinkive
 * @date 2015/8/24
 */
public class Request1000000 extends BaseRequest {

    public static final String BUNDLE_KEY_PUBLIC_EXPONENT = "public_exponent";

    public static final String BUNDLE_KEY_MODULUS = "modulus";

    public Request1000000(HashMap<String, String> paramMap, IRequestAction action) {
        super(action);
        paramMap.put("funcNo", "1000000");
        setParamHashMap(paramMap);
        setUrlName(Constants.URL_TRADE);
    }

    @Override
    void getJsonDataWithoutError(JSONObject jsonObject) {
        LogUtil.printLog("d", jsonObject.toString());
        try {
            String resultTarget = jsonObject.getJSONArray("dsName").get(0).toString();
            JSONObject resultJson = jsonObject.getJSONArray(resultTarget).getJSONObject(0);
            Bundle bundle = new Bundle();
            String publicExponent = resultJson.getString("publicExponent");
            String modulus = resultJson.getString("modulus");
            bundle.putString(BUNDLE_KEY_PUBLIC_EXPONENT, publicExponent);
            bundle.putString(BUNDLE_KEY_MODULUS, modulus);
            transferAction(REQUEST_SUCCESS, bundle);
        } catch (JSONException je) {
            je.printStackTrace();
        }

    }
}
