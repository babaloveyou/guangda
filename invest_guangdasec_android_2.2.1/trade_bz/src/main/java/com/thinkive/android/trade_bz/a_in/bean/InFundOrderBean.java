package com.thinkive.android.trade_bz.a_in.bean;

import android.os.Parcel;

import com.thinkive.android.trade_bz.a_stock.bean.BaseBean;
import com.thinkive.android.trade_bz.others.JsonKey;

/**
 *
 * Description：场内基金下单（301501）结果，对应的bean <br>
 * Author：晏政清 <br>
 * Corporation：深圳市思迪信息技术股份有限公司 <br>
 * Date：2016/8/11 <br>
 */

public class InFundOrderBean extends BaseBean{
    @JsonKey("entrust_no")
    private String entrust_no = "";//委托编号
    @JsonKey("batch_no")
    private String batch_no = "";//委托批号
    @JsonKey("report_no")
    private String report_no = "";//申报编号

    public InFundOrderBean() {
    }

    public String getEntrust_no() {
        return entrust_no;
    }

    public void setEntrust_no(String entrust_no) {
        this.entrust_no = entrust_no;
    }

    public String getBatch_no() {
        return batch_no;
    }

    public void setBatch_no(String batch_no) {
        this.batch_no = batch_no;
    }

    public String getReport_no() {
        return report_no;
    }

    public void setReport_no(String report_no) {
        this.report_no = report_no;
    }

    public InFundOrderBean(Parcel in) {
        this.entrust_no = in.readString();
        this.batch_no = in.readString();
        this.report_no = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.entrust_no);
        dest.writeString(this.batch_no);
        dest.writeString(this.report_no);
    }

    public static final Creator<InFundOrderBean> CREATOR = new Creator<InFundOrderBean>() {
        @Override
        public InFundOrderBean createFromParcel(Parcel source) {
            return new InFundOrderBean(source);
        }

        @Override
        public InFundOrderBean[] newArray(int size) {
            return new InFundOrderBean[size];
        }
    };
}
