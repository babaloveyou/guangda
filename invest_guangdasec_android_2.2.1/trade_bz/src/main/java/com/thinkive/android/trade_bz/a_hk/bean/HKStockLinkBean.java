package com.thinkive.android.trade_bz.a_hk.bean;

import com.thinkive.android.trade_bz.a_stock.bean.BaseBean;
import com.thinkive.android.trade_bz.others.JsonKey;

/**
 *  港股通 联动查询（ 301602）
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/11/18
 */
public class HKStockLinkBean extends BaseBean {
    /**
     * 证券代码
     */
    @JsonKey("stock_code")
    private String stock_code = "";
    /**
     *证券名称
     */
    @JsonKey("stock_name")
    private String stock_name = "";
    /**
     *交易市场类别（见数据字典)
     */
    @JsonKey("exchange_type")
    private String exchange_type = "";
    /**
     * 证券类别（见数据字典)
     */
    @JsonKey("stock_type")
    private String stock_type = "";
    /**
     *涨停价
     */
    @JsonKey("up_limit")
    private String up_limit = "";
    /**
     * 跌停价
     */
    @JsonKey("down_limit")
    private String down_limit = "";
    /**
     *可用资金
     */
    @JsonKey("enable_balance")
    private String enable_balance = "";
    /**
     * 证券账号
     */
    @JsonKey("stock_account")
    private String stock_account = "";
    /**
     *证券最大可买/卖数量
     */
    @JsonKey("stock_max_amount")
    private String stock_max_amount = "";
    /**
     *当前价格
     */
    @JsonKey("price")
    private String price = "";
    /**
     *每手股数
     */
    @JsonKey("buy_unit")
    private String buy_unit = "";
    /**
     *价差（根据价格不同，公式计算）
     */
    @JsonKey("step_price")
    private String step_price = "";
    /**
     *上限价差
     */
    @JsonKey("up")
    private String up = "";
    /**
     *下限价差
     */
    @JsonKey("down")
    private String down = "";
    /**
     *交易最高数量
     */
    @JsonKey("high_amount")
    private String high_amount = "";
    /**
     *交易最低数量
     */
    @JsonKey("low_amount")
    private String low_amount = "";
    /**
     * 买入汇率
     */
    @JsonKey("buy_exchange_rate")
    private String buy_exchange_rate = "";
    /**
     *卖出汇率
     */
    @JsonKey("sell_exchange_rate")
    private String sell_exchange_rate = "";
    /**
     * 证券市场
     */
    @JsonKey("market")
    protected String market = "";

    public HKStockLinkBean() {

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

    public String getStock_type() {
        return stock_type;
    }

    public void setStock_type(String stock_type) {
        this.stock_type = stock_type;
    }

    public String getUp_limit() {
        return up_limit;
    }

    public void setUp_limit(String up_limit) {
        this.up_limit = up_limit;
    }

    public String getDown_limit() {
        return down_limit;
    }

    public void setDown_limit(String down_limit) {
        this.down_limit = down_limit;
    }

    public String getEnable_balance() {
        return enable_balance;
    }

    public void setEnable_balance(String enable_balance) {
        this.enable_balance = enable_balance;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getBuy_unit() {
        return buy_unit;
    }

    public void setBuy_unit(String buy_unit) {
        this.buy_unit = buy_unit;
    }

    public String getStep_price() {
        return step_price;
    }

    public void setStep_price(String step_price) {
        this.step_price = step_price;
    }

    public String getUp() {
        return up;
    }

    public void setUp(String up) {
        this.up = up;
    }

    public String getDown() {
        return down;
    }

    public void setDown(String down) {
        this.down = down;
    }

    public String getHigh_amount() {
        return high_amount;
    }

    public void setHigh_amount(String high_amount) {
        this.high_amount = high_amount;
    }

    public String getLow_amount() {
        return low_amount;
    }

    public void setLow_amount(String low_amount) {
        this.low_amount = low_amount;
    }

    public String getBuy_exchange_rate() {
        return buy_exchange_rate;
    }

    public void setBuy_exchange_rate(String buy_exchange_rate) {
        this.buy_exchange_rate = buy_exchange_rate;
    }

    public String getSell_exchange_rate() {
        return sell_exchange_rate;
    }

    public void setSell_exchange_rate(String sell_exchange_rate) {
        this.sell_exchange_rate = sell_exchange_rate;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

}
