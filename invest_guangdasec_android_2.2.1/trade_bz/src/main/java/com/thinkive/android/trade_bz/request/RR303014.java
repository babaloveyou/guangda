package com.thinkive.android.trade_bz.request;

import android.os.Bundle;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_rr.bean.RCollaterLinkBean;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.others.constants.Constants;
import com.thinkive.android.trade_bz.utils.JsonParseUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 *  融资融券交易--担保品划转联动
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/8/14
 */
public class RR303014 extends BaseCreditRequest {

    public static final String BUNDLE_KEY_TRANSFER_LIAN = "Request303014_result";

    public RR303014(HashMap<String, String> paramMap, IRequestAction action) {
        super(action);
        paramMap.put("funcNo", "303014");
        setParamHashMap(paramMap);
        setUrlName(Constants.URL_CREDIT_TRADE);
    }

    @Override
    void getJsonDataWithoutError(JSONObject jsonObject) {
        Bundle bundle = new Bundle();
        try {
            String resultTarget = jsonObject.getJSONArray("dsName").get(0).toString();
            JSONObject jsonResult = jsonObject.getJSONArray(resultTarget).getJSONObject(0);
            if (jsonResult != null) {
                RCollaterLinkBean bean;
                bean = JsonParseUtils.createBean(RCollaterLinkBean.class, jsonResult);
                bundle.putSerializable(BUNDLE_KEY_TRANSFER_LIAN, bean);
                transferAction(REQUEST_SUCCESS, bundle);
            }else {
                bundle.putString(BUNDLE_KEY_TRANSFER_LIAN,mContext.getResources().getString(R.string.data_error));
                transferAction(REQUEST_SUCCESS, bundle);
            }
        } catch (JSONException je) {
            je.printStackTrace();
        }
    }
}
