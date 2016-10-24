package com.thinkive.android.trade_bz.a_stock.bean;

import com.thinkive.android.trade_bz.others.JsonKey;

/**
 * 对账单
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/6/24
 */

public class StatementAccountBean extends BaseBean {
    @JsonKey("matchamt")
    private String matchamt ="";
    @JsonKey("orderprice")
    private String  orderprice ="";
    @JsonKey("business_date")
    private String  business_date ="";
    @JsonKey("remark")
    private String  remark ="";
    @JsonKey("stkbal")
    private String  stkbal ="";
    @JsonKey("entrust_bs")
    private String  entrust_bs ="";
    @JsonKey("money_type_name")
    private String money_type_name  ="";
    @JsonKey("money_type")
    private String  money_type ="";
    @JsonKey("matchqty")
    private String  matchqty ="";
    @JsonKey("exchange_type")
    private String  exchange_type ="";
    @JsonKey("stock_code")
    private String  stock_code ="";
    @JsonKey("business_name")
    private String  business_name ="";
    @JsonKey("business_flag")
    private String  business_flag ="";
    @JsonKey("exchange_type_name")
    private String  exchange_type_name ="";
    @JsonKey("entrust_no")
    private String  entrust_no ="";
    @JsonKey("enable_balance")
    private String  enable_balance ="";
    @JsonKey("fund_account")
    private String  fund_account ="";
    @JsonKey("stock_account")
    private String  stock_account ="";
    @JsonKey("stock_name")
    private String  stock_name ="";
    @JsonKey("occur_balance")
    private String  occur_balance ="";
    @JsonKey("business_price")
    private String  business_price ="";
    @JsonKey("occur_amount")
    private String  occur_amount ="";
    @JsonKey("entrust_name")
    private String entrust_name ="";
    @JsonKey("fundbal")
    private String fundbal = "";
    @JsonKey("eremark")
    private String eremark="";
    @JsonKey("serial_no")
    private String serial_no="";
    @JsonKey("fee_sxf")
    private String fee_sxf ="";//佣金
    @JsonKey("fare1")
    private String fare1="";//印花税
    @JsonKey("fee_ghf")
    private String fee_ghf ="";//过户费


    public StatementAccountBean() {
    }

    public String getMatchamt() {
        return matchamt;
    }

    public void setMatchamt(String matchamt) {
        this.matchamt = matchamt;
    }

    public String getOrderprice() {
        return orderprice;
    }

    public void setOrderprice(String orderprice) {
        this.orderprice = orderprice;
    }

    public String getBusiness_date() {
        return business_date;
    }

    public void setBusiness_date(String business_date) {
        this.business_date = business_date;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStkbal() {
        return stkbal;
    }

    public void setStkbal(String stkbal) {
        this.stkbal = stkbal;
    }

    public String getEntrust_bs() {
        return entrust_bs;
    }

    public void setEntrust_bs(String entrust_bs) {
        this.entrust_bs = entrust_bs;
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

    public String getMatchqty() {
        return matchqty;
    }

    public void setMatchqty(String matchqty) {
        this.matchqty = matchqty;
    }

    public String getExchange_type() {
        return exchange_type;
    }

    public void setExchange_type(String exchange_type) {
        this.exchange_type = exchange_type;
    }

    public String getStock_code() {
        return stock_code;
    }

    public void setStock_code(String stock_code) {
        this.stock_code = stock_code;
    }

    public String getBusiness_name() {
        return business_name;
    }

    public void setBusiness_name(String business_name) {
        this.business_name = business_name;
    }

    public String getBusiness_flag() {
        return business_flag;
    }

    public void setBusiness_flag(String business_flag) {
        this.business_flag = business_flag;
    }

    public String getExchange_type_name() {
        return exchange_type_name;
    }

    public void setExchange_type_name(String exchange_type_name) {
        this.exchange_type_name = exchange_type_name;
    }

    public String getEntrust_no() {
        return entrust_no;
    }

    public void setEntrust_no(String entrust_no) {
        this.entrust_no = entrust_no;
    }

    public String getEnable_balance() {
        return enable_balance;
    }

    public void setEnable_balance(String enable_balance) {
        this.enable_balance = enable_balance;
    }

    public String getFund_account() {
        return fund_account;
    }

    public void setFund_account(String fund_account) {
        this.fund_account = fund_account;
    }

    public String getStock_account() {
        return stock_account;
    }

    public void setStock_account(String stock_account) {
        this.stock_account = stock_account;
    }

    public String getStock_name() {
        return stock_name;
    }

    public void setStock_name(String stock_name) {
        this.stock_name = stock_name;
    }

    public String getOccur_balance() {
        return occur_balance;
    }

    public void setOccur_balance(String occur_balance) {
        this.occur_balance = occur_balance;
    }

    public String getBusiness_price() {
        return business_price;
    }

    public void setBusiness_price(String business_price) {
        this.business_price = business_price;
    }

    public String getOccur_amount() {
        return occur_amount;
    }

    public void setOccur_amount(String occur_amount) {
        this.occur_amount = occur_amount;
    }

    public String getEntrust_name() {
        return entrust_name;
    }

    public void setEntrust_name(String entrust_name) {
        this.entrust_name = entrust_name;
    }

    public String getEremark() {
        return eremark;
    }

    public void setEremark(String eremark) {
        this.eremark = eremark;
    }

    public String getSerial_no() {
        return serial_no;
    }

    public void setSerial_no(String serial_no) {
        this.serial_no = serial_no;
    }

    public String getFee_sxf() {
        return fee_sxf;
    }

    public void setFee_sxf(String fee_sxf) {
        this.fee_sxf = fee_sxf;
    }

    public String getFare1() {
        return fare1;
    }

    public void setFare1(String fare1) {
        this.fare1 = fare1;
    }

    public String getFee_ghf() {
        return fee_ghf;
    }

    public void setFee_ghf(String fee_ghf) {
        this.fee_ghf = fee_ghf;
    }

    public String getFundbal() {
        return fundbal;
    }

    public void setFundbal(String fundbal) {
        this.fundbal = fundbal;
    }
}
