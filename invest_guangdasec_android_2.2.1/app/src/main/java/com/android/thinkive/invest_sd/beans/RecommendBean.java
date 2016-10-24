package com.android.thinkive.invest_sd.beans;

import java.io.Serializable;

/**
 * Created by xiangfan on 2015/9/22.
 */
public class RecommendBean implements Serializable {

    



    public String getIncomeunit() {
        return incomeunit;
    }

    public void setIncomeunit(String incomeunit) {
        this.incomeunit = incomeunit;
    }

    private String incomeunit;
    public String getEarnings() {
        return earnings;
    }

    public void setEarnings(String earnings) {
        this.earnings = earnings;
    }

    private String earnings;
    public String getCumulative_net() {
        return cumulative_net;
    }

    public void setCumulative_net(String cumulative_net) {
        this.cumulative_net = cumulative_net;
    }

    private String cumulative_net;
    private int product_id;
    private String product_code;
    private int risk_level;
    private String  product_name;
    /**
     * 收益描述
     */
    private String  yield_desc;
    /**
     * 起购金额
     */
    private String  ent_buy_limit;

    /**
     * 收益类型
     */
    private int yield_type =-1;
    public String getPer_buy_limit() {
        return per_buy_limit;
    }
    public void setPer_buy_limit(String per_buy_limit) {
        this.per_buy_limit = per_buy_limit;
    }

    private String per_buy_limit;
    private String  yieldrate1m;
    private String  yieldrate3m;
    private String  yieldrate6m;
    private String  yieldrate1y;
    private String  yieldrate1d;
    private String  annual_profit;
    private String  profit_of_10_thousands;
    private String  yield_type_input;
    private String  seven_days_annual_profit;

    public String getCurrent_price() {
        return current_price;
    }

    public void setCurrent_price(String current_price) {
        this.current_price = current_price;
    }

    public int getYield_type() {
        return yield_type;
    }

    public void setYield_type(int yield_type) {
        this.yield_type = yield_type;
    }

    private String current_price;
    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getProduct_code() {
        return product_code;
    }

    public void setProduct_code(String product_code) {
        this.product_code = product_code;
    }

    public int getRisk_level() {
        return risk_level;
    }

    public void setRisk_level(int risk_level) {
        this.risk_level = risk_level;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getYield_desc() {
        return yield_desc;
    }

    public void setYield_desc(String yield_desc) {
        this.yield_desc = yield_desc;
    }

    public String getEnt_buy_limit() {
        return ent_buy_limit;
    }

    public void setEnt_buy_limit(String ent_buy_limit) {
        this.ent_buy_limit = ent_buy_limit;
    }


    public String getYieldrate1m() {
        return yieldrate1m;
    }

    public void setYieldrate1m(String yieldrate1m) {
        this.yieldrate1m = yieldrate1m;
    }

    public String getYieldrate3m() {
        return yieldrate3m;
    }

    public void setYieldrate3m(String yieldrate3m) {
        this.yieldrate3m = yieldrate3m;
    }

    public String getYieldrate6m() {
        return yieldrate6m;
    }

    public void setYieldrate6m(String yieldrate6m) {
        this.yieldrate6m = yieldrate6m;
    }

    public String getYieldrate1y() {
        return yieldrate1y;
    }

    public void setYieldrate1y(String yieldrate1y) {
        this.yieldrate1y = yieldrate1y;
    }

    public String getYieldrate1d() {
        return yieldrate1d;
    }

    public void setYieldrate1d(String yieldrate1d) {
        this.yieldrate1d = yieldrate1d;
    }

    public String getAnnual_profit() {
        return annual_profit;
    }

    public void setAnnual_profit(String annual_profit) {
        this.annual_profit = annual_profit;
    }

    public String getProfit_of_10_thousands() {
        return profit_of_10_thousands;
    }

    public void setProfit_of_10_thousands(String profit_of_10_thousands) {
        this.profit_of_10_thousands = profit_of_10_thousands;
    }

    public String getYield_type_input() {
        return yield_type_input;
    }

    public void setYield_type_input(String yield_type_input) {
        this.yield_type_input = yield_type_input;
    }

    public String getSeven_days_annual_profit() {
        return seven_days_annual_profit;
    }

    public void setSeven_days_annual_profit(String seven_days_annual_profit) {
        this.seven_days_annual_profit = seven_days_annual_profit;
    }
}
