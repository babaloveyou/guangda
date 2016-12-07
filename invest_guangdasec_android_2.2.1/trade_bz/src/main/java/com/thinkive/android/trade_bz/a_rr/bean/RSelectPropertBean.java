package com.thinkive.android.trade_bz.a_rr.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.thinkive.android.trade_bz.a_stock.bean.BaseBean;
import com.thinkive.android.trade_bz.others.JsonKey;

/**
 * 融资融券--查询--资产负债(融资融券--资产)
 * Announcements：
 *  303026 融资融券资产负债综合查询
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/8/17
 */

public class RSelectPropertBean extends BaseBean implements Parcelable {
    public String getSlo_enable_quota() {
        return slo_enable_quota;
    }

    public void setSlo_enable_quota(String slo_enable_quota) {
        this.slo_enable_quota = slo_enable_quota;
    }

    /**
     * 融券授信额度
     */
    @JsonKey("slo_enable_quota")
    private String slo_enable_quota="";

    /**
     * 币种类别
     */
    @JsonKey("money_type")
    private String money_type="";



    /**
     * 额度

     */
    @JsonKey("credit_up")
    private String credit_up="";
    /**
     *币种类别名称
     */
    @JsonKey("money_type_name")
    private String money_type_name="";
    /**
     *当前余额
     */
    @JsonKey("current_balance")
    private String current_balance="";
    /**
     * 可用资金
     */
    @JsonKey("enable_balance")
    private String enable_balance="";
    /**
     *可取金额
     */
    @JsonKey("fetch_balance")
    private String fetch_balance="";
    /**
     *总资产
     */
    @JsonKey("assert_val")
    private String assert_val="";
    /**
     * 现金资产
     */
    @JsonKey("fund_asset")
    private String fund_asset="";
    /**
     *证券市值
     */
    @JsonKey("market_value")
    private String market_value="";
    /**
     * 总负债
     */
    @JsonKey("total_debit")
    private String total_debit="";
    /**
     * 净资产
     */
    @JsonKey("net_asset")
    private String net_asset="";
    /**
     *融资负债
     */
    @JsonKey("fin_debit")
    private String fin_debit="";
    /**
     * 融券负债
     */
    @JsonKey("slo_debit")
    private String slo_debit="";
    /**
     * 个人维持担保比例
     */
    @JsonKey("per_assurescale_value")
    private String per_assurescale_value="";
    /**
     * 可用保证金
     */
    @JsonKey("enable_bail_balance")
    private String enable_bail_balance="";
    /**
     *现金还款可用资金
     */
    @JsonKey("fin_enrepaid_balance")
    private String fin_enrepaid_balance="";
    /**
     * 融资已用额度
     */
    @JsonKey("fin_used_quota")
    private String fin_used_quota="";
    /**
     * 融资合约费用
     */
    @JsonKey("fin_compact_fare")
    private String fin_compact_fare="";
    /**
     * 融券可用额度
     */
    @JsonKey("slo_used_quota")
    private String slo_used_quota="";
    /**
     *标的证券市值
     */
    @JsonKey("underly_market_value")
    private String underly_market_value="";
    /**
     *融资未成交金额
     */
    @JsonKey("fin_unbusi_balance")
    private String fin_unbusi_balance="";
    /**
     *融券未成交金额
     */
    @JsonKey("slo_unbusi_balance")
    private String slo_unbusi_balance="";
    /**
     * 融券卖出所得资金
     */
    @JsonKey("slo_sell_balance")
    private String slo_sell_balance="";
    /**
     *可融资金额(可用保证金/融券保证金比例)
     */
    @JsonKey("fin_enable_balance")
    private String fin_enable_balance="";
    /**
     * 融资费息
     */
    @JsonKey("ffee")
    private String ffee ="";
    /**
     * 融券费息
     */
    @JsonKey("dfee")
    private String dfee ="";
    /**
     * 总可用额度
     */
    @JsonKey("acreditavl")
    private String acreditavl ="";
    /**
     * 融资授信额度
     */
    @JsonKey("fcreditbal")
    private String fcreditbal ="";
    /**
     *融券授信额度
     */
    @JsonKey("dcreditbal")
    private String dcreditbal ="";
    /**
     * 融资可用额度
     */
    @JsonKey("fcreditavl")
    private String fcreditavl ="";
    /**
     *融券可用额度
     */
    @JsonKey("dcreditavl")
    private String dcreditavl ="";
    /**
     * 资金可用数
     */
    @JsonKey("fundavl")
    private String fundavl ="";
    /**
     *可转出担保资产
     */
    @JsonKey("guaranteeout")
    private String guaranteeout ="";
    /**
     *融资保证金比例
     */
    @JsonKey("fin_ratio")
    private String fin_ratio = "";
    /**
     *融券保证金比例
     */
    @JsonKey("slo_ratio")
    private String slo_ratio = "";

    public String getFin_ratio() {
        return fin_ratio;
    }

    public void setFin_ratio(String fin_ratio) {
        this.fin_ratio = fin_ratio;
    }

    public String getSlo_ratio() {
        return slo_ratio;
    }

    public void setSlo_ratio(String slo_ratio) {
        this.slo_ratio = slo_ratio;
    }

    /**
     * 利息/费用
     */
    @JsonKey("sum_compact_interest")
    private String sum_compact_interest = "";

    public RSelectPropertBean() {

    }

    public String getFundavl() {
        return fundavl;
    }

    public void setFundavl(String fundavl) {
        this.fundavl = fundavl;
    }

    public String getMoney_type() {
        return money_type;
    }

    public void setMoney_type(String money_type) {
        this.money_type = money_type;
    }

    public String getMoney_type_name() {
        return money_type_name;
    }

    public void setMoney_type_name(String money_type_name) {
        this.money_type_name = money_type_name;
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

    public String getFetch_balance() {
        return fetch_balance;
    }

    public void setFetch_balance(String fetch_balance) {
        this.fetch_balance = fetch_balance;
    }

    public String getAssert_val() {
        return assert_val;
    }

    public void setAssert_val(String assert_val) {
        this.assert_val = assert_val;
    }

    public String getFund_asset() {
        return fund_asset;
    }

    public void setFund_asset(String fund_asset) {
        this.fund_asset = fund_asset;
    }

    public String getMarket_value() {
        return market_value;
    }

    public void setMarket_value(String market_value) {
        this.market_value = market_value;
    }

    public String getTotal_debit() {
        return total_debit;
    }

    public void setTotal_debit(String total_debit) {
        this.total_debit = total_debit;
    }

    public String getNet_asset() {
        return net_asset;
    }

    public void setNet_asset(String net_asset) {
        this.net_asset = net_asset;
    }

    public String getFin_debit() {
        return fin_debit;
    }

    public void setFin_debit(String fin_debit) {
        this.fin_debit = fin_debit;
    }

    public String getSlo_debit() {
        return slo_debit;
    }

    public void setSlo_debit(String slo_debit) {
        this.slo_debit = slo_debit;
    }

    public String getPer_assurescale_value() {
        return per_assurescale_value;
    }

    public void setPer_assurescale_value(String per_assurescale_value) {
        this.per_assurescale_value = per_assurescale_value;
    }

    public String getEnable_bail_balance() {
        return enable_bail_balance;
    }

    public void setEnable_bail_balance(String enable_bail_balance) {
        this.enable_bail_balance = enable_bail_balance;
    }
    public String getFin_enrepaid_balance() {
        return fin_enrepaid_balance;
    }

    public void setFin_enrepaid_balance(String fin_enrepaid_balance) {
        this.fin_enrepaid_balance = fin_enrepaid_balance;
    }
    public String getFin_used_quota() {
        return fin_used_quota;
    }

    public void setFin_used_quota(String fin_used_quota) {
        this.fin_used_quota = fin_used_quota;
    }

    public String getFin_compact_fare() {
        return fin_compact_fare;
    }

    public void setFin_compact_fare(String fin_compact_fare) {
        this.fin_compact_fare = fin_compact_fare;
    }
    public String getSlo_used_quota() {
        return slo_used_quota;
    }

    public void setSlo_used_quota(String slo_used_quota) {
        this.slo_used_quota = slo_used_quota;
    }

    public String getUnderly_market_value() {
        return underly_market_value;
    }

    public void setUnderly_market_value(String underly_market_value) {
        this.underly_market_value = underly_market_value;
    }

    public String getFin_unbusi_balance() {
        return fin_unbusi_balance;
    }

    public void setFin_unbusi_balance(String fin_unbusi_balance) {
        this.fin_unbusi_balance = fin_unbusi_balance;
    }

    public String getSlo_unbusi_balance() {
        return slo_unbusi_balance;
    }

    public void setSlo_unbusi_balance(String slo_unbusi_balance) {
        this.slo_unbusi_balance = slo_unbusi_balance;
    }
    public String getSlo_sell_balance() {
        return slo_sell_balance;
    }

    public void setSlo_sell_balance(String slo_sell_balance) {
        this.slo_sell_balance = slo_sell_balance;
    }
    public String getFin_enable_balance() {
        return fin_enable_balance;
    }

    public void setFin_enable_balance(String fin_enable_balance) {
        this.fin_enable_balance = fin_enable_balance;
    }

    public String getFfee() {
        return ffee;
    }

    public void setFfee(String ffee) {
        this.ffee = ffee;
    }

    public String getDfee() {
        return dfee;
    }

    public void setDfee(String dfee) {
        this.dfee = dfee;
    }

    public String getAcreditavl() {
        return acreditavl;
    }

    public void setAcreditavl(String acreditavl) {
        this.acreditavl = acreditavl;
    }

    public String getFcreditbal() {
        return fcreditbal;
    }

    public void setFcreditbal(String fcreditbal) {
        this.fcreditbal = fcreditbal;
    }

    public String getDcreditbal() {
        return dcreditbal;
    }

    public void setDcreditbal(String dcreditbal) {
        this.dcreditbal = dcreditbal;
    }

    public String getFcreditavl() {
        return fcreditavl;
    }

    public void setFcreditavl(String fcreditavl) {
        this.fcreditavl = fcreditavl;
    }

    public String getDcreditavl() {
        return dcreditavl;
    }

    public void setDcreditavl(String dcreditavl) {
        this.dcreditavl = dcreditavl;
    }

    public String getGuaranteeout() {
        return guaranteeout;
    }

    public void setGuaranteeout(String guaranteeout) {
        this.guaranteeout = guaranteeout;
    }

    public String getSum_compact_interest() {
        return sum_compact_interest;
    }

    public void setSum_compact_interest(String sum_compact_interest) {
        this.sum_compact_interest = sum_compact_interest;
    }
    public String getCredit_up() {
        return credit_up;
    }

    public void setCredit_up(String credit_up) {
        this.credit_up = credit_up;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.slo_enable_quota);
        dest.writeString(this.money_type);
        dest.writeString(this.credit_up);
        dest.writeString(this.money_type_name);
        dest.writeString(this.current_balance);
        dest.writeString(this.enable_balance);
        dest.writeString(this.fetch_balance);
        dest.writeString(this.assert_val);
        dest.writeString(this.fund_asset);
        dest.writeString(this.market_value);
        dest.writeString(this.total_debit);
        dest.writeString(this.net_asset);
        dest.writeString(this.fin_debit);
        dest.writeString(this.slo_debit);
        dest.writeString(this.per_assurescale_value);
        dest.writeString(this.enable_bail_balance);
        dest.writeString(this.fin_enrepaid_balance);
        dest.writeString(this.fin_used_quota);
        dest.writeString(this.fin_compact_fare);
        dest.writeString(this.slo_used_quota);
        dest.writeString(this.underly_market_value);
        dest.writeString(this.fin_unbusi_balance);
        dest.writeString(this.slo_unbusi_balance);
        dest.writeString(this.slo_sell_balance);
        dest.writeString(this.fin_enable_balance);
        dest.writeString(this.ffee);
        dest.writeString(this.dfee);
        dest.writeString(this.acreditavl);
        dest.writeString(this.fcreditbal);
        dest.writeString(this.dcreditbal);
        dest.writeString(this.fcreditavl);
        dest.writeString(this.dcreditavl);
        dest.writeString(this.fundavl);
        dest.writeString(this.guaranteeout);
        dest.writeString(this.fin_ratio);
        dest.writeString(this.slo_ratio);
        dest.writeString(this.sum_compact_interest);
    }

    protected RSelectPropertBean(Parcel in) {
        this.slo_enable_quota = in.readString();
        this.money_type = in.readString();
        this.credit_up = in.readString();
        this.money_type_name = in.readString();
        this.current_balance = in.readString();
        this.enable_balance = in.readString();
        this.fetch_balance = in.readString();
        this.assert_val = in.readString();
        this.fund_asset = in.readString();
        this.market_value = in.readString();
        this.total_debit = in.readString();
        this.net_asset = in.readString();
        this.fin_debit = in.readString();
        this.slo_debit = in.readString();
        this.per_assurescale_value = in.readString();
        this.enable_bail_balance = in.readString();
        this.fin_enrepaid_balance = in.readString();
        this.fin_used_quota = in.readString();
        this.fin_compact_fare = in.readString();
        this.slo_used_quota = in.readString();
        this.underly_market_value = in.readString();
        this.fin_unbusi_balance = in.readString();
        this.slo_unbusi_balance = in.readString();
        this.slo_sell_balance = in.readString();
        this.fin_enable_balance = in.readString();
        this.ffee = in.readString();
        this.dfee = in.readString();
        this.acreditavl = in.readString();
        this.fcreditbal = in.readString();
        this.dcreditbal = in.readString();
        this.fcreditavl = in.readString();
        this.dcreditavl = in.readString();
        this.fundavl = in.readString();
        this.guaranteeout = in.readString();
        this.fin_ratio = in.readString();
        this.slo_ratio = in.readString();
        this.sum_compact_interest = in.readString();
    }

    public static final Creator<RSelectPropertBean> CREATOR = new Creator<RSelectPropertBean>() {
        @Override
        public RSelectPropertBean createFromParcel(Parcel source) {
            return new RSelectPropertBean(source);
        }

        @Override
        public RSelectPropertBean[] newArray(int size) {
            return new RSelectPropertBean[size];
        }
    };
}
