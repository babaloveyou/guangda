package com.thinkive.android.trade_bz.a_stock.bean;


import android.os.Parcel;

import com.thinkive.android.trade_bz.others.JsonKey;

/**
 * 我的持仓数据列表
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/6/23
 */
public class MyStoreStockBean extends BaseBean {
    /**
     *  证券名称
     */
    @JsonKey("stock_name")
    private String stock_name = "";
    /**
     * 证券代码
     */
    @JsonKey("stock_code")
    private String stock_code = "";
    /**
     * 证券市值
     */
    @JsonKey("market_value")
    private String market_value = "";
    /**
     *浮动盈亏
     */
    @JsonKey("float_yk")
    private String float_yk = "";
    /**
     *浮动盈亏%
     */
    @JsonKey("float_yk_per")
    private String float_yk_per = "";
    /**
     *持仓数量
     */
    @JsonKey("cost_amount")
    private String cost_amount = "";
    /**
     *可用数量
     */
    @JsonKey("enable_amount")
    private String enable_amount = "";
    /**
     * 持仓成本
     */
    @JsonKey("cost_balance")
    private String cost_balance = "";
    /**
     *最新价
     */
    @JsonKey("last_price")
    private String last_price = "";
    /**
     *交易市场类别
     */
    @JsonKey("exchange_type")
    private String exchange_type = "";
    /**
     *交易市场名称
     */
    @JsonKey("exchange_type_name")
    private String exchange_type_name = "";
    /**
     *证券账号
     */
    @JsonKey("stock_account")
    private String stock_account = "";
    /**
     *成本价
     */
    @JsonKey("cost_price")
    private String cost_price = "";
    /**
     *在途股份数
     */
    @JsonKey("share_otd")
    private String share_otd = "";
    /**
     *折算率
     */
    @JsonKey("pledgerate")
    private String pledgerate = "";
    /**
     *交易市场
     */
    @JsonKey("market")
    private String market = "";
    /**
     *折算率
     */
    @JsonKey("pledge_rate")
    private String pledge_rate = "";

    public MyStoreStockBean() {
    }

    public MyStoreStockBean(String stock_name, String cost_balance,
                            String last_price, String enable_amount, String cost_amount,
                            String float_yk_per, String float_yk, String market_value) {
        this.stock_name = stock_name;
        this.cost_balance = cost_balance;
        this.last_price = last_price;
        this.enable_amount = enable_amount;
        this.cost_amount = cost_amount;
        this.float_yk_per = float_yk_per;
        this.float_yk = float_yk;
        this.market_value = market_value;
    }

    public String getStock_name() {
        return stock_name;
    }

    public String getLast_price() {
        return last_price;
    }

    public String getCost_amount() {
        return cost_amount;
    }

    public String getFloat_yk() {
        return float_yk;
    }

    public String getMarket_value() {
        return market_value;
    }

    public String getFloat_yk_per() {
        return float_yk_per;
    }

    public String getEnable_amount() {
        return enable_amount;
    }

    public String getCost_balance() {
        return cost_balance;
    }

    public void setStock_name(String stock_name) {
        this.stock_name = stock_name;
    }

    public void setLast_price(String last_price) {
        this.last_price = last_price;
    }

    public void setCost_balance(String cost_balance) {
        this.cost_balance = cost_balance;
    }

    public void setEnable_amount(String enable_amount) {
        this.enable_amount = enable_amount;
    }

    public void setCost_amount(String cost_amount) {
        this.cost_amount = cost_amount;
    }

    public void setFloat_yk_per(String float_yk_per) {
        this.float_yk_per = float_yk_per;
    }

    public void setFloat_yk(String float_yk) {
        this.float_yk = float_yk;
    }

    public void setMarket_value(String market_value) {
        this.market_value = market_value;
    }

    public String getStock_code() {
        return stock_code;
    }

    public void setStock_code(String stock_code) {
        this.stock_code = stock_code;
    }

    public String getCost_price() {
        return cost_price;
    }

    public void setCost_price(String cost_price) {
        this.cost_price = cost_price;
    }

    public String getExchange_type_name() {
        return exchange_type_name;
    }

    public void setExchange_type_name(String exchange_type_name) {
        this.exchange_type_name = exchange_type_name;
    }

    public String getExchange_type() {
        return exchange_type;
    }

    public void setExchange_type(String exchange_type) {
        this.exchange_type = exchange_type;
    }

    public String getShare_otd() {
        return share_otd;
    }

    public void setShare_otd(String share_otd) {
        this.share_otd = share_otd;
    }

    public String getStock_account() {
        return stock_account;
    }

    public void setStock_account(String stock_account) {
        this.stock_account = stock_account;
    }

    public String getPledgerate() {
        return pledgerate;
    }

    public void setPledgerate(String pledgerate) {
        this.pledgerate = pledgerate;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public String getPledge_rate() {
        return pledge_rate;
    }

    public void setPledge_rate(String pledge_rate) {
        this.pledge_rate = pledge_rate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.stock_name);
        dest.writeString(this.stock_code);
        dest.writeString(this.market_value);
        dest.writeString(this.float_yk);
        dest.writeString(this.float_yk_per);
        dest.writeString(this.cost_amount);
        dest.writeString(this.enable_amount);
        dest.writeString(this.cost_balance);
        dest.writeString(this.last_price);
        dest.writeString(this.exchange_type);
        dest.writeString(this.exchange_type_name);
        dest.writeString(this.stock_account);
        dest.writeString(this.cost_price);
        dest.writeString(this.share_otd);
        dest.writeString(this.pledgerate);
        dest.writeString(this.market);
        dest.writeString(this.pledge_rate);
    }

    protected MyStoreStockBean(Parcel in) {
        this.stock_name = in.readString();
        this.stock_code = in.readString();
        this.market_value = in.readString();
        this.float_yk = in.readString();
        this.float_yk_per = in.readString();
        this.cost_amount = in.readString();
        this.enable_amount = in.readString();
        this.cost_balance = in.readString();
        this.last_price = in.readString();
        this.exchange_type = in.readString();
        this.exchange_type_name = in.readString();
        this.stock_account = in.readString();
        this.cost_price = in.readString();
        this.share_otd = in.readString();
        this.pledgerate = in.readString();
        this.market = in.readString();
        this.pledge_rate = in.readString();
    }

    public static final Creator<MyStoreStockBean> CREATOR = new Creator<MyStoreStockBean>() {
        @Override
        public MyStoreStockBean createFromParcel(Parcel source) {
            return new MyStoreStockBean(source);
        }

        @Override
        public MyStoreStockBean[] newArray(int size) {
            return new MyStoreStockBean[size];
        }
    };
}
