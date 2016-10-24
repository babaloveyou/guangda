package com.thinkive.android.trade_bz.request;

import android.os.Bundle;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.bean.PublicUseBean;
import com.thinkive.android.trade_bz.others.constants.Constants;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.utils.JsonParseUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 *  基金交易--申购--基金申购
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/7/30
 */
public class Fund302007 extends BaseNormalRequest {

    public static final String BUNDLE_KEY_SUBTRICBUTE = "Request302007_result";

    public Fund302007(HashMap<String, String> paramMap, IRequestAction action) {
        super(action);
        paramMap.put("funcNo", "302007");
        setParamHashMap(paramMap);
        setUrlName(Constants.URL_TRADE);
    }
    @Override
    void getJsonDataWithoutError(JSONObject jsonObject) {
        Bundle bundle = new Bundle();
        try {
            String resultTarget = jsonObject.getJSONArray("dsName").get(0).toString();
            JSONObject jsonResult = jsonObject.getJSONArray(resultTarget).getJSONObject(0);
            if (jsonResult != null) {
                PublicUseBean bean;
                bean = JsonParseUtils.createBean(PublicUseBean.class, jsonResult);
                bundle.putSerializable(BUNDLE_KEY_SUBTRICBUTE, bean);
                transferAction(REQUEST_SUCCESS, bundle);
            }else {
                bundle.putString(BUNDLE_KEY_SUBTRICBUTE,mContext.getResources().getString(R.string.data_error));
                transferAction(REQUEST_SUCCESS, bundle);
            }
        } catch (JSONException je) {
            je.printStackTrace();
        }
    }
}
