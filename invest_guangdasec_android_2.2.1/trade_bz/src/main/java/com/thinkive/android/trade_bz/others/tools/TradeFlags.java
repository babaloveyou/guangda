package com.thinkive.android.trade_bz.others.tools;

import com.thinkive.android.trade_bz.utils.LogUtil;

/**
 * @Created by   Huangzq
 * @on 2016/5/11 9:26.
 * @des 交易状态Flags
 * 采用一个int值来设置所有的状态,每一位二进制表示一个开关  ox00000000
 * <p>
 * 注意: 基本标志位的定义只能使用2的n次方形式定义
 */
public class TradeFlags {
    /*----------------------  基本标志位  ----------------------*/

    //杂项  0x00000001~0x000000FF    预留8位
    /**
     * 手机号已登录的标志位
     */
    public static final int FLAG_PHONE_LOGIN            = 0x00000001;
    /**
     * 非统一账户登陆 默认统一账户0  交易单独登陆为1
     */
    public static final int FLAG_NOT_UNITY_LOGIN_TYPE   = 0x00000002;
    /**
     * 会话建立成功的标志位
     */
    public static final int FLAG_CREATE_SESSION_SUCCESS = 0x00000004;




    //账户校验  0x00000100~0x0000FF00   预留8位
    /**
     * 普通交易账户校验成功
     */
    public static final int FLAG_NORMAL_ACCOUNT_CHECK_SUCCESS = 0x00000100;
    /**
     * 信用账户校验成功
     */
    public static final int FLAG_CREDIT_ACCOUNT_CHECK_SUCCESS = 0x00000200;
    /**
     * 期权账户校验成功
     */
    public static final int FLAG_OPTION_ACCOUNT_CHECK_SUCCESS = 0x00000400;


    //用户信息  0x00010000~0x00FF0000   预留8位
    /**
     * 获取用户信息成功
     */
    public static final int FLAG_GET_NORMAL_USERINFO_SUCCESS = 0x00010000;
    /**
     * 获取用户信息成功
     */
    public static final int FLAG_GET_CREDIT_USERINFO_SUCCESS = 0x00020000;
    /**
     * 获取用户信息成功
     */
    public static final int FLAG_GET_OPTION_USERINFO_SUCCESS = 0x00040000;
    /*----------------------  基本标志位  ----------------------*/

    /*----------------------  复合标志位  ----------------------*/
    /**
     * 普通交易可正常交易的状态
     * 可交易需要:  建立会话  资金账号校验成功  获取用户信息成功(普通)
     */
    public static final int FLAG_NORMAL_TRADE_YES = FLAG_CREATE_SESSION_SUCCESS |
            FLAG_GET_NORMAL_USERINFO_SUCCESS | FLAG_NORMAL_ACCOUNT_CHECK_SUCCESS;

    /**
     * 两融可正常交易状态
     * 可交易需要:  建立会话 信用账号校验成功  获取用户信息成功(两融)
     */
    public static final int FLAG_CREDIT_TRADE_YES = FLAG_CREATE_SESSION_SUCCESS |
            FLAG_GET_CREDIT_USERINFO_SUCCESS | FLAG_CREDIT_ACCOUNT_CHECK_SUCCESS;

    /**
     * 两融可正常交易状态
     * 可交易需要:  建立会话 期权账号校验成功  获取用户信息成功(期权)
     */
    public static final int FLAG_OPTION_TRADE_YES = FLAG_CREATE_SESSION_SUCCESS |
            FLAG_GET_OPTION_USERINFO_SUCCESS | FLAG_OPTION_ACCOUNT_CHECK_SUCCESS;
    /*----------------------  复合标志位  ----------------------*/
    /**
     * 交易状态
     */
    public static       int sFlag                 = 0x00000000;

    /**
     * 判断标志位是否符合
     *
     * @param flag 标志位
     * @return 是否符合标志位
     * 与运算符用符号“&”表示，两个操作数中位都为1，结果才为1，否则结果为0
     */
    public static boolean check(int flag) {
        //若是 flag、sFlag 操作位都为 1，result = sFlag
        //若是 flag、sFlag 操作位不全为 1 ，result = flag
        printLog();
        int result = flag & sFlag;
        if (result != 0) {
            if (result == flag) {
                return true;
            }
        }
        return false;
    }

    /**
     * 添加标志位（标志位设为true）
     *
     * @param flag 所要添加的标志位
     *             或 | 两个位只要有一个为1，那么结果就是1，否则就为0
     */
    public static void addFlag(int flag) {
        sFlag |= flag;
//        sFlag = sFlag |flag;
        printLog();
    }

    /**
     * 设置标志位
     *
     * @param flag 所要设置的标志位
     */
    public static void setFlag(int flag) {
        sFlag = flag;
    }

    /**
     * 移除标志位 (标志位设为false)
     *
     * @param flag 所要删除的标志位
     *             与 & 两个操作数中位都为1，结果才为1，否则结果为0
     *             非 ~ 如果位为0，结果是1，如果位为1，结果是0
     */
    public static void removeFlag(int flag) {
        sFlag &= ~flag;
//        sFlag = ~flag & sFlag;
        printLog();
    }

    public static void printLog() {
        LogUtil.printLog("d", "--------------------------------------\n" +
                Integer.toBinaryString(sFlag) + "\n" +
                "--------------------------------------\n");
    }
}
