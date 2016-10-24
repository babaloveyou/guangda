package com.thinkive.android.trade_bz.a_stock.bean;

import android.os.Parcel;

import com.thinkive.android.trade_bz.others.JsonKey;


/**
 *  退市板块协议签署(301512)
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/8/1
 */

public class SignAgreementBean extends BaseBean {

    @JsonKey("exchange_type")
    private String exchange_type = ""; //交易市场类别
    @JsonKey("exchange_type_name")
    private String exchange_type_name = ""; //交易市场名称
    @JsonKey("stock_account")
    private String stock_account = ""; //证券账号
    @JsonKey("holder_kind")
    private String holder_kind = ""; //账户类别
    @JsonKey("main_flag")
    private String main_flag = ""; //主账标志(0:否，1：是)
    @JsonKey("holder_status")
    private String holder_status = ""; //账户状态
    @JsonKey("holder_rights")
    private String holder_rights = ""; //股东权限

    public SignAgreementBean() {

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

    public String getHolder_kind() {
        return holder_kind;
    }

    public void setHolder_kind(String holder_kind) {
        this.holder_kind = holder_kind;
    }

    public String getMain_flag() {
        return main_flag;
    }

    public void setMain_flag(String main_flag) {
        this.main_flag = main_flag;
    }

    public String getHolder_status() {
        return holder_status;
    }

    public void setHolder_status(String holder_status) {
        this.holder_status = holder_status;
    }

    public String getHolder_rights() {
        return holder_rights;
    }

    public void setHolder_rights(String holder_rights) {
        this.holder_rights = holder_rights;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.exchange_type);
        dest.writeString(this.exchange_type_name);
        dest.writeString(this.stock_account);
        dest.writeString(this.holder_kind);
        dest.writeString(this.main_flag);
        dest.writeString(this.holder_status);
        dest.writeString(this.holder_rights);
    }

    protected SignAgreementBean(Parcel in) {
        this.exchange_type = in.readString();
        this.exchange_type_name = in.readString();
        this.stock_account = in.readString();
        this.holder_kind = in.readString();
        this.main_flag = in.readString();
        this.holder_status = in.readString();
        this.holder_rights = in.readString();
    }

    public static final Creator<SignAgreementBean> CREATOR = new Creator<SignAgreementBean>() {
        @Override
        public SignAgreementBean createFromParcel(Parcel source) {
            return new SignAgreementBean(source);
        }

        @Override
        public SignAgreementBean[] newArray(int size) {
            return new SignAgreementBean[size];
        }
    };
}
