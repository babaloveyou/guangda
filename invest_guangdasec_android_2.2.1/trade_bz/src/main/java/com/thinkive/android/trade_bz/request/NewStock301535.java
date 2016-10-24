package com.thinkive.android.trade_bz.request;

import android.os.Bundle;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_new.bean.NewStockBean;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.others.constants.Constants;
import com.thinkive.android.trade_bz.utils.JsonParseUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 当日新股查询（普通交易）
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/12/14
 */
public class NewStock301535 extends BaseNormalRequest {

    public static final String BUNDLE_KEY_TODAY_NEW_STOCK = "bundle_key_today_new_stock";

    public NewStock301535(HashMap<String, String> params, IRequestAction action) {
        super(action);
        params.put("funcNo", "301535");
        setParamHashMap(params);
        setUrlName(Constants.URL_TRADE);
    }

    @Override
    void getJsonDataWithoutError(JSONObject jsonObject) {
        try {
            String resultTarget = jsonObject.getJSONArray("dsName").get(0).toString();
            JSONArray resultJsonArray = jsonObject.getJSONArray(resultTarget);
            ArrayList<NewStockBean> dataList = JsonParseUtils.createBeanList(NewStockBean.class, resultJsonArray);
            if (dataList != null) {
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList(BUNDLE_KEY_TODAY_NEW_STOCK, dataList);
                transferAction(REQUEST_SUCCESS, bundle);
            } else {
                Bundle bundle = new Bundle();
                bundle.putString(BUNDLE_KEY_TODAY_NEW_STOCK,
                        mContext.getResources().getString(R.string.data_error));
                transferAction(REQUEST_SUCCESS, bundle);
            }
        } catch (JSONException je) {
            je.printStackTrace();
        }
    }
}

