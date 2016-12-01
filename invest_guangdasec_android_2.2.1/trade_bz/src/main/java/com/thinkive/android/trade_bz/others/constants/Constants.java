package com.thinkive.android.trade_bz.others.constants;

/**
 * <p>
 * 描述：全局常量类
 * </p>
 *
 * @author 黎丝军
 * @version 1.0
 * @corporation 深圳市思迪信息科技有限公司
 * @date 2015/6/3
 */
public class Constants {
    public static final String MODULE_NAME_TRADE = "trade";
    public static final String MODULE_NAME_ACCOUNT_VERIFY = "account_verify";
    public static final String MODULE_NAME_SSO = "sso";
    public static final String MODULE_NAME_HOME = "home";
    // 普通交易验证码保存

    public static final String SAVED_VERTIFYCATION = "saved_vertifycation";
    // 标记普通登录时，是否选择了记住账号的key
    public static final String IS_SAVE_NORMAL_ACCOUNT_KEY = "isSaveAccount";
    // 保存用户普通账号的key
    public static final String USER_NORMAL_ACCOUNT_KEY = "userAccount";
    // 标记融资融券登录时，是否选择了记住账号的key
    public static final String IS_SAVE_CREDIT_ACCOUNT_KEY = "isSaveCreditAccount";
    // 保存用户融资融券账号的key
    public static final String USER_CREDIT_ACCOUNT_KEY = "userCreditAccount";
    /**
     * 普通交易URL前缀
     */
    public static final String URL_TRADE = "URL_TRADE";
    /**
     * 行情URL前缀
     */
    public static final String URL_MARKET = "URL_MARKET";
    /**
     * 融资融券（信用）交易URL前缀
     */
    public static final String URL_CREDIT_TRADE = "CREDIT_URL_TRADE";
    /**
     * 普通交易URL前缀 验证码
     */
    public static final String URL_TRADE_CODE = "LOGIN_HTTP_VERIFY_CODE_URL";
    /**
     * 融资融券（信用）交易URL前缀 验证码
     */
    public static final String URL_CREDIT_TRADE_CODE = "CREDIT_LOGIN_HTTP_VERIFY_CODE_URL";

    public static final String LOGIN_TYPE = "login_type";
    public static final String NORMAL_LOGIN_USERINFO_FORH5 = "stock_userInfo";
    public static final String CREDIT_LOGIN_USERINFO_FORH5 = "credit_userInfo";
    public static final String TRADE_TYPE_KEY = "trade_type_key";
    public static final String TOH5PAGE = "toh5page";

    public static final String ACTION_TO_H5_PAGE = "com.toh5.broadcast.action";
    public static final String STOCK_CREDIT_FLAG = "stock_credit_flag";
    public static final String NORMAL_LOGIN_SESSIONID = "normal_login_sessionid";
    public static final String CREDIT_COOKIE_KEY = "credit_cookie_key";

    public static final int TIME_LIMIT = 7;
    public static String  TODAY_DATE = null;
    public static String  CREDIT_DOTAY_DATE = null;
    public static String HISTORY_START_DATE = null;
    public static String HISTORY_END_DATE = null;
}
