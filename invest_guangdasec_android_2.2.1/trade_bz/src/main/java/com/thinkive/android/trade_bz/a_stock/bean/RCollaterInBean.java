package com.thinkive.android.trade_bz.a_stock.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.thinkive.android.trade_bz.others.JsonKey;

/**
 * Created by Administrator on 2016/11/15.
 */
public class RCollaterInBean extends BaseBean implements Parcelable {

    /**
     * float_yk : 0.00
     * cost_price : 0.0
     * available_remaining : 50000
     * cost_balance : 0.000
     * float_yk_per : --
     * exchange_type : 2
     * last_price : 0.000
     * stock_code : SHXGED
     * enable_amount : 50000
     * share_otd :
     * market_value : 0.0
     * cost_amount : 50000
     * exchange_type_name : 沪A
     * day_float_yk :
     * stock_account : A439964219
     * stock_name : 沪新股额
     */
    @JsonKey("float_yk")
    private String float_yk;
    @JsonKey("cost_price")
    private String cost_price;
    @JsonKey("available_remaining")
    private String available_remaining;
    @JsonKey("cost_balance")
    private String cost_balance;
    @JsonKey("float_yk_per")
    private String float_yk_per;
    @JsonKey("exchange_type")
    private String exchange_type;
    @JsonKey("last_price")
    private String last_price;
    @JsonKey("stock_code")
    private String stock_code;
    @JsonKey("enable_amount")
    private String enable_amount;
    @JsonKey("share_otd")
    private String share_otd;
    @JsonKey("market_value")
    private String market_value;
    @JsonKey("cost_amount")
    private String cost_amount;
    @JsonKey("exchange_type_name")
    private String exchange_type_name;
    @JsonKey("day_float_yk")
    private String day_float_yk;
    @JsonKey("stock_account")
    private String stock_account;
    @JsonKey("stock_name")
    private String stock_name;


    public String getFloat_yk() {
        return float_yk;
    }

    public void setFloat_yk(String float_yk) {
        this.float_yk = float_yk;
    }

    public String getCost_price() {
        return cost_price;
    }

    public void setCost_price(String cost_price) {
        this.cost_price = cost_price;
    }

    public String getAvailable_remaining() {
        return available_remaining;
    }

    public void setAvailable_remaining(String available_remaining) {
        this.available_remaining = available_remaining;
    }

    public String getCost_balance() {
        return cost_balance;
    }

    public void setCost_balance(String cost_balance) {
        this.cost_balance = cost_balance;
    }

    public String getFloat_yk_per() {
        return float_yk_per;
    }

    public void setFloat_yk_per(String float_yk_per) {
        this.float_yk_per = float_yk_per;
    }

    public String getExchange_type() {
        return exchange_type;
    }

    public void setExchange_type(String exchange_type) {
        this.exchange_type = exchange_type;
    }

    public String getLast_price() {
        return last_price;
    }

    public void setLast_price(String last_price) {
        this.last_price = last_price;
    }

    public String getStock_code() {
        return stock_code;
    }

    public void setStock_code(String stock_code) {
        this.stock_code = stock_code;
    }

    public String getEnable_amount() {
        return enable_amount;
    }

    public void setEnable_amount(String enable_amount) {
        this.enable_amount = enable_amount;
    }

    public String getShare_otd() {
        return share_otd;
    }

    public void setShare_otd(String share_otd) {
        this.share_otd = share_otd;
    }

    public String getMarket_value() {
        return market_value;
    }

    public void setMarket_value(String market_value) {
        this.market_value = market_value;
    }

    public String getCost_amount() {
        return cost_amount;
    }

    public void setCost_amount(String cost_amount) {
        this.cost_amount = cost_amount;
    }

    public String getExchange_type_name() {
        return exchange_type_name;
    }

    public void setExchange_type_name(String exchange_type_name) {
        this.exchange_type_name = exchange_type_name;
    }

    public String getDay_float_yk() {
        return day_float_yk;
    }

    public void setDay_float_yk(String day_float_yk) {
        this.day_float_yk = day_float_yk;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.float_yk);
        dest.writeString(this.cost_price);
        dest.writeString(this.available_remaining);
        dest.writeString(this.cost_balance);
        dest.writeString(this.float_yk_per);
        dest.writeString(this.exchange_type);
        dest.writeString(this.last_price);
        dest.writeString(this.stock_code);
        dest.writeString(this.enable_amount);
        dest.writeString(this.share_otd);
        dest.writeString(this.market_value);
        dest.writeString(this.cost_amount);
        dest.writeString(this.exchange_type_name);
        dest.writeString(this.day_float_yk);
        dest.writeString(this.stock_account);
        dest.writeString(this.stock_name);
    }

    public RCollaterInBean() {
    }

    public RCollaterInBean(Parcel in) {
        this.float_yk = in.readString();
        this.cost_price = in.readString();
        this.available_remaining = in.readString();
        this.cost_balance = in.readString();
        this.float_yk_per = in.readString();
        this.exchange_type = in.readString();
        this.last_price = in.readString();
        this.stock_code = in.readString();
        this.enable_amount = in.readString();
        this.share_otd = in.readString();
        this.market_value = in.readString();
        this.cost_amount = in.readString();
        this.exchange_type_name = in.readString();
        this.day_float_yk = in.readString();
        this.stock_account = in.readString();
        this.stock_name = in.readString();
    }

    public static final Creator<RCollaterInBean> CREATOR = new Creator<RCollaterInBean>() {
        @Override
        public RCollaterInBean createFromParcel(Parcel source) {
            return new RCollaterInBean(source);
        }

        @Override
        public RCollaterInBean[] newArray(int size) {
            return new RCollaterInBean[size];
        }
    };
}
