package com.thinkive.android.trade_bz.request;

import android.os.Bundle;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_option.bean.OptionRevocationResultBean;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.others.constants.Constants;
import com.thinkive.android.trade_bz.utils.JsonParseUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 *  个股期权撤单结果
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/7/29
 */
public class Option305006 extends BaseOptionRequest {

    public static final String BUNDLE_KEY_REVOCATION_RESULT = "Option305006";

    public Option305006(HashMap<String, String> paramMap, IRequestAction action) {
        super(action);
        paramMap.put("funcNo", "305006");
        setParamHashMap(paramMap);
        setUrlName(Constants.URL_TRADE);
    }

    @Override
    void getJsonDataWithoutError(JSONObject jsonObject) {
        Bundle bundle = new Bundle();
        try {
            JSONArray jArray=jsonObject.getJSONArray("results");
            if (jArray != null && jArray.length() > 0) {
                OptionRevocationResultBean bean;
                jsonObject = jArray.getJSONObject(0);
                bean = JsonParseUtils.createBean(OptionRevocationResultBean.class, jsonObject);
                if (jsonObject != null) {
                    bundle.putSerializable(BUNDLE_KEY_REVOCATION_RESULT, bean);
                    transferAction(REQUEST_SUCCESS, bundle);
                }else {
                    bundle.putString(BUNDLE_KEY_REVOCATION_RESULT,mContext.getResources().getString(R.string.data_error));
                    transferAction(REQUEST_SUCCESS, bundle);
                }
            }
        } catch (JSONException je) {
            je.printStackTrace();
        }
    }
}
