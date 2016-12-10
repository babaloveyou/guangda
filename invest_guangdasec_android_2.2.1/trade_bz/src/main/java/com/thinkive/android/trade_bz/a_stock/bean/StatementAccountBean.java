package com.thinkive.android.trade_bz.a_stock.bean;

import android.os.Parcel;

import com.thinkive.android.trade_bz.others.JsonKey;

/**
 * 对账单
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/6/24
 */

public class StatementAccountBean extends BaseBean {


    @JsonKey("post_balance")
    private String post_balance ="";
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
    /**
     * zy : 可取同名转入
     * entrust_bs_name : 转
     * lsh : 2016120900000587
     */
    @JsonKey("zy")
    private String zy;
    @JsonKey("entrust_bs_name")
    private String entrust_bs_name;
    @JsonKey("lsh")
    private String lsh;

    public String getPost_balance() {
        return post_balance;
    }

    public void setPost_balance(String post_balance) {
        this.post_balance = post_balance;
    }

    public static Creator<StatementAccountBean> getCREATOR() {
        return CREATOR;
    }

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

    public String getZy() {
        return zy;
    }

    public void setZy(String zy) {
        this.zy = zy;
    }

    public String getEntrust_bs_name() {
        return entrust_bs_name;
    }

    public void setEntrust_bs_name(String entrust_bs_name) {
        this.entrust_bs_name = entrust_bs_name;
    }

    public String getLsh() {
        return lsh;
    }

    public void setLsh(String lsh) {
        this.lsh = lsh;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.post_balance);
        dest.writeString(this.matchamt);
        dest.writeString(this.orderprice);
        dest.writeString(this.business_date);
        dest.writeString(this.remark);
        dest.writeString(this.stkbal);
        dest.writeString(this.entrust_bs);
        dest.writeString(this.money_type_name);
        dest.writeString(this.money_type);
        dest.writeString(this.matchqty);
        dest.writeString(this.exchange_type);
        dest.writeString(this.stock_code);
        dest.writeString(this.business_name);
        dest.writeString(this.business_flag);
        dest.writeString(this.exchange_type_name);
        dest.writeString(this.entrust_no);
        dest.writeString(this.enable_balance);
        dest.writeString(this.fund_account);
        dest.writeString(this.stock_account);
        dest.writeString(this.stock_name);
        dest.writeString(this.occur_balance);
        dest.writeString(this.business_price);
        dest.writeString(this.occur_amount);
        dest.writeString(this.entrust_name);
        dest.writeString(this.fundbal);
        dest.writeString(this.eremark);
        dest.writeString(this.serial_no);
        dest.writeString(this.fee_sxf);
        dest.writeString(this.fare1);
        dest.writeString(this.fee_ghf);
        dest.writeString(this.zy);
        dest.writeString(this.entrust_bs_name);
        dest.writeString(this.lsh);
    }

    protected StatementAccountBean(Parcel in) {
        this.post_balance = in.readString();
        this.matchamt = in.readString();
        this.orderprice = in.readString();
        this.business_date = in.readString();
        this.remark = in.readString();
        this.stkbal = in.readString();
        this.entrust_bs = in.readString();
        this.money_type_name = in.readString();
        this.money_type = in.readString();
        this.matchqty = in.readString();
        this.exchange_type = in.readString();
        this.stock_code = in.readString();
        this.business_name = in.readString();
        this.business_flag = in.readString();
        this.exchange_type_name = in.readString();
        this.entrust_no = in.readString();
        this.enable_balance = in.readString();
        this.fund_account = in.readString();
        this.stock_account = in.readString();
        this.stock_name = in.readString();
        this.occur_balance = in.readString();
        this.business_price = in.readString();
        this.occur_amount = in.readString();
        this.entrust_name = in.readString();
        this.fundbal = in.readString();
        this.eremark = in.readString();
        this.serial_no = in.readString();
        this.fee_sxf = in.readString();
        this.fare1 = in.readString();
        this.fee_ghf = in.readString();
        this.zy = in.readString();
        this.entrust_bs_name = in.readString();
        this.lsh = in.readString();
    }

    public static final Creator<StatementAccountBean> CREATOR = new Creator<StatementAccountBean>() {
        @Override
        public StatementAccountBean createFromParcel(Parcel source) {
            return new StatementAccountBean(source);
        }

        @Override
        public StatementAccountBean[] newArray(int size) {
            return new StatementAccountBean[size];
        }
    };
}
