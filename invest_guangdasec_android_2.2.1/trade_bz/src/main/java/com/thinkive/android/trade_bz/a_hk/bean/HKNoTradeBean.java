package com.thinkive.android.trade_bz.a_hk.bean;

import com.thinkive.android.trade_bz.a_stock.bean.BaseBean;
import com.thinkive.android.trade_bz.others.JsonKey;


/**
 * 创建者     舒旺
 * 创建时间   2016/6/2 17:19
 * 描述	       未交明细查询Bean
 * 更新者     $Author$
 * 更新时间   $Date$
 */
public class HKNoTradeBean extends BaseBean {
    @JsonKey("entrust_amount")
    private String entrust_amount ="";
    @JsonKey("settrate")
    private String settrate ="";
    @JsonKey("fee_yhs")
    private String fee_yhs ="";
    @JsonKey("entrust_date")
    private String entrust_date ="";
    @JsonKey("business_balance")
    private String business_balance ="";
    @JsonKey("cleared_balance")
    private String cleared_balance ="";
    @JsonKey("money_type_name")
    private String money_type_name ="";
    @JsonKey("business_id")
    private String business_id ="";
    @JsonKey("stock_code")
    private String stock_code ="";
    @JsonKey("business_amount")
    private String business_amount ="";
    @JsonKey("stock_name")
    private String stock_name ="";
    @JsonKey("state_busitype_name")
    private String state_busitype_name ="";
    @JsonKey("fee_sxf")
    private String fee_sxf ="";

    public void setEntrust_amount(String entrust_amount) {
        this.entrust_amount = entrust_amount;
    }

    public void setSettrate(String settrate) {
        this.settrate = settrate;
    }

    public void setFee_yhs(String fee_yhs) {
        this.fee_yhs = fee_yhs;
    }

    public void setEntrust_date(String entrust_date) {
        this.entrust_date = entrust_date;
    }

    public void setBusiness_balance(String business_balance) {
        this.business_balance = business_balance;
    }

    public void setCleared_balance(String cleared_balance) {
        this.cleared_balance = cleared_balance;
    }

    public void setMoney_type_name(String money_type_name) {
        this.money_type_name = money_type_name;
    }

    public void setBusiness_id(String business_id) {
        this.business_id = business_id;
    }

    public void setStock_code(String stock_code) {
        this.stock_code = stock_code;
    }

    public void setBusiness_amount(String business_amount) {
        this.business_amount = business_amount;
    }

    public void setStock_name(String stock_name) {
        this.stock_name = stock_name;
    }

    public void setState_busitype_name(String state_busitype_name) {
        this.state_busitype_name = state_busitype_name;
    }

    public void setFee_sxf(String fee_sxf) {
        this.fee_sxf = fee_sxf;
    }

    public String getEntrust_amount() {
        return entrust_amount;
    }

    public String getSettrate() {
        return settrate;
    }

    public String getFee_yhs() {
        return fee_yhs;
    }

    public String getEntrust_date() {
        return entrust_date;
    }

    public String getBusiness_balance() {
        return business_balance;
    }

    public String getCleared_balance() {
        return cleared_balance;
    }

    public String getMoney_type_name() {
        return money_type_name;
    }

    public String getBusiness_id() {
        return business_id;
    }

    public String getStock_code() {
        return stock_code;
    }

    public String getBusiness_amount() {
        return business_amount;
    }

    public String getStock_name() {
        return stock_name;
    }

    public String getState_busitype_name() {
        return state_busitype_name;
    }

    public String getFee_sxf() {
        return fee_sxf;
    }
}
