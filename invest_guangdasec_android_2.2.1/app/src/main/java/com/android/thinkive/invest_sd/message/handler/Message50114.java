package com.android.thinkive.invest_sd.message.handler;

import android.content.Context;

import com.android.thinkive.framework.message.AppMessage;
import com.android.thinkive.framework.message.IMessageHandler;
import com.android.thinkive.framework.message.MessageManager;
import com.android.thinkive.invest_sd.activities.WebViewActivity;

import org.json.JSONObject;

/**
 * Created by zhuduanchang on 2015/8/7.
 */
public class Message50114 implements IMessageHandler {
    private Context mContext;
    @Override
    public String handlerMessage(Context context, AppMessage appMessage) {
        this.mContext = context;
        JSONObject msg  = appMessage.getContent();
        String moduleName = msg.optString("moduleName");
        String params = msg.optString("params");

        if(mContext instanceof WebViewActivity){
            ((WebViewActivity) mContext).finish();
        }

        return MessageManager.getInstance(context).buildMessageReturn(1,null,null);
    }
}
