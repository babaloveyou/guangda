package com.thinkive.android.trade_bz.a_rr.bean;

import com.thinkive.android.trade_bz.a_stock.bean.BaseBean;
import com.thinkive.android.trade_bz.others.JsonKey;

/**
 *  融资融券交易--现券还券联动(303013)
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/1/21
 */
public class RStockToStockLinkBean extends BaseBean {
    /**
     * 证券代码
     */
    @JsonKey("stock_code")
    private String stock_code = "";
    /**
     * 证券名称
     */
    @JsonKey("stock_name")
    private String stock_name = "";
    /**
     * 市场交易类别
     */
    @JsonKey("exchange_type")
    private String exchange_type = "";
    /**
     * 证券账号
     */
    @JsonKey("stock_account")
    private String stock_account = "";
    /**
     * 总应还数量 (现券还券有效)
     */
    @JsonKey("enable_return_amount")
    private String enable_return_amount = "";
    /**
     * 可卖（还）数量(现券还券有效)
     */
    @JsonKey("enable_amount")
    private String enable_amount = "";


/*******************直接还款联动 303012****************************8*/
    /**
     * 现金还款可用资金
     */
    @JsonKey("fin_enrepaid_balance")
    private String fin_enrepaid_balance = "";
    /**
     * 需还款额
     */
    @JsonKey("real_compact_balance")
    private String real_compact_balance = "";

    public RStockToStockLinkBean() {

    }

    public String getStock_code() {
        return stock_code;
    }

    public void setStock_code(String stock_code) {
        this.stock_code = stock_code;
    }

    public String getStock_name() {
        return stock_name;
    }

    public void setStock_name(String stock_name) {
        this.stock_name = stock_name;
    }

    public String getExchange_type() {
        return exchange_type;
    }

    public void setExchange_type(String exchange_type) {
        this.exchange_type = exchange_type;
    }

    public String getStock_account() {
        return stock_account;
    }

    public void setStock_account(String stock_account) {
        this.stock_account = stock_account;
    }

    public String getEnable_return_amount() {
        return enable_return_amount;
    }

    public void setEnable_return_amount(String enable_return_amount) {
        this.enable_return_amount = enable_return_amount;
    }

    public String getEnable_amount() {
        return enable_amount;
    }

    public void setEnable_amount(String enable_amount) {
        this.enable_amount = enable_amount;
    }

    public String getFin_enrepaid_balance() {
        return fin_enrepaid_balance;
    }

    public void setFin_enrepaid_balance(String fin_enrepaid_balance) {
        this.fin_enrepaid_balance = fin_enrepaid_balance;
    }

    public String getReal_compact_balance() {
        return real_compact_balance;
    }

    public void setReal_compact_balance(String real_compact_balance) {
        this.real_compact_balance = real_compact_balance;
    }
}
