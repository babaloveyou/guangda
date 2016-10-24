package com.thinkive.android.trade_bz.a_in.bean;

import android.os.Parcel;

import com.thinkive.android.trade_bz.a_stock.bean.BaseBean;
import com.thinkive.android.trade_bz.others.JsonKey;

/**
 *
 * Description：证券持仓查询结果（301503） 对应的实体 <br>
 * Author：晏政清 <br>
 * Corporation：深圳市思迪信息技术股份有限公司 <br>
 * Date：2016/8/12 <br>
 */

public class InFundSercuritiesPositionsQueryBean extends BaseBean{
    @JsonKey("stock_code")
    private String stock_code = "";//证券代码
    @JsonKey("stock_name")
    private String stock_name = "";//证券名称
    @JsonKey("exchange_type")
    private String exchange_type = "";//交易市场类别
    @JsonKey("exchange_type_name")
    private String exchange_type_name = "";//交易市场名称
    @JsonKey("stock_account")
    private String stock_account = "";//证券账号
    @JsonKey("enable_amount")
    private String enable_amount = "";//可用数量
    @JsonKey("last_price")
    private String last_price = "";//最新价
    @JsonKey("cost_price")
    private String cost_price = "";//成本价
    @JsonKey("market_value")
    private String market_value = "";//证券市值
    @JsonKey("float_yk")
    private String float_yk = "";//浮动盈亏
    @JsonKey("float_yk_per")
    private String float_yk_per = "";//浮动盈亏%
    @JsonKey("cost_balance")
    private String cost_balance = "";//持仓成本
    @JsonKey("share_otd")
    private String share_otd = "";//在途股份数
    @JsonKey("cost_amount")
    private String cost_amount = "";//持仓数量

    public InFundSercuritiesPositionsQueryBean() {
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

    public String getEnable_amount() {
        return enable_amount;
    }

    public void setEnable_amount(String enable_amount) {
        this.enable_amount = enable_amount;
    }

    public String getLast_price() {
        return last_price;
    }

    public void setLast_price(String last_price) {
        this.last_price = last_price;
    }

    public String getCost_price() {
        return cost_price;
    }

    public void setCost_price(String cost_price) {
        this.cost_price = cost_price;
    }

    public String getMarket_value() {
        return market_value;
    }

    public void setMarket_value(String market_value) {
        this.market_value = market_value;
    }

    public String getFloat_yk() {
        return float_yk;
    }

    public void setFloat_yk(String float_yk) {
        this.float_yk = float_yk;
    }

    public String getFloat_yk_per() {
        return float_yk_per;
    }

    public void setFloat_yk_per(String float_yk_per) {
        this.float_yk_per = float_yk_per;
    }

    public String getCost_balance() {
        return cost_balance;
    }

    public void setCost_balance(String cost_balance) {
        this.cost_balance = cost_balance;
    }

    public String getShare_otd() {
        return share_otd;
    }

    public void setShare_otd(String share_otd) {
        this.share_otd = share_otd;
    }

    public String getCost_amount() {
        return cost_amount;
    }

    public void setCost_amount(String cost_amount) {
        this.cost_amount = cost_amount;
    }

    public InFundSercuritiesPositionsQueryBean(Parcel in) {
        this.stock_code = in.readString();
        this.stock_name = in.readString();
        this.exchange_type = in.readString();
        this.exchange_type_name = in.readString();
        this.stock_account = in.readString();
        this.enable_amount = in.readString();
        this.last_price = in.readString();
        this.cost_price = in.readString();
        this.market_value = in.readString();
        this.float_yk = in.readString();
        this.float_yk_per = in.readString();
        this.cost_balance = in.readString();
        this.share_otd = in.readString();
        this.cost_amount = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.stock_code);
        dest.writeString(this.stock_name);
        dest.writeString(this.exchange_type);
        dest.writeString(this.exchange_type_name);
        dest.writeString(this.stock_account);
        dest.writeString(this.enable_amount);
        dest.writeString(this.last_price);
        dest.writeString(this.cost_price);
        dest.writeString(this.market_value);
        dest.writeString(this.float_yk);
        dest.writeString(this.float_yk_per);
        dest.writeString(this.cost_balance);
        dest.writeString(this.share_otd);
        dest.writeString(this.cost_amount);
    }

    public static Creator<InFundSercuritiesPositionsQueryBean> CREATOR = new Creator<InFundSercuritiesPositionsQueryBean>() {
        @Override
        public InFundSercuritiesPositionsQueryBean createFromParcel(Parcel source) {
            return new InFundSercuritiesPositionsQueryBean(source);
        }

        @Override
        public InFundSercuritiesPositionsQueryBean[] newArray(int size) {
            return new InFundSercuritiesPositionsQueryBean[size];
        }
    };
}
