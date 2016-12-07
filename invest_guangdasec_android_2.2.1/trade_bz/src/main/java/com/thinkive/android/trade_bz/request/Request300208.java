package com.thinkive.android.trade_bz.request;

import android.os.Bundle;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.bean.OneKeyBean;
import com.thinkive.android.trade_bz.others.constants.Constants;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.utils.JsonParseUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 一键归集--资产信息账户查询
 *
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/8/12
 */
public class Request300208 extends BaseNormalRequest {

    public static final String BUNDLE_KEY_ONE_KEY_SELECT = "Request300208_result";

    public Request300208(HashMap<String, String> paramMap, IRequestAction action) {
        super(action);
        paramMap.put("funcNo", "300208");
        setParamHashMap(paramMap);
        setUrlName(Constants.URL_TRADE);
    }

    @Override
    void getJsonDataWithoutError(JSONObject jsonObject) {
        System.out.println("300208========================");
        try {
            String resultTarget = jsonObject.getJSONArray("dsName").get(0).toString();
            JSONArray jArray = jsonObject.getJSONArray(resultTarget);
            JSONObject o = (JSONObject) jArray.get(0);
            System.out.println("0000000000000000" + o.toString());
            if (jArray != null) {
                ArrayList<OneKeyBean> dataList = JsonParseUtils.createBeanList(OneKeyBean.class, jArray);
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList(BUNDLE_KEY_ONE_KEY_SELECT, dataList);
                transferAction(REQUEST_SUCCESS, bundle);
            } else {
                Bundle bundle = new Bundle();
                bundle.putString(BUNDLE_KEY_ONE_KEY_SELECT, mContext.getResources().getString(R.string.data_error));
                transferAction(REQUEST_SUCCESS, bundle);
            }
        } catch (JSONException je) {
            je.printStackTrace();
        }
    }
}
