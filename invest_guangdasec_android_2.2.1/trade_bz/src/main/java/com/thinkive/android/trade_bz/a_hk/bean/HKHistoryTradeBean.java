package com.thinkive.android.trade_bz.a_hk.bean;

import com.thinkive.android.trade_bz.a_stock.bean.BaseBean;
import com.thinkive.android.trade_bz.others.JsonKey;

/**
 * 港股通  历史成交（301609）
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/11/2
 */
public class HKHistoryTradeBean extends BaseBean {
    /**
     * 委托标志（见数据字典)
     */
    @JsonKey("entrust_bs")
    private String entrust_bs="";
    /**
     *  业务名称
     */
    @JsonKey("entrust_name")
    private String entrust_name="";
    /**
     *委托类别（见数字字典）
     */
    @JsonKey("entrust_type")
    private String entrust_type="";
    /**
     * 委托类别名称
     */
    @JsonKey("entrust_type_name")
    private String entrust_type_name="";
    /**
     *成交状态（见数据字典)
     */
    @JsonKey("real_status")
    private String real_status="";
    /**
     * 成交状态名称
     */
    @JsonKey("real_status_name")
    private String real_status_name="";
    /**
     *交易市场类别（见数据字典)
     */
    @JsonKey("exchange_type")
    private String exchange_type="";
    /**
     *交易市场名称
     */
    @JsonKey("exchange_type_name")
    private String exchange_type_name="";
    /**
     * 证券账号
     */
    @JsonKey("stock_account")
    private String stock_account="";
    /**
     *委托日期
     */
    @JsonKey("entrust_date")
    private String entrust_date="";
    /**
     *成交日期
     */
    @JsonKey("business_date")
    private String business_date="";
    /**
     *成交时间
     */
    @JsonKey("business_time")
    private String business_time="";
    /**
     *证券代码
     */
    @JsonKey("stock_code")
    private String stock_code="";
    /**
     *证券名称
     */
    @JsonKey("stock_name")
    private String stock_name="";
    /**
     *委托编号
     */
    @JsonKey("entrust_no")
    private String entrust_no="";
    /**
     * 委托价格
     */
    @JsonKey("entrust_price")
    private String entrust_price="";
    /**
     *委托数量
     */
    @JsonKey("entrust_amount")
    private String entrust_amount="";
    /**
     *成交编号
     */
    @JsonKey("report_no")
    private String report_no="";
    /**
     * 成交价格
     */
    @JsonKey("business_price")
    private String business_price="";
    /**
     * 成交数目
     */
    @JsonKey("business_amount")
    private String business_amount="";
    /**
     *成交金额
     */
    @JsonKey("business_balance")
    private String business_balance="";
    /**
     *交易汇率
     */
    @JsonKey("exchange_rate")
    private String exchange_rate="";

    public HKHistoryTradeBean() {

    }

    public String getEntrust_bs() {
        return entrust_bs;
    }

    public void setEntrust_bs(String entrust_bs) {
        this.entrust_bs = entrust_bs;
    }

    public String getEntrust_name() {
        return entrust_name;
    }

    public void setEntrust_name(String entrust_name) {
        this.entrust_name = entrust_name;
    }

    public String getEntrust_type() {
        return entrust_type;
    }

    public void setEntrust_type(String entrust_type) {
        this.entrust_type = entrust_type;
    }

    public String getEntrust_type_name() {
        return entrust_type_name;
    }

    public void setEntrust_type_name(String entrust_type_name) {
        this.entrust_type_name = entrust_type_name;
    }

    public String getReal_status() {
        return real_status;
    }

    public void setReal_status(String real_status) {
        this.real_status = real_status;
    }

    public String getReal_status_name() {
        return real_status_name;
    }

    public void setReal_status_name(String real_status_name) {
        this.real_status_name = real_status_name;
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

    public String getEntrust_date() {
        return entrust_date;
    }

    public void setEntrust_date(String entrust_date) {
        this.entrust_date = entrust_date;
    }

    public String getBusiness_date() {
        return business_date;
    }

    public void setBusiness_date(String business_date) {
        this.business_date = business_date;
    }

    public String getBusiness_time() {
        return business_time;
    }

    public void setBusiness_time(String business_time) {
        this.business_time = business_time;
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

    public String getEntrust_no() {
        return entrust_no;
    }

    public void setEntrust_no(String entrust_no) {
        this.entrust_no = entrust_no;
    }

    public String getEntrust_price() {
        return entrust_price;
    }

    public void setEntrust_price(String entrust_price) {
        this.entrust_price = entrust_price;
    }

    public String getEntrust_amount() {
        return entrust_amount;
    }

    public void setEntrust_amount(String entrust_amount) {
        this.entrust_amount = entrust_amount;
    }

    public String getReport_no() {
        return report_no;
    }

    public void setReport_no(String report_no) {
        this.report_no = report_no;
    }

    public String getBusiness_price() {
        return business_price;
    }

    public void setBusiness_price(String business_price) {
        this.business_price = business_price;
    }

    public String getBusiness_amount() {
        return business_amount;
    }

    public void setBusiness_amount(String business_amount) {
        this.business_amount = business_amount;
    }

    public String getBusiness_balance() {
        return business_balance;
    }

    public void setBusiness_balance(String business_balance) {
        this.business_balance = business_balance;
    }

    public String getExchange_rate() {
        return exchange_rate;
    }

    public void setExchange_rate(String exchange_rate) {
        this.exchange_rate = exchange_rate;
    }
}