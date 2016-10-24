package com.thinkive.android.trade_bz.a_hk.bean;

import com.thinkive.android.trade_bz.a_stock.bean.BaseBean;
import com.thinkive.android.trade_bz.others.JsonKey;

/**
 * 港股通 公司行为信息查询
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/7/28
 */
public class HKCapitalCompanyMsgBean extends BaseBean {
    @JsonKey("stock_code")
    private String stock_code = ""; // 证券代码
    @JsonKey("stock_name")
    private String stock_name = ""; //证券名称
    @JsonKey("corpbehavior_code")
    private String corpbehavior_code = ""; // 公司行为代码
    @JsonKey("market_year")
    private String market_year = ""; // 挂牌年份
    @JsonKey("authority_times")
    private String authority_times = ""; // 权益次数
    @JsonKey("authority_type")
    private String authority_type = ""; //权益类别（见数据字典)
    @JsonKey("authority_type_name")
    private String authority_type_name = ""; //权益类别名称
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

    public HKCapitalCompanyMsgBean() {

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

    public String getCorpbehavior_code() {
        return corpbehavior_code;
    }

    public void setCorpbehavior_code(String corpbehavior_code) {
        this.corpbehavior_code = corpbehavior_code;
    }

    public String getMarket_year() {
        return market_year;
    }

    public void setMarket_year(String market_year) {
        this.market_year = market_year;
    }

    public String getAuthority_times() {
        return authority_times;
    }

    public void setAuthority_times(String authority_times) {
        this.authority_times = authority_times;
    }

    public String getAuthority_type() {
        return authority_type;
    }

    public void setAuthority_type(String authority_type) {
        this.authority_type = authority_type;
    }

    public String getAuthority_type_name() {
        return authority_type_name;
    }

    public void setAuthority_type_name(String authority_type_name) {
        this.authority_type_name = authority_type_name;
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
