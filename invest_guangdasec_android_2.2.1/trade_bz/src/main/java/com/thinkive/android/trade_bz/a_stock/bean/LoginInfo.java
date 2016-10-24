package com.thinkive.android.trade_bz.a_stock.bean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/18.
 */

public class LoginInfo {
    private static String username;
    private static String password;
    //登录之后返回的完整股东账号列表
    private static ArrayList<SignStockAccountLimitBean> stockLimitBeans;
    //默认账号 如果不选择默认设置为数据第一条
    private static String selectHolderAccount;
    //选择股票联动后筛选到的股东账号列表bean
    private static ArrayList<SignStockAccountLimitBean> filStockLimitBeans;

    public static ArrayList<SignStockAccountLimitBean> getFilStockLimitBeans() {
        return filStockLimitBeans;
    }

    public static void setFilStockLimitBeans(ArrayList<SignStockAccountLimitBean> filStockLimitBeans) {
        LoginInfo.filStockLimitBeans = filStockLimitBeans;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        LoginInfo.username = username;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        LoginInfo.password = password;
    }

    public static ArrayList<SignStockAccountLimitBean> getStockLimitBeans() {
        return stockLimitBeans;
    }
    public static void setStockLimitBeans(ArrayList<SignStockAccountLimitBean> beans) {
        LoginInfo.stockLimitBeans = beans;
        //登录后初始化筛选列表   以后直接操作此列表
        filStockLimitBeans = beans;
    }

    public static String getSelectHolderAccount() {
        return selectHolderAccount;
    }

    public static String setSelectHolderAccount(String accountString) {
        return  LoginInfo.selectHolderAccount = accountString;
    }
    public static void setSelectHolderAccount(ArrayList<SignStockAccountLimitBean> stockLimitBeans) {
//        //每次初始化都设置默认股东账号为适配器数据第一条
//        if (LoginInfo.stockLimitBeans.size() != 0) {
//            LoginInfo.selectHolderAccount = LoginInfo.stockLimitBeans.get(0).getStock_account();
//        }
    }
    public static  ArrayList<String> getAccountStringList() {
        ArrayList<String> list = new ArrayList<>();
        for (SignStockAccountLimitBean bean : stockLimitBeans) {
            list.add(bean.getStock_account());
        }
        return list;
    }
}
