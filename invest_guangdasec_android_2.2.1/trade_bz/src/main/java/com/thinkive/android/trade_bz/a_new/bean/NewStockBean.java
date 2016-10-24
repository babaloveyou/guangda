package com.thinkive.android.trade_bz.a_new.bean;


import com.thinkive.android.trade_bz.a_stock.bean.BaseBean;
import com.thinkive.android.trade_bz.others.JsonKey;

/**
 * 新股实体类
 * Announcements：
 * @author 王志鸿
 * @company Thinkive
 * @date 2015/8/27
 */
public class NewStockBean extends BaseBean {
    @JsonKey("market")
    private String market = ""; //交易市场
    @JsonKey("stock_name")
    private String stock_name = ""; //股票名称
    @JsonKey("stock_code")
    private String stock_code = ""; //股票代码
    @JsonKey("subscribe_code")
    private String subscribe_code = ""; //申购代码
    @JsonKey("subscribe_date")
    private String subscribe_date = ""; //申购日期
    @JsonKey("applymax_online")
    private String applymax_online = ""; //申购数量上限
    @JsonKey("ballot_date")
    private String ballot_date = ""; //中签号公布日
    @JsonKey("ballot_rate")
    private String ballot_rate = ""; //中签率
    @JsonKey("list_date")
    private String list_date = ""; //上市日期
    @JsonKey("issue_price")
    private String issue_price = ""; //发行价
    @JsonKey("dilutedperatio")
    private String dilutedperatio = ""; //发行市盈率
    @JsonKey("issuevol")
    private String issuevol = ""; //发行总数
    @JsonKey("shares_online")
    private String shares_online = ""; //网上发行
    @JsonKey("applyunitonline")
    private String applyunitonline = ""; //上网发行认购/申购单位（股）
    @JsonKey("exchange_type")
    private String exchange_type = ""; //交易市场类别
    @JsonKey("stock_account")
    private String stock_account = "";  //股东账号

    public NewStockBean() {

    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public String getStock_name() {
        return stock_name;
    }

    public void setStock_name(String stock_name) {
        this.stock_name = stock_name;
    }
//
//    public String getSubscribe_code() {
//        return subscribe_code;
//    }
//
//    public void setSubscribe_code(String subscribe_code) {
//        this.subscribe_code = subscribe_code;
//    }

    public String getSubscribe_date() {
        return subscribe_date;
    }

    public void setSubscribe_date(String subscribe_date) {
        this.subscribe_date = subscribe_date;
    }

    public String getApplymax_online() {
        return applymax_online;
    }

    public void setApplymax_online(String applymax_online) {
        this.applymax_online = applymax_online;
    }

    public String getBallot_date() {
        return ballot_date;
    }

    public void setBallot_date(String ballot_date) {
        this.ballot_date = ballot_date;
    }

    public String getBallot_rate() {
        return ballot_rate;
    }

    public void setBallot_rate(String ballot_rate) {
        this.ballot_rate = ballot_rate;
    }

    public String getList_date() {
        return list_date;
    }

    public void setList_date(String list_date) {
        this.list_date = list_date;
    }

    public String getIssue_price() {
        return issue_price;
    }

    public void setIssue_price(String issue_price) {
        this.issue_price = issue_price;
    }

    public String getDilutedperatio() {
        return dilutedperatio;
    }

    public void setDilutedperatio(String dilutedperatio) {
        this.dilutedperatio = dilutedperatio;
    }

    public String getIssuevol() {
        return issuevol;
    }

    public void setIssuevol(String issuevol) {
        this.issuevol = issuevol;
    }

    public String getShares_online() {
        return shares_online;
    }

    public void setShares_online(String shares_online) {
        this.shares_online = shares_online;
    }

    public String getApplyunitonline() {
        return applyunitonline;
    }

    public void setApplyunitonline(String applyunitonline) {
        this.applyunitonline = applyunitonline;
    }

    public String getStock_code() {
        return stock_code;
    }

    public void setStock_code(String stock_code) {
        this.stock_code = stock_code;
    }

    public String getSubscribe_code() {
        return subscribe_code;
    }

    public void setSubscribe_code(String subscribe_code) {
        this.subscribe_code = subscribe_code;
    }

    public String getExchange_type() {
        return exchange_type;
    }

    public void setExchange_type(String exchange_type) {
        this.exchange_type = exchange_type;
    }

    public String getStock_account() {
        return stock_account;
    }

    public void setStock_account(String stock_account) {
        this.stock_account = stock_account;
    }
}
