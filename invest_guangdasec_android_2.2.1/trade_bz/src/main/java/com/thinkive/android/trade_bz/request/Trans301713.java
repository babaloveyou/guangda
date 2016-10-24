package com.thinkive.android.trade_bz.request;

import android.os.Bundle;
import android.util.Log;

import com.thinkive.android.trade_bz.a_trans.bean.TransSelectTicketBean;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.others.constants.Constants;
import com.thinkive.android.trade_bz.utils.JsonParseUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *  转股交易挂牌股票查询
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/7/29
 */
public class Trans301713 extends BaseNormalRequest {

    public static final String BUNDLE_KEY_HISTORY_TRADE = "Trans301713";

    public Trans301713(HashMap<String, String> paramMap, IRequestAction action) {
        super(action);
        paramMap.put("funcNo", "301713");
        setParamHashMap(paramMap);
        setUrlName(Constants.URL_TRADE);
    }

    @Override
    void getJsonDataWithoutError(JSONObject jsonObject) {
        try {
            String resultTarget = jsonObject.getJSONArray("dsName").get(0).toString();
            JSONArray jsonArray = jsonObject.getJSONArray(resultTarget);
            if (jsonArray != null) {
                Log.d("TAG","挂牌股票查询 =="+jsonArray.get(0));
                ArrayList<TransSelectTicketBean> dataList = JsonParseUtils.createBeanList(TransSelectTicketBean.class, jsonArray);
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList(BUNDLE_KEY_HISTORY_TRADE, dataList);
                transferAction(REQUEST_SUCCESS, bundle);
            }
        } catch (JSONException je) {
            je.printStackTrace();
        }
    }
}
