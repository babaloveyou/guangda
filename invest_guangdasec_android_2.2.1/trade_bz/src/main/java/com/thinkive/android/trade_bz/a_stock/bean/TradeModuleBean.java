package com.thinkive.android.trade_bz.a_stock.bean;

/**
 * 交易模块实体类
 * @author 王志鸿
 * @company Thinkive
 * @date 2015/8/13
 */
public class TradeModuleBean {

    private String value;
    private boolean usable;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isUsable() {
        return usable;
    }

    public void setUsable(boolean usable) {
        this.usable = usable;
    }
}
