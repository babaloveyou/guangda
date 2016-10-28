package com.thinkive.android.trade_bz.request;

import android.os.Bundle;

import com.thinkive.android.trade_bz.others.constants.Constants;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;

import org.json.JSONObject;

import java.util.HashMap;

/**
 * 委托下单
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/1/20
 */
public class Request303001 extends BaseCreditRequest {

    public static final String BUNDLE_KEY_ENTRUST_ORDER = "Request303001";

    public Request303001(HashMap<String, String> paramMap, IRequestAction action) {
        super(action);
        paramMap.put("funcNo", "303001");
        setParamHashMap(paramMap);
        if (paramMap.get("entrust_bs").equals("1")) {
            System.out.println("融卖参数" + paramMap.toString());
        }
        setUrlName(Constants.URL_CREDIT_TRADE);
    }

    @Override
    void getJsonDataWithoutError(JSONObject jsonObject) {
        String result;
        try {
            result = jsonObject.optString("error_info");
            Bundle bundle = new Bundle();
            bundle.putString(BUNDLE_KEY_ENTRUST_ORDER, result);
            transferAction(REQUEST_SUCCESS, bundle);
        } catch (Exception je) {
            je.printStackTrace();
        }
    }
}
