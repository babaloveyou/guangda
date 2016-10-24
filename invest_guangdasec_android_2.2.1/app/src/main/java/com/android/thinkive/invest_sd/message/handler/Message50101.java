package com.android.thinkive.invest_sd.message.handler;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.text.TextUtils;

import com.android.thinkive.framework.config.ConfigManager;
import com.android.thinkive.framework.fragment.BaseWebFragment;
import com.android.thinkive.framework.message.AppMessage;
import com.android.thinkive.framework.message.IMessageHandler;
import com.android.thinkive.framework.message.MessageManager;
import com.android.thinkive.framework.module.ModuleManager;
import com.android.thinkive.framework.util.Constant;
import com.android.thinkive.framework.util.Log;
import com.android.thinkive.invest_sd.activities.ActivateActivity;
import com.android.thinkive.invest_sd.activities.CommWebActivity;
import com.android.thinkive.invest_sd.activities.LoginActivity;
import com.android.thinkive.invest_sd.activities.MainActivity;
import com.android.thinkive.invest_sd.activities.WebViewActivity;
import com.android.thinkive.invest_sd.fragment.OpenAccountFragment;
import com.android.thinkive.invest_sd.fragment.WebCacheFragment;
import com.android.thinkive.invest_sd.util.ThinkiveUtil;
import com.android.thinkive.invest_sd.util.WebFragmentManager;

import org.json.JSONObject;

import java.util.List;


/**
 * Created by liujianwei on 15/6/30.
 */
public class Message50101 implements IMessageHandler {
    private Handler mHandler;
    private MainActivity.FragmentAdapter mAdapter;
    private Context mContext;
    private MainActivity mActivity;

    public Message50101(Handler handler, MainActivity.FragmentAdapter adapter) {
        mHandler = handler;
        mAdapter = adapter;
    }

    @Override
    public String handlerMessage(Context context, AppMessage appMessage) {
        mContext = context;
        mActivity = (MainActivity) context;
        JSONObject content = appMessage.getContent();
        JSONObject params = content.optJSONObject("params");
        if(params!=null) {
            MainActivity.moduleName = params.optString(Constant.MODULE_NAME);
            MainActivity.toPage = params.optString("toPage");
        }
        String targmoduleName = content.optString(Constant.MODULE_NAME);
        if("login".equals(targmoduleName)){
            startLogin(MainActivity.moduleName);
        }else if("activate".equals(targmoduleName)){
            if(mActivity.getCuItem() == 2) {
                Intent intent = new Intent(mActivity, ActivateActivity.class);
                mActivity.startActivityForResult(intent, MainActivity.REUQEST_ACTIVATE);
            }
        }else{
            startModule(targmoduleName, content);
        }
        return MessageManager.getInstance(context).buildMessageReturn(1, null, null);
    }


    /**
     * 打开登录
     */
    private void startLogin(String moduleName){
        if (ModuleManager.getInstance().getModule(moduleName) instanceof Fragment) {
            Fragment fragment = (Fragment) ModuleManager.getInstance().getModule(moduleName);
            int item = mAdapter.getmFragments().indexOf(fragment);
            Message msg = new Message();
            msg.what = MainActivity.SET_CURRENT_ITEM;
            msg.arg1 = MainActivity.REUQEST_LOGIN;
            msg.arg2 = item;
            mHandler.sendMessage(msg);
        }else{
            if (!isTopActivity(mActivity.getClass().getName())) {
                Intent intent = new Intent(mActivity, LoginActivity.class);
                mActivity.startActivityForResult(intent, mActivity.REUQEST_LOGIN);
            }
        }
    }
    /**
     * 打开对应模块
     *
     * @param content
     */
    private void startModule(String moduleName, JSONObject content) {
        if(moduleName.equals("comm_web")){
            JSONObject params = content.optJSONObject("params");
            Intent intent = new Intent(mActivity, WebViewActivity.class);
            intent.putExtra("url", params.optString("url"));
            intent.putExtra("title",  params.optString("title"));
            intent.putExtra("statusColor","#1199EE");
            mActivity.startActivity(intent);
            return;
        }else if(moduleName.equals("comm_h5")){
            JSONObject params = content.optJSONObject("params");
            String toPage = params.optString("toPage");
            if(!toPage.startsWith("m"))
                toPage = "m"+toPage;
            String url = ConfigManager.getInstance(mActivity).getAddressConfigValue("H5_url_pre")+toPage;
            String preUrl = ThinkiveUtil.getPreUrl(url);
            Log.d("@@@读取的url +++" +preUrl);
            WebCacheFragment webCacheFragment = WebFragmentManager.getInstance().getWebCacheFragment(preUrl);
            if(webCacheFragment!=null){
                int item = mAdapter.getmFragments().indexOf(webCacheFragment);
                if (item >= 0) {
                    Message msg = new Message();
                    msg.what = MainActivity.SET_CURRENT_ITEM;
                    msg.arg1 = item;
                    msg.obj = content;
                    mHandler.sendMessage(msg);
                }else{
                    openH5Page(toPage);
                    }
                }else{
                openH5Page(toPage);
            }
            return;
        }
        if (ModuleManager.getInstance().getModule(moduleName) instanceof Fragment) {
            Fragment fragment = (Fragment) ModuleManager.getInstance().getModule(moduleName);
            //判断MainActivity是否是栈顶activity，如不是，则start MainActivity
            if (!isTopActivity(mActivity.getClass().getName())) {
                Intent intent = new Intent(mContext, MainActivity.class);
                mContext.startActivity(intent);
            }
            int item = mAdapter.getmFragments().indexOf(fragment);
            Log.d("moduleName" + moduleName);
            if (item >= 0) {
                Message msg = new Message();
                msg.what = MainActivity.SET_CURRENT_ITEM;
                msg.arg1 = item;
                msg.obj = content;
                mHandler.sendMessage(msg);
            }else{
                if(fragment instanceof BaseWebFragment) {
                    JSONObject params = content.optJSONObject("params");
                    String toPage = params.optString("toPage");
                    openH5Page(toPage);
                }
            }
        } else if (ModuleManager.getInstance().getModule(moduleName) instanceof Activity) {
            Activity activity = (Activity) ModuleManager.getInstance().getModule(moduleName);
            Intent intent1 = new Intent(mContext, activity.getClass());
            mContext.startActivity(intent1);
        }else {
            JSONObject params = content.optJSONObject("params");
            String toPage = params.optString("toPage");
            if(!toPage.startsWith("m"))
                toPage = "m"+toPage;
            String url = ConfigManager.getInstance(mActivity).getAddressConfigValue("H5_url_pre")+toPage;
            String preUrl = ThinkiveUtil.getPreUrl(url);
            Log.d("@@@读取的url +++" +preUrl);
            WebCacheFragment webCacheFragment = WebFragmentManager.getInstance().getWebCacheFragment(preUrl);
            if(webCacheFragment!=null){
                int item = mAdapter.getmFragments().indexOf(webCacheFragment);
                if (item >= 0) {
                    Message msg = new Message();
                    msg.what = MainActivity.SET_CURRENT_ITEM;
                    msg.arg1 = item;
                    msg.obj = content;
                    mHandler.sendMessage(msg);
                }else{
                    openH5Page(toPage);
                }
            }else{
                openH5Page(toPage);
            }
        }


    }


    /**
     * 打开一个H5页面。延时打开，以防出现同一模块的上一个页面残留
     * 但是，打开“开户”模块的H5，需要用到下面的方法{@link #)}
     *
     * @param url
     *         被打开的H5页面的Url
     */
    private void openH5Page( String url) {
        if(!url.startsWith("m"))
            url = "m"+url;
        final String loadurl = ConfigManager.getInstance(mActivity).getAddressConfigValue("H5_url_pre")+url;
        final String preUrl = ThinkiveUtil.getPreUrl(url);
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
        WebCacheFragment webCacheFragment = WebFragmentManager.getInstance().getWebCacheFragment(preUrl);
        if (webCacheFragment == null) {
            if(preUrl.contains("m/open/index")){
                webCacheFragment = new OpenAccountFragment();
            }else {
                webCacheFragment = new WebCacheFragment();
            }
            WebFragmentManager.getInstance().putCacheFragment(preUrl, webCacheFragment);
        }
                if(loadurl.contains("m/ggt/index")){
                    webCacheFragment.setNeedLoadUrl(loadurl);
                    webCacheFragment.setUrl(loadurl);
                }else if(loadurl.contains("m/xdt/index")){
                    webCacheFragment.setNeedLoadUrl(loadurl);
                    webCacheFragment.setUrl(loadurl);
                }else {
                    webCacheFragment.preloadUrl(mActivity, loadurl);
                    webCacheFragment.setUrl(loadurl);
                }
                final Intent intent = new Intent(mActivity, CommWebActivity.class);
                intent.putExtra(CommWebActivity.BUNDLE_KEY_FRAGMENT_KEY, preUrl);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mActivity.startActivity(intent);
                    }
                }, 150);
            }
        });
    }

    private boolean isTopActivity(String activityClassName) {
        boolean isTop = false;
        if (TextUtils.isEmpty(activityClassName)) {
            return isTop;
        }

        if (activityClassName.equals(getTopActivity())) {
            isTop = true;
        }
        Log.d("activity = " + activityClassName + " isTop = " + isTop);
        return isTop;
    }

    private String getTopActivity() {
        ActivityManager manager = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> runningTaskInfos = manager.getRunningTasks(1);

        if (runningTaskInfos != null) {
            ComponentName componentName = runningTaskInfos.get(0).topActivity;
            String className = componentName.getClassName();
            return className;
        } else {
            return null;
        }

    }
}
