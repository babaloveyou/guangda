package com.thinkive.android.trade_bz.request;

import android.os.Bundle;

import com.thinkive.android.trade_bz.a_trans.bean.TransStockLinkBean;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.others.constants.Constants;
import com.thinkive.android.trade_bz.utils.JsonParseUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * 转股交易的联动接口
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/1/4
 */
public class Trans301700 extends BaseNormalRequest {

    public static final String BUNDLE_KEY_TRANS_LINK = "Trans301700";

    public Trans301700(HashMap<String, String> paramMap, IRequestAction action) {
        super(action);
        paramMap.put("funcNo", "301700");
        setParamHashMap(paramMap);
        setUrlName(Constants.URL_TRADE);
    }

    @Override
    void getJsonDataWithoutError(JSONObject jsonObject) {
        Bundle bundle = new Bundle();
        try {
            String resultTarget = jsonObject.getJSONArray("dsName").get(0).toString();
            JSONObject resultJsonObject = jsonObject.getJSONArray(resultTarget).getJSONObject(0);
            TransStockLinkBean bean;
            if(resultJsonObject!=null){
                bean = JsonParseUtils.createBean(TransStockLinkBean.class, resultJsonObject);
                bundle.putSerializable(BUNDLE_KEY_TRANS_LINK, bean);
                transferAction(REQUEST_SUCCESS, bundle);
            }
        } catch (JSONException je) {
            je.printStackTrace();
        }
    }
}
