package com.thinkive.android.trade_bz.request;

import android.os.Bundle;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_rr.bean.RRevocationBean;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.others.constants.Constants;
import com.thinkive.android.trade_bz.utils.JsonParseUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Administrator on 2016/10/26.
 */
public class Request303017 extends BaseCreditRequest{
    public static final String BUNDLE_KEY_REVOCATION = "Request303017_result";

    public Request303017(HashMap<String, String> paramMap, IRequestAction action) {
        super(action);
        paramMap.put("funcNo", "303017");
        setParamHashMap(paramMap);
        paramMap.put("entrust_way", "SJWT");

        setUrlName(Constants.URL_TRADE);
    }

    @Override
    void getJsonDataWithoutError(JSONObject jsonObject) {
        try {
            String resultTarget = jsonObject.getJSONArray("dsName").get(0).toString();
            JSONArray jsonArray = jsonObject.getJSONArray(resultTarget);
            if (jsonArray != null) {
                ArrayList<RRevocationBean> dataList = JsonParseUtils.createBeanList(RRevocationBean.class, jsonArray);
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList(BUNDLE_KEY_REVOCATION, dataList);
                transferAction(REQUEST_SUCCESS, bundle);
            }else {
                Bundle bundle = new Bundle();
                bundle.putString(BUNDLE_KEY_REVOCATION,mContext.getResources().getString(R.string.data_error));
                transferAction(REQUEST_SUCCESS, bundle);
            }
        } catch (JSONException je) {
            je.printStackTrace();
        }
    }
}
