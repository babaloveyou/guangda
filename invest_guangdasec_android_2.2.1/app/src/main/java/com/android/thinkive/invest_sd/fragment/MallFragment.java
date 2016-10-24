package com.android.thinkive.invest_sd.fragment;

import android.os.Bundle;

import com.android.thinkive.framework.fragment.BaseWebFragment;
import com.android.thinkive.framework.message.AppMessage;
import com.android.thinkive.framework.message.MessageManager;
import com.android.thinkive.framework.module.IWebModule;
import com.android.thinkive.framework.module.ModuleManager;
import com.android.thinkive.framework.theme.ThemeManager;
import com.android.thinkive.framework.util.Log;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by zhuduanchang on 2015/6/23.
 */
public class MallFragment extends WebCacheFragment implements IWebModule {
    //不需要支持h5文件单独升级的项目，采用如下url格式
    public final static String assert_url = "file:///android_asset/www/m/mall/index.html";
    //支持H5文件单独升级的项目，采用如下url格式
    public final static String url = "www/m/mall/index.html";
    public MallFragment(){
        ModuleManager.getInstance().registerModule(this);
    }
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        ModuleManager.getInstance().registerModule(this);
//    }
//
//    @Override
//    /**
//     * 返回一个webview的名字，用于做预加载，如果不做预加载的项目，简单一点可直接返回null
//     */
//    public String returnWebViewName() {1
//        return "mall";
//    }
//
//    @Override
//    public void onActivityCreated(Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        // 采用了预加载机制的话，此处可以不需要在loadurl了，否则还需调用loadUrl
////        loadUrl(url);
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        if (ThemeManager.getInstance(getActivity()).getThemeBean() != null) {
//            String styleColor = ThemeManager.getInstance(getActivity()).getThemeBean().getStyleColor();
//            if (styleColor != null) {
////                sendMessage50104(styleColor);
//            }
//        }
//    }
//
//    @Override
//    public void sendMessageByWebModule(AppMessage appMessage) {
//        Log.d("shop module send message = " + appMessage.getContent());
//        sendMessageToH5(appMessage);
//    }
//
//    @Override
//    public String onMessageReceive(AppMessage appMessage) {
//        int msgId = appMessage.getMsgId();
//        switch (msgId) {
//            case 50100://通知原生H5模块加载完毕
//
//                return MessageManager.getInstance(getActivity()).buildMessageReturn(1, null, null);
//            default:
//                break;
//        }
//        return null;
//    }
//
//    private void sendMessage50104(String styleColor) {
//        JSONObject param = new JSONObject();
//        try {
//            param.put("theme", styleColor);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        AppMessage appMessage = new AppMessage(50104, param);
//        sendMessageToH5(appMessage);
//    }
}
