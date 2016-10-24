package com.thinkive.android.trade_bz.a_option.bean;

import android.os.Parcel;

import com.thinkive.android.trade_bz.a_stock.bean.BaseBean;
import com.thinkive.android.trade_bz.others.JsonKey;

/**
 * 个股期权合约查询（305022）
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/6/16
 */
public class OptionContractBean extends BaseBean {
    @JsonKey("exchange_type")
    private String exchange_type = ""; // 交易类别
    @JsonKey("exchange_type_name")
    private String exchange_type_name = ""; //交易类别名称
    @JsonKey("option_code")
    private String option_code = ""; // 期权合约编码
    @JsonKey("optcontract_id")
    private String optcontract_id = ""; //合约交易代码
    @JsonKey("opt_up_price")
    private String opt_up_price = ""; //期权涨停价
    @JsonKey("opt_down_price")
    private String opt_down_price = ""; //期权跌停价
    @JsonKey("option_name")
    private String option_name = ""; //期权名称
    @JsonKey("option_type")
    private String option_type = ""; //期权类别（'C'-认购 'P'-认沽）
    @JsonKey("option_type_name")
    private String option_type_name = ""; //期权类别名称
    @JsonKey("stock_code")
    private String stock_code = ""; // 标的证券代码
    @JsonKey("amount_per_hand")
    private String amount_per_hand = ""; // 合约单位
    @JsonKey("option_mode")
    private String option_mode = ""; //期权模式（'E'-欧式 'A'-美式）
    @JsonKey("option_mode_name")
    private String option_mode_name = ""; //期权模式名称
    @JsonKey("opt_close_price")
    private String opt_close_price = ""; //期权昨收盘价
    @JsonKey("close_price")
    private String close_price = ""; //标的昨收盘价
    @JsonKey("exercise_price")
    private String exercise_price = ""; //行权价格
    @JsonKey("initper_balance")
    private String initper_balance = ""; // 单位保证金
    @JsonKey("limit_high_amount")
    private String limit_high_amount = ""; //单笔限价申报最高数量
    @JsonKey("limit_low_amount")
    private String limit_low_amount = ""; //单笔限价申报最低数量
    @JsonKey("mkt_high_amount")
    private String mkt_high_amount = ""; // 单笔市价申报最高数量
    @JsonKey("mkt_low_amount")
    private String mkt_low_amount = ""; //单笔市价申报最低数量
    @JsonKey("begin_date")
    private String begin_date = ""; //交易开始日期
    @JsonKey("end_date")
    private String end_date = ""; //交易截止日期
    @JsonKey("exe_begin_date")
    private String exe_begin_date = ""; //行权开始日期
    @JsonKey("exe_end_date")
    private String exe_end_date = ""; //行权截止日期
    @JsonKey("optcode_status")
    private String optcode_status = ""; //期权代码状态（'0'-正常 '1'-停牌）
    @JsonKey("optcode_status_name")
    private String optcode_status_name = ""; //期权代码状态名称
    @JsonKey("opt_updated_status_name")
    private String opt_updated_status_name = ""; //期权合约调整标志名称
    @JsonKey("opt_updated_status")
    private String opt_updated_status = ""; // 期权合约调整标志（'0'-未调整 '1'-已调整）

    public OptionContractBean() {

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

    public String getOpt_up_price() {
        return opt_up_price;
    }

    public void setOpt_up_price(String opt_up_price) {
        this.opt_up_price = opt_up_price;
    }

    public String getOpt_down_price() {
        return opt_down_price;
    }

    public void setOpt_down_price(String opt_down_price) {
        this.opt_down_price = opt_down_price;
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

    public String getStock_code() {
        return stock_code;
    }

    public void setStock_code(String stock_code) {
        this.stock_code = stock_code;
    }

    public String getAmount_per_hand() {
        return amount_per_hand;
    }

    public void setAmount_per_hand(String amount_per_hand) {
        this.amount_per_hand = amount_per_hand;
    }

    public String getOption_mode() {
        return option_mode;
    }

    public void setOption_mode(String option_mode) {
        this.option_mode = option_mode;
    }

    public String getOption_mode_name() {
        return option_mode_name;
    }

    public void setOption_mode_name(String option_mode_name) {
        this.option_mode_name = option_mode_name;
    }

    public String getOpt_close_price() {
        return opt_close_price;
    }

    public void setOpt_close_price(String opt_close_price) {
        this.opt_close_price = opt_close_price;
    }

    public String getClose_price() {
        return close_price;
    }

    public void setClose_price(String close_price) {
        this.close_price = close_price;
    }

    public String getExercise_price() {
        return exercise_price;
    }

    public void setExercise_price(String exercise_price) {
        this.exercise_price = exercise_price;
    }

    public String getInitper_balance() {
        return initper_balance;
    }

    public void setInitper_balance(String initper_balance) {
        this.initper_balance = initper_balance;
    }

    public String getLimit_high_amount() {
        return limit_high_amount;
    }

    public void setLimit_high_amount(String limit_high_amount) {
        this.limit_high_amount = limit_high_amount;
    }

    public String getLimit_low_amount() {
        return limit_low_amount;
    }

    public void setLimit_low_amount(String limit_low_amount) {
        this.limit_low_amount = limit_low_amount;
    }

    public String getMkt_high_amount() {
        return mkt_high_amount;
    }

    public void setMkt_high_amount(String mkt_high_amount) {
        this.mkt_high_amount = mkt_high_amount;
    }

    public String getMkt_low_amount() {
        return mkt_low_amount;
    }

    public void setMkt_low_amount(String mkt_low_amount) {
        this.mkt_low_amount = mkt_low_amount;
    }

    public String getBegin_date() {
        return begin_date;
    }

    public void setBegin_date(String begin_date) {
        this.begin_date = begin_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getExe_begin_date() {
        return exe_begin_date;
    }

    public void setExe_begin_date(String exe_begin_date) {
        this.exe_begin_date = exe_begin_date;
    }

    public String getExe_end_date() {
        return exe_end_date;
    }

    public void setExe_end_date(String exe_end_date) {
        this.exe_end_date = exe_end_date;
    }

    public String getOptcode_status() {
        return optcode_status;
    }

    public void setOptcode_status(String optcode_status) {
        this.optcode_status = optcode_status;
    }

    public String getOptcode_status_name() {
        return optcode_status_name;
    }

    public void setOptcode_status_name(String optcode_status_name) {
        this.optcode_status_name = optcode_status_name;
    }

    public String getOpt_updated_status_name() {
        return opt_updated_status_name;
    }

    public void setOpt_updated_status_name(String opt_updated_status_name) {
        this.opt_updated_status_name = opt_updated_status_name;
    }

    public String getOpt_updated_status() {
        return opt_updated_status;
    }

    public void setOpt_updated_status(String opt_updated_status) {
        this.opt_updated_status = opt_updated_status;
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
        dest.writeString(this.option_code);
        dest.writeString(this.optcontract_id);
        dest.writeString(this.opt_up_price);
        dest.writeString(this.opt_down_price);
        dest.writeString(this.option_name);
        dest.writeString(this.option_type);
        dest.writeString(this.option_type_name);
        dest.writeString(this.stock_code);
        dest.writeString(this.amount_per_hand);
        dest.writeString(this.option_mode);
        dest.writeString(this.option_mode_name);
        dest.writeString(this.opt_close_price);
        dest.writeString(this.close_price);
        dest.writeString(this.exercise_price);
        dest.writeString(this.initper_balance);
        dest.writeString(this.limit_high_amount);
        dest.writeString(this.limit_low_amount);
        dest.writeString(this.mkt_high_amount);
        dest.writeString(this.mkt_low_amount);
        dest.writeString(this.begin_date);
        dest.writeString(this.end_date);
        dest.writeString(this.exe_begin_date);
        dest.writeString(this.exe_end_date);
        dest.writeString(this.optcode_status);
        dest.writeString(this.optcode_status_name);
        dest.writeString(this.opt_updated_status_name);
        dest.writeString(this.opt_updated_status);
    }

    protected OptionContractBean(Parcel in) {
        this.exchange_type = in.readString();
        this.exchange_type_name = in.readString();
        this.option_code = in.readString();
        this.optcontract_id = in.readString();
        this.opt_up_price = in.readString();
        this.opt_down_price = in.readString();
        this.option_name = in.readString();
        this.option_type = in.readString();
        this.option_type_name = in.readString();
        this.stock_code = in.readString();
        this.amount_per_hand = in.readString();
        this.option_mode = in.readString();
        this.option_mode_name = in.readString();
        this.opt_close_price = in.readString();
        this.close_price = in.readString();
        this.exercise_price = in.readString();
        this.initper_balance = in.readString();
        this.limit_high_amount = in.readString();
        this.limit_low_amount = in.readString();
        this.mkt_high_amount = in.readString();
        this.mkt_low_amount = in.readString();
        this.begin_date = in.readString();
        this.end_date = in.readString();
        this.exe_begin_date = in.readString();
        this.exe_end_date = in.readString();
        this.optcode_status = in.readString();
        this.optcode_status_name = in.readString();
        this.opt_updated_status_name = in.readString();
        this.opt_updated_status = in.readString();
    }

    public static final Creator<OptionContractBean> CREATOR = new Creator<OptionContractBean>() {
        @Override
        public OptionContractBean createFromParcel(Parcel source) {
            return new OptionContractBean(source);
        }

        @Override
        public OptionContractBean[] newArray(int size) {
            return new OptionContractBean[size];
        }
    };
}
