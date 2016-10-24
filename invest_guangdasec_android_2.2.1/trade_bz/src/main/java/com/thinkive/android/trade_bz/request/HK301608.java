package com.thinkive.android.trade_bz.request;

import android.os.Bundle;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_hk.bean.HKHistoryEntrustBean;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.others.constants.Constants;
import com.thinkive.android.trade_bz.utils.JsonParseUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 港股通 历史委托（301608）
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/11/2
 */
public class HK301608 extends BaseNormalRequest {

    public static final String BUNDLE_KEY_HK_HISTORY_ENTRUST = "bundle_key_Request301608";

    public HK301608(HashMap<String, String> params, IRequestAction action) {
        super(action);
        params.put("funcNo", "301608");
        setParamHashMap(params);
        setUrlName(Constants.URL_TRADE);
    }

    @Override
    void getJsonDataWithoutError(JSONObject jsonObject) {
        try {
            String resultTarget = jsonObject.getJSONArray("dsName").get(0).toString();
            JSONArray resultJsonArray = jsonObject.getJSONArray(resultTarget);
            ArrayList<HKHistoryEntrustBean> dataList = JsonParseUtils.createBeanList(HKHistoryEntrustBean.class, resultJsonArray);
            if (dataList != null) {
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList(BUNDLE_KEY_HK_HISTORY_ENTRUST, dataList);
                transferAction(REQUEST_SUCCESS, bundle);
            } else {
                Bundle bundle = new Bundle();
                bundle.putString(BUNDLE_KEY_HK_HISTORY_ENTRUST,mContext.getResources().getString(R.string.hk_order25));
                transferAction(REQUEST_SUCCESS, bundle);
            }
        } catch (JSONException je) {
            je.printStackTrace();
        }
    }
}
