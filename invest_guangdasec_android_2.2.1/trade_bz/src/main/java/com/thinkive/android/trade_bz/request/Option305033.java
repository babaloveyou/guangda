package com.thinkive.android.trade_bz.request;


import android.os.Bundle;

import com.thinkive.android.trade_bz.a_option.bean.OptionContractOpenBean;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.others.constants.Constants;
import com.thinkive.android.trade_bz.utils.JsonParseUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * Description：股期权备兑解锁合约查询 <br>
 * Author：晏政清 <br>
 * Corporation：深圳市思迪信息技术股份有限公司 <br>
 * Date：2016/7/28 <br>
 */
public class Option305033 extends BaseOptionRequest {
    public static final String BUNDLE_KEY_CONTRACT_OPEN = "Option305033";

    /**
     * @param action
     */
    public Option305033(HashMap<String,String> paramMap, IRequestAction action) {
        super(action);
        paramMap.put("funcNo","305033");
        setParamHashMap(paramMap);
        setUrlName(Constants.URL_TRADE);
    }

    /**
     * 对成功返回的结果进行初步处理
     * @param jsonObject
     */
    @Override
    void getJsonDataWithoutError(JSONObject jsonObject) {
        super.getJsonDataWithoutError(jsonObject);
        Bundle bundle=null;
        try {
            bundle = new Bundle();
            JSONArray resultTargets = jsonObject.getJSONArray("dsName");
            ArrayList<OptionContractOpenBean> optionContractOpenBeans = JsonParseUtils.createBeanList(OptionContractOpenBean.class, jsonObject.getJSONArray(resultTargets.get(0).toString()));
            bundle.putParcelableArrayList(BUNDLE_KEY_CONTRACT_OPEN,optionContractOpenBeans);
            transferAction(REQUEST_SUCCESS,bundle);
        } catch (JSONException e) {
            e.printStackTrace();
            transferAction(REQUEST_SUCCESS, bundle);
        }
    }
}
