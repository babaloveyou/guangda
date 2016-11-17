package com.thinkive.android.trade_bz.a_stock.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.thinkive.android.trade_bz.others.JsonKey;

/**
 * Created by Administrator on 2016/11/16.
 * 划转担保品查询bean
 */
public class RCollaterSearchBean extends BaseBean implements Parcelable{
    /**
     * entrust_type_name : 担保品返还
     * entrust_state_name : 未报
     * entrust_date : 2016-11-16
     * entrust_bs :
     * entrust_type :
     * exchange_type : 2
     * stock_code : 600000
     * business_amount :
     * compact_id :
     * entrust_amount : 100
     * entrust_price : 600.000
     * entrust_name :
     * exchange_type_name : 沪A
     * business_balance :
     * entrust_state : 0
     * entrust_no : 32353
     * trade_name :
     * stock_account : E000004216
     * report_no : 32353
     * stock_name :
     * cancel_amount :
     * entrust_time : 14:15:23
     * business_price :
     */
    @JsonKey("entrust_type_name")
    private String entrust_type_name;
    @JsonKey("entrust_state_name")
    private String entrust_state_name;
    @JsonKey("entrust_date")
    private String entrust_date;
    @JsonKey("entrust_bs")
    private String entrust_bs;
    @JsonKey("entrust_type")
    private String entrust_type;
    @JsonKey("exchange_type")
    private String exchange_type;
    @JsonKey("stock_code")
    private String stock_code;
    @JsonKey("business_amount")
    private String business_amount;
    @JsonKey("compact_id")
    private String compact_id;
    @JsonKey("entrust_amount")
    private String entrust_amount;
    @JsonKey("entrust_price")
    private String entrust_price;
    @JsonKey("entrust_name")
    private String entrust_name;
    @JsonKey("exchange_type_name")
    private String exchange_type_name;
    @JsonKey("business_balance")
    private String business_balance;
    @JsonKey("entrust_state")
    private String entrust_state;
    @JsonKey("entrust_no")
    private String entrust_no;
    @JsonKey("trade_name")
    private String trade_name;
    @JsonKey("stock_account")
    private String stock_account;
    @JsonKey("report_no")
    private String report_no;
    @JsonKey("stock_name")
    private String stock_name;
    @JsonKey("cancel_amount")
    private String cancel_amount;
    @JsonKey("entrust_time")
    private String entrust_time;
    @JsonKey("business_price")
    private String business_price;
    public RCollaterSearchBean() {
    }

    protected RCollaterSearchBean(Parcel in) {
        this.entrust_type_name = in.readString();
        this.entrust_state_name = in.readString();
        this.entrust_date = in.readString();
        this.entrust_bs = in.readString();
        this.entrust_type = in.readString();
        this.exchange_type = in.readString();
        this.stock_code = in.readString();
        this.business_amount = in.readString();
        this.compact_id = in.readString();
        this.entrust_amount = in.readString();
        this.entrust_price = in.readString();
        this.entrust_name = in.readString();
        this.exchange_type_name = in.readString();
        this.business_balance = in.readString();
        this.entrust_state = in.readString();
        this.entrust_no = in.readString();
        this.trade_name = in.readString();
        this.stock_account = in.readString();
        this.report_no = in.readString();
        this.stock_name = in.readString();
        this.cancel_amount = in.readString();
        this.entrust_time = in.readString();
        this.business_price = in.readString();
    }

    public String getEntrust_type_name() {
        return entrust_type_name;
    }

    public void setEntrust_type_name(String entrust_type_name) {
        this.entrust_type_name = entrust_type_name;
    }

    public String getEntrust_state_name() {
        return entrust_state_name;
    }

    public void setEntrust_state_name(String entrust_state_name) {
        this.entrust_state_name = entrust_state_name;
    }

    public String getEntrust_date() {
        return entrust_date;
    }

    public void setEntrust_date(String entrust_date) {
        this.entrust_date = entrust_date;
    }

    public String getEntrust_bs() {
        return entrust_bs;
    }

    public void setEntrust_bs(String entrust_bs) {
        this.entrust_bs = entrust_bs;
    }

    public String getEntrust_type() {
        return entrust_type;
    }

    public void setEntrust_type(String entrust_type) {
        this.entrust_type = entrust_type;
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

    public String getBusiness_amount() {
        return business_amount;
    }

    public void setBusiness_amount(String business_amount) {
        this.business_amount = business_amount;
    }

    public String getCompact_id() {
        return compact_id;
    }

    public void setCompact_id(String compact_id) {
        this.compact_id = compact_id;
    }

    public String getEntrust_amount() {
        return entrust_amount;
    }

    public void setEntrust_amount(String entrust_amount) {
        this.entrust_amount = entrust_amount;
    }

    public String getEntrust_price() {
        return entrust_price;
    }

    public void setEntrust_price(String entrust_price) {
        this.entrust_price = entrust_price;
    }

    public String getEntrust_name() {
        return entrust_name;
    }

    public void setEntrust_name(String entrust_name) {
        this.entrust_name = entrust_name;
    }

    public String getExchange_type_name() {
        return exchange_type_name;
    }

    public void setExchange_type_name(String exchange_type_name) {
        this.exchange_type_name = exchange_type_name;
    }

    public String getBusiness_balance() {
        return business_balance;
    }

    public void setBusiness_balance(String business_balance) {
        this.business_balance = business_balance;
    }

    public String getEntrust_state() {
        return entrust_state;
    }

    public void setEntrust_state(String entrust_state) {
        this.entrust_state = entrust_state;
    }

    public String getEntrust_no() {
        return entrust_no;
    }

    public void setEntrust_no(String entrust_no) {
        this.entrust_no = entrust_no;
    }

    public String getTrade_name() {
        return trade_name;
    }

    public void setTrade_name(String trade_name) {
        this.trade_name = trade_name;
    }

    public String getStock_account() {
        return stock_account;
    }

    public void setStock_account(String stock_account) {
        this.stock_account = stock_account;
    }

    public String getReport_no() {
        return report_no;
    }

    public void setReport_no(String report_no) {
        this.report_no = report_no;
    }

    public String getStock_name() {
        return stock_name;
    }

    public void setStock_name(String stock_name) {
        this.stock_name = stock_name;
    }

    public String getCancel_amount() {
        return cancel_amount;
    }

    public void setCancel_amount(String cancel_amount) {
        this.cancel_amount = cancel_amount;
    }

    public String getEntrust_time() {
        return entrust_time;
    }

    public void setEntrust_time(String entrust_time) {
        this.entrust_time = entrust_time;
    }

    public String getBusiness_price() {
        return business_price;
    }

    public void setBusiness_price(String business_price) {
        this.business_price = business_price;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.entrust_type_name);
        dest.writeString(this.entrust_state_name);
        dest.writeString(this.entrust_date);
        dest.writeString(this.entrust_bs);
        dest.writeString(this.entrust_type);
        dest.writeString(this.exchange_type);
        dest.writeString(this.stock_code);
        dest.writeString(this.business_amount);
        dest.writeString(this.compact_id);
        dest.writeString(this.entrust_amount);
        dest.writeString(this.entrust_price);
        dest.writeString(this.entrust_name);
        dest.writeString(this.exchange_type_name);
        dest.writeString(this.business_balance);
        dest.writeString(this.entrust_state);
        dest.writeString(this.entrust_no);
        dest.writeString(this.trade_name);
        dest.writeString(this.stock_account);
        dest.writeString(this.report_no);
        dest.writeString(this.stock_name);
        dest.writeString(this.cancel_amount);
        dest.writeString(this.entrust_time);
        dest.writeString(this.business_price);
    }


    public static final Creator<RCollaterSearchBean> CREATOR = new Creator<RCollaterSearchBean>() {
        @Override
        public RCollaterSearchBean createFromParcel(Parcel source) {
            return new RCollaterSearchBean(source);
        }

        @Override
        public RCollaterSearchBean[] newArray(int size) {
            return new RCollaterSearchBean[size];
        }
    };
}
