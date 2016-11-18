package com.thinkive.android.trade_bz.a_stock.bean;

import com.thinkive.android.trade_bz.others.JsonKey;

/**
 * 买入卖出下单页面的股票搜索提示列表的项的bean
 * @author 王志鸿
 * @company Thinkive
 * @date 2015/6/16
 */
public class CodeTableBean extends BaseBean {
	//名称：市场：代码：涨停：跌停：是否停牌:股票类型:现价
	/**
	 * 股票名称
	 */
	@JsonKey("name")
	private String name = "";
	/**
	 * 交易市场
	 */
	@JsonKey("market")
	private String market = "";
	/**
	 * 股票代码
	 */
	@JsonKey("code")
	private String code = "";

	/**
	 * 涨停价
	 */
	@JsonKey("limitUp")
	private String upLimit = "";
	/**
	 * 跌停价
	 */
	@JsonKey("limitDown")
	private String downLimit = "";
	/**
	 * 是否停牌
	 */
	@JsonKey("issuspend")
	private String issuspend = "";
	/**
	 * 针对行情的股票类型
	 */
	@JsonKey("stktype")
	private String stockType = "";
	/**
	 * 现价
	 */
	@JsonKey("now")
	private String now = "";
	/**
	 * 交易市场类别，由行情类别转换得到的
	 */
	private String exchange_type = "";
	/**
	 * 点击加减号时增减单位
	 */
	private String step_unit = "";
	/**
	 * 股东账号
	 */
	private String stock_account = "";

	public CodeTableBean() {

	}

	public String getNow() {
		return now;
	}

	public void setNow(String now) {
		this.now = now;
	}

	public String getDownLimit() {
		return downLimit;
	}

	public void setDownLimit(String downLimit) {
		this.downLimit = downLimit;
	}

	public String getIssuspend() {
		return issuspend;
	}

	public void setIssuspend(String issuspend) {
		this.issuspend = issuspend;
	}

	public String getStockType() {
		return stockType;
	}

	public void setStockType(String stockType) {
		this.stockType = stockType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMarket() {
		return market;
	}

	public void setMarket(String market) {
		this.market = market;
	}

	public String getUpLimit() {
		return upLimit;
	}

	public void setUpLimit(String upLimit) {
		this.upLimit = upLimit;
	}

	public String getExchange_type() {
		return exchange_type;
	}

	public void setExchange_type(String exchange_type) {
		this.exchange_type = exchange_type;
	}

	public String getStep_unit() {
		return step_unit;
	}

	public void setStep_unit(String step_unit) {
		this.step_unit = step_unit;
	}

	public String getStock_account() {
		return stock_account;
	}

	public void setStock_account(String stock_account) {
		this.stock_account = stock_account;
	}
	@Override
	public String toString() {
		return "CodeTableBean{" +
				"name='" + name + '\'' +
				", market='" + market + '\'' +
				", code='" + code + '\'' +
				", upLimit='" + upLimit + '\'' +
				", downLimit='" + downLimit + '\'' +
				", issuspend='" + issuspend + '\'' +
				", stockType='" + stockType + '\'' +
				", now='" + now + '\'' +
				", exchange_type='" + exchange_type + '\'' +
				", step_unit='" + step_unit + '\'' +
				", stock_account='" + stock_account + '\'' +
				'}';
	}
}
