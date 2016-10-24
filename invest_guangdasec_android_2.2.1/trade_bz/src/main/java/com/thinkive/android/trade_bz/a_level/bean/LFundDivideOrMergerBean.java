package com.thinkive.android.trade_bz.a_level.bean;

import com.thinkive.android.trade_bz.a_stock.bean.BaseBean;
import com.thinkive.android.trade_bz.others.JsonKey;

/**
 * 查询分级基金 -- 最大可分拆或合并的数量(302056)
 * 基金 -- 分拆与合并（302055）
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/10/27.
 */
public class LFundDivideOrMergerBean extends BaseBean {
    /**
     *基金代码
     */
    @JsonKey("fund_code")
    private String fund_code="";
    /**
     * 基金名称
     */
    @JsonKey("fund_name")
    private String fund_name="";
    /**
     * 交易市场类别（见数据字典)
     */
    @JsonKey("exchange_type")
    private String exchange_type="";
    /**
     * 证券账号
     */
    @JsonKey("stock_account")
    private String stock_account="";
    /**
     * 基金最大可合并/分拆数量
     */
    @JsonKey("fund_max_amount")
    private String fund_max_amount="";

    /************************* 基金分支与合并  302055 ********************************/
    /**
     *委托编号
     */
    @JsonKey("entrust_no")
    private String entrust_no="";
    /**
     *委托批号
     */
    @JsonKey("batch_no")
    private String batch_no="";
    /**
     *申报编号
     */
    @JsonKey("report_no")
    private String report_no="";


    public LFundDivideOrMergerBean() {

    }

    public String getFund_code() {
        return fund_code;
    }

    public void setFund_code(String fund_code) {
        this.fund_code = fund_code;
    }

    public String getFund_name() {
        return fund_name;
    }

    public void setFund_name(String fund_name) {
        this.fund_name = fund_name;
    }

    public String getExchange_type() {
        return exchange_type;
    }

    public void setExchange_type(String exchange_type) {
        this.exchange_type = exchange_type;
    }

    public String getStock_account() {
        return stock_account;
    }

    public void setStock_account(String stock_account) {
        this.stock_account = stock_account;
    }

    public String getFund_max_amount() {
        return fund_max_amount;
    }

    public void setFund_max_amount(String fund_max_amount) {
        this.fund_max_amount = fund_max_amount;
    }

    public String getEntrust_no() {
        return entrust_no;
    }

    public void setEntrust_no(String entrust_no) {
        this.entrust_no = entrust_no;
    }

    public String getBatch_no() {
        return batch_no;
    }

    public void setBatch_no(String batch_no) {
        this.batch_no = batch_no;
    }

    public String getReport_no() {
        return report_no;
    }

    public void setReport_no(String report_no) {
        this.report_no = report_no;
    }
}
