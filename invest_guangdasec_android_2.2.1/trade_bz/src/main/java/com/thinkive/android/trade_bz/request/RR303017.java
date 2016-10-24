package com.thinkive.android.trade_bz.request;

import android.os.Bundle;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_rr.bean.RRevocationBean;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.others.constants.Constants;
import com.thinkive.android.trade_bz.utils.JsonParseUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 融资融券--下单--撤单（303017）
 *
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/8/24
 */
public class RR303017 extends BaseCreditRequest {

    public static final String BUNDLE_KEY_R_REVOCATION = "Request303017_result";

    public RR303017(HashMap<String, String> paramMap, IRequestAction action) {
        super(action);
        paramMap.put("funcNo", "303017");
        setParamHashMap(paramMap);
        setUrlName(Constants.URL_CREDIT_TRADE);
    }

    @Override
    void getJsonDataWithoutError(JSONObject jsonObject) {
        try {
            String resultTarget = jsonObject.getJSONArray("dsName").get(0).toString();
            JSONArray jArray = jsonObject.getJSONArray(resultTarget);
            if (jArray != null) {
                ArrayList<RRevocationBean> dataList = JsonParseUtils.createBeanList(RRevocationBean.class, jArray);
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList(BUNDLE_KEY_R_REVOCATION, dataList);
                transferAction(REQUEST_SUCCESS, bundle);
            } else {
                Bundle bundle = new Bundle();
                bundle.putString(BUNDLE_KEY_R_REVOCATION, mContext.getResources().getString(R.string.data_error));
                transferAction(REQUEST_SUCCESS, bundle);
            }
        } catch (JSONException je) {
            je.printStackTrace();
        }
    }
}
