package com.thinkive.android.trade_bz.a_hk.bean;

import com.thinkive.android.trade_bz.a_stock.bean.BaseBean;
import com.thinkive.android.trade_bz.others.JsonKey;


/**
 * 创建者     舒旺
 * 创建时间   2016/6/3 17:19
 * 描述	  对账单查询Bean
 * 更新者     $Author$
 * 更新时间   $Date$
 */
public class HKCapitalStatementBean extends BaseBean {
    @JsonKey("cleardate")
    private String cleardate = "";
    @JsonKey("settrate")
    private String settrate = "";
    @JsonKey("entrust_date")
    private String entrust_date = "";
    @JsonKey("business_balance")
    private String business_balance = "";
    @JsonKey("entrust_no")
    private String entrust_no = "";
    @JsonKey("money_type_name")
    private String money_type_name = "";
    @JsonKey("surplus_balance")
    private String surplus_balance = "";
    @JsonKey("business_type_name")
    private String business_type_name = "";
    @JsonKey("business_amount")
    private String business_amount = "";
    @JsonKey("bizdate")
    private String bizdate = "";
    @JsonKey("business_price")
    private String business_price = "";
    @JsonKey("entrust_balance")
    private String entrust_balance = "";
    @JsonKey("entrust_amount")
    private String entrust_amount = "";

    public String getUddelegere_priser() {
        return uddelegere_priser;
    }

    public void setUddelegere_priser(String uddelegere_priser) {
        this.uddelegere_priser = uddelegere_priser;
    }

    private String uddelegere_priser;


    public String getStock_name() {
        return stock_name;
    }

    public void setStock_name(String stock_name) {
        this.stock_name = stock_name;
    }

    public String getStock_code() {
        return stock_code;
    }

    public void setStock_code(String stock_code) {
        this.stock_code = stock_code;
    }

    private String stock_name;
    private String stock_code;

    public void setCleardate(String cleardate) {
        this.cleardate = cleardate;
    }

    public void setSettrate(String settrate) {
        this.settrate = settrate;
    }

    public void setEntrust_date(String entrust_date) {
        this.entrust_date = entrust_date;
    }

    public void setBusiness_balance(String business_balance) {
        this.business_balance = business_balance;
    }

    public void setEntrust_no(String entrust_no) {
        this.entrust_no = entrust_no;
    }

    public void setMoney_type_name(String money_type_name) {
        this.money_type_name = money_type_name;
    }

    public void setSurplus_balance(String surplus_balance) {
        this.surplus_balance = surplus_balance;
    }

    public void setBusiness_type_name(String business_type_name) {
        this.business_type_name = business_type_name;
    }

    public void setBusiness_amount(String business_amount) {
        this.business_amount = business_amount;
    }

    public void setBizdate(String bizdate) {
        this.bizdate = bizdate;
    }

    public void setBusiness_price(String business_price) {
        this.business_price = business_price;
    }

    public void setEntrust_balance(String entrust_balance) {
        this.entrust_balance = entrust_balance;
    }

    public String getCleardate() {
        return cleardate;
    }

    public String getSettrate() {
        return settrate;
    }

    public String getEntrust_date() {
        return entrust_date;
    }

    public String getBusiness_balance() {
        return business_balance;
    }

    public String getEntrust_no() {
        return entrust_no;
    }

    public String getMoney_type_name() {
        return money_type_name;
    }

    public String getSurplus_balance() {
        return surplus_balance;
    }

    public String getBusiness_type_name() {
        return business_type_name;
    }

    public String getBusiness_amount() {
        return business_amount;
    }

    public String getBizdate() {
        return bizdate;
    }

    public String getBusiness_price() {
        return business_price;
    }
    public String getEntrust_balance() {
        return entrust_balance;
    }

    public String getEntrust_amount() {
        return entrust_amount;
    }

    public void setEntrust_amount(String entrust_amount) {
        this.entrust_amount = entrust_amount;
    }
}
