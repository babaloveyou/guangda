package com.thinkive.android.trade_bz.a_trans.bean;

import com.thinkive.android.trade_bz.a_stock.bean.BaseBean;
import com.thinkive.android.trade_bz.others.JsonKey;

/**
 * 转股交易挂牌股票查询（301713）
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/7/29
 */
public class TransSelectTicketBean extends BaseBean {
    @JsonKey("stock_code")
    private String stock_code = ""; //证券代码
    @JsonKey("stock_name")
    private String stock_name = ""; //证券名称
    @JsonKey("exchange_type")
    private String exchange_type = ""; //交易市场类别（见数据字典)
    @JsonKey("exchange_type_name")
    private String exchange_type_name = ""; //交易市场类别（见数据字典)
    @JsonKey("up_limit")
    private String up_limit = ""; //涨停价
    @JsonKey("down_limit")
    private String down_limit = ""; //跌停价
    @JsonKey("stock_type")
    private String stock_type = ""; //证券类型 g：挂牌公司证券
    @JsonKey("stock_type_name")
    private String stock_type_name = ""; //证券类型 g：挂牌公司证券

    public TransSelectTicketBean() {

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

    public String getExchange_type() {
        return exchange_type;
    }

    public void setExchange_type(String exchange_type) {
        this.exchange_type = exchange_type;
    }

    public String getUp_limit() {
        return up_limit;
    }

    public void setUp_limit(String up_limit) {
        this.up_limit = up_limit;
    }

    public String getDown_limit() {
        return down_limit;
    }

    public void setDown_limit(String down_limit) {
        this.down_limit = down_limit;
    }

    public String getStock_type() {
        return stock_type;
    }

    public void setStock_type(String stock_type) {
        this.stock_type = stock_type;
    }

    public String getExchange_type_name() {
        return exchange_type_name;
    }

    public void setExchange_type_name(String exchange_type_name) {
        this.exchange_type_name = exchange_type_name;
    }

    public String getStock_type_name() {
        return stock_type_name;
    }

    public void setStock_type_name(String stock_type_name) {
        this.stock_type_name = stock_type_name;
    }
}