package com.thinkive.android.trade_bz.request;

import android.os.Bundle;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_out.bean.FundRiskTestResultBean;
import com.thinkive.android.trade_bz.others.constants.Constants;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.utils.JsonParseUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * 基金查询-风险测评-风险测评问卷提交
 * @author 王志鸿
 * @company Thinkive
 * @date 2015/8/7
 */
public class Fund300106 extends BaseNormalRequest {

    public static final String BUNDLE_KEY_300106 = "bundle_key_300106";

    public Fund300106(HashMap<String, String> paramMap, IRequestAction action) {
        super(action);
        paramMap.put("funcNo", "300106");
        setParamHashMap(paramMap);
        setUrlName(Constants.URL_TRADE);
    }

    @Override
    void getJsonDataWithoutError(JSONObject jsonObject) {
        Bundle bundle = new Bundle();
        try {
            // 获取存放结果集的json key
            String resultTarget = jsonObject.getJSONArray("dsName").get(0).toString();
            // 获取结果集
            JSONObject jsonResult = jsonObject.getJSONArray(resultTarget).getJSONObject(0);
            FundRiskTestResultBean bean = JsonParseUtils.createBean(FundRiskTestResultBean.class, jsonResult);
            bundle.putString(ERROR_INFO, jsonObject.getString(ERROR_INFO));
            bundle.putSerializable(BUNDLE_KEY_300106, bean);
            transferAction(REQUEST_SUCCESS, bundle);
        } catch (JSONException je) {
            je.printStackTrace();
            bundle.putString(ERROR_INFO,
                    mContext.getString(R.string.fund_trade_risk_test_submit_error));
            transferAction(REQUEST_FAILED, bundle);
        }
    }
}
