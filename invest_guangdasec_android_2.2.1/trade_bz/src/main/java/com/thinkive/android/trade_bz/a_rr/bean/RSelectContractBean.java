package com.thinkive.android.trade_bz.a_rr.bean;

import com.thinkive.android.trade_bz.a_stock.bean.BaseBean;
import com.thinkive.android.trade_bz.others.JsonKey;

/**
 * 融资融券--查询--合约查询（303035）
 * 客户融资融券未平仓合约查询（已了结 和未了结查询）
 * Announcements：
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/8/18
 */
public class RSelectContractBean extends BaseBean {
  /**
   *合约编号
   */
  @JsonKey("compact_id")
  private String compact_id="";
  /**
   *资金账号
   */
  @JsonKey("fund_account")
  private String fund_account="";
  /**
   *币种类别
   */
  @JsonKey("money_type")
  private String money_type="";
  /**
   *币种类别名称
   */
  @JsonKey("money_type_name")
  private String money_type_name="";
  /**
   *  交易类别
   */
  @JsonKey("exchange_type")
  private String exchange_type="";
  /**
   *交易类别名称
   */
  @JsonKey("exchange_type_name")
  private String exchange_type_name="";
  /**
   *  市场
   */
  @JsonKey("market")
  private String market="";
  /**
   *证券账号
   */
  @JsonKey("stock_account")
  private String stock_account="";
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
   * 融资融券保证金比例
   */
  @JsonKey("crdt_ratio")
  private String crdt_ratio="";
  /**
   *委托编号
   */
  @JsonKey("entrust_no")
  private String entrust_no="";
  /**
   *委托价格
   */
  @JsonKey("entrust_price")
  private String entrust_price="";
  /**
   *委托数量
   */
  @JsonKey("entrust_amount")
  private String entrust_amount="";
  /**
   *合约开仓金额
   */
  @JsonKey("business_balance")
  private String business_balance="";
  /**
   *合约开仓费用
   */
  @JsonKey("business_fare")
  private String business_fare="";
  /**
   *合约类型(0-融资，1-融券)
   */
  @JsonKey("compact_type")
  private String compact_type="";
  /**
   * 合约状态
   */
  @JsonKey("compact_status")
  private String compact_status="";
  /**
   *期初合约数量
   */
  @JsonKey("begin_compact_amount")
  private String begin_compact_amount="";
  /**
   *期初合约金额
   */
  @JsonKey("begin_compact_balance")
  private String begin_compact_balance="";
  /**
   * 期初合约费用
   */
  @JsonKey("begin_compact_fare")
  private String begin_compact_fare="";
  /**
   *未还合约金额
   */
  @JsonKey("real_compact_balance")
  private String real_compact_balance="";
  /**
   * 未还合约数量
   */
  @JsonKey("real_compact_amount")
  private String real_compact_amount="";
  /**
   *未还合约费用
   */
  @JsonKey("real_compact_fare")
  private String real_compact_fare="";
  /**
   *未还合约利息
   */
  @JsonKey("real_compact_interest")
  private String real_compact_interest="";
  /**
   *已还利息
   */
  @JsonKey("repaid_interest")
  private String repaid_interest="";
  /**
   *已还数量
   */
  @JsonKey("repaid_amount")
  private String repaid_amount="";
  /**
   *已还金额
   */
  @JsonKey("repaid_balance")
  private String repaid_balance="";
  /**
   * 合约总利息
   */
  @JsonKey("compact_interest")
  private String compact_interest="";
  /**
   *占用保证金
   */
  @JsonKey("used_bail_balance")
  private String used_bail_balance="";
  /**
   * 合约年利率
   */
  @JsonKey("year_rate")
  private String year_rate="";
  /**
   *归还截止日
   */
  @JsonKey("ret_end_date")
  private String ret_end_date="";
  /**
   *了结日期
   */
  @JsonKey("date_clear")
  private String date_clear="";
  /**
   *  融资盈亏
   */
  @JsonKey("fin_income")
  private String fin_income="";
  /**
   * 融券盈亏
   */
  @JsonKey("slo_income")
  private String slo_income="";
  /**
   *续签到期日期
   */
  @JsonKey("postpone_end_date")
  private String postpone_end_date="";
  /**
   *   总负债
   */
  @JsonKey("total_debit")
  private String total_debit="";
  /**
   *委托日期
   */
  @JsonKey("open_date")
  private String open_date ="";
  /**
   * 负债截止日期
   */
  @JsonKey("end_date")
  private String end_date ="";
  /**
   * 证券代码
   */
  @JsonKey("code")
  private String code ="";
  /**
   *证券名称
   */
  @JsonKey("name")
  private String name ="";
  /**
   * 融资融券方向((0-融资，1-融券))
   */
  @JsonKey("type")
  private String type ="";
  @JsonKey("type_name")
  private String type_name ="";
  /**
   * 合约状态(0-未偿还 1-已偿还 2-到期未平仓)
   */
  @JsonKey("status")
  private String status ="";
  @JsonKey("status_name")
  private String status_name ="";
  /**
   *委托数量
   */
  @JsonKey("amount")
  private String amount="";
  /**
   * 委托价格
   */
  @JsonKey("price")
  private String price ="";
  /**
   *成交数量
   */
  @JsonKey("business_amount")
  private String business_amount ="";
  /**
   * 成交价格
   */
  @JsonKey("business_price")
  private String business_price ="";
  /**
   *融资融券息、费
   */
  @JsonKey("fee")
  private String fee ="";
  /**
   * 逾期未偿还息、费
   */
  @JsonKey("overduefee")
  private String overduefee ="";
  /**
   *利息产生的罚息
   */
  @JsonKey("punifee")
  private String punifee ="";
  /**
   *未还罚息
   */
  @JsonKey("punidebts")
  private String punidebts ="";
  /**
   * 己偿还罚息
   */
  @JsonKey("punifee_repay")
  private String punifee_repay ="";
  /**
   *逾期息费罚息
   */
  @JsonKey("punifeeunfrz")
  private String punifeeunfrz ="";
  /**
   *  T日归还金额
   */
  @JsonKey("creditrepayunfrz")
  private String creditrepayunfrz ="";
  /**
   *T日归还数量
   */
  @JsonKey("stkrepayunfrz")
  private String stkrepayunfrz ="";
  /**
   *合约编号
   */
  @JsonKey("sno")
  private String sno ="";
  /**
   *成交金额
   */
  @JsonKey("business_money")
  private String business_money ="";
  /**
   * 清算金额
   */
  @JsonKey("clearing_money")
  private String clearing_money ="";
  /**
   *合约盈亏
   */
  @JsonKey("contractProfit")
  private String contractProfit ="";
  /**
   * 未还权益补偿金额/数量
   */
  @JsonKey("equity_not")
  private String equity_not ="";
  /**
   * 逾期未偿还权益/数量
   */
  @JsonKey("equity_has")
  private String equity_has ="";
  /**
   * 未还本金
   */
  @JsonKey("fundremain")
  private String fundremain="";

  public RSelectContractBean() {
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

  public String getCrdt_ratio() {
    return crdt_ratio;
  }

  public void setCrdt_ratio(String crdt_ratio) {
    this.crdt_ratio = crdt_ratio;
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

  public String getBusiness_balance() {
    return business_balance;
  }

  public void setBusiness_balance(String business_balance) {
    this.business_balance = business_balance;
  }

  public String getBusiness_fare() {
    return business_fare;
  }

  public void setBusiness_fare(String business_fare) {
    this.business_fare = business_fare;
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

  public String getBegin_compact_amount() {
    return begin_compact_amount;
  }

  public void setBegin_compact_amount(String begin_compact_amount) {
    this.begin_compact_amount = begin_compact_amount;
  }

  public String getBegin_compact_balance() {
    return begin_compact_balance;
  }

  public void setBegin_compact_balance(String begin_compact_balance) {
    this.begin_compact_balance = begin_compact_balance;
  }

  public String getBegin_compact_fare() {
    return begin_compact_fare;
  }

  public void setBegin_compact_fare(String begin_compact_fare) {
    this.begin_compact_fare = begin_compact_fare;
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

  public String getReal_compact_fare() {
    return real_compact_fare;
  }

  public void setReal_compact_fare(String real_compact_fare) {
    this.real_compact_fare = real_compact_fare;
  }

  public String getReal_compact_interest() {
    return real_compact_interest;
  }

  public void setReal_compact_interest(String real_compact_interest) {
    this.real_compact_interest = real_compact_interest;
  }

  public String getRepaid_interest() {
    return repaid_interest;
  }

  public void setRepaid_interest(String repaid_interest) {
    this.repaid_interest = repaid_interest;
  }

  public String getRepaid_amount() {
    return repaid_amount;
  }

  public void setRepaid_amount(String repaid_amount) {
    this.repaid_amount = repaid_amount;
  }

  public String getRepaid_balance() {
    return repaid_balance;
  }

  public void setRepaid_balance(String repaid_balance) {
    this.repaid_balance = repaid_balance;
  }

  public String getCompact_interest() {
    return compact_interest;
  }

  public void setCompact_interest(String compact_interest) {
    this.compact_interest = compact_interest;
  }

  public String getUsed_bail_balance() {
    return used_bail_balance;
  }

  public void setUsed_bail_balance(String used_bail_balance) {
    this.used_bail_balance = used_bail_balance;
  }

  public String getYear_rate() {
    return year_rate;
  }

  public void setYear_rate(String year_rate) {
    this.year_rate = year_rate;
  }

  public String getRet_end_date() {
    return ret_end_date;
  }

  public void setRet_end_date(String ret_end_date) {
    this.ret_end_date = ret_end_date;
  }

  public String getDate_clear() {
    return date_clear;
  }

  public void setDate_clear(String date_clear) {
    this.date_clear = date_clear;
  }

  public String getFin_income() {
    return fin_income;
  }

  public void setFin_income(String fin_income) {
    this.fin_income = fin_income;
  }

  public String getSlo_income() {
    return slo_income;
  }

  public void setSlo_income(String slo_income) {
    this.slo_income = slo_income;
  }

  public String getPostpone_end_date() {
    return postpone_end_date;
  }

  public void setPostpone_end_date(String postpone_end_date) {
    this.postpone_end_date = postpone_end_date;
  }

  public String getTotal_debit() {
    return total_debit;
  }

  public void setTotal_debit(String total_debit) {
    this.total_debit = total_debit;
  }

  public String getopen_date() {
    return open_date;
  }

  public void setopen_date(String open_date) {
    this.open_date = open_date;
  }

  public String getend_date() {
    return end_date;
  }

  public void setend_date(String end_date) {
    this.end_date = end_date;
  }

  public String getcode() {
    return code;
  }

  public void setcode(String code) {
    this.code = code;
  }

  public String getname() {
    return name;
  }

  public void setname(String name) {
    this.name = name;
  }

  public String gettype() {
    return type;
  }

  public void settype(String type) {
    this.type = type;
  }

  public String getstatus() {
    return status;
  }

  public void setstatus(String status) {
    this.status = status;
  }

  public String getAmount() {
    return amount;
  }

  public void setAmount(String amount) {
    this.amount = amount;
  }

  public String getprice() {
    return price;
  }

  public void setprice(String price) {
    this.price = price;
  }

  public String getbusiness_amount() {
    return business_amount;
  }

  public void setbusiness_amount(String business_amount) {
    this.business_amount = business_amount;
  }

  public String getbusiness_price() {
    return business_price;
  }

  public void setbusiness_price(String business_price) {
    this.business_price = business_price;
  }

  public String getfee() {
    return fee;
  }

  public void setfee(String fee) {
    this.fee = fee;
  }

  public String getoverduefee() {
    return overduefee;
  }

  public void setoverduefee(String overduefee) {
    this.overduefee = overduefee;
  }

  public String getpunifee() {
    return punifee;
  }

  public void setpunifee(String punifee) {
    this.punifee = punifee;
  }

  public String getpunidebts() {
    return punidebts;
  }

  public void setpunidebts(String punidebts) {
    this.punidebts = punidebts;
  }

  public String getpunifee_repay() {
    return punifee_repay;
  }

  public void setpunifee_repay(String punifee_repay) {
    this.punifee_repay = punifee_repay;
  }

  public String getpunifeeunfrz() {
    return punifeeunfrz;
  }

  public void setpunifeeunfrz(String punifeeunfrz) {
    this.punifeeunfrz = punifeeunfrz;
  }

  public String getcreditrepayunfrz() {
    return creditrepayunfrz;
  }

  public void setcreditrepayunfrz(String creditrepayunfrz) {
    this.creditrepayunfrz = creditrepayunfrz;
  }

  public String getstkrepayunfrz() {
    return stkrepayunfrz;
  }

  public void setstkrepayunfrz(String stkrepayunfrz) {
    this.stkrepayunfrz = stkrepayunfrz;
  }

  public String getsno() {
    return sno;
  }

  public void setsno(String sno) {
    this.sno = sno;
  }

  public String getbusiness_money() {
    return business_money;
  }

  public void setbusiness_money(String business_money) {
    this.business_money = business_money;
  }

  public String getclearing_money() {
    return clearing_money;
  }

  public void setclearing_money(String clearing_money) {
    this.clearing_money = clearing_money;
  }

  public String getcontractProfit() {
    return contractProfit;
  }

  public void setcontractProfit(String contractProfit) {
    this.contractProfit = contractProfit;
  }

  public String getequity_not() {
    return equity_not;
  }

  public void setequity_not(String equity_not) {
    this.equity_not = equity_not;
  }

  public String getequity_has() {
    return equity_has;
  }

  public void setequity_has(String equity_has) {
    this.equity_has = equity_has;
  }

  public String getfundremain() {
    return fundremain;
  }

  public void setfundremain(String fundremain) {
    this.fundremain = fundremain;
  }

  public String getMarket() {
    return market;
  }

  public void setMarket(String market) {
    this.market = market;
  }

  public String getStatus_name() {
    return status_name;
  }

  public void setStatus_name(String status_name) {
    this.status_name = status_name;
  }

  public String getType_name() {
    return type_name;
  }

  public void setType_name(String type_name) {
    this.type_name = type_name;
  }
}
