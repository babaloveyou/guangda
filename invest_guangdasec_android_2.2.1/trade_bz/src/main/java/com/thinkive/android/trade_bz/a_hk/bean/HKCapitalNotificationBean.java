package com.thinkive.android.trade_bz.a_hk.bean;

import com.thinkive.android.trade_bz.a_stock.bean.BaseBean;
import com.thinkive.android.trade_bz.others.JsonKey;


/**
 * 创建者     舒旺
 * 创建时间   2016/6/7 17:46
 * 描述	     通知信息查询的Bean
 * 更新者     $Author$
 * 更新时间   $Date$
 */
public class HKCapitalNotificationBean extends BaseBean {
    @JsonKey("sno")
    private String sno = "";
    @JsonKey("circulation_type_name")
    private String circulation_type_name = "";
    @JsonKey("money_type_name")
    private String money_type_name = "";
    @JsonKey("current_price")
    private String current_price = "";
    @JsonKey("authority_times")
    private String authority_times = "";
    @JsonKey("authority_type_name")
    private String authority_type_name = "";
    @JsonKey("note_date")
    private String note_date = "";
    @JsonKey("stock_name")
    private String stock_name = "";
    @JsonKey("stock_code")
    private String stock_code = "";
    @JsonKey("current_rate")
    private String current_rate = "";
    @JsonKey("market_year")
    private String market_year = "";

    public String getStock_name() {
        return stock_name;
    }

    public void setStock_name(String stock_name) {
        this.stock_name = stock_name;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public void setCirculation_type_name(String circulation_type_name) {
        this.circulation_type_name = circulation_type_name;
    }

    public void setMoney_type_name(String money_type_name) {
        this.money_type_name = money_type_name;
    }

    public void setCurrent_price(String current_price) {
        this.current_price = current_price;
    }

    public void setAuthority_times(String authority_times) {
        this.authority_times = authority_times;
    }

    public void setAuthority_type_name(String authority_type_name) {
        this.authority_type_name = authority_type_name;
    }

    public void setNote_date(String note_date) {
        this.note_date = note_date;
    }

    public void setStock_code(String stock_code) {
        this.stock_code = stock_code;
    }

    public void setCurrent_rate(String current_rate) {
        this.current_rate = current_rate;
    }

    public void setMarket_year(String market_year) {
        this.market_year = market_year;
    }

    public String getSno() {
        return sno;
    }

    public String getCirculation_type_name() {
        return circulation_type_name;
    }

    public String getMoney_type_name() {
        return money_type_name;
    }

    public String getCurrent_price() {
        return current_price;
    }

    public String getAuthority_times() {
        return authority_times;
    }

    public String getAuthority_type_name() {
        return authority_type_name;
    }

    public String getNote_date() {
        return note_date;
    }

    public String getStock_code() {
        return stock_code;
    }

    public String getCurrent_rate() {
        return current_rate;
    }

    public String getMarket_year() {
        return market_year;
    }
}
