package com.thinkive.android.trade_bz.a_stock.bean;

/**
 * 普通交易买入卖出，市价委托的类别
 * 2015/12/4
 * 张雪梅
 */
public class MarketEntrustWay extends BaseBean {
    /**
     * 市价委托名称
     */
    private String entrust_way_name = "";
    /**
     * 市价委托名称编号
     */
    private String entrust_way_num = "";

    public String getEntrust_way_name() {
        return entrust_way_name;
    }

    public void setEntrust_way_name(String entrust_way_name) {
        this.entrust_way_name = entrust_way_name;
    }

    public String getEntrust_way_num() {
        return entrust_way_num;
    }

    public void setEntrust_way_num(String entrust_way_num) {
        this.entrust_way_num = entrust_way_num;
    }
}
