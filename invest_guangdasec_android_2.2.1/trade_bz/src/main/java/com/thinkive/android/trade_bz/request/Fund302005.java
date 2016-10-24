package com.thinkive.android.trade_bz.request;

import android.os.Bundle;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_out.bean.FundInfoBean;
import com.thinkive.android.trade_bz.others.constants.Constants;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.utils.JsonParseUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 *  基金交易--查询基金详情
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/7/29
 */
public class Fund302005 extends BaseNormalRequest {

    public static final String BUNDLE_KEY_FUND = "Request302005_result";

    public Fund302005(HashMap<String, String> paramMap, IRequestAction action) {
        super(action);
        paramMap.put("funcNo", "302005");
        setParamHashMap(paramMap);
        setUrlName(Constants.URL_TRADE);
    }

    @Override
    void getJsonDataWithoutError(JSONObject jsonObject) {
        Bundle bundle = new Bundle();
        try {
            JSONArray jArray=jsonObject.getJSONArray("results");
            if (jArray == null || jArray.length()==0) {
                bundle.putString(ERROR_INFO, mContext.getResources().getString(R.string.history_trade_fund));
                transferAction(REQUEST_FAILED, bundle);
            }else {
                FundInfoBean bean;
                jsonObject = jArray.getJSONObject(0);
                bean = JsonParseUtils.createBean(FundInfoBean.class, jsonObject);
                if (jsonObject != null) {
                    bundle.putSerializable(BUNDLE_KEY_FUND, bean);
                    transferAction(REQUEST_SUCCESS, bundle);
                }else {
                    bundle.putString(BUNDLE_KEY_FUND,mContext.getResources().getString(R.string.data_error));
                    transferAction(REQUEST_SUCCESS, bundle);
                }
            }
        } catch (JSONException je) {
            je.printStackTrace();
        }
    }
}
