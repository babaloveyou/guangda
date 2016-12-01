package com.thinkive.android.trade_bz.a_stock.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.thinkive.android.trade_bz.others.JsonKey;

/**
 * 撤单列表 、今日委托
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/6/16
 */

public class RevocationBean extends BaseBean implements Parcelable {
    /**
     *  限价or市价
     */
    @JsonKey("entrust_limit")
    private String entrust_limit="";

    public String getEntrust_limit() {
        return entrust_limit;
    }

    public void setEntrust_limit(String entrust_limit) {
        this.entrust_limit = entrust_limit;
    }

    /**
     * 委托标志
     */
    @JsonKey("entrust_bs")
    private String entrust_bs="";
    /**
     *   业务名称
     */
    @JsonKey("entrust_name")
    private String entrust_name="";
    /**
     * 委托类别名称
     */
    @JsonKey("entrust_type")
    private String entrust_type="";
//    /**
//     *  委托状态
//     */
//    @JsonKey("entrust_bs")
// private String entrust_state="";
    /**
     *委托状态名称
     */
    @JsonKey("entrust_state_name")
    private String entrust_state_name="";
    /**
     *交易市场类别
     */
    @JsonKey("exchange_type")
    private String exchange_type="";
    /**
     *交易市场名称
     */
    @JsonKey("exchange_type_name")
    private String exchange_type_name="";
    /**
     * 证券账号
     */
    @JsonKey("stock_account")
    private String stock_account="";
    /**
     * 委托日期
     */
    @JsonKey("entrust_date")
    private String entrust_date="";
    /**
     *  委托时间
     */
    @JsonKey("entrust_time")
    private String entrust_time="";
    /**
     * 证券代码
     */
    @JsonKey("stock_code")
    private String stock_code="";
    /**
     *   证券名称
     */
    @JsonKey("stock_name")
    private String stock_name="";
    /**
     * 申请编号
     */
    @JsonKey("report_no")
    private String report_no="";
    /**
     *   成交编号
     */
    @JsonKey("entrust_no")
    private String entrust_no="";
    /**
     *  委托价格
     */
    @JsonKey("entrust_price")
    private String entrust_price="";
    /**
     * 委托数量
     */
    @JsonKey("entrust_amount")
    private String entrust_amount="";
    /**
     * 成交价格
     */
    @JsonKey("business_price")
    private String business_price="";
    /**
     * 成交数目
     */
    @JsonKey("business_amount")
    private String business_amount="";
    /**
     *  成交金额
     */
    @JsonKey("business_balance")
    private String business_balance="";
//    /**
//     * 撤单数目
//     */
//    @JsonKey("entrust_bs")
// private String cancel_amount="";
//    /**
//     * 撤单标志（0:可撤 1：不可撤）
//     */
//    @JsonKey("entrust_bs")
// private String cancel_flag="";

    public String getEntrust_state_name() {
        return entrust_state_name;
    }

    public void setEntrust_state_name(String entrust_state_name) {
        this.entrust_state_name = entrust_state_name;
    }




    public RevocationBean() {
    }

    public String getEntrust_bs() {
        return entrust_bs;
    }

    public void setEntrust_bs(String entrust_bs) {
        this.entrust_bs = entrust_bs;
    }

    public String getEntrust_name() {
        return entrust_name;
    }

    public void setEntrust_name(String entrust_name) {
        this.entrust_name = entrust_name;
    }

    public String getEntrust_type() {
        return entrust_type;
    }

    public void setEntrust_type(String entrust_type) {
        this.entrust_type = entrust_type;
    }

//    public String getEntrust_state() {
//        return entrust_state;
//    }
//
//    public void setEntrust_state(String entrust_state) {
//        this.entrust_state = entrust_state;
//    }


    public String getExchange_type() {
        return exchange_type;
    }

    public void setExchange_type(String exchange_type) {
        this.exchange_type = exchange_type;
    }

    public String getExchange_type_name() {
        return exchange_type_name;
    }

    public void setExchange_type_name(String exchange_type_name) {
        this.exchange_type_name = exchange_type_name;
    }

    public String getStock_account() {
        return stock_account;
    }

    public void setStock_account(String stock_account) {
        this.stock_account = stock_account;
    }

    public String getEntrust_date() {
        return entrust_date;
    }

    public void setEntrust_date(String entrust_date) {
        this.entrust_date = entrust_date;
    }

    public String getEntrust_time() {
        return entrust_time;
    }

    public void setEntrust_time(String entrust_time) {
        this.entrust_time = entrust_time;
    }

    public String getStock_code() {
        return stock_code;
    }

    public void setStock_code(String stock_code) {
        this.stock_code = stock_code;
    }

    public String getStock_name() {
        return stock_name;
    }

    public void setStock_name(String stock_name) {
        this.stock_name = stock_name;
    }

    public String getReport_no() {
        return report_no;
    }

    public void setReport_no(String report_no) {
        this.report_no = report_no;
    }

    public String getEntrust_no() {
        return entrust_no;
    }

    public void setEntrust_no(String entrust_no) {
        this.entrust_no = entrust_no;
    }

    public String getEntrust_price() {
        return entrust_price;
    }

    public void setEntrust_price(String entrust_price) {
        this.entrust_price = entrust_price;
    }

    public String getEntrust_amount() {
        return entrust_amount;
    }

    public void setEntrust_amount(String entrust_amount) {
        this.entrust_amount = entrust_amount;
    }

    public String getBusiness_price() {
        return business_price;
    }

    public void setBusiness_price(String business_price) {
        this.business_price = business_price;
    }

    public String getBusiness_amount() {
        return business_amount;
    }

    public void setBusiness_amount(String business_amount) {
        this.business_amount = business_amount;
    }

    public String getBusiness_balance() {
        return business_balance;
    }

    public void setBusiness_balance(String business_balance) {
        this.business_balance = business_balance;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.entrust_limit);
        dest.writeString(this.entrust_bs);
        dest.writeString(this.entrust_name);
        dest.writeString(this.entrust_type);
        dest.writeString(this.entrust_state_name);
        dest.writeString(this.exchange_type);
        dest.writeString(this.exchange_type_name);
        dest.writeString(this.stock_account);
        dest.writeString(this.entrust_date);
        dest.writeString(this.entrust_time);
        dest.writeString(this.stock_code);
        dest.writeString(this.stock_name);
        dest.writeString(this.report_no);
        dest.writeString(this.entrust_no);
        dest.writeString(this.entrust_price);
        dest.writeString(this.entrust_amount);
        dest.writeString(this.business_price);
        dest.writeString(this.business_amount);
        dest.writeString(this.business_balance);
        dest.writeString(this.entrust_state_name);
    }

    protected RevocationBean(Parcel in) {
        this.entrust_limit = in.readString();
        this.entrust_bs = in.readString();
        this.entrust_name = in.readString();
        this.entrust_type = in.readString();
        this.entrust_state_name = in.readString();
        this.exchange_type = in.readString();
        this.exchange_type_name = in.readString();
        this.stock_account = in.readString();
        this.entrust_date = in.readString();
        this.entrust_time = in.readString();
        this.stock_code = in.readString();
        this.stock_name = in.readString();
        this.report_no = in.readString();
        this.entrust_no = in.readString();
        this.entrust_price = in.readString();
        this.entrust_amount = in.readString();
        this.business_price = in.readString();
        this.business_amount = in.readString();
        this.business_balance = in.readString();
        this.entrust_state_name = in.readString();
    }

    public static final Creator<RevocationBean> CREATOR = new Creator<RevocationBean>() {
        @Override
        public RevocationBean createFromParcel(Parcel source) {
            return new RevocationBean(source);
        }

        @Override
        public RevocationBean[] newArray(int size) {
            return new RevocationBean[size];
        }
    };
}
