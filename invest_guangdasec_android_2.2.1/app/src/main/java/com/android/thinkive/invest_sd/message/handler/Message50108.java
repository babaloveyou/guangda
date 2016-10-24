package com.android.thinkive.invest_sd.message.handler;

import android.content.Context;
import android.view.View;

import com.android.thinkive.framework.message.AppMessage;
import com.android.thinkive.framework.message.IMessageHandler;
import com.android.thinkive.framework.message.MessageManager;

import org.json.JSONObject;

/**
 * Created by liujianwei on 15/6/30.
 */
public class Message50108 implements IMessageHandler {
    private View mNavigationBar;

    public Message50108(View navBar) {
        mNavigationBar = navBar;
    }

    @Override
    public String handlerMessage(Context context, AppMessage appMessage) {
        JSONObject content = appMessage.getContent();
        String flag = content.optString("flag");
        if ("1".equals(flag)) {
            mNavigationBar.setVisibility(View.VISIBLE);
        } else if ("0".equals(flag)) {
            mNavigationBar.setVisibility(View.GONE);
        }
        return MessageManager.getInstance(context).buildMessageReturn(1, null, null);
    }


}
