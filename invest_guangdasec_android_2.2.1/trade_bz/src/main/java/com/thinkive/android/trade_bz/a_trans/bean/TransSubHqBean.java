package com.thinkive.android.trade_bz.a_trans.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.thinkive.android.trade_bz.a_stock.bean.BaseBean;
import com.thinkive.android.trade_bz.others.JsonKey;

/**
 * 转股交易定价申报行情
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/1/6
 */
public class TransSubHqBean extends BaseBean {
    /**
     *证券代码
     */
    @JsonKey("stock_code")
    private String stock_code ="";
    /**
     *证券名称
     */
    @JsonKey("stock_name")
    private String stock_name ="";
    /**
     * 席位代码
     */
    @JsonKey("seat")
    private String seat ="";
    /**
     * 委托类别
     */
    @JsonKey("bsflag")
    private String bsflag ="";
    /**
     *委托数量
     */
    @JsonKey("orderqty")
    private String orderqty ="";
    /**
     *委托价格
     */
    @JsonKey("orderprice")
    private String orderprice ="";
    /**
     * 成交约定号
     */
    @JsonKey("confernum")
    private String confernum ="";
    /**
     *委托时间
     */
    @JsonKey("ordertime")
    private String ordertime ="";
    /**
     *记录状态
     */
    @JsonKey("status")
    private String status ="";
    /**
     * 备用标志
     */
    @JsonKey("remark")
    private String remark ="";
    /**
     * 业务标准名称
     */
    @JsonKey("bsflag_name")
    private String bsflag_name ="";
    /**
     * 状态名称
     */
    @JsonKey("status_name")
    private String status_name ="";


    public TransSubHqBean() {

    }

    public String getStock_code() {
        return stock_code;
    }

    public void setStock_code(String stock_code) {
        this.stock_code = stock_code;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public String getBsflag() {
        return bsflag;
    }

    public void setBsflag(String bsflag) {
        this.bsflag = bsflag;
    }

    public String getOrderqty() {
        return orderqty;
    }

    public void setOrderqty(String orderqty) {
        this.orderqty = orderqty;
    }

    public String getOrderprice() {
        return orderprice;
    }

    public void setOrderprice(String orderprice) {
        this.orderprice = orderprice;
    }

    public String getConfernum() {
        return confernum;
    }

    public void setConfernum(String confernum) {
        this.confernum = confernum;
    }

    public String getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(String ordertime) {
        this.ordertime = ordertime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStock_name() {
        return stock_name;
    }

    public void setStock_name(String stock_name) {
        this.stock_name = stock_name;
    }

    public String getBsflag_name() {
        return bsflag_name;
    }

    public void setBsflag_name(String bsflag_name) {
        this.bsflag_name = bsflag_name;
    }

    public String getStatus_name() {
        return status_name;
    }

    public void setStatus_name(String status_name) {
        this.status_name = status_name;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.stock_code);
        dest.writeString(this.stock_name);
        dest.writeString(this.seat);
        dest.writeString(this.bsflag);
        dest.writeString(this.orderqty);
        dest.writeString(this.orderprice);
        dest.writeString(this.confernum);
        dest.writeString(this.ordertime);
        dest.writeString(this.status);
        dest.writeString(this.remark);
        dest.writeString(this.bsflag_name);
        dest.writeString(this.status_name);
    }

    protected TransSubHqBean(Parcel in) {
        this.stock_code = in.readString();
        this.stock_name = in.readString();
        this.seat = in.readString();
        this.bsflag = in.readString();
        this.orderqty = in.readString();
        this.orderprice = in.readString();
        this.confernum = in.readString();
        this.ordertime = in.readString();
        this.status = in.readString();
        this.remark = in.readString();
        this.bsflag_name = in.readString();
        this.status_name = in.readString();
    }

    public static final Parcelable.Creator<TransSubHqBean> CREATOR = new Parcelable.Creator<TransSubHqBean>() {
        @Override
        public TransSubHqBean createFromParcel(Parcel source) {
            return new TransSubHqBean(source);
        }

        @Override
        public TransSubHqBean[] newArray(int size) {
            return new TransSubHqBean[size];
        }
    };
}
