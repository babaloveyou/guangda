package com.thinkive.android.trade_bz.a_net.bean;


import com.thinkive.android.trade_bz.a_stock.bean.BaseBean;
import com.thinkive.android.trade_bz.others.JsonKey;

/**
 * 网络投票(股东大会信息查询 301530)
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/8/5
 */
public class NetVoteStockMsgBean extends BaseBean {
    @JsonKey("exchange_type")
    private String exchange_type = ""; //市场
    @JsonKey("company_code")
    private String company_code = ""; //公司代码
    @JsonKey("company_name")
    private String company_name = ""; //公司简称
    @JsonKey("meeting_begin")
    private String meeting_begin = ""; //股东大会开始日
    @JsonKey("meeting_end")
    private String meeting_end = ""; //股东大会结束日
    @JsonKey("meeting_type")
    private String meeting_type = ""; //股东大会类型
    @JsonKey("meeting_desc")
    private String meeting_desc = ""; //股东大会名称
    @JsonKey("meeting_seq")
    private String meeting_seq = ""; //股东大会编码
    @JsonKey("vote_code")
    private String vote_code = ""; //产品代码
    @JsonKey("stock_abbr")
    private String stock_abbr = ""; //股票简称
    @JsonKey("stock_type")
    private String stock_type = ""; //股份类别
    @JsonKey("reg_date")
    private String reg_date = ""; //股权登记日
    @JsonKey("ast_tradedate")
    private String ast_tradedate = ""; //最后交易日

    public NetVoteStockMsgBean() {

    }

    public String getExchange_type() {
        return exchange_type;
    }

    public void setExchange_type(String exchange_type) {
        this.exchange_type = exchange_type;
    }

    public String getCompany_code() {
        return company_code;
    }

    public void setCompany_code(String company_code) {
        this.company_code = company_code;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getMeeting_begin() {
        return meeting_begin;
    }

    public void setMeeting_begin(String meeting_begin) {
        this.meeting_begin = meeting_begin;
    }

    public String getMeeting_end() {
        return meeting_end;
    }

    public void setMeeting_end(String meeting_end) {
        this.meeting_end = meeting_end;
    }

    public String getMeeting_type() {
        return meeting_type;
    }

    public void setMeeting_type(String meeting_type) {
        this.meeting_type = meeting_type;
    }

    public String getMeeting_desc() {
        return meeting_desc;
    }

    public void setMeeting_desc(String meeting_desc) {
        this.meeting_desc = meeting_desc;
    }

    public String getMeeting_seq() {
        return meeting_seq;
    }

    public void setMeeting_seq(String meeting_seq) {
        this.meeting_seq = meeting_seq;
    }

    public String getVote_code() {
        return vote_code;
    }

    public void setVote_code(String vote_code) {
        this.vote_code = vote_code;
    }

    public String getStock_abbr() {
        return stock_abbr;
    }

    public void setStock_abbr(String stock_abbr) {
        this.stock_abbr = stock_abbr;
    }

    public String getStock_type() {
        return stock_type;
    }

    public void setStock_type(String stock_type) {
        this.stock_type = stock_type;
    }

    public String getReg_date() {
        return reg_date;
    }

    public void setReg_date(String reg_date) {
        this.reg_date = reg_date;
    }

    public String getAst_tradedate() {
        return ast_tradedate;
    }

    public void setAst_tradedate(String ast_tradedate) {
        this.ast_tradedate = ast_tradedate;
    }

}
