package com.thinkive.android.trade_bz.request;

import android.os.Bundle;

import com.thinkive.android.trade_bz.others.constants.Constants;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;

import org.json.JSONObject;

import java.util.HashMap;

/**
 *  修改密码(融资融券密码)
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/8/21
 */
public class Request303042 extends BaseCreditRequest {

    public Request303042(HashMap<String, String> paramMap, IRequestAction action) {
        super(action);
        paramMap.put("funcNo", "303042");
        setParamHashMap(paramMap);
        setUrlName(Constants.URL_CREDIT_TRADE);
    }
    @Override
    void getJsonDataWithoutError(JSONObject jsonObject) {
        transferAction(REQUEST_SUCCESS, new Bundle());
    }
}
