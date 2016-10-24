package com.android.thinkive.invest_sd.message.handler;

import android.content.Context;
import android.content.Intent;

import com.android.thinkive.framework.message.AppMessage;
import com.android.thinkive.framework.message.IMessageHandler;
import com.android.thinkive.framework.message.MessageManager;
import com.android.thinkive.invest_sd.activities.MainActivity;
import com.android.thinkive.invest_sd.activities.WebViewActivity;

import org.json.JSONObject;

/**
 * Created by zhuduanchang on 2015/8/7.
 */
public class Message50115 implements IMessageHandler {
    MainActivity mMainActivity;
    @Override
    public String handlerMessage(Context context, AppMessage appMessage) {
        mMainActivity = (MainActivity)context;
        JSONObject msg  = appMessage.getContent();
        String moduleName = msg.optString("moduleName");
        String url = msg.optString("url");
        String statusColor = msg.optString("statusColor");
        String title = msg.optString("title");
        if (moduleName.isEmpty() || url.isEmpty()) {
            return MessageManager.getInstance(context).buildMessageReturn(-5011501, "链接地址不能为空", null);
        }
        Intent intent = new Intent(mMainActivity, WebViewActivity.class);
        intent.putExtra("url",url);
        intent.putExtra("title",title);
        intent.putExtra("statusColor",statusColor);
        mMainActivity.startActivity(intent);
        return MessageManager.getInstance(context).buildMessageReturn(1,null,null);
    }
}
