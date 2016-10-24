package com.thinkive.android.trade_bz.a_option.bean;

import android.os.Parcel;

import com.thinkive.android.trade_bz.a_stock.bean.BaseBean;
import com.thinkive.android.trade_bz.others.JsonKey;

/**
 *
 * Description：期权代码信息查询305001 <br>
 * Author：晏政清 <br>
 * Corporation：深圳市思迪信息技术股份有限公司 <br>
 * Date：2016/7/18 <br>
 */

public class OptionInfoBean extends BaseBean{
    @JsonKey("exercise_month")
    private String exercise_month="";//行权年月
    @JsonKey("stock_name")
    private String stock_name="";//证券名称
    @JsonKey("stock_code")
    private String stock_code="";//证券代码
    @JsonKey("option_code")
    private String option_code="";//期权合约编码
    @JsonKey("optcontract_id")
    private String optcontract_id="";//交易合同编码
    @JsonKey("exercise_price")
    private String exercise_price="";//行权价格
    @JsonKey("min_price")
    private String min_price="";//跌停价格
    @JsonKey("max_price")
    private String max_price="";//涨停价格
    @JsonKey("opt_price_step")
    private String opt_price_step="";//最小价差
    @JsonKey("lastprice")
    private String lastprice="";//最新价格
    @JsonKey("enable_amount")
    private String enable_amount="";//可用数量
    @JsonKey("stock_account")
    private String stock_account="";//证券账户
    @JsonKey("option_name")
    private String option_name="";//期权名称
    @JsonKey("exchange_type")
    private String exchange_type="";//交易市场类别

    public String getExercise_month() {
        return exercise_month;
    }

    public void setExercise_month(String exercise_month) {
        this.exercise_month = exercise_month;
    }

    public String getStock_name() {
        return stock_name;
    }

    public void setStock_name(String stock_name) {
        this.stock_name = stock_name;
    }

    public String getStock_code() {
        return stock_code;
    }

    public void setStock_code(String stock_code) {
        this.stock_code = stock_code;
    }

    public String getOption_code() {
        return option_code;
    }

    public void setOption_code(String option_code) {
        this.option_code = option_code;
    }

    public String getOptcontract_id() {
        return optcontract_id;
    }

    public void setOptcontract_id(String optcontract_id) {
        this.optcontract_id = optcontract_id;
    }

    public String getExercise_price() {
        return exercise_price;
    }

    public void setExercise_price(String exercise_price) {
        this.exercise_price = exercise_price;
    }

    public String getMin_price() {
        return min_price;
    }

    public void setMin_price(String min_price) {
        this.min_price = min_price;
    }

    public String getMax_price() {
        return max_price;
    }

    public void setMax_price(String max_price) {
        this.max_price = max_price;
    }

    public String getOpt_price_step() {
        return opt_price_step;
    }

    public void setOpt_price_step(String opt_price_step) {
        this.opt_price_step = opt_price_step;
    }

    public String getLastprice() {
        return lastprice;
    }

    public void setLastprice(String lastprice) {
        this.lastprice = lastprice;
    }

    public String getEnable_amount() {
        return enable_amount;
    }

    public void setEnable_amount(String enable_amount) {
        this.enable_amount = enable_amount;
    }

    public String getStock_account() {
        return stock_account;
    }

    public void setStock_account(String stock_account) {
        this.stock_account = stock_account;
    }

    public String getOption_name() {
        return option_name;
    }

    public void setOption_name(String option_name) {
        this.option_name = option_name;
    }

    public String getExchange_type() {
        return exchange_type;
    }

    public void setExchange_type(String exchange_type) {
        this.exchange_type = exchange_type;
    }

    public OptionInfoBean() {
    }

    public OptionInfoBean(String exercise_month, String stock_name, String stock_code, String option_code, String optcontract_id, String exercise_price, String min_price, String max_price, String opt_price_step, String lastprice, String enable_amount, String stock_account, String option_name, String exchange_type) {
        this.exercise_month = exercise_month;
        this.stock_name = stock_name;
        this.stock_code = stock_code;
        this.option_code = option_code;
        this.optcontract_id = optcontract_id;
        this.exercise_price = exercise_price;
        this.min_price = min_price;
        this.max_price = max_price;
        this.opt_price_step = opt_price_step;
        this.lastprice = lastprice;
        this.enable_amount = enable_amount;
        this.stock_account = stock_account;
        this.option_name = option_name;
        this.exchange_type = exchange_type;
    }

    public OptionInfoBean(Parcel in) {
        this.exercise_month = in.readString();
        this.stock_name = in.readString();
        this.stock_code = in.readString();
        this.option_code = in.readString();
        this.optcontract_id = in.readString();
        this.exercise_price = in.readString();
        this.min_price = in.readString();
        this.max_price = in.readString();
        this.opt_price_step = in.readString();
        this.lastprice = in.readString();
        this.enable_amount = in.readString();
        this.stock_account = in.readString();
        this.option_name = in.readString();
        this.exchange_type = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.exercise_month);
        dest.writeString(this.stock_name);
        dest.writeString(this.stock_code);
        dest.writeString(this.option_code);
        dest.writeString(this.optcontract_id);
        dest.writeString(this.exercise_price);
        dest.writeString(this.min_price);
        dest.writeString(this.max_price);
        dest.writeString(this.opt_price_step);
        dest.writeString(this.lastprice);
        dest.writeString(this.enable_amount);
        dest.writeString(this.stock_account);
        dest.writeString(this.option_name);
        dest.writeString(this.exchange_type);
    }

    public static final Creator<OptionInfoBean> CREATOR = new Creator<OptionInfoBean>() {
        @Override
        public OptionInfoBean createFromParcel(Parcel parcel) {
            return new OptionInfoBean(parcel);
        }

        @Override
        public OptionInfoBean[] newArray(int i) {
            return new OptionInfoBean[i];
        }
    };
}
