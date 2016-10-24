package com.thinkive.android.trade_bz.request;

import android.os.Bundle;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_hk.bean.HKStockLinkBean;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.others.constants.Constants;
import com.thinkive.android.trade_bz.utils.JsonParseUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * 港股通 股票联动
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/11/18
 */
public class HK301602 extends BaseNormalRequest {

    public static final String BUNDLE_KEY_HK_LINKAGE = "Request301602_key";

    public HK301602(HashMap<String, String> paramMap, IRequestAction action) {
        super(action);
        paramMap.put("funcNo", "301602");
        setParamHashMap(paramMap);
        setUrlName(Constants.URL_TRADE);
    }

    @Override
    void getJsonDataWithoutError(JSONObject jsonObject) {
        Bundle bundle = new Bundle();
        try {
            String resultTarget = jsonObject.getJSONArray("dsName").get(0).toString();
            JSONObject resultJsonObject = jsonObject.getJSONArray(resultTarget).getJSONObject(0);
            HKStockLinkBean bean;
            if(resultJsonObject != null){
                bean = JsonParseUtils.createBean(HKStockLinkBean.class, resultJsonObject);
                bundle.putSerializable(BUNDLE_KEY_HK_LINKAGE, bean);
                transferAction(REQUEST_SUCCESS, bundle);
            }else{
                bundle.putString(ERROR_INFO, mContext.getResources().getString(R.string.hk_order25));
                transferAction(REQUEST_FAILED, bundle);
            }
        } catch (JSONException je) {
            je.printStackTrace();
        }
    }
}
