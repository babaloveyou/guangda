package com.thinkive.android.trade_bz.request;

import android.os.Bundle;

import com.thinkive.android.trade_bz.a_rr.bean.RStockLinkBean;
import com.thinkive.android.trade_bz.others.constants.Constants;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.utils.JsonParseUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * 获取股票联动数据的请求(信用账户)
 * @author 王志鸿
 * @company Thinkive
 * @date 2015/12/1
 */
public class Request303000 extends BaseCreditRequest {

    public static final String BUNDLE_KEY_LINKAGE = "Request303000_key";

    public Request303000(HashMap<String, String> paramMap, IRequestAction action) {
        super(action);
        paramMap.put("funcNo", "303000");
        setParamHashMap(paramMap);
        setUrlName(Constants.URL_CREDIT_TRADE);
    }

    @Override
    void getJsonDataWithoutError(JSONObject jsonObject) {
        try {
            String resultTarget = jsonObject.getJSONArray("dsName").get(0).toString();
            JSONObject resultJsonObject = jsonObject.getJSONArray(resultTarget).getJSONObject(0);
            RStockLinkBean bean = JsonParseUtils.createBean(RStockLinkBean.class, resultJsonObject);
            Bundle bundle = new Bundle();
            bundle.putSerializable(BUNDLE_KEY_LINKAGE, bean);
            transferAction(REQUEST_SUCCESS, bundle);
        } catch (JSONException je) {
            je.printStackTrace();
        }
    }
}
