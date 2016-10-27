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
 * Created by Administrator on 2016/10/26.
 */

public class Request306000 extends BaseCreditRequest {
    public static final String BUNDLE_KEY_306000 = "request306000_result";
    /**
     * @param action
     */
    public Request306000(HashMap<String, String> paramMap, IRequestAction action) {
        super(action);
        paramMap.put("funcNo", "306000");
        setParamHashMap(paramMap);
        //// TODO: 2016/10/20 服务端还未处理手机端
        paramMap.put("entrust_way", "SJWT");
        setUrlName(Constants.URL_TRADE);
    }

    @Override
    void getJsonDataWithoutError(JSONObject jsonObject) {
        try {
            String resultTarget = jsonObject.getJSONArray("dsName").get(0).toString();
            JSONArray jsonArray = jsonObject.getJSONArray(resultTarget);
            JSONObject jObj = (JSONObject) jsonArray.get(0);
            String date = (String) jObj.opt("date");
            if (date != null) {
                Bundle bundle = new Bundle();
                bundle.putString(BUNDLE_KEY_306000, date);
                transferAction(REQUEST_SUCCESS, bundle);
            } else {
                Bundle bundle = new Bundle();
                bundle.putString(BUNDLE_KEY_306000, mContext.getResources().getString(R.string.data_error));
                transferAction(REQUEST_SUCCESS, bundle);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
