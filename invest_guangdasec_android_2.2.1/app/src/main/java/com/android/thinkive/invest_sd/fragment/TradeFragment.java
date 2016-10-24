package com.android.thinkive.invest_sd.fragment;

import android.os.Bundle;

import com.android.thinkive.framework.WebViewManager;
import com.android.thinkive.framework.module.IWebModule;
import com.android.thinkive.framework.module.ModuleManager;

/**
 * Created by liujianwei on 15/7/8.
 */
public class TradeFragment extends WebCacheFragment implements IWebModule {
    public TradeFragment(){
        ModuleManager.getInstance().registerModule(this);
    }
//    //不需要支持h5文件单独升级的项目，采用如下url格式
    public final static String assert_url = "file:///android_asset/www/m/trade/index.html#!/account/index.html";

//    //支持H5文件单独升级的项目，采用如下url格式
    public final static String url = "www/m/trade/index.html#!/account/index.html";
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        ModuleManager.getInstance().registerModule(this);
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
//    }
//
//    @Override
//    public String returnWebViewName() {
//        return "trade";
//    }
//
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        WebViewManager.unZipState unZipState = WebViewManager.getInstance().getH5UnZipState();
        // 判断m.zip文件的解压状态，如果解压完成，则延迟LAUNCHER_TIME后，进入应用
        if (unZipState == WebViewManager.unZipState.STATUS_FINISHED) {
            loadUrl(url);
        }
    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
////        if(!MainActivity.islogin){
////            sendMessageByWebModule(new AppMessage(50113,new JSONObject()));
////        }
//        if (ThemeManager.getInstance(getActivity()).getThemeBean() != null) {
//            String styleColor = ThemeManager.getInstance(getActivity()).getThemeBean().getStyleColor();
//            if (styleColor != null) {
////                sendMessage50104(styleColor);
//            }
//        }
//    }
//
//    private void sendMessage50104(String styleColor) {
//        JSONObject param = new JSONObject();
//        try {
//            param.put("theme", styleColor);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        AppMessage appMessage = new AppMessage(50104, param);
//        sendMessageToH5(appMessage);
//    }
//
//
//    @Override
//    public void sendMessageByWebModule(AppMessage appMessage) {
//        Log.d("trade module send message = " + appMessage.getContent());
//        sendMessageToH5(appMessage);
//    }
//
//
//
//    @Override
//    public String onMessageReceive(AppMessage appMessage) {
//        int msgId = appMessage.getMsgId();
//        switch (msgId) {
//            case 50100://通知原生H5模块加载完毕
//
//                return MessageManager.getInstance(getActivity()).buildMessageReturn(1, null, null);
//            default:
//                break;
//        }
//
//        return null;
//    }


}
