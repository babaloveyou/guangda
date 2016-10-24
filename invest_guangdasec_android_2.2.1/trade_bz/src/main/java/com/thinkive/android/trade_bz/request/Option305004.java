package com.thinkive.android.trade_bz.request;

import android.os.Bundle;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_option.bean.OptionMoneyBean;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.others.constants.Constants;
import com.thinkive.android.trade_bz.utils.JsonParseUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * 个股期权资产查询
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/7/15
 */
public class Option305004 extends BaseOptionRequest {

    public static final String BUNDLE_KEY_MYHOLD_HEAD = "Option305004";

    public Option305004(HashMap<String, String> paramMap, IRequestAction action) {
        super(action);
        paramMap.put("funcNo", "305004");
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
                for (int i = 0; i < jArray.length(); i++) {
                    jsonObject = jArray.getJSONObject(i);
                }
                OptionMoneyBean bean;
                bean = JsonParseUtils.createBean(OptionMoneyBean.class, jsonObject);
                if (bean != null) {
                    bundle.putSerializable(BUNDLE_KEY_MYHOLD_HEAD, bean);
                    transferAction(REQUEST_SUCCESS, bundle);
                }else {
                    bundle.putString(BUNDLE_KEY_MYHOLD_HEAD,mContext.getResources().getString(R.string.data_error));
                    transferAction(REQUEST_SUCCESS, bundle);
                }
            }
        } catch (JSONException je) {
            je.printStackTrace();
        }
    }
}
