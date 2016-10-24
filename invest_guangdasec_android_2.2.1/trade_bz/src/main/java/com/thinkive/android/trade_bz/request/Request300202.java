package com.thinkive.android.trade_bz.request;

import android.os.Bundle;

import com.thinkive.android.trade_bz.a_stock.bean.BankTransferBean;
import com.thinkive.android.trade_bz.others.constants.Constants;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.utils.JsonParseUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 *  银证转账（转账查询结果）
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/8/10
 */
public class Request300202 extends BaseNormalRequest {

    public static final String BUNDLE_KEY_TRANSFER = "Request300202_result";

    public Request300202(HashMap<String, String> paramMap, IRequestAction action) {
        super(action);
        paramMap.put("funcNo", "300202");
        setParamHashMap(paramMap);
        setUrlName(Constants.URL_TRADE);
    }

    @Override
    void getJsonDataWithoutError(JSONObject jsonObject) {
        Bundle bundle = new Bundle();
        try {
            String resultTarget = jsonObject.getJSONArray("dsName").get(0).toString();
            JSONArray jArray=jsonObject.getJSONArray(resultTarget);
            if (jArray.length()>0) {
                jsonObject = jArray.getJSONObject(0);
                BankTransferBean bean;
                bean = JsonParseUtils.createBean(BankTransferBean.class, jsonObject);
                if (jsonObject != null) {
                    bundle.putSerializable(BUNDLE_KEY_TRANSFER, bean);
                    transferAction(REQUEST_SUCCESS, bundle);
                }
            } else {
                bundle.putString(ERROR_INFO, jsonObject.getString(ERROR_INFO));
                transferAction(REQUEST_FAILED, bundle);
            }
        } catch (JSONException je) {
            je.printStackTrace();
        }
    }
}
