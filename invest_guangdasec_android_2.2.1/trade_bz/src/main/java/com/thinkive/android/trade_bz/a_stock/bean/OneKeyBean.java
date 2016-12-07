package com.thinkive.android.trade_bz.a_stock.bean;

import android.os.Parcel;

import com.thinkive.android.trade_bz.others.JsonKey;

/**
 * 一键归集
 * 300206(一键归集)
 * 300207（资金转账）
 * 300208（归集查询）
 *
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/8/12
 */

public class OneKeyBean extends BaseBean {

    /**
     * renum:0,
     * fundeffec:0.0
     * assert_val :
     * bank_name : 工行存管
     * market_val :
     * frozen_balance :
     * fundseq : 0
     * money_type_name : 人民币
     * enable_balance : 7050127.220
     * money_type : 0
     * fetch_balance : 7050127.220
     * current_balance : 7234909.070
     * fundid : 41518655
     * bank_account : 6222083901006714508
     */
    @JsonKey("renum")
    private String renum;
    @JsonKey("fundeffec")
    private String fundeffec;
    @JsonKey("assert_val")
    private String assert_val;
    @JsonKey("bank_name")
    private String bank_name;
    @JsonKey("market_val")
    private String market_val;
    @JsonKey("frozen_balance")
    private String frozen_balance;
    @JsonKey("fundseq")
    private String fundseq;
    @JsonKey("money_type_name")
    private String money_type_name;
    @JsonKey("enable_balance")
    private String enable_balance;
    @JsonKey("money_type")
    private String money_type;
    @JsonKey("fetch_balance")
    private String fetch_balance;
    @JsonKey("current_balance")
    private String current_balance;
    @JsonKey("fundid")
    private String fundid;
    @JsonKey("bank_account")
    private String bank_account;

    public String getRenum() {
        return renum;
    }

    public void setRenum(String renum) {
        this.renum = renum;
    }

    public String getFundeffec() {
        return fundeffec;
    }

    public void setFundeffec(String fundeffec) {
        this.fundeffec = fundeffec;
    }

    public static Creator<OneKeyBean> getCREATOR() {
        return CREATOR;
    }

    public String getAssert_val() {
        return assert_val;
    }

    public void setAssert_val(String assert_val) {
        this.assert_val = assert_val;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public String getMarket_val() {
        return market_val;
    }

    public void setMarket_val(String market_val) {
        this.market_val = market_val;
    }

    public String getFrozen_balance() {
        return frozen_balance;
    }

    public void setFrozen_balance(String frozen_balance) {
        this.frozen_balance = frozen_balance;
    }

    public String getFundseq() {
        return fundseq;
    }

    public void setFundseq(String fundseq) {
        this.fundseq = fundseq;
    }

    public String getMoney_type_name() {
        return money_type_name;
    }

    public void setMoney_type_name(String money_type_name) {
        this.money_type_name = money_type_name;
    }

    public String getEnable_balance() {
        return enable_balance;
    }

    public void setEnable_balance(String enable_balance) {
        this.enable_balance = enable_balance;
    }

    public String getMoney_type() {
        return money_type;
    }

    public void setMoney_type(String money_type) {
        this.money_type = money_type;
    }

    public String getFetch_balance() {
        return fetch_balance;
    }

    public void setFetch_balance(String fetch_balance) {
        this.fetch_balance = fetch_balance;
    }

    public String getCurrent_balance() {
        return current_balance;
    }

    public void setCurrent_balance(String current_balance) {
        this.current_balance = current_balance;
    }

    public String getFundid() {
        return fundid;
    }

    public void setFundid(String fundid) {
        this.fundid = fundid;
    }

    public String getBank_account() {
        return bank_account;
    }

    public void setBank_account(String bank_account) {
        this.bank_account = bank_account;
    }


    public OneKeyBean() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.renum);
        dest.writeString(this.fundeffec);
        dest.writeString(this.assert_val);
        dest.writeString(this.bank_name);
        dest.writeString(this.market_val);
        dest.writeString(this.frozen_balance);
        dest.writeString(this.fundseq);
        dest.writeString(this.money_type_name);
        dest.writeString(this.enable_balance);
        dest.writeString(this.money_type);
        dest.writeString(this.fetch_balance);
        dest.writeString(this.current_balance);
        dest.writeString(this.fundid);
        dest.writeString(this.bank_account);
    }

    protected OneKeyBean(Parcel in) {
        this.renum = in.readString();
        this.fundeffec = in.readString();
        this.assert_val = in.readString();
        this.bank_name = in.readString();
        this.market_val = in.readString();
        this.frozen_balance = in.readString();
        this.fundseq = in.readString();
        this.money_type_name = in.readString();
        this.enable_balance = in.readString();
        this.money_type = in.readString();
        this.fetch_balance = in.readString();
        this.current_balance = in.readString();
        this.fundid = in.readString();
        this.bank_account = in.readString();
    }

    public static final Creator<OneKeyBean> CREATOR = new Creator<OneKeyBean>() {
        @Override
        public OneKeyBean createFromParcel(Parcel source) {
            return new OneKeyBean(source);
        }

        @Override
        public OneKeyBean[] newArray(int size) {
            return new OneKeyBean[size];
        }
    };
}
