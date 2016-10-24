package com.thinkive.android.trade_bz.a_option.bean;

import com.thinkive.android.trade_bz.a_stock.bean.BaseBean;
import com.thinkive.android.trade_bz.others.JsonKey;

/**
 * 历史成交（305010）
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/6/14
 */
public class OptionHistoryTradeBean extends BaseBean {
    @JsonKey("init_date")
    private String init_date = ""; // 发生日期
    @JsonKey("serial_no")
    private String serial_no = ""; //流水序号
    @JsonKey("exchange_type")
    private String exchange_type = ""; // 交易类别
    @JsonKey("exchange_type_name")
    private String exchange_type_name = ""; // 交易类别名称
    @JsonKey("option_account")
    private String option_account = ""; //衍生品合约账户
    @JsonKey("opthold_type")
    private String opthold_type = ""; // 期权持仓类别
    @JsonKey("option_code")
    private String option_code = ""; //  期权合约编码
    @JsonKey("optcontract_id")
    private String optcontract_id = ""; //合约交易代码
    @JsonKey("option_name")
    private String option_name = ""; //期权名称
    @JsonKey("stock_code")
    private String stock_code = ""; //证券代码
    @JsonKey("stock_name")
    private String stock_name = ""; //证券名称
    @JsonKey("entrust_bs")
    private String entrust_bs = ""; //买卖方向
    @JsonKey("entrust_oc")
    private String entrust_oc = ""; // 开平仓方向
    @JsonKey("covered_flag")
    private String covered_flag = ""; //备兑标志
    @JsonKey("opt_business_price")
    private String opt_business_price = ""; //  成交价格
    @JsonKey("business_time")
    private String business_time = ""; //成交时间
    @JsonKey("business_status")
    private String business_status = ""; //业务状态（0-‘成交’2-‘废单’4-‘确认’）
    @JsonKey("business_times")
    private String business_times = ""; //分笔成交笔数
    @JsonKey("entrust_no")
    private String entrust_no = ""; // 委托编号
    @JsonKey("report_no")
    private String report_no = ""; //申请编号
    @JsonKey("occur_amount")
    private String occur_amount = ""; //发生数量
    @JsonKey("post_balance")
    private String post_balance = ""; // 后资金额
    @JsonKey("business_balance")
    private String business_balance = ""; //成交金额
    @JsonKey("occur_balance")
    private String occur_balance = ""; //发生金额
    @JsonKey("post_amount")
    private String post_amount = ""; // 后证券额
    @JsonKey("fare0")
    private String fare0 = ""; //佣金
    @JsonKey("fare1")
    private String fare1 = ""; // 印花税
    @JsonKey("fare2")
    private String fare2 = ""; // 过户费
    @JsonKey("other_fare")
    private String other_fare = ""; // 其他费用
    @JsonKey("remark")
    private String remark = ""; //备注
    @JsonKey("opthold_type_name")
    private String opthold_type_name = ""; //期权持仓类别名称
    @JsonKey("entrust_bs_name")
    private String entrust_bs_name = ""; //委托标志名称
    @JsonKey("entrust_oc_name")
    private String entrust_oc_name = ""; //开平仓方向名称
    @JsonKey("covered_flag_name")
    private String covered_flag_name = ""; //备兑标志名称
    @JsonKey("real_type")
    private String real_type = ""; //成交类别（'0'-买卖 '2'-撤单）
    @JsonKey("real_type_name")
    private String real_type_name = ""; //成交类别名称

    public OptionHistoryTradeBean() {

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

    public String getOption_account() {
        return option_account;
    }

    public void setOption_account(String option_account) {
        this.option_account = option_account;
    }

    public String getOpthold_type() {
        return opthold_type;
    }

    public void setOpthold_type(String opthold_type) {
        this.opthold_type = opthold_type;
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

    public String getOption_name() {
        return option_name;
    }

    public void setOption_name(String option_name) {
        this.option_name = option_name;
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

    public String getEntrust_bs() {
        return entrust_bs;
    }

    public void setEntrust_bs(String entrust_bs) {
        this.entrust_bs = entrust_bs;
    }

    public String getEntrust_oc() {
        return entrust_oc;
    }

    public void setEntrust_oc(String entrust_oc) {
        this.entrust_oc = entrust_oc;
    }

    public String getCovered_flag() {
        return covered_flag;
    }

    public void setCovered_flag(String covered_flag) {
        this.covered_flag = covered_flag;
    }

    public String getOpt_business_price() {
        return opt_business_price;
    }

    public void setOpt_business_price(String opt_business_price) {
        this.opt_business_price = opt_business_price;
    }

    public String getBusiness_time() {
        return business_time;
    }

    public void setBusiness_time(String business_time) {
        this.business_time = business_time;
    }

    public String getBusiness_status() {
        return business_status;
    }

    public void setBusiness_status(String business_status) {
        this.business_status = business_status;
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

    public String getReport_no() {
        return report_no;
    }

    public void setReport_no(String report_no) {
        this.report_no = report_no;
    }

    public String getOccur_amount() {
        return occur_amount;
    }

    public void setOccur_amount(String occur_amount) {
        this.occur_amount = occur_amount;
    }

    public String getPost_balance() {
        return post_balance;
    }

    public void setPost_balance(String post_balance) {
        this.post_balance = post_balance;
    }

    public String getBusiness_balance() {
        return business_balance;
    }

    public void setBusiness_balance(String business_balance) {
        this.business_balance = business_balance;
    }

    public String getOccur_balance() {
        return occur_balance;
    }

    public void setOccur_balance(String occur_balance) {
        this.occur_balance = occur_balance;
    }

    public String getPost_amount() {
        return post_amount;
    }

    public void setPost_amount(String post_amount) {
        this.post_amount = post_amount;
    }

    public String getFare0() {
        return fare0;
    }

    public void setFare0(String fare0) {
        this.fare0 = fare0;
    }

    public String getFare1() {
        return fare1;
    }

    public void setFare1(String fare1) {
        this.fare1 = fare1;
    }

    public String getFare2() {
        return fare2;
    }

    public void setFare2(String fare2) {
        this.fare2 = fare2;
    }

    public String getOther_fare() {
        return other_fare;
    }

    public void setOther_fare(String other_fare) {
        this.other_fare = other_fare;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getOpthold_type_name() {
        return opthold_type_name;
    }

    public void setOpthold_type_name(String opthold_type_name) {
        this.opthold_type_name = opthold_type_name;
    }

    public String getEntrust_bs_name() {
        return entrust_bs_name;
    }

    public void setEntrust_bs_name(String entrust_bs_name) {
        this.entrust_bs_name = entrust_bs_name;
    }

    public String getEntrust_oc_name() {
        return entrust_oc_name;
    }

    public void setEntrust_oc_name(String entrust_oc_name) {
        this.entrust_oc_name = entrust_oc_name;
    }

    public String getCovered_flag_name() {
        return covered_flag_name;
    }

    public void setCovered_flag_name(String covered_flag_name) {
        this.covered_flag_name = covered_flag_name;
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
}
