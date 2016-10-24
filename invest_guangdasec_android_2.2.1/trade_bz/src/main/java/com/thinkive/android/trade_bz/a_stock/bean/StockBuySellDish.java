package com.thinkive.android.trade_bz.a_stock.bean;

import java.util.ArrayList;

/**
 * 相关注释待本类代码基本稳定后添加，敬请期待！
 * 股票的五档买卖盘实体定义
 * @author 王志鸿
 * @company Thinkive
 * @date 2015/6/25
 */
public class StockBuySellDish extends BaseBean {
    private ArrayList<String> valueBuySale;
    private ArrayList<String> priceColorsList;

    public ArrayList<String> getValueBuySale() {
        return valueBuySale;
    }

    public void setValueBuySale(ArrayList<String> valueBuySale) {
        this.valueBuySale = valueBuySale;
    }

    public ArrayList<String> getPriceColorsList() {
        return priceColorsList;
    }

    public void setPriceColorsList(ArrayList<String> priceColorsList) {
        this.priceColorsList = priceColorsList;
    }
}
