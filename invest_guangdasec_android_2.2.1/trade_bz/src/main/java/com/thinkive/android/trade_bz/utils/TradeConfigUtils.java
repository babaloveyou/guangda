package com.thinkive.android.trade_bz.utils;

import com.thinkive.android.trade_bz.others.constants.TradeColorConstants;

/**
 * 相关注释待本类代码基本稳定后添加，敬请期待！
 * @author 王志鸿
 * @company Thinkive
 * @date 2015/6/18
 */
public class TradeConfigUtils {
    /**
     * 交易中的行情上涨时使用的颜色值
     */
    public static String sPriceUpColor = TradeColorConstants.RED;
    /**
     * 交易中的行情下跌时使用的颜色值
     */
    public static String sPriceDownColor = TradeColorConstants.GREEN;
    /**
     * 常用的灰色字体
     */
    public static String sGrayTextColor = TradeColorConstants.GRAY_TEXT;
    /**
     * 主题色1（买）
     */
    public static String sBuyTextColor = TradeColorConstants.TRADE_BUY;
    /**
     * 主题色2（卖）
     */
    public static String sSaleTextColor = TradeColorConstants.TRADE_SALE;
}
