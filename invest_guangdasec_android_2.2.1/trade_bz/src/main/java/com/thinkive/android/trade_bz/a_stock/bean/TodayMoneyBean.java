package com.thinkive.android.trade_bz.a_stock.bean;

import com.thinkive.android.trade_bz.others.JsonKey;

/**
 * 当日资金流水数据实体类
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/6/25
 */

public class TodayMoneyBean extends BaseBean {
    /**
     * 清算日期
     */
    @JsonKey("business_date")
    private String business_date = "";
    /**
     * 业务标志
     */
    @JsonKey("business_flag")
    private String business_flag = "";
    /**
     * 业务名称
     */
    @JsonKey("business_name")
    private String business_name = "";
    /**
     * 后资金额
     */
    @JsonKey("enable_balance")
    private String enable_balance = "";
    /**
     * 发生金额
     */
    @JsonKey("occur_balance")
    private String occur_balance = "";
    /**
     * 币种类别
     */
    @JsonKey("money_type")
    private String money_type = "";
    /**
     * 币种名称
     */
    @JsonKey("money_type_name")
    private String money_type_name = "";
    /**
     * 资金账号
     */
    @JsonKey("fund_account")
    private String fund_account = "";
    /**
     * 交易类别
     */
    @JsonKey("exchange_type")
    private String exchange_type = "";
    /**
     * 交易类别名称
     */
    @JsonKey("exchange_type_name")
    private String exchange_type_name = "";
    /**
     * 证券账号
     */
    @JsonKey("stock_account")
    private String stock_account = "";
    /**
     * 证券代码
     */
    @JsonKey("stock_code")
    private String stock_code = "";
    /**
     * 证券名称
     */
    @JsonKey("stock_name")
    private String stock_name = "";
    /**
     * 委托标志
     */
    @JsonKey("entrust_bs")
    private String entrust_bs = "";
    /**
     * 发生数量
     */
    @JsonKey("occur_amount")
    private String occur_amount = "";
    /**
     * 备注
     */
    @JsonKey("remark")
    private String remark = "";

    /**
     * 添加
     */
//    private String matchamt="";
    @JsonKey("orderprice")
    private String orderprice = "";//自定义为委托价格
    //    private String stkbal="";
//    private String fundbal="";
//    private String matchqty="";
    @JsonKey("entrust_no")
    private String entrust_no = "";//委托编号
    @JsonKey("orderqty")
    private String orderqty = "";//自定为成交数量
    @JsonKey("matchprice")
    private String matchprice = "";//成交价格
    @JsonKey("entrust_bs_name")
    private String entrust_bs_name = "";///自定为委托数量
    @JsonKey("business_price")
    private String business_price = "";//成交价格


    public TodayMoneyBean() {
    }

    public String getOrderprice() {
        return orderprice;
    }

    public void setOrderprice(String orderprice) {
        this.orderprice = orderprice;
    }

//    public String getMatchamt() {
//        return matchamt;
//    }
//
//    public void setMatchamt(String matchamt) {
//        this.matchamt = matchamt;
//    }

    public String getBusiness_price() {
        return business_price;
    }

    public void setBusiness_price(String business_price) {
        this.business_price = business_price;
    }

    public String getBusiness_date() {
        return business_date;
    }

    public void setBusiness_date(String business_date) {
        this.business_date = business_date;
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

    public String getEnable_balance() {
        return enable_balance;
    }

    public void setEnable_balance(String enable_balance) {
        this.enable_balance = enable_balance;
    }

    public String getOccur_balance() {
        return occur_balance;
    }

    public void setOccur_balance(String occur_balance) {
        this.occur_balance = occur_balance;
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

    public String getFund_account() {
        return fund_account;
    }

    public void setFund_account(String fund_account) {
        this.fund_account = fund_account;
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

//    public String getJzwin_business_price() {
//        return business_price;
//    }
//
//    public void setJzwin_business_price(String business_price) {
//        this.business_price = business_price;
//    }

    public String getOccur_amount() {
        return occur_amount;
    }

    public void setOccur_amount(String occur_amount) {
        this.occur_amount = occur_amount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

//    public String getStkbal() {
//        return stkbal;
//    }
//
//    public void setStkbal(String stkbal) {
//        this.stkbal = stkbal;
//    }
//
//    public String getFundbal() {
//        return fundbal;
//    }
//
//    public void setFundbal(String fundbal) {
//        this.fundbal = fundbal;
//    }

    //    public String getMatchqty() {
//        return matchqty;
//    }
//
//    public void setMatchqty(String matchqty) {
//        this.matchqty = matchqty;
//    }
//
    public String getEntrust_no() {
        return entrust_no;
    }

    public void setEntrust_no(String entrust_no) {
        this.entrust_no = entrust_no;
    }

    public String getOrderqty() {
        return orderqty;
    }

    public void setOrderqty(String orderqty) {
        this.orderqty = orderqty;
    }

    //
    public String getMatchprice() {
        return matchprice;
    }

    public void setMatchprice(String matchprice) {
        this.matchprice = matchprice;
    }

    public String getEntrust_bs_name() {
        return entrust_bs_name;
    }

    public void setEntrust_bs_name(String entrust_bs_name) {
        this.entrust_bs_name = entrust_bs_name;
    }

    public TodayMoneyBean(String business_date, String business_flag,
                          String business_name, String enable_balance,
                          String occur_balance, String money_type,
                          String money_type_name, String fund_account,
                          String exchange_type, String exchange_type_name,
                          String stock_account, String stock_code,
                          String stock_name, String entrust_bs, String occur_amount,
                          String remark, String orderprice,/*String matchamt,
                          String stkbal, String fundbal, String matchqty,*/
                          String entrust_no, String orderqty, String matchprice,
                          String entrust_bs_name) {
        this.business_date = business_date;
        this.business_flag = business_flag;
        this.business_name = business_name;
        this.enable_balance = enable_balance;
        this.occur_balance = occur_balance;
        this.money_type = money_type;
        this.money_type_name = money_type_name;
        this.fund_account = fund_account;
        this.exchange_type = exchange_type;
        this.exchange_type_name = exchange_type_name;
        this.stock_account = stock_account;
        this.stock_code = stock_code;
        this.stock_name = stock_name;
        this.entrust_bs = entrust_bs;
//        this.business_price = business_price;
        this.occur_amount = occur_amount;
        this.remark = remark;
//        this.matchamt = matchamt;
        this.orderprice = orderprice;
//        this.stkbal = stkbal;
//        this.fundbal = fundbal;
//        this.matchqty = matchqty;
        this.entrust_no = entrust_no;
        this.orderqty = orderqty;
        this.matchprice = matchprice;
        this.entrust_bs_name = entrust_bs_name;
    }
}
