package com.thinkive.android.trade_bz.request;

import android.os.Bundle;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_hk.bean.HKCapitalVoteMsgBean;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.others.constants.Constants;
import com.thinkive.android.trade_bz.utils.JsonParseUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 港股通 投票信息查询
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/9/19
 */
public class HK301626 extends BaseNormalRequest {

    public static final String BUNDLE_KEY_RESULT = "HK301626";

    public HK301626(HashMap<String, String> paramMap, IRequestAction action) {
        super(action);
        paramMap.put("funcNo", "301626");
        setParamHashMap(paramMap);
        setUrlName(Constants.URL_TRADE);
    }

    @Override
    void getJsonDataWithoutError(JSONObject jsonObject) {
        try {
            String resultTarget = jsonObject.getJSONArray("dsName").get(0).toString();
            JSONArray jsonArray = jsonObject.getJSONArray(resultTarget);
            Bundle bundle = new Bundle();
            if (jsonArray != null) {
                ArrayList<HKCapitalVoteMsgBean> dataList = JsonParseUtils.createBeanList(HKCapitalVoteMsgBean.class, jsonArray);
                bundle.putParcelableArrayList(BUNDLE_KEY_RESULT, dataList);
                transferAction(REQUEST_SUCCESS, bundle);
            } else {
                bundle.putString(ERROR_INFO, mContext.getResources().getString(R.string.hk_order25));
                transferAction(REQUEST_FAILED, bundle);
            }
        } catch (JSONException je) {
            je.printStackTrace();
        }
    }
}
