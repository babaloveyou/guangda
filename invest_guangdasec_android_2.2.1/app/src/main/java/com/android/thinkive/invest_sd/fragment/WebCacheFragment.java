package com.android.thinkive.invest_sd.fragment;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.android.thinkive.framework.CoreApplication;
import com.android.thinkive.framework.fragment.BaseWebFragment;
import com.android.thinkive.framework.message.AppMessage;
import com.android.thinkive.framework.message.IMessageHandler;
import com.android.thinkive.framework.message.MessageManager;
import com.android.thinkive.framework.module.IWebModule;
import com.android.thinkive.framework.module.ModuleManager;
import com.android.thinkive.framework.theme.ThemeManager;
import com.android.thinkive.invest_sd.util.ThinkiveUtil;
import com.thinkive.android.message.handler.Message60000;
import com.thinkive.android.message.handler.Message60001;
import com.thinkive.android.message.handler.Message60002;
import com.thinkive.android.message.handler.Message60003;
import com.thinkive.android.message.handler.Message60004;
import com.thinkive.android.message.handler.Message60005;
import com.thinkive.android.message.handler.Message60006;
import com.thinkive.android.message.handler.Message60007;
import com.thinkive.android.message.handler.Message60008;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 负责加载H5页面的Fragment
 * 实现H5页面的预加载、接收50114消息实现H5页面返回按钮监听，发送50104消息通知H5设置主题颜色。
 * Announcements：框架新增了预加载功能，本类预加载功能取消。
 *
 * @author 王志鸿
 * @company Thinkive
 * @date 2015/9/19
 */
public class WebCacheFragment extends BaseWebFragment implements IWebModule {

    /**
     * H5页面加载地址，必须在外部调用{@link #setUrl(String)}方法为其赋值。
     * {@link #setUrl(String)}方法内部会为其添加屏幕宽高入参，外部调用时只传入H5加载绝对路径即可。
     */
    String mUrl;
    String reloadurl;
    private boolean isneedloadutl = false;
    private boolean isrePreloade = false;
    private String mWebviewName;

    /**
     * 唯一构造方法，注册模块
     */
    public WebCacheFragment() {
        mWebviewName = "";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ModuleManager.getInstance().registerModule(this);
        if(isneedloadutl){
            preloadUrl(getActivity(),reloadurl);
            isneedloadutl = false;
            isrePreloade= true;
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViews();
        Log.e("thinkive", "loadUrl即将执行，url == " + mUrl);
        if(!isrePreloade) {
            loadUrl(mUrl);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (ThemeManager.getInstance(getActivity()).getThemeBean() != null) {
            String styleColor = ThemeManager.getInstance(getActivity()).getThemeBean().getStyleColor();
            if (styleColor != null) {
                sendMessage50104(styleColor);
            }
        }
    }

    @Override
    public void sendMessageByWebModule(AppMessage appMessage) {
        Log.d("fragment", "框架给H5发送消息，消息号是：" + appMessage.getMsgId() +
                "。消息内容为：" + appMessage.getContent().toString());
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
//            case 50115:
//                messageHandler = new Message50115();
//                break;
            case 60000:
                messageHandler = new Message60000();
                break;
            case 60001:
                messageHandler = new Message60001();
                break;
            case 60002:
                messageHandler = new Message60002();
                break;
            case 60003:
                messageHandler = new Message60003();
                break;
            case 60004:
                messageHandler = new Message60004();
                break;
            case 60005:
                messageHandler = new Message60005();
                break;
            case 60006:
                messageHandler = new Message60006();
                break;
            case 60007:
                messageHandler = new Message60007();
                break;
            case 60008:
                messageHandler = new Message60008();
                break;
//            case 60013:
//                messageHandler = new Message60013();
//                break;
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
    public void setNeedLoadUrl(String url){
        reloadurl = url;
        isneedloadutl =true;
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
     *
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

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
        setWebviewName();
    }

//    public void setWebviewName(String webviewName) {
//        mWebviewName = webviewName;
//    }

    private void setWebviewName() {
//        int nameEndPos =  mUrl.indexOf("#!/");
//        if (nameEndPos > 0) {
//            mWebviewName = mUrl.substring(0, nameEndPos);
//        } else {
//            mWebviewName = mUrl;
//        }
        mWebviewName = ThinkiveUtil.getPreUrl(mUrl);
        com.android.thinkive.framework.util.Log.d("@@@@@预加载 得到的webview名字"+mWebviewName);
    }
}
