package com.thinkive.android.trade_bz.request;

import android.os.Bundle;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.bean.MyStoreStockBean;
import com.thinkive.android.trade_bz.others.constants.Constants;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.utils.JsonParseUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 普通交易证券持仓请求
 * 注：该类只适合普通交易账户使用
 * @author 王志鸿
 * @company Thinkive
 * @date 2015/7/7
 */
public class Request301503 extends BaseNormalRequest {

    public static final String BUNDLE_KEY_RESULT = "Request301503_result";

    /**
     * @param paramMap 请求参数列表
     * @param action 回调到主线程的接口
     */
    public Request301503(HashMap<String, String> paramMap, IRequestAction action) {
        super(action);
        paramMap.put("funcNo", "301503");
        setParamHashMap(paramMap);
        setUrlName(Constants.URL_TRADE);
    }

    @Override
    void getJsonDataWithoutError(JSONObject jsonObject) {
        try {
            String resultTarget = jsonObject.getJSONArray("dsName").get(0).toString();
            JSONArray jsonArray = jsonObject.getJSONArray(resultTarget);
            if (jsonArray != null) {
                ArrayList<MyStoreStockBean> dataList = JsonParseUtils.createBeanList(MyStoreStockBean.class, jsonArray);
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList(BUNDLE_KEY_RESULT, dataList);
                transferAction(REQUEST_SUCCESS, bundle);
            }else {
                Bundle bundle = new Bundle();
                bundle.putString(BUNDLE_KEY_RESULT,mContext.getResources().getString(R.string.data_error));
                transferAction(REQUEST_SUCCESS, bundle);
            }
        } catch (JSONException je) {
            je.printStackTrace();
        }
    }
}
