package com.thinkive.android.trade_bz.a_stock.bean;

import android.os.Parcel;

import com.thinkive.android.trade_bz.others.JsonKey;

/**
 *   一键归集
 *   300206(一键归集)
 *   300207（资金转账）
 *   300208（归集查询）
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/8/12
 */

public class OneKeyBean extends BaseBean {
    /**
     *归集金额
     */
    @JsonKey("fundeffec")
   private String fundeffec="";
    /**
     *返回笔数
     */
    @JsonKey("renum")
    private String renum="";

    //-------------------------------资金转账------------------------------
    /**
     *转出流水号
     */
    @JsonKey("outsno")
    private String outsno="";
    /**
     *转入流水号
     */
    @JsonKey("insno")
    private String insno="";

    //---------------------------账户转账查询-------------------------------
    /**
     *币种类别
     */
    @JsonKey("money_type")
    private String money_type="";
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
     *冻结资金
     */
    @JsonKey("frozen_balance")
    private String frozen_balance="";
    /**
     *总资金
     */
    @JsonKey("assert_val")
    private String assert_val="";
    /**
     * 持仓市值
     */
    @JsonKey("market_val")
    private String market_val="";
    /**
     * 主资金标志
     */
    @JsonKey("fundseq")
    private String fundseq="";
    /**
     * 银行名称
     */
    @JsonKey("bank_name")
     private String bank_name="";
    /**
     * 资金账户
     */
    @JsonKey("fund_account")
    private String fund_account="";


    public OneKeyBean() {
    }

    public String getFundeffec() {
        return fundeffec;
    }

    public void setFundeffec(String fundeffec) {
        this.fundeffec = fundeffec;
    }

    public String getRenum() {
        return renum;
    }

    public void setRenum(String renum) {
        this.renum = renum;
    }

    public String getOutsno() {
        return outsno;
    }

    public void setOutsno(String outsno) {
        this.outsno = outsno;
    }

    public String getInsno() {
        return insno;
    }

    public void setInsno(String insno) {
        this.insno = insno;
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

    public String getFrozen_balance() {
        return frozen_balance;
    }

    public void setFrozen_balance(String frozen_balance) {
        this.frozen_balance = frozen_balance;
    }

    public String getAssert_val() {
        return assert_val;
    }

    public void setAssert_val(String assert_val) {
        this.assert_val = assert_val;
    }

    public String getMarket_val() {
        return market_val;
    }

    public void setMarket_val(String market_val) {
        this.market_val = market_val;
    }

    public String getFundseq() {
        return fundseq;
    }

    public void setFundseq(String fundseq) {
        this.fundseq = fundseq;
    }
    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public String getFund_account() {
        return fund_account;
    }

    public void setFund_account(String fund_account) {
        this.fund_account = fund_account;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.fundeffec);
        dest.writeString(this.renum);
        dest.writeString(this.outsno);
        dest.writeString(this.insno);
        dest.writeString(this.money_type);
        dest.writeString(this.money_type_name);
        dest.writeString(this.current_balance);
        dest.writeString(this.enable_balance);
        dest.writeString(this.fetch_balance);
        dest.writeString(this.frozen_balance);
        dest.writeString(this.assert_val);
        dest.writeString(this.market_val);
        dest.writeString(this.fundseq);
        dest.writeString(this.bank_name);
        dest.writeString(this.fund_account);
    }

    protected OneKeyBean(Parcel in) {
        this.fundeffec = in.readString();
        this.renum = in.readString();
        this.outsno = in.readString();
        this.insno = in.readString();
        this.money_type = in.readString();
        this.money_type_name = in.readString();
        this.current_balance = in.readString();
        this.enable_balance = in.readString();
        this.fetch_balance = in.readString();
        this.frozen_balance = in.readString();
        this.assert_val = in.readString();
        this.market_val = in.readString();
        this.fundseq = in.readString();
        this.bank_name = in.readString();
        this.fund_account = in.readString();
    }

    public static final Creator<OneKeyBean> CREATOR = new Creator<OneKeyBean>() {
        @Override
        public OneKeyBean createFromParcel(Parcel source) {
            return new OneKeyBean(source);
        }

        @Override
        public OneKeyBean[] newArray(int size) {
            return new OneKeyBean[size];
        }
    };
}
