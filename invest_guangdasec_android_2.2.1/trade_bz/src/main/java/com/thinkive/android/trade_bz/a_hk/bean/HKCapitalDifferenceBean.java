package com.thinkive.android.trade_bz.a_hk.bean;

import com.thinkive.android.trade_bz.a_stock.bean.BaseBean;
import com.thinkive.android.trade_bz.others.JsonKey;


/**
 * 创建者     舒旺
 * 创建时间   2016/6/6 16:40
 * 描述	      价差查询Bean
 * 更新者     $Author$
 * 更新时间   $Date$
 */
public class HKCapitalDifferenceBean  extends BaseBean {
    @JsonKey("step_price")
    private String step_price ="";
    @JsonKey("up_limit")
    private String up_limit ="";
    @JsonKey("exchange_type_name")
    private String exchange_type_name ="";
    @JsonKey("stock_type_name")
    private String stock_type_name ="";
    @JsonKey("down_limit")
    private String down_limit ="";

    public void setStep_price(String step_price) {
        this.step_price = step_price;
    }

    public void setUp_limit(String up_limit) {
        this.up_limit = up_limit;
    }

    public void setExchange_type_name(String exchange_type_name) {
        this.exchange_type_name = exchange_type_name;
    }

    public void setStock_type_name(String stock_type_name) {
        this.stock_type_name = stock_type_name;
    }

    public void setDown_limit(String down_limit) {
        this.down_limit = down_limit;
    }

    public String getStep_price() {
        return step_price;
    }

    public String getUp_limit() {
        return up_limit;
    }

    public String getExchange_type_name() {
        return exchange_type_name;
    }

    public String getStock_type_name() {
        return stock_type_name;
    }

    public String getDown_limit() {
        return down_limit;
    }
}
