package com.thinkive.android.trade_bz.a_out.bean;

import com.thinkive.android.trade_bz.a_stock.bean.BaseBean;
import com.thinkive.android.trade_bz.others.JsonKey;

/**
 * 风险测评结果
 * @author 王志鸿
 * @company Thinkive
 * @date 2015/8/10
 */
public class FundRiskTestResultBean extends BaseBean {

    @JsonKey("risk_name")
    private String risk_name = "";
    @JsonKey("risk_level")
    private String risk_level = "";
    @JsonKey("update_date")
    private String update_date = "";
    /**
     * 是否过期
     */
    @JsonKey("risk_flag")
    private String risk_flag = "";

    public String getRisk_name() {
        return risk_name;
    }

    public void setRisk_name(String risk_name) {
        this.risk_name = risk_name;
    }

    public String getRisk_level() {
        return risk_level;
    }

    public void setRisk_level(String risk_level) {
        this.risk_level = risk_level;
    }

    public String getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(String update_date) {
        this.update_date = update_date;
    }

    public String getRisk_flag() {
        return risk_flag;
    }

    public void setRisk_flag(String risk_flag) {
        this.risk_flag = risk_flag;
    }
}
