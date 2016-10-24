package com.thinkive.android.trade_bz.a_option.bean;

import com.thinkive.android.trade_bz.a_stock.bean.BaseBean;
import com.thinkive.android.trade_bz.others.JsonKey;

/**
 * 历史对账单（305017）
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/6/14
 */
public class OptionHistoryListBean extends BaseBean {
    @JsonKey("init_date")
    private String init_date = ""; //发生日期
    @JsonKey("business_flag")
    private String business_flag = ""; // 业务标志
    @JsonKey("business_type")
    private String business_type = ""; //业务类型
    @JsonKey("business_name")
    private String business_name = ""; // 业务名称
    @JsonKey("fund_account")
    private String fund_account = ""; // 衍生品资金账户
    @JsonKey("bank_no")
    private String bank_no = ""; //  银行代码
    @JsonKey("bank_name")
    private String bank_name = ""; //银行名称
    @JsonKey("money_type_name")
    private String money_type_name = ""; // 币种类别名称
    @JsonKey("money_type")
    private String money_type = ""; //币种类别
    @JsonKey("business_balance")
    private String business_balance = ""; // 成交金额
    @JsonKey("clear_balance")
    private String clear_balance = ""; // 清算金额
    @JsonKey("exchange_type")
    private String exchange_type = ""; // 交易类别
    @JsonKey("exchange_type_name")
    private String exchange_type_name = ""; // 交易类别名称
    @JsonKey("option_account")
    private String option_account = ""; // 衍生品合约账户
    @JsonKey("option_code")
    private String option_code = ""; // 期权合约编码
    @JsonKey("option_name")
    private String option_name = ""; // 期权名称
    @JsonKey("business_amount")
    private String business_amount = ""; //成交数量
    @JsonKey("standard_fare0")
    private String standard_fare0 = ""; //标准佣金
    @JsonKey("fare0")
    private String fare0 = ""; //佣金
    @JsonKey("fare1")
    private String fare1 = ""; // 印花税
    @JsonKey("fare2")
    private String fare2 = ""; //过户费
    @JsonKey("fare3")
    private String fare3 = ""; //费用3
    @JsonKey("farex")
    private String farex = ""; //费用X
    @JsonKey("exchange_fare")
    private String exchange_fare = ""; //一级总费用
    @JsonKey("exchange_fare0")
    private String exchange_fare0 = ""; // 一级经手费
    @JsonKey("exchange_fare1")
    private String exchange_fare1 = ""; // 一级印花税
    @JsonKey("exchange_fare2")
    private String exchange_fare2 = ""; // 一级过户费
    @JsonKey("exchange_fare3")
    private String exchange_fare3 = ""; // 一级证管费
    @JsonKey("exchange_fare4")
    private String exchange_fare4 = ""; //一级规费
    @JsonKey("exchange_fare5")
    private String exchange_fare5 = ""; // 一级手续费
    @JsonKey("exchange_fare6")
    private String exchange_fare6 = ""; //其他费(结算费)
    @JsonKey("exchange_farex")
    private String exchange_farex = ""; //  风险金

    public OptionHistoryListBean() {

    }

    public String getInit_date() {
        return init_date;
    }

    public void setInit_date(String init_date) {
        this.init_date = init_date;
    }

    public String getBusiness_flag() {
        return business_flag;
    }

    public void setBusiness_flag(String business_flag) {
        this.business_flag = business_flag;
    }

    public String getBusiness_type() {
        return business_type;
    }

    public void setBusiness_type(String business_type) {
        this.business_type = business_type;
    }

    public String getBusiness_name() {
        return business_name;
    }

    public void setBusiness_name(String business_name) {
        this.business_name = business_name;
    }

    public String getFund_account() {
        return fund_account;
    }

    public void setFund_account(String fund_account) {
        this.fund_account = fund_account;
    }

    public String getBank_no() {
        return bank_no;
    }

    public void setBank_no(String bank_no) {
        this.bank_no = bank_no;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public String getMoney_type_name() {
        return money_type_name;
    }

    public void setMoney_type_name(String money_type_name) {
        this.money_type_name = money_type_name;
    }

    public String getMoney_type() {
        return money_type;
    }

    public void setMoney_type(String money_type) {
        this.money_type = money_type;
    }

    public String getBusiness_balance() {
        return business_balance;
    }

    public void setBusiness_balance(String business_balance) {
        this.business_balance = business_balance;
    }

    public String getClear_balance() {
        return clear_balance;
    }

    public void setClear_balance(String clear_balance) {
        this.clear_balance = clear_balance;
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

    public String getOption_code() {
        return option_code;
    }

    public void setOption_code(String option_code) {
        this.option_code = option_code;
    }

    public String getOption_name() {
        return option_name;
    }

    public void setOption_name(String option_name) {
        this.option_name = option_name;
    }

    public String getBusiness_amount() {
        return business_amount;
    }

    public void setBusiness_amount(String business_amount) {
        this.business_amount = business_amount;
    }

    public String getStandard_fare0() {
        return standard_fare0;
    }

    public void setStandard_fare0(String standard_fare0) {
        this.standard_fare0 = standard_fare0;
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

    public String getFare3() {
        return fare3;
    }

    public void setFare3(String fare3) {
        this.fare3 = fare3;
    }

    public String getFarex() {
        return farex;
    }

    public void setFarex(String farex) {
        this.farex = farex;
    }

    public String getExchange_fare() {
        return exchange_fare;
    }

    public void setExchange_fare(String exchange_fare) {
        this.exchange_fare = exchange_fare;
    }

    public String getExchange_fare0() {
        return exchange_fare0;
    }

    public void setExchange_fare0(String exchange_fare0) {
        this.exchange_fare0 = exchange_fare0;
    }

    public String getExchange_fare1() {
        return exchange_fare1;
    }

    public void setExchange_fare1(String exchange_fare1) {
        this.exchange_fare1 = exchange_fare1;
    }

    public String getExchange_fare2() {
        return exchange_fare2;
    }

    public void setExchange_fare2(String exchange_fare2) {
        this.exchange_fare2 = exchange_fare2;
    }

    public String getExchange_fare3() {
        return exchange_fare3;
    }

    public void setExchange_fare3(String exchange_fare3) {
        this.exchange_fare3 = exchange_fare3;
    }

    public String getExchange_fare4() {
        return exchange_fare4;
    }

    public void setExchange_fare4(String exchange_fare4) {
        this.exchange_fare4 = exchange_fare4;
    }

    public String getExchange_fare5() {
        return exchange_fare5;
    }

    public void setExchange_fare5(String exchange_fare5) {
        this.exchange_fare5 = exchange_fare5;
    }

    public String getExchange_fare6() {
        return exchange_fare6;
    }

    public void setExchange_fare6(String exchange_fare6) {
        this.exchange_fare6 = exchange_fare6;
    }

    public String getExchange_farex() {
        return exchange_farex;
    }

    public void setExchange_farex(String exchange_farex) {
        this.exchange_farex = exchange_farex;
    }
}
