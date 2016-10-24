package com.thinkive.android.trade_bz.a_option.bean;

import com.thinkive.android.trade_bz.a_stock.bean.BaseBean;
import com.thinkive.android.trade_bz.others.JsonKey;

/**
 * 撤单结果（305006）
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/6/14
 */
public class OptionRevocationResultBean extends BaseBean {
    @JsonKey("init_date")
    private String init_date = ""; //交易日期
    @JsonKey("entrust_no")
    private String entrust_no = ""; //委托编号
    @JsonKey("entrust_no_old")
    private String entrust_no_old = ""; //原委托编号
    @JsonKey("report_no_old")
    private String report_no_old = ""; //原申请编号
    @JsonKey("exchange_type")
    private String exchange_type = ""; //原委托交易类别
    @JsonKey("option_account")
    private String option_account = ""; //原委托衍生品合约账号
    @JsonKey("stock_code")
    private String stock_code = ""; //原委托标的证券代码
    @JsonKey("option_code")
    private String option_code = ""; //原委托期权合约编码
    @JsonKey("entrust_status_old")
    private String entrust_status_old = ""; //原委托委托状态
    @JsonKey("entrust_state_name")
    private String entrust_state_name = ""; //委托状态名称

    public OptionRevocationResultBean() {

    }

    public String getInit_date() {
        return init_date;
    }

    public void setInit_date(String init_date) {
        this.init_date = init_date;
    }

    public String getEntrust_no() {
        return entrust_no;
    }

    public void setEntrust_no(String entrust_no) {
        this.entrust_no = entrust_no;
    }

    public String getEntrust_no_old() {
        return entrust_no_old;
    }

    public void setEntrust_no_old(String entrust_no_old) {
        this.entrust_no_old = entrust_no_old;
    }

    public String getReport_no_old() {
        return report_no_old;
    }

    public void setReport_no_old(String report_no_old) {
        this.report_no_old = report_no_old;
    }

    public String getExchange_type() {
        return exchange_type;
    }

    public void setExchange_type(String exchange_type) {
        this.exchange_type = exchange_type;
    }

    public String getOption_account() {
        return option_account;
    }

    public void setOption_account(String option_account) {
        this.option_account = option_account;
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

    public String getEntrust_status_old() {
        return entrust_status_old;
    }

    public void setEntrust_status_old(String entrust_status_old) {
        this.entrust_status_old = entrust_status_old;
    }

    public String getEntrust_state_name() {
        return entrust_state_name;
    }

    public void setEntrust_state_name(String entrust_state_name) {
        this.entrust_state_name = entrust_state_name;
    }
}
