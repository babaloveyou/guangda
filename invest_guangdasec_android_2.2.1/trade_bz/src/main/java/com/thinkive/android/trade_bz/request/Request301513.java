package com.thinkive.android.trade_bz.request;

import android.os.Bundle;
import android.text.TextUtils;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.others.constants.Constants;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Administrator on 2016/11/24.
 */
public class Request301513 extends BaseNormalRequest {

    public static final String BUNDLE_KEY_Request301513 = "Request301513";

    public Request301513(HashMap<String, String> paramMap, IRequestAction action) {
        super(action);
        paramMap.put("funcNo", "301513");
        setParamHashMap(paramMap);
        System.out.println("301513================" + paramMap.toString());
        setUrlName(Constants.URL_TRADE);
    }

    @Override
    void getJsonDataWithoutError(JSONObject jsonObject) {
        System.out.println("301513Object=====" + jsonObject.toString());
        try {
            String resultTarget = jsonObject.getJSONArray("dsName").get(0).toString();
            JSONObject josnObj = (JSONObject) jsonObject.getJSONArray(resultTarget).get(0);
            ArrayList<String> hintList = new ArrayList<>();
            if (josnObj != null) {
                String type = josnObj.optString("type");
                if (!TextUtils.isEmpty(type)) {
                    if ("1".equals(type)) {
                        hintList.add(josnObj.optString("TSMsg"));
                    } else if ("2".equals(type)) {
                        hintList.add(josnObj.optString("JSMsg"));
                    } else if ("3".equals(type)) {
                        hintList.add(josnObj.optString("QXMsg"));
                        hintList.add("");
                        hintList.add("");
                    } else if ("12".equals(type)) {
                        hintList.add(josnObj.optString("TSMsg"));
                        hintList.add(josnObj.optString("JSMsg"));
                    } else if ("13".equals(type)) {
                        hintList.add(josnObj.optString("TSMsg"));
                        hintList.add(josnObj.optString("QXMsg"));
                        hintList.add("");
                    }
                }
                Bundle bundle = new Bundle();
                bundle.putStringArrayList(BUNDLE_KEY_Request301513, hintList);
                transferAction(REQUEST_SUCCESS, bundle);
            }
        } catch (JSONException je) {
            Bundle bundle = new Bundle();
            bundle.putString(BUNDLE_KEY_Request301513, mContext.getResources().getString(R.string.data_error));
            transferAction(REQUEST_SUCCESS, bundle);
        }
    }
}

