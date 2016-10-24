package com.thinkive.android.trade_bz.request;

import android.os.Bundle;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_out.bean.FundCompanyBean;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.others.constants.Constants;
import com.thinkive.android.trade_bz.utils.JsonParseUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 场外基金公司查询
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/5/9
 */
public class Fund302004 extends BaseNormalRequest {

    public static final String BUNDLE_KEY_FUND_COMPANY = "Fund302004";

    public Fund302004(HashMap<String, String> paramMap, IRequestAction action) {
        super(action);
        paramMap.put("funcNo", "302004");
        setParamHashMap(paramMap);
        setUrlName(Constants.URL_TRADE);
    }
    @Override
    void getJsonDataWithoutError(JSONObject jsonObject) {
        Bundle bundle = new Bundle();
        try {
            String resultTarget = jsonObject.getJSONArray("dsName").get(0).toString();
            JSONArray jsonArray = jsonObject.getJSONArray(resultTarget);
            ArrayList<FundCompanyBean> list;
            if (jsonArray != null && jsonArray.length() > 0) {
                list = JsonParseUtils.createBeanList(FundCompanyBean.class, jsonArray);
                bundle.putParcelableArrayList(BUNDLE_KEY_FUND_COMPANY, list);
                transferAction(REQUEST_SUCCESS, bundle);
            }else {
                bundle.putString(BUNDLE_KEY_FUND_COMPANY,mContext.getResources().getString(R.string.data_error));
                transferAction(REQUEST_SUCCESS, bundle);
            }
        } catch (JSONException je) {
            je.printStackTrace();
        }
    }
}
