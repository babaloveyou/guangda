package com.thinkive.android.trade_bz.a_stock.bean;

import android.os.Parcel;

import com.thinkive.android.trade_bz.others.JsonKey;

/**
 * 交易模块，买卖界面中股票联动使用的Java bean
 *
 * @author 王志鸿
 * @company Thinkive
 * @date 2015/6/24
 */
public class StockLinkageBean extends BaseBean {
    /*
    * 风险提示参数
    * */
    @JsonKey("limited_type")
    private String limited_type = "";
    /*
    * 股票数量输入框中控制整数倍的基数
    * */
    @JsonKey("store_unit")
    private String store_unit = "";

    public String getStore_unit() {
        return store_unit;
    }

    public void setStore_unit(String store_unit) {
        this.store_unit = store_unit;
    }

    public String getLimited_type() {
        return limited_type;
    }

    public void setLimited_type(String limited_type) {
        this.limited_type = limited_type;
    }

    @JsonKey("exchange_type")
    private String exchange_type = "";
    @JsonKey("enable_balance")
    private String enable_balance = "";
    @JsonKey("stock_account")
    private String stock_account = "";
    @JsonKey("stock_max_amount")
    private String stock_max_amount = "";
    @JsonKey("price")
    private String price = "";
    @JsonKey("buy_unit")
    private String buy_unit = "";
    @JsonKey("point")
    private String point = "";
    @JsonKey("price_step")
    private String price_step = "";
    /**
     * 证券名称
     */
    @JsonKey("stock_name")
    protected String stock_name = "";
    /**
     * 证券代码
     */
    @JsonKey("stock_code")
    protected String stock_code = "";
    /**
     * 证券市场
     */
    @JsonKey("market")
    protected String market = "";
    /**
     * 证券类型
     */
    @JsonKey("type")
    protected int type = -999;
    /**
     * 发行价
     */
    private String issue_price = "";

    public String getIssue_price() {
        return issue_price;
    }

    public void setIssue_price(String issue_price) {
        this.issue_price = issue_price;
    }

    public String getStock_name() {
        return stock_name;
    }

    public void setStock_name(String stock_name) {
        this.stock_name = stock_name;
    }

    public String getStock_code() {
        return stock_code;
    }

    public void setStock_code(String stock_code) {
        this.stock_code = stock_code;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getBuy_unit() {
        return buy_unit;
    }

    public void setBuy_unit(String buy_unit) {
        this.buy_unit = buy_unit;
    }

    public String getEnable_balance() {
        return enable_balance;
    }

    public void setEnable_balance(String enable_balance) {
        this.enable_balance = enable_balance;
    }

    public String getExchange_type() {
        return exchange_type;
    }

    public void setExchange_type(String exchange_type) {
        this.exchange_type = exchange_type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStock_account() {
        return stock_account;
    }

    public void setStock_account(String stock_account) {
        this.stock_account = stock_account;
    }

    public String getStock_max_amount() {
        return stock_max_amount;
    }

    public void setStock_max_amount(String stock_max_amount) {
        this.stock_max_amount = stock_max_amount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }


    public String getPrice_step() {
        return price_step;
    }

    public void setPrice_step(String price_step) {
        this.price_step = price_step;
    }
}
