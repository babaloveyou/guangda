package com.thinkive.android.trade_bz.a_option.bean;

import android.os.Parcel;

import com.thinkive.android.trade_bz.a_stock.bean.BaseBean;
import com.thinkive.android.trade_bz.others.JsonKey;

/**
 * 备兑证券划转（锁定和解锁）305020 结果集接收 实体
 */
public class OptionCoveredSecuritiesTransferBean extends BaseBean{
    @JsonKey("init_date")
    private String init_date = "";//交易日期

    @JsonKey("entrust_no")
    private String entrust_no = "";//委托编号

    @JsonKey("entrust_time")
    private String entrust_time = "";//委托时间

    public OptionCoveredSecuritiesTransferBean() {
    }

    public String getInit_date() {
        return init_date;
    }

    public void setInit_date(String init_date) {
        this.init_date = init_date;
    }

    public String getEntrust_no() {
        return entrust_no;
    }

    public void setEntrust_no(String entrust_no) {
        this.entrust_no = entrust_no;
    }

    public String getEntrust_time() {
        return entrust_time;
    }

    public void setEntrust_time(String entrust_time) {
        this.entrust_time = entrust_time;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.init_date);
        dest.writeString(this.entrust_no);
        dest.writeString(this.entrust_time);
    }

    public OptionCoveredSecuritiesTransferBean(Parcel in) {
        this.init_date = in.readString();
        this.entrust_no = in.readString();
        this.entrust_time = in.readString();
    }

    public static final Creator<OptionCoveredSecuritiesTransferBean> CREATOR = new Creator<OptionCoveredSecuritiesTransferBean>() {
        @Override
        public OptionCoveredSecuritiesTransferBean createFromParcel(Parcel source) {
            return new OptionCoveredSecuritiesTransferBean(source);
        }

        @Override
        public OptionCoveredSecuritiesTransferBean[] newArray(int size) {
            return new OptionCoveredSecuritiesTransferBean[size];
        }
    };
}
