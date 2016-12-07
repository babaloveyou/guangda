package com.thinkive.android.trade_bz.request;

import android.os.Bundle;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.bean.OneKeyBean;
import com.thinkive.android.trade_bz.others.constants.Constants;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.utils.JsonParseUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * 一键归集--资金转账
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/8/12
 */
public class Request300207 extends BaseNormalRequest {

    public static final String BUNDLE_KEY_ONE_KEY_MONEY= "Request300207_result";

    public Request300207(HashMap<String, String> paramMap, IRequestAction action) {
        super(action);
        paramMap.put("funcNo", "300207");
        setParamHashMap(paramMap);
        setUrlName(Constants.URL_TRADE);
    }

    @Override
    void getJsonDataWithoutError(JSONObject jsonObject) {
        Bundle bundle = new Bundle();
        try {
            String resultTarget = jsonObject.getJSONArray("dsName").get(0).toString();
            JSONObject jsonResult = jsonObject.getJSONArray(resultTarget).getJSONObject(0);
            OneKeyBean bean;
                bean = JsonParseUtils.createBean(OneKeyBean.class,jsonResult);
                if (jsonResult != null && bean !=null) {
                    bundle.putSerializable(BUNDLE_KEY_ONE_KEY_MONEY, bean);
                    transferAction(REQUEST_SUCCESS, bundle);
                }else {
                    bundle.putString(BUNDLE_KEY_ONE_KEY_MONEY, mContext.getResources().getString(R.string.data_error));
                    transferAction(REQUEST_SUCCESS, bundle);
                }
        } catch (JSONException je) {
            je.printStackTrace();
        }
    }

}
