package com.thinkive.android.trade_bz.request;

import android.os.Bundle;

import com.thinkive.android.trade_bz.a_new.bean.NewNumberWinningBean;
import com.thinkive.android.trade_bz.others.constants.Constants;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.utils.JsonParseUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 新股中签 （信用账户）
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/7/7
 */
public class NewStock303030 extends BaseCreditRequest {

    public static final String BUNDLE_KEY_WINNING_NUMBER = "NewStock303030";

    public NewStock303030(HashMap<String, String> paramMap, IRequestAction action) {
        super(action);
        paramMap.put("funcNo", "303030");
        setParamHashMap(paramMap);
        setUrlName(Constants.URL_CREDIT_TRADE);
    }

    @Override
    void getJsonDataWithoutError(JSONObject jsonObject) {
        Bundle bundle = new Bundle();
        try {
            String resultTarget = jsonObject.getJSONArray("dsName").get(0).toString();
            JSONArray jsonArray = jsonObject.getJSONArray(resultTarget);
            if (jsonArray != null) {
                ArrayList<NewNumberWinningBean> dataList = JsonParseUtils.createBeanList(NewNumberWinningBean.class, jsonArray);
                bundle.putParcelableArrayList(BUNDLE_KEY_WINNING_NUMBER, dataList);
                transferAction(REQUEST_SUCCESS, bundle);
            }else {
                bundle.putString(ERROR_INFO, jsonObject.getString(ERROR_INFO));
                transferAction(REQUEST_FAILED, bundle);
            }
        } catch (JSONException je) {
            je.printStackTrace();
        }
    }
}
