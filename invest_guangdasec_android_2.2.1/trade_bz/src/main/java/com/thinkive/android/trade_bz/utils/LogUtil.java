package com.thinkive.android.trade_bz.utils;

import android.util.Log;

import com.android.thinkive.framework.config.ConfigManager;


/**
 * 思迪项目日志打印
 * Announcements：
 *
 * @author 王志鸿
 * @company Thinkive
 * @date 2015/9/9
 */
public class LogUtil {

    /**
     * 思迪项目打印日志专用tag
     */
    private static final String LOG_TAG = "thinkive_log";

    /**
     * 投资标准外壳专用打印日志方法
     * @param type 日志类型
     * @param content 日志内容
     */
    public static void printLog(String type, String content) {
        if (!ConfigManager.getInstance().getSystemConfigValue("isDebug").equals("true")) {
            return;
        }
        switch (type) {
            case "d":
                Log.d(LOG_TAG, content);
                break;
            case "v":
                Log.v(LOG_TAG, content);
                break;
            case "i":
                Log.i(LOG_TAG, content);
                break;
            case "w":
                Log.w(LOG_TAG, content);
                break;
            case "e":
                Log.e(LOG_TAG, content);
                break;
        }
    }
}
