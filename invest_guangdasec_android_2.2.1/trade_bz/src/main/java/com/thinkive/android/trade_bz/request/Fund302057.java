package com.thinkive.android.trade_bz.request;

import android.os.Bundle;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_out.bean.FundReturnMoneyDateBean;
import com.thinkive.android.trade_bz.others.constants.Constants;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.utils.JsonParseUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *  基金还款日期
 * @author 张雪梅
 * @company Thinkive
 * @date 2015//11/5
 */
public class Fund302057 extends BaseNormalRequest {

    public static final String BUNDLE_KEY_RETURN_DATE= "Request302057_result";

    public Fund302057(HashMap<String, String> paramMap, IRequestAction action) {
        super(action);
        paramMap.put("funcNo", "302057");
        setParamHashMap(paramMap);
        setUrlName(Constants.URL_TRADE);
    }

    @Override
    void getJsonDataWithoutError(JSONObject jsonObject) {
        try {
            String resultTarget = jsonObject.getJSONArray("dsName").get(0).toString();
            JSONArray jArray=jsonObject.getJSONArray(resultTarget);
            Bundle bundle=new Bundle();
            if (jArray != null) {
                ArrayList<FundReturnMoneyDateBean> dataList = JsonParseUtils.createBeanList(FundReturnMoneyDateBean.class, jArray);
                bundle.putParcelableArrayList(BUNDLE_KEY_RETURN_DATE, dataList);
                transferAction(REQUEST_SUCCESS, bundle);
            }else {
                bundle.putString(ERROR_INFO, mContext.getResources().getString(R.string.toast_data_from_service_error_nodata));
                transferAction(REQUEST_FAILED, bundle);
            }
        } catch (JSONException je) {
            je.printStackTrace();
        }
    }
}
