package com.thinkive.android.trade_bz.a_stock.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;

import com.android.thinkive.framework.fragment.BaseWebFragment;
import com.android.thinkive.framework.message.AppMessage;
import com.android.thinkive.framework.message.IMessageHandler;
import com.android.thinkive.framework.message.handler.Message50041;
import com.android.thinkive.framework.module.IModule;
import com.android.thinkive.framework.module.ModuleManager;
import com.android.thinkive.framework.util.Constant;
import com.android.thinkive.framework.util.Log;
import com.thinkive.android.trade_bz.others.constants.Constants;
import com.thinkive.android.trade_bz.others.handler.Message50101;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2016/11/10.
 */
public class NewStockWebFragment extends BaseWebFragment implements IModule {
    private String mSourceModule;
    private String mWebViewName;
    private String mUrl;
    private boolean isH5Prepare = false;
    private BroadcastReceiver mToH5PageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String toH5Page = intent.getStringExtra(Constants.TOH5PAGE);
            String stock_credit_flag = intent.getStringExtra(Constants.STOCK_CREDIT_FLAG);
            try {
                sendMessage60250(NewStockWebFragment.this, toH5Page,stock_credit_flag);
            } catch (JSONException e) {
                Log.e(e.getMessage());
            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ModuleManager.getInstance().registerModule(this, "new-stock");
        IntentFilter filter = new IntentFilter();
        filter.addAction(Constants.ACTION_TO_H5_PAGE);
        getContext().registerReceiver(mToH5PageReceiver, filter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setBackListener();
    }

    private void setBackListener() {
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                    sendMessage50107(NewStockWebFragment.this);
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null && savedInstanceState.getInt("loadurl") == 1) {
            loadUrl(mUrl);
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (!isVisibleToUser) {
            isH5Prepare = false;
        }
    }

    @Override
    public String returnWebViewName() {
        return mWebViewName;
    }

    public void setWebViewName(String mWebViewName) {
        this.mWebViewName = mWebViewName;
    }

    public void setUrl(String mUrl) {
        this.mUrl = mUrl;
    }

    @Override
    public String onMessageReceive(AppMessage appMessage) {
        int msgId = appMessage.getMsgId();
        IMessageHandler messageHandler = null;
        Log.d("home module message = " + appMessage.getContent());
        mSourceModule = appMessage.getContent().optString(Constant.MODULE_NAME);
        switch (msgId) {
            case 50100:
                isH5Prepare = true;
                break;
            case 50041:
                messageHandler = new Message50041();
                break;
            case 50101:
                messageHandler = new Message50101();
            case 50114:
                getActivity().finish();
            default:
                break;
        }
        if (messageHandler != null) {
            return messageHandler.handlerMessage(getActivity(), appMessage);
        }
        return null;
    }
    private void sendMessage60250(BaseWebFragment baseWebFragment,String toPage,String stock_credit_flag) throws JSONException {
        JSONObject param = new JSONObject();
        param.put("moduleName", "trade");
        param.put("to_page",toPage);
        param.put("stock_credit_flag",stock_credit_flag);
        AppMessage appMessage = new AppMessage(60250, param);
        baseWebFragment.sendMessageToH5(appMessage);
    }
    @Override
    public void onDetach() {
        super.onDetach();
//        destroyWebView();
    }
    /**
     * 将返回键传给H5处理
     *
     * @param baseWebFragment
     */
    private void sendMessage50107(BaseWebFragment baseWebFragment) {
        JSONObject param = new JSONObject();
        AppMessage appMessage = new AppMessage(50107, param);
        baseWebFragment.sendMessageToH5(appMessage);
    }

    public void destroyWebView() {
        if (getWebView() != null) {
            getWebView().clearHistory();
            getWebView().clearCache(true);
            getWebView().loadUrl("about:blank"); // clearView() should be changed to loadUrl("about:blank"), since clearView() is deprecated now
            getWebView().freeMemory();
            getWebView().pauseTimers();
        }
    }
}
