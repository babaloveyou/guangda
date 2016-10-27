package com.thinkive.android.trade_bz.request;

import android.os.Bundle;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.others.constants.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by 柳广剑 on 2016/10/20.
 */

public class Request306001 extends BaseNormalRequest {
    public static final String BUNDLE_KEY_306001 = "request306001_result";

    public Request306001(HashMap<String, String> paramMap, IRequestAction action) {
        super(action);
        paramMap.put("funcNo", "306001");
        setParamHashMap(paramMap);
        paramMap.put("entrust_way", "SJWT");
        setUrlName(Constants.URL_TRADE);
    }

    @Override
    void getJsonDataWithoutError(JSONObject jsonObject) {
        //jsonObj={"error_no":"0","results":[{"copy_date":"20160902","sys_type":"1","type_name":"当日交易","date":"2016-10-20","err_state":"Y"}],"dsName":["results"],"error_info":""}
        try {
            String resultTarget = jsonObject.getJSONArray("dsName").get(0).toString();
            JSONArray jsonArray = jsonObject.getJSONArray(resultTarget);
            JSONObject jObj = (JSONObject) jsonArray.get(0);
            String date = (String) jObj.opt("date");
            if (date != null) {
                Bundle bundle = new Bundle();
                bundle.putString(BUNDLE_KEY_306001, date);
                transferAction(REQUEST_SUCCESS, bundle);
            } else {
                Bundle bundle = new Bundle();
                bundle.putString(BUNDLE_KEY_306001, mContext.getResources().getString(R.string.data_error));
                transferAction(REQUEST_SUCCESS, bundle);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
