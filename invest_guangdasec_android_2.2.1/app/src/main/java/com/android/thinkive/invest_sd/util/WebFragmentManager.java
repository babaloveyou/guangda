package com.android.thinkive.invest_sd.util;

import android.text.TextUtils;

import com.android.thinkive.framework.WebViewManager;
import com.android.thinkive.invest_sd.fragment.WebCacheFragment;

import java.util.HashMap;

/**
 * WebFragment管理类，负责给所有WebFragment做预加载，并提供索引Map让Activity类能够找到所需的WebFragment
 * Announcements：
 *
 * @author 王志鸿
 * @company Thinkive
 * @date 2015/10/15
 */
public class WebFragmentManager {

    /**
     * 本类的单例对象
     */
    private static WebFragmentManager sInstance;

    /**
     * 收集所有的webFragment，让Activity能够通过key找到自己所需的Fragment
     */
    private HashMap<String, WebCacheFragment> mCacheFragmentHashMap;
    /**
     * 框架的webview管理类，管理所有webFragment中用到的webview
     */
    private WebViewManager mWebViewManager;

    /**
     * 单例模式的私有构造方法
     */
    private WebFragmentManager() {
        mWebViewManager = WebViewManager.getInstance();
        mCacheFragmentHashMap = new HashMap<String, WebCacheFragment>();
    }

    /**
     * 获取本类单例对象的方法
     *
     * @return 本类单例对象
     */
    public static synchronized WebFragmentManager getInstance() {
        if (sInstance == null) {
            sInstance = new WebFragmentManager();
        }
        return sInstance;
    }

    /**
     * 添加一个WebCacheFragment到{@link #mCacheFragmentHashMap}中
     * @param key 添加到本类的Map中的key
     * @param fragment 要被添加的WebCacheFragment对象
     */
    public void putCacheFragment(String key, WebCacheFragment fragment){
        mCacheFragmentHashMap.put(key, fragment);
    }

    /**
     * 从{@link #mCacheFragmentHashMap}中移除一个WebCacheFragment对象
     * @param key 要被移除的对象的key
     */
    public void removeCacheFragment(String key) {
        mCacheFragmentHashMap.remove(key);
    }

    /**
     * 从{@link #mCacheFragmentHashMap}中获取一个WebCacheFragment对象
     * @param key
     * @return
     */
    public WebCacheFragment getWebCacheFragment(String key) {
        return mCacheFragmentHashMap.get(key);
    }

    /**
     * 预加载{@link #mCacheFragmentHashMap}中某个fragment的webview
     * 加载的是fragment对象中设置的url
     * @param key 要进行预加载的fragment的key
     */
    public void preLoadUrl(String key) {
        WebCacheFragment fragment = mCacheFragmentHashMap.get(key);
        // 获取要加载的url
        String url = fragment.getUrl();
        // 检查要加载的url是否为空
        if (TextUtils.isEmpty(url)) {
            throw new IllegalArgumentException("url is empty!");
        }
        mWebViewManager.preLoad(url, fragment.returnWebViewName(), true);
    }

    /**
     * 预加载{@link #mCacheFragmentHashMap}中某个fragment的webview，
     * 加载的是入参中设定的url，并更新这个fragment中webview的url
     * @param key 要进行预加载的fragment的key
     * @param url 加载的url
     */
    public void preLoadUrl(String key, String url) {
        // 检查要加载的url是否为空
        if (TextUtils.isEmpty(url)) {
            throw new IllegalArgumentException("url is empty!");
        }
        WebCacheFragment fragment = mCacheFragmentHashMap.get(key);
        fragment.setUrl(url);
        mWebViewManager.preLoad(url, fragment.returnWebViewName(), true);
    }

    /**
     * 预加载{@link #mCacheFragmentHashMap}中某个fragment的webview
     * 加载的是fragment对象中设置的url
     * @param key 要进行预加载的fragment的key
     * @param isReload 是否是重复加载
     */
    public void preLoadUrl(String key, boolean isReload) {
        WebCacheFragment fragment = mCacheFragmentHashMap.get(key);
        // 获取要加载的url
        String url = fragment.getUrl();
        // 检查要加载的url是否为空
        if (TextUtils.isEmpty(url)) {
            throw new IllegalArgumentException("url is empty!");
        }
        mWebViewManager.preLoad(url, fragment.returnWebViewName(), isReload);
    }
}
