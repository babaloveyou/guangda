package com.thinkive.android.trade_bz.a_option.bean;

import android.os.Parcel;

import com.thinkive.android.trade_bz.a_stock.bean.BaseBean;
import com.thinkive.android.trade_bz.others.JsonKey;

/**
 *
 * Description：备兑锁定 合约查询结果，对应实体 305032 <br>
 * Author：晏政清 <br>
 * Corporation：深圳市思迪信息技术股份有限公司 <br>
 * Date：2016/7/28 <br>
 */
public class OptionContractLockBean extends BaseBean{
    @JsonKey("exchange_type")
    private String exchange_type = "";//交易市场
    @JsonKey("trade_sector")
    private String trade_sector = "";//交易板块（见数据字典)
    @JsonKey("stock_account")
    private String stock_account = "";//证券账户
    @JsonKey("stock_accountNo")
    private String stock_accountNo = "";//证券账户子编码
    @JsonKey("fund_account_opt")
    private String fund_account_opt ="";//衍生品资金账户
    @JsonKey("fund_account")
    private String fund_account = "";//期权合约账户
    @JsonKey("stock_code")
    private String stock_code = "";//证券代码
    @JsonKey("order_qty")
    private String order_qty = "";//可委托数量
    public OptionContractLockBean() {
    }

    public String getExchange_type() {
        return exchange_type;
    }

    public void setExchange_type(String exchange_type) {
        this.exchange_type = exchange_type;
    }

    public String getTrade_sector() {
        return trade_sector;
    }

    public void setTrade_sector(String trade_sector) {
        this.trade_sector = trade_sector;
    }

    public String getStock_account() {
        return stock_account;
    }

    public void setStock_account(String stock_account) {
        this.stock_account = stock_account;
    }

    public String getStock_accountNo() {
        return stock_accountNo;
    }

    public void setStock_accountNo(String stock_accountNo) {
        this.stock_accountNo = stock_accountNo;
    }

    public String getFund_account_opt() {
        return fund_account_opt;
    }

    public void setFund_account_opt(String fund_account_opt) {
        this.fund_account_opt = fund_account_opt;
    }

    public String getFund_account() {
        return fund_account;
    }

    public void setFund_account(String fund_account) {
        this.fund_account = fund_account;
    }

    public String getStock_code() {
        return stock_code;
    }

    public void setStock_code(String stock_code) {
        this.stock_code = stock_code;
    }

    public String getOrder_qty() {
        return order_qty;
    }

    public void setOrder_qty(String order_qty) {
        this.order_qty = order_qty;
    }

    public OptionContractLockBean(Parcel in) {
        this.exchange_type = in.readString();
        this.trade_sector = in.readString();
        this.stock_account = in.readString();
        this.stock_accountNo = in.readString();
        this.fund_account_opt = in.readString();
        this.fund_account = in.readString();
        this.stock_code = in.readString();
        this.order_qty = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.exchange_type);
        dest.writeString(this.trade_sector);
        dest.writeString(this.stock_account);
        dest.writeString(this.stock_accountNo);
        dest.writeString(this.fund_account_opt);
        dest.writeString(this.fund_account);
        dest.writeString(this.stock_code);
        dest.writeString(this.order_qty);
    }

    public static final Creator<OptionContractLockBean> CREATOR = new Creator<OptionContractLockBean>() {
        @Override
        public OptionContractLockBean createFromParcel(Parcel parcel) {
            return new OptionContractLockBean(parcel);
        }

        @Override
        public OptionContractLockBean[] newArray(int i) {
            return new OptionContractLockBean[i];
        }
    };
}
