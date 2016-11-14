package com.thinkive.android.trade_bz.request;

import android.os.Bundle;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.bean.MoneyBean;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.others.constants.Constants;
import com.thinkive.android.trade_bz.utils.JsonParseUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by Administrator on 2016/11/10.
 */
public class CRequest301504 extends BaseNormalRequest{
    private String page = null;
    public static final String BUNDLE_KEY_MYHOLD_HEAD = "CRequest301504_result";
    private Runnable mRunnable;
    public CRequest301504(HashMap<String, String> paramMap, IRequestAction action) {
        super(action);
        paramMap.put("funcNo", "301504");
        page = paramMap.get("page");
        paramMap.remove("page");
        setParamHashMap(paramMap);
        setUrlName(Constants.URL_TRADE);

    }

    @Override
    void getJsonDataWithoutError(JSONObject jsonObject) {
        Bundle bundle = new Bundle();
        try {
            String resultTarget = jsonObject.getJSONArray("dsName").get(0).toString();
            JSONArray jArray = jsonObject.getJSONArray(resultTarget);
            if (jArray != null) {
                jsonObject = jArray.getJSONObject(Integer.parseInt(page));
                MoneyBean bean;
                bean = JsonParseUtils.createBean(MoneyBean.class, jsonObject);
                if (bean != null) {
                    bundle.putSerializable(BUNDLE_KEY_MYHOLD_HEAD, bean);
                    transferAction(REQUEST_SUCCESS, bundle);
                } else {
                    bundle.putString(BUNDLE_KEY_MYHOLD_HEAD, mContext.getResources().getString(R.string.data_error));
                    transferAction(REQUEST_SUCCESS, bundle);
                }
            }
        } catch (JSONException je) {
            je.printStackTrace();
        }
    }
}
