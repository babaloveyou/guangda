package com.thinkive.android.trade_bz.a_new.bean;

import com.thinkive.android.trade_bz.a_stock.bean.BaseBean;
import com.thinkive.android.trade_bz.others.JsonKey;

/**
 *  新股配号实体类
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/6/25
 */

public class NewDistributeNumBean extends BaseBean {

    @JsonKey("bizdate")
    private String bizdate = "";//配号日期
    @JsonKey("stock_name")
    private String stock_name = "";//证券名称
    @JsonKey("stock_code")
    private String stock_code = "";//证券代码
    @JsonKey("matchqty")
    private String matchqty = "";//委托数量（成交数量）
    @JsonKey("mateno")
    private String mateno = "";//起始配号（申购配号）
    @JsonKey("stock_account")
    private String stock_account = "";//配号数量（股东代码）
    @JsonKey("exchange_type")
    private String exchange_type = "";  //新增字段

    public NewDistributeNumBean() {
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

    public String getMatchqty() {
        return matchqty;
    }

    public String getMateno() {
        return mateno;
    }

    public String getStock_account() {
        return stock_account;
    }

    public void setBizdate(String bizdate) {
        this.bizdate = bizdate;
    }

    public void setStock_name(String stock_name) {
        this.stock_name = stock_name;
    }

    public void setStock_code(String stock_code) {
        this.stock_code = stock_code;
    }

    public void setMatchqty(String matchqty) {
        this.matchqty = matchqty;
    }

    public void setMateno(String mateno) {
        this.mateno = mateno;
    }

    public void setStock_account(String stock_account) {
        this.stock_account = stock_account;
    }

    public NewDistributeNumBean(String bizdate, String stock_name, String stock_code,
                                String matchqty, String mateno, String stock_account) {
        this.bizdate = bizdate;
        this.stock_name = stock_name;
        this.stock_code = stock_code;
        this.matchqty = matchqty;
        this.mateno = mateno;
        this.stock_account = stock_account;
    }

    public String getExchange_type() {
        return exchange_type;
    }

    public void setExchange_type(String exchange_type) {
        this.exchange_type = exchange_type;
    }
}
