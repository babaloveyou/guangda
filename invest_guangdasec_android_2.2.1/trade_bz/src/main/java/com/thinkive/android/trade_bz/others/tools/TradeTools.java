package com.thinkive.android.trade_bz.others.tools;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.android.thinkive.framework.config.ConfigManager;
import com.android.thinkive.framework.keyboard.BaseKeyboard;
import com.android.thinkive.framework.message.AppMessage;
import com.android.thinkive.framework.message.MessageManager;
import com.android.thinkive.framework.network.ProtocolType;
import com.android.thinkive.framework.network.RequestBean;
import com.thinkive.android.trade_bz.keyboard.KeyboardManager;
import com.thinkive.android.trade_bz.others.constants.Constants;
import com.thinkive.android.trade_bz.utils.LogUtil;
import com.thinkive.android.trade_bz.utils.TradeUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 与交易项目业务逻辑和其他代码有耦合的交易工具类
 * 为达到项目级别的功能组件化，在本类中实现若干静态方法。为交易项目的开发提供便利。
 * @author 王志鸿
 * @company Thinkive
 * @date 2015/7/10
 */
public class TradeTools {

    /**
     * 初始化框架中的原生自绘键盘，并返回一个自绘键盘对象。
     * 执行本方法后，会有一个EditText与框架自绘键盘绑定，后者输入的内容会出现在前者中。
     *
     * @param activity                自绘键盘出现的Activity
     * @param editText                与自绘键盘绑定的EditText
     * @param keyboardType            自绘键盘类型
     * @param theme                   自绘键盘的主题，1是白色，2是黑色。
     * @param focusChangeWithKeyboard 外部的输入框editText焦点改变监听
     * @param clickWithKeyboard       外部的输入框单击事件监听
     * @return 自绘键盘管理器类（KeyboardManager）对象
     */
    public static KeyboardManager initKeyBoard(final Activity activity,
                                               EditText editText,
                                               short keyboardType,
                                               final short theme,
                                               final OnFocusChangeWithKeyboard focusChangeWithKeyboard,
                                               final OnClickWithKeyboard clickWithKeyboard) {
        final KeyboardManager keyboardManager;
        // 从最根处弹出自绘键盘
        if (activity.getParent() == null) {
            keyboardManager = new KeyboardManager(activity, editText, keyboardType);
        } else {
            keyboardManager = new KeyboardManager(activity.getParent(), editText, keyboardType);
        }
        // 利用反射，设置不显示系统自带键盘
        try {
            Method setShowSoftInputOnFocus =
                    editText.getClass().getMethod("setShowSoftInputOnFocus", boolean.class);
            setShowSoftInputOnFocus.setAccessible(true);
            setShowSoftInputOnFocus.invoke(editText, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 给自绘键盘设置主题
        keyboardManager.setTheme(theme);
        // 设置目标EditText焦点变化监听，如果焦点进入目标EditText，则弹出自绘键盘，反之隐藏自绘键盘
        if (editText != null) {
            editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (focusChangeWithKeyboard != null) {
                        focusChangeWithKeyboard.onFocusChange(hasFocus);
                    }
                    if (hasFocus) {
                        if (!activity.isFinishing()) {
                            keyboardManager.show();
                        }
                        TradeUtils.hideSystemKeyBoard(activity);
                    } else {
                        keyboardManager.dismiss();
                    }
                }
            });
            // 设置单击目标EditText时，如果自绘键盘还没弹出，那就弹出它
            editText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (clickWithKeyboard != null) {
                        clickWithKeyboard.onClick();
                    }
                    if (!keyboardManager.isShowing() && !activity.isFinishing()) {
                        keyboardManager.show();
                        TradeUtils.hideSystemKeyBoard(activity);
                    }
                }
            });
        }
        return keyboardManager;
    }

    public static KeyboardManager initKeyBoard(final Activity activity, EditText editText, short keyboardType, final short theme) {
        return initKeyBoard(activity, editText, keyboardType, theme, null, null);
    }

    /**
     * 初始化框架中的原生自绘键盘，并返回一个自绘键盘对象。
     * 执行本方法后，会有一个EditText与框架自绘键盘绑定，后者输入的内容会出现在前者中。
     *
     * @param activity     自绘键盘出现的Activity
     * @param editText     与自绘键盘绑定的EditText
     * @param keyboardType 自绘键盘类型
     * @return 自绘键盘管理器类（KeyboardManager）对象
     */
    public static KeyboardManager initKeyBoard(final Activity activity, EditText editText, short keyboardType,
                                               final OnFocusChangeWithKeyboard focusChangeWithKeyboard,
                                               final OnClickWithKeyboard clickWithKeyboard) {
        return initKeyBoard(activity, editText, keyboardType, BaseKeyboard.THEME_LIGHT, focusChangeWithKeyboard, clickWithKeyboard);
    }

    /**
     * 初始化框架中的原生自绘键盘，并返回一个自绘键盘对象。
     * 执行本方法后，会有一个EditText与框架自绘键盘绑定，后者输入的内容会出现在前者中。
     *
     * @param activity     自绘键盘出现的Activity
     * @param editText     与自绘键盘绑定的EditText
     * @param keyboardType 自绘键盘类型
     * @return 自绘键盘管理器类（KeyboardManager）对象
     */
    public static KeyboardManager initKeyBoard(final Activity activity, EditText editText, short keyboardType) {
        return initKeyBoard(activity, editText, keyboardType, BaseKeyboard.THEME_LIGHT);
    }

    public interface OnClickWithKeyboard {
        void onClick();
    }

    public interface OnFocusChangeWithKeyboard {
        void onFocusChange(boolean hasFocus);
    }

    /**
     * 发送消息给登录模块，让他们知道我们跳转要完成了，登录的Activity可以finish了
     */
    public static void sendMsgToLoginForSubmitFinish(Context context) {
        JSONObject jsonObject = new JSONObject();
        JSONObject paramJsonObject = new JSONObject();
        try {
            paramJsonObject.put("moduleName", Constants.MODULE_NAME_TRADE);
            jsonObject.put("moduleName", Constants.MODULE_NAME_ACCOUNT_VERIFY);
            jsonObject.put("params", paramJsonObject);
            AppMessage appMessage = new AppMessage(Constants.MODULE_NAME_ACCOUNT_VERIFY, 50114, jsonObject);
            appMessage.setTargetModule(Constants.MODULE_NAME_ACCOUNT_VERIFY);
            MessageManager.getInstance(context).sendMessage(appMessage);
        } catch (JSONException je) {
            je.printStackTrace();
        }
    }

    /**
     * 将行情的stockType数据转换为交易的
     * 行情的股票市场类别和交易的数据字典不一样，
     * 对应关系：
     * var _exchange = {
     * "0":"0",
     * "1":"1",
     * "2":"0",
     * "3":"0",
     * "4":"0",
     * "5":"0",
     * "6":"0",
     * "7":"0",
     * "8":"0",
     * "9":"2",
     * "10":"3",
     * "11":"2",
     * "12":"2",
     * "13":"2",
     * "14":"2",
     * "15":"2",
     * "16":"2",
     * "17":"4",
     * "18":"0",
     * "19":"0",
     * "20":"2",
     * "21":"0",
     * "22":"0",
     * "23":"0",
     * "24":"0",
     * "25":"2",
     * "26":"2",
     * "27":"2",
     * "30":"2",
     * "64":"0",
     * "65":"2",
     * "66":"2"
     * };
     *
     * @param stockTypeHq 按照行情数据字典取值的股票市场类别
     * @return 按照交易数据字典取值的股票市场类别
     */
    public static String transferStockTypeHqToTrade(String stockTypeHq) {
        // int形式的行情股票市场类别
        int stockTypeInt = Integer.valueOf(stockTypeHq);
        // int形式的交易市场类别
        int resultInt = 0;
        switch (stockTypeInt) {
            case 0:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 18:
            case 19:
            case 21:
            case 22:
            case 23:
            case 24:
            case 64:
                resultInt = 0;
                break;
            case 1:
                resultInt = 1;
                break;
            case 9:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 20:
            case 25:
            case 26:
            case 27:
            case 30:
            case 65:
            case 66:
                resultInt = 2;
                break;
            case 10:
                resultInt = 3;
                break;
            case 17:
                resultInt = 4;
                break;
        }
        return String.valueOf(resultInt);
    }

    /**
     * [10,12,13,4,6,19,20,3,11,14,16] // 基金
     * [21,22,23,24,25,26,27,30] // 国债
     */
    public static int transferStockType(String stockTypeHq) {
        // int形式的行情股票市场类别
        int stockTypeInt = Integer.valueOf(stockTypeHq);
        // int形式的交易市场类别
        int isFund = 2;
        switch (stockTypeInt) {
            //基金
            case 10:
            case 12:
            case 13:
            case 4:
            case 6:
            case 19:
            case 20:
            case 3:
            case 11:
            case 14:
            case 16:
                isFund = 3;
                break;
            //国债
            case 21:
            case 22:
            case 23:
            case 24: //深圳回购
            case 25:
            case 26:
            case 27:
            case 30: //上海回购
                isFund = 4;
                break;
        }
        return isFund;
    }

    public static String forMateStockAccount(String exchangeType){
        String result = "";
        if(exchangeType != null && !TextUtils.isEmpty(exchangeType)){
            if (exchangeType.equals("0")) { // 深A
                result = TradeLoginManager.sNormalUserInfo_shen_A.getStock_account();
            } else if (exchangeType.equals("1")) { // 深B
                result = TradeLoginManager.sNormalUserInfo_shen_B.getStock_account();
            } else if (exchangeType.equals("2")) { // 沪A
                result = TradeLoginManager.sNormalUserInfo_hu_A.getStock_account();
            } else if (exchangeType.equals("3")) { // 沪B
                result = TradeLoginManager.sNormalUserInfo_hu_B.getStock_account();
            }
        }
        return result;
    }

    /**
     * 为本次请求打印出请求的Url日志。
     * 为了方便调试，每次发起请求的时候都执行一次这个方法。
     *
     * @param requestBean 本次请求的数据
     */
    public static void printLogForRequestUrl(RequestBean requestBean) {
        // 首先判断本次请求的类型，
        if (requestBean.getProtocolType() == ProtocolType.HTTP) { // 如果是HTTP请求。
            // 获取本次请求的完整Url，
            String urlPre = ConfigManager.getInstance().getAddressConfigValue(requestBean.getUrlName());
            if(!urlPre.endsWith("?")) {
                urlPre += "?";
            }
            // 取出本次请求的入参
            HashMap<String, String> params = requestBean.getParam();
            // 定义拼接入参的StringBuilder
            StringBuilder paramsStringBuilder = new StringBuilder();
            // 循环取出入参map中的数据，拼接到一起
            for (Map.Entry entry : params.entrySet()) {
                String key = entry.getKey().toString();
                String value = "";
                if (entry.getValue()!=null) {
                    value = entry.getValue().toString();
                }
                paramsStringBuilder.append(key);
                paramsStringBuilder.append("=");
                paramsStringBuilder.append(value);
                paramsStringBuilder.append("&");
            }
            // 删除最后一次循环时多余拼接的"&"
            paramsStringBuilder.deleteCharAt(paramsStringBuilder.length() - 1);
            // 打印出请求的地址Url
            LogUtil.printLog("d", "发起请求：" + urlPre + paramsStringBuilder.toString());
        }
    }
}
