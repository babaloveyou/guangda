package com.thinkive.android.trade_bz.request;

import android.os.Bundle;

import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.others.constants.Constants;

import org.json.JSONObject;

import java.util.HashMap;

/**
 * 新股一键申购(信用)
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/4/25
 */
public class NewStock303052 extends BaseCreditRequest {

    public static final String BUNDLE_KEY_ONE_KEY_SUB = "NewStock303052";

    public NewStock303052(HashMap<String, String> paramMap, IRequestAction action) {
        super(action);
        paramMap.put("funcNo", "303052");
        setParamHashMap(paramMap);
        setUrlName(Constants.URL_CREDIT_TRADE);
    }

    @Override
    void getJsonDataWithoutError(JSONObject jsonObject) {
        String revocationResult;
        try {
            revocationResult = jsonObject.optString("error_info");
            Bundle bundle = new Bundle();
            bundle.putString(BUNDLE_KEY_ONE_KEY_SUB, revocationResult);
            transferAction(REQUEST_SUCCESS, bundle);
        } catch (Exception je) {
            je.printStackTrace();
        }
    }
}
