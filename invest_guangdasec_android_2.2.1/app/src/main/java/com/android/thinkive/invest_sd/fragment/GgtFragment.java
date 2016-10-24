package com.android.thinkive.invest_sd.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
 * Created by xiangfan on 2015/11/4.
 */
public class GgtFragment extends BaseWebFragment implements IWebModule {
    public final static String assert_url = "file:///android_asset/www/m/ggt/index.html#!/business/index.html";
    public final static String url = "www/m/ggt/index.html#!/business/index.html";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ModuleManager.getInstance().registerModule(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public String returnWebViewName() {
        return "ggt";
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("trade onActivityCreated =========== ");
//        loadUrl(url);
        loadUrl(assert_url);
    }

    @Override
    public void onResume() {
        super.onResume();
//        if(!MainActivity.islogin){
//            sendMessageByWebModule(new AppMessage(50113,new JSONObject()));
//        }
        if (ThemeManager.getInstance(getActivity()).getThemeBean() != null) {
            String styleColor = ThemeManager.getInstance(getActivity()).getThemeBean().getStyleColor();
            if (styleColor != null) {
//                sendMessage50104(styleColor);
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    private void sendMessage50104(String styleColor) {
        JSONObject param = new JSONObject();
        try {
            param.put("theme", styleColor);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        AppMessage appMessage = new AppMessage(50104, param);
        sendMessageToH5(appMessage);
    }


    @Override
    public void sendMessageByWebModule(AppMessage appMessage) {
        Log.d("trade module send message = " + appMessage.getContent());
        sendMessageToH5(appMessage);
    }



    @Override
    public String onMessageReceive(AppMessage appMessage) {
        int msgId = appMessage.getMsgId();
        switch (msgId) {
            case 50100:

                return MessageManager.getInstance(getActivity()).buildMessageReturn(1, null, null);
            default:
                break;
        }

        return null;
    }
}