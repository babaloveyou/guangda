package com.thinkive.android.trade_bz.a_option.bean;

import com.thinkive.android.trade_bz.a_stock.bean.BaseBean;
import com.thinkive.android.trade_bz.others.JsonKey;

/**
 * 可撤单查询（305021）
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/6/14
 */
public class OptionRevocationBean extends BaseBean {
    @JsonKey("init_date")
    private String init_date = ""; //交易日期
    @JsonKey("batch_no")
    private String batch_no = ""; //委托批号
    @JsonKey("entrust_no")
    private String entrust_no = ""; //委托编号
    @JsonKey("exchange_type")
    private String exchange_type = ""; //交易类别
    @JsonKey("exchange_type_name")
    private String exchange_type_name = ""; //交易类别名称
    @JsonKey("fund_account")
    private String fund_account = ""; //衍生品资金账户
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
    private String entrust_oc = ""; //开平仓方向（见数据字典)
    @JsonKey("entrust_oc_name")
    private String entrust_oc_name = ""; //开平仓方向名称
    @JsonKey("covered_flag")
    private String covered_flag = ""; //备兑标志（'1'-备兑）
    @JsonKey("covered_flag_name")
    private String covered_flag_name = ""; //备兑标志名称
    @JsonKey("opt_entrust_price")
    private String opt_entrust_price = ""; //委托价格
    @JsonKey("entrust_amount")
    private String entrust_amount = ""; //委托数量
    @JsonKey("business_amount")
    private String business_amount = ""; //成交数量
    @JsonKey("opt_business_price")
    private String opt_business_price = ""; //成交价格
    @JsonKey("business_balance")
    private String business_balance = ""; // 成交金额
    @JsonKey("report_no")
    private String report_no = ""; //申请编号
    @JsonKey("report_time")
    private String report_time = ""; //申报时间
    @JsonKey("entrust_type")
    private String entrust_type = ""; //委托类别
    @JsonKey("entrust_type_name")
    private String entrust_type_name = ""; //委托类别名称
    @JsonKey("entrust_status_name")
    private String entrust_status_name = ""; //委托状态名称
    @JsonKey("entrust_status")
    private String entrust_status = ""; //委托状态
    @JsonKey("entrust_time")
    private String entrust_time = ""; //委托时间
    @JsonKey("entrust_date")
    private String entrust_date = ""; //委托日期
    @JsonKey("entrust_prop")
    private String entrust_prop = ""; //委托属性
    @JsonKey("entrust_src")
    private String entrust_src = ""; //委托来源
    @JsonKey("trade_name")
    private String trade_name = ""; //订单名称
    @JsonKey("option_name")
    private String option_name = ""; //期权名称
    @JsonKey("cancel_info")
    private String cancel_info = ""; // 废单原因
    @JsonKey("withdraw_amount")
    private String withdraw_amount = ""; //撤单数量
    @JsonKey("withdraw_flag")
    private String withdraw_flag = ""; //撤单标志

    public OptionRevocationBean() {

    }

    public String getInit_date() {
        return init_date;
    }

    public void setInit_date(String init_date) {
        this.init_date = init_date;
    }

    public String getBatch_no() {
        return batch_no;
    }

    public void setBatch_no(String batch_no) {
        this.batch_no = batch_no;
    }

    public String getEntrust_no() {
        return entrust_no;
    }

    public void setEntrust_no(String entrust_no) {
        this.entrust_no = entrust_no;
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

    public String getOpt_entrust_price() {
        return opt_entrust_price;
    }

    public void setOpt_entrust_price(String opt_entrust_price) {
        this.opt_entrust_price = opt_entrust_price;
    }

    public String getEntrust_amount() {
        return entrust_amount;
    }

    public void setEntrust_amount(String entrust_amount) {
        this.entrust_amount = entrust_amount;
    }

    public String getBusiness_amount() {
        return business_amount;
    }

    public void setBusiness_amount(String business_amount) {
        this.business_amount = business_amount;
    }

    public String getOpt_business_price() {
        return opt_business_price;
    }

    public void setOpt_business_price(String opt_business_price) {
        this.opt_business_price = opt_business_price;
    }

    public String getBusiness_balance() {
        return business_balance;
    }

    public void setBusiness_balance(String business_balance) {
        this.business_balance = business_balance;
    }

    public String getReport_no() {
        return report_no;
    }

    public void setReport_no(String report_no) {
        this.report_no = report_no;
    }

    public String getReport_time() {
        return report_time;
    }

    public void setReport_time(String report_time) {
        this.report_time = report_time;
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

    public String getEntrust_status_name() {
        return entrust_status_name;
    }

    public void setEntrust_status_name(String entrust_status_name) {
        this.entrust_status_name = entrust_status_name;
    }

    public String getEntrust_status() {
        return entrust_status;
    }

    public void setEntrust_status(String entrust_status) {
        this.entrust_status = entrust_status;
    }

    public String getEntrust_time() {
        return entrust_time;
    }

    public void setEntrust_time(String entrust_time) {
        this.entrust_time = entrust_time;
    }

    public String getEntrust_date() {
        return entrust_date;
    }

    public void setEntrust_date(String entrust_date) {
        this.entrust_date = entrust_date;
    }

    public String getEntrust_prop() {
        return entrust_prop;
    }

    public void setEntrust_prop(String entrust_prop) {
        this.entrust_prop = entrust_prop;
    }

    public String getEntrust_src() {
        return entrust_src;
    }

    public void setEntrust_src(String entrust_src) {
        this.entrust_src = entrust_src;
    }

    public String getTrade_name() {
        return trade_name;
    }

    public void setTrade_name(String trade_name) {
        this.trade_name = trade_name;
    }

    public String getOption_name() {
        return option_name;
    }

    public void setOption_name(String option_name) {
        this.option_name = option_name;
    }

    public String getCancel_info() {
        return cancel_info;
    }

    public void setCancel_info(String cancel_info) {
        this.cancel_info = cancel_info;
    }

    public String getWithdraw_amount() {
        return withdraw_amount;
    }

    public void setWithdraw_amount(String withdraw_amount) {
        this.withdraw_amount = withdraw_amount;
    }

    public String getWithdraw_flag() {
        return withdraw_flag;
    }

    public void setWithdraw_flag(String withdraw_flag) {
        this.withdraw_flag = withdraw_flag;
    }
}
