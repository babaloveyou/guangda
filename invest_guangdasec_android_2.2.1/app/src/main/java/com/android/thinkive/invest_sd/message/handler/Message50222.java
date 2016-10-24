package com.android.thinkive.invest_sd.message.handler;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;

import com.android.thinkive.framework.message.AppMessage;
import com.android.thinkive.framework.message.IMessageHandler;
import com.android.thinkive.framework.message.MessageManager;
import com.android.thinkive.invest_sd.activities.MainActivity;

/**
 * Created by liujianwei on 15/6/30.
 */
public class Message50222 implements IMessageHandler {
    private MainActivity mActivity;

    @Override
    public String handlerMessage(Context context, AppMessage appMessage) {
        mActivity = (MainActivity) context;
        Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        mActivity.startActivityForResult(intent, MainActivity.PICK_CONTACT);
        return MessageManager.getInstance(mActivity).buildMessageReturn(1, null, null);
    }
}
