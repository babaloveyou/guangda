package com.thinkive.android.trade_bz.a_hk.bean;

import com.thinkive.android.trade_bz.a_stock.bean.BaseBean;
import com.thinkive.android.trade_bz.others.JsonKey;


/**
 * 投票委托查询查询
 * Created by xuemei on 2016/6/28.
 */
public class HKCapitalTrustBean extends BaseBean {
    @JsonKey("corpbehavior_code")
    private String corpbehavior_code = "";
    @JsonKey("stock_name")
    private String stock_name = "";
    @JsonKey("oppose_amount")
    private String oppose_amount = "";
    @JsonKey("apply_time")
    private String apply_time = "";
    @JsonKey("business_type_name")
    private String business_type_name = "";
    @JsonKey("report_type")
    private String report_type = "";
    @JsonKey("stock_account")
    private String stock_account = "";
    @JsonKey("entrust_amount")
    private String entrust_amount = "";
    @JsonKey("waive_amount")
    private String waive_amount = "";
    @JsonKey("result")
    private String result = "";
    @JsonKey("current_amount")
    private String current_amount = "";
    @JsonKey("motion_id")
    private String motion_id = "";
    @JsonKey("placard_id")
    private String placard_id = "";
    @JsonKey("trust_status_fj")
    private String trust_status_fj = "";
    @JsonKey("report_id")
    private String report_id = "";
    @JsonKey("business_type")
    private String business_type = "";
    @JsonKey("isin_code")
    private String isin_code = "";
    @JsonKey("result_code")
    private String result_code = "";
    @JsonKey("approve_amount")
    private String approve_amount = "";
    @JsonKey("stock_code")
    private String stock_code = "";
    @JsonKey("business_id")
    private String business_id = "";

    public void setCorpbehavior_code(String corpbehavior_code) {
        this.corpbehavior_code = corpbehavior_code;
    }

    public void setStock_name(String stock_name) {
        this.stock_name = stock_name;
    }

    public void setOppose_amount(String oppose_amount) {
        this.oppose_amount = oppose_amount;
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

    public void setStock_account(String stock_account) {
        this.stock_account = stock_account;
    }

    public void setEntrust_amount(String entrust_amount) {
        this.entrust_amount = entrust_amount;
    }

    public void setWaive_amount(String waive_amount) {
        this.waive_amount = waive_amount;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setCurrent_amount(String current_amount) {
        this.current_amount = current_amount;
    }

    public void setMotion_id(String motion_id) {
        this.motion_id = motion_id;
    }

    public void setPlacard_id(String placard_id) {
        this.placard_id = placard_id;
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

    public void setIsin_code(String isin_code) {
        this.isin_code = isin_code;
    }

    public void setResult_code(String result_code) {
        this.result_code = result_code;
    }

    public void setApprove_amount(String approve_amount) {
        this.approve_amount = approve_amount;
    }

    public void setStock_code(String stock_code) {
        this.stock_code = stock_code;
    }

    public void setBusiness_id(String business_id) {
        this.business_id = business_id;
    }

    public String getCorpbehavior_code() {
        return corpbehavior_code;
    }

    public String getStock_name() {
        return stock_name;
    }

    public String getOppose_amount() {
        return oppose_amount;
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

    public String getStock_account() {
        return stock_account;
    }

    public String getEntrust_amount() {
        return entrust_amount;
    }

    public String getWaive_amount() {
        return waive_amount;
    }

    public String getResult() {
        return result;
    }

    public String getCurrent_amount() {
        return current_amount;
    }

    public String getMotion_id() {
        return motion_id;
    }

    public String getPlacard_id() {
        return placard_id;
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

    public String getIsin_code() {
        return isin_code;
    }

    public String getResult_code() {
        return result_code;
    }

    public String getApprove_amount() {
        return approve_amount;
    }

    public String getStock_code() {
        return stock_code;
    }

    public String getBusiness_id() {
        return business_id;
    }
}
