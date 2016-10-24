package com.thinkive.android.trade_bz.a_trans.bll;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;

import com.android.thinkive.framework.CoreApplication;
import com.android.thinkive.framework.message.AppMessage;
import com.android.thinkive.framework.message.ICallBack;
import com.android.thinkive.framework.message.MessageManager;
import com.android.thinkive.framework.util.JsonParseUtil;
import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.bean.CodeTableBean;
import com.thinkive.android.trade_bz.a_stock.bll.BasicServiceImpl;
import com.thinkive.android.trade_bz.a_trans.bean.TransStockLinkBean;
import com.thinkive.android.trade_bz.a_trans.fragment.TransSureBuyOrSaleFragment;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.others.tools.TradeLoginManager;
import com.thinkive.android.trade_bz.request.RequestHQ20000;
import com.thinkive.android.trade_bz.request.Trans301700;
import com.thinkive.android.trade_bz.request.Trans301701;
import com.thinkive.android.trade_bz.utils.LoadingDialogUtil;
import com.thinkive.android.trade_bz.utils.ToastUtils;
import com.thinkive.android.trade_bz.utils.TradeUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 转股交易 互报确认交易
 * @author 张雪梅
 * @corporation
 * @date 2015/12/31
 */
public class TransSureBuyOrSaleServiceImpl extends BasicServiceImpl {

    private TransSureBuyOrSaleFragment mFragment = null;

    /**
     * 委托标识，请求时用作入参
     * 买入：0，卖出：1
     */
    private String mEntrust_bs;
    private Context mContext;
    private LoadingDialogUtil loadingDialogUtil;//请求数据状态框 工具类

    public TransSureBuyOrSaleServiceImpl(TransSureBuyOrSaleFragment fragment) {
        mFragment = fragment;
        mContext = CoreApplication.getInstance();
        loadingDialogUtil = new LoadingDialogUtil(fragment.getContext());
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
                    mFragment.clearDataInViewsExpectStockCodeEd();
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
                            mFragment.clearDataInViewsExpectStockCodeEd();
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
                    mFragment.onGetSuspendStock(data.getName());
                } else if (data.getIssuspend().equals("2")) {//未停牌
                    mFragment.onGetHqData(data);
                    requestLinkageData(data.getCode(), TradeUtils.formatDouble2(data.getNow()));
                }
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(RequestHQ20000.ERROR_INFO));
            }
        }).request();
    }
    /**
     * 股票数据联动数据请求
     */
    public void requestLinkageData(final String stock_code, String entrustPrice) {
        HashMap<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("stock_account",TradeLoginManager.sNormalUserInfo_three.getStock_account());
        paramMap.put("entrust_bs", mEntrust_bs);
        paramMap.put("stock_code", stock_code);
        paramMap.put("exchange_type", "41");
        if (entrustPrice != null && !TextUtils.isEmpty(entrustPrice)) {
            float priceFloat = Float.parseFloat(entrustPrice);
            if(priceFloat <= 0){
                entrustPrice = "";
            }
        }else{
            entrustPrice = "";
        }
        paramMap.put("entrust_price", entrustPrice);
        new Trans301700(paramMap, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                TransStockLinkBean bean = (TransStockLinkBean)bundle.getSerializable(Trans301700.BUNDLE_KEY_TRANS_LINK);
                mFragment.onGetStockLinkAgeData(bean);
            }

            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, context.getResources().getString(R.string.trade_get_linkage_stock_failed) + bundle.getString(Trans301700.ERROR_INFO));
                mFragment.clearDataInViewsExpectStockCodeEd();
            }
        }).request();
    }
    /**
     * 委托交易请求，一般在“买入”、“卖出”按钮被单击时执行
     */
    public void requestBuyOrSell() {
        loadingDialogUtil.showLoadingDialog(0);
        HashMap<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("stock_account",TradeLoginManager.sNormalUserInfo_three.getStock_account());
        paramMap.put("entrust_bs", mEntrust_bs);
        paramMap.put("exchange_type", "41");
        paramMap.put("entrust_prop","0");
        paramMap.put("stock_code", mFragment.getEntrustCode());
        paramMap.put("entrust_price", mFragment.getEntrustPrice());
        paramMap.put("entrust_amount", mFragment.getEntrustAmount());
        paramMap.put("confer_no",mFragment.getYueNum());//约定号
        paramMap.put("targetseat",mFragment.getTargetNum());//对方席位号
        paramMap.put("targetsecuid",mFragment.getTargetAccount());//对方账户
        new Trans301701(paramMap, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                loadingDialogUtil.hideDialog();
                mFragment.onSuccessEntrustTrade(bundle.getString(Trans301701.BUNDLE_KEY_ORDER_RESULT));
                // 委托成功后，清空界面上的数据
                mFragment.clearDataInViews();
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                loadingDialogUtil.hideDialog();
                mFragment.clearDataInViewsExpectStockCodeEd();
                ToastUtils.toast(context, bundle.getString(Trans301701.ERROR_INFO));
            }
        }).request();
    }

    public String getEntrust_bs() {
        return mEntrust_bs;
    }

    public void setEntrust_bs(String entrust_bs) {
        mEntrust_bs = entrust_bs;
    }
}
