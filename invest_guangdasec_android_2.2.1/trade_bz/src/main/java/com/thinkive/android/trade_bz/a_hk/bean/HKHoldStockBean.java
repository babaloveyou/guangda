package com.thinkive.android.trade_bz.a_hk.bean;

import com.thinkive.android.trade_bz.a_stock.bean.BaseBean;
import com.thinkive.android.trade_bz.others.JsonKey;

/**
 * 港股通  持仓查询（ 301605）
 *
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/11/2
 */
public class HKHoldStockBean extends BaseBean {
    /**
     * 证券代码
     */
    @JsonKey("stock_code")
    private String stock_code          = "";
    /**
     * 证券名称
     */
    @JsonKey("stock_name")
    private String stock_name          = "";
    /**
     * 交易市场类别（见数据字典)
     */
    @JsonKey("exchange_type")
    private String exchange_type       = "";
    /**
     * 交易市场名称
     */
    @JsonKey("exchange_type_name")
    private String exchange_type_name  = "";
    /**
     * 证券账号
     */
    @JsonKey("stock_account")
    private String stock_account       = "";
    /**
     * 最新价
     */
    @JsonKey("last_price")
    private String last_price          = "";
    /**
     * 成本价
     */
    @JsonKey("cost_price")
    private String cost_price          = "";
    /**
     * 证券市值
     */
    @JsonKey("market_value")
    private String market_value        = "";
    /**
     * 浮动盈亏
     */
    @JsonKey("float_yk")
    private String float_yk            = "";
    /**
     * 浮动盈亏%
     */
    @JsonKey("float_yk_per")
    private String float_yk_per        = "";
    /**
     * 持仓成本（累积买+当日买-累积卖-当日卖）
     */
    @JsonKey("cost_balance")
    private String cost_balance        = "";
    /**
     * 可用数量
     */
    @JsonKey("enable_amount")
    private String enable_amount       = "";
    /**
     * 当前数量
     */
    @JsonKey("current_amount")
    private String current_amount      = "";
    /**
     * 持仓数量
     */
    @JsonKey("hold_amount")
    private String hold_amount         = "";
    /**
     * 回报买入数量
     */
    @JsonKey("real_buy_amount")
    private String real_buy_amount     = "";
    /**
     * 回报卖出数量
     */
    @JsonKey("real_sell_amount")
    private String real_sell_amount    = "";
    /**
     * 未回买入数量
     */
    @JsonKey("uncome_buy_amount")
    private String uncome_buy_amount   = "";
    /**
     * 未回卖出数量
     */
    @JsonKey("uncome_sell_amount")
    private String uncome_sell_amount  = "";
    /**
     * 委托卖出数量
     */
    @JsonKey("entrust_sell_amount")
    private String entrust_sell_amount = "";
    /**
     * 盈亏金额
     */
    @JsonKey("income_balance")
    private String income_balance      = "";

    /**
     * 冻结数量
     */
    @JsonKey("freeze_amount")
    private String freeze_amount   = "";
    /**
     * 币种类别名称
     */
    @JsonKey("money_type_name")
    private String money_type_name = "";

    public String getFreeze_amount() {
        return freeze_amount;
    }

    public void setFreeze_amount(String freeze_amount) {
        this.freeze_amount = freeze_amount;
    }

    public String getMoney_type_name() {
        return money_type_name;
    }

    public void setMoney_type_name(String money_type_name) {
        this.money_type_name = money_type_name;
    }

    public HKHoldStockBean() {

    }

    public String getStock_code() {
        return stock_code;
    }

    public void setStock_code(String stock_code) {
        this.stock_code = stock_code;
    }

    public String getStock_name() {
        return stock_name;
    }

    public void setStock_name(String stock_name) {
        this.stock_name = stock_name;
    }

    public String getExchange_type() {
        return exchange_type;
    }

    public void setExchange_type(String exchange_type) {
        this.exchange_type = exchange_type;
    }

    public String getExchange_type_name() {
        return exchange_type_name;
    }

    public void setExchange_type_name(String exchange_type_name) {
        this.exchange_type_name = exchange_type_name;
    }

    public String getStock_account() {
        return stock_account;
    }

    public void setStock_account(String stock_account) {
        this.stock_account = stock_account;
    }

    public String getLast_price() {
        return last_price;
    }

    public void setLast_price(String last_price) {
        this.last_price = last_price;
    }

    public String getCost_price() {
        return cost_price;
    }

    public void setCost_price(String cost_price) {
        this.cost_price = cost_price;
    }

    public String getMarket_value() {
        return market_value;
    }

    public void setMarket_value(String market_value) {
        this.market_value = market_value;
    }

    public String getFloat_yk() {
        return float_yk;
    }

    public void setFloat_yk(String float_yk) {
        this.float_yk = float_yk;
    }

    public String getFloat_yk_per() {
        return float_yk_per;
    }

    public void setFloat_yk_per(String float_yk_per) {
        this.float_yk_per = float_yk_per;
    }

    public String getCost_balance() {
        return cost_balance;
    }

    public void setCost_balance(String cost_balance) {
        this.cost_balance = cost_balance;
    }

    public String getEnable_amount() {
        return enable_amount;
    }

    public void setEnable_amount(String enable_amount) {
        this.enable_amount = enable_amount;
    }

    public String getCurrent_amount() {
        return current_amount;
    }

    public void setCurrent_amount(String current_amount) {
        this.current_amount = current_amount;
    }

    public String getHold_amount() {
        return hold_amount;
    }

    public void setHold_amount(String hold_amount) {
        this.hold_amount = hold_amount;
    }

    public String getReal_buy_amount() {
        return real_buy_amount;
    }

    public void setReal_buy_amount(String real_buy_amount) {
        this.real_buy_amount = real_buy_amount;
    }

    public String getReal_sell_amount() {
        return real_sell_amount;
    }

    public void setReal_sell_amount(String real_sell_amount) {
        this.real_sell_amount = real_sell_amount;
    }

    public String getUncome_buy_amount() {
        return uncome_buy_amount;
    }

    public void setUncome_buy_amount(String uncome_buy_amount) {
        this.uncome_buy_amount = uncome_buy_amount;
    }

    public String getUncome_sell_amount() {
        return uncome_sell_amount;
    }

    public void setUncome_sell_amount(String uncome_sell_amount) {
        this.uncome_sell_amount = uncome_sell_amount;
    }

    public String getEntrust_sell_amount() {
        return entrust_sell_amount;
    }

    public void setEntrust_sell_amount(String entrust_sell_amount) {
        this.entrust_sell_amount = entrust_sell_amount;
    }

    public String getIncome_balance() {
        return income_balance;
    }

    public void setIncome_balance(String income_balance) {
        this.income_balance = income_balance;
    }
}
