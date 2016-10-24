package com.android.thinkive.invest_sd.constants;

/**
 * <p>
 * 描述：静态常量
 * </p>
 *
 * @author 黎丝军
 * @version 1.0
 * @corporation 深圳市思迪信息科技有限公司
 * @date 2015/3/17
 */
public class Constant {
    //缓存key
    /**
     * 记住普通登陆账号的账号key
     */
    public static final String REMBER_USERNAME = "REMBER_USERNAME";

    /**
     * 启动页保存的key
     */
    public static final String LAUNCHER_PIC = "launcher_pic.jpg";


    /**
     * 记住E账通登陆账号的账号key
     */
    public static final String REMBER_USERNAME_EZT = "REMBER_USERNAME_EZT";
    //主页底部菜单
    /**
     * 掌上富尊模块名
     */
    public static final String MODEL_HAND_FUND = "hand_fund";
    /**
     * 行情资讯模块名
     */
    public static final String MODEL_PRICE_NEWS = "price_news";
    /**
     * 常用功能模块名
     */
    public static final String MODEL_OFTEN_FUNC = "often_func";
    /**
     * 业务开通模块名
     */
    public static final String MODEL_BUSINESS_OPEN = "business_open";

    //模块名
    /**
     * 主页模块名
     */
    public static final String MODEL_HOME = "home";
    /**
     * 我的富尊模块
     */
    public static final String MODEL_USER_CENTER = "user-center";
    /**
     * 登录模块名
     */
    public static final String MODEL_LOGIN = "login";
    /**
     * 左菜单模块名
     */
    public static final String MODEL_LEFT_MENU = "left-menu";
    /**
     * 开户模块名
     */
    public static final String MODEL_OPEN_ACCOUNT = "open-account";
    /**
     * 模块开户首页模块名
     */
    public static final String MODEL_OPEN_ACCOUNT_HOME = "open-account-home";
    /**
     * 商城模块名
     */
    public static final String MODEL_FINANCIAL_MALL = "financial-mall";
    /**
     * 交易模块名
     */
    public static final String MODEL_TRADE_MALL = "trade";
    /**
     * 融资模块名
     */
    public static final String MODEL_FUND_LOAN = "fund-loan";
    /**
     * 我的融资
     */
    public static final String MODEL_MY_FINANCING = "my-financing";
    /**
     * 我的产品模块名
     */
    public static final String MODEL_PRODUCT = "my-product";
    /**
     * 我的持仓模块名
     */
    public static final String MODEL_MY_HOLD_STORAGE = "my-hold-storage";
    /**
     * 两融开户模块名
     */
    public static final String MODEL_TWO_MELT_OPEN = "two-melt-open";
    /**
     * 港股通
     */
    public static final String MODEL_GGT = "ggt";


    //关于登录后的信息存储键名
    /**
     * 资金账号登录存信息储键名
     */
    public static final String RESULTS_OF_ACCOUNT = "resultsOfAccount";
    /**
     * 阳光E账通登录存储信息键名
     */
    public static final String RESULTS_OF_ACCOUNT_E = "resultsOfAccountE";
    /**
     * 切换账号将改变的数据存入键名
     */
    public static final String SWITCH_ACCOUNT_RESULTS = "switchAccountResults";
    /**
     * 富尊token键名
     */
    public static final String APP_FZ_TOKEN = "APP_FZ";
    /**
     * 交易token键名
     */
    public static final String APP_TRADE_TOKEN = "APP_TRADE";
    /**
     * 商城token键名
     */
    public static final String APP_SC_TOKEN = "APP_SC";
    /**
     * 小戴token键名
     */
    public static final String APP_XD_TOKEN = "APP_XD";
    /**
     * 港股通token键名
     */
    public static final String APP_GGT_TOKEN = "APP_GGT";
    /**
     * 登录状态键名
     */
    public static final String LOGIN_STATE = "login_state";
    /**
     * 富尊token
     */
    public static final String TOKEN = "token";
    /**
     * 交易token
     */
    public static final String TRADE_TOKEN = "trade_token";
    /**
     * 商城token
     */
    public static final String MALL_TOKEN = "mall_token";
    /**
     * 小戴token
     */
    public static final String XDT_TOKEN = "xdt_token";
    /**
     * 港股通token
     */
    public static final String HGT_TOKEN  = "hgt_token";
    /**
     * 请求结果results数据
     */
    public static final String LOGIN_RESULTS = "results";
    /**
     * 跳转根地址
     */
    public static final String TO_PAGE_ROOT_URL = "file:///android_asset/www/m";

    //缓存键值名，用于登录成功后，获取跳转地址
    /**
     * 缓存模块名
     */
    public static final String CACHE_MODULE_NAME = "moduleName";

    /**
     * 缓存是否行情点了交易买卖
     */
    public static final String is_privetotrade = "is_privetotrade";

    /**
     * 跳转也地址
     */
    public static final String TO_PAGE_URL = "toPageUrl";

    //消息ID
    /**
     * 模块被切换到后台的信息ID，
     * 此处用来传递登录后改变各个模块状态的信息ID
     */
    public static final String MSG_ID_10004 = "10004";
    /**
     * 隐藏和显示底部菜单的信息ID
     */
    public static final String MSG_ID_1002 = "1002";
    /**
     * 重置登录状态
     */
    public static final String MSG_ID_10006 = "10006";

    public final static String IS_ACCOUNT_ACTIVATE = "is_account_activate";
    public final static String MOBILE_NUMBER = "mobile_number";


    public final static String CACHA_FRISTPAGE_MENU = "CACHA_FRISTPAGE_MENU";

    public final static String CACHA_FRISTPAGE_MENU_EDIT = "CACHA_FRISTPAGE_MENU_EDIT";

    public final static String CACHA_FRISTPAGE_HEAD = "CACHA_FRISTPAGE_HEAD";

    public final static String CACHA_FRISTPAGE_REMMOEND = "CACHA_FRISTPAGE_REMMOEND";
    public final static String CACHA_FRISTPAGE_REMMOEND_LC = "CACHA_FRISTPAGE_REMMOEND_LC";
    public final static String CACHA_FRISTPAGE_HOTPRODUCT = "CACHA_FRISTPAGE_HOTPRODUCT";
    //登陆完成广播
    public final static String ACTION_LOGINEND= "com.broadcast.action.gdloginend";
}
