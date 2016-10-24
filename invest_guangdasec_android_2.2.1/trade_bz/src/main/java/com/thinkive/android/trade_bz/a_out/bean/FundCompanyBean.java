package com.thinkive.android.trade_bz.a_out.bean;

import android.os.Parcel;

import com.thinkive.android.trade_bz.a_stock.bean.BaseBean;
import com.thinkive.android.trade_bz.others.JsonKey;

/**
 * 场外基金公司查询
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/5/9
 */

public class FundCompanyBean extends BaseBean {
    /**
     * 基金公司代码
     */
    @JsonKey("fund_company")
    private String fund_company="";
    /**
     *基金公司名称
     */
    @JsonKey("company_name")
    private String company_name="";
    /**
     *基金公司状态（0：正常交易，9：暂停交易）
     */
    @JsonKey("status")
    private String status="";

    public FundCompanyBean() {

    }

    public String getFund_company() {
        return fund_company;
    }

    public void setFund_company(String fund_company) {
        this.fund_company = fund_company;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.fund_company);
        dest.writeString(this.company_name);
        dest.writeString(this.status);
    }

    protected FundCompanyBean(Parcel in) {
        this.fund_company = in.readString();
        this.company_name = in.readString();
        this.status = in.readString();
    }

    public static final Creator<FundCompanyBean> CREATOR = new Creator<FundCompanyBean>() {
        @Override
        public FundCompanyBean createFromParcel(Parcel source) {
            return new FundCompanyBean(source);
        }

        @Override
        public FundCompanyBean[] newArray(int size) {
            return new FundCompanyBean[size];
        }
    };
}
