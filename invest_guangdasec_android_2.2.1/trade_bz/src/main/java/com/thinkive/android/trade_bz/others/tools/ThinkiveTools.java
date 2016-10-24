package com.thinkive.android.trade_bz.others.tools;

import android.content.Context;

import com.android.thinkive.framework.message.AppMessage;
import com.android.thinkive.framework.message.MessageManager;
import com.android.thinkive.framework.storage.MemoryStorage;
import com.thinkive.android.trade_bz.utils.LogUtil;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 和外壳项目有耦合的静态工具方法
 * Announcements：
 *
 * @author 王志鸿
 * @company Thinkive
 * @date 2015/9/15
 */
public class ThinkiveTools {

    /**
     * 在程序持久性缓存中保存数据，以Map形式
     * 此功能在框架中通过功能号实现，所以本方法发送50042消息，以执行保存数据操作
     *
     * @param context 外部调用环境
     * @param key     以Map形式保存数据所用的Key
     * @param value   以Map形式保存数据的value
     * @return true：保存操作执行成功；false：保存操作执行失败。
     */
    public static boolean putDataByMsg(Context context, String key, String value) {
        boolean result = false;
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("key", key);
            jsonObject.put("value", value);
            AppMessage appMessage = new AppMessage(50042, jsonObject);
            String resultJsonStr = MessageManager.getInstance(context).sendMessage(appMessage);
            // 存放调用50042功能号的调用结果的JsonObject
            JSONObject resultJsonObject = new JSONObject(resultJsonStr);
            // 获取上一行调用结果中的，error_no字段，它是判断成功与否的关键
            String resultErrorNo = resultJsonObject.optString("error_no");
            // error_no为0时，说明调用成功
            if (resultErrorNo.equals("0")) {
                result = true;
            } else {
                LogUtil.printLog("e",
                        "putDataByMsg方法调用50042功能号出错！错误信息：" +
                                resultJsonObject.toString());
            }
        } catch (JSONException je) {
            je.printStackTrace();
        }
        return result;
    }

    /**
     * 获取程序持久性缓存当中的数据，以Map形式
     * 此功能在框架中通过功能号实现，所以本方法发送50043消息，以执行读取数据操作
     *
     * @param key 以Map形式取数据所用的Key
     * @return 最终取得的数据，key对应的value数据
     */
    public static String getDataByMsg(Context context, String key) {
        String result = "";
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("key", key);
            AppMessage appMessage = new AppMessage(50043, jsonObject);
            String resultJsonStr = MessageManager.getInstance(context).sendMessage(appMessage);
            // 存放调用50042功能号的调用结果的JsonObject
            JSONObject resultJsonObject = new JSONObject(resultJsonStr);
            // 获取上一行调用结果中的，error_no字段，它是判断成功与否的关键
            String resultErrorNo = resultJsonObject.optString("error_no");
            // error_no为0时，说明调用成功
            if (resultErrorNo.equals("0")) {
                result = resultJsonObject.getJSONArray("results").getJSONObject(0).getString("value");
            } else {
                LogUtil.printLog("e",
                        "getDataByMsg方法调用50043功能号出错！错误信息：" +
                                resultJsonObject.toString());
            }
        } catch (JSONException je) {
            je.printStackTrace();
        }
        return result;
    }

    /**
     * 在程序内存缓存中保存数据，以Map形式，程序退出后失效。
     * 此功能在框架中通过功能号实现，本方法等价于H5给原生发送50040消息，以执行保存数据操作
     *
     * @param key   以Map形式保存数据所用的Key
     * @param value 以Map形式保存数据的value
     * @return true：保存操作执行成功；false：保存操作执行失败。
     */
    public static boolean putDataToMemoryByMsg(String key, String value) {
        MemoryStorage memoryStorage = new MemoryStorage();
        memoryStorage.saveData(key, value);
        return true;
    }

    /**
     * 获取程序内存缓存当中的数据，以Map形式，程序退出后失效。
     * 此功能在框架中通过功能号实现，本方法等价于H5给原生发送50041消息，以执行读取数据操作
     *
     * @param key 以Map形式取数据所用的Key
     * @return 最终取得的数据，key对应的value数据
     */
    public static String getDataToMemoryByMsg(String key) {
        MemoryStorage memoryStorage = new MemoryStorage();
        return memoryStorage.loadData(key);
    }
}
