package com.android.thinkive.invest_sd.activities;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.thinkive.framework.activity.BaseWebActivity;
import com.android.thinkive.framework.message.AppMessage;
import com.android.thinkive.framework.message.IMessageHandler;
import com.android.thinkive.framework.message.MessageManager;
import com.android.thinkive.framework.module.IWebModule;
import com.android.thinkive.framework.module.ModuleManager;
import com.android.thinkive.invest_sd.R;
import com.android.thinkive.invest_sd.message.handler.Message50114;
import com.umeng.analytics.MobclickAgent;

/**
 * Created by zhuduanchang on 2015/8/7.
 */
public class WebViewActivity extends BaseWebActivity implements IWebModule {
    private String mUrl;
    private String mTitle = "",mStatusColor ="";
    private int color = 0x188ACF;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ModuleManager.getInstance().registerModule(this);
        try {
            mUrl = getIntent().getStringExtra("url");
            mTitle= getIntent().getStringExtra("title");
            mStatusColor = getIntent().getStringExtra("statusColor");
        }catch (Exception e){

        }
        if(mStatusColor.isEmpty()){
            mStatusColor = "#188ACF";
        }
        if(mTitle!=null&&!mTitle.isEmpty()){
            View view = View.inflate(WebViewActivity.this, R.layout.title_view,null);
            TextView title = (TextView)view.findViewById(R.id.title);
            ImageView back = (ImageView)view.findViewById(R.id.back);
            title.setText(mTitle);
          
            try {
                color = Color.parseColor(mStatusColor);
            }catch (Exception e){
                color = 0xffee1308;
            }
            view.setBackgroundColor(color);
            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
            setTitleBar(view);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(color);
        }
        loadUrl(mUrl);
    }

    @Override
    public String returnWebViewName() {
        return null;
    }


    @Override
    public void sendMessageByWebModule(AppMessage appMessage) {
        sendMessageToH5(appMessage);
    }

    @Override
    public String onMessageReceive(AppMessage appMessage) {
        int msgId = appMessage.getMsgId();
        IMessageHandler messageHandler = null;
        switch (msgId) {
            case 50100://通知原生H5模块加载完毕

                return MessageManager.getInstance(this).buildMessageReturn(1, null, null);
            case 50114:
                messageHandler = new Message50114();
                break;
            default:
                break;
        }
        if (messageHandler != null) {
            return messageHandler.handlerMessage(WebViewActivity.this, appMessage);
        }
        return null;
    }
    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}
