package com.thinkive.android.trade_bz.a_net.bean;

import com.thinkive.android.trade_bz.a_stock.bean.BaseBean;
import com.thinkive.android.trade_bz.others.JsonKey;

/**
 * 网络投票查询(投票委托查询 301534)
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/8/2
 */
public class NetVoteEntrustSelectBean extends BaseBean {

    @JsonKey("order_sno")
    private String order_sno = ""; //委托序号
    @JsonKey("order_group")
    private String order_group = ""; //批量委托批号
    @JsonKey("order_id")
    private String order_id = ""; //申报合同序号
    @JsonKey("order_date")
    private String order_date = ""; //委托交易日期
    @JsonKey("cust_code")
    private String cust_code = ""; //客户代码
    @JsonKey("cust_name")
    private String cust_name = ""; //客户姓名
    @JsonKey("orgid")
    private String orgid = ""; //分支机构
    @JsonKey("brhid")
    private String brhid = ""; //机构分支
    @JsonKey("fund_account")
    private String fund_account = ""; //资产帐户
    @JsonKey("money_type")
    private String money_type = ""; //货币代码
    @JsonKey("stock_account")
    private String stock_account = ""; //股东代码
    @JsonKey("exchange_type")
    private String exchange_type = ""; //交易市场
    @JsonKey("seat")
    private String seat = ""; //报盘席位
    @JsonKey("channel")
    private String channel = ""; //通道号
    @JsonKey("stock_code")
    private String stock_code = ""; //证券代码
    @JsonKey("stock_name")
    private String stock_name = ""; //证券名称
    @JsonKey("stock_type")
    private String stock_type = ""; //证券类别
    @JsonKey("bsflag")
    private String bsflag = ""; //买卖方向
    @JsonKey("meeting_seq")
    private String meeting_seq = ""; //股东大会编码
    @JsonKey("v_id")
    private String v_id = ""; //投票议案编号
    @JsonKey("vote_result")
    private String vote_result = ""; //投票意见
    @JsonKey("vote_qty")
    private String vote_qty = ""; //投票数量
    @JsonKey("segment")
    private String segment = ""; //分段统计段号
    @JsonKey("long_qty")
    private String long_qty = ""; //总股份数量
    @JsonKey("report_time")
    private String report_time = ""; //申报时间
    @JsonKey("order_status_name")
    private String order_status_name = ""; //委托状态名称
    @JsonKey("order_status")
    private String order_status = ""; //委托状态
    @JsonKey("send_status")
    private String send_status = ""; //发送状态
    @JsonKey("remark")
    private String remark = ""; //其他备注
    @JsonKey("vote_type")
    private String vote_type = ""; //议案类型

    public NetVoteEntrustSelectBean() {

    }

    public String getOrder_sno() {
        return order_sno;
    }

    public void setOrder_sno(String order_sno) {
        this.order_sno = order_sno;
    }

    public String getOrder_group() {
        return order_group;
    }

    public void setOrder_group(String order_group) {
        this.order_group = order_group;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public String getCust_code() {
        return cust_code;
    }

    public void setCust_code(String cust_code) {
        this.cust_code = cust_code;
    }

    public String getCust_name() {
        return cust_name;
    }

    public void setCust_name(String cust_name) {
        this.cust_name = cust_name;
    }

    public String getOrgid() {
        return orgid;
    }

    public void setOrgid(String orgid) {
        this.orgid = orgid;
    }

    public String getBrhid() {
        return brhid;
    }

    public void setBrhid(String brhid) {
        this.brhid = brhid;
    }

    public String getFund_account() {
        return fund_account;
    }

    public void setFund_account(String fund_account) {
        this.fund_account = fund_account;
    }

    public String getMoney_type() {
        return money_type;
    }

    public void setMoney_type(String money_type) {
        this.money_type = money_type;
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

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getStock_code() {
        return stock_code;
    }

    public void setStock_code(String stock_code) {
        this.stock_code = stock_code;
    }

    public String getStock_name() {
        return stock_name;
    }

    public void setStock_name(String stock_name) {
        this.stock_name = stock_name;
    }

    public String getStock_type() {
        return stock_type;
    }

    public void setStock_type(String stock_type) {
        this.stock_type = stock_type;
    }

    public String getBsflag() {
        return bsflag;
    }

    public void setBsflag(String bsflag) {
        this.bsflag = bsflag;
    }

    public String getMeeting_seq() {
        return meeting_seq;
    }

    public void setMeeting_seq(String meeting_seq) {
        this.meeting_seq = meeting_seq;
    }

    public String getV_id() {
        return v_id;
    }

    public void setV_id(String v_id) {
        this.v_id = v_id;
    }

    public String getVote_result() {
        return vote_result;
    }

    public void setVote_result(String vote_result) {
        this.vote_result = vote_result;
    }

    public String getVote_qty() {
        return vote_qty;
    }

    public void setVote_qty(String vote_qty) {
        this.vote_qty = vote_qty;
    }

    public String getSegment() {
        return segment;
    }

    public void setSegment(String segment) {
        this.segment = segment;
    }

    public String getLong_qty() {
        return long_qty;
    }

    public void setLong_qty(String long_qty) {
        this.long_qty = long_qty;
    }

    public String getReport_time() {
        return report_time;
    }

    public void setReport_time(String report_time) {
        this.report_time = report_time;
    }

    public String getOrder_status_name() {
        return order_status_name;
    }

    public void setOrder_status_name(String order_status_name) {
        this.order_status_name = order_status_name;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public String getSend_status() {
        return send_status;
    }

    public void setSend_status(String send_status) {
        this.send_status = send_status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getVote_type() {
        return vote_type;
    }

    public void setVote_type(String vote_type) {
        this.vote_type = vote_type;
    }
}
