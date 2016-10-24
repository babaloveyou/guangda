package com.thinkive.android.trade_bz.request;

import android.os.Bundle;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_hk.bean.HKTodayTradeBean;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.others.constants.Constants;
import com.thinkive.android.trade_bz.utils.JsonParseUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 港股通 今日成交（301607）
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/11/2
 */
public class HK301607 extends BaseNormalRequest {

    public static final String BUNDLE_KEY_HK_TODAY_TRADE = "bundle_key_Request301607";

    public HK301607(HashMap<String, String> params, IRequestAction action) {
        super(action);
        params.put("funcNo", "301607");
        setParamHashMap(params);
        setUrlName(Constants.URL_TRADE);
    }

    @Override
    void getJsonDataWithoutError(JSONObject jsonObject) {
        try {
            String resultTarget = jsonObject.getJSONArray("dsName").get(0).toString();
            JSONArray resultJsonArray = jsonObject.getJSONArray(resultTarget);
            ArrayList<HKTodayTradeBean> dataList = JsonParseUtils.createBeanList(HKTodayTradeBean.class, resultJsonArray);
            if (dataList != null) {
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList(BUNDLE_KEY_HK_TODAY_TRADE, dataList);
                transferAction(REQUEST_SUCCESS, bundle);
            } else {
                Bundle bundle = new Bundle();
                bundle.putString(BUNDLE_KEY_HK_TODAY_TRADE,mContext.getResources().getString(R.string.hk_order25));
                transferAction(REQUEST_SUCCESS, bundle);
            }
        } catch (JSONException je) {
            je.printStackTrace();
        }
    }
}
