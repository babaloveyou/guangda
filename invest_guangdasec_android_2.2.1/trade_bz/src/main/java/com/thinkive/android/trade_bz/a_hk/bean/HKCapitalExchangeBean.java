package com.thinkive.android.trade_bz.a_hk.bean;

import com.thinkive.android.trade_bz.a_stock.bean.BaseBean;
import com.thinkive.android.trade_bz.others.JsonKey;


/**
 * 创建者     舒旺
 * 创建时间   2016/6/6 17:23
 * 描述	       汇率查询Bean
 * 更新者     $Author$
 * 更新时间   $Date$
 */
public class HKCapitalExchangeBean extends BaseBean {
    @JsonKey("salerate")
    private String salerate = "";
    @JsonKey("settrate")
    private String settrate = "";
    @JsonKey("exchange_type_name")
    private String exchange_type_name = "";
    @JsonKey("sysdate")
    private String sysdate = "";
    @JsonKey("daybuyriserate")
    private String daybuyriserate = "";
    @JsonKey("money_type_name")
    private String money_type_name = "";
    @JsonKey("buyrate")
    private String buyrate = "";
    @JsonKey("daysaleriserate")
    private String daysaleriserate = "";
    @JsonKey("midrate")
    private String midrate = "";

    public void setSalerate(String salerate) {
        this.salerate = salerate;
    }

    public void setSettrate(String settrate) {
        this.settrate = settrate;
    }

    public void setExchange_type_name(String exchange_type_name) {
        this.exchange_type_name = exchange_type_name;
    }

    public void setSysdate(String sysdate) {
        this.sysdate = sysdate;
    }

    public void setDaybuyriserate(String daybuyriserate) {
        this.daybuyriserate = daybuyriserate;
    }

    public void setMoney_type_name(String money_type_name) {
        this.money_type_name = money_type_name;
    }

    public void setBuyrate(String buyrate) {
        this.buyrate = buyrate;
    }

    public void setDaysaleriserate(String daysaleriserate) {
        this.daysaleriserate = daysaleriserate;
    }

    public void setMidrate(String midrate) {
        this.midrate = midrate;
    }

    public String getSalerate() {
        return salerate;
    }

    public String getSettrate() {
        return settrate;
    }

    public String getExchange_type_name() {
        return exchange_type_name;
    }

    public String getSysdate() {
        return sysdate;
    }

    public String getDaybuyriserate() {
        return daybuyriserate;
    }

    public String getMoney_type_name() {
        return money_type_name;
    }

    public String getBuyrate() {
        return buyrate;
    }

    public String getDaysaleriserate() {
        return daysaleriserate;
    }

    public String getMidrate() {
        return midrate;
    }
}
