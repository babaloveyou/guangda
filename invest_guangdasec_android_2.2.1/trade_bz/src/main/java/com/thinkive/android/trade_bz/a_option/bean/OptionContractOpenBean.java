package com.thinkive.android.trade_bz.a_option.bean;

import android.os.Parcel;

import com.thinkive.android.trade_bz.a_stock.bean.BaseBean;
import com.thinkive.android.trade_bz.others.JsonKey;

/**
 *
 * Description：备兑解锁 合约查询结果，对应实体 305033 <br>
 * Author：晏政清 <br>
 * Corporation：深圳市思迪信息技术股份有限公司 <br>
 * Date：2016/7/28 <br>
 */
public class OptionContractOpenBean extends BaseBean{
    @JsonKey("cvd_stk_avl")
    private String cvd_stk_avl = "";//备兑股份可用
    @JsonKey("cvd_stk_bln")
    private String cvd_stk_bln = "";//备兑股份余额
    @JsonKey("cvd_stk_prebln")
    private String cvd_stk_prebln = "";//备兑股份昨日余额
    @JsonKey("option_type")
    private String option_type = "";//证券类别
    @JsonKey("stock_name")
    private String stock_name = "";//证券名称
    @JsonKey("stock_code")
    private String stock_code = "";//证券代码
    @JsonKey("opt_trdacct")
    private String opt_trdacct = "";//期权合约账户
    @JsonKey("stock_account")
    private String stock_account = "";//证券账户
    @JsonKey("trade_sector")
    private String trade_sector = "";//交易板块（见数据字典)
    @JsonKey("fund_account_opt")
    private String fund_account_opt ="";//衍生品资金账户
    @JsonKey("fund_account")
    private String fund_account = "";//资产账户
    @JsonKey("cust_code")
    private String cust_code = "";//客户代码

    public OptionContractOpenBean() {
    }

    public String getCvd_stk_avl() {
        return cvd_stk_avl;
    }

    public void setCvd_stk_avl(String cvd_stk_avl) {
        this.cvd_stk_avl = cvd_stk_avl;
    }

    public String getCvd_stk_bln() {
        return cvd_stk_bln;
    }

    public void setCvd_stk_bln(String cvd_stk_bln) {
        this.cvd_stk_bln = cvd_stk_bln;
    }

    public String getCvd_stk_prebln() {
        return cvd_stk_prebln;
    }

    public void setCvd_stk_prebln(String cvd_stk_prebln) {
        this.cvd_stk_prebln = cvd_stk_prebln;
    }

    public String getOption_type() {
        return option_type;
    }

    public void setOption_type(String option_type) {
        this.option_type = option_type;
    }

    public String getStock_name() {
        return stock_name;
    }

    public void setStock_name(String stock_name) {
        this.stock_name = stock_name;
    }

    public String getStock_code() {
        return stock_code;
    }

    public void setStock_code(String stock_code) {
        this.stock_code = stock_code;
    }

    public String getOpt_trdacct() {
        return opt_trdacct;
    }

    public void setOpt_trdacct(String opt_trdacct) {
        this.opt_trdacct = opt_trdacct;
    }

    public String getStock_account() {
        return stock_account;
    }

    public void setStock_account(String stock_account) {
        this.stock_account = stock_account;
    }

    public String getTrade_sector() {
        return trade_sector;
    }

    public void setTrade_sector(String trade_sector) {
        this.trade_sector = trade_sector;
    }

    public String getFund_account_opt() {
        return fund_account_opt;
    }

    public void setFund_account_opt(String fund_account_opt) {
        this.fund_account_opt = fund_account_opt;
    }

    public String getFund_account() {
        return fund_account;
    }

    public void setFund_account(String fund_account) {
        this.fund_account = fund_account;
    }

    public String getCust_code() {
        return cust_code;
    }

    public void setCust_code(String cust_code) {
        this.cust_code = cust_code;
    }

    public OptionContractOpenBean(Parcel in) {
        this.cvd_stk_avl = in.readString();
        this.cvd_stk_bln = in.readString();
        this.cvd_stk_prebln = in.readString();
        this.option_type = in.readString();
        this.stock_name = in.readString();
        this.stock_code = in.readString();
        this.opt_trdacct = in.readString();
        this.stock_account = in.readString();
        this.trade_sector = in.readString();
        this.fund_account_opt = in.readString();
        this.fund_account = in.readString();
        this.cust_code = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.cvd_stk_avl);
        dest.writeString(this.cvd_stk_bln);
        dest.writeString(this.cvd_stk_prebln);
        dest.writeString(this.option_type);
        dest.writeString(this.stock_name);
        dest.writeString(this.stock_code);
        dest.writeString(this.opt_trdacct);
        dest.writeString(this.stock_account);
        dest.writeString(this.trade_sector);
        dest.writeString(this.fund_account_opt);
        dest.writeString(this.fund_account);
        dest.writeString(this.cust_code);
    }

    public static final Creator<OptionContractOpenBean> CREATOR = new Creator<OptionContractOpenBean>() {
        @Override
        public OptionContractOpenBean createFromParcel(Parcel parcel) {
            return new OptionContractOpenBean(parcel);
        }

        @Override
        public OptionContractOpenBean[] newArray(int i) {
            return new OptionContractOpenBean[i];
        }
    };
}
