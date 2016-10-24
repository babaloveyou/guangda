package com.thinkive.android.trade_bz.a_trans.bll;

import android.content.Context;
import android.os.Bundle;

import com.android.thinkive.framework.CoreApplication;
import com.android.thinkive.framework.message.AppMessage;
import com.android.thinkive.framework.message.ICallBack;
import com.android.thinkive.framework.message.MessageManager;
import com.android.thinkive.framework.util.JsonParseUtil;
import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.bean.CodeTableBean;
import com.thinkive.android.trade_bz.a_stock.bll.BasicServiceImpl;
import com.thinkive.android.trade_bz.a_trans.bean.TransSubHqBean;
import com.thinkive.android.trade_bz.a_trans.fragment.TransHqOneFragment;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.request.RequestHQ20000;
import com.thinkive.android.trade_bz.request.Trans301703;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 转股交易  委托交易
 * @author 张雪梅
 * @version 1.0
 * @corporation
 * @date 2016/1/5
 */
public class TransHqSelectServiceImpl extends BasicServiceImpl {

    private TransHqOneFragment mFragment = null;
    private Context mContext;

    public TransHqSelectServiceImpl(TransHqOneFragment fragment) {
        mFragment = fragment;
        mContext = CoreApplication.getInstance();
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onStop() {

    }

    /**
     * 开始进行股票联动的组合式查询，股票联动请求逻辑如下：
     * 1.请求20000接口，获取行情数据并显示，判断是否停牌
     * 2.如果停牌，弹出toast提示用户该股票已经停牌
     * 3.如果没有停牌，查询交易301514接口，获取联动数据和五档买卖盘数据，并显示
     * @param stockCode 用户输入的完整的股票代码
     */
    public void startLinkage(final String stockCode) {
        sendMsgToHqForStockList(stockCode, new IHqCallBackStock() {
            @Override
            public void onGetStockMsg(ArrayList<CodeTableBean> dataList) {
                if (dataList.size() == 1 && stockCode.equals(dataList.get(0).getCode())) { // 只有获取到唯一结果时才是正常情况
                    CodeTableBean codeTableBean = dataList.get(0);
                    request20000ForHqData(stockCode, codeTableBean.getMarket());
                } else {
                    ToastUtils.toast(mContext, mContext.getResources().getString(R.string.toast_data_error));
                }
            }
        });
    }
    /**
     * 给行情模块发送消息，让行情模块给本类返回股票搜索提示列表
     * 这里的模块通信异步返回结果。
     */
    private void sendMsgToHqForStockList(String curEditStockCode, final IHqCallBackStock iHqCallBackStock) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("searchKey", curEditStockCode);
            jsonObject.put("num", "30");
            AppMessage msg = new AppMessage("self-stock", 60101, jsonObject);
            msg.setCallBack(new ICallBack() {
                @Override
                public void callback(Object o) {
                    ArrayList<CodeTableBean> dataList = new ArrayList<CodeTableBean>();
                    try {
                        if(o != null && !o.equals("")){
                            String result = o.toString();
                            JSONObject resultJsonObject = new JSONObject(result);
                            JSONArray resultJsonArray = resultJsonObject.getJSONArray("results");
                            int resultJsonArrayLength = resultJsonArray.length();
                            CodeTableBean tempBean;
                            for (int i = 0; i < resultJsonArrayLength; i++) {
                                tempBean = JsonParseUtil.parseJsonToObject(resultJsonArray.getJSONObject(i), CodeTableBean.class);
                                dataList.add(tempBean);
                            }
                            iHqCallBackStock.onGetStockMsg(dataList);
                        }else{
                            ToastUtils.toast(mContext, mContext.getResources().getString(R.string.toast_call_back_hq));
                        }
                    } catch (JSONException je) {
                        je.printStackTrace();
                    }
                }
            });
            MessageManager.getInstance(CoreApplication.getInstance()).sendMessage(msg);
        } catch (JSONException je) {
            je.printStackTrace();
        }
    }

    interface IHqCallBackStock {
        void onGetStockMsg(ArrayList<CodeTableBean> dataList);
    }

    public void request20000ForHqData(final String stockCode, final String market) {
        HashMap<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("version", "1");
        paramMap.put("stock_list", market + ":" + stockCode);
        //名称：市场：代码：涨停：跌停：是否停牌:股票类型:现价
        paramMap.put("field", "22:23:24:45:46:48:21:2");
        new RequestHQ20000(paramMap, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                CodeTableBean data = (CodeTableBean) bundle.getSerializable(RequestHQ20000.BUNDLE_KEY_SOCKET);
                if (data.getIssuspend().equals("1")) {//停牌
                    ToastUtils.toast(context, context.getResources().getString(R.string.trade_stock_has_suspend));
                } else if (data.getIssuspend().equals("2")) {//未停牌
                    mFragment.onGetHqData(data);
                }
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(RequestHQ20000.ERROR_INFO));
            }
        }).request();
    }

    /**
     * 初始化请求到的数据
     */
    public void requestLimitSubData(String code,String bsflag) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("stock_code",code);
        map.put("bsflag",bsflag);
        new Trans301703(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                ArrayList<TransSubHqBean> dataList = bundle.getParcelableArrayList(Trans301703.BUNDLE_KEY_SUB_SELECT);
                mFragment.getTodayTradeData(dataList);
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(Trans301703.ERROR_INFO));
            }
        }).request();
    }
}
