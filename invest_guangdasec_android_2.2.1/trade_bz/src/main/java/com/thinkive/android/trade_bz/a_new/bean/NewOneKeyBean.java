package com.thinkive.android.trade_bz.a_new.bean;

import com.thinkive.android.trade_bz.a_stock.bean.BaseBean;
import com.thinkive.android.trade_bz.others.JsonKey;

/**
 * 批量申购新股列表
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/8/12
 */

public class NewOneKeyBean extends BaseBean {
    @JsonKey("exchange_type")
    private String exchange_type = "";
    @JsonKey("stock_code")
    private String stock_code = "";
    @JsonKey("price")
    private String price = "";
    @JsonKey("stock_name")
    private String stock_name = "";
    @JsonKey("maxamount")
    private String maxamount = "";
    @JsonKey("stock_account")
    private String stock_account = "";
    /**
     * 设置当前bean是否被选中
     */
    private boolean isCheck = false;
    /**
     * 记录用户输入的申购数
     */
    private String input_num = "";

    public NewOneKeyBean() {

    }

    public String getExchange_type() {
        return exchange_type;
    }

    public void setExchange_type(String exchange_type) {
        this.exchange_type = exchange_type;
    }

    public String getStock_code() {
        return stock_code;
    }

    public void setStock_code(String stock_code) {
        this.stock_code = stock_code;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStock_name() {
        return stock_name;
    }

    public void setStock_name(String stock_name) {
        this.stock_name = stock_name;
    }

    public String getMaxamount() {
        return maxamount;
    }

    public void setMaxamount(String maxamount) {
        this.maxamount = maxamount;
    }

    public String getStock_account() {
        return stock_account;
    }

    public void setStock_account(String stock_account) {
        this.stock_account = stock_account;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public String getInput_num() {
        return input_num;
    }

    public void setInput_num(String input_num) {
        this.input_num = input_num;
    }
}
