package com.thinkive.android.trade_bz.a_stock.bean;

import android.os.Parcel;

import com.thinkive.android.trade_bz.others.JsonKey;

/**
 * 银证转账 （存管银行查询）
 * 普通账户和融资融券账户共用
 * Announcements：
 *
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/8/10
 */
public class BankTransferBean extends BaseBean {

    //----------------------------银行存管查询300200/303037-------------------------
    /**
     * 银行帐户
     */
    @JsonKey("bank_account")
    private String bank_account = "";
    /**
     * 银行名称
     */
    @JsonKey("bank_name")
    private String bank_name = "";
    /**
     * 币种
     */
    @JsonKey("money_type")
    private String money_type = "";
    /**
     * 银行代码
     */
    @JsonKey("bank_code")
    private String bank_code = "";
    /**
     * 资金账号
     */
    @JsonKey("fund_account")
    private String fund_account = "";
    //---------------------转入--当前余额查询300204/303041-----------------
    /**
     * 资金余额
     */
    @JsonKey("fundeffect")
    private String fundeffect = "";
    //--------------------转账查询300202/303039----------------------------
    /**
     * 流水序号
     */
    @JsonKey("serial_no")
    private String serial_no = "";
    /**
     * 请求状态
     */
    @JsonKey("bktrans_status")
    private String bktrans_status = "";

    //-------------------查询转账银行业务信息300201/303038-------------------
    /**
     * 转账方向
     */
    @JsonKey("transfer_direction")
    private String transfer_direction = "";
    /**
     * 资金密码标志(0：否，1：是)
     */
    @JsonKey("fund_password_flag")
    private String fund_password_flag = "";
    /**
     * 银行密码标志(0：否，1：是)
     */
    @JsonKey("bank_password_flag")
    private String bank_password_flag = "";


    public BankTransferBean() {
    }

    public String getBank_account() {
        return bank_account;
    }

    public void setBank_account(String bank_account) {
        this.bank_account = bank_account;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public String getMoney_type() {
        return money_type;
    }

    public void setMoney_type(String money_type) {
        this.money_type = money_type;
    }

    public String getBank_code() {
        return bank_code;
    }

    public void setBank_code(String bank_code) {
        this.bank_code = bank_code;
    }

    public String getFund_account() {
        return fund_account;
    }

    public void setFund_account(String fund_account) {
        this.fund_account = fund_account;
    }

    public String getFundeffect() {
        return fundeffect;
    }

    public void setFundeffect(String fundeffect) {
        this.fundeffect = fundeffect;
    }

    public String getSerial_no() {
        return serial_no;
    }

    public void setSerial_no(String serial_no) {
        this.serial_no = serial_no;
    }

    public String getBktrans_status() {
        return bktrans_status;
    }

    public void setBktrans_status(String bktrans_status) {
        this.bktrans_status = bktrans_status;
    }

    public String getTransfer_direction() {
        return transfer_direction;
    }

    public void setTransfer_direction(String transfer_direction) {
        this.transfer_direction = transfer_direction;
    }

    public String getFund_password_flag() {
        return fund_password_flag;
    }

    public void setFund_password_flag(String fund_password_flag) {
        this.fund_password_flag = fund_password_flag;
    }

    public String getBank_password_flag() {
        return bank_password_flag;
    }

    public void setBank_password_flag(String bank_password_flag) {
        this.bank_password_flag = bank_password_flag;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.bank_account);
        dest.writeString(this.bank_name);
        dest.writeString(this.money_type);
        dest.writeString(this.bank_code);
        dest.writeString(this.fund_account);
        dest.writeString(this.fundeffect);
        dest.writeString(this.serial_no);
        dest.writeString(this.bktrans_status);
        dest.writeString(this.transfer_direction);
        dest.writeString(this.fund_password_flag);
        dest.writeString(this.bank_password_flag);
    }

    protected BankTransferBean(Parcel in) {
        this.bank_account = in.readString();
        this.bank_name = in.readString();
        this.money_type = in.readString();
        this.bank_code = in.readString();
        this.fund_account = in.readString();
        this.fundeffect = in.readString();
        this.serial_no = in.readString();
        this.bktrans_status = in.readString();
        this.transfer_direction = in.readString();
        this.fund_password_flag = in.readString();
        this.bank_password_flag = in.readString();
    }

    public static final Creator<BankTransferBean> CREATOR = new Creator<BankTransferBean>() {
        @Override
        public BankTransferBean createFromParcel(Parcel source) {
            return new BankTransferBean(source);
        }

        @Override
        public BankTransferBean[] newArray(int size) {
            return new BankTransferBean[size];
        }
    };
}
