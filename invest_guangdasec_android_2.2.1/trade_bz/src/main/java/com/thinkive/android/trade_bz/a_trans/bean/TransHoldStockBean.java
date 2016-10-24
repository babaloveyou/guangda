package com.thinkive.android.trade_bz.a_trans.bean;


import com.thinkive.android.trade_bz.a_stock.bean.BaseBean;
import com.thinkive.android.trade_bz.others.JsonKey;

/**
 * 股转持仓数据（301702）
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/6/23
 */
public class TransHoldStockBean extends BaseBean {
    /**
     *  证券名称
     */
    @JsonKey("stock_name")
    private String stock_name = "";
    /**
     * 证券代码
     */
    @JsonKey("stock_code")
    private String stock_code = "";
    /**
     * 证券市值
     */
    @JsonKey("market_value")
    private String market_value = "";
    /**
     *浮动盈亏
     */
    @JsonKey("float_yk")
    private String float_yk = "";
    /**
     *浮动盈亏%
     */
    @JsonKey("float_yk_per")
    private String float_yk_per = "";
    /**
     *持仓数量
     */
    @JsonKey("cost_amount")
    private String cost_amount = "";
    /**
     *可用数量
     */
    @JsonKey("enable_amount")
    private String enable_amount = "";
    /**
     * 持仓成本
     */
    @JsonKey("cost_balance")
    private String cost_balance = "";
    /**
     *最新价
     */
    @JsonKey("last_price")
    private String last_price = "";
    /**
     *交易市场类别
     */
    @JsonKey("exchange_type")
    private String exchange_type = "";
    /**
     *交易市场名称
     */
    @JsonKey("exchange_type_name")
    private String exchange_type_name = "";
    /**
     *证券账号
     */
    @JsonKey("stock_account")
    private String stock_account = "";
    /**
     *成本价
     */
    @JsonKey("cost_price")
    private String cost_price = "";
    /**
     *市场
     */
    @JsonKey("market")
    private String market = "";

    public TransHoldStockBean() {

    }
    public String getStock_name() {
        return stock_name;
    }

    public String getLast_price() {
        return last_price;
    }

    public String getCost_amount() {
        return cost_amount;
    }

    public String getFloat_yk() {
        return float_yk;
    }

    public String getMarket_value() {
        return market_value;
    }

    public String getFloat_yk_per() {
        return float_yk_per;
    }

    public String getEnable_amount() {
        return enable_amount;
    }

    public String getCost_balance() {
        return cost_balance;
    }

    public void setStock_name(String stock_name) {
        this.stock_name = stock_name;
    }

    public void setLast_price(String last_price) {
        this.last_price = last_price;
    }

    public void setCost_balance(String cost_balance) {
        this.cost_balance = cost_balance;
    }

    public void setEnable_amount(String enable_amount) {
        this.enable_amount = enable_amount;
    }

    public void setCost_amount(String cost_amount) {
        this.cost_amount = cost_amount;
    }

    public void setFloat_yk_per(String float_yk_per) {
        this.float_yk_per = float_yk_per;
    }

    public void setFloat_yk(String float_yk) {
        this.float_yk = float_yk;
    }

    public void setMarket_value(String market_value) {
        this.market_value = market_value;
    }

    public String getStock_code() {
        return stock_code;
    }

    public void setStock_code(String stock_code) {
        this.stock_code = stock_code;
    }

    public String getCost_price() {
        return cost_price;
    }

    public void setCost_price(String cost_price) {
        this.cost_price = cost_price;
    }

    public String getExchange_type_name() {
        return exchange_type_name;
    }

    public void setExchange_type_name(String exchange_type_name) {
        this.exchange_type_name = exchange_type_name;
    }

    public String getExchange_type() {
        return exchange_type;
    }

    public void setExchange_type(String exchange_type) {
        this.exchange_type = exchange_type;
    }

    public String getStock_account() {
        return stock_account;
    }

    public void setStock_account(String stock_account) {
        this.stock_account = stock_account;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }
}
