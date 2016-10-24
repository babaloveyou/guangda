package com.thinkive.android.trade_bz.a_hk.bean;

import com.thinkive.android.trade_bz.a_stock.bean.BaseBean;
import com.thinkive.android.trade_bz.others.JsonKey;


/**
 * 创建者     舒旺
 * 创建时间   2016/6/3 15:44
 * 描述	      组合费查询
 * 更新者     $Author$
 * 更新时间   $Date$
 */
public class HKCapitalLiabiitiesBean extends BaseBean {
    @JsonKey("jsdate")
    private String jsdate = "";
    @JsonKey("settrate")
    private String settrate = "";
    @JsonKey("qsdate")
    private String qsdate = "";
    @JsonKey("sno")
    private String sno = "";
    @JsonKey("combinfee_hk")
    private String combinfee_hk = "";
    @JsonKey("money_type_name")
    private String money_type_name = "";
    @JsonKey("lastmktvalue")
    private String lastmktvalue = "";
    @JsonKey("combinfee_rmb")
    private String combinfee_rmb = "";
    @JsonKey("debtsid")
    private String debtsid = "";

    public void setJsdate(String jsdate) {
        this.jsdate = jsdate;
    }

    public void setSettrate(String settrate) {
        this.settrate = settrate;
    }

    public void setQsdate(String qsdate) {
        this.qsdate = qsdate;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public void setCombinfee_hk(String combinfee_hk) {
        this.combinfee_hk = combinfee_hk;
    }

    public void setMoney_type_name(String money_type_name) {
        this.money_type_name = money_type_name;
    }

    public void setLastmktvalue(String lastmktvalue) {
        this.lastmktvalue = lastmktvalue;
    }

    public void setCombinfee_rmb(String combinfee_rmb) {
        this.combinfee_rmb = combinfee_rmb;
    }

    public void setDebtsid(String debtsid) {
        this.debtsid = debtsid;
    }

    public String getJsdate() {
        return jsdate;
    }

    public String getSettrate() {
        return settrate;
    }

    public String getQsdate() {
        return qsdate;
    }

    public String getSno() {
        return sno;
    }

    public String getCombinfee_hk() {
        return combinfee_hk;
    }

    public String getMoney_type_name() {
        return money_type_name;
    }

    public String getLastmktvalue() {
        return lastmktvalue;
    }

    public String getCombinfee_rmb() {
        return combinfee_rmb;
    }

    public String getDebtsid() {
        return debtsid;
    }
}
