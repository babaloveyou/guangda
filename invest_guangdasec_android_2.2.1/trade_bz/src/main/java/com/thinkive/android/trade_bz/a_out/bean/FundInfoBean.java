package com.thinkive.android.trade_bz.a_out.bean;

import com.thinkive.android.trade_bz.a_stock.bean.BaseBean;
import com.thinkive.android.trade_bz.others.JsonKey;

/**
 *   基金交易详情查询 (302005)
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/7/29
 */

public class FundInfoBean extends BaseBean {
    /**
     * "position_str":"",
     */
    @JsonKey("fund_company")
    private String fund_company = "";//基金公司
    @JsonKey("company_name")
    private String company_name="";//基金公司名称
    @JsonKey("fund_code")
    private String fund_code = "";//基金代码
    @JsonKey("fund_name")
    private String fund_name = "";//  基金名称
    @JsonKey("fund_status")
    private String fund_status = "";// 基金状态
    @JsonKey("fund_status_name")
    private String fund_status_name="";//   基金状态名称
    @JsonKey("nav")
    private String nav = "";//日基金单位净值
    @JsonKey("redeem_limitshare")
    private String redeem_limitshare="";// 赎回最低份额
    @JsonKey("sub_unit")
    private String sub_unit="";// 认购/申购的单位
    @JsonKey("total_share")
    private String total_share="";//可分红的基数份额
    @JsonKey("fund_type")
    private String fund_type="";// 基金类型
    @JsonKey("fund_risklevel")
    private String fund_risklevel="";// 基金风险等级
    @JsonKey("fund_risklevel_name")
    private String fund_risklevel_name="";//基金风险等级中文含义
    @JsonKey("open_share")
    private String open_share="";//  个人首次购入下限
    @JsonKey("minsize")
    private String minsize="";//机构首次购入最低金额
    @JsonKey("hold_minshare")
    private String hold_minshare="";// 最低持有份额
    @JsonKey("enable_redeem_share")
    private String enable_redeem_share="";// 基金当日可赎回数量
    @JsonKey("charge_type")
    private String charge_type="";//前后收费类型
    @JsonKey("min_share2")
    private String min_share2="";// 每笔最低申购起点
    @JsonKey("min_timer_balance")
    private String min_timer_balance="";// 最低定投起点
    @JsonKey("trans_limitshare")
    private String trans_limitshare="";//  最少转换份额
    @JsonKey("income_unit")
    private String income_unit="";// 成份基金单位收益
    @JsonKey("income_ratio")
    private String income_ratio="";//  收益率
    @JsonKey("redemption_unit")
    private String redemption_unit="";//  赎回最小单位

    public FundInfoBean() {

    }
    public String getFund_company() {
        return fund_company;
    }

    public void setFund_company(String fund_company) {
        this.fund_company = fund_company;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getFund_code() {
        return fund_code;
    }

    public void setFund_code(String fund_code) {
        this.fund_code = fund_code;
    }

    public String getFund_name() {
        return fund_name;
    }

    public void setFund_name(String fund_name) {
        this.fund_name = fund_name;
    }

    public String getFund_status() {
        return fund_status;
    }

    public void setFund_status(String fund_status) {
        this.fund_status = fund_status;
    }
    public String getFund_status_name() {
        return fund_status_name;
    }

    public void setFund_status_name(String fund_status_name) {
        this.fund_status_name = fund_status_name;
    }

    public String getNav() {
        return nav;
    }

    public void setNav(String nav) {
        this.nav = nav;
    }

    public String getRedeem_limitshare() {
        return redeem_limitshare;
    }

    public void setRedeem_limitshare(String redeem_limitshare) {
        this.redeem_limitshare = redeem_limitshare;
    }

    public String getSub_unit() {
        return sub_unit;
    }

    public void setSub_unit(String sub_unit) {
        this.sub_unit = sub_unit;
    }

    public String getTotal_share() {
        return total_share;
    }

    public void setTotal_share(String total_share) {
        this.total_share = total_share;
    }

    public String getFund_type() {
        return fund_type;
    }

    public void setFund_type(String fund_type) {
        this.fund_type = fund_type;
    }

    public String getFund_risklevel() {
        return fund_risklevel;
    }

    public void setFund_risklevel(String fund_risklevel) {
        this.fund_risklevel = fund_risklevel;
    }

    public String getFund_risklevel_name() {
        return fund_risklevel_name;
    }

    public void setFund_risklevel_name(String fund_risklevel_name) {
        this.fund_risklevel_name = fund_risklevel_name;
    }

    public String getOpen_share() {
        return open_share;
    }

    public void setOpen_share(String open_share) {
        this.open_share = open_share;
    }

    public String getMinsize() {
        return minsize;
    }

    public void setMinsize(String minsize) {
        this.minsize = minsize;
    }

    public String getHold_minshare() {
        return hold_minshare;
    }

    public void setHold_minshare(String hold_minshare) {
        this.hold_minshare = hold_minshare;
    }

    public String getEnable_redeem_share() {
        return enable_redeem_share;
    }

    public void setEnable_redeem_share(String enable_redeem_share) {
        this.enable_redeem_share = enable_redeem_share;
    }

    public String getCharge_type() {
        return charge_type;
    }

    public void setCharge_type(String charge_type) {
        this.charge_type = charge_type;
    }

    public String getMin_share2() {
        return min_share2;
    }

    public void setMin_share2(String min_share2) {
        this.min_share2 = min_share2;
    }

    public String getRedemption_unit() {
        return redemption_unit;
    }

    public void setRedemption_unit(String redemption_unit) {
        this.redemption_unit = redemption_unit;
    }

    public String getMin_timer_balance() {
        return min_timer_balance;
    }

    public void setMin_timer_balance(String min_timer_balance) {
        this.min_timer_balance = min_timer_balance;
    }

    public String getTrans_limitshare() {
        return trans_limitshare;
    }

    public void setTrans_limitshare(String trans_limitshare) {
        this.trans_limitshare = trans_limitshare;
    }

    public String getIncome_unit() {
        return income_unit;
    }

    public void setIncome_unit(String income_unit) {
        this.income_unit = income_unit;
    }

    public String getIncome_ratio() {
        return income_ratio;
    }

    public void setIncome_ratio(String income_ratio) {
        this.income_ratio = income_ratio;
    }
}
