package com.thinkive.android.trade_bz.a_hk.bean;

import com.thinkive.android.trade_bz.a_stock.bean.BaseBean;
import com.thinkive.android.trade_bz.others.JsonKey;

/**
 * 资金账户查询（我的持仓头部数据)
 * 301504(普通账户--资金账户查询)
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/6/23
 */
  public class HkMoneyAccountBean extends BaseBean {
    /**
     *  币种类别
     */
    @JsonKey("money_type")
   private String money_type="";
    /**
     *币种类别名称
     */
    @JsonKey("money_type_name")
    private String money_type_name="";
    /**
     * 当前余额
     */
    @JsonKey("current_balance")
    private String current_balance="";
    /**
     *可用资金
     */
    @JsonKey("enable_balance")
    private String enable_balance="";
    /**
     *  可取金额
     */
    @JsonKey("fetch_balance")
    private String fetch_balance="";
    /**
     * 冻结资金
     */
    private String frozen_balance="";
    /**
     * 总资金
     */
    @JsonKey("assert_val")
    private String assert_val="";
    /**
     *  持仓市值
     */
    @JsonKey("market_val")
    private String market_val="";
    /**
     * 基金市值
     */
    @JsonKey("fund_val")
    private String fund_val="";
    /**
     * 总盈亏
     */
    @JsonKey("total_income_balance")
    private String total_income_balance="";
    /**
     * 今日盈亏
     */
    @JsonKey("daily_income_balance")
    private String daily_income_balance="";

    public HkMoneyAccountBean() {
    }

    public String getMoney_type() {
        return money_type;
    }

    public void setMoney_type(String money_type) {
        this.money_type = money_type;
    }

    public String getMoney_type_name() {
        return money_type_name;
    }

    public void setMoney_type_name(String money_type_name) {
        this.money_type_name = money_type_name;
    }

    public String getCurrent_balance() {
        return current_balance;
    }

    public void setCurrent_balance(String current_balance) {
        this.current_balance = current_balance;
    }

    public String getEnable_balance() {
        return enable_balance;
    }

    public void setEnable_balance(String enable_balance) {
        this.enable_balance = enable_balance;
    }

    public String getFetch_balance() {
        return fetch_balance;
    }

    public void setFetch_balance(String fetch_balance) {
        this.fetch_balance = fetch_balance;
    }

    public String getFrozen_balance() {
        return frozen_balance;
    }

    public void setFrozen_balance(String frozen_balance) {
        this.frozen_balance = frozen_balance;
    }

    public String getAssert_val() {
        return assert_val;
    }

    public void setAssert_val(String assert_val) {
        this.assert_val = assert_val;
    }

    public String getMarket_val() {
        return market_val;
    }

    public void setMarket_val(String market_val) {
        this.market_val = market_val;
    }

    public String getFund_val() {
        return fund_val;
    }

    public void setFund_val(String fund_val) {
        this.fund_val = fund_val;
    }

    public String getTotal_income_balance() {
        return total_income_balance;
    }

    public void setTotal_income_balance(String total_income_balance) {
        this.total_income_balance = total_income_balance;
    }

    public String getDaily_income_balance() {
        return daily_income_balance;
    }

    public void setDaily_income_balance(String daily_income_balance) {
        this.daily_income_balance = daily_income_balance;
    }
}
