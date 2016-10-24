package com.thinkive.android.trade_bz.a_trans.bll;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;

import com.android.thinkive.framework.CoreApplication;
import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.bean.CodeTableBean;
import com.thinkive.android.trade_bz.a_stock.bean.StockBuySellDish;
import com.thinkive.android.trade_bz.a_stock.bll.BasicServiceImpl;
import com.thinkive.android.trade_bz.a_trans.bean.TransHoldStockBean;
import com.thinkive.android.trade_bz.a_trans.bean.TransStockLinkBean;
import com.thinkive.android.trade_bz.a_trans.fragment.TransMarketBuyOrSaleFragment;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.others.tools.TradeLoginManager;
import com.thinkive.android.trade_bz.request.RequestHQ20000;
import com.thinkive.android.trade_bz.request.RequestHQ20003;
import com.thinkive.android.trade_bz.request.Trans301700;
import com.thinkive.android.trade_bz.request.Trans301701;
import com.thinkive.android.trade_bz.request.Trans301702;
import com.thinkive.android.trade_bz.utils.LoadingDialogUtil;
import com.thinkive.android.trade_bz.utils.ToastUtils;
import com.thinkive.android.trade_bz.utils.TradeUtils;


import java.util.ArrayList;
import java.util.HashMap;
/**
 * 转股交易 做市买入/做市卖出
 * @author 张雪梅
 * @corporation thinkive
 * @date 2016/7/28
 */
public class TransMarketBuyOrSaleServiceImpl extends BasicServiceImpl {

    private TransMarketBuyOrSaleFragment mFragment = null;
    /**
     * 委托标识，请求时用作入参
     * 买入：0，卖出：1
     */
    private String mEntrust_bs;
    private LoadingDialogUtil loadingDialogUtil;//请求数据状态框 工具类
    private Context mContext;

    public TransMarketBuyOrSaleServiceImpl(TransMarketBuyOrSaleFragment fragment) {
        mFragment = fragment;
        loadingDialogUtil = new LoadingDialogUtil(fragment.getContext());
        mContext = CoreApplication.getInstance();
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onStop() {

    }
    /**
     * 发起请求，获取持仓列表
     */
    public void getHoldList() {
        HashMap<String, String> paramMap = new HashMap<String, String>();
        new Trans301702(paramMap, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                ArrayList<TransHoldStockBean> dataList = bundle.getParcelableArrayList(Trans301702.BUNDLE_KEY_RESULT);
                mFragment.getStoreData(dataList);
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(Trans301702.ERROR_INFO));
            }
        }).request();
    }

    public void request20000ForHqData(final String stockCode, final String market) {
        HashMap<String, String> paramMap = new HashMap<String, String>();
        if(!market.equals("SZ") && !market.equals("SH")){
            ToastUtils.toast(mContext, mContext.getResources().getString(R.string.toast_data_error));
            return;
        }
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
                    mFragment.onGetSuspendStock(data.getName(), data.getCode());
                } else if (data.getIssuspend().equals("2")) {//未停牌
                    mFragment.onGetHqData(data);
                    requestStockWuDangPan(data.getCode(), data.getMarket(),true);
                }
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(RequestHQ20000.ERROR_INFO));
            }
        }).request();
    }

    /**
     * 请求服务器，获取股票行情五档买卖盘数据
     */
    public void requestStockWuDangPan(final String stockCode, final String market, final boolean isSetText) {
        HashMap<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("stock_list", market + ":" + stockCode);
        paramMap.put("version", "1");
        new RequestHQ20003(paramMap, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                StockBuySellDish bean = (StockBuySellDish) bundle.getSerializable(RequestHQ20003.BUNDLE_KEY_WUDANG);
                if (bean != null) {
                    ArrayList<String> valueList = bean.getValueBuySale();
                    for (int i = 0; i <= 4; i++) { // 卖价五~卖价一
                        valueList.set(i, TradeUtils.formatDouble2(valueList.get(i)));
                    }
                    for (int i = 5; i <= 9; i++) { // 卖量五~卖量一
                        valueList.set(i, TradeUtils.formateDataWithQUnit(valueList.get(i)));
                    }
                    for (int i = 10; i <= 14; i++) { // 买价一~买价五
                        valueList.set(i, TradeUtils.formatDouble2(valueList.get(i)));
                    }
                    for (int i = 15; i <= 19; i++) { // 买量一~买量五
                        valueList.set(i, TradeUtils.formateDataWithQUnit(valueList.get(i)));
                    }
                    mFragment.onGetWuDangDishData(bean,isSetText);
                }
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(RequestHQ20003.ERROR_INFO));
            }
        }).request();
    }

    /**
     * 股票数据联动数据请求
     */
    public void requestLinkageData(final String stock_code, String entrustPrice) {
        HashMap<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("stock_account", TradeLoginManager.sNormalUserInfo_three.getStock_account());
        paramMap.put("entrust_bs", mEntrust_bs);
        paramMap.put("exchange_type", "41");
        paramMap.put("stock_code", stock_code);
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
        paramMap.put("stock_account", TradeLoginManager.sNormalUserInfo_three.getStock_account());
        paramMap.put("entrust_prop","0");
        paramMap.put("exchange_type", "41");
        paramMap.put("entrust_bs", mEntrust_bs);
        paramMap.put("stock_code", mFragment.getEntrustCode());
        paramMap.put("entrust_price", mFragment.getEntrustPrice());
        paramMap.put("entrust_amount", mFragment.getEntrustAmount());
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
