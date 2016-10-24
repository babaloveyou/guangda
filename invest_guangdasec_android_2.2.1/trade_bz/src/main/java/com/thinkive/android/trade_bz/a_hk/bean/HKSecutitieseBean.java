package com.thinkive.android.trade_bz.a_hk.bean;

import com.thinkive.android.trade_bz.a_stock.bean.BaseBean;
import com.thinkive.android.trade_bz.others.JsonKey;


/**
 * 创建者     舒旺
 * 创建时间   2016/6/3 10:12
 * 更新者     $Author$
 * 更新时间   $Date$
 */
public class HKSecutitieseBean  extends BaseBean {
    @JsonKey("update_date")
    private String update_date = "";
    @JsonKey("lgtrdstatusName")
    private String lgtrdstatusName = "";
    @JsonKey("stock_code")
    private String stock_code = "";
    @JsonKey("stock_name")
    private String stock_name = "";
    @JsonKey("zstrdstatusName")
    private String zstrdstatusName = "";

    public void setUpdate_date(String update_date) {
        this.update_date = update_date;
    }

    public void setLgtrdstatusName(String lgtrdstatusName) {
        this.lgtrdstatusName = lgtrdstatusName;
    }

    public void setStock_code(String stock_code) {
        this.stock_code = stock_code;
    }

    public void setStock_name(String stock_name) {
        this.stock_name = stock_name;
    }

    public void setZstrdstatusName(String zstrdstatusName) {
        this.zstrdstatusName = zstrdstatusName;
    }

    public String getUpdate_date() {
        return update_date;
    }

    public String getLgtrdstatusName() {
        return lgtrdstatusName;
    }

    public String getStock_code() {
        return stock_code;
    }

    public String getStock_name() {
        return stock_name;
    }

    public String getZstrdstatusName() {
        return zstrdstatusName;
    }
}
