package com.thinkive.android.trade_bz.a_stock.bean;

import com.thinkive.android.trade_bz.others.JsonKey;

/**
 *  当功能号只返回以下两字段时使用
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/10/28.
 */
public class PublicUseBean extends BaseBean {
    /**
     *流水号
     */
    @JsonKey("serial_no")
    private String serial_no="";
    /**
     *交易日期
     */
    @JsonKey("init_date")
    private String init_date="";
    /**
     *委托编号
     */
    @JsonKey("entrust_no")
    private String entrust_no="";
    /**
     * 是否完成某任务（风险测评/是否开户）  0:没开过 1 开过
     */
    @JsonKey("flag")
    private String flag="";
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


    public PublicUseBean() {

    }

    public String getSerial_no() {
        return serial_no;
    }

    public void setSerial_no(String serial_no) {
        this.serial_no = serial_no;
    }

    public String getInit_date() {
        return init_date;
    }

    public void setInit_date(String init_date) {
        this.init_date = init_date;
    }

    public String getEntrust_no() {
        return entrust_no;
    }

    public void setEntrust_no(String entrust_no) {
        this.entrust_no = entrust_no;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
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
