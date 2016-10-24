package com.thinkive.android.trade_bz.a_rr.bean;

import android.os.Parcel;

import com.thinkive.android.trade_bz.a_stock.bean.BaseBean;
import com.thinkive.android.trade_bz.others.JsonKey;

/**
 * 融资融券_合约查询请求(明细模式303021)
 * @author 张雪梅
 * @date 2016/8/23
 */
public class RChooseContractBean extends BaseBean {
 @JsonKey("open_date")
 private String open_date = ""; // 合约开仓日期
 @JsonKey("compact_id")
 private String compact_id = ""; // 合约编号
 @JsonKey("fund_account")
 private String fund_account = ""; //资金账号
 @JsonKey("money_type")
 private String money_type = ""; // 币种类别（见数据字典）
 @JsonKey("money_type_name")
 private String money_type_name = ""; // 币种类别名称
 @JsonKey("exchange_type")
 private String exchange_type = ""; // 交易类别（见数据字典）
 @JsonKey("exchange_type_name")
 private String exchange_type_name = ""; // 交易类别名称
 @JsonKey("stock_account")
 private String stock_account = ""; // 证券账号
 @JsonKey("stock_code")
 private String stock_code = ""; // 证券代码
 @JsonKey("stock_name")
 private String stock_name = ""; // 证券名称
 @JsonKey("entrust_no")
 private String entrust_no = ""; // 委托编号
 @JsonKey("entrust_price")
 private String entrust_price = ""; // 委托价格
 @JsonKey("entrust_amount")
 private String entrust_amount = ""; // 委托数量
 @JsonKey("business_amount")
 private String business_amount = ""; //合约开仓数量
 @JsonKey("business_balance")
 private String business_balance = ""; //合约开仓金额
 @JsonKey("compact_type")
 private String compact_type = ""; //合约类型(0-融资，1-融券)
 @JsonKey("compact_status")
 private String compact_status = ""; // 合约状态（见数据字典）
 @JsonKey("begin_compact_balance")
 private String begin_compact_balance = ""; // 期初合约金额
 @JsonKey("real_compact_balance")
 private String real_compact_balance = ""; //  未还合约金额
 @JsonKey("real_compact_amount")
 private String real_compact_amount = ""; //未还合约数量
 @JsonKey("ret_end_date")
 private String ret_end_date = ""; // 归还截止日
 @JsonKey("business_price")
 private String business_price = ""; //   成交价格
 @JsonKey("market")
 private String market = "";//市场
 /**
  * 是否被选中 默认没有被选中
  */
 private boolean checked = false;
 /**
  * 默认是可以选择对的
  */
 private boolean can_checked = true;


 public RChooseContractBean() {

 }

 public String getOpen_date() {
  return open_date;
 }

 public void setOpen_date(String open_date) {
  this.open_date = open_date;
 }

 public String getCompact_id() {
  return compact_id;
 }

 public void setCompact_id(String compact_id) {
  this.compact_id = compact_id;
 }

 public String getFund_account() {
  return fund_account;
 }

 public void setFund_account(String fund_account) {
  this.fund_account = fund_account;
 }

 public String getMoney_type() {
  return money_type;
 }

 public void setMoney_type(String money_type) {
  this.money_type = money_type;
 }

 public String getMoney_type_name() {
  return money_type_name;
 }

 public void setMoney_type_name(String money_type_name) {
  this.money_type_name = money_type_name;
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

 public String getEntrust_no() {
  return entrust_no;
 }

 public void setEntrust_no(String entrust_no) {
  this.entrust_no = entrust_no;
 }

 public String getEntrust_price() {
  return entrust_price;
 }

 public void setEntrust_price(String entrust_price) {
  this.entrust_price = entrust_price;
 }

 public String getEntrust_amount() {
  return entrust_amount;
 }

 public void setEntrust_amount(String entrust_amount) {
  this.entrust_amount = entrust_amount;
 }

 public String getBusiness_amount() {
  return business_amount;
 }

 public void setBusiness_amount(String business_amount) {
  this.business_amount = business_amount;
 }

 public String getBusiness_balance() {
  return business_balance;
 }

 public void setBusiness_balance(String business_balance) {
  this.business_balance = business_balance;
 }
 public String getCompact_type() {
  return compact_type;
 }

 public void setCompact_type(String compact_type) {
  this.compact_type = compact_type;
 }

 public String getCompact_status() {
  return compact_status;
 }

 public void setCompact_status(String compact_status) {
  this.compact_status = compact_status;
 }

 public String getBegin_compact_balance() {
  return begin_compact_balance;
 }

 public void setBegin_compact_balance(String begin_compact_balance) {
  this.begin_compact_balance = begin_compact_balance;
 }
 public String getReal_compact_balance() {
  return real_compact_balance;
 }

 public void setReal_compact_balance(String real_compact_balance) {
  this.real_compact_balance = real_compact_balance;
 }

 public String getReal_compact_amount() {
  return real_compact_amount;
 }

 public void setReal_compact_amount(String real_compact_amount) {
  this.real_compact_amount = real_compact_amount;
 }
 public String getRet_end_date() {
  return ret_end_date;
 }

 public void setRet_end_date(String ret_end_date) {
  this.ret_end_date = ret_end_date;
 }

 public String getBusiness_price() {
  return business_price;
 }

 public void setBusiness_price(String business_price) {
  this.business_price = business_price;
 }

 public boolean isChecked() {
  return checked;
 }

 public void setChecked(boolean checked) {
  this.checked = checked;
 }

 public boolean isCan_checked() {
  return can_checked;
 }

 public void setCan_checked(boolean can_checked) {
  this.can_checked = can_checked;
 }

 public String getMarket() {
  return market;
 }

 public void setMarket(String market) {
  this.market = market;
 }

 @Override
 public int describeContents() {
  return 0;
 }

 @Override
 public void writeToParcel(Parcel dest, int flags) {
  super.writeToParcel(dest, flags);
  dest.writeString(this.open_date);
  dest.writeString(this.compact_id);
  dest.writeString(this.fund_account);
  dest.writeString(this.money_type);
  dest.writeString(this.money_type_name);
  dest.writeString(this.exchange_type);
  dest.writeString(this.exchange_type_name);
  dest.writeString(this.stock_account);
  dest.writeString(this.stock_code);
  dest.writeString(this.stock_name);
  dest.writeString(this.entrust_no);
  dest.writeString(this.entrust_price);
  dest.writeString(this.entrust_amount);
  dest.writeString(this.business_amount);
  dest.writeString(this.business_balance);
  dest.writeString(this.compact_type);
  dest.writeString(this.compact_status);
  dest.writeString(this.begin_compact_balance);
  dest.writeString(this.real_compact_balance);
  dest.writeString(this.real_compact_amount);
  dest.writeString(this.ret_end_date);
  dest.writeString(this.business_price);
  dest.writeString(this.market);
  dest.writeByte(this.checked ? (byte) 1 : (byte) 0);
  dest.writeByte(this.can_checked ? (byte) 1 : (byte) 0);
 }

 protected RChooseContractBean(Parcel in) {
  this.open_date = in.readString();
  this.compact_id = in.readString();
  this.fund_account = in.readString();
  this.money_type = in.readString();
  this.money_type_name = in.readString();
  this.exchange_type = in.readString();
  this.exchange_type_name = in.readString();
  this.stock_account = in.readString();
  this.stock_code = in.readString();
  this.stock_name = in.readString();
  this.entrust_no = in.readString();
  this.entrust_price = in.readString();
  this.entrust_amount = in.readString();
  this.business_amount = in.readString();
  this.business_balance = in.readString();
  this.compact_type = in.readString();
  this.compact_status = in.readString();
  this.begin_compact_balance = in.readString();
  this.real_compact_balance = in.readString();
  this.real_compact_amount = in.readString();
  this.ret_end_date = in.readString();
  this.business_price = in.readString();
  this.market = in.readString();
  this.checked = in.readByte() != 0;
  this.can_checked = in.readByte() != 0;
 }

 public static final Creator<RChooseContractBean> CREATOR = new Creator<RChooseContractBean>() {
  @Override
  public RChooseContractBean createFromParcel(Parcel source) {
   return new RChooseContractBean(source);
  }

  @Override
  public RChooseContractBean[] newArray(int size) {
   return new RChooseContractBean[size];
  }
 };
}
