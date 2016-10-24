package com.thinkive.android.trade_bz.a_rr.bean;

import com.thinkive.android.trade_bz.a_stock.bean.BaseBean;
import com.thinkive.android.trade_bz.others.JsonKey;

/**
 * 融资融券--查询--交割单（303027）
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/8/19
 */
public class RSelectDOSelectBean extends BaseBean {
  /**
   *发生日期
   */
  @JsonKey("init_date")
  private String init_date="";
  /**
   *委托日期
   */
  @JsonKey("entrust_date")
  private String entrust_date="";
  /**
   *业务名称
   */
  @JsonKey("business_name")
  private String business_name="";
  /**
   *交易市场类别
   */
  @JsonKey("exchange_type")
  private String exchange_type="";
  /**
   *交易市场类别名称
   */
  @JsonKey("exchange_type_name")
  private String exchange_type_name="";
  /**
   *证券账号
   */
  @JsonKey("stock_account")
  private String stock_account="";
  /**
   *席位编号
   */
  @JsonKey("seat_no")
  private String seat_no="";
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
   *委托标志
   */
  @JsonKey("entrust_bs")
  private String entrust_bs="";
  /**
   *委托标志名称
   */
  @JsonKey("entrust_bs_name")
  private String entrust_bs_name="";
  /**
   * 成交价格
   */
  @JsonKey("business_price")
  private String business_price="";
  /**
   * 发生数量
   */
  @JsonKey("occur_amount")
  private String occur_amount="";
  /**
   *成交金额
   */
  @JsonKey("business_balance")
  private String business_balance="";
  /**
   *发生金额
   */
  @JsonKey("occur_balance")
  private String occur_balance="";
  /**
   *后资金额
   */
  @JsonKey("post_balance")
  private String post_balance="";
  /**
   *后证券额
   */
  @JsonKey("post_amount")
  private String post_amount="";
  /**
   * 委托编号
   */
  @JsonKey("entrust_no")
  private String entrust_no="";
  /**
   *成交编号
   */
  @JsonKey("business_no")
  private String business_no="";
  /**
   *申报时间
   */
  @JsonKey("report_time")
  private String report_time="";
  /**
   *成交时间
   */
  @JsonKey("business_time")
  private String business_time="";
  /**
   * 手续费
   */
  @JsonKey("fee_sxf")
  private String fee_sxf ="";
  /**
   * 清算费
   */
  @JsonKey("fee_qsf")
  private String fee_qsf ="";
  /**
   * 交易规费
   */
  @JsonKey("fee_jygf")
  private String fee_jygf ="";
  /**
   * 印花费
   */
  @JsonKey("fare1")
  private String fare1="";
  /**
   * 过户费
   */
  @JsonKey("fare2")
  private String fare2="";
  /**
   * 备注
   */
  @JsonKey("remark")
  private String remark="";
  /**
   * 清算金额
   */
  @JsonKey("fundeffect")
  private String fundeffect = "";
  /**
   * 合同序号
   */
  @JsonKey("report_no")
  private String report_no = "";

  public RSelectDOSelectBean() {
  }

  public String getInit_date() {
    return init_date;
  }

  public void setInit_date(String init_date) {
    this.init_date = init_date;
  }

  public String getEntrust_date() {
    return entrust_date;
  }

  public void setEntrust_date(String entrust_date) {
    this.entrust_date = entrust_date;
  }

  public String getBusiness_name() {
    return business_name;
  }

  public void setBusiness_name(String business_name) {
    this.business_name = business_name;
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

  public String getEntrust_bs() {
    return entrust_bs;
  }

  public void setEntrust_bs(String entrust_bs) {
    this.entrust_bs = entrust_bs;
  }

  public String getEntrust_bs_name() {
    return entrust_bs_name;
  }

  public void setEntrust_bs_name(String entrust_bs_name) {
    this.entrust_bs_name = entrust_bs_name;
  }

  public String getBusiness_price() {
    return business_price;
  }

  public void setBusiness_price(String business_price) {
    this.business_price = business_price;
  }

  public String getOccur_amount() {
    return occur_amount;
  }

  public void setOccur_amount(String occur_amount) {
    this.occur_amount = occur_amount;
  }

  public String getBusiness_balance() {
    return business_balance;
  }

  public void setBusiness_balance(String business_balance) {
    this.business_balance = business_balance;
  }

  public String getOccur_balance() {
    return occur_balance;
  }

  public void setOccur_balance(String occur_balance) {
    this.occur_balance = occur_balance;
  }

  public String getPost_balance() {
    return post_balance;
  }

  public void setPost_balance(String post_balance) {
    this.post_balance = post_balance;
  }

  public String getPost_amount() {
    return post_amount;
  }

  public void setPost_amount(String post_amount) {
    this.post_amount = post_amount;
  }

  public String getEntrust_no() {
    return entrust_no;
  }

  public void setEntrust_no(String entrust_no) {
    this.entrust_no = entrust_no;
  }

  public String getBusiness_no() {
    return business_no;
  }

  public void setBusiness_no(String business_no) {
    this.business_no = business_no;
  }

  public String getReport_time() {
    return report_time;
  }

  public void setReport_time(String report_time) {
    this.report_time = report_time;
  }

  public String getBusiness_time() {
    return business_time;
  }

  public void setBusiness_time(String business_time) {
    this.business_time = business_time;
  }

  public String getFee_sxf() {
    return fee_sxf;
  }

  public void setFee_sxf(String fee_sxf) {
    this.fee_sxf = fee_sxf;
  }

  public String getFee_qsf() {
    return fee_qsf;
  }

  public void setFee_qsf(String fee_qsf) {
    this.fee_qsf = fee_qsf;
  }

  public String getFare2() {
    return fare2;
  }

  public void setFare2(String fare2) {
    this.fare2 = fare2;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public String getFundeffect() {
    return fundeffect;
  }

  public void setFundeffect(String fundeffect) {
    this.fundeffect = fundeffect;
  }

  public String getFee_jygf() {
    return fee_jygf;
  }

  public void setFee_jygf(String fee_jygf) {
    this.fee_jygf = fee_jygf;
  }

  public String getFare1() {
    return fare1;
  }

  public void setFare1(String fare1) {
    this.fare1 = fare1;
  }

  public String getReport_no() {
    return report_no;
  }

  public void setReport_no(String report_no) {
    this.report_no = report_no;
  }

}
