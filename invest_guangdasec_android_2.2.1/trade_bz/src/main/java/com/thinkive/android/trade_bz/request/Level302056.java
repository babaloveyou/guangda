package com.thinkive.android.trade_bz.request;

import android.os.Bundle;

import com.thinkive.android.trade_bz.a_level.bean.LFundDivideOrMergerBean;
import com.thinkive.android.trade_bz.others.constants.Constants;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.utils.JsonParseUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * 查询分级基金 -- 最大可分拆或合并的数量
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/10/27
 */
public class Level302056 extends BaseNormalRequest {

    public static final String BUNDLE_KEY_OTHER_MONEY = "Request302056_result";

    public Level302056(HashMap<String, String> paramMap, IRequestAction action) {
        super(action);
        paramMap.put("funcNo", "302056");
        setParamHashMap(paramMap);
        setUrlName(Constants.URL_TRADE);
    }

    @Override
    void getJsonDataWithoutError(JSONObject jsonObject) {
        Bundle bundle = new Bundle();
        try {
            String resultTarget = jsonObject.getJSONArray("dsName").get(0).toString();
            JSONObject jsonResult = jsonObject.getJSONArray(resultTarget).getJSONObject(0);
            LFundDivideOrMergerBean bean;
                bean = JsonParseUtils.createBean(LFundDivideOrMergerBean.class, jsonResult);
                if (jsonResult != null && bean !=null) {
                    bundle.putSerializable(BUNDLE_KEY_OTHER_MONEY, bean);
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
