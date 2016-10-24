package com.thinkive.android.trade_bz.a_rr.bean;

import com.thinkive.android.trade_bz.a_stock.bean.BaseBean;
import com.thinkive.android.trade_bz.others.JsonKey;

/**
 *  融资融券--担保品划转联动
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/1/21
 */

public class RCollaterLinkBean extends BaseBean {
    /**
     *普通证券账号
     */
    @JsonKey("stock_account")
    private String stock_account = "";
    /**
     *普通席位编号
     */
    @JsonKey("seat_no")
    private String seat_no = "";
    /**
     *交易类别(见数据字典)
     */
    @JsonKey("exchange_type")
    private String exchange_type = "";
    /**
     *  信用证券账号
     */
    @JsonKey("stock_account_crdt")
    private String stock_account_crdt = "";
    /**
     * 信用席位编号
     */
    @JsonKey("seat_no_crdt")
    private String seat_no_crdt = "";
    /**
     * 持仓成本价（根据买卖方向不同查询不同的持仓）
     */
    @JsonKey("cost_price")
    private String cost_price = "";
    /**
     *  最大可转数量（根据买卖方向不同查询不同的持仓）
     */
    @JsonKey("enable_amount")
    private String enable_amount = "";
    /**
     * 现价
     */
    @JsonKey("last_price")
    private String last_price = "";
    /**
     * 证券代码
     */
    @JsonKey("stock_code")
    private String stock_code = "";

    public RCollaterLinkBean() {

    }

    public String getStock_account() {
        return stock_account;
    }

    public void setStock_account(String stock_account) {
        this.stock_account = stock_account;
    }

    public String getSeat_no() {
        return seat_no;
    }

    public void setSeat_no(String seat_no) {
        this.seat_no = seat_no;
    }

    public String getExchange_type() {
        return exchange_type;
    }

    public void setExchange_type(String exchange_type) {
        this.exchange_type = exchange_type;
    }

    public String getStock_account_crdt() {
        return stock_account_crdt;
    }

    public void setStock_account_crdt(String stock_account_crdt) {
        this.stock_account_crdt = stock_account_crdt;
    }

    public String getSeat_no_crdt() {
        return seat_no_crdt;
    }

    public void setSeat_no_crdt(String seat_no_crdt) {
        this.seat_no_crdt = seat_no_crdt;
    }

    public String getCost_price() {
        return cost_price;
    }

    public void setCost_price(String cost_price) {
        this.cost_price = cost_price;
    }

    public String getEnable_amount() {
        return enable_amount;
    }

    public void setEnable_amount(String enable_amount) {
        this.enable_amount = enable_amount;
    }

    public String getLast_price() {
        return last_price;
    }

    public void setLast_price(String last_price) {
        this.last_price = last_price;
    }

    public String getStock_code() {
        return stock_code;
    }

    public void setStock_code(String stock_code) {
        this.stock_code = stock_code;
    }
}
