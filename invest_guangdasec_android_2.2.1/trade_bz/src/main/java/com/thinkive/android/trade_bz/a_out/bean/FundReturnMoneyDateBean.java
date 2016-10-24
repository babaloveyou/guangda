package com.thinkive.android.trade_bz.a_out.bean;

import android.os.Parcel;

import com.thinkive.android.trade_bz.a_stock.bean.BaseBean;
import com.thinkive.android.trade_bz.others.JsonKey;

/**
 *  基金还款日期查询(302057)
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/11/5
 */

public class FundReturnMoneyDateBean extends BaseBean {
    /**
     * 定时定额种类
     */
    @JsonKey("entrust_no")
    private String entrust_no="";
    /**
     * 周期类型
     */
    @JsonKey("batch_no")
    private String batch_no="";
    /**
     * 周期数量
     */
    @JsonKey("report_no")
    private String report_no="";
    /**
     *基金公司代码
     */
    @JsonKey("fund_company")
    private String fund_company="";
    /**
     *种类名称
     */
    @JsonKey("save_plan_name")
    private String save_plan_name="";
    /**
     *还款日期
     */
    @JsonKey("pay_day")
    private String pay_day="";
    /**
     *
     */
    @JsonKey("cycle_num")
    private String cycle_num = "";
    /**
     *定投种类
     */
    @JsonKey("save_plan_cls")
    private String save_plan_cls ="";

    public FundReturnMoneyDateBean() {

    }

    public String getCycle_num() {
        return cycle_num;
    }

    public void setCycle_num(String cycle_num) {
        this.cycle_num = cycle_num;
    }

    public String getSave_plan_cls() {
        return save_plan_cls;
    }

    public void setSave_plan_cls(String save_plan_cls) {
        this.save_plan_cls = save_plan_cls;
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

    public String getFund_company() {
        return fund_company;
    }

    public void setFund_company(String fund_company) {
        this.fund_company = fund_company;
    }

    public String getSave_plan_name() {
        return save_plan_name;
    }

    public void setSave_plan_name(String save_plan_name) {
        this.save_plan_name = save_plan_name;
    }

    public String getPay_day() {
        return pay_day;
    }

    public void setPay_day(String pay_day) {
        this.pay_day = pay_day;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.entrust_no);
        dest.writeString(this.batch_no);
        dest.writeString(this.report_no);
        dest.writeString(this.fund_company);
        dest.writeString(this.save_plan_name);
        dest.writeString(this.pay_day);
        dest.writeString(this.cycle_num);
        dest.writeString(this.save_plan_cls);
    }

    protected FundReturnMoneyDateBean(Parcel in) {
        this.entrust_no = in.readString();
        this.batch_no = in.readString();
        this.report_no = in.readString();
        this.fund_company = in.readString();
        this.save_plan_name = in.readString();
        this.pay_day = in.readString();
        this.cycle_num = in.readString();
        this.save_plan_cls = in.readString();
    }

    public static final Creator<FundReturnMoneyDateBean> CREATOR = new Creator<FundReturnMoneyDateBean>() {
        @Override
        public FundReturnMoneyDateBean createFromParcel(Parcel source) {
            return new FundReturnMoneyDateBean(source);
        }

        @Override
        public FundReturnMoneyDateBean[] newArray(int size) {
            return new FundReturnMoneyDateBean[size];
        }
    };
}
