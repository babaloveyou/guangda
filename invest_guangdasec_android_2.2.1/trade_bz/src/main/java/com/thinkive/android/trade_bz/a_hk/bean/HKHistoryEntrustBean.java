package com.thinkive.android.trade_bz.a_hk.bean;

import com.thinkive.android.trade_bz.a_stock.bean.BaseBean;
import com.thinkive.android.trade_bz.others.JsonKey;

/**
 * 港股通  历史委托（301608）
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/11/2
 */
public class HKHistoryEntrustBean extends BaseBean {
    /**
     * 委托标志（见数据字典)
     */
    @JsonKey("entrust_bs")
    private String entrust_bs="";
    /**
     * 业务名称
     */
    @JsonKey("entrust_name")
    private String entrust_name="";
    /**
     * 委托类别（见数字字典）
     */
    @JsonKey("entrust_type")
    private String entrust_type="";
    /**
     *  委托类别名称
     */
    @JsonKey("entrust_type_name")
    private String entrust_type_name="";
    /**
     *委托状态（见数据字典)
     */
    @JsonKey("entrust_state")
    private String entrust_state="";
    /**
     *委托状态名称
     */
    @JsonKey("entrust_state_name")
    private String entrust_state_name="";
    /**
     * 交易市场类别（见数据字典)
     */
    @JsonKey("exchange_type")
    private String exchange_type="";
    /**
     * 交易市场名称
     */
    @JsonKey("exchange_type_name")
    private String exchange_type_name="";
    /**
     *证券账号
     */
    @JsonKey("stock_account")
    private String stock_account="";
    /**
     * 委托日期
     */
    @JsonKey("entrust_date")
    private String entrust_date="";
    /**
     * 委托时间
     */
    @JsonKey("entrust_time")
    private String entrust_time="";
    /**
     * 证券代码
     */
    @JsonKey("stock_code")
    private String stock_code="";
    /**
     *证券名称
     */
    @JsonKey("stock_name")
    private String stock_name="";
    /**
     *申请编号
     */
    @JsonKey("report_no")
    private String report_no="";
    /**
     * 成交编号
     */
    @JsonKey("entrust_no")
    private String entrust_no="";
    /**
     * 委托价格
     */
    @JsonKey("entrust_price")
    private String entrust_price="";
    /**
     * 委托数量
     */
    @JsonKey("entrust_amount")
    private String entrust_amount="";
    /**
     *交易汇率
     */
    @JsonKey("exchange_rate")
    private String exchange_rate="";
    /**
     * 成交数目
     */
    @JsonKey("business_amount")
    private String business_amount="";
    /**
     * 最大价格等级
     */
    @JsonKey("max_price_levels")
    private String max_price_levels="";
    /**
     *订单时间类型
     */
    @JsonKey("trade_time_type")
    private String trade_time_type="";

    public HKHistoryEntrustBean() {

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

    public String getEntrust_state() {
        return entrust_state;
    }

    public void setEntrust_state(String entrust_state) {
        this.entrust_state = entrust_state;
    }

    public String getEntrust_state_name() {
        return entrust_state_name;
    }

    public void setEntrust_state_name(String entrust_state_name) {
        this.entrust_state_name = entrust_state_name;
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

    public String getEntrust_time() {
        return entrust_time;
    }

    public void setEntrust_time(String entrust_time) {
        this.entrust_time = entrust_time;
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

    public String getReport_no() {
        return report_no;
    }

    public void setReport_no(String report_no) {
        this.report_no = report_no;
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

    public String getExchange_rate() {
        return exchange_rate;
    }

    public void setExchange_rate(String exchange_rate) {
        this.exchange_rate = exchange_rate;
    }

    public String getBusiness_amount() {
        return business_amount;
    }

    public void setBusiness_amount(String business_amount) {
        this.business_amount = business_amount;
    }

    public String getMax_price_levels() {
        return max_price_levels;
    }

    public void setMax_price_levels(String max_price_levels) {
        this.max_price_levels = max_price_levels;
    }

    public String getTrade_time_type() {
        return trade_time_type;
    }

    public void setTrade_time_type(String trade_time_type) {
        this.trade_time_type = trade_time_type;
    }
}