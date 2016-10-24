package com.android.thinkive.invest_sd.beans;

/**
 * Created by xiangfan on 2015/11/2.
 */
public class LoginTokenBean  {
    /**
     * 交易token
     */

    private String trade_token;
    /**
     * 商城token
     */
    private String mall_token;
    /**
     * 小贷token
     */
    private String xdt_token;
    /**
     * 港股通token
     */
    private String  hgt_token;

    /**
     * 富尊token
     */
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTrade_token() {
        return trade_token;
    }

    public void setTrade_token(String trade_token) {
        this.trade_token = trade_token;
    }

    public String getMall_token() {
        return mall_token;
    }

    public void setMall_token(String mall_token) {
        this.mall_token = mall_token;
    }

    public String getXdt_token() {
        return xdt_token;
    }

    public void setXdt_token(String xdt_token) {
        this.xdt_token = xdt_token;
    }

    public String getHgt_token() {
        return hgt_token;
    }

    public void setHgt_token(String hgt_token) {
        this.hgt_token = hgt_token;
    }
}
