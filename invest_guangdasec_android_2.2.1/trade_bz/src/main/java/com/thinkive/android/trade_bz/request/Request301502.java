package com.thinkive.android.trade_bz.request;

import android.os.Bundle;

import com.thinkive.android.trade_bz.others.constants.Constants;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * 委托撤单下单
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/7/7
 */
public class Request301502 extends BaseNormalRequest {

    public static final String BUNDLE_KEY_REVOCATION_DIALOG = "Request301502_result";

    public Request301502(HashMap<String, String> paramMap, IRequestAction action) {
        super(action);
        paramMap.put("funcNo", "301502");
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
