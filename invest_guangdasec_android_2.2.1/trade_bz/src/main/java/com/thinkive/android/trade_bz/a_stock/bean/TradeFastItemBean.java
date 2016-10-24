package com.thinkive.android.trade_bz.a_stock.bean;

/**
 * Created by Administrator on 2016/10/10.
 */
public class TradeFastItemBean {
    private int imageRes;
    private String itemName;
    private String url;

    @Override
    public String toString() {
        return "TradeFastItemBean{" +
                "itemName='" + itemName + '\'' +
                ", imageRes=" + imageRes +
                ", url='" + url + '\'' +
                '}';
    }

    public int getImageRes() {
        return imageRes;
    }

    public void setImageRes(int imageRes) {
        this.imageRes = imageRes;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public TradeFastItemBean(int imageRes, String itemName, String url) {

        this.imageRes = imageRes;
        this.itemName = itemName;
        this.url = url;
    }
}
