package com.thinkive.android.trade_bz.a_option.bean;

import android.os.Parcel;

import com.thinkive.android.trade_bz.a_stock.bean.BaseBean;
import com.thinkive.android.trade_bz.others.JsonKey;

/**
 * 个股期权持仓实体类（305003）
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/6/14
 */
public class OptionHoldStockBean extends BaseBean {
    @JsonKey("fund_account")
    private String fund_account = ""; // 衍生品资金账户
    @JsonKey("exchange_type")
    private String exchange_type = ""; // 交易类别
    @JsonKey("exchange_type_name")
    private String exchange_type_name = ""; // 交易类别名称
    @JsonKey("option_account")
    private String option_account = ""; //  衍生品合约账户
    @JsonKey("opthold_type")
    private String opthold_type = ""; // 期权持仓类别（'0'-权利方 '1'-义务方 '2'-备兑方）
    @JsonKey("opthold_type_name")
    private String opthold_type_name = ""; // 期权持仓类别名称
    @JsonKey("option_code")
    private String option_code = ""; // 期权合约编码
    @JsonKey("stock_code")
    private String  stock_code = ""; // 证券代码
    @JsonKey("optcontract_id")
    private String optcontract_id = ""; //  合约交易代码
    @JsonKey("option_name")
    private String option_name = ""; //  期权名称
    @JsonKey("option_type")
    private String option_type = ""; // 期权类别（'C'-认购 'P'-认沽）
    @JsonKey("option_type_name")
    private String option_type_name = ""; // 期权类别名称
    @JsonKey("current_amount")
    private String current_amount = ""; // 当前数量
    @JsonKey("hold_amount")
    private String hold_amount = ""; //  持有数量（包括委托未成交部分）
    @JsonKey("enable_amount")
    private String enable_amount = ""; //  可用数量
    @JsonKey("real_open_amount")
    private String real_open_amount = ""; //  今开仓持仓量
    @JsonKey("real_drop_amount")
    private String real_drop_amount = ""; // 今平仓持仓量
    @JsonKey("entrust_drop_amount")
    private String entrust_drop_amount = ""; //  今平仓委托量
    @JsonKey("last_price")
    private String last_price = ""; // 最新价
    @JsonKey("cost_price")
    private String cost_price = ""; //   开仓均价
    @JsonKey("exercise_price")
    private String exercise_price = ""; //行权价格
    @JsonKey("market_value")
    private String market_value = ""; // 期权市值
    @JsonKey("av_income_balance")
    private String av_income_balance = ""; // 实现盈亏
    @JsonKey("income_balance")
    private String income_balance = ""; //盈亏金额
    @JsonKey("exercise_income")
    private String exercise_income = ""; // 行权盈亏金额
    @JsonKey("cost_balance")
    private String cost_balance = ""; //持仓成本（累积买+当日买-累积卖-当日卖）
    @JsonKey("duty_used_bail")
    private String duty_used_bail = ""; //义务仓占用保证金
    @JsonKey("exercise_date")
    private String exercise_date = ""; //  行权日期

    public OptionHoldStockBean() {

    }

    public String getFund_account() {
        return fund_account;
    }

    public void setFund_account(String fund_account) {
        this.fund_account = fund_account;
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

    public String getOption_account() {
        return option_account;
    }

    public void setOption_account(String option_account) {
        this.option_account = option_account;
    }

    public String getOpthold_type() {
        return opthold_type;
    }

    public void setOpthold_type(String opthold_type) {
        this.opthold_type = opthold_type;
    }

    public String getOpthold_type_name() {
        return opthold_type_name;
    }

    public void setOpthold_type_name(String opthold_type_name) {
        this.opthold_type_name = opthold_type_name;
    }

    public String getOption_code() {
        return option_code;
    }

    public void setOption_code(String option_code) {
        this.option_code = option_code;
    }

    public String getStock_code() {
        return stock_code;
    }

    public void setStock_code(String stock_code) {
        this.stock_code = stock_code;
    }

    public String getOptcontract_id() {
        return optcontract_id;
    }

    public void setOptcontract_id(String optcontract_id) {
        this.optcontract_id = optcontract_id;
    }

    public String getOption_name() {
        return option_name;
    }

    public void setOption_name(String option_name) {
        this.option_name = option_name;
    }

    public String getOption_type() {
        return option_type;
    }

    public void setOption_type(String option_type) {
        this.option_type = option_type;
    }

    public String getOption_type_name() {
        return option_type_name;
    }

    public void setOption_type_name(String option_type_name) {
        this.option_type_name = option_type_name;
    }

    public String getCurrent_amount() {
        return current_amount;
    }

    public void setCurrent_amount(String current_amount) {
        this.current_amount = current_amount;
    }

    public String getHold_amount() {
        return hold_amount;
    }

    public void setHold_amount(String hold_amount) {
        this.hold_amount = hold_amount;
    }

    public String getEnable_amount() {
        return enable_amount;
    }

    public void setEnable_amount(String enable_amount) {
        this.enable_amount = enable_amount;
    }

    public String getReal_open_amount() {
        return real_open_amount;
    }

    public void setReal_open_amount(String real_open_amount) {
        this.real_open_amount = real_open_amount;
    }

    public String getReal_drop_amount() {
        return real_drop_amount;
    }

    public void setReal_drop_amount(String real_drop_amount) {
        this.real_drop_amount = real_drop_amount;
    }

    public String getEntrust_drop_amount() {
        return entrust_drop_amount;
    }

    public void setEntrust_drop_amount(String entrust_drop_amount) {
        this.entrust_drop_amount = entrust_drop_amount;
    }

    public String getLast_price() {
        return last_price;
    }

    public void setLast_price(String last_price) {
        this.last_price = last_price;
    }

    public String getCost_price() {
        return cost_price;
    }

    public void setCost_price(String cost_price) {
        this.cost_price = cost_price;
    }

    public String getExercise_price() {
        return exercise_price;
    }

    public void setExercise_price(String exercise_price) {
        this.exercise_price = exercise_price;
    }

    public String getMarket_value() {
        return market_value;
    }

    public void setMarket_value(String market_value) {
        this.market_value = market_value;
    }

    public String getAv_income_balance() {
        return av_income_balance;
    }

    public void setAv_income_balance(String av_income_balance) {
        this.av_income_balance = av_income_balance;
    }

    public String getIncome_balance() {
        return income_balance;
    }

    public void setIncome_balance(String income_balance) {
        this.income_balance = income_balance;
    }

    public String getExercise_income() {
        return exercise_income;
    }

    public void setExercise_income(String exercise_income) {
        this.exercise_income = exercise_income;
    }

    public String getCost_balance() {
        return cost_balance;
    }

    public void setCost_balance(String cost_balance) {
        this.cost_balance = cost_balance;
    }

    public String getDuty_used_bail() {
        return duty_used_bail;
    }

    public void setDuty_used_bail(String duty_used_bail) {
        this.duty_used_bail = duty_used_bail;
    }

    public String getExercise_date() {
        return exercise_date;
    }

    public void setExercise_date(String exercise_date) {
        this.exercise_date = exercise_date;
    }

    public OptionHoldStockBean(Parcel in) {
        this.fund_account = in.readString();
        this.exchange_type = in.readString();
        this.exchange_type_name = in.readString();
        this.option_account = in.readString();
        this.opthold_type = in.readString();
        this.opthold_type_name = in.readString();
        this.option_code = in.readString();
        this.stock_code = in.readString();
        this.optcontract_id = in.readString();
        this.option_name = in.readString();
        this.option_type = in.readString();
        this.option_type_name = in.readString();
        this.current_amount = in.readString();
        this.hold_amount = in.readString();
        this.enable_amount = in.readString();
        this.real_open_amount = in.readString();
        this.real_drop_amount = in.readString();
        this.entrust_drop_amount = in.readString();
        this.last_price = in.readString();
        this.cost_price = in.readString();
        this.exercise_price = in.readString();
        this.market_value = in.readString();
        this.av_income_balance = in.readString();
        this.income_balance = in.readString();
        this.exercise_income = in.readString();
        this.cost_balance = in.readString();
        this.duty_used_bail = in.readString();
        this.exercise_date = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.fund_account);
        dest.writeString(this.exchange_type);
        dest.writeString(this.exchange_type_name);
        dest.writeString(this.option_account);
        dest.writeString(this.opthold_type);
        dest.writeString(this.opthold_type_name);
        dest.writeString(this.option_code);
        dest.writeString(this.stock_code);
        dest.writeString(this.optcontract_id);
        dest.writeString(this.option_name);
        dest.writeString(this.option_type);
        dest.writeString(this.option_type_name);
        dest.writeString(this.current_amount);
        dest.writeString(this.hold_amount);
        dest.writeString(this.enable_amount);
        dest.writeString(this.real_open_amount);
        dest.writeString(this.real_drop_amount);
        dest.writeString(this.entrust_drop_amount);
        dest.writeString(this.last_price);
        dest.writeString(this.cost_price);
        dest.writeString(this.exercise_price);
        dest.writeString(this.market_value);
        dest.writeString(this.av_income_balance);
        dest.writeString(this.income_balance);
        dest.writeString(this.exercise_income);
        dest.writeString(this.cost_balance);
        dest.writeString(this.duty_used_bail);
        dest.writeString(this.exercise_date);
    }

    public static final Creator<OptionHoldStockBean> CREATOR = new Creator<OptionHoldStockBean>() {
        @Override
        public OptionHoldStockBean createFromParcel(Parcel parcel) {
            return new OptionHoldStockBean(parcel);
        }

        @Override
        public OptionHoldStockBean[] newArray(int i) {
            return new OptionHoldStockBean[i];
        }
    };
}
