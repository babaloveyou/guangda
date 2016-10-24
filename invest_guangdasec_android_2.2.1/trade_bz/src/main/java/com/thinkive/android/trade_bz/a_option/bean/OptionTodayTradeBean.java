package com.thinkive.android.trade_bz.a_option.bean;

import com.thinkive.android.trade_bz.a_stock.bean.BaseBean;
import com.thinkive.android.trade_bz.others.JsonKey;

/**
 * 当日成交（305009）
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/6/14
 */
public class OptionTodayTradeBean extends BaseBean {
    @JsonKey("init_date")
    private String init_date = ""; //交易日期
    @JsonKey("serial_no")
    private String serial_no = ""; // 流水序号
    @JsonKey("exchange_type")
    private String exchange_type = ""; //  交易类别
    @JsonKey("exchange_type_name")
    private String exchange_type_name = ""; // 交易类别名称
    @JsonKey("fund_account")
    private String fund_account = ""; // 衍生品资金账户
    @JsonKey("option_account")
    private String option_account = ""; //衍生品合约账户
    @JsonKey("option_code")
    private String option_code = ""; //期权合约编码
    @JsonKey("optcontract_id")
    private String optcontract_id = ""; //合约交易代码
    @JsonKey("stock_code")
    private String stock_code = ""; //证券代码
    @JsonKey("entrust_bs")
    private String entrust_bs = ""; //买卖方向
    @JsonKey("entrust_bs_name")
    private String entrust_bs_name = ""; //买卖方向名称
    @JsonKey("entrust_oc")
    private String entrust_oc = ""; //  开平仓方向（见数据字典)
    @JsonKey("entrust_oc_name")
    private String entrust_oc_name = ""; //开平仓方向名称
    @JsonKey("covered_flag")
    private String covered_flag = ""; // 备兑标志（'1'-备兑）
    @JsonKey("covered_flag_name")
    private String covered_flag_name = ""; // 备兑标志名称
    @JsonKey("opt_business_price")
    private String opt_business_price = ""; //  成交价格
    @JsonKey("business_amount")
    private String business_amount = ""; //成交数量
    @JsonKey("business_time")
    private String business_time = ""; // 成交时间
    @JsonKey("real_type")
    private String real_type = ""; // 成交类别（'0'-买卖 '2'-撤单）
    @JsonKey("real_type_name")
    private String real_type_name = ""; // 成交类别名称
    @JsonKey("real_status")
    private String real_status = ""; // 成交状态（'0'-成交 '2'-废单 '4'-确认）
    @JsonKey("real_status_name")
    private String real_status_name = ""; // 成交状态名称
    @JsonKey("business_times")
    private String business_times = ""; //  分笔成交笔数
    @JsonKey("entrust_no")
    private String entrust_no = ""; // 委托编号
    @JsonKey("business_balance")
    private String business_balance = ""; // 成交金额
    @JsonKey("option_name")
    private String option_name = ""; // 期权名称
    @JsonKey("trade_name")
    private String trade_name = ""; //订单名称
    @JsonKey("report_no")
    private String report_no = ""; //申请编号
    @JsonKey("entrust_prop")
    private String entrust_prop = ""; //  委托属性
    @JsonKey("business_id")
    private String business_id = ""; //  成交编号
    @JsonKey("entrust_date")
    private String entrust_date = ""; // 委托日期

    public OptionTodayTradeBean() {

    }

    public String getInit_date() {
        return init_date;
    }

    public void setInit_date(String init_date) {
        this.init_date = init_date;
    }

    public String getSerial_no() {
        return serial_no;
    }

    public void setSerial_no(String serial_no) {
        this.serial_no = serial_no;
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

    public String getFund_account() {
        return fund_account;
    }

    public void setFund_account(String fund_account) {
        this.fund_account = fund_account;
    }

    public String getOption_account() {
        return option_account;
    }

    public void setOption_account(String option_account) {
        this.option_account = option_account;
    }

    public String getOption_code() {
        return option_code;
    }

    public void setOption_code(String option_code) {
        this.option_code = option_code;
    }

    public String getOptcontract_id() {
        return optcontract_id;
    }

    public void setOptcontract_id(String optcontract_id) {
        this.optcontract_id = optcontract_id;
    }

    public String getStock_code() {
        return stock_code;
    }

    public void setStock_code(String stock_code) {
        this.stock_code = stock_code;
    }

    public String getEntrust_bs() {
        return entrust_bs;
    }

    public void setEntrust_bs(String entrust_bs) {
        this.entrust_bs = entrust_bs;
    }

    public String getEntrust_bs_name() {
        return entrust_bs_name;
    }

    public void setEntrust_bs_name(String entrust_bs_name) {
        this.entrust_bs_name = entrust_bs_name;
    }

    public String getEntrust_oc() {
        return entrust_oc;
    }

    public void setEntrust_oc(String entrust_oc) {
        this.entrust_oc = entrust_oc;
    }

    public String getEntrust_oc_name() {
        return entrust_oc_name;
    }

    public void setEntrust_oc_name(String entrust_oc_name) {
        this.entrust_oc_name = entrust_oc_name;
    }

    public String getCovered_flag() {
        return covered_flag;
    }

    public void setCovered_flag(String covered_flag) {
        this.covered_flag = covered_flag;
    }

    public String getCovered_flag_name() {
        return covered_flag_name;
    }

    public void setCovered_flag_name(String covered_flag_name) {
        this.covered_flag_name = covered_flag_name;
    }

    public String getOpt_business_price() {
        return opt_business_price;
    }

    public void setOpt_business_price(String opt_business_price) {
        this.opt_business_price = opt_business_price;
    }

    public String getBusiness_amount() {
        return business_amount;
    }

    public void setBusiness_amount(String business_amount) {
        this.business_amount = business_amount;
    }

    public String getBusiness_time() {
        return business_time;
    }

    public void setBusiness_time(String business_time) {
        this.business_time = business_time;
    }

    public String getReal_type() {
        return real_type;
    }

    public void setReal_type(String real_type) {
        this.real_type = real_type;
    }

    public String getReal_type_name() {
        return real_type_name;
    }

    public void setReal_type_name(String real_type_name) {
        this.real_type_name = real_type_name;
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

    public String getBusiness_times() {
        return business_times;
    }

    public void setBusiness_times(String business_times) {
        this.business_times = business_times;
    }

    public String getEntrust_no() {
        return entrust_no;
    }

    public void setEntrust_no(String entrust_no) {
        this.entrust_no = entrust_no;
    }

    public String getBusiness_balance() {
        return business_balance;
    }

    public void setBusiness_balance(String business_balance) {
        this.business_balance = business_balance;
    }

    public String getOption_name() {
        return option_name;
    }

    public void setOption_name(String option_name) {
        this.option_name = option_name;
    }

    public String getTrade_name() {
        return trade_name;
    }

    public void setTrade_name(String trade_name) {
        this.trade_name = trade_name;
    }

    public String getReport_no() {
        return report_no;
    }

    public void setReport_no(String report_no) {
        this.report_no = report_no;
    }

    public String getEntrust_prop() {
        return entrust_prop;
    }

    public void setEntrust_prop(String entrust_prop) {
        this.entrust_prop = entrust_prop;
    }

    public String getBusiness_id() {
        return business_id;
    }

    public void setBusiness_id(String business_id) {
        this.business_id = business_id;
    }

    public String getEntrust_date() {
        return entrust_date;
    }

    public void setEntrust_date(String entrust_date) {
        this.entrust_date = entrust_date;
    }
}