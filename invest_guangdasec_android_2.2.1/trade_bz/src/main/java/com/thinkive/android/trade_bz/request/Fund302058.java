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
 * 场外基金转托管
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/10/27
 */
public class Fund302058 extends BaseNormalRequest {

    public static final String BUNDLE_KEY_RESULT_TRANS = "Fund302058";

    public Fund302058(HashMap<String, String> paramMap, IRequestAction action) {
        super(action);
        paramMap.put("funcNo", "302058");
        setParamHashMap(paramMap);
        setUrlName(Constants.URL_TRADE);
    }

    @Override
    void getJsonDataWithoutError(JSONObject jsonObject) {
        Bundle bundle = new Bundle();
        try {
            String resultTarget = jsonObject.getJSONArray("dsName").get(0).toString();
            JSONObject jsonResult = jsonObject.getJSONArray(resultTarget).getJSONObject(0);
            if (jsonResult!=null){
                PublicUseBean bean;
                bean = JsonParseUtils.createBean(PublicUseBean.class, jsonResult);
                if (bean !=null) {
                    bundle.putSerializable(BUNDLE_KEY_RESULT_TRANS, bean);
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
