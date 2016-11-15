package com.thinkive.android.trade_bz.request;

import android.os.Bundle;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.bean.RCollaterInBean;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.others.constants.Constants;
import com.thinkive.android.trade_bz.utils.JsonParseUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Administrator on 2016/11/15.
 */
public class RR303057 extends BaseCreditRequest{
    public static final String BUNDLE_KEY_RESULT = "RR303057";

    public RR303057(HashMap<String, String> paramMap, IRequestAction action) {
        super(action);
        paramMap.put("funcNo", "303057");
        setParamHashMap(paramMap);
        setUrlName(Constants.URL_CREDIT_TRADE);
    }

    @Override
    void getJsonDataWithoutError(JSONObject jsonObject) {
        try {
            String resultTarget = jsonObject.getJSONArray("dsName").get(0).toString();
            JSONArray jsonArray = jsonObject.getJSONArray(resultTarget);
            if (jsonArray != null) {
                ArrayList<RCollaterInBean> dataList = JsonParseUtils.createBeanList(RCollaterInBean.class, jsonArray);
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList(BUNDLE_KEY_RESULT, dataList);
                transferAction(REQUEST_SUCCESS, bundle);
            }else {
                Bundle bundle = new Bundle();
                bundle.putString(BUNDLE_KEY_RESULT,mContext.getResources().getString(R.string.data_error));
                transferAction(REQUEST_SUCCESS, bundle);
            }
        } catch (JSONException je) {
            je.printStackTrace();
        }
    }
}
