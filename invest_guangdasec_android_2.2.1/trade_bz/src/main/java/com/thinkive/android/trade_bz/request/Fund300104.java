package com.thinkive.android.trade_bz.request;

import android.os.Bundle;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.others.constants.Constants;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.a_out.bean.FundRiskTestResultBean;
import com.thinkive.android.trade_bz.utils.JsonParseUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * 基金查询-风险测评-风险测评结果查询
 * @author 王志鸿
 * @company Thinkive
 * @date 2015/8/7
 */
public class Fund300104 extends BaseNormalRequest {

    public static final String BUNDLE_KEY_300104 = "bundle_key_300104";

    public Fund300104(HashMap<String, String> paramMap, IRequestAction action) {
        super(action);
        paramMap.put("funcNo", "300104");
        setParamHashMap(paramMap);
        setUrlName(Constants.URL_TRADE);
    }

    @Override
    void getJsonDataWithoutError(JSONObject jsonObject) {
        Bundle bundle = new Bundle();
        try {
            String resultTarget = jsonObject.getJSONArray("dsName").get(0).toString();
            JSONArray jsonArray = jsonObject.getJSONArray(resultTarget);
            JSONObject jsonResult = null;
            FundRiskTestResultBean bean =null;
            if(jsonArray != null && jsonArray.length()>0){
                jsonResult = jsonObject.getJSONArray(resultTarget).getJSONObject(0);
                bean = JsonParseUtils.createBean(FundRiskTestResultBean.class, jsonResult);
            }else{
                jsonResult = null;
            }
            if (jsonResult != null && bean !=null) {
                bundle.putSerializable(BUNDLE_KEY_300104, bean);
                transferAction(REQUEST_SUCCESS, bundle);
            }else {
                bundle.putString(BUNDLE_KEY_300104, mContext.getResources().getString(R.string.data_error));
                transferAction(REQUEST_SUCCESS, bundle);
            }
        } catch (JSONException je) {
            je.printStackTrace();
        }
    }
}
