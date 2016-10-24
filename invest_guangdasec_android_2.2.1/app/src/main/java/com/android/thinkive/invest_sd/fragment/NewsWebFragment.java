package com.android.thinkive.invest_sd.fragment;

import android.os.Bundle;

import com.android.thinkive.framework.CoreApplication;
import com.android.thinkive.framework.module.IWebModule;
import com.android.thinkive.framework.module.ModuleManager;
import com.android.thinkive.framework.util.Log;

/**
 * Created by xiangfan on 2016/6/28.
 */
public class NewsWebFragment  extends WebCacheFragment implements IWebModule {
    public NewsWebFragment(){
        ModuleManager.getInstance().registerModule(this,"newsWeb");
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){
            loadUrl(url);
        }
    }

    public final static String assert_url = "file:///android_asset/www/m/news/index.html";
    public final static String url = "www/m/news/index.html";
}
