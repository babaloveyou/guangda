package com.android.thinkive.invest_sd.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.thinkive.framework.message.AppMessage;
import com.android.thinkive.framework.module.IWebModule;
import com.android.thinkive.framework.util.Log;

/**
 * 开户单独定义一个模块用来收消息
 */
public class OpenAccountFragment extends WebCacheFragment {
    public OpenAccountFragment() {
        super();
        Log.d("@@@@@@新建了一个开户实例");
    }
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
//    }
//
//    @Override
//    public String onMessageReceive(AppMessage appMessage) {
//        Log.d("@@@@@@开户实例接到消息");
//        return super.onMessageReceive(appMessage);
//    }
}
