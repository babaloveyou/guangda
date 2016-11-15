package com.thinkive.android.trade_bz.a_stock.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.thinkive.android.trade_bz.others.JsonKey;

/**
 * 历史成交、今日成交
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/6/23
 */

public class HistoryTradeBean extends BaseBean implements Parcelable{
    /**
     * 委托标志
     */
    @JsonKey("entrust_bs")
    private String entrust_bs = "";
    /**
     * 业务名称
     */
    @JsonKey("entrust_name")
    private String entrust_name = "";
    /**
     * 委托类别
     */
    @JsonKey("entrust_type")
    private String entrust_type = "";
    /**
     * 委托类别名称
     */
    @JsonKey("entrust_type_name")
    private String entrust_type_name = "";
    /**
     *成交状态
     */
    private String real_status="";
    /**
     * 成交状态名称
     */
    private String real_status_name="";
    /**
     * 交易市场类别
     */
    @JsonKey("exchange_type")
    private String exchange_type = "";
    /**
     * 交易市场名称
     */
    @JsonKey("exchange_type_name")
    private String exchange_type_name = "";
    /**
     * 证券账号
     */
    @JsonKey("stock_account")
    private String stock_account = "";
    /**
     * 成交日期
     */
    @JsonKey("business_date")
    private String business_date = "";
    /**
     * 成交时间
     */
    @JsonKey("business_time")
    private String business_time = "";
    /**
     * 证券代码
     */
    @JsonKey("stock_code")
    private String stock_code = "";
    /**
     * 证券名称
     */
    @JsonKey("stock_name")
    private String stock_name = "";
    /**
     * 申请编号
     */
    @JsonKey("report_no")
    private String report_no = "";
    /**
     * 成交编号
     */
    @JsonKey("entrust_no")
    private String entrust_no = "";
    /**
     * 成交价格
     */
    @JsonKey("business_price")
    private String business_price = "";
    /**
     * 成交数目
     */
    @JsonKey("business_amount")
    private String business_amount = "";
    /**
     * 成交金额
     */
    @JsonKey("business_balance")
    private String business_balance = "";
    /**
     *佣金
     */
    private String fare0="";
    /**
     * 印花税
     */
    @JsonKey("fare1")
    private String fare1 = "";
    /**
     * 过户费
     */
    @JsonKey("fare2")
    private String fare2 = "";
    /**
     * 新增字段
     */
    @JsonKey("remark")
    private String remark = "";
    /**
     * 新增字段
     */
    @JsonKey("serial_no")
    private String serial_no = "";
    /**
     * 新增字段
     */
    @JsonKey("other_fare")
    private String other_fare = "";


    public HistoryTradeBean() {
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

    public String getEntrust_type_name() {
        return entrust_type_name;
    }

    public void setEntrust_type_name(String entrust_type_name) {
        this.entrust_type_name = entrust_type_name;
    }

    public String getReal_status_name() {
        return real_status_name;
    }

    public void setReal_status_name(String real_status_name) {
        this.real_status_name = real_status_name;
    }

    public String getReal_status() {
        return real_status;
    }

    public void setReal_status(String real_status) {
        this.real_status = real_status;
    }

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

    public String getBusiness_date() {
        return business_date;
    }

    public void setBusiness_date(String business_date) {
        this.business_date = business_date;
    }

    public String getBusiness_time() {
        return business_time;
    }

    public void setBusiness_time(String business_time) {
        this.business_time = business_time;
    }

    public String getStock_code() {
        return stock_code;
    }

    public void setStock_code(String stock_code) {
        this.stock_code = stock_code;
    }

    public String getReport_no() {
        return report_no;
    }

    public void setReport_no(String report_no) {
        this.report_no = report_no;
    }

    public String getStock_name() {
        return stock_name;
    }

    public void setStock_name(String stock_name) {
        this.stock_name = stock_name;
    }

    public String getEntrust_no() {
        return entrust_no;
    }

    public void setEntrust_no(String entrust_no) {
        this.entrust_no = entrust_no;
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

    public String getFare0() {
        return fare0;
    }

    public void setFare0(String fare0) {
        this.fare0 = fare0;
    }

    public String getFare1() {
        return fare1;
    }

    public void setFare1(String fare1) {
        this.fare1 = fare1;
    }

    public String getFare2() {
        return fare2;
    }

    public void setFare2(String fare2) {
        this.fare2 = fare2;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSerial_no() {
        return serial_no;
    }

    public void setSerial_no(String serial_no) {
        this.serial_no = serial_no;
    }

    public String getOther_fare() {
        return other_fare;
    }

    public void setOther_fare(String other_fare) {
        this.other_fare = other_fare;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.entrust_bs);
        dest.writeString(this.entrust_name);
        dest.writeString(this.entrust_type);
        dest.writeString(this.entrust_type_name);
        dest.writeString(this.real_status);
        dest.writeString(this.real_status_name);
        dest.writeString(this.exchange_type);
        dest.writeString(this.exchange_type_name);
        dest.writeString(this.stock_account);
        dest.writeString(this.business_date);
        dest.writeString(this.business_time);
        dest.writeString(this.stock_code);
        dest.writeString(this.stock_name);
        dest.writeString(this.report_no);
        dest.writeString(this.entrust_no);
        dest.writeString(this.business_price);
        dest.writeString(this.business_amount);
        dest.writeString(this.business_balance);
        dest.writeString(this.fare0);
        dest.writeString(this.fare1);
        dest.writeString(this.fare2);
        dest.writeString(this.remark);
        dest.writeString(this.serial_no);
        dest.writeString(this.other_fare);
    }

    protected HistoryTradeBean(Parcel in) {
        this.entrust_bs = in.readString();
        this.entrust_name = in.readString();
        this.entrust_type = in.readString();
        this.entrust_type_name = in.readString();
        this.real_status = in.readString();
        this.real_status_name = in.readString();
        this.exchange_type = in.readString();
        this.exchange_type_name = in.readString();
        this.stock_account = in.readString();
        this.business_date = in.readString();
        this.business_time = in.readString();
        this.stock_code = in.readString();
        this.stock_name = in.readString();
        this.report_no = in.readString();
        this.entrust_no = in.readString();
        this.business_price = in.readString();
        this.business_amount = in.readString();
        this.business_balance = in.readString();
        this.fare0 = in.readString();
        this.fare1 = in.readString();
        this.fare2 = in.readString();
        this.remark = in.readString();
        this.serial_no = in.readString();
        this.other_fare = in.readString();
    }

    public static final Creator<HistoryTradeBean> CREATOR = new Creator<HistoryTradeBean>() {
        @Override
        public HistoryTradeBean createFromParcel(Parcel source) {
            return new HistoryTradeBean(source);
        }

        @Override
        public HistoryTradeBean[] newArray(int size) {
            return new HistoryTradeBean[size];
        }
    };
}
