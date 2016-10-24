package com.android.thinkive.invest_sd.activities;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.provider.ContactsContract;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.view.View;

import com.android.thinkive.framework.compatible.DialogFrame;
import com.android.thinkive.framework.compatible.TKFragmentActivity;
import com.android.thinkive.framework.config.ConfigManager;
import com.android.thinkive.framework.fragment.BaseWebFragment;
import com.android.thinkive.framework.message.AppMessage;
import com.android.thinkive.framework.module.ModuleManager;
import com.android.thinkive.framework.network.ResponseListener;
import com.android.thinkive.framework.network.http.HttpService;
import com.android.thinkive.framework.storage.MemoryStorage;
import com.android.thinkive.framework.util.Log;
import com.android.thinkive.invest_sd.R;
import com.android.thinkive.invest_sd.constants.Constant;
import com.android.thinkive.invest_sd.fragment.WebCacheFragment;
import com.android.thinkive.invest_sd.util.WebFragmentManager;
import com.umeng.analytics.MobclickAgent;

import org.apache.http.entity.mime.content.FileBody;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;


/**
 * 通用的加载H5的Activity
 *
 * Announcements：
 *
 * @author 王志鸿
 * @company Thinkive
 * @date 2015/9/24
 */
public class CommWebActivity extends TKFragmentActivity {

    /**
     * 用于Bundle传递Fragment在{@link WebFragmentManager}中的key的一个常量key
     */
    public static final String BUNDLE_KEY_FRAGMENT_KEY = "fragmentKey";
    /**
     * 加载H5页面的Fragment，由本类对象显示
     */
    protected WebCacheFragment mWebCacheFragment;
    private FragmentManager mFragmentManager;
    private LoginSuccedReceiver receiver=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        receiver=new LoginSuccedReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Constant.ACTION_LOGINEND);
        this.registerReceiver(receiver, filter);
        mFragmentManager = getSupportFragmentManager();
        setContentView(R.layout.activity_for_fragment);
        initViews();
    }

    @Override
    protected void findViews() {

    }

    @Override
    protected void setListeners() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initViews() {
//        mWebCacheFragment = new WebCacheFragment();
//        String url = getIntent().getExtras().getString("url");
//        mWebCacheFragment.setUrl(url);
//        mWebCacheFragment.preloadUrl(this, url);

        String fragmentKey = getIntent().getExtras().getString(BUNDLE_KEY_FRAGMENT_KEY);
        WebFragmentManager webFragmentManager = WebFragmentManager.getInstance();
        mWebCacheFragment = webFragmentManager.getWebCacheFragment(fragmentKey);
        if(mWebCacheFragment!=null) {
            mFragmentManager.beginTransaction().add(R.id.fl_container, mWebCacheFragment).commit();
        }else{
            finish();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) { // 监听手机上的物理返回键
            // 给H5发消息，通知他们用户按了物理返回键
            mWebCacheFragment.sendMessage50107();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        this.unregisterReceiver(receiver);
        super.onDestroy();
    }

    /**
     * 将跳转完传给H5处理
     *
     * @param baseWebFragment
     */
    private void sendMessage50113(BaseWebFragment baseWebFragment, JSONObject param) {
        AppMessage appMessage = new AppMessage(50113, param);
        baseWebFragment.sendMessageToH5(appMessage);
    }

    public class LoginSuccedReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent==null){
                if(MainActivity.toPage.contains("ggt/index"))
                CommWebActivity.this.finish();
                return;
            }
            boolean islogin = intent.getBooleanExtra("islogin",false);

            if(islogin){
                if(!MainActivity.toPage.startsWith("m"))
                    MainActivity.toPage = "m"+MainActivity.toPage;
                String url = ConfigManager.getInstance(CommWebActivity.this).getAddressConfigValue("H5_url_pre")+MainActivity.toPage;
                mWebCacheFragment.loadUrl(url);
            }else{
                if(MainActivity.toPage.contains("ggt/index"))
                CommWebActivity.this.finish();
            }
        }
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
