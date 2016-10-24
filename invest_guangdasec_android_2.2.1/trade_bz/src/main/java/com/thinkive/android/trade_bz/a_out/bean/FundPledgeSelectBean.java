package com.thinkive.android.trade_bz.a_out.bean;

import com.thinkive.android.trade_bz.a_stock.bean.BaseBean;
import com.thinkive.android.trade_bz.others.JsonKey;

/**
 *  场外基金定投查询  （203051）
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/10/27.
 */

public class FundPledgeSelectBean extends BaseBean {
    /**
     *发生日期
     */
    @JsonKey("init_date")
    private String init_date="";
    /**
     * 委托编号
     */
    @JsonKey("entrust_no")
    private String entrust_no="";
    /**
     *申请编号
     */
    @JsonKey("allotno")
    private String allotno="";
    /**
     * 交易日期
     */
    @JsonKey("allot_date")
    private String allot_date="";
    /**
     * 证券账号
     */
    @JsonKey("stock_account")
    private String stock_account="";
    /**
     * 交易账号
     */
    @JsonKey("trans_account")
    private String trans_account="";
    /**
     * 基金公司
     */
    @JsonKey("fund_company")
    private String fund_company="";
    /**
     *交易名称
     */
    @JsonKey("exchange_name")
    private String exchange_name="";
    /**
     * 基金代码
     */
    @JsonKey("fund_code")
    private String fund_code="";
    /**
     * 基金名称
     */
    @JsonKey("fund_name")
    private String fund_name="";
    /**
     *金额
     */
    @JsonKey("balance")
    private String balance="";
    /**
     *共申购金额
     */
    @JsonKey("send_balance")
    private String send_balance="";
    /**
     *扣款允许日
     */
    @JsonKey("en_fund_date")
    private String en_fund_date="";
    /**
     *开始日期
     */
    @JsonKey("start_date")
    private String start_date="";
    /**
     * 到期日期
     */
    @JsonKey("end_date")
    private String end_date="";
    /**
     * 失败次数
     */
    @JsonKey("fail_time")
    private String fail_time="";
    /**
     *协议状态（见数据字典）
     */
    @JsonKey("protocol_status")
    private String protocol_status="";
    /**
     * 协议状态名称
     */
    @JsonKey("protocol_status_name")
    private String protocol_status_name="";
    /**
     *下次扣款日期
     */
    @JsonKey("next_pay_date")
    private String next_pay_date="";

    public FundPledgeSelectBean() {

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

    public String getAllotno() {
        return allotno;
    }

    public void setAllotno(String allotno) {
        this.allotno = allotno;
    }

    public String getAllot_date() {
        return allot_date;
    }

    public void setAllot_date(String allot_date) {
        this.allot_date = allot_date;
    }

    public String getStock_account() {
        return stock_account;
    }

    public void setStock_account(String stock_account) {
        this.stock_account = stock_account;
    }

    public String getTrans_account() {
        return trans_account;
    }

    public void setTrans_account(String trans_account) {
        this.trans_account = trans_account;
    }

    public String getFund_company() {
        return fund_company;
    }

    public void setFund_company(String fund_company) {
        this.fund_company = fund_company;
    }

    public String getExchange_name() {
        return exchange_name;
    }

    public void setExchange_name(String exchange_name) {
        this.exchange_name = exchange_name;
    }

    public String getFund_code() {
        return fund_code;
    }

    public void setFund_code(String fund_code) {
        this.fund_code = fund_code;
    }

    public String getFund_name() {
        return fund_name;
    }

    public void setFund_name(String fund_name) {
        this.fund_name = fund_name;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getSend_balance() {
        return send_balance;
    }

    public void setSend_balance(String send_balance) {
        this.send_balance = send_balance;
    }

    public String getEn_fund_date() {
        return en_fund_date;
    }

    public void setEn_fund_date(String en_fund_date) {
        this.en_fund_date = en_fund_date;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getFail_time() {
        return fail_time;
    }

    public void setFail_time(String fail_time) {
        this.fail_time = fail_time;
    }

    public String getProtocol_status() {
        return protocol_status;
    }

    public void setProtocol_status(String protocol_status) {
        this.protocol_status = protocol_status;
    }

    public String getProtocol_status_name() {
        return protocol_status_name;
    }

    public void setProtocol_status_name(String protocol_status_name) {
        this.protocol_status_name = protocol_status_name;
    }

    public String getNext_pay_date() {
        return next_pay_date;
    }

    public void setNext_pay_date(String next_pay_date) {
        this.next_pay_date = next_pay_date;
    }
}
