package com.thinkive.android.trade_bz.request;

import android.os.Bundle;

import com.thinkive.android.trade_bz.a_option.bean.OptionEntrustOrderBean;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.others.constants.Constants;
import com.thinkive.android.trade_bz.utils.JsonParseUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 个股期权委托撤单下单
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/6/21
 */
public class Option305005 extends BaseOptionRequest {

    public static final String BUNDLE_KEY_ENTRUST_ORDER = "Option305005";

    public Option305005(HashMap<String, String> paramMap, IRequestAction action) {
        super(action);
        paramMap.put("funcNo", "305005");
        setParamHashMap(paramMap);
        setUrlName(Constants.URL_TRADE);
    }

    @Override
    void getJsonDataWithoutError(JSONObject jsonObject) {
        Bundle bundle = new Bundle();
        ArrayList<OptionEntrustOrderBean> optionEntrustOrderBeans = null;
        try {
            JSONArray resultTargets = jsonObject.getJSONArray("dsName");
            optionEntrustOrderBeans= JsonParseUtils.createBeanList(OptionEntrustOrderBean.class, jsonObject.getJSONArray(resultTargets.get(0).toString()));
            bundle.putParcelableArrayList(BUNDLE_KEY_ENTRUST_ORDER,optionEntrustOrderBeans);
            transferAction(REQUEST_SUCCESS, bundle);
        } catch (Exception je) {
            je.printStackTrace();
        }
    }
}
