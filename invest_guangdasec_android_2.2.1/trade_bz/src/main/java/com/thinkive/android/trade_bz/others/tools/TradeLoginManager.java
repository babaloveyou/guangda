package com.thinkive.android.trade_bz.others.tools;

import android.content.Context;

import com.android.thinkive.framework.CoreApplication;
import com.android.thinkive.framework.message.AppMessage;
import com.android.thinkive.framework.message.MessageManager;
import com.android.thinkive.framework.util.Constant;
import com.thinkive.android.trade_bz.a_stock.bean.UserInfo;
import com.thinkive.android.trade_bz.others.constants.Constants;
import com.thinkive.android.trade_bz.utils.TradeUtils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @Created by   Huangzq
 * @on 2016/6/12 11:07.
 * @des 交易登陆的管理类
 * 主要用于用户信息存储
 * 交易登录相关常量配置
 * 及一些静态方法等
 */
public class TradeLoginManager {
    /**
     * 全局上下文
     */
    private final Context mContext;
    /**
     * 是否走统一登陆
     */
    public static final boolean    IF_UNITY_LOGIN           = false;
    /**
     * 是否已经在两融中登录普通交易的标志
     */
    public static       boolean    sIsNormalLogin_in_credit = false;
    /**
     * op_station的值格式为如下：通道编号|IP地址|MAC地址|硬盘序列号|CPU序列号|手机号码|硬件特征码|来源|其他
     * 来源的定义为（0：腾讯，1：新浪，2：百度）
     */
    public static       String     OP_STATION_2             = TradeUtils.setOpStation2(CoreApplication.getInstance());//由TradeUtils.setOpStation2();方法设置
    /**
     * 委托方式
     */
    public static final String     ENTRUST_WAY              = "5";
    /**
     * 普通交易用户信息
     */
    public static       UserInfo   sNormalUserInfo          = new UserInfo();
    public static       JSONObject sNormalUserInfo_json     = new JSONObject();
    /**
     * 深A
     */
    public static       UserInfo   sNormalUserInfo_shen_A   = new UserInfo();
    /**
     * 沪A
     */
    public static       UserInfo   sNormalUserInfo_hu_A     = new UserInfo();
    /**
     * 深B
     */
    public static       UserInfo   sNormalUserInfo_shen_B   = new UserInfo();
    /**
     * 沪B
     */
    public static       UserInfo   sNormalUserInfo_hu_B     = new UserInfo();
    /**
     * 新三板
     */
    public static       UserInfo   sNormalUserInfo_three    = new UserInfo();
    /**
     * 港股
     */
    public static       UserInfo   sNormalUserInfo_hk       = new UserInfo();

    //--------------------------------个股期权用户信息--------------------------------------
    /**
     * 个股期权用户信息
     */
    public static UserInfo   sOptionUserInfo      = new UserInfo();
    public static JSONObject sOptionUserInfo_json = new JSONObject();
    /**
     * 个股期权深市
     */
    public static UserInfo   sOptionUserInfo_shen = new UserInfo();
    /**
     * 个股期权沪市
     */
    public static UserInfo   sOptionUserInfo_hu   = new UserInfo();

    //--------------------------------两融用户信息--------------------------------------

    /**
     * 信用交易用户信息
     */
    public static UserInfo   sCreditUserInfo           = new UserInfo();
    public static JSONObject sCreditUserInfo_json      = new JSONObject();
    /**
     * 信用深市
     */
    public static UserInfo   sCreditUserInfo_shen      = new UserInfo();
    /**
     * 信用沪市
     */
    public static UserInfo   sCreditUserInfo_hu        = new UserInfo();
    /**
     * 保持与信用账户客户号一致的普通交易资金账号用户信息
     */
    public static UserInfo   sNormalUserInfo_in_credit = new UserInfo();

    //---------------------------------------账户类型------------------------------------
    /**
     * 登录账号类型：资金账号
     */
    public static final String LOGIN_TYPE_FUND_ACCOUNT        = "201";
    public static final String LOGIN_TYPE_FUND_ACCOUNT_TEXT   = "资金账号";
    /**
     * 登录账号类型：资金账号
     */
    public static final String LOGIN_TYPE_CREDIT_ACCOUNT      = "202";
    public static final String LOGIN_TYPE_CREDIT_ACCOUNT_TEXT = "信用账号";
    /**
     * 登录账号类型：资金账号
     */
    public static final String LOGIN_TYPE_OPTION_ACCOUNT      = "204";
    public static final String LOGIN_TYPE_OPTION_ACCOUNT_TEXT = "个股期权";
    /**
     * 登录账号类型：资金账号
     */
    public static final String LOGIN_TYPE_HU_A_ACCOUNT        = "207";
    public static final String LOGIN_TYPE_HU_A_ACCOUNT_TEXT   = "沪A账号";
    /**
     * 登录账号类型：资金账号
     */
    public static final String LOGIN_TYPE_SHEN_A_ACCOUNT      = "208";
    public static final String LOGIN_TYPE_SHEN_A_ACCOUNT_TEXT = "深A账号";
    /**
     * 登录账号类型：资金账号
     */
    public static final String LOGIN_TYPE_HU_B_ACCOUNT        = "209";
    public static final String LOGIN_TYPE_HU_B_ACCOUNT_TEXT   = "沪B账号";
    /**
     * 登录账号类型：资金账号
     */
    public static final String LOGIN_TYPE_SHEN_B_ACCOUNT      = "210";
    public static final String LOGIN_TYPE_SHEN_B_ACCOUNT_TEXT = "深B账号";
    /**
     * 普通账户登录标识值
     */
    public static final String LOGIN_TYPE_NORMAL              = "0";
    /**
     * 融资融券登录标识值
     */
    public static final String LOGIN_TYPE_CREDIT              = "1";
    /**
     * 个股期权登录标识值
     */
    public static final String LOGIN_TYPE_OPTION              = "2";
    /**
     * 在融资融券中登录普通交易
     */
    public static final String LOGIN_TYPE_NORMAL_IN_CREDIT    = "3";
    /**
     * 普通交易账户类型   默认资金账号
     */
    public static       String sNormalLoginType               = LOGIN_TYPE_FUND_ACCOUNT;
    /**
     * 信用账户类型
     */
    public static       String sCreditLoginType               = LOGIN_TYPE_CREDIT_ACCOUNT;
    /**
     * 个股期权账户类型
     */
    public static       String sOptionLoginType               = LOGIN_TYPE_OPTION_ACCOUNT;
    /**
     * 普通交易登陆账号
     */
    public static String sNormalLoginAccount;
    /**
     * 信用交易账号
     */
    public static String sCreditLoginAccount;
    /**
     * 个股期权账号
     */
    public static String sOptionLoginAccount;


    private TradeLoginManager() {
        mContext = CoreApplication.getInstance().getApplicationContext();
    }

    public static TradeLoginManager getInstance() {
        return TradeLoginManagerHolder.sInstance;
    }

    private static class TradeLoginManagerHolder {
        private static final TradeLoginManager sInstance = new TradeLoginManager();
    }

    /**
     * 清除信用账户信息
     * 移除登录成功标志位
     */
    public void clearCreditUserInfo() {
        TradeLoginManager.sCreditUserInfo = new UserInfo();
        TradeLoginManager.sCreditUserInfo_json = new JSONObject();
        TradeLoginManager.sCreditUserInfo_shen = new UserInfo();
        TradeLoginManager.sCreditUserInfo_hu = new UserInfo();
        //移除信用交易标志位
        TradeFlags.removeFlag(TradeFlags.FLAG_CREDIT_ACCOUNT_CHECK_SUCCESS);
        TradeFlags.removeFlag(TradeFlags.FLAG_GET_CREDIT_USERINFO_SUCCESS);
    }

    /**
     * 清除个股期权账户信息
     * 移除登录成功标志位
     */
    public void clearOptionUserInfo() {
        TradeLoginManager.sOptionUserInfo = new UserInfo();
        TradeLoginManager.sOptionUserInfo_json = new JSONObject();
        TradeLoginManager.sOptionUserInfo_shen = new UserInfo();
        TradeLoginManager.sOptionUserInfo_hu = new UserInfo();
        //移除个股期权标志位
        TradeFlags.removeFlag(TradeFlags.FLAG_OPTION_ACCOUNT_CHECK_SUCCESS);
        TradeFlags.removeFlag(TradeFlags.FLAG_GET_OPTION_USERINFO_SUCCESS);
    }

    /**
     * 清除普通用户的数据信息
     * 移除登录成功标志位
     */
    public void clearNormalUserInfo() {
        TradeLoginManager.sNormalUserInfo = new UserInfo();
        TradeLoginManager.sNormalUserInfo_json = new JSONObject();
        TradeLoginManager.sNormalUserInfo_hu_A = new UserInfo();
        TradeLoginManager.sNormalUserInfo_hu_B = new UserInfo();
        TradeLoginManager.sNormalUserInfo_shen_A = new UserInfo();
        TradeLoginManager.sNormalUserInfo_shen_B = new UserInfo();
        TradeLoginManager.sNormalUserInfo_hk = new UserInfo();
        TradeLoginManager.sNormalUserInfo_three = new UserInfo();
        //移除普通交易标志位
        TradeFlags.removeFlag(TradeFlags.FLAG_NORMAL_ACCOUNT_CHECK_SUCCESS);
        TradeFlags.removeFlag(TradeFlags.FLAG_GET_NORMAL_USERINFO_SUCCESS);
    }

    /**
     * 重构token
     */
    public void reGetToken() {
        try {
            JSONObject content = new JSONObject();
            content.put(Constant.MODULE_NAME, Constants.MODULE_NAME_TRADE);
            AppMessage appMessage = new AppMessage(60402, content);
            MessageManager.getInstance(mContext).sendMessage(appMessage);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据账户类型获取用户信息
     *
     * @param userType 用户类型  两融或普通交易 以0  1区分
     * @return 用户信息
     */
    public UserInfo getUserInfoByAcctType(String userType) {
        UserInfo userInfo = null;
        if (LOGIN_TYPE_CREDIT.equals(userType)) {//信用交易
            switch (sCreditLoginType) {
                case LOGIN_TYPE_CREDIT_ACCOUNT:
                    userInfo = sCreditUserInfo;
                    break;
                default:
                    userInfo = sCreditUserInfo;
                    break;
            }
        } else if (LOGIN_TYPE_OPTION.equals(userType)) {//个股期权
            switch (sOptionLoginType) {
                case LOGIN_TYPE_OPTION_ACCOUNT:
                    userInfo = sOptionUserInfo;
                    break;
                default:
                    userInfo = sOptionUserInfo;
                    break;
            }
        } else if (LOGIN_TYPE_NORMAL.equals(userType)) {//普通交易
            switch (sNormalLoginType) {
                case LOGIN_TYPE_HU_A_ACCOUNT: //沪A
                    userInfo = sNormalUserInfo_hu_A;
                    break;
                case LOGIN_TYPE_HU_B_ACCOUNT: //沪B
                    userInfo = sNormalUserInfo_hu_B;
                    break;
                case LOGIN_TYPE_SHEN_A_ACCOUNT: //深A
                    userInfo = sNormalUserInfo_shen_A;
                    break;
                case LOGIN_TYPE_SHEN_B_ACCOUNT: //深B
                    userInfo = sNormalUserInfo_shen_B;
                    break;
                default:   //资金账号或默认情况
                    userInfo = sNormalUserInfo;
                    break;
            }
        }
        return userInfo;
    }

    /**
     * 重载{@link #getUserInfoByAcctType(String)}方法
     * 作为默认情况  默认普通交易
     *
     * @return 返回普通交易的userInfo
     */
    public UserInfo getUserInfoByAcctType() {
        return getUserInfoByAcctType(LOGIN_TYPE_NORMAL);
    }

    /**
     * 将账户类型转成账户类型名称
     *
     * @param acctType 账户类型
     * @return 账户类型名称
     */
    public static String transferAcctTypeText(String acctType) {
        switch (acctType) {
            case LOGIN_TYPE_FUND_ACCOUNT:
                return LOGIN_TYPE_FUND_ACCOUNT_TEXT;
            case LOGIN_TYPE_HU_A_ACCOUNT:
                return LOGIN_TYPE_HU_A_ACCOUNT_TEXT;
            case LOGIN_TYPE_HU_B_ACCOUNT:
                return LOGIN_TYPE_HU_B_ACCOUNT_TEXT;
            case LOGIN_TYPE_SHEN_A_ACCOUNT:
                return LOGIN_TYPE_SHEN_A_ACCOUNT_TEXT;
            case LOGIN_TYPE_SHEN_B_ACCOUNT:
                return LOGIN_TYPE_SHEN_B_ACCOUNT_TEXT;
            case LOGIN_TYPE_CREDIT_ACCOUNT:
                return LOGIN_TYPE_CREDIT_ACCOUNT_TEXT;
            case LOGIN_TYPE_OPTION_ACCOUNT:
                return LOGIN_TYPE_OPTION_ACCOUNT_TEXT;
            default:
                return LOGIN_TYPE_FUND_ACCOUNT_TEXT;
        }
    }

    /**
     * 存储账号及账户类型
     * @param account 账号
     * @param acctType 账户类型
     */
    public void saveAccountAndType(String account, String acctType) {
        switch (acctType) {
            case LOGIN_TYPE_CREDIT_ACCOUNT://信用账号
                sCreditLoginType = acctType;
                sCreditLoginAccount = account;
                break;
            case LOGIN_TYPE_OPTION_ACCOUNT:// 个股期权
                sOptionLoginType = acctType;
                sOptionLoginAccount = account;
                break;
            default: // 普通交易
                sNormalLoginType = acctType;
                sNormalLoginAccount = account;
                break;
        }
    }
}
