package com.thinkive.android.trade_bz.a_hk.bll;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_hk.bean.HKHoldStockBean;
import com.thinkive.android.trade_bz.a_hk.bean.HKStockLinkBean;
import com.thinkive.android.trade_bz.a_hk.bean.HKStockMessageBean;
import com.thinkive.android.trade_bz.a_hk.fragment.HKBuySellFragment;
import com.thinkive.android.trade_bz.a_stock.bll.BasicServiceImpl;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.others.tools.TradeLoginManager;
import com.thinkive.android.trade_bz.request.HK301602;
import com.thinkive.android.trade_bz.request.HK301603;
import com.thinkive.android.trade_bz.request.HK301605;
import com.thinkive.android.trade_bz.request.RequestHQ50000;
import com.thinkive.android.trade_bz.utils.LoadingDialogUtil;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 普通交易业务类
 * @author 王志鸿
 * @version 1.0
 * @corporation
 * @date 2015/6/5
 */
public class HKBuySellServiceImpl extends BasicServiceImpl {

    private HKBuySellFragment mFragment = null;
    /**
     * 委托标识，请求时用作入参
     * 买入：0，卖出：1
     */
    private String mEntrust_bs;
    /**
     * 最大价格等级(1:表示竞价限价盘
     *  0:表示增强限价盘)
     */
    private String mEntrust_level;
    private LoadingDialogUtil loadingDialogUtil;//请求数据状态框 工具类
    public HKBuySellServiceImpl(HKBuySellFragment fragment) {
        mFragment = fragment;
        loadingDialogUtil = new LoadingDialogUtil(fragment.getContext());
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
        new HK301605(paramMap, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                ArrayList<HKHoldStockBean> dataList = bundle.getParcelableArrayList(HK301605.BUNDLE_KEY_RESULT);
                mFragment.getStoreData(dataList);
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(HK301605.ERROR_INFO));
            }
        }).request();
    }

    /**
     * 开始进行股票联动的组合式查询，股票联动请求逻辑如下：
     * 1.请求50000接口，获取行情数据并显示，判断是否停牌
     * 2.如果停牌，弹出toast提示用户该股票已经停牌
     * 3.如果没有停牌，查询交易301514接口，获取联动数据和五档买卖盘数据，并显示
     * @param stockCode
     * 用户输入的完整的股票代码
     */
    public void startLinkage(String stockCode,final boolean isRefresh) {
        HashMap<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("version","1");
        paramMap.put("field","4:5:22:24:41:42:43:48:2");
        if(stockCode != null && stockCode.length() == 5){
            paramMap.put("stock_list","HK"+":"+"0"+stockCode);
        }else {
            paramMap.put("stock_list","HK"+":"+stockCode);
        }
        new RequestHQ50000(paramMap, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                HKStockMessageBean data = (HKStockMessageBean)bundle.getSerializable(RequestHQ50000.BUNDLE_KEY_SOCKET_HK);
                if(data.getIs_stop().equals("1")){//停牌
                    ToastUtils.toast(context, R.string.hk_order26);
                    mFragment.onGetSuspendStock(data.getName(),data.getCode());
                }else if(data.getIs_stop().equals("2")) {//未停牌
                    mFragment.onGetHqData(data,isRefresh);
                }
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(RequestHQ50000.ERROR_INFO));
            }
        }).request();
    }

    /**
     * 股票数据联动数据请求
     */
    public void requestLinkageData(final String stock_code, String entrustPrice) {
        HashMap<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("entrust_bs", mEntrust_bs);
        paramMap.put("stock_code", stock_code);
        paramMap.put("max_price_levels",mEntrust_level);//最大价格等级(1:表示竞价限价盘 0:表示增强限价盘)
        if (entrustPrice != null && !TextUtils.isEmpty(entrustPrice)) {
            float priceFloat = Float.parseFloat(entrustPrice);
            if(priceFloat <= 0){
                entrustPrice = "";
            }
        }else{
            entrustPrice = "";
        }
        paramMap.put("entrust_price", entrustPrice);
        new HK301602(paramMap, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                HKStockLinkBean stockLinkageBean = (HKStockLinkBean) bundle.getSerializable(HK301602.BUNDLE_KEY_HK_LINKAGE);
                if (stockLinkageBean != null) {
                    mFragment.onGetStockLinkAgeData(stockLinkageBean);
                }
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, context.getResources().getString(R.string.hk_order19) + bundle.getString(HK301602.ERROR_INFO));
            }
        }).request();
    }

    /**
     * 委托交易请求，一般在“买入”、“卖出”按钮被单击时执行
     */
    public void requestBuyOrSell() {
        loadingDialogUtil.showLoadingDialog(0);
        HashMap<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("entrust_bs", mEntrust_bs);
        paramMap.put("exchange_type", "G");
        paramMap.put("stock_account", TradeLoginManager.sNormalUserInfo_hk.getStock_account());
        paramMap.put("stock_code", mFragment.getEntrustCode());
        paramMap.put("entrust_price", mFragment.getEntrustPrice());
        paramMap.put("entrust_amount", mFragment.getEntrustAmount());
        paramMap.put("max_price_levels",mEntrust_level);//最大价格等级(1:表示竞价限价盘 0:表示增强限价盘)
        new HK301603(paramMap, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                loadingDialogUtil.hideDialog();
                mFragment.onSuccessEntrustTrade(bundle.getString(HK301603.BUNDLE_KEY_RESULT));
                // 委托成功后，清空界面上的数据
                mFragment.clearDataInViews();
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                loadingDialogUtil.hideDialog();
                ToastUtils.toast(context, bundle.getString(HK301603.ERROR_INFO));
            }
        }).request();
    }

    public String getEntrust_bs() {
        return mEntrust_bs;
    }

    public void setEntrust_bs(String entrust_bs) {
        mEntrust_bs = entrust_bs;
    }

    public void setEntrust_level(String entrust_level) {
        mEntrust_level = entrust_level;
    }
}
