package com.android.thinkive.invest_sd.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.thinkive.framework.fragment.BaseWebFragment;
import com.android.thinkive.framework.message.AppMessage;
import com.android.thinkive.framework.message.MessageManager;
import com.android.thinkive.framework.module.IWebModule;
import com.android.thinkive.framework.module.ModuleManager;
import com.android.thinkive.framework.theme.ThemeManager;
import com.android.thinkive.framework.util.Log;
import com.android.thinkive.invest_sd.activities.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by xiangfan on 2015/11/3.
 */
public class UserCenterFragment  extends WebCacheFragment implements IWebModule {
    public UserCenterFragment(){
        ModuleManager.getInstance().registerModule(this);
    }
    public final static String assert_url = "file:///android_asset/www/m/index/index.html#!/account/userCenter.html";
    public final static String url = "www/m/index/index.html#!/account/userCenter.html";

}