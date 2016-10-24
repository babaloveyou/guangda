package com.thinkive.android.trade_bz.request;

import android.os.Bundle;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_rr.bean.RSelectPropertBean;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.others.constants.Constants;
import com.thinkive.android.trade_bz.utils.JsonParseUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * 融资融券--查询--资产负债  或  (融资融券--资产)
 *  303026 融资融券资产负债综合查询
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/8/17
 */
public class RR303026 extends BaseCreditRequest {

    public static final String BUNDLE_KEY_SELECT_PROPERT= "Request303026_result";

    public RR303026(HashMap<String, String> paramMap, IRequestAction action) {
        super(action);
        paramMap.put("funcNo", "303026");
        setParamHashMap(paramMap);
        setUrlName(Constants.URL_CREDIT_TRADE);
    }

    @Override
    void getJsonDataWithoutError(JSONObject jsonObject) {
        Bundle bundle = new Bundle();
        try {
            String resultTarget = jsonObject.getJSONArray("dsName").get(0).toString();
            JSONObject jsonResult = jsonObject.getJSONArray(resultTarget).getJSONObject(0);
            RSelectPropertBean bean;
            bean = JsonParseUtils.createBean(RSelectPropertBean.class, jsonResult);
            if (jsonResult != null && bean !=null) {
                bundle.putSerializable(BUNDLE_KEY_SELECT_PROPERT, bean);
                transferAction(REQUEST_SUCCESS, bundle);
            }else {
                bundle.putString(BUNDLE_KEY_SELECT_PROPERT, mContext.getResources().getString(R.string.data_error));
                transferAction(REQUEST_SUCCESS, bundle);
            }
        } catch (JSONException je) {
            je.printStackTrace();
        }
    }
}
