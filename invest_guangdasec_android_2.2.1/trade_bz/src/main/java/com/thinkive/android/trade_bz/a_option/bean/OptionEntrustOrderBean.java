package com.thinkive.android.trade_bz.a_option.bean;

import android.os.Parcel;

import com.thinkive.android.trade_bz.a_stock.bean.BaseBean;
import com.thinkive.android.trade_bz.others.JsonKey;

/**
 *
 * Description：期权委托(305001) <br>
 * Author：晏政清 <br>
 * Corporation：深圳市思迪信息技术股份有限公司 <br>
 * Date：2016/7/23 <br>
 */
public class OptionEntrustOrderBean extends BaseBean{
    @JsonKey("init_date")
    private String init_date="";//交易日期
    @JsonKey("entrust_no")
    private String entrust_no="";//委托编号
    @JsonKey("report_no")
    private String report_no="";//申请编号
    @JsonKey("batch_no")
    private String batch_no="";//委托批号
    @JsonKey("entrust_time")
    private String entrust_time="";//委托时间

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

    public String getReport_no() {
        return report_no;
    }

    public void setReport_no(String report_no) {
        this.report_no = report_no;
    }

    public String getBatch_no() {
        return batch_no;
    }

    public void setBatch_no(String batch_no) {
        this.batch_no = batch_no;
    }

    public String getEntrust_time() {
        return entrust_time;
    }

    public void setEntrust_time(String entrust_time) {
        this.entrust_time = entrust_time;
    }

    public OptionEntrustOrderBean() {
    }

    public OptionEntrustOrderBean(Parcel in) {
        this.init_date=in.readString();
        this.entrust_no=in.readString();
        this.report_no=in.readString();
        this.batch_no=in.readString();
        this.entrust_time=in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.init_date);
        dest.writeString(this.entrust_no);
        dest.writeString(this.report_no);
        dest.writeString(this.batch_no);
        dest.writeString(this.entrust_time);
    }

    public static final Creator<OptionEntrustOrderBean> CREATOR = new Creator<OptionEntrustOrderBean>() {
        @Override
        public OptionEntrustOrderBean createFromParcel(Parcel parcel) {
            return new OptionEntrustOrderBean(parcel);
        }

        @Override
        public OptionEntrustOrderBean[] newArray(int i) {
            return new OptionEntrustOrderBean[i];
        }
    };

}
