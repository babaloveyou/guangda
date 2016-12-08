package com.thinkive.android.trade_bz.request;

import android.os.Bundle;
import android.text.TextUtils;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.bean.CodeTableBean;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.others.tools.TradeTools;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * 请求行情接口，获取证券信息
 * @author 王志鸿
 * @company Thinkive
 * @date 2015/7/14
 */
public class RequestHQ20000 extends BaseSocketRequest {

    public static final String BUNDLE_KEY_SOCKET = "RequestHQ20000";

    public RequestHQ20000(HashMap<String, String> paramMap, IRequestAction action) {
        super(action);
        paramMap.put("funcno", "20000");
        setParamHashMap(paramMap);
//        setUrlName("HQ_URL_SOCKET");
        setUrlName("HQ_URL_HTTP");
    }

    @Override
    void getErrorInfoWithoutError(String string) {

    }

    @Override
    void getJsonDataWithoutError(JSONObject jsonObject) {
        Bundle bundle = new Bundle();
        try {
            JSONArray resultJsonArray = jsonObject.getJSONArray("results");
            CodeTableBean bean = new CodeTableBean();
            if (resultJsonArray == null) {
                bundle.putString(ERROR_INFO, mContext.getResources().getString(R.string.data_error));
                transferAction(REQUEST_FAILED, bundle);
            } else if(resultJsonArray != null && resultJsonArray.length() == 0) {
                bundle.putString(ERROR_INFO, mContext.getResources().getString(R.string.toast_data_error));
                transferAction(REQUEST_FAILED, bundle);
            } else if(resultJsonArray != null && resultJsonArray.length() > 0){
                JSONArray JArray = resultJsonArray.getJSONArray(0);
                //名称：市场：代码：涨停：跌停：是否停牌:股票类型:现价
                bean.setName(JArray.get(0).toString());
                bean.setMarket(JArray.get(1).toString());
                bean.setCode(JArray.get(2).toString());
                bean.setUpLimit(JArray.get(3).toString());
                bean.setDownLimit(JArray.get(4).toString());
                bean.setIssuspend(JArray.get(5).toString());
                String stockType = JArray.get(6).toString();
                bean.setNow(JArray.get(7).toString());
                bean.setStockType(stockType);
                String exchangeType = TradeTools.transferStockTypeHqToTrade(stockType);
                bean.setExchange_type(exchangeType);
                bean.setStock_account(TradeTools.forMateStockAccount(exchangeType));
                bean.setStep_unit(forMatePriceStep(stockType,JArray.get(1).toString()));
                bundle.putSerializable(BUNDLE_KEY_SOCKET, bean);
                transferAction(REQUEST_SUCCESS, bundle);
            }
        } catch (JSONException je) {
            je.printStackTrace();
        }
    }
    private String forMatePriceStep(String stockType,String market){
        String result = "0.01";
        if(stockType != null && !TextUtils.isEmpty(stockType)){
            int count = TradeTools.transferStockType(stockType);
            if(count == 3){ // 基金
                result = "0.001";
            }else if(count == 4){ // 债券
                if(market != null && !TextUtils.isEmpty(market)){
                    if(market.equals("SH")){  //沪市
                        result = "0.005";
                    }else{  //深市
                        result = "0.001";
                    }
                }else{
                    result = "0.001";
                }
            }
        }
        return result;
    }
}
