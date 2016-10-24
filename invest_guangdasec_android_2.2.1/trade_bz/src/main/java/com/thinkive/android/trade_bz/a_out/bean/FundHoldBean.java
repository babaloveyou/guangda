package com.thinkive.android.trade_bz.a_out.bean;

import android.os.Parcel;

import com.thinkive.android.trade_bz.a_stock.bean.BaseBean;
import com.thinkive.android.trade_bz.others.JsonKey;

/**
 *  场外基金持仓（ 302012）
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/8/6
 */

public class FundHoldBean extends BaseBean {
    /**
     * 基金公司代码
     */
    @JsonKey("fund_company")
    private String fund_company="";
    /**
     * 基金公司名称
     */
    @JsonKey("company_name")
    private String company_name="";
    /**
     * 基金代码
     */
    @JsonKey("fund_code")
    private String fund_code="";
    /**
     *   基金名称
     */
    @JsonKey("fund_name")
    private String fund_name="";
    /**
     *日基金单位净值
     */
    @JsonKey("nav")
    private String nav="";
    /**
     * 可用份额
     */
    @JsonKey("enable_shares")
    private String enable_shares="";
    /**
     * 最新市值
     */
    @JsonKey("market_value")
    private String market_value="";
    /**
     *买入成本
     */
    @JsonKey("cost_price")
    private String cost_price="";
    /**
     * 盈亏金额
     */
    @JsonKey("income_balance")
    private String income_balance="";
    /**
     * 资金账号
     */
    @JsonKey("fund_account")
    private String fund_account="";
    /**
     *当前数量
     */
    @JsonKey("current_amount")
    private String current_amount="";
    /**
     * 基金当日可赎回数量
     */
    @JsonKey("enable_redeem_share")
    private String enable_redeem_share="";
    /**
     * 分红标志
     */
    @JsonKey("dividendmethod")
    private String dividendmethod="";
    /**
     *分红标志名称
     */
    @JsonKey("dividendmethod_name")
    private String dividendmethod_name="";
    /**
     *前后收费类型
     */
    @JsonKey("charge_type")
    private String charge_type = "";
    /**
     *基金状态
     */
    @JsonKey("fund_status")
    private String fund_status = "";
    /**
     *基金状态名称
     */
    @JsonKey("fund_status_name")
    private String fund_status_name = "";
    /**
     * 上限
     */
    @JsonKey("enable_amount")
    private String enable_amount = "";


    public FundHoldBean() {
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

    public String getNav() {
        return nav;
    }

    public void setNav(String nav) {
        this.nav = nav;
    }

    public String getEnable_shares() {
        return enable_shares;
    }

    public void setEnable_shares(String enable_shares) {
        this.enable_shares = enable_shares;
    }

    public String getMarket_value() {
        return market_value;
    }

    public void setMarket_value(String market_value) {
        this.market_value = market_value;
    }

    public String getCost_price() {
        return cost_price;
    }

    public void setCost_price(String cost_price) {
        this.cost_price = cost_price;
    }

    public String getIncome_balance() {
        return income_balance;
    }

    public void setIncome_balance(String income_balance) {
        this.income_balance = income_balance;
    }

    public String getFund_account() {
        return fund_account;
    }

    public void setFund_account(String fund_account) {
        this.fund_account = fund_account;
    }

    public String getCurrent_amount() {
        return current_amount;
    }

    public void setCurrent_amount(String current_amount) {
        this.current_amount = current_amount;
    }

    public String getEnable_redeem_share() {
        return enable_redeem_share;
    }

    public void setEnable_redeem_share(String enable_redeem_share) {
        this.enable_redeem_share = enable_redeem_share;
    }

    public String getDividendmethod() {
        return dividendmethod;
    }

    public void setDividendmethod(String dividendmethod) {
        this.dividendmethod = dividendmethod;
    }

    public String getDividendmethod_name() {
        return dividendmethod_name;
    }

    public void setDividendmethod_name(String dividendmethod_name) {
        this.dividendmethod_name = dividendmethod_name;
    }
    public String getCharge_type() {
        return charge_type;
    }

    public void setCharge_type(String charge_type) {
        this.charge_type = charge_type;
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

    public String getEnable_amount() {
        return enable_amount;
    }

    public void setEnable_amount(String enable_amount) {
        this.enable_amount = enable_amount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.fund_company);
        dest.writeString(this.company_name);
        dest.writeString(this.fund_code);
        dest.writeString(this.fund_name);
        dest.writeString(this.nav);
        dest.writeString(this.enable_shares);
        dest.writeString(this.market_value);
        dest.writeString(this.cost_price);
        dest.writeString(this.income_balance);
        dest.writeString(this.fund_account);
        dest.writeString(this.current_amount);
        dest.writeString(this.enable_redeem_share);
        dest.writeString(this.dividendmethod);
        dest.writeString(this.dividendmethod_name);
        dest.writeString(this.charge_type);
        dest.writeString(this.fund_status);
        dest.writeString(this.fund_status_name);
        dest.writeString(this.enable_amount);
    }

    protected FundHoldBean(Parcel in) {
        this.fund_company = in.readString();
        this.company_name = in.readString();
        this.fund_code = in.readString();
        this.fund_name = in.readString();
        this.nav = in.readString();
        this.enable_shares = in.readString();
        this.market_value = in.readString();
        this.cost_price = in.readString();
        this.income_balance = in.readString();
        this.fund_account = in.readString();
        this.current_amount = in.readString();
        this.enable_redeem_share = in.readString();
        this.dividendmethod = in.readString();
        this.dividendmethod_name = in.readString();
        this.charge_type = in.readString();
        this.fund_status = in.readString();
        this.fund_status_name = in.readString();
        this.enable_amount = in.readString();
    }

    public static final Creator<FundHoldBean> CREATOR = new Creator<FundHoldBean>() {
        @Override
        public FundHoldBean createFromParcel(Parcel source) {
            return new FundHoldBean(source);
        }

        @Override
        public FundHoldBean[] newArray(int size) {
            return new FundHoldBean[size];
        }
    };
}
