package com.thinkive.android.trade_bz.a_hk.bean;

import com.thinkive.android.trade_bz.a_stock.bean.BaseBean;
import com.thinkive.android.trade_bz.others.JsonKey;


/**
 * 创建者     舒旺
 * 创建时间   2016/6/6 15:54
 * 描述	       负债查询Bean
 * 更新者     $Author$
 * 更新时间   $Date$
 */
public class HKCapitalLiabilitiesBean extends BaseBean {
    @JsonKey("unpayamt")
    private String unpayamt = "";
    @JsonKey("sumdebtamt")
    private String sumdebtamt = "";
    @JsonKey("sumpaidamt")
    private String sumpaidamt = "";
    @JsonKey("money_type_name")
    private String money_type_name = "";
    @JsonKey("debtsid")
    private String debtsid = "";

    public void setUnpayamt(String unpayamt) {
        this.unpayamt = unpayamt;
    }

    public void setSumdebtamt(String sumdebtamt) {
        this.sumdebtamt = sumdebtamt;
    }

    public void setSumpaidamt(String sumpaidamt) {
        this.sumpaidamt = sumpaidamt;
    }

    public void setMoney_type_name(String money_type_name) {
        this.money_type_name = money_type_name;
    }

    public void setDebtsid(String debtsid) {
        this.debtsid = debtsid;
    }

    public String getUnpayamt() {
        return unpayamt;
    }

    public String getSumdebtamt() {
        return sumdebtamt;
    }

    public String getSumpaidamt() {
        return sumpaidamt;
    }

    public String getMoney_type_name() {
        return money_type_name;
    }

    public String getDebtsid() {
        return debtsid;
    }
}
