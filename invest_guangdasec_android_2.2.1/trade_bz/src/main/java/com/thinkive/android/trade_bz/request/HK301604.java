package com.thinkive.android.trade_bz.request;

import android.os.Bundle;

import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.others.constants.Constants;

import org.json.JSONObject;

import java.util.HashMap;

/**
 * 港股通委托撤单
 * 点击撤单按钮后，发出的请求类
 * 为了获得撤单结果
 *
 * @author 王志鸿
 * @company Thinkive
 * @date 2015/7/7
 */
public class HK301604 extends BaseNormalRequest {

    public static final String BUNDLE_KEY_REVOCATION_DIALOG = "Request301604_result";

    public HK301604(HashMap<String, String> paramMap, IRequestAction action) {
        super(action);
        paramMap.put("funcNo", "301604");
        setParamHashMap(paramMap);
        setUrlName(Constants.URL_TRADE);
    }

    @Override
    void getJsonDataWithoutError(JSONObject jsonObject) {
        String revocationResult = "";
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
