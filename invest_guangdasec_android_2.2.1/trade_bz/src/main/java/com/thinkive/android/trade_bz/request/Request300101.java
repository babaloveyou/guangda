package com.thinkive.android.trade_bz.request;

import android.os.Bundle;

import com.thinkive.android.trade_bz.others.constants.Constants;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;

import org.json.JSONObject;

import java.util.HashMap;

/**
 *  修改密码（交易密码）
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/8/11
 */
public class Request300101 extends BaseNormalRequest {

    public Request300101(HashMap<String, String> paramMap, IRequestAction action) {
        super(action);
        paramMap.put("funcNo", "300101");
        setParamHashMap(paramMap);
        setUrlName(Constants.URL_TRADE);
    }

    @Override
    void getJsonDataWithoutError(JSONObject jsonObject) {
        transferAction(REQUEST_SUCCESS, new Bundle());
    }
}
