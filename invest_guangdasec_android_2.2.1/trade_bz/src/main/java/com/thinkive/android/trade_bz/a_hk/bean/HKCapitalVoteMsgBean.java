package com.thinkive.android.trade_bz.a_hk.bean;

import com.thinkive.android.trade_bz.a_stock.bean.BaseBean;
import com.thinkive.android.trade_bz.others.JsonKey;

/**
 * 港股通 投票信息查询
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/7/28
 */
public class HKCapitalVoteMsgBean extends BaseBean {
    @JsonKey("stock_code")
    private String stock_code = ""; //证券代码
    @JsonKey("stock_name")
    private String stock_name = ""; //证券名称
    @JsonKey("isin_code")
    private String isin_code = ""; //ISIN代码
    @JsonKey("placard_id")
    private String placard_id = ""; //公告编号
    @JsonKey("motion_id")
    private String motion_id = ""; //议案编号
    @JsonKey("motion_str")
    private String motion_str = ""; //议案内容
    @JsonKey("begin_date")
    private String begin_date = ""; //起始日期
    @JsonKey("end_date")
    private String end_date = ""; //结束日期
    @JsonKey("notice_type")
    private String notice_type = ""; // 通知类型
    @JsonKey("notice_date")
    private String notice_date = ""; // 通知日期
    @JsonKey("trade_date")
    private String trade_date = ""; // 交易日期

    public HKCapitalVoteMsgBean() {

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

    public String getIsin_code() {
        return isin_code;
    }

    public void setIsin_code(String isin_code) {
        this.isin_code = isin_code;
    }

    public String getPlacard_id() {
        return placard_id;
    }

    public void setPlacard_id(String placard_id) {
        this.placard_id = placard_id;
    }

    public String getMotion_id() {
        return motion_id;
    }

    public void setMotion_id(String motion_id) {
        this.motion_id = motion_id;
    }

    public String getMotion_str() {
        return motion_str;
    }

    public void setMotion_str(String motion_str) {
        this.motion_str = motion_str;
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

    public String getNotice_type() {
        return notice_type;
    }

    public void setNotice_type(String notice_type) {
        this.notice_type = notice_type;
    }

    public String getNotice_date() {
        return notice_date;
    }

    public void setNotice_date(String notice_date) {
        this.notice_date = notice_date;
    }

    public String getTrade_date() {
        return trade_date;
    }

    public void setTrade_date(String trade_date) {
        this.trade_date = trade_date;
    }
}
