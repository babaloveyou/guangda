package com.thinkive.android.trade_bz.a_hk.bean;


import android.os.Parcel;

import com.thinkive.android.trade_bz.a_stock.bean.BaseBean;

/**
 * 港股通  历史委托（301608）
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/11/2
 */
public class HKCompanyActionTypeBean extends BaseBean {
    /**
     * 委托名称
     */
    private String entrust_way_name="";
    /**
     * 委托名称编号
     */
    private String entrust_way_num="";

    public HKCompanyActionTypeBean() {

    }

    public String getEntrust_way_name() {
        return entrust_way_name;
    }

    public void setEntrust_way_name(String entrust_way_name) {
        this.entrust_way_name = entrust_way_name;
    }

    public String getEntrust_way_num() {
        return entrust_way_num;
    }

    public void setEntrust_way_num(String entrust_way_num) {
        this.entrust_way_num = entrust_way_num;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.entrust_way_name);
        dest.writeString(this.entrust_way_num);
    }

    protected HKCompanyActionTypeBean(Parcel in) {
        this.entrust_way_name = in.readString();
        this.entrust_way_num = in.readString();
    }

    public static final Creator<HKCompanyActionTypeBean> CREATOR = new Creator<HKCompanyActionTypeBean>() {
        @Override
        public HKCompanyActionTypeBean createFromParcel(Parcel source) {
            return new HKCompanyActionTypeBean(source);
        }

        @Override
        public HKCompanyActionTypeBean[] newArray(int size) {
            return new HKCompanyActionTypeBean[size];
        }
    };
}