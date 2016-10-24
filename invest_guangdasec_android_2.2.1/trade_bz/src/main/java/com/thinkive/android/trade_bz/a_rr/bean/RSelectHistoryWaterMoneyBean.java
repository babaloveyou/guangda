package com.thinkive.android.trade_bz.a_rr.bean;

import com.thinkive.android.trade_bz.a_stock.bean.BaseBean;
import com.thinkive.android.trade_bz.others.JsonKey;

/**
 * 融资融券--查询--历史资金流水（303043）
 * 历史资金流失（对账单）
 * Announcements：
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/8/19
 */
public class RSelectHistoryWaterMoneyBean extends BaseBean {
  /**
   *发生日期
   */
  @JsonKey("init_date")
  private String init_date="";
  /**
   *委托日期
   */
  @JsonKey("init_time")
  private String init_time="";
  /**
   * 业务名称
   */
  @JsonKey("business_name")
  private String business_name="";
  /**
   *后资金额
   */
  @JsonKey("post_balance")
  private String post_balance="";
  /**
   * 币种名称
   */
  @JsonKey("money_type_name")
  private String money_type_name="";
  /**
   *  委托编号
   */
  @JsonKey("entrust_no")
  private String entrust_no="";
  /**
   * 证券代码
   */
  @JsonKey("stock_code")
  private String stock_code="";
  /**
   *证券名称
   */
  @JsonKey("stock_name")
  private String stock_name="";
  /**
   *成交价格
   */
  @JsonKey("business_price")
  private String business_price="";
  /**
   *发生金额
   */
  @JsonKey("occur_balance")
  private String occur_balance="";
  /**
   * 成交数量
   */
  @JsonKey("occur_amount")
  private String occur_amount="";

  public RSelectHistoryWaterMoneyBean() {
  }

  public String getInit_date() {
    return init_date;
  }

  public void setInit_date(String init_date) {
    this.init_date = init_date;
  }

  public String getInit_time() {
    return init_time;
  }

  public void setInit_time(String init_time) {
    this.init_time = init_time;
  }

  public String getBusiness_name() {
    return business_name;
  }

  public void setBusiness_name(String business_name) {
    this.business_name = business_name;
  }

  public String getPost_balance() {
    return post_balance;
  }

  public void setPost_balance(String post_balance) {
    this.post_balance = post_balance;
  }

  public String getMoney_type_name() {
    return money_type_name;
  }

  public void setMoney_type_name(String money_type_name) {
    this.money_type_name = money_type_name;
  }

  public String getEntrust_no() {
    return entrust_no;
  }

  public void setEntrust_no(String entrust_no) {
    this.entrust_no = entrust_no;
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

  public String getBusiness_price() {
    return business_price;
  }

  public void setBusiness_price(String business_price) {
    this.business_price = business_price;
  }

  public String getOccur_balance() {
    return occur_balance;
  }

  public void setOccur_balance(String occur_balance) {
    this.occur_balance = occur_balance;
  }

  public String getOccur_amount() {
    return occur_amount;
  }

  public void setOccur_amount(String occur_amount) {
    this.occur_amount = occur_amount;
  }
}
