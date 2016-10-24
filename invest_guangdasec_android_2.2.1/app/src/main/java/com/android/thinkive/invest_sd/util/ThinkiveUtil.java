package com.android.thinkive.invest_sd.util;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListAdapter;

import com.android.thinkive.framework.WebViewManager;
import com.android.thinkive.framework.util.Log;
import com.android.thinkive.invest_sd.fragment.MallFragment;
import com.android.thinkive.invest_sd.fragment.OpenAccountFragment;
import com.android.thinkive.invest_sd.fragment.TradeFragment;
import com.android.thinkive.invest_sd.fragment.UserCenterFragment;
import com.android.thinkive.invest_sd.fragment.WebCacheFragment;

/**
 * 一些不好分类的Util
 * Announcements：
 *
 * @author 王志鸿
 * @company Thinkive
 * @date 2015/9/10
 */
public class ThinkiveUtil {

    /**
     * 过滤网络请求地址，只留下ip及其之前的东西
     *
     * @param originalAddress
     *         过滤前的地址
     * @return 过滤后的、只留下ip及其之前的东西的地址
     */
    public static String formatUrlToIp(String originalAddress) {
        String result = "";
        String[] parts = originalAddress.split("/");
        StringBuffer stringBuffer = new StringBuffer();
        if (parts.length >= 3) {
            stringBuffer.append(parts[0]);
            stringBuffer.append(parts[1]);
            stringBuffer.append("//");
            stringBuffer.append(parts[2]);
        }
        result = stringBuffer.toString();
        return result;
    }

    /**
     * 动态设置GridView的高度，其中要求这个GridView的列数固定，然后本方法根据行数设置GridView的高度
     *
     * @param col
     *         GridView的列数
     * @param gridView
     *         要动态设置高度的GridView
     * @param expectHeight
     *         额外补充的高度
     */
    public static void setGridViewHeight(int col, GridView gridView, int expectHeight, int spacePx) {
        // 获取listview的adapter
        ListAdapter listAdapter = gridView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        // i每次加4，相当于listAdapter.getCount()小于等于4时 循环一次，计算一次item的高度，
        // listAdapter.getCount()小于等于8时计算两次高度相加
        int listAdapterCount = listAdapter.getCount();
        for (int i = 0; i < listAdapterCount; i += col) {
            // 获取listview的每一个item
            View listItem = listAdapter.getView(i, null, gridView);
            listItem.measure(0, 0);
            // 获取item的高度和
            totalHeight += listItem.getMeasuredHeight() + spacePx;
        }
//        if (listAdapterCount == 1) {
        totalHeight -= spacePx;
//        }

        // 获取listview的布局参数
        ViewGroup.LayoutParams params = gridView.getLayoutParams();
        // 设置高度
        params.height = totalHeight + expectHeight + 20;
//        // 设置margin
//        ((ViewGroup.MarginLayoutParams) params).setMargins(10, 10, 10, 10);
        // 设置参数
        gridView.setLayoutParams(params);
    }

    /**
     * 隐藏系统自带的键盘
     *
     * @param activity
     *         外部调用环境
     */
    public static void hideSystemKeyBoard(Activity activity) {
        // 收起系统键盘
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(activity.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(activity.getWindow().getDecorView().getWindowToken(), 0);
        }
    }

    /**
     * 设置让一个EditText获取焦点并弹出键盘
     *
     * @param activity
     *         外部调用的activity
     * @param editText
     *         键盘的目标EditText
     * @param isShowSystemKeyboard
     *         是否弹出系统的键盘，如果为false，那么弹出的是框架的自绘键盘
     */
    public static void showKeyBoard(final Activity activity, final EditText editText, final boolean isShowSystemKeyboard) {
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        // 延迟半秒弹出键盘，防止因为界面未加载完而导致键盘弹不出来
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isShowSystemKeyboard) { // 强行弹出系统键盘的代码
                    InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(editText, InputMethodManager.SHOW_FORCED);
                } else { // 执行一次EditText的点击事件就可以弹出自绘键盘
                    editText.performClick();
                }
//                editText.performClick();
            }
        }, 500);
    }

    /**
     * 获取一个Url的“#！”之前的东西
     *
     * @param url
     *         完整的Url
     */
    public static String getPreUrl(String url) {
        String results = "";
        int nameEndPos = url.indexOf("#!/");
        // 判断url是否包含“#！”，
        if (nameEndPos > 0) { // 包含则用“#！”前的内容作为webview的名字
            results = url.substring(0, nameEndPos);
        } else { // 不包含就直接用整个url作为webview的名字
            results = url;
        }
        return results;
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }


    /**
     根据url获取缓存的fragment，如果没有就新建
     */
    public static WebCacheFragment getWebviewFragment(String url) {
        String preUrl = ThinkiveUtil.getPreUrl(url);
        WebCacheFragment mWebCacheFragment;
        WebFragmentManager webFragmentManager = WebFragmentManager.getInstance();
        mWebCacheFragment = webFragmentManager.getWebCacheFragment(preUrl);
        if (mWebCacheFragment == null) {
            Log.d("@@@@fragment不存在去重建");
            mWebCacheFragment =  getpreloadWebview(url);
        }else{
            mWebCacheFragment.setUrl(url);
            mWebCacheFragment = webFragmentManager.getWebCacheFragment(preUrl);
        }
        return mWebCacheFragment;
    }


    /**
     * 预加载一个url，并将预加载这个url的webview的名字命名为url的前缀。
     * 框架将会保存这个webview对象，并在需要同名的webview时进行复用
     *
     * @param url
     *         预加载的url，同时也用其#！前的字符给webview的命名
     */
    public static void  preloadWebview(String url) {
        String webviewName = ThinkiveUtil.getPreUrl(url);
        WebViewManager.getInstance().preLoad(url, webviewName, false);
        WebCacheFragment webCacheFragment;
        webCacheFragment = WebFragmentManager.getInstance().getWebCacheFragment(webviewName);
        if(webCacheFragment!=null)
            return;
            if(webviewName.contains("m/open/index")){
                Log.d("@@@@@@去新建开户实例");
                webCacheFragment = new OpenAccountFragment();
            }else if(webviewName.contains("m/mall/index")) {
                Log.d("@@@@@@去新建开户实例");
                webCacheFragment = new MallFragment();
            }else if(webviewName.contains("m/index/index")) {
                webCacheFragment = new UserCenterFragment();
            } else if(webviewName.contains("m/trade/index")) {
                webCacheFragment = new TradeFragment();
            }
            else
            {
                webCacheFragment = new WebCacheFragment();
            }
        webCacheFragment.setUrl(url);
        WebFragmentManager.getInstance().putCacheFragment(webviewName, webCacheFragment);
    }

    public static WebCacheFragment getpreloadWebview(String url) {
        String webviewName = ThinkiveUtil.getPreUrl(url);
        WebViewManager.getInstance().preLoad(url, webviewName, false);
        WebCacheFragment webCacheFragment;
        webCacheFragment = WebFragmentManager.getInstance().getWebCacheFragment(webviewName);
        if(webCacheFragment!=null)
            return webCacheFragment;
        Log.d("@@@@@@地址" + webviewName);
        if(webviewName.contains("m/open/index")){
            webCacheFragment = new OpenAccountFragment();
        }else if(webviewName.contains("m/mall/index")) {
            webCacheFragment = new MallFragment();
        }else if(webviewName.contains("m/index/index")) {
            webCacheFragment = new UserCenterFragment();
        } else if(webviewName.contains("m/trade/index")) {
            webCacheFragment = new TradeFragment();
        }   else    {
            webCacheFragment = new WebCacheFragment();
        }
        webCacheFragment.setUrl(url);
        WebFragmentManager.getInstance().putCacheFragment(webviewName, webCacheFragment);
        return webCacheFragment;
    }

    /**
     * 根据服务器返回的标志位，确定标志位的含义，是否是要求进行强制更新
     * 0：表示不进行强制更新；1：表示进行强制更新
     *
     * @param updateFlag 服务器返回的标志位
     * @return 是否是要求进行强制更新
     */
    public static boolean isForceUpgrade(String updateFlag) {
        boolean result = false;
        if (TextUtils.isEmpty(updateFlag)) {
            return result;
        }
        if (updateFlag.equals("0")) {
            result = false;
        } else if (updateFlag.equals("1")) {
            result = true;
        }
        return result;
    }


    /**
     * 格式化资讯详情内容，将“\n、\r”等格式字符，替换成对应的html格式符
     */
    public static String formatInfoContent(String originalContent) {
        String result = originalContent.replaceAll("\n\r", "<br>");
        result = result.replaceAll("\n", "<br>");
        return result;
    }

}
