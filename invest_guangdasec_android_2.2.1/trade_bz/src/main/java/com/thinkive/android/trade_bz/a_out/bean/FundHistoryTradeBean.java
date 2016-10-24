package com.thinkive.android.trade_bz.a_out.bean;

import com.thinkive.android.trade_bz.a_stock.bean.BaseBean;
import com.thinkive.android.trade_bz.others.JsonKey;

/**
 *  基金交易--查询--历史成交
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/8/7
 */

public class FundHistoryTradeBean extends BaseBean {
    /**
     *成交编号
     */
    @JsonKey("entrust_no")
    private String entrust_no="";
    /**
     * 成交日期
     */
    @JsonKey("business_date")
    private String business_date="";
    /**
     *成交时间
     */
    @JsonKey("business_time")
    private String business_time="";
    /**
     * 基金公司代码
     */
    @JsonKey("fund_company")
    private String fund_company="";
    /**
     * 基金公司名称
     */
    @JsonKey("company_name")
    private String company_name="";
    /**
     *基金代码
     */
    @JsonKey("fund_code")
    private String fund_code="";
    /**
     *基金名称
     */
    @JsonKey("fund_name")
    private String fund_name="";
    /**
     *业务标志
     */
    @JsonKey("business_flag")
    private String business_flag="";
    /**
     *业务标志名称
     */
    @JsonKey("business_name")
    private String business_name="";
    /**
     *币种
     */
    @JsonKey("money_type")
    private String money_type="";
    /**
     * 币种名称
     */
    @JsonKey("money_type_name")
    private String money_type_name="";
    /**
     * 成交金额
     */
    @JsonKey("balance")
    private String balance="";
    /**
     * 成交份额
     */
    @JsonKey("shares")
    private String shares="";
    /**
     *转换代码
     */
    @JsonKey("trans_code")
    private String trans_code="";
    /**
     * 基金账户
     */
    @JsonKey("fund_account")
    private String fund_account="";
    /**
     *日基金单位净值
     */
    @JsonKey("nav")
    private String nav="";
    /**
     *手续费
     */
    @JsonKey("fare_sx")
    private String fare_sx="";
    /**
     *委托状态
     */
    @JsonKey("entrust_status")
    private String entrust_status="";
    /**
     * 委托状态名称
     */
    @JsonKey("entrust_status_mame")
    private String entrust_status_mame="";

    public FundHistoryTradeBean() {
    }

    public String getEntrust_no() {
        return entrust_no;
    }

    public void setEntrust_no(String entrust_no) {
        this.entrust_no = entrust_no;
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

    public String getFund_company() {
        return fund_company;
    }

    public void setFund_company(String fund_company) {
        this.fund_company = fund_company;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getFund_code() {
        return fund_code;
    }

    public void setFund_code(String fund_code) {
        this.fund_code = fund_code;
    }

    public String getFund_name() {
        return fund_name;
    }

    public void setFund_name(String fund_name) {
        this.fund_name = fund_name;
    }

    public String getBusiness_flag() {
        return business_flag;
    }

    public void setBusiness_flag(String business_flag) {
        this.business_flag = business_flag;
    }

    public String getBusiness_name() {
        return business_name;
    }

    public void setBusiness_name(String business_name) {
        this.business_name = business_name;
    }

    public String getMoney_type() {
        return money_type;
    }

    public void setMoney_type(String money_type) {
        this.money_type = money_type;
    }

    public String getMoney_type_name() {
        return money_type_name;
    }

    public void setMoney_type_name(String money_type_name) {
        this.money_type_name = money_type_name;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getShares() {
        return shares;
    }

    public void setShares(String shares) {
        this.shares = shares;
    }

    public String getTrans_code() {
        return trans_code;
    }

    public void setTrans_code(String trans_code) {
        this.trans_code = trans_code;
    }

    public String getFund_account() {
        return fund_account;
    }

    public void setFund_account(String fund_account) {
        this.fund_account = fund_account;
    }

    public String getNav() {
        return nav;
    }

    public void setNav(String nav) {
        this.nav = nav;
    }

    public String getFare_sx() {
        return fare_sx;
    }

    public void setFare_sx(String fare_sx) {
        this.fare_sx = fare_sx;
    }

    public String getEntrust_status() {
        return entrust_status;
    }

    public void setEntrust_status(String entrust_status) {
        this.entrust_status = entrust_status;
    }

    public String getEntrust_status_mame() {
        return entrust_status_mame;
    }

    public void setEntrust_status_mame(String entrust_status_mame) {
        this.entrust_status_mame = entrust_status_mame;
    }
}
