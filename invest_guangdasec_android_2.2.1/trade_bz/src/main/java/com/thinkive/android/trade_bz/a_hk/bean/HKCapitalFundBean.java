package com.thinkive.android.trade_bz.a_hk.bean;

import com.thinkive.android.trade_bz.a_stock.bean.BaseBean;
import com.thinkive.android.trade_bz.others.JsonKey;


/**
 * 创建者     舒旺
 * 创建时间   2016/6/3 11:57
 * 更新时间   $Date$
 */
public class HKCapitalFundBean extends BaseBean {
    @JsonKey("fund_realin")
    private String fund_realin = "";
    @JsonKey("fund_code")
    private String fund_code = "";
    @JsonKey("assert_val")
    private String assert_val = "";
    @JsonKey("fund_tranin")
    private String fund_tranin = "";
    @JsonKey("surplus_balance")
    private String surplus_balance = "";
    @JsonKey("enable_balance")
    private String enable_balance = "";
    @JsonKey("fund_tranout")
    private String fund_tranout = "";
    @JsonKey("fund_real_own")
    private String fund_real_own = "";
    @JsonKey("set_balance")
    private String set_balance = "";
    @JsonKey("fund_realout")
    private String fund_realout = "";
    @JsonKey("funduncomeout")
    private String funduncomeout = "";
    @JsonKey("funduncomein")
    private String funduncomein = "";
    @JsonKey("fundnightout")
    private String fundnightout = "";

    public void setFund_realin(String fund_realin) {
        this.fund_realin = fund_realin;
    }

    public void setFund_code(String fund_code) {
        this.fund_code = fund_code;
    }

    public void setAssert_val(String assert_val) {
        this.assert_val = assert_val;
    }

    public void setFund_tranin(String fund_tranin) {
        this.fund_tranin = fund_tranin;
    }

    public void setSurplus_balance(String surplus_balance) {
        this.surplus_balance = surplus_balance;
    }

    public void setEnable_balance(String enable_balance) {
        this.enable_balance = enable_balance;
    }

    public void setFund_tranout(String fund_tranout) {
        this.fund_tranout = fund_tranout;
    }

    public void setFund_real_own(String fund_real_own) {
        this.fund_real_own = fund_real_own;
    }

    public void setSet_balance(String set_balance) {
        this.set_balance = set_balance;
    }

    public void setFund_realout(String fund_realout) {
        this.fund_realout = fund_realout;
    }

    public String getFund_realin() {
        return fund_realin;
    }

    public String getFund_code() {
        return fund_code;
    }

    public String getAssert_val() {
        return assert_val;
    }

    public String getFund_tranin() {
        return fund_tranin;
    }

    public String getSurplus_balance() {
        return surplus_balance;
    }

    public String getEnable_balance() {
        return enable_balance;
    }

    public String getFund_tranout() {
        return fund_tranout;
    }

    public String getFund_real_own() {
        return fund_real_own;
    }

    public String getSet_balance() {
        return set_balance;
    }

    public String getFund_realout() {
        return fund_realout;
    }

    public String getFunduncomeout() {
        return funduncomeout;
    }

    public void setFunduncomeout(String funduncomeout) {
        this.funduncomeout = funduncomeout;
    }

    public String getFunduncomein() {
        return funduncomein;
    }

    public void setFunduncomein(String funduncomein) {
        this.funduncomein = funduncomein;
    }

    public String getFundnightout() {
        return fundnightout;
    }

    public void setFundnightout(String fundnightout) {
        this.fundnightout = fundnightout;
    }
}
