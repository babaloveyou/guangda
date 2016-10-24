package com.thinkive.android.trade_bz.a_hk.bean;

import com.thinkive.android.trade_bz.a_stock.bean.BaseBean;
import com.thinkive.android.trade_bz.others.JsonKey;


/**
 * 创建者     舒旺(301601)
 * 创建时间   2016/6/6 13:56
 * 描述	     港股通额度查询Bean
 * 更新者     $Author$
 * 更新时间   $Date$
 */
public class HKCapitalLimitBean  extends BaseBean {
    @JsonKey("product_status")
    private String product_status = "";
    @JsonKey("surplus_quota")
    private String surplus_quota = "";
    @JsonKey("total_quota")
    private String total_quota = "";
    @JsonKey("exchange_type_name")
    private String exchange_type_name = "";
    @JsonKey("mkttrdstatus_name")
    private String mkttrdstatus_name = "";
    @JsonKey("upddate")
    private String upddate = "";

    public String getExchange_type_name() {
        return exchange_type_name;
    }

    public void setExchange_type_name(String exchange_type_name) {
        this.exchange_type_name = exchange_type_name;
    }

    public String getMkttrdstatus_name() {
        return mkttrdstatus_name;
    }

    public void setMkttrdstatus_name(String mkttrdstatus_name) {
        this.mkttrdstatus_name = mkttrdstatus_name;
    }

    public String getUpddate() {
        return upddate;
    }

    public void setUpddate(String upddate) {
        this.upddate = upddate;
    }

    public void setProduct_status(String product_status) {
        this.product_status = product_status;
    }

    public void setSurplus_quota(String surplus_quota) {
        this.surplus_quota = surplus_quota;
    }

    public void setTotal_quota(String total_quota) {
        this.total_quota = total_quota;
    }

    public String getProduct_status() {
        return product_status;
    }

    public String getSurplus_quota() {
        return surplus_quota;
    }

    public String getTotal_quota() {
        return total_quota;
    }
}
