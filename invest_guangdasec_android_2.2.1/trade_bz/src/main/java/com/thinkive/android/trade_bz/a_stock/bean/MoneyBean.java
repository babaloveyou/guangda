package com.thinkive.android.trade_bz.a_stock.bean;

import com.thinkive.android.trade_bz.others.JsonKey;

/**
 * Created by Administrator on 2016/11/7.
 */
public class MoneyBean extends BaseBean {
    @JsonKey("dayfloat_yk")
    private String dayfloat_yk = "";
    @JsonKey("Z_float_yk")
    private String z_float_yk = "";
    @JsonKey("assert_val")
    private String assert_val = "";
    @JsonKey("market_val")
    private String market_val = "";
    @JsonKey("frozen_balance")
    private String frozen_balance = "";
    @JsonKey("money_type_name")
    private String money_type_name = "";

    @JsonKey("enable_balance")
    private String enable_balance = "";

    @JsonKey("money_type")
    private String money_type = "";
    @JsonKey("fetch_balance")
    private String fetch_balance = "";
    @JsonKey("current_balance")
    private String current_balance = "";


    @JsonKey("float_yk_per")
    private String float_yk_per = "";

    public String getDayfloat_yk() {
        return dayfloat_yk;
    }

    public void setDayfloat_yk(String dayfloat_yk) {
        this.dayfloat_yk = dayfloat_yk;
    }

    public String getZ_float_yk() {
        return z_float_yk;
    }

    public void setZ_float_yk(String z_float_yk) {
        this.z_float_yk = z_float_yk;
    }

    public String getAssert_val() {
        return assert_val;
    }

    public void setAssert_val(String assert_val) {
        this.assert_val = assert_val;
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

    public String getFloat_yk_per() {
        return float_yk_per;
    }

    public void setFloat_yk_per(String float_yk_per) {
        this.float_yk_per = float_yk_per;
    }
}
