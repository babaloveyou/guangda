package com.thinkive.android.trade_bz.request;

import android.os.Bundle;
import android.util.Log;

import com.thinkive.android.trade_bz.a_stock.bean.StockLinkageBean;
import com.thinkive.android.trade_bz.others.constants.Constants;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.utils.JsonParseUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * 获取股票联动数据的请求
 * @author 王志鸿
 * @company Thinkive
 * @date 2015/7/7
 */
public class Request301514 extends BaseNormalRequest {

    public static final String BUNDLE_KEY_LINKAGE = "Request301514_key";

    public Request301514(HashMap<String, String> paramMap, IRequestAction action) {
        super(action);
        paramMap.put("funcNo", "301514");
        setParamHashMap(paramMap);
        setUrlName(Constants.URL_TRADE);
    }

    @Override
    void getJsonDataWithoutError(JSONObject jsonObject) {
        Bundle bundle = new Bundle();
        try {
            String resultTarget = jsonObject.getJSONArray("dsName").get(0).toString();
            JSONObject resultJsonObject = jsonObject.getJSONArray(resultTarget).getJSONObject(0);
            Log.d("TAG",resultJsonObject.toString());
            StockLinkageBean bean;
            if(resultJsonObject!=null){
                bean = JsonParseUtils.createBean(StockLinkageBean.class, resultJsonObject);
                bundle.putSerializable(BUNDLE_KEY_LINKAGE, bean);
                transferAction(REQUEST_SUCCESS, bundle);
            }
        } catch (JSONException je) {
            je.printStackTrace();
        }
    }
}
