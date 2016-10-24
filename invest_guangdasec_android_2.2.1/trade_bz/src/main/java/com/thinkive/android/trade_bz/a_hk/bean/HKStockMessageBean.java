package com.thinkive.android.trade_bz.a_hk.bean;

import com.thinkive.android.trade_bz.a_stock.bean.BaseBean;
import com.thinkive.android.trade_bz.others.JsonKey;

/**
 *  港股通 联动查询（ HQ21007）
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/11/18
 */
public class HKStockMessageBean extends BaseBean {
    /**
     * 买一
     */
    @JsonKey("BuyPrice1")
    private String buy_price = "";
    private int buyColor;
    /**
     *卖一
     */
    @JsonKey("SellPrice1")
    private String sell_price = "";
    private int sellColor;
    /**
     *名称
     */
    @JsonKey("Symbol")
    private String name = "";
    /**
     *代码
     */
    @JsonKey("SecurityID")
    private String code = "";
    /**
     *买一量
     */
    @JsonKey("BuyVolume1")
    private String buy_num = "";
    /**
     *卖一量
     */
    @JsonKey("SellVolume1")
    private String sell_num = "";
    /**
     *是否停牌
     */
    @JsonKey("issuspend")
    private String is_stop = "";
    /**
     *每手量
     */
    @JsonKey("RoundLot")
    private String one_unit_num = "";
    /**
     *昨收
     */
    @JsonKey("TradePrice")
    private String now_price = "";

    public HKStockMessageBean() {

    }

    public String getBuy_price() {
        return buy_price;
    }

    public void setBuy_price(String buy_price) {
        this.buy_price = buy_price;
    }

    public String getOne_unit_num() {
        return one_unit_num;
    }

    public void setOne_unit_num(String one_unit_num) {
        this.one_unit_num = one_unit_num;
    }

    public String getSell_price() {
        return sell_price;
    }

    public void setSell_price(String sell_price) {
        this.sell_price = sell_price;
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

    public String getBuy_num() {
        return buy_num;
    }

    public void setBuy_num(String buy_num) {
        this.buy_num = buy_num;
    }

    public String getSell_num() {
        return sell_num;
    }

    public void setSell_num(String sell_num) {
        this.sell_num = sell_num;
    }

    public String getIs_stop() {
        return is_stop;
    }

    public void setIs_stop(String is_stop) {
        this.is_stop = is_stop;
    }

    public String getNow_price() {
        return now_price;
    }

    public void setNow_price(String now_price) {
        this.now_price = now_price;
    }

    public int getBuyColor() {
        return buyColor;
    }

    public void setBuyColor(int buyColor) {
        this.buyColor = buyColor;
    }

    public int getSellColor() {
        return sellColor;
    }

    public void setSellColor(int sellColor) {
        this.sellColor = sellColor;
    }
}
