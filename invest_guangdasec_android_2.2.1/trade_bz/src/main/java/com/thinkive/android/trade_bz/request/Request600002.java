package com.thinkive.android.trade_bz.request;

import android.os.Bundle;

import com.thinkive.android.trade_bz.others.constants.Constants;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.utils.LogUtil;

import org.json.JSONObject;

import java.util.HashMap;

/**
 *
 * 业务逻辑：
 * @author 王志鸿
 * @company Thinkive
 * @date 2015/8/17
 */
public class Request600002 extends BaseRequest {

//    private String mLoginType;

    public Request600002(HashMap<String, String> paramMap, IRequestAction action) {
        super(action);
        paramMap.put("funcNo", "600002");
        setParamHashMap(paramMap);
        setUrlName(Constants.URL_TRADE);
//        mLoginType = loginType;
    }

    @Override
    void getErrorInfoWithoutError(String string) {

    }

    @Override
    void getJsonDataWithoutError(JSONObject jsonObject) {
        LogUtil.printLog("d", jsonObject.toString());
        transferAction(REQUEST_SUCCESS, new Bundle());
    }
}
