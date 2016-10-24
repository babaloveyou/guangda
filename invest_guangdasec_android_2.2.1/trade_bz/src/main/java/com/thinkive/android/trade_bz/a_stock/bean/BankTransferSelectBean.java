package com.thinkive.android.trade_bz.a_stock.bean;

import com.thinkive.android.trade_bz.others.JsonKey;

/**
 *  银证转账 --流水--转账查询(融资融券账号同用)
 *  300203/303040
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/8/10
 */

public class BankTransferSelectBean extends BaseBean {
    /**
     * 委托编号
     */
    @JsonKey("entrust_no")
    private String entrust_no="";
    /**
     *货币类型
     */
    @JsonKey("money_type")
    private String money_type="";
    /**
     *币种名称
     */
    @JsonKey("money_type_name")
    private String money_type_name="";
    /**
     * 发生日期
     */
    @JsonKey("business_date")
    private String business_date="";
    /**
     * 发生时间
     */
    @JsonKey("business_time")
    private String business_time="";
    /**
     * 发生金额
     */
    @JsonKey("tranamt")
    private String tranamt="";
    /**
     *银行帐户
     */
    @JsonKey("bank_account")
    private String bank_account="";
    /**
     * 银行代码
     */
    @JsonKey("bank_code")
    private String bank_code="";
    /**
     *银行名称
     */
    @JsonKey("bank_name")
    private String bank_name="";
    /**
     * 转账方向
     */
    @JsonKey("transfer_direction")
    private String transfer_direction="";
    /**
     * 业务名称
     */
    @JsonKey("transfer_direction_name")
    private String transfer_direction_name="";
    /**
     * 资金账户
     */
    @JsonKey("fund_account")
    private String fund_account="";
    /**
     * 银行错误信息
     */
    @JsonKey("bank_error_info")
    private String bank_error_info="";
    /**
     *  备注
     */
    @JsonKey("remark")
    private String remark="";
    /**
     *委托状态名称
     */
    @JsonKey("entrust_name")
    private String entrust_name="";

    public BankTransferSelectBean() {
    }

    public String getEntrust_no() {
        return entrust_no;
    }

    public void setEntrust_no(String entrust_no) {
        this.entrust_no = entrust_no;
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

    public String getBusiness_time() {
        return business_time;
    }

    public void setBusiness_time(String business_time) {
        this.business_time = business_time;
    }

    public String getBusiness_date() {
        return business_date;
    }

    public void setBusiness_date(String business_date) {
        this.business_date = business_date;
    }

    public String getTranamt() {
        return tranamt;
    }

    public void setTranamt(String tranamt) {
        this.tranamt = tranamt;
    }

    public String getBank_account() {
        return bank_account;
    }

    public void setBank_account(String bank_account) {
        this.bank_account = bank_account;
    }

    public String getBank_code() {
        return bank_code;
    }

    public void setBank_code(String bank_code) {
        this.bank_code = bank_code;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public String getTransfer_direction() {
        return transfer_direction;
    }

    public void setTransfer_direction(String transfer_direction) {
        this.transfer_direction = transfer_direction;
    }

    public String getTransfer_direction_name() {
        return transfer_direction_name;
    }

    public void setTransfer_direction_name(String transfer_direction_name) {
        this.transfer_direction_name = transfer_direction_name;
    }

    public String getFund_account() {
        return fund_account;
    }

    public void setFund_account(String fund_account) {
        this.fund_account = fund_account;
    }

    public String getBank_error_info() {
        return bank_error_info;
    }

    public void setBank_error_info(String bank_error_info) {
        this.bank_error_info = bank_error_info;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getEntrust_name() {
        return entrust_name;
    }

    public void setEntrust_name(String entrust_name) {
        this.entrust_name = entrust_name;
    }
}
