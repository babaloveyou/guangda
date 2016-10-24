package com.thinkive.android.trade_bz.a_stock.fragment;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.WebView;

import com.android.thinkive.framework.CoreApplication;
import com.android.thinkive.framework.config.ConfigManager;
import com.android.thinkive.framework.fragment.BaseWebFragment;
import com.android.thinkive.framework.message.AppMessage;
import com.android.thinkive.framework.message.IMessageHandler;
import com.android.thinkive.framework.message.MessageManager;
import com.android.thinkive.framework.module.IWebModule;
import com.android.thinkive.framework.module.ModuleManager;
import com.android.thinkive.framework.theme.ThemeManager;
import com.thinkive.android.trade_bz.others.tools.TradeLoginManager;
import com.thinkive.android.trade_bz.utils.LogUtil;
import com.thinkive.android.trade_bz.utils.TradeUtils;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * 负责加载H5页面的Fragment
 * 实现H5页面的预加载、接收50114消息实现H5页面返回按钮监听，发送50104消息通知H5设置主题颜色。
 * Announcements：框架新增了预加载功能，本类预加载功能取消。
 * @author 王志鸿
 * @company Thinkive
 * @date 2015/9/19
 */
@Deprecated
public class TradeWebCacheFragment extends BaseWebFragment implements IWebModule {

    //不需要支持h5文件单独升级的项目，采用如下url格式
    private final static String url = "file:///android_asset/www/m/trade/index.html";

    private String mWebviewName;

    private AppMessage mAppMessage;
    /**
     * 方法模块名，这里的方法模块名和原生Android框架里的模块无关。这个只是调用H5方法的一个入参。
     */
    private String mFuncModule;

    private FragmentActivity mActivity;
    /**
     * 唯一构造方法，注册模块
     */
    public TradeWebCacheFragment() {
        setWebviewName();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ModuleManager.getInstance().registerModule(this);
        mActivity = (FragmentActivity)getActivity();
        LogUtil.printLog("d", "onCreateView");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LogUtil.printLog("d", "onActivityCreated");
        initViews();
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtil.printLog("d", "onResume");
        if (ThemeManager.getInstance(getActivity()).getThemeBean() != null) {
            String styleColor = ThemeManager.getInstance(getActivity()).getThemeBean().getStyleColor();
            if (styleColor != null) {
                sendMessage50104(styleColor);
            }
        }
    }

    public void setFuncModule(String funcModule) {
        mFuncModule = funcModule;
    }
    /**
     * 给H5发消息，以实现页面的跳转
     */
    public void prepareMsgToH5ForSkip(String loginType) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("moduleName", "trade");
            jsonObject.put("funcModule", mFuncModule);
            JSONObject userInfoJson;
            if (loginType.equals(TradeLoginManager.LOGIN_TYPE_CREDIT)) { // 给H5融资融券账户信息
                userInfoJson = TradeLoginManager.sCreditUserInfo_json;
                jsonObject.put("userInfo", userInfoJson);
                jsonObject.put("loginType", "1");
            } else if (loginType.equals(TradeLoginManager.LOGIN_TYPE_NORMAL)) { // 给H5普通账户信息
                userInfoJson = TradeLoginManager.sNormalUserInfo_json;
                jsonObject.put("userInfo", userInfoJson);
                jsonObject.put("loginType", "0");
            }
            String jsessionid = TradeLoginManager.sCreditUserInfo_json.optString("jsessionid");
            String wholeUrl = ConfigManager.getInstance().getAddressConfigValue("CREDIT_URL_TRADE");
            // http://220.178.81.210:8001/servlet/json?
            String url = wholeUrl.substring(wholeUrl.indexOf("/"));
            url = url.substring(2);
            url = url.substring(0, url.indexOf("/"));
            CookieManager.getInstance().setCookie(url, "jsessionid="+jsessionid);
        } catch (JSONException je) {
            je.printStackTrace();
        }
        mAppMessage = new AppMessage(60251, jsonObject);
        sendMessageByWebModule(mAppMessage);
    }

    @Override
    public void sendMessageByWebModule(AppMessage appMessage) {
        sendMessageToH5(appMessage);
    }

    @Override
    public String onMessageReceive(final AppMessage appMessage) {
        if (appMessage == null) {
            return null;
        }
        int msgId = appMessage.getMsgId();
        final Activity activity = getActivity();
        IMessageHandler messageHandler = null;
        switch (msgId) {
            case 50114:
                CoreApplication.getInstance().getHandler().post(new Runnable() {
                    @Override
                    public void run() {
                        if (getWebView() != null && appMessage.getWebView() == getWebView()) {
                            com.android.thinkive.framework.util.Log.e("webview name = " + appMessage.getWebView().getWebViewName() + " current webview name = " + getWebView().getWebViewName());
                            if (activity != null) {
                                activity.finish();
                            }
                            MessageManager.getInstance(activity).buildMessageReturn(1, null, null);
                        }
                    }
                });
                break;
            case 60403:
                sendMessageByWebModule(appMessage);
                return MessageManager.getInstance(activity).buildMessageReturn(0, null, null); // 0是继续广播
            case 60050:
                sendMessageByWebModule(appMessage);
                return MessageManager.getInstance(activity).buildMessageReturn(0, null, null);
            case 60051:
                sendMessageByWebModule(appMessage);
                return MessageManager.getInstance(activity).buildMessageReturn(0, null, null);
            case 60053:
                sendMessageByWebModule(appMessage);
                return MessageManager.getInstance(activity).buildMessageReturn(0, null, null);
            case 60054:
                sendMessageByWebModule(appMessage);
                return MessageManager.getInstance(activity).buildMessageReturn(0, null, null);
            case 60055:
                sendMessageByWebModule(appMessage);
                return MessageManager.getInstance(activity).buildMessageReturn(0, null, null);
            case 60201:
                sendMessageByWebModule(appMessage);
                return MessageManager.getInstance(activity).buildMessageReturn(0, null, null);

        }
        if (messageHandler != null) {
            return messageHandler.handlerMessage(activity, appMessage);
        }
        return null;
    }

    @Override
    public String returnWebViewName() {
        return mWebviewName;
    }

    private void initViews() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
    }

    private void sendMessage50104(String styleColor) {
        JSONObject param = new JSONObject();
        try {
            param.put("theme", styleColor);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        AppMessage appMessage = new AppMessage(50104, param);
        sendMessageByWebModule(appMessage);
    }

    /**
     * 发送50113消息，通知H5刷新页面并做一些操作，比如跳转
     * @param params
     *         决定H5做什么操作的消息入参
     */
    public void sendMessage50113(JSONObject params) {
        if (getWebView() != null) {
            AppMessage appMessage = new AppMessage(50113, params);
            sendMessageByWebModule(appMessage);
        }
    }

    /**
     * 发送50113消息给H5，但是不传任何参数，只是通知H5刷新一下页面
     */
    public void sendMessage50113() {
        sendMessage50113(new JSONObject());
    }

    /**
     * 发送50107消息给H5，通知他们，用户单击了手机的物理返回键
     */
    public void sendMessage50107() {
        if (getWebView() != null) {
            AppMessage appMessage = new AppMessage(50107, new JSONObject());
            sendMessageByWebModule(appMessage);
        }
    }

    public void preloadUrl(Activity activity) {
        super.preloadUrl(activity, url);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {

    }
    private void setWebviewName() {
        mWebviewName = TradeUtils.getPreUrl(url);
    }
}
