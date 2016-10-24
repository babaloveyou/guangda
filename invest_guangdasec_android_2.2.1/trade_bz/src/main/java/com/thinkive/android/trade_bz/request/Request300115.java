package com.thinkive.android.trade_bz.request;

import android.os.Bundle;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.bean.SignStockAccountLimitBean;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.others.constants.Constants;
import com.thinkive.android.trade_bz.utils.JsonParseUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


/**
 *  客户证券账户权限查询(300115)
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/8/1
 */

public class Request300115 extends BaseRequest {

    public static final String BUNDLE_KEY_STOCK_ACCOUNT = "Request300115";

    public Request300115(HashMap<String, String> paramMap, IRequestAction action) {
        super(action);
        setParamHashMap(paramMap);
        setUrlName(Constants.URL_TRADE);
    }

    @Override
    void getJsonDataWithoutError(JSONObject jsonObject) {
        try {
            String resultTarget = jsonObject.getJSONArray("dsName").get(0).toString();
            JSONArray jsonArray = jsonObject.getJSONArray(resultTarget);
            if (jsonArray != null) {
                ArrayList<SignStockAccountLimitBean> dataList = JsonParseUtils.createBeanList(SignStockAccountLimitBean.class, jsonArray);
                Bundle bundle = new Bundle();
                bundle.putSerializable(BUNDLE_KEY_STOCK_ACCOUNT, dataList);
                transferAction(REQUEST_SUCCESS, bundle);
            }else {
                Bundle bundle = new Bundle();
                bundle.putString(BUNDLE_KEY_STOCK_ACCOUNT,mContext.getResources().getString(R.string.data_error));
                transferAction(REQUEST_SUCCESS, bundle);
            }
        } catch (JSONException je) {
            je.printStackTrace();
        }
    }
}
