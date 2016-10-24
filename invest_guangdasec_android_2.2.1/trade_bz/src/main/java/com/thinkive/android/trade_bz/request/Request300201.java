package com.thinkive.android.trade_bz.request;

import android.os.Bundle;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.bean.BankTransferBean;
import com.thinkive.android.trade_bz.others.constants.Constants;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.utils.JsonParseUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * 银证转账--查询转帐银行业务信息
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/8/10
 */
public class Request300201 extends BaseNormalRequest {

    public static final String BUNDLE_KEY_OTHER_MONEY = "Request300201_result";

    public Request300201(HashMap<String, String> paramMap, IRequestAction action) {
        super(action);
        paramMap.put("funcNo", "300201");
        setParamHashMap(paramMap);
        setUrlName(Constants.URL_TRADE);
    }

    @Override
    void getJsonDataWithoutError(JSONObject jsonObject) {
        Bundle bundle = new Bundle();
        try {
            String resultTarget = jsonObject.getJSONArray("dsName").get(0).toString();
            JSONObject jsonResult = jsonObject.getJSONArray(resultTarget).getJSONObject(0);
            BankTransferBean bean;
                if (jsonResult != null) {
                    bean = JsonParseUtils.createBean(BankTransferBean.class,jsonResult);
                    bundle.putSerializable(BUNDLE_KEY_OTHER_MONEY, bean);
                    transferAction(REQUEST_SUCCESS, bundle);
                }else {
                    bundle.putString(BUNDLE_KEY_OTHER_MONEY, mContext.getResources().getString(R.string.data_error));
                    transferAction(REQUEST_SUCCESS, bundle);
                }
        } catch (JSONException je) {
            je.printStackTrace();
        }
    }
}
