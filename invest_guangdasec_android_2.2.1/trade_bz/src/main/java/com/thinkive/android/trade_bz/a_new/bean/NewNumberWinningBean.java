package com.thinkive.android.trade_bz.a_new.bean;

import com.thinkive.android.trade_bz.a_stock.bean.BaseBean;
import com.thinkive.android.trade_bz.others.JsonKey;

/**
 * 新股中签实体类
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/6/25
 */

public class NewNumberWinningBean extends BaseBean {

    @JsonKey("bizdate")
    private String bizdate  = "";//中签日期
    @JsonKey("stock_name")
    private String stock_name  = "";//证券名称
    @JsonKey("stock_code")
    private String stock_code  = "";//证券代码
    @JsonKey("stock_account")
    private String stock_account  = "";//成交编码（股东代码）
    @JsonKey("winning_count")
    private String winning_count  = "";//中签数量
    @JsonKey("matchprice")
    private String matchprice  = "";//成交价格
    @JsonKey("matchqty")
    private String matchqty  = "";//成交数量
    @JsonKey("trade_count")
    private String trade_count  = "";//成交金额
    @JsonKey("giveupqty")
    private String giveupqty  = ""; //放弃数量
    @JsonKey("clearsno")
    private String clearsno  = ""; //交收顺序
    @JsonKey("jkamt")
    private String jkamt  = ""; //缴款金额
    @JsonKey("exchange_type")
    private String exchange_type  = "";//交易市场

    public NewNumberWinningBean() {
    }

    public NewNumberWinningBean(String bizdate, String stock_name, String stock_code,
                                String stock_account, String winning_count, String matchprice,
                                String matchqty, String trade_count) {
        this.bizdate = bizdate;
        this.stock_name = stock_name;
        this.stock_code = stock_code;
        this.stock_account = stock_account;
        this.winning_count = winning_count;
        this.matchprice = matchprice;
        this.matchqty = matchqty;
        this.trade_count = trade_count;
    }

    public String getBizdate() {
        return bizdate;
    }

    public String getStock_name() {
        return stock_name;
    }

    public String getStock_code() {
        return stock_code;
    }

    public String getStock_account() {
        return stock_account;
    }

    public String getWinning_count() {
        return winning_count;
    }

    public String getMatchprice() {
        return matchprice;
    }

    public String getMatchqty() {
        return matchqty;
    }

    public String getTrade_count() {
        return trade_count;
    }

    public void setBizdate(String bizdate) {
        this.bizdate = bizdate;
    }

    public void setStock_name(String stock_name) {
        this.stock_name = stock_name;
    }

    public void setStock_account(String stock_account) {
        this.stock_account = stock_account;
    }

    public void setStock_code(String stock_code) {
        this.stock_code = stock_code;
    }

    public void setWinning_count(String winning_count) {
        this.winning_count = winning_count;
    }

    public void setMatchprice(String matchprice) {
        this.matchprice = matchprice;
    }

    public void setMatchqty(String matchqty) {
        this.matchqty = matchqty;
    }

    public void setTrade_count(String trade_count) {
        this.trade_count = trade_count;
    }

    public String getGiveupqty() {
        return giveupqty;
    }

    public void setGiveupqty(String giveupqty) {
        this.giveupqty = giveupqty;
    }

    public String getClearsno() {
        return clearsno;
    }

    public void setClearsno(String clearsno) {
        this.clearsno = clearsno;
    }

    public String getJkamt() {
        return jkamt;
    }

    public void setJkamt(String jkamt) {
        this.jkamt = jkamt;
    }

    public String getExchange_type() {
        return exchange_type;
    }

    public void setExchange_type(String exchange_type) {
        this.exchange_type = exchange_type;
    }
}
