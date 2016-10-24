package com.thinkive.android.trade_bz.a_stock.bean;

import com.thinkive.android.trade_bz.others.JsonKey;

/**
 * 历史委托数据实体类
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/6/23
 */

public class HistoryEntrustBean extends BaseBean {
    /**
     * 是否委托买入
     */
    @JsonKey("entrust_name")
    private String entrust_name = "";

    /**
     * 委托日期
     */
    @JsonKey("entrust_date")
    private String entrust_date = "";

    /**
     * 委托时间
     */
    @JsonKey("entrust_time")
    private String entrust_time = "";

    /**
     * 股票名称
     */
    @JsonKey("stock_name")
    private String stock_name = "";

    /**
     * 股票号码
     */
    @JsonKey("stock_code")
    private String stock_code = "";
    /**
     * 证券编号
     */
    @JsonKey("entrust_no")
    private String entrust_no = "";

    /**
     * 委托状态
     */
    @JsonKey("entrust_state_name")
    private String entrust_state_name = "";
    /**
     * 委托价
     */
    @JsonKey("entrust_price")
    private String entrust_price = "";
    /**
     * 委托数
     */
    @JsonKey("entrust_amount")
    private String entrust_amount = "";
    /**
     * 成交价
     */
    @JsonKey("business_price")
    private String business_price = "";
    /**
     * 成交数
     */
    @JsonKey("business_amount")
    private String business_amount = "";
    /**
     * 委托标志
     */
    @JsonKey("entrust_bs")
    private String  entrust_bs="";

    //以下未使用
//    private String  entrust_type="";
//    private String  entrust_type_name="";
//    private String entrust_state="";
//    private String exchange_type="";
//    private String exchange_type_name="";
//    private String stock_account="";
//    private String report_no="";
//    private String business_balance="";
//    private String cancel_amount="";
//    private String cancel_flag="";

    public HistoryEntrustBean() {
    }

    public String getEntrust_name() {
        return entrust_name;
    }

    public void setEntrust_name(String entrust_name) {
        this.entrust_name = entrust_name;
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

    public String getEntrust_no() {
        return entrust_no;
    }

    public void setEntrust_no(String entrust_no) {
        this.entrust_no = entrust_no;
    }

    public String getEntrust_state_name() {
        return entrust_state_name;
    }

    public void setEntrust_state_name(String entrust_state_name) {
        this.entrust_state_name = entrust_state_name;
    }

    public String getEntrust_price() {
        return entrust_price;
    }

    public void setEntrust_price(String entrust_price) {
        this.entrust_price = entrust_price;
    }

    public String getBusiness_price() {
        return business_price;
    }

    public void setBusiness_price(String business_price) {
        this.business_price = business_price;
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

    public String getEntrust_bs() {
        return entrust_bs;
    }

    public void setEntrust_bs(String entrust_bs) {
        this.entrust_bs = entrust_bs;
    }

//    public String getEntrust_type() {
//        return entrust_type;
//    }
//
//    public void setEntrust_type(String entrust_type) {
//        this.entrust_type = entrust_type;
//    }
//
//    public String getEntrust_type_name() {
//        return entrust_type_name;
//    }
//
//    public void setEntrust_type_name(String entrust_type_name) {
//        this.entrust_type_name = entrust_type_name;
//    }

//    public String getEntrust_state() {
//        return entrust_state;
//    }
//
//    public void setEntrust_state(String entrust_state) {
//        this.entrust_state = entrust_state;
//    }

//    public String getExchange_type_name() {
//        return exchange_type_name;
//    }
//
//    public void setExchange_type_name(String exchange_type_name) {
//        this.exchange_type_name = exchange_type_name;
//    }
//
//    public String getExchange_type() {
//        return exchange_type;
//    }
//
//    public void setExchange_type(String exchange_type) {
//        this.exchange_type = exchange_type;
//    }
//
//    public String getStock_account() {
//        return stock_account;
//    }
//
//    public void setStock_account(String stock_account) {
//        this.stock_account = stock_account;
//    }
//
//    public String getReport_no() {
//        return report_no;
//    }
//
//    public void setReport_no(String report_no) {
//        this.report_no = report_no;
//    }
//
//    public String getBusiness_balance() {
//        return business_balance;
//    }
//
//    public void setBusiness_balance(String business_balance) {
//        this.business_balance = business_balance;
//    }

//    public String getCancel_amount() {
//        return cancel_amount;
//    }
//
//    public void setCancel_amount(String cancel_amount) {
//        this.cancel_amount = cancel_amount;
//    }
//
//    public String getCancel_flag() {
//        return cancel_flag;
//    }
//
//    public void setCancel_flag(String cancel_flag) {
//        this.cancel_flag = cancel_flag;
//    }
}
