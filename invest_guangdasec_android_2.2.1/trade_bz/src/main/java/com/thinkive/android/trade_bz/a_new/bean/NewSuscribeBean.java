package com.thinkive.android.trade_bz.a_new.bean;

import com.thinkive.android.trade_bz.a_stock.bean.BaseBean;
import com.thinkive.android.trade_bz.others.JsonKey;

/**
 * 新股申购
 * 查询当前可申请额度（301519）
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/8/21
 */
public class NewSuscribeBean extends BaseBean {
    /**
     * 交易市场类别
     */
    @JsonKey("exchange_type")
    private String exchange_type = "";
    /**
     * 交易市场类别名称
     */
    @JsonKey("exchange_type_name")
    private String exchange_type_name = "";
    /**
     * 证券账号
     */
    @JsonKey("stock_account")
    private String stock_account = "";
    /**
     * 资金账号
     */
    @JsonKey("fund_account")
    private String fund_account = "";
    /**
     * 新股申购额度
     */
    @JsonKey("enable_amount")
    private String enable_amount = "";

    public NewSuscribeBean() {
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

    public String getFund_account() {
        return fund_account;
    }

    public void setFund_account(String fund_account) {
        this.fund_account = fund_account;
    }

    public String getEnable_amount() {
        return enable_amount;
    }

    public void setEnable_amount(String enable_amount) {
        this.enable_amount = enable_amount;
    }
}
