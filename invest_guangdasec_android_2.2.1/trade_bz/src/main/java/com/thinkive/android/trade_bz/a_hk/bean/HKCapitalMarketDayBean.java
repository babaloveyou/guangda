package com.thinkive.android.trade_bz.a_hk.bean;

import com.thinkive.android.trade_bz.a_stock.bean.BaseBean;
import com.thinkive.android.trade_bz.others.JsonKey;


/**
 * 创建者     舒旺
 * 创建时间   2016/6/6 20:15
 * 描述	      港股通交易日历查询
 * 更新者     $Author$
 * 更新时间   $Date$
 */
public class HKCapitalMarketDayBean extends BaseBean {
    @JsonKey("exchange_type_name")
    private String exchange_type_name = "";
    @JsonKey("begin_time")
    private String begin_time = "";
    @JsonKey("money_type_name")
    private String money_type_name = "";
    @JsonKey("businessflag")
    private String businessflag = "";
    @JsonKey("commitflag")
    private String commitflag = "";

    public void setExchange_type_name(String exchange_type_name) {
        this.exchange_type_name = exchange_type_name;
    }

    public void setBegin_time(String begin_time) {
        this.begin_time = begin_time;
    }

    public void setMoney_type_name(String money_type_name) {
        this.money_type_name = money_type_name;
    }

    public void setBusinessflag(String businessflag) {
        this.businessflag = businessflag;
    }

    public void setCommitflag(String commitflag) {
        this.commitflag = commitflag;
    }

    public String getExchange_type_name() {
        return exchange_type_name;
    }

    public String getBegin_time() {
        return begin_time;
    }

    public String getMoney_type_name() {
        return money_type_name;
    }

    public String getBusinessflag() {
        return businessflag;
    }

    public String getCommitflag() {
        return commitflag;
    }
}
