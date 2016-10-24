package com.thinkive.android.trade_bz.a_out.bean;

import com.thinkive.android.trade_bz.a_stock.bean.BaseBean;
import com.thinkive.android.trade_bz.others.JsonKey;

/**
 *  基金交易--查询--基金账户查询
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/8/7
 */

public class FundAccountBean extends BaseBean {
    /**
     * 基金公司代码
     */
    @JsonKey("fund_company")
    private String fund_company="";
    /**
     *基金公司名称
     */
    @JsonKey("company_name")
    private String company_name="";
    /**
     *基金账户
     */
    @JsonKey("fund_account")
    private String fund_account="";
    /**
     *  交易账号
     */
    @JsonKey("trans_account")
    private String trans_account="";
    /**
     * 客户类型
     */
    @JsonKey("client_type")
    private String client_type="";
    /**
     * 帐户状态
     */
    @JsonKey("holder_status")
    private String holder_status="";
    /**
     *账户姓名
     */
    @JsonKey("holder_name")
    private String holder_name="";
    /**
     *开户日期
     */
    @JsonKey("open_date")
    private String open_date="";
    /**
     *证件类型
     */
    @JsonKey("id_kind")
    private String id_kind="";
    /**
     *证件号码
     */
    @JsonKey("id_no")
    private String id_no="";

    public FundAccountBean() {
    }

    public String getFund_company() {
        return fund_company;
    }

    public void setFund_company(String fund_company) {
        this.fund_company = fund_company;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getFund_account() {
        return fund_account;
    }

    public void setFund_account(String fund_account) {
        this.fund_account = fund_account;
    }

    public String getTrans_account() {
        return trans_account;
    }

    public void setTrans_account(String trans_account) {
        this.trans_account = trans_account;
    }

    public String getClient_type() {
        return client_type;
    }

    public void setClient_type(String client_type) {
        this.client_type = client_type;
    }

    public String getHolder_status() {
        return holder_status;
    }

    public void setHolder_status(String holder_status) {
        this.holder_status = holder_status;
    }

    public String getHolder_name() {
        return holder_name;
    }

    public void setHolder_name(String holder_name) {
        this.holder_name = holder_name;
    }

    public String getOpen_date() {
        return open_date;
    }

    public void setOpen_date(String open_date) {
        this.open_date = open_date;
    }

    public String getId_kind() {
        return id_kind;
    }

    public void setId_kind(String id_kind) {
        this.id_kind = id_kind;
    }

    public String getId_no() {
        return id_no;
    }

    public void setId_no(String id_no) {
        this.id_no = id_no;
    }
}
