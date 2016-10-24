package com.thinkive.android.trade_bz.request;

import android.os.Bundle;

import com.thinkive.android.trade_bz.a_stock.bean.MoneySelectBean;
import com.thinkive.android.trade_bz.others.constants.Constants;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.utils.JsonParseUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * 融资融券--资金账户查询
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/8/24
 */
public class Request303004 extends BaseCreditRequest {

    public static final String BUNDLE_KEY_R_MYHOLD_HEAD = "Request303004_result";

    public Request303004(HashMap<String, String> paramMap, IRequestAction action) {
        super(action);
        paramMap.put("funcNo", "303004");
        setParamHashMap(paramMap);
        setUrlName(Constants.URL_CREDIT_TRADE);
    }

    @Override
    void getJsonDataWithoutError(JSONObject jsonObject) {
        Bundle bundle = new Bundle();
        try {
            String resultTarget = jsonObject.getJSONArray("dsName").get(0).toString();
            JSONArray jArray = jsonObject.getJSONArray(resultTarget);
            if (jArray != null) {
                for (int i = 0; i < jArray.length(); i++) {
                    jsonObject = jArray.getJSONObject(i);
                }
                MoneySelectBean bean;
                bean = JsonParseUtils.createBean(MoneySelectBean.class, jsonObject);
                if (bean != null) {
                    bundle.putSerializable(BUNDLE_KEY_R_MYHOLD_HEAD, bean);
                    transferAction(REQUEST_SUCCESS, bundle);
                }else {
                    bundle.putString(ERROR_INFO, jsonObject.getString(ERROR_INFO));
                    transferAction(REQUEST_FAILED, bundle);
                }
            }
        } catch (JSONException je) {
            je.printStackTrace();
        }
    }
}
