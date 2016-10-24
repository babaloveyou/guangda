package com.thinkive.android.trade_bz.request;

import android.os.Bundle;

import com.thinkive.android.trade_bz.a_stock.bean.PublicUseBean;
import com.thinkive.android.trade_bz.others.constants.Constants;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.utils.JsonParseUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 *  是否做过风险测评
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/11/5
 */
public class Fund302052 extends BaseNormalRequest {

    public static final String BUNDLE_KEY_RISK_IS_OK = "Request302052_key";

    public Fund302052(HashMap<String, String> paramMap, IRequestAction action) {
        super(action);
        paramMap.put("funcNo", "302052");
        setParamHashMap(paramMap);
        setUrlName(Constants.URL_TRADE);
    }

    @Override
    void getJsonDataWithoutError(JSONObject jsonObject) {
        Bundle bundle = new Bundle();
        try {
            String resultTarget = jsonObject.getJSONArray("dsName").get(0).toString();
            JSONObject resultJsonObject = jsonObject.getJSONArray(resultTarget).getJSONObject(0);
            PublicUseBean bean;
            if(resultJsonObject != null){
                bean = JsonParseUtils.createBean(PublicUseBean.class, resultJsonObject);
                bundle.putSerializable(BUNDLE_KEY_RISK_IS_OK, bean);
                transferAction(REQUEST_SUCCESS, bundle);
            }else {
                bundle.putString(ERROR_INFO, jsonObject.getString(ERROR_INFO));
                transferAction(REQUEST_FAILED, bundle);
            }
        } catch (JSONException je) {
            je.printStackTrace();
        }
    }
}
