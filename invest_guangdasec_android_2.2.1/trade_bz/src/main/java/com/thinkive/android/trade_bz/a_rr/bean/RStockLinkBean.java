package com.thinkive.android.trade_bz.a_rr.bean;

import com.thinkive.android.trade_bz.a_stock.bean.BaseBean;
import com.thinkive.android.trade_bz.others.JsonKey;

/**
 *  融资融券--证券买卖联动实体类（303000）
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/1/21
 */
public class RStockLinkBean extends BaseBean {
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
     *交易市场类别
     */
    @JsonKey("exchange_type")
    private String exchange_type = "";
    /**
     *证券类别（见数据字典)
     */
    @JsonKey("stock_type")
    private String stock_type = "";
    /**
     * 涨停价
     */
    @JsonKey("up_limit")
    private String up_limit = "";
    /**
     * 跌停价
     */
    @JsonKey("down_limit")
    private String down_limit = "";
    /**
     * 可用资金
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
     * 当前价格
     */
    @JsonKey("price")
    private String price = "";
    /**
     * 每手股数
     */
    @JsonKey("buy_unit")
    private String buy_unit = "";
    /**
     *价差
     */
    @JsonKey("step_price")
    private String step_price = "";
    /**
     *还券数量
     */
    @JsonKey("repay_amount")
    private String repay_amount = "";
    /**
     * 市场
     */
    private String market = "";

    public RStockLinkBean() {

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

    public String getRepay_amount() {
        return repay_amount;
    }

    public void setRepay_amount(String repay_amount) {
        this.repay_amount = repay_amount;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }
}
