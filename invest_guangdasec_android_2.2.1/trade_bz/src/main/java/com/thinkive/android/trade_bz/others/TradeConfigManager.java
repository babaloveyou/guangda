package com.thinkive.android.trade_bz.others;

import android.content.Context;
import android.content.res.XmlResourceParser;

import com.android.thinkive.framework.util.ResourceUtil;
import com.thinkive.android.trade_bz.a_stock.bean.TradeModuleBean;
import com.thinkive.android.trade_bz.utils.LogUtil;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;

/**
 * 交易模块程序配置管理器，读取、实现交易模块配置文件trade_configuration.xml中的配置
 * 目前作用是初始化交易模块的配置信息
 * Announcements：本类解析数据，依赖res/xml/trade_configuration.xml文件
 *
 * @author 王志鸿
 * @company Thinkive
 * @date 2015/8/13
 */
public class TradeConfigManager {

    /**
     * 配置文件标签名常量
     */
    private static final String MODULE = "module";

    /**
     * 配置文件的模块名常量集合
     */
    public static final String[] MODULE_NAMES = {"TRADE_MY_HOLD","TRADE_TODAY_TRADE","TRADE_TODAY_ENTRUST","NEW_STOCK_SUBSCRIBE",
            "CREDIT_TRADE", "HK_GENERAL","TRANS_STOCK","OPTION_STOCK", "FUND_TRADE","IN_FUND_TRADE", "LEVEL_FUND_TRADE","TRADE_OTC","ONE_KEY", "TRANSFER_BANK",
            "CHANGE_TRADE_PWD","SIGN_AGREEMENT","CHANGE_ADDRESS_BOOK_PWD","TRADE_NET","TRADE_YU_SELL"};

    /**
     * xml配置文件中可用性属性值的常量定义
     */
    private static final String USABLE_TRUE = "true";
    /**
     * xml配置文件中可用性属性值的常量定义
     */
    private static final String USABLE_FALSE = "false";

    private static final int MIN_MODULES_NUM = 8;

    /**
     *
     */
    private ArrayList<TradeModuleBean> mModules;
    /**
     * 本类单例模式下的单例实例
     */
    private static TradeConfigManager sInstance;

    private TradeConfigManager() {
        mModules = new ArrayList<TradeModuleBean>();
        // 设置循环临时变量
        TradeModuleBean tradeModuleBean = null;
        // 循环构造默认的模块配置信息集，当配置文件读取出错时使用
        for (String moduleName : MODULE_NAMES) {
            tradeModuleBean = new TradeModuleBean();
            tradeModuleBean.setValue(moduleName);
            tradeModuleBean.setUsable(true);
            mModules.add(tradeModuleBean);
        }
    }

    /**
     * 获取本类单例对象
     *
     * @return 本类单例对象
     */
    public static synchronized TradeConfigManager getInstance() {
        if (sInstance == null) {
            sInstance = new TradeConfigManager();
        }
        return sInstance;
    }

    /**
     * 获取写在xml文件中的config信息
     */
    public void getXmlConfig(Context context) {
        XmlResourceParser xmlParser = null;
        try {
            xmlParser = context.getResources().getXml(
                    ResourceUtil.getResourceID(context, "xml", "trade_configuration"));
            if (xmlParser == null) {
                return;
            }
            TradeModuleBean moduleBean;
            mModules = new ArrayList<TradeModuleBean>();
            do {
                xmlParser.next();
                String tagName = xmlParser.getName();
                int eventType = xmlParser.getEventType();
                switch (eventType) {
                    case XmlPullParser.START_TAG: {
                        // 遍历到的xml标签中的属性是否是“module”
                        if (MODULE.equalsIgnoreCase(tagName)) {
                            moduleBean = new TradeModuleBean();
                            moduleBean.setValue(xmlParser.getAttributeValue(null, "value"));
                            // 获取这个标签中的usable属性的值，并判断这个值是否是USABLE_FALSE
                            if (xmlParser.getAttributeValue(null, "usable").equals(USABLE_FALSE)) {
                                moduleBean.setUsable(false);
                            } else if (xmlParser.getAttributeValue(null, "usable").
                                    equals(USABLE_TRUE)) { //
                                moduleBean.setUsable(true);
                            } else { // xml配置文件编写错误！
                                LogUtil.printLog("e",
                                        "交易模块配置文件（trade_configuration）读取出错！请检查" +
                                                moduleBean.getValue() + "模块下的usable的值是否是true或false");
                            }
                            mModules.add(moduleBean);
                        }
                        break;
                    }
                }
            } while (xmlParser.getEventType() != XmlPullParser.END_DOCUMENT);
            // 对读取结果进行检查

        } catch (XmlPullParserException xppe) {
            xppe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (xmlParser != null) {
                xmlParser.close();
            }
        }
    }

    /**
     * 判断交易配置文件是否成功解析
     * @return true：解析成功；false：解析失败
     */
    public boolean isLegalParseResult() {
        boolean result = true;
        boolean isOneModuleLegal = false;
        if (mModules.size() < MIN_MODULES_NUM) { // 如果解析到的模块个数少于实际应有的模块个数
            result = false;
        } else { // 解析到了所有的模块配置
            // 循环所有解析到的模块配置
            for (TradeModuleBean module : mModules) {
                // 循环静态模块名称
                for (String stardModuleName : MODULE_NAMES) {
                    // 循环中，判断当前遍历到的mModule是否存在于所有模块名称集合中，即模块名是否合法
                    if (stardModuleName.equals(module.getValue())) {
                        isOneModuleLegal = true;
                        break;
                    }
                }
                // 如果模块名不合法，那么输出日志
                if (!isOneModuleLegal) {
                    LogUtil.printLog("e", "在trade_configuration.xml中，" +
                            "找不到value值为" + module.getValue() +
                            "的对应模块，模块的value值只能为如下几种：");
                    // 循环输出所有合法名称
                    for (String stardModuleName : MODULE_NAMES) {
                        LogUtil.printLog("e", stardModuleName);
                    }
                    result = false;
                }
                isOneModuleLegal = false;
            }
        }
        return result;
    }

    public ArrayList<TradeModuleBean> getModules() {
        return mModules;
    }
}
