package com.thinkive.android.trade_bz.a_stock.bean;

import android.os.Parcel;

import com.thinkive.android.trade_bz.others.JsonKey;


/**
 *  客户证券账户权限查询(300115)
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/8/1
 */

public class SignStockAccountLimitBean extends BaseBean {
    @JsonKey("stock_account")
    private String stock_account = ""; //证券账号
    @JsonKey("exchange_type")
    private String exchange_type = ""; //交易市场类别
    @JsonKey("client_rights")
    private String client_rights = ""; //所有权限标识
    @JsonKey("has_delist")
    private String has_delist = ""; //  是否开通权限 1：开通   0：未开通
    @JsonKey("has_delist_name")
    private String has_delist_name = ""; //  是否开通权限 1：开通   0：未开通
    @JsonKey("business_type")
    private String business_type = ""; //业务类别(权限编号) 3: 开通退市证券买入权限    4: 开通风险警示证券买入权限
    @JsonKey("business_type_name")
    private String business_type_name = ""; //业务名称
    @JsonKey("content")
    private String content = ""; // 协议内容
    @JsonKey("title")
    private String title = ""; //协议标题

    public SignStockAccountLimitBean() {

    }

    public String getStock_account() {
        return stock_account;
    }

    public void setStock_account(String stock_account) {
        this.stock_account = stock_account;
    }

    public String getExchange_type() {
        return exchange_type;
    }

    public void setExchange_type(String exchange_type) {
        this.exchange_type = exchange_type;
    }

    public String getClient_rights() {
        return client_rights;
    }

    public void setClient_rights(String client_rights) {
        this.client_rights = client_rights;
    }

    public String getHas_delist() {
        return has_delist;
    }

    public void setHas_delist(String has_delist) {
        this.has_delist = has_delist;
    }

    public String getBusiness_type() {
        return business_type;
    }

    public void setBusiness_type(String business_type) {
        this.business_type = business_type;
    }

    public String getBusiness_type_name() {
        return business_type_name;
    }

    public void setBusiness_type_name(String business_type_name) {
        this.business_type_name = business_type_name;
    }

    public String getHas_delist_name() {
        return has_delist_name;
    }

    public void setHas_delist_name(String has_delist_name) {
        this.has_delist_name = has_delist_name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.stock_account);
        dest.writeString(this.exchange_type);
        dest.writeString(this.client_rights);
        dest.writeString(this.has_delist);
        dest.writeString(this.has_delist_name);
        dest.writeString(this.business_type);
        dest.writeString(this.business_type_name);
        dest.writeString(this.content);
        dest.writeString(this.title);
    }

    protected SignStockAccountLimitBean(Parcel in) {
        this.stock_account = in.readString();
        this.exchange_type = in.readString();
        this.client_rights = in.readString();
        this.has_delist = in.readString();
        this.has_delist_name = in.readString();
        this.business_type = in.readString();
        this.business_type_name = in.readString();
        this.content = in.readString();
        this.title = in.readString();
    }

    @Override
    public String toString() {
        return "SignStockAccountLimitBean{" +
                "stock_account='" + stock_account + '\'' +
                ", exchange_type='" + exchange_type + '\'' +
                ", client_rights='" + client_rights + '\'' +
                ", has_delist='" + has_delist + '\'' +
                ", has_delist_name='" + has_delist_name + '\'' +
                ", business_type='" + business_type + '\'' +
                ", business_type_name='" + business_type_name + '\'' +
                ", content='" + content + '\'' +
                ", title='" + title + '\'' +
                '}';
    }

    public static final Creator<SignStockAccountLimitBean> CREATOR = new Creator<SignStockAccountLimitBean>() {
        @Override
        public SignStockAccountLimitBean createFromParcel(Parcel source) {
            return new SignStockAccountLimitBean(source);
        }

        @Override
        public SignStockAccountLimitBean[] newArray(int size) {
            return new SignStockAccountLimitBean[size];
        }
    };
}
