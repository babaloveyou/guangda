package com.thinkive.android.trade_bz.request;


import android.os.Bundle;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_hk.bean.HKCapitalExchangeBean;
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
 * 创建时间   2016/6/2 10:15
 * 描述	    沪港通参考汇率信息查询
 * 更新者     $Author$
 * 更新时间   $Date$
 */
public class HK301635 extends BaseNormalRequest {
    public static final String BUNDLE_KEY_HK_HISTORY_TRADE = "bundle_key_Request301635";


    public HK301635(HashMap<String, String> params, IRequestAction action) {
        super(action);
        params.put("funcNo", "301635");
        setParamHashMap(params);
        setUrlName(Constants.URL_TRADE);

    }

    @Override
    void getJsonDataWithoutError(JSONObject jsonObject) {
        try {
            Bundle bundle = new Bundle();
            String resultTarget = jsonObject.getJSONArray("dsName").get(0).toString();
            JSONArray jsonArray = jsonObject.getJSONArray(resultTarget);
            if (jsonArray != null) {
                ArrayList<HKCapitalExchangeBean> dataList = JsonParseUtils.createBeanList(HKCapitalExchangeBean.class, jsonArray);
                bundle.putParcelableArrayList(BUNDLE_KEY_HK_HISTORY_TRADE, dataList);
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
