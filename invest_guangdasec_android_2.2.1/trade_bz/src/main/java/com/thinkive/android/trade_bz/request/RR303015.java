package com.thinkive.android.trade_bz.request;

import android.os.Bundle;

import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.others.constants.Constants;

import org.json.JSONObject;

import java.util.HashMap;

/**
 *  融资融券交易--担保品划转
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/8/14
 */
public class RR303015 extends BaseCreditRequest {

    public static final String BUNDLE_KEY_TRANSFEER = "Request303015_result";

    public RR303015(HashMap<String, String> paramMap, IRequestAction action) {
        super(action);
        paramMap.put("funcNo", "303015");
        setParamHashMap(paramMap);
        System.out.println("转入参数request303015" + paramMap.toString());
        setUrlName(Constants.URL_CREDIT_TRADE);
    }

    @Override
    void getJsonDataWithoutError(JSONObject jsonObject) {
        String revocationResult;
        try {
            revocationResult = jsonObject.optString("error_info");
            Bundle bundle = new Bundle();
            bundle.putString(BUNDLE_KEY_TRANSFEER, revocationResult);
            transferAction(REQUEST_SUCCESS, bundle);
        } catch (Exception je) {
            je.printStackTrace();
        }
    }
}
