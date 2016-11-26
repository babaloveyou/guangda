package com.thinkive.android.trade_bz.request;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.android.thinkive.framework.CoreApplication;
import com.android.thinkive.framework.network.NetWorkService;
import com.android.thinkive.framework.network.RequestBean;
import com.android.thinkive.framework.network.ResponseListener;
import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.others.tools.TradeFlags;
import com.thinkive.android.trade_bz.others.tools.TradeTools;
import com.thinkive.android.trade_bz.receivers.TradeBaseBroadcastReceiver;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.SocketTimeoutException;
import java.util.HashMap;

/**
 * 新框架下的请求基类
 * 所有的错误情况都在本类中处理，子类只处理返回了结果，且是正确结果的情况。
 * 本类还提供回调到主线程的方法{@link #transferAction(int, Bundle)}
 *
 * @author 王志鸿
 * @company Thinkive
 * @date 2015/7/3
 */
public abstract class BaseRequest {

    //-------------------mHandler常量定义，开始----------------------
    /**
     * 请求成功并正确返回，error_no == 0时，在transferAction方法中作为第一个入参
     */
    public static final int REQUEST_SUCCESS = 11;
    /**
     * 请求失败，error_no != 0时，在transferAction方法中作为第一个入参
     */
    public static final int REQUEST_FAILED = 22;

    public static final String ERROR_NO = "error_no";
    public static final String ERROR_INFO = "error_info";

    public static final String ERRORNO = "errorNo";
    public static final String ERRORINFO = "errorInfo";
    //-------------------mHandler常量定义，结束----------------------
    /**
     * 新框架中的CoreApplication调用环境
     */
    static Context mContext = CoreApplication.getInstance();
    /**
     * 自定义内部Handler，用来回调到主线程
     */
    private ActionHandler mActionHandler = new ActionHandler();
    /**
     * 请求需要的数据对象
     */
    protected RequestBean mRequestBean;

    /**
     * 请求结果返回监听
     */
    private ResponseListener<JSONObject> mListener = new ResponseListener<JSONObject>() {
        /**
         * 相当于老框架的handler方法，执行在子线程、服务器返回了内容的时后执行
         * @param jsonObject 服务器返回的结果，不一定是正确的结果
         */
        @Override
        public void onResponse(JSONObject jsonObject) {
            String errorInfo = "";
            int errorCode;
            Bundle bundle = new Bundle();
            if (jsonObject.has(ERROR_NO)) {
                errorCode = jsonObject.optInt(ERROR_NO);
                //调用接口正常
                if (errorCode == 0) {
                    getJsonDataWithoutError(jsonObject);
                } else if(errorCode == -999||errorCode== -919) { // 如果error_no为-999，说明是未登录，此时要特殊处理
                    Intent intent = new Intent();
                    Bundle bundle999 = new Bundle();
                    String funcNo = mRequestBean.getParam().get("funcNo");
                    if(!funcNo.equals("600003")){
                        ToastUtils.toast(mContext,mContext.getResources().getString(R.string.error_999));
                    }
                    bundle999.putString("funcNo",funcNo);
                    bundle999.putBoolean("isUnityLogin", !TradeFlags.check(TradeFlags.FLAG_NOT_UNITY_LOGIN_TYPE));
                    intent.putExtras(bundle999);
                    TradeBaseBroadcastReceiver.sendBroadcast(mContext,intent,TradeBaseBroadcastReceiver.ACTION_ERROR_999);
                } else  { //调用接口出现其他异常
                    if (jsonObject.has(ERROR_INFO)) {
                        errorInfo = jsonObject.optString(ERROR_INFO);
                    }
                    bundle.putString(ERROR_INFO, errorInfo);
                    bundle.putString(ERROR_NO, String.valueOf(errorCode));
                    transferAction(REQUEST_FAILED, bundle);
                }
            } else if (jsonObject.has(ERRORNO)) {
                errorCode = jsonObject.optInt(ERRORNO);
                //调用接口正常
                if (errorCode == 0) {
                    getJsonDataWithoutError(jsonObject);
                } else { //调用接口异常
                    if (jsonObject.has(ERRORINFO)) {
                        errorInfo = jsonObject.optString(ERRORINFO);
                    }
                    bundle.putString(ERRORINFO, errorInfo);
                    bundle.putString(ERRORNO, String.valueOf(errorCode));
                    transferAction(REQUEST_FAILED, bundle);
                }
            } else {
                bundle.putString(ERROR_INFO, "服务器返回数据有误！");
                bundle.putString(ERROR_NO, String.valueOf("-120"));
                transferAction(REQUEST_FAILED, bundle);
            }
        }

        /**
         * 出错了，服务器没有返回任何结果时执行
         * @param e 在请求服务器过程中，抛出的java异常。
         */
        @Override
        public void onErrorResponse(Exception e) {
            Bundle bundle = new Bundle();
            if (e instanceof SocketTimeoutException) {
                bundle.putString(ERROR_NO, "-111");
                bundle.putString(ERROR_INFO, "服务器响应超时!!");
            } else if (e instanceof ConnectTimeoutException) {
                bundle.putString(ERROR_NO, "-112");
                bundle.putString(ERROR_INFO, "服务器请求超时!");
            } else if (e instanceof UnsupportedEncodingException) {
                bundle.putString(ERROR_NO, "-113");
                bundle.putString(ERROR_INFO, "服务器认证身份失败!");
            } else if (e instanceof com.android.volley.ServerError) {
                bundle.putString(ERROR_NO, "-114");
                bundle.putString(ERROR_INFO, "服务器地址出错!");
            } else {
                bundle.putString(ERROR_NO, "-119");
                bundle.putString(ERROR_INFO, "请检查您的网络!");
            }
            transferAction(REQUEST_FAILED, bundle);
        }
    };

    /**
     * @param action
     */
    public BaseRequest(IRequestAction action) {
        mRequestBean = new RequestBean();
        mActionHandler.setAction(action);
    }

    /**
     * 发起请求
     */
    public void request() {
        // 将本次请求的Url打印到日志
        TradeTools.printLogForRequestUrl(mRequestBean);
        // 发起请求
        NetWorkService.getInstance().request(mRequestBean, mListener);
    }

    /**
     * 回调到主线程，告知主线程请求结果，并给主线程传递请求得到的数据
     *
     * @param actionType 回调类型
     * @param bundle     保存请求得到的数据
     */
    void transferAction(int actionType, Bundle bundle) {
        Message msg = mActionHandler.obtainMessage();
        msg.setData(bundle);
        msg.what = actionType;
        msg.sendToTarget();
    }

    /**
     * 服务器正常返回得到了结果，框架等底层程序没有报错。请求结果中error_no等于0，正确得到了数据。
     *
     * @param jsonObject 服务器返回的全部数据
     */
    abstract void getJsonDataWithoutError(JSONObject jsonObject);

    /**
     * 设置请求入参
     *
     * @param paramHashMap Map形式的请求入参
     */
    public void setParamHashMap(HashMap<String, String> paramHashMap) {
        setPublicParam(paramHashMap);
        mRequestBean.setParam(paramHashMap);
    }

    /**
     * 暴露设置公共入参的方法
     * @param paramHashMap
     */
    public void setPublicParam(HashMap<String, String> paramHashMap) {

    }

    /**
     * 设置请求的Url名称，Url名称和具体Url在configuration.xml文件中“env-xml”标签指定的配置文件中一一对应
     *
     * @param urlName Url名称
     */
    public void setUrlName(String urlName) {
        mRequestBean.setUrlName(urlName);
    }

    /**
     * 回调主线程使用的Handler
     */
    static class ActionHandler extends Handler {

        private IRequestAction mAction;

        public ActionHandler() {
        }

        @Override
        public void handleMessage(Message msg) {
            // 用what来区分请求结果
            switch (msg.what) {
                case REQUEST_SUCCESS:
                    mAction.onSuccess(mContext, msg.getData());
                    break;
                case REQUEST_FAILED:
                    mAction.onFailed(mContext, msg.getData());
                    break;
            }
            super.handleMessage(msg);
        }

        public void setAction(IRequestAction action) {
            mAction = action;
        }
    }
}
