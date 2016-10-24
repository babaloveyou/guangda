package com.thinkive.android.trade_bz.a_new.bean;

import com.thinkive.android.trade_bz.a_stock.bean.BaseBean;
import com.thinkive.android.trade_bz.others.JsonKey;

/**
 * 放弃认购申报撤单
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/6/22
 */
public class NewGiveUpRevocationBean extends BaseBean {
    /**
     * 股票代码、也用作申购代码
     */
    @JsonKey("stock_code")
    private String stock_code = "";
    /**
     * 市场
     */
    @JsonKey("market")
    private String market = "";
    /**
     * 股票名称
     */
    @JsonKey("stock_name")
    private String stock_name = "";
    /**
     * 申购数量上限
     */
    @JsonKey("applymax_online")
    private String applymax_online = "";
    /**
     * 发行价
     */
    @JsonKey("issue_price")
    private String issue_price = "";
    /**
     * 单位、上网发行认购/申购单位（股）
     */
    @JsonKey("applyunitonline")
    private String applyunitonline = "";

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

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public String getApplymax_online() {
        return applymax_online;
    }

    public void setApplymax_online(String applymax_online) {
        this.applymax_online = applymax_online;
    }

    public String getIssue_price() {
        return issue_price;
    }

    public void setIssue_price(String issue_price) {
        this.issue_price = issue_price;
    }

    public String getApplyunitonline() {
        return applyunitonline;
    }

    public void setApplyunitonline(String applyunitonline) {
        this.applyunitonline = applyunitonline;
    }
}
