package com.thinkive.android.trade_bz.request;

import android.os.Bundle;

import com.thinkive.android.trade_bz.a_level.bean.LFundRevocationBean;
import com.thinkive.android.trade_bz.others.constants.Constants;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.utils.JsonParseUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 分级基金 可撤单查询
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/11/17
 */
public class Level302063 extends BaseNormalRequest {

    public static final String BUNDLE_KEY_LEVEL_REVOCATION= "Request302063_result";

    public Level302063(HashMap<String, String> paramMap, IRequestAction action) {
        super(action);
        paramMap.put("funcNo", "302063");
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
                ArrayList<LFundRevocationBean> dataList = JsonParseUtils.createBeanList(LFundRevocationBean.class, jsonArray);
                bundle.putParcelableArrayList(BUNDLE_KEY_LEVEL_REVOCATION, dataList);
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
