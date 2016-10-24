package com.thinkive.android.trade_bz.request;

import android.os.Bundle;

import com.thinkive.android.trade_bz.a_out.bean.FundPledgeSelectBean;
import com.thinkive.android.trade_bz.others.constants.Constants;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.utils.JsonParseUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *  场外基金定投查询
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/10/27
 */
public class Fund302051 extends BaseNormalRequest {

    public static final String BUNDLE_KEY_PLEDGE_SELECT = "Request302051_result";

    public Fund302051(HashMap<String, String> paramMap, IRequestAction action) {
        super(action);
        paramMap.put("funcNo", "302051");
        setParamHashMap(paramMap);
        setUrlName(Constants.URL_TRADE);
    }

    @Override
    void getJsonDataWithoutError(JSONObject jsonObject) {
        Bundle bundle=new Bundle();
        try {
            String resultTarget = jsonObject.getJSONArray("dsName").get(0).toString();
            JSONArray jArray=jsonObject.getJSONArray(resultTarget);
            if (jArray != null) {
                ArrayList<FundPledgeSelectBean> dataList = JsonParseUtils.createBeanList(FundPledgeSelectBean.class, jArray);
                bundle.putParcelableArrayList(BUNDLE_KEY_PLEDGE_SELECT, dataList);
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
