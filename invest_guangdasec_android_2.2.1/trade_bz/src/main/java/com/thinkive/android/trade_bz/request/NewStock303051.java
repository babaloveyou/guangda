package com.thinkive.android.trade_bz.request;

import android.os.Bundle;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_new.bean.NewOneKeyBean;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.others.constants.Constants;
import com.thinkive.android.trade_bz.utils.JsonParseUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 新股批量申购 列表查询（信用）
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/12/31
 */
public class NewStock303051 extends BaseCreditRequest {

    public static final String BUNDLE_KEY_TODAY_TOTAL_STOCK = "NewStock303051";

    public NewStock303051(HashMap<String, String> params, IRequestAction action) {
        super(action);
        params.put("funcNo", "303051");
        setParamHashMap(params);
        setUrlName(Constants.URL_CREDIT_TRADE);
    }

    @Override
    void getJsonDataWithoutError(JSONObject jsonObject) {
        try {
            String resultTarget = jsonObject.getJSONArray("dsName").get(0).toString();
            JSONArray resultJsonArray = jsonObject.getJSONArray(resultTarget);
            ArrayList<NewOneKeyBean> dataList = JsonParseUtils.createBeanList(NewOneKeyBean.class, resultJsonArray);
            if (dataList != null) {
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList(BUNDLE_KEY_TODAY_TOTAL_STOCK, dataList);
                transferAction(REQUEST_SUCCESS, bundle);
            } else {
                Bundle bundle = new Bundle();
                bundle.putString(BUNDLE_KEY_TODAY_TOTAL_STOCK,
                        mContext.getResources().getString(R.string.data_error));
                transferAction(REQUEST_SUCCESS, bundle);
            }
        } catch (JSONException je) {
            je.printStackTrace();
        }
    }
}

