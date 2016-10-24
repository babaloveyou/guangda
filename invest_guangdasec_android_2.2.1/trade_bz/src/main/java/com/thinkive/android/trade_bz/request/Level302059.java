package com.thinkive.android.trade_bz.request;

import android.os.Bundle;

import com.thinkive.android.trade_bz.a_level.bean.LFundEntrustDataBean;
import com.thinkive.android.trade_bz.others.constants.Constants;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.utils.JsonParseUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 分级基金  当日委托
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/11/13
 */
public class Level302059 extends BaseNormalRequest {

    public static final String BUNDLE_KEY_LEVEL_TENTRUST = "Request302059_result";

    public Level302059(HashMap<String, String> paramMap, IRequestAction action) {
        super(action);
        paramMap.put("funcNo", "302059");
        setParamHashMap(paramMap);
        setUrlName(Constants.URL_TRADE);
    }

    @Override
    void getJsonDataWithoutError(JSONObject jsonObject) {
        Bundle bundle = new Bundle();
        try {
            String resultTarget = jsonObject.getJSONArray("dsName").get(0).toString();
            JSONArray jsonArray = jsonObject.getJSONArray(resultTarget);
            if (jsonArray != null) {
                ArrayList<LFundEntrustDataBean> dataList = JsonParseUtils.createBeanList(LFundEntrustDataBean.class, jsonArray);
                bundle.putParcelableArrayList(BUNDLE_KEY_LEVEL_TENTRUST, dataList);
                transferAction(REQUEST_SUCCESS, bundle);
            }else {
                bundle.putString(ERROR_INFO, jsonObject.getString(ERROR_INFO));
                transferAction(REQUEST_FAILED, bundle);
            }
        } catch (JSONException je) {
            je.printStackTrace();
        }
    }
}
