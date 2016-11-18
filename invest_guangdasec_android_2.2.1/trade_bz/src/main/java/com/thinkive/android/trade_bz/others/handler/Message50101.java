package com.thinkive.android.trade_bz.others.handler;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.android.thinkive.framework.message.AppMessage;
import com.android.thinkive.framework.message.MessageManager;
import com.thinkive.android.trade_bz.a_stock.activity.TradeLoginActivity;
import com.thinkive.android.trade_bz.others.constants.Constants;
import com.thinkive.android.trade_bz.utils.ToastUtil;

import org.json.JSONObject;

/**
 * Created by Administrator on 2016/11/3.
 */
public class Message50101 implements com.android.thinkive.framework.message.IMessageHandler {
    private Context mContext;
    private String moduleName;

    @Override
    public String handlerMessage(Context context, AppMessage appMessage) {
        mContext = context;
        JSONObject content = appMessage.getContent();
        moduleName = content.optString("moduleName");
        JSONObject paramObj = (JSONObject) content.opt("params");
        String loginType = paramObj.optString("loginType");
        String toPage = paramObj.optString("to_page");
        if ("login".equals(moduleName)) {
            try {
                Intent intent = new Intent(mContext, TradeLoginActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString(Constants.LOGIN_TYPE, loginType);
                bundle.putString(Constants.TOH5PAGE, toPage);
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            } catch (Exception e) {
                ToastUtil.showToast(e.toString());

            }

        }
        return MessageManager.getInstance(context).buildMessageReturn(1, null, null);
    }
}
