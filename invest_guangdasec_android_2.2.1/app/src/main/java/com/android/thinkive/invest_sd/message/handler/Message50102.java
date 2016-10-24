package com.android.thinkive.invest_sd.message.handler;

import android.content.Context;
import android.os.Handler;

import com.android.thinkive.framework.message.AppMessage;
import com.android.thinkive.framework.message.IMessageHandler;
import com.android.thinkive.framework.message.MessageManager;
import com.android.thinkive.framework.util.Constant;
import com.android.thinkive.invest_sd.activities.MainActivity;

import org.json.JSONObject;


/**
 * Created by liujianwei on 15/6/30.
 */
public class Message50102 implements IMessageHandler {
    private Handler mHandler;
    public Message50102(Handler handler){
        mHandler = handler;
    }

    @Override
    public String handlerMessage(Context context, AppMessage appMessage) {
        JSONObject content = appMessage.getContent();
        switchSlideMenu(content);
        return  MessageManager.getInstance(context).buildMessageReturn(1, null, null);
    }

    /**
     * 打开关闭左右侧滑栏
     *
     * @param content
     */
    private void switchSlideMenu(JSONObject content) {
        String module = content.optString(Constant.MODULE_NAME);
        String menuType = content.optString("menuType");
        String openType = content.optString("openType");
        if ("0".equals(menuType)) {
            if ("0".equals(openType)) {
                mHandler.sendEmptyMessage(MainActivity.LEFT_MENU_OPEN);
            } else if ("1".equals(openType)) {
                mHandler.sendEmptyMessage(MainActivity.RIGHT_MENU_OPEN);
            }
        } else if ("1".equals(menuType)) {
            if ("0".equals(openType)) {
                mHandler.sendEmptyMessage(MainActivity.LEFT_MENU_CLOSE);
            } else if ("1".equals(openType)) {
                mHandler.sendEmptyMessage(MainActivity.RIGHT_MENU_CLOSE);
            }
        }
    }


}
