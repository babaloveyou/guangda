package com.thinkive.android.trade_bz.a_option.bean;


import com.thinkive.android.trade_bz.a_stock.bean.BaseBean;
import com.thinkive.android.trade_bz.others.JsonKey;
/**
 * 个股期权资产查询(305004)
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/7/15
 */
public class OptionMoneyBean extends BaseBean {
    @JsonKey("total_asset")
    private String total_asset = ""; //总资产
    @JsonKey("fund_asset")
    private String fund_asset = ""; //现金资产
    @JsonKey("current_balance")
    private String current_balance = ""; //当前余额
    @JsonKey("enable_balance")
    private String enable_balance = ""; //可用资金
    @JsonKey("enable_bail_balance")
    private String enable_bail_balance = ""; //可用保证金
    @JsonKey("used_bail_balance")
    private String used_bail_balance = ""; //已用保证金
    @JsonKey("used_pur_balance")
    private String used_pur_balance = ""; //已用买入额度
    @JsonKey("enable_pur_balance")
    private String enable_pur_balance = ""; //可用买入额度
    @JsonKey("pur_quota")
    private String pur_quota = ""; //买入额度
    @JsonKey("income_balance")
    private String income_balance = ""; //总盈亏金额
    @JsonKey("market_value")
    private String market_value = ""; //期权持仓动态市值
    @JsonKey("real_used_bail")
    private String real_used_bail = ""; //实时已用保证金
    @JsonKey("risk_degree")
    private String risk_degree = ""; //风险度
    @JsonKey("real_risk_degree")
    private String real_risk_degree = ""; //实时风险度
    @JsonKey("optrisk_type")
    private String optrisk_type = ""; //风险监控类别（'0'-正常 '1'-关注 '2'-警告 '3'-强平）

    public OptionMoneyBean() {

    }

    public String getTotal_asset() {
        return total_asset;
    }

    public void setTotal_asset(String total_asset) {
        this.total_asset = total_asset;
    }

    public String getFund_asset() {
        return fund_asset;
    }

    public void setFund_asset(String fund_asset) {
        this.fund_asset = fund_asset;
    }

    public String getCurrent_balance() {
        return current_balance;
    }

    public void setCurrent_balance(String current_balance) {
        this.current_balance = current_balance;
    }

    public String getEnable_balance() {
        return enable_balance;
    }

    public void setEnable_balance(String enable_balance) {
        this.enable_balance = enable_balance;
    }

    public String getEnable_bail_balance() {
        return enable_bail_balance;
    }

    public void setEnable_bail_balance(String enable_bail_balance) {
        this.enable_bail_balance = enable_bail_balance;
    }

    public String getUsed_bail_balance() {
        return used_bail_balance;
    }

    public void setUsed_bail_balance(String used_bail_balance) {
        this.used_bail_balance = used_bail_balance;
    }

    public String getUsed_pur_balance() {
        return used_pur_balance;
    }

    public void setUsed_pur_balance(String used_pur_balance) {
        this.used_pur_balance = used_pur_balance;
    }

    public String getEnable_pur_balance() {
        return enable_pur_balance;
    }

    public void setEnable_pur_balance(String enable_pur_balance) {
        this.enable_pur_balance = enable_pur_balance;
    }

    public String getPur_quota() {
        return pur_quota;
    }

    public void setPur_quota(String pur_quota) {
        this.pur_quota = pur_quota;
    }

    public String getIncome_balance() {
        return income_balance;
    }

    public void setIncome_balance(String income_balance) {
        this.income_balance = income_balance;
    }

    public String getMarket_value() {
        return market_value;
    }

    public void setMarket_value(String market_value) {
        this.market_value = market_value;
    }

    public String getReal_used_bail() {
        return real_used_bail;
    }

    public void setReal_used_bail(String real_used_bail) {
        this.real_used_bail = real_used_bail;
    }

    public String getRisk_degree() {
        return risk_degree;
    }

    public void setRisk_degree(String risk_degree) {
        this.risk_degree = risk_degree;
    }

    public String getReal_risk_degree() {
        return real_risk_degree;
    }

    public void setReal_risk_degree(String real_risk_degree) {
        this.real_risk_degree = real_risk_degree;
    }

    public String getOptrisk_type() {
        return optrisk_type;
    }

    public void setOptrisk_type(String optrisk_type) {
        this.optrisk_type = optrisk_type;
    }
}
