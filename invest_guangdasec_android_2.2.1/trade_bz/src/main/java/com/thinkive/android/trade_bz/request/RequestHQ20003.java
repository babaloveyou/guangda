package com.thinkive.android.trade_bz.request;

import android.os.Bundle;
import android.text.TextUtils;

import com.thinkive.android.trade_bz.a_stock.bean.StockBuySellDish;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.utils.TradeConfigUtils;
import com.thinkive.android.trade_bz.utils.TradeUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 获取股票行情五档买卖盘的请求，请求的是行情接口20003
 * @author 王志鸿
 * @company Thinkive
 * @date 2015/7/7
 */
public class RequestHQ20003 extends BaseSocketRequest {

    public static final String BUNDLE_KEY_WUDANG = "RequestHQ20003_wudang_result";
    public static final String NOW_PRICE = "RequestHQ20003_now_price";
    public static final String INCREASE_AMOUNT = "RequestHQ20003_increase_amount";

    public RequestHQ20003(HashMap<String, String> paramMap, IRequestAction action) {
        super(action);
        paramMap.put("funcno", "20003");
        setParamHashMap(paramMap);
//        setUrlName("HQ_URL_SOCKET");
        setUrlName("HQ_URL_HTTP");
    }

    @Override
    void getErrorInfoWithoutError(String string) {

    }

    @Override
    void getJsonDataWithoutError(JSONObject jsonObject) {
        try {
            JSONArray resultDataArray = jsonObject.getJSONArray("results").getJSONArray(0);
            if (resultDataArray.length() > 25) { // 返回的数据必须大于25个，不然报错
                Double nowPrice = (double) resultDataArray.get(30);
                Double increase = (double) (resultDataArray.get(36))*100;
                String increaseString = null;
                if (increase >=0.01) {
                    increaseString=TradeUtils.formatDouble2(increase);
                } else {
                    increaseString = "0.00";
                }
                StockBuySellDish bean = getWuDangDishBean(resultDataArray);
                Bundle bundle = new Bundle();
                bundle.putSerializable(BUNDLE_KEY_WUDANG, bean);
                bundle.putString(NOW_PRICE, TradeUtils.formatDouble2(nowPrice));
                bundle.putString(INCREASE_AMOUNT,increaseString);
                transferAction(REQUEST_SUCCESS, bundle);
            } else {
                Bundle bundle = new Bundle();
                bundle.putSerializable(ERROR_INFO, "no enough data!");
                transferAction(REQUEST_FAILED, bundle);
            }
        } catch (JSONException je) {
            je.printStackTrace();
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        }
    }

    /**
     * 将服务器放回的JSONArray对象转化为Bean对象
     * 格式化数据等业务性过强的功能在业务类中。
     * @param resultDataArray
     * @return
     * @throws JSONException
     * @throws NumberFormatException
     */
    private StockBuySellDish getWuDangDishBean(JSONArray resultDataArray) throws JSONException, NumberFormatException {
        // 五档买卖盘十个数据的值
        ArrayList<String> valueBuySale = new ArrayList<String>();
        // 五档买卖盘十个数据的显示颜色
        ArrayList<String> priceColorsList = new ArrayList<String>();
        StockBuySellDish bean = new StockBuySellDish();
        double yesterdayClose = Double.parseDouble(resultDataArray.get(26).toString());
        double tempPriceDouble;
        String tempPriceStr;
        // 下面四个循环就是设置上面两个ArrayList的值的，根据服务器返回的数据
        for (int i = 6; i <= 10; i++) { // 卖价五~卖价一
            tempPriceStr = resultDataArray.get(i).toString();
            tempPriceDouble = Double.parseDouble(tempPriceStr);
            valueBuySale.add(forMateData(tempPriceStr));
            if (tempPriceDouble < yesterdayClose) {
                priceColorsList.add(TradeConfigUtils.sPriceDownColor);
            } else if (tempPriceDouble > yesterdayClose){
                priceColorsList.add(TradeConfigUtils.sPriceUpColor);
            } else {
                priceColorsList.add(TradeConfigUtils.sGrayTextColor);
            }
        }
        for (int i = 11; i <= 15; i++) { // 卖量五~卖量一
            valueBuySale.add(forMateData(resultDataArray.get(i).toString()));
            priceColorsList.add("");
        }
        for (int i = 16; i <= 20; i++) { // 买价一~买价五
            tempPriceStr = resultDataArray.get(i).toString();
            tempPriceDouble = Double.parseDouble(tempPriceStr);
            valueBuySale.add(forMateData(tempPriceStr));
            if (tempPriceDouble < yesterdayClose) {
                priceColorsList.add(TradeConfigUtils.sPriceDownColor);
            } else if (tempPriceDouble > yesterdayClose){
                priceColorsList.add(TradeConfigUtils.sPriceUpColor);
            } else {
                priceColorsList.add(TradeConfigUtils.sGrayTextColor);
            }
        }
        for (int i = 21; i <= 25; i++) { // 买量一~买量五
            valueBuySale.add(forMateData(resultDataArray.get(i).toString()));
            priceColorsList.add("");
        }
        bean.setPriceColorsList(priceColorsList);
        bean.setValueBuySale(valueBuySale);
        return bean;
    }

    /**
     * 如果五档返回给我的数据是 0则返回 “ ”
     * @param str
     * @return
     */
    public String forMateData(String str){
        String result = "";
        double tempPriceDouble = 0.0;
        if(!TextUtils.isEmpty(str) && str != null){
            tempPriceDouble = Double.parseDouble(str);
            if(tempPriceDouble > 0){
                result = str;
            }
        }
        return result;
    }
}
