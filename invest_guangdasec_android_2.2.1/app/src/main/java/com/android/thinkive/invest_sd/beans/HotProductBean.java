package com.android.thinkive.invest_sd.beans;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
/**
 * Created by xiangfan on 2016/7/12.
 */

public class HotProductBean {
    public int getUi_type() {
        return ui_type;
    }

    public void setUi_type(int ui_type) {
        this.ui_type = ui_type;
    }

    /**
     * Auto-generated: 2016-07-12 16:19:42
     *
     * @author aTool.org (i@aTool.org)
     * @website http://www.atool.org/json2javabean.php
     */
    //用来区分界面上展示为具体产品还是产品分类栏  1为产品 2为分类标题
    private int ui_type;
    @JsonProperty("product_depict")
    private String productDepict;
    @JsonProperty("product_id")
    private String productId;
    @JsonProperty("sell_type")
    private int sellType;
    @JsonProperty("product_name")
    private String productName;
    @JsonProperty("product_link_type")
    private int productLinkType;
    @JsonProperty("est_yield")
    private String estYield;
    private String slogan;
    @JsonProperty("product_sell_date")
    private String productSellDate;
    @JsonProperty("product_profit")
    private String productProfit;
    @JsonProperty("assortment_id")
    private String assortmentId;
    private String assortment;
    @JsonProperty("product_code")
    private String productCode;
    @JsonProperty("sell_time")
    private String sellTime;
    @JsonProperty("est_type")
    private int estType;
    @JsonProperty("sort_id")
    private int sortId;
    public void setProductDepict(String productDepict) {
        this.productDepict = productDepict;
    }
    public String getProductDepict() {
        return productDepict;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
    public String getProductId() {
        return productId;
    }

    public void setSellType(int sellType) {
        this.sellType = sellType;
    }
    public int getSellType() {
        return sellType;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
    public String getProductName() {
        return productName;
    }

    public void setProductLinkType(int productLinkType) {
        this.productLinkType = productLinkType;
    }
    public int getProductLinkType() {
        return productLinkType;
    }

    public void setEstYield(String estYield) {
        this.estYield = estYield;
    }
    public String getEstYield() {
        return estYield;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }
    public String getSlogan() {
        return slogan;
    }

    public void setProductSellDate(String productSellDate) {
        this.productSellDate = productSellDate;
    }
    public String getProductSellDate() {
        return productSellDate;
    }

    public void setProductProfit(String productProfit) {
        this.productProfit = productProfit;
    }
    public String getProductProfit() {
        return productProfit;
    }

    public void setAssortmentId(String assortmentId) {
        this.assortmentId = assortmentId;
    }
    public String getAssortmentId() {
        return assortmentId;
    }

    public void setAssortment(String assortment) {
        this.assortment = assortment;
    }
    public String getAssortment() {
        return assortment;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }
    public String getProductCode() {
        return productCode;
    }

    public void setSellTime(String sellTime) {
        this.sellTime = sellTime;
    }
    public String getSellTime() {
        return sellTime;
    }

    public void setEstType(int estType) {
        this.estType = estType;
    }
    public int getEstType() {
        return estType;
    }

    public void setSortId(int sortId) {
        this.sortId = sortId;
    }
    public int getSortId() {
        return sortId;
    }

}
