package com.android.thinkive.invest_sd.message.handler;

import android.content.Context;
import android.content.Intent;

import com.android.thinkive.framework.message.AppMessage;
import com.android.thinkive.framework.message.IMessageHandler;
import com.android.thinkive.framework.message.MessageManager;
import com.android.thinkive.invest_sd.activities.MainActivity;
import com.android.thinkive.invest_sd.activities.VideoPlayerActivity;

import org.json.JSONObject;

/**
 * Created by zhuduanchang on 2015/8/4.
 */
public class Message50275 implements IMessageHandler {
    MainActivity mMainActivity;
    @Override
    public String handlerMessage(Context context, AppMessage appMessage) {
        mMainActivity = (MainActivity)context;
        JSONObject msg  = appMessage.getContent();
        String title = msg.optString("title");
        String mediaUrl = msg.optString("mediaUrl");
        if (mediaUrl.isEmpty() || title.isEmpty()) {
            return MessageManager.getInstance(context).buildMessageReturn(-5027501, "视频文件地址不能为空", null);
        }

        Intent intent = new Intent(mMainActivity, VideoPlayerActivity.class);
        intent.putExtra("title",title);
        intent.putExtra("mediaUrl",mediaUrl);
        mMainActivity.startActivity(intent);

        return MessageManager.getInstance(context).buildMessageReturn(1,null,null);
    }
}
