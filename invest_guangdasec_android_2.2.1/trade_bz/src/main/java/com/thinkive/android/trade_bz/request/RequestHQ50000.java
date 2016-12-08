package com.thinkive.android.trade_bz.request;

import android.graphics.Color;
import android.os.Bundle;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_hk.bean.HKStockMessageBean;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.utils.TradeConfigUtils;
import com.thinkive.android.trade_bz.utils.TradeUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * 港股数据请求（调用C版行情服务器）
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/1/29
 */
public class RequestHQ50000 extends BaseSocketRequest {

    public static final String BUNDLE_KEY_SOCKET_HK = "RequestHQ50000";
    public RequestHQ50000(HashMap<String, String> paramMap, IRequestAction action) {
        super(action);
        paramMap.put("funcno", "50000");
        setParamHashMap(paramMap);
        setUrlName("HQ_URL_SOCKET");
    }

    @Override
    void getErrorInfoWithoutError(String string) {

    }

    @Override
    void getJsonDataWithoutError(JSONObject jsonObject) {
        Bundle bundle = new Bundle();
        try {
            JSONArray resultJsonArray = jsonObject.getJSONArray("results");
            HKStockMessageBean bean = new HKStockMessageBean();
            //买价：卖价：名称：代码：每手：买量：卖量：是否停牌 :现价
            if (resultJsonArray == null || resultJsonArray.length() == 0) {
                bundle.putString(ERROR_INFO, mContext.getResources().getString(R.string.toast_data_error));
                transferAction(REQUEST_FAILED, bundle);
            } else {
                JSONArray jsonArray = resultJsonArray.getJSONArray(0);

                double buyPrice = Double.parseDouble(jsonArray.get(0).toString());
                double sellPrice = Double.parseDouble(jsonArray.get(1).toString());
                double nowPrice = Double.parseDouble(jsonArray.get(8).toString());

                bean.setBuy_price(TradeUtils.formatDouble3(buyPrice));
                bean.setSell_price(TradeUtils.formatDouble3(sellPrice));
                bean.setName(jsonArray.get(2).toString());
                bean.setCode(jsonArray.get(3).toString());
                bean.setOne_unit_num(jsonArray.get(4).toString());
                bean.setBuy_num(jsonArray.get(5).toString());
                bean.setSell_num(jsonArray.get(6).toString());
                bean.setIs_stop(jsonArray.get(7).toString());
                bean.setNow_price(TradeUtils.formatDouble3(nowPrice));
                if(buyPrice < nowPrice){
                    bean.setBuyColor(Color.parseColor(TradeConfigUtils.sPriceDownColor));
                }else if(buyPrice > nowPrice){
                    bean.setBuyColor(Color.parseColor(TradeConfigUtils.sPriceDownColor));
                }else{
                    bean.setBuyColor(Color.parseColor(TradeConfigUtils.sGrayTextColor));
                }

                if(sellPrice < nowPrice){
                    bean.setSellColor(Color.parseColor(TradeConfigUtils.sPriceDownColor));
                }else if(sellPrice > nowPrice){
                    bean.setSellColor(Color.parseColor(TradeConfigUtils.sPriceDownColor));
                }else{
                    bean.setSellColor(Color.parseColor(TradeConfigUtils.sGrayTextColor));
                }
                bundle.putSerializable(BUNDLE_KEY_SOCKET_HK, bean);
                transferAction(REQUEST_SUCCESS, bundle);
            }
        } catch (JSONException je) {
            je.printStackTrace();
        }
    }
}
