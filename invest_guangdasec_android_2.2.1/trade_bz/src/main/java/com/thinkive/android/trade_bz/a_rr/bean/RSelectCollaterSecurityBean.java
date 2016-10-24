package com.thinkive.android.trade_bz.a_rr.bean;

import com.thinkive.android.trade_bz.a_stock.bean.BaseBean;
import com.thinkive.android.trade_bz.others.JsonKey;

/**
 *  融资融券--查询--担保品证券查询（303002）
 *  融资融券--查询--标的证券--融资标的（303005）
 *  融资融券--查询--标的证券--融券标的（303006）
 * @author 张雪梅
 * @company Thinkive
 * @date 15/8/19
 */
public class RSelectCollaterSecurityBean extends BaseBean {
    /**
     * 担保品折算比例
     */
    @JsonKey("assure_ratio")
    private String assure_ratio="";
    /**
     * 个人浮动比例
     */
    @JsonKey("float_ratio")
    private String float_ratio="";
    /**
     * 担保状态( 0-正常 1-暂停 )
     */
    @JsonKey("assure_status")
    private String assure_status="";
    /**
     *市值价
     */
    @JsonKey("asset_price")
    private String asset_price="";
    /**
     *定位串
     */
    @JsonKey("poststr")
    private String poststr="";
    /**
     *证券代码
     */
    @JsonKey("stock_code")
    private String stock_code="";
    /**
     * 证券名称
     */
    @JsonKey("stock_name")
    private String stock_name="";
    /**
     * 交易市场类别
     */
    @JsonKey("exchange_type")
    private String exchange_type="";
    /**
     * 交易市场名称
     */
    @JsonKey("exchange_type_name")
    private String exchange_type_name="";
    /**
     *证券代码融资状态（0-正常 1-暂停）
     */
    @JsonKey("finance_status")
    private String finance_status="";
    /**
     * 保证金比例
     */
    @JsonKey("bail_ratio")
    private String bail_ratio="";
    /**
     * 当日可融 0-正常 1-暂停
     */
    @JsonKey("today_enable")
    private String today_enable="";
    /**
     * 结束日期
     */
    @JsonKey("end_date")
    private String end_date="";
    /**
     * 备注
     */
    @JsonKey("remark")
    private String remark="";
    /**
     * 融券状态(0-正常 1-暂停)
     */
    @JsonKey("slo_status")
    private String slo_status="";
    /**
     * 融券保证金比例
     */
    @JsonKey("slo_ratio")
    private String slo_ratio="";
    /**
     * 融券可融数量
     */
    @JsonKey("enable_amount")
    private String enable_amount="";
    /**
     *回报买入数量
     */
    @JsonKey("real_buy_amount")
    private String real_buy_amount="";
    /**
     *回报卖出数量
     */
    @JsonKey("real_sell_amount")
    private String real_sell_amount="";
    /**
     * 交易市场
     */
    @JsonKey("market")
    private String market = "";

    public RSelectCollaterSecurityBean() {
    }

    public String getAssure_ratio() {
        return assure_ratio;
    }

    public void setAssure_ratio(String assure_ratio) {
        this.assure_ratio = assure_ratio;
    }

    public String getFloat_ratio() {
        return float_ratio;
    }

    public void setFloat_ratio(String float_ratio) {
        this.float_ratio = float_ratio;
    }

    public String getAssure_status() {
        return assure_status;
    }

    public void setAssure_status(String assure_status) {
        this.assure_status = assure_status;
    }

    public String getAsset_price() {
        return asset_price;
    }

    public void setAsset_price(String asset_price) {
        this.asset_price = asset_price;
    }

    public String getPoststr() {
        return poststr;
    }

    public void setPoststr(String poststr) {
        this.poststr = poststr;
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

    public String getExchange_type_name() {
        return exchange_type_name;
    }

    public void setExchange_type_name(String exchange_type_name) {
        this.exchange_type_name = exchange_type_name;
    }

    public String getFinance_status() {
        return finance_status;
    }

    public void setFinance_status(String finance_status) {
        this.finance_status = finance_status;
    }

    public String getBail_ratio() {
        return bail_ratio;
    }

    public void setBail_ratio(String bail_ratio) {
        this.bail_ratio = bail_ratio;
    }

    public String getToday_enable() {
        return today_enable;
    }

    public void setToday_enable(String today_enable) {
        this.today_enable = today_enable;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSlo_status() {
        return slo_status;
    }

    public void setSlo_status(String slo_status) {
        this.slo_status = slo_status;
    }

    public String getSlo_ratio() {
        return slo_ratio;
    }

    public void setSlo_ratio(String slo_ratio) {
        this.slo_ratio = slo_ratio;
    }

    public String getEnable_amount() {
        return enable_amount;
    }

    public void setEnable_amount(String enable_amount) {
        this.enable_amount = enable_amount;
    }

    public String getReal_buy_amount() {
        return real_buy_amount;
    }

    public void setReal_buy_amount(String real_buy_amount) {
        this.real_buy_amount = real_buy_amount;
    }

    public String getReal_sell_amount() {
        return real_sell_amount;
    }

    public void setReal_sell_amount(String real_sell_amount) {
        this.real_sell_amount = real_sell_amount;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }
}
