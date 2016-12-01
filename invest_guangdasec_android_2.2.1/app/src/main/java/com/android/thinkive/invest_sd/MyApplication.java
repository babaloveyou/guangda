package com.android.thinkive.invest_sd;

import android.content.Context;
import android.support.multidex.MultiDex;

import com.android.thinkive.framework.CoreApplication;

/**
 * Created by Administrator on 2016/10/12.
 */

public class MyApplication extends CoreApplication {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }
}
