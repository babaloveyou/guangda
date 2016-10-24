package com.thinkive.android.trade_bz.a_option.bean;

import com.thinkive.android.trade_bz.a_stock.bean.BaseBean;
import com.thinkive.android.trade_bz.others.JsonKey;

/**
 * 个股期权历史风险通知查询
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/7/28
 */
public class OptionHistoryRiskBean extends BaseBean {
    @JsonKey("notice_sn")
    private String notice_sn = ""; //通知序号
    @JsonKey("risk_id")
    private String risk_id = ""; //风险标识
    @JsonKey("create_date")
    private String create_date = ""; //创建日期
    @JsonKey("create_time")
    private String create_time = ""; //创建时间
    @JsonKey("content")
    private String content = ""; //通知内容
    @JsonKey("channel")
    private String channel = ""; //通知渠道
    @JsonKey("send_type")
    private String send_type = ""; //发送类型
    @JsonKey("send_date")
    private String send_date = ""; //发送日期
    @JsonKey("send_time")
    private String send_time = ""; //发送时间
    @JsonKey("resp_flag")
    private String resp_flag = ""; //反馈标志

    public OptionHistoryRiskBean() {

    }

    public String getNotice_sn() {
        return notice_sn;
    }

    public void setNotice_sn(String notice_sn) {
        this.notice_sn = notice_sn;
    }

    public String getRisk_id() {
        return risk_id;
    }

    public void setRisk_id(String risk_id) {
        this.risk_id = risk_id;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getSend_type() {
        return send_type;
    }

    public void setSend_type(String send_type) {
        this.send_type = send_type;
    }

    public String getSend_date() {
        return send_date;
    }

    public void setSend_date(String send_date) {
        this.send_date = send_date;
    }

    public String getSend_time() {
        return send_time;
    }

    public void setSend_time(String send_time) {
        this.send_time = send_time;
    }

    public String getResp_flag() {
        return resp_flag;
    }

    public void setResp_flag(String resp_flag) {
        this.resp_flag = resp_flag;
    }
}