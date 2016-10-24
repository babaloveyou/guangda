package com.thinkive.android.trade_bz.a_in.bean;

import android.os.Parcel;

import com.thinkive.android.trade_bz.a_stock.bean.BaseBean;
import com.thinkive.android.trade_bz.others.JsonKey;

/**
 * 场内基金查询（301514）结果对应的bean
 */
public class InFundQueryBean extends BaseBean{
    @JsonKey("stock_code")
    private String stock_code = "";//证券代码
    @JsonKey("stock_name")
    private String stock_name = "";//证券名称
    @JsonKey("exchange_type")
    private String exchange_type = "";//交易市场类别
    @JsonKey("stock_type")
    private String stock_type = "";//证券类别
    @JsonKey("up_limit")
    private String up_limit = "";//涨停价
    @JsonKey("down_limit")
    private String down_limit = "";//跌停价
    @JsonKey("enable_balance")
    private String enable_balance = "";//可用资金
    @JsonKey("stock_account")
    private String stock_account = "";//证券账号
    @JsonKey("stock_max_amount")
    private String stock_max_amount = "";//证券最大可买/卖数量
    @JsonKey("price")
    private String price = "";//当前价格
    @JsonKey("buy_unit")
    private String buy_unit = "";//每手股数
    @JsonKey("step_price")
    private String step_price = "";//价差

    public InFundQueryBean() {
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

    public String getExchange_type() {
        return exchange_type;
    }

    public void setExchange_type(String exchange_type) {
        this.exchange_type = exchange_type;
    }

    public String getStock_type() {
        return stock_type;
    }

    public void setStock_type(String stock_type) {
        this.stock_type = stock_type;
    }

    public String getUp_limit() {
        return up_limit;
    }

    public void setUp_limit(String up_limit) {
        this.up_limit = up_limit;
    }

    public String getDown_limit() {
        return down_limit;
    }

    public void setDown_limit(String down_limit) {
        this.down_limit = down_limit;
    }

    public String getEnable_balance() {
        return enable_balance;
    }

    public void setEnable_balance(String enable_balance) {
        this.enable_balance = enable_balance;
    }

    public String getStock_account() {
        return stock_account;
    }

    public void setStock_account(String stock_account) {
        this.stock_account = stock_account;
    }

    public String getStock_max_amount() {
        return stock_max_amount;
    }

    public void setStock_max_amount(String stock_max_amount) {
        this.stock_max_amount = stock_max_amount;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getBuy_unit() {
        return buy_unit;
    }

    public void setBuy_unit(String buy_unit) {
        this.buy_unit = buy_unit;
    }

    public String getStep_price() {
        return step_price;
    }

    public void setStep_price(String step_price) {
        this.step_price = step_price;
    }

    public InFundQueryBean(Parcel in) {
        this.stock_code = in.readString();
        this.stock_name = in.readString();
        this.exchange_type = in.readString();
        this.stock_type = in.readString();
        this.up_limit = in.readString();
        this.down_limit = in.readString();
        this.enable_balance = in.readString();
        this.stock_account = in.readString();
        this.stock_max_amount = in.readString();
        this.price = in.readString();
        this.buy_unit = in.readString();
        this.step_price = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.stock_code);
        dest.writeString(this.stock_name);
        dest.writeString(this.exchange_type);
        dest.writeString(this.stock_type);;
        dest.writeString(this.up_limit);
        dest.writeString(this.down_limit);
        dest.writeString(this.enable_balance);
        dest.writeString(this.stock_account);
        dest.writeString(this.stock_max_amount);
        dest.writeString(this.price);
        dest.writeString(this.buy_unit);
        dest.writeString(this.step_price);
    }

    public static final Creator<InFundQueryBean> CREATOR = new Creator<InFundQueryBean>() {
        @Override
        public InFundQueryBean createFromParcel(Parcel source) {
            return new InFundQueryBean(source);
        }

        @Override
        public InFundQueryBean[] newArray(int size) {
            return new InFundQueryBean[size];
        }
    };
}
