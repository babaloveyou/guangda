package com.android.thinkive.invest_sd.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.thinkive.framework.CoreApplication;
import com.android.thinkive.framework.WebViewManager;
import com.android.thinkive.framework.config.ConfigManager;
import com.android.thinkive.framework.util.Log;
import com.android.thinkive.framework.util.PreferencesUtil;
import com.android.thinkive.framework.view.MyWebView;
import com.android.thinkive.invest_sd.R;
import com.android.thinkive.invest_sd.constants.Constant;
import com.android.thinkive.invest_sd.fragment.LoanFragment;
import com.android.thinkive.invest_sd.fragment.MallFragment;
import com.android.thinkive.invest_sd.fragment.OpenAccountFragment;
import com.android.thinkive.invest_sd.fragment.UserCenterFragment;
import com.android.thinkive.invest_sd.fragment.WebCacheFragment;
import com.android.thinkive.invest_sd.util.FileCacheToSdcardUtil;
import com.android.thinkive.invest_sd.util.ThinkiveUtil;
import com.android.thinkive.invest_sd.util.WebFragmentManager;
import com.thinkive.aqf.utils.ConfigUtil;
import com.umeng.analytics.MobclickAgent;


/**
 * Created by zhuduanchang on 2015/6/19.
 * 启动页
 */
public class LauncherActivity extends FragmentActivity {
    private static final int LAUNCHER_TIME = 1100;
    private static final int LAUNCHER_FLAG = 0x1000;
    public static final String IS_FIRST_LAUNCHER = "is_first_launcher";
    private FrameLayout mContainer;
    private TextView but_start_junmp;
    private  int starttime = 4;//4秒启动图
    private  int Nousetime =0;
    private boolean isjump = false;
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if(isjump){
                return;
            }
            Nousetime++;
            if(Nousetime>=starttime){
                if (isFirstLauncher(LauncherActivity.this)) {
                    startGuidePage();
                } else {
                    startMainActivity();
                }
            }else {
                but_start_junmp.setText("跳过 "+(starttime-Nousetime)+"S");
                mHandler.postDelayed(this, LAUNCHER_TIME);
            }
        }
    };
    private Handler mHandler = new Handler() ;

    /**
     * 接收m.zip文件解压完成广播
     */
    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(WebViewManager.BROADCAST_H5_UNZIP_COMPLETE)) {
                //开始预加载下一个界面需要用到的H5
                Nousetime = 0;
                mHandler.postDelayed(runnable, LAUNCHER_TIME);
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        //隐藏标题栏
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_launcher);
        ConfigUtil.init(this);
        but_start_junmp = (TextView) findViewById(R.id.but_start_junmp);
        ImageView bg = (ImageView) findViewById(R.id.iv_launcher_bg);
        Bitmap bitmap = FileCacheToSdcardUtil.getBitmap(Constant.LAUNCHER_PIC,LauncherActivity.this );
        if(bitmap!=null){
            bg.setImageBitmap(bitmap);
        }else{
            bg.setImageResource(R.drawable.ic_launcher_bg);
        }


        mContainer = (FrameLayout) findViewById(R.id.fv_webview_container);
        /**
         *  初始化configuration.xml文件中，指定的webview个数，之所以把这些webview都加入到mContainer中，
         *  是为了能渲染出webview的宽高，这样在预加载h5的时候，h5就能获取到webivew的宽高；
         */
        for (MyWebView webView : WebViewManager.getInstance().getFixedWebViewList()) {
            try {
                ((ViewGroup) webView.getParent()).removeView(webView);
            }catch (Exception e){

            }
            mContainer.addView(webView);
        }
        IntentFilter filter = new IntentFilter();
        filter.addAction(WebViewManager.BROADCAST_H5_UNZIP_COMPLETE);
        registerReceiver(mReceiver, filter);
        //判断是否支持H5升级，根据asset目录下是否放有m.zip文件来决定；
        if (WebViewManager.getInstance().isSupportH5Upgrade()) {
            WebViewManager.unZipState unZipState = WebViewManager.getInstance().getH5UnZipState();
            // 判断m.zip文件的解压状态，如果解压完成，则延迟LAUNCHER_TIME后，进入应用
            if (unZipState == WebViewManager.unZipState.STATUS_FINISHED) {
                //开始预加载下一个界面需要用到的H5
//                preLoadUrl();
                Nousetime = 0;
                mHandler.postDelayed(runnable, LAUNCHER_TIME);
            }
            //如果没有解压完成，则通过注册的广播接收器去接受m.zip解压完成广播
            Log.e("unZipState = " + unZipState.ordinal());
        } else {
            //开始预加载下一个界面需要用到的H5
            preLoadAssertUrl();
            Nousetime = 0;
            mHandler.postDelayed(runnable, LAUNCHER_TIME);
        }
        findViewById(R.id.but_start_junmp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runnable =null;
                isjump = true;
                if (isFirstLauncher(LauncherActivity.this)) {
                    startGuidePage();
                } else {
                    startMainActivity();
                }
            }
        });
    }


    private void preLoadAssertUrl(){
        // 加载所有H5模块的首页
        // url前缀
        String urlPre = ConfigManager.getInstance(this).getAddressConfigValue("H5_url_pre");

        // 预加载“小贷”模块
        ThinkiveUtil.preloadWebview(urlPre + "m/xdt/index.html");

        // 预加载“商城”模块
        ThinkiveUtil.preloadWebview(urlPre + "m/mall/index.html");

        // 预加载“我的富尊”模块
//        ThinkiveUtil.preloadWebview(urlPre + "m/index/index.html");

        // 预加载“港股通”模块
        ThinkiveUtil.preloadWebview(urlPre + "m/ggt/index.html");

        // 预加载“开户”模块
        ThinkiveUtil. preloadWebview(urlPre + "m/open/index.html");

        // 预加载“资讯”模块
//        ThinkiveUtil.preloadWebview(urlPre + "m/news/index.html");
//        WebViewManager.getInstance().preLoad(TradeFragment.assert_url, "trade", true);
//        WebViewManager.getInstance().preLoad(LoanFragment.assert_url, "loan", true);
//        WebViewManager.getInstance().preLoad(MallFragment.assert_url, "mall", true);
//        WebViewManager.getInstance().preLoad(UserCenterFragment.assert_url, "user-center", true);
//        WebViewManager.getInstance().preLoad(OpenAccountFragment.assert_url, "open", true);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        CoreApplication.getInstance().exit();
    }

    private void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        for (int i = 0; i < mContainer.getChildCount(); ) {
            mContainer.removeViewAt(i);
        }
        finish();
    }

    private void startGuidePage() {
        Intent intent = new Intent(this, GuideActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        for (int i = 0; i < mContainer.getChildCount(); ) {
            mContainer.removeViewAt(i);
        }
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mReceiver);
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

    /**
     * 是否是第一次启动app
     * @param context
     * @return
     */
    public static boolean isFirstLauncher(Context context) {
        return PreferencesUtil.getBoolean(context, IS_FIRST_LAUNCHER, true);
    }
}
