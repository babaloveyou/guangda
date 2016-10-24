package com.thinkive.android.trade_bz.a_stock.bean;

import com.thinkive.android.trade_bz.others.JsonKey;

/**
 * 普通资金用户信息实体类，
 * Announcements：以后可能会有拓展
 * @author 王志鸿
 * @company Thinkive
 * @date 2015/6/16
 */
public class UserInfo {
    @Override
    public String toString() {
        return "UserInfo{" +
                "entrust_way='" + entrust_way + '\'' +
                ", branch_no='" + branch_no + '\'' +
                ", fund_account='" + fund_account + '\'' +
                ", cust_code='" + cust_code + '\'' +
                ", op_station='" + op_station + '\'' +
                ", jsessionid='" + jsessionid + '\'' +
                ", stock_account='" + stock_account + '\'' +
                ", exchange_type='" + exchange_type + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    /**
     * 委托方式（见数据字典)
     */
    @JsonKey("entrust_way")
    private String entrust_way = "";
    /**
     * 分支机构
     */
    @JsonKey("branch_no")
    private String branch_no = "";
    /**
     * 资金账号
     */
    @JsonKey("fund_account")
    private String fund_account = "";
    /**
     * 客户编号
     */
    @JsonKey("cust_code")
    private String cust_code = "";
    /**
     * 操作站点（见数据字典)
     */
    @JsonKey("op_station")
    private String op_station = "";
    /**
     * 会话号
     */
    @JsonKey("jsessionid")
    private String jsessionid = "";
    /**
     * 股东账号，分深A和沪A两种
     */
    @JsonKey("stock_account")
    private String stock_account = "";
    /**
     * 交易市场类别，
     * 数据字典：0：深A，1：深B，2：沪A，3：沪B，4：三板，
     * 9：特转A，A：特转B，F1：郑州交易所，F2：大连交易所，
     * F3：上海交易所，F4：金融交易所，G：港股
     */
    @JsonKey("exchange_type")
    private String exchange_type = "";
    /**
     * 资金账号密码
     */
    @JsonKey("password")
    private String password = "";

    public String getBranch_no() {
        return branch_no;
    }

    public void setBranch_no(String branch_no) {
        this.branch_no = branch_no;
    }

    public String getFund_account() {
        return fund_account;
    }

    public void setFund_account(String fund_account) {
        this.fund_account = fund_account;
    }

    public String getEntrust_way() {
        return entrust_way;
    }

    public void setEntrust_way(String entrust_way) {
        this.entrust_way = entrust_way;
    }

    public String getCust_code() {
        return cust_code;
    }

    public void setCust_code(String cust_code) {
        this.cust_code = cust_code;
    }

    public String getOp_station() {
        return op_station;
    }

    public void setOp_station(String op_station) {
        this.op_station = op_station;
    }

    public String getJsessionid() {
        return jsessionid;
    }

    public void setJsessionid(String jsessionid) {
        this.jsessionid = jsessionid;
    }

    public String getStock_account() {
        return stock_account;
    }

    public void setStock_account(String stock_account) {
        this.stock_account = stock_account;
    }

    public String getExchange_type() {
        return exchange_type;
    }

    public void setExchange_type(String exchange_type) {
        this.exchange_type = exchange_type;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}