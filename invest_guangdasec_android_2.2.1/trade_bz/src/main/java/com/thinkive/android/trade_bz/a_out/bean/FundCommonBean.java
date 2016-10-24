package com.thinkive.android.trade_bz.a_out.bean;

import com.thinkive.android.trade_bz.a_stock.bean.BaseBean;
import com.thinkive.android.trade_bz.others.JsonKey;

/**
 *   开放式基金查询 （根据不同状态或类型进行筛选） 302002
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/7/31
 */

public class FundCommonBean extends BaseBean {
    /**
     * 基金公司
     */
    @JsonKey("fund_company")
    private String fund_company = "";
    /**
     * 基金公司名称
     */
    @JsonKey("company_name")
    private String company_name="";
    /**
     * 基金代码
     */
    @JsonKey("fund_code")
    private String fund_code = "";
    /**
     * 基金名称
     */
    @JsonKey("fund_name")
    private String fund_name = "";
    /**
     *基金类型
     */
    @JsonKey("fund_type")
    private String fund_type="";
    /**
     * 基金状态
     */
    @JsonKey("fund_status")
    private String fund_status = "";
    /**
     * 基金风险等级
     */
    @JsonKey("fund_risklevel")
    private String fund_risklevel="";
    /**
     * 基金风险等级名称
     */
    @JsonKey("fund_risklevel_name")
    private String fund_risklevel_name="";
    /**
     *日基金单位净值
     */
    @JsonKey("nav")
    private String nav="";
    /**
     *认购/申购的单位
     */
    @JsonKey("sub_unit")
    private String sub_unit="";
    /**
     * 赎回最低份额
     */
    @JsonKey("redeem_limitshare")
    private String redeem_limitshare="";
    /**
     *  可分红的基数份额
     */
    @JsonKey("total_share")
    private String total_share="";
    /**
     * 基金类型名称
     */
    @JsonKey("fund_type_name")
    private String fund_type_name="";
    /**
     *基金状态名称
     */
    @JsonKey("fund_status_name")
    private String fund_status_name="";
    /**
     * 个人最少投资
     */
    @JsonKey("person_invest")
    private String person_invest="";
    /**
     * 机构最少投资
     */
    @JsonKey("mach_invest")
    private String mach_invest="";


    // 以下新增
    private String min_share2="";
    private String minsize="";
    private String charge_type="";
    private String open_share="";
    private String hold_minshare="";

    public FundCommonBean() {
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

    public String getHold_minshare() {
        return hold_minshare;
    }

    public void setHold_minshare(String hold_minshare) {
        this.hold_minshare = hold_minshare;
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

    public String getFund_type() {
        return fund_type;
    }

    public void setFund_type(String fund_type) {
        this.fund_type = fund_type;
    }

    public String getFund_status() {
        return fund_status;
    }

    public void setFund_status(String fund_status) {
        this.fund_status = fund_status;
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

    public String getNav() {
        return nav;
    }

    public void setNav(String nav) {
        this.nav = nav;
    }

    public String getSub_unit() {
        return sub_unit;
    }

    public void setSub_unit(String sub_unit) {
        this.sub_unit = sub_unit;
    }

    public String getRedeem_limitshare() {
        return redeem_limitshare;
    }

    public void setRedeem_limitshare(String redeem_limitshare) {
        this.redeem_limitshare = redeem_limitshare;
    }

    public String getTotal_share() {
        return total_share;
    }

    public void setTotal_share(String total_share) {
        this.total_share = total_share;
    }

    public String getMin_share2() {
        return min_share2;
    }

    public void setMin_share2(String min_share2) {
        this.min_share2 = min_share2;
    }

    public String getMinsize() {
        return minsize;
    }

    public void setMinsize(String minsize) {
        this.minsize = minsize;
    }

    public String getCharge_type() {
        return charge_type;
    }

    public void setCharge_type(String charge_type) {
        this.charge_type = charge_type;
    }

    public String getOpen_share() {
        return open_share;
    }

    public void setOpen_share(String open_share) {
        this.open_share = open_share;
    }

    public String getFund_type_name() {
        return fund_type_name;
    }

    public void setFund_type_name(String fund_type_name) {
        this.fund_type_name = fund_type_name;
    }

    public String getFund_status_name() {
        return fund_status_name;
    }

    public void setFund_status_name(String fund_status_name) {
        this.fund_status_name = fund_status_name;
    }

    public String getPerson_invest() {
        return person_invest;
    }

    public void setPerson_invest(String person_invest) {
        this.person_invest = person_invest;
    }

    public String getMach_invest() {
        return mach_invest;
    }

    public void setMach_invest(String mach_invest) {
        this.mach_invest = mach_invest;
    }
}
