package com.thinkive.android.trade_bz.request;


import android.os.Bundle;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_hk.bean.HKCapitalTrustBean;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.others.constants.Constants;
import com.thinkive.android.trade_bz.utils.JsonParseUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 创建者     舒旺
 * 创建时间   2016/6/6 13:25
 * 描述	      港股通投票申报结果查询
 * 更新者     $Author$
 * 更新时间   $Date$
 */
public class HK301622 extends BaseNormalRequest {

    public static final String BUNDLE_KEY_LIMIT = "HK301622";

    public HK301622(HashMap<String, String> paramMap, IRequestAction action) {
        super(action);
        paramMap.put("funcNo", "301622");
        setParamHashMap(paramMap);
        setUrlName(Constants.URL_TRADE);
    }


    @Override
    void getJsonDataWithoutError(JSONObject jsonObject) {
        try {
            Bundle bundle = new Bundle();
            String resultTarget = jsonObject.getJSONArray("dsName").get(0).toString();
            JSONArray jsonArray = jsonObject.getJSONArray(resultTarget);
            if (jsonArray != null) {
                ArrayList<HKCapitalTrustBean> dataList = JsonParseUtils.createBeanList(HKCapitalTrustBean.class, jsonArray);
                bundle.putParcelableArrayList(BUNDLE_KEY_LIMIT, dataList);
                transferAction(REQUEST_SUCCESS, bundle);
            } else {
                bundle.putString(ERROR_INFO, mContext.getResources().getString(R.string.hk_order25));
                transferAction(REQUEST_FAILED, bundle);
            }
        } catch (JSONException je) {
            je.printStackTrace();
        }
    }
}
