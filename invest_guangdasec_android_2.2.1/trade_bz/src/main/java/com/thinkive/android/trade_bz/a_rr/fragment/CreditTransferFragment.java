package com.thinkive.android.trade_bz.a_rr.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

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
import com.thinkive.android.trade_bz.utils.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2016/12/5.
 */
public class CreditTransferFragment extends BaseWebFragment implements IModule {
    private String mSourceModule;
    private String mUrl;
    private boolean canReceive;
    private boolean isH5Prepare = false;
    private BroadcastReceiver mToH5PageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String toH5Page = intent.getStringExtra(Constants.TOH5PAGE);
            String stock_credit_flag = intent.getStringExtra(Constants.STOCK_CREDIT_FLAG);
            try {
                sendMessage60250(CreditTransferFragment.this, toH5Page,stock_credit_flag);
            } catch (JSONException e) {
                Log.e(e.getMessage());
            }
            ToastUtil.showToast(toH5Page+ "   "+stock_credit_flag);
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ModuleManager.getInstance().registerModule(this, "yzzz-credit");
        IntentFilter filter = new IntentFilter();
        filter.addAction(Constants.ACTION_TO_H5_PAGE);
        getContext().registerReceiver(mToH5PageReceiver, filter);
        canReceive = true;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        canReceive = false;
        getContext().unregisterReceiver(mToH5PageReceiver);
    }
    //    @Override
    //    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    //        super.onActivityCreated(savedInstanceState);
    //        setBackListener();
    //    }

    //    private void setBackListener() {
    //        getView().setOnKeyListener(new View.OnKeyListener() {
    //            @Override
    //            public boolean onKey(View v, int keyCode, KeyEvent event) {
    //                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
    //                    sendMessage50107(CreditNewStockFragment.this);
    //                    return true;
    //                }
    //                return false;
    //            }
    //        });
    //    }

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
        return "yzzz-credit";
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
        if (canReceive) {
            switch (msgId) {
                case 50100:
                    isH5Prepare = true;
                    break;
                case 50041:
                    messageHandler = new Message50041();
                    break;
                case 50101:
                    messageHandler = new Message50101();
                    break;
                case 50114:
                    getActivity().finish();
                default:
                    break;
            }
            if (messageHandler != null) {
                return messageHandler.handlerMessage(getActivity(), appMessage);
            }
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
        getContext().unregisterReceiver(mToH5PageReceiver);
    }
    /**
     * 发送50107消息给H5，通知他们，用户单击了手机的物理返回键
     */
    public void sendMessage50107() {
        if (!isH5Prepare) {
            getActivity().finish();
        }else if (getWebView() != null) {
            AppMessage appMessage = new AppMessage(50107, new JSONObject());
            sendMessageToH5(appMessage);
        }
    }
}


