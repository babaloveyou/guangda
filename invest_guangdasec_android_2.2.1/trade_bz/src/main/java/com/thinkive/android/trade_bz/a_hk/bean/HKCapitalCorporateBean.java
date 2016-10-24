package com.thinkive.android.trade_bz.a_hk.bean;

import com.thinkive.android.trade_bz.a_stock.bean.BaseBean;
import com.thinkive.android.trade_bz.others.JsonKey;

/**
 * 公司行为委托查询
 * Created by xuemei on 2016/6/13.
 */
public class HKCapitalCorporateBean extends BaseBean {
    @JsonKey("corpbehavior_code")
    private String corpbehavior_code = "";
    @JsonKey("stock_name")
    private String stock_name = "";
    @JsonKey("trust_status_fj")
    private String trust_status_fj = "";
    @JsonKey("report_id")
    private String report_id = "";
    @JsonKey("business_type")
    private String business_type = "";
    @JsonKey("report_type_name")
    private String report_type_name = "";
    @JsonKey("apply_time")
    private String apply_time = "";
    @JsonKey("business_type_name")
    private String business_type_name = "";
    @JsonKey("report_type")
    private String report_type = "";
    @JsonKey("entrust_amount")
    private String entrust_amount = "";
    @JsonKey("business_id")
    private String business_id = "";
    @JsonKey("stock_code")
    private String stock_code = "";

    public void setCorpbehavior_code(String corpbehavior_code) {
        this.corpbehavior_code = corpbehavior_code;
    }

    public void setStock_name(String stock_name) {
        this.stock_name = stock_name;
    }

    public void setTrust_status_fj(String trust_status_fj) {
        this.trust_status_fj = trust_status_fj;
    }

    public void setReport_id(String report_id) {
        this.report_id = report_id;
    }

    public void setBusiness_type(String business_type) {
        this.business_type = business_type;
    }

    public void setReport_type_name(String report_type_name) {
        this.report_type_name = report_type_name;
    }

    public void setApply_time(String apply_time) {
        this.apply_time = apply_time;
    }

    public void setBusiness_type_name(String business_type_name) {
        this.business_type_name = business_type_name;
    }

    public void setReport_type(String report_type) {
        this.report_type = report_type;
    }

    public void setEntrust_amount(String entrust_amount) {
        this.entrust_amount = entrust_amount;
    }

    public void setBusiness_id(String business_id) {
        this.business_id = business_id;
    }

    public void setStock_code(String stock_code) {
        this.stock_code = stock_code;
    }

    public String getCorpbehavior_code() {
        return corpbehavior_code;
    }

    public String getStock_name() {
        return stock_name;
    }

    public String getTrust_status_fj() {
        return trust_status_fj;
    }

    public String getReport_id() {
        return report_id;
    }

    public String getBusiness_type() {
        return business_type;
    }

    public String getReport_type_name() {
        return report_type_name;
    }

    public String getApply_time() {
        return apply_time;
    }

    public String getBusiness_type_name() {
        return business_type_name;
    }

    public String getReport_type() {
        return report_type;
    }

    public String getEntrust_amount() {
        return entrust_amount;
    }

    public String getBusiness_id() {
        return business_id;
    }

    public String getStock_code() {
        return stock_code;
    }
}
