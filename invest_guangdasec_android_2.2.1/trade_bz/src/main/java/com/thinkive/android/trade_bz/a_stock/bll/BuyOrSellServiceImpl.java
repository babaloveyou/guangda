package com.thinkive.android.trade_bz.a_stock.bll;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;

import com.android.thinkive.framework.CoreApplication;
import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.bean.CodeTableBean;
import com.thinkive.android.trade_bz.a_stock.bean.LoginInfo;
import com.thinkive.android.trade_bz.a_stock.bean.MoneySelectBean;
import com.thinkive.android.trade_bz.a_stock.bean.MyStoreStockBean;
import com.thinkive.android.trade_bz.a_stock.bean.StockBuySellDish;
import com.thinkive.android.trade_bz.a_stock.bean.StockLinkageBean;
import com.thinkive.android.trade_bz.a_stock.fragment.BuyOrSellFragment;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.others.tools.TradeTools;
import com.thinkive.android.trade_bz.request.Request301501;
import com.thinkive.android.trade_bz.request.Request301503;
import com.thinkive.android.trade_bz.request.Request301504;
import com.thinkive.android.trade_bz.request.Request301514;
import com.thinkive.android.trade_bz.request.RequestHQ20000;
import com.thinkive.android.trade_bz.request.RequestHQ20003;
import com.thinkive.android.trade_bz.utils.LoadingDialogUtil;
import com.thinkive.android.trade_bz.utils.ToastUtil;
import com.thinkive.android.trade_bz.utils.ToastUtils;
import com.thinkive.android.trade_bz.utils.TradeUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 普通交易业务类
 * @author 王志鸿
 * @version 1.0
 * @corporation 深圳市思迪信息技术股份有限公司
 * @date 2015/6/5
 */
public class BuyOrSellServiceImpl implements Serializable{

    private BuyOrSellFragment mFragment = null;
    /**
     * 委托价格保留小数位
     */
    public int mCount = 2;
    private Context mContext;

    private LoadingDialogUtil loadingDialogUtil;//请求数据状态框 工具类

    public BuyOrSellServiceImpl(BuyOrSellFragment fragment) {
        mFragment = fragment;
        loadingDialogUtil = new LoadingDialogUtil(fragment.getContext());
        mContext = CoreApplication.getInstance();
    }

    /**
     * 发起请求，获取持仓列表
     */
    public void getHoldList() {
        HashMap<String, String> paramMap = new HashMap<String, String>();
        new Request301503(paramMap, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                ArrayList<MyStoreStockBean> dataList = bundle.getParcelableArrayList(Request301503.BUNDLE_KEY_RESULT);
                mFragment.getStoreData(dataList);
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(Request301503.ERROR_INFO));
            }
        }).request();
    }
    /**
     * 从行情请求证券信息
     * @param stockCode
     * @param market
     */
    public void request20000ForHqData(final String stockCode, final String market) {
        HashMap<String, String> paramMap = new HashMap<String, String>();
        if(!market.equals("SZ") && !market.equals("SH")){
            ToastUtils.toast(mContext, mContext.getResources().getString(R.string.toast_data_error));
            return;
        }
        paramMap.put("version", "1");
        paramMap.put("stock_list", market + ":" + stockCode);
        //名称：市场：代码：涨停：跌停：是否停牌:股票类型:现价
        paramMap.put("field","22:23:24:45:46:48:21:2");
        new RequestHQ20000(paramMap, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                CodeTableBean data = (CodeTableBean)bundle.getSerializable(RequestHQ20000.BUNDLE_KEY_SOCKET);
                if(data.getIssuspend().equals("1")){//停牌
                    ToastUtils.toast(context, context.getResources().getString(R.string.trade_stock_has_suspend));
                    mFragment.onGetSuspendStock(data.getName(),data.getCode());
                }else if(data.getIssuspend().equals("2")) {//未停牌
                    mCount = TradeTools.transferStockType(data.getStockType());
                    if (mCount != 2) { //是国债或者基金
                        data.setDownLimit(TradeUtils.formatDouble3(data.getDownLimit()));
                        data.setUpLimit(TradeUtils.formatDouble3(data.getUpLimit()));
                        data.setNow(TradeUtils.formatDouble3(data.getNow()));
                    } else {
                        data.setDownLimit(TradeUtils.formatDouble2(data.getDownLimit()));
                        data.setUpLimit(TradeUtils.formatDouble2(data.getUpLimit()));
                        data.setNow(TradeUtils.formatDouble2(data.getNow()));
                    }
                    mFragment.onGetHqData(data);
                    requestStockWuDangPan(data.getCode(), data.getMarket(),data.getExchange_type(),true);
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
    public void requestStockWuDangPan(final String stockCode,final String market,final String exchangeType, final boolean isSetText) {
        HashMap<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("stock_list", market + ":" + stockCode);
        paramMap.put("version", "1");
        new RequestHQ20003(paramMap, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                StockBuySellDish bean = (StockBuySellDish) bundle.getSerializable(RequestHQ20003.BUNDLE_KEY_WUDANG);
                String nowPrice = bundle.getString(RequestHQ20003.NOW_PRICE);
                String increase = bundle.getString(RequestHQ20003.INCREASE_AMOUNT);
                if (bean != null) {
                    ArrayList<String> valueList = bean.getValueBuySale();
                    for (int i = 0; i <= 4; i++) { // 卖价五~卖价一
                        if (mCount != 2) {
                            valueList.set(i, TradeUtils.formatDouble3(valueList.get(i)));
                        } else {
                            valueList.set(i, TradeUtils.formatDouble2(valueList.get(i)));
                        }
                    }
                    for (int i = 5; i <= 9; i++) { // 卖量五~卖量一
                        valueList.set(i, TradeUtils.formateDataWithQUnit(valueList.get(i)));
                    }
                    for (int i = 10; i <= 14; i++) { // 买价一~买价五
                        if (mCount != 2) {
                            valueList.set(i, TradeUtils.formatDouble3(valueList.get(i)));
                        } else {
                            valueList.set(i, TradeUtils.formatDouble2(valueList.get(i)));
                        }
                    }
                    for (int i = 15; i <= 19; i++) { // 买量一~买量五
                        valueList.set(i, TradeUtils.formateDataWithQUnit(valueList.get(i)));
                    }
                    mFragment.onGetWuDangDishData(bean,market,exchangeType,isSetText,nowPrice,increase);
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
    public void requestLinkageData(final String stock_code, String entrustPrice,
                                   final String market, final String exchange_type,String entrustBs) {
        HashMap<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("entrust_bs", entrustBs);
        paramMap.put("stock_code", stock_code);
        paramMap.put("exchange_type", exchange_type);
        paramMap.put("stock_account", TradeTools.forMateStockAccount(exchange_type));
        if (entrustPrice != null && !TextUtils.isEmpty(entrustPrice)) {
            float priceFloat = Float.parseFloat(entrustPrice);
            if(priceFloat <= 0){
                entrustPrice = "";
            }
        }else{
            entrustPrice = "";
        }
        paramMap.put("entrust_price", entrustPrice);
        new Request301514(paramMap, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                if(mFragment == null || mFragment.getActivity() == null ||
                        mFragment.getActivity().isFinishing()){
                    return;
                }
                StockLinkageBean stockLinkageBean = (StockLinkageBean) bundle.getSerializable(Request301514.BUNDLE_KEY_LINKAGE);
                if (stockLinkageBean != null) {
                    // 可交易数量取整数
                    stockLinkageBean.setStock_max_amount(TradeUtils.formatDouble0(stockLinkageBean.getStock_max_amount()));
                    // 设置市场
                    stockLinkageBean.setMarket(market);
                    // 设置交易市场类别
                    stockLinkageBean.setExchange_type(exchange_type);
                    // 传输数据到fragment
                    mFragment.onGetStockLinkAgeData(stockLinkageBean);
                    //可用资金请求
                    String exchange_type = stockLinkageBean.getExchange_type();
                    HashMap<String, String> map = new HashMap<>();
                    switch (exchange_type) {
                        case "0":
                        case "2":
                            map.put("money_type","0");
                            break;
                        case "1":
                            map.put("money_type", "2");
                            break;
                        case "3":
                            map.put("money_type",  "1");
                            break;
                    }
                    new Request301504(map, new IRequestAction() {
                        @Override
                        public void onSuccess(Context context, Bundle bundle) {
                            MoneySelectBean moneySelectBean = (MoneySelectBean) bundle.getSerializable(Request301504.BUNDLE_KEY_MYHOLD_HEAD);
                            String moneyUnit = moneySelectBean.getMoney_type_name();
                            switch (moneyUnit.substring(0,1)) {
                                case "美":
                                    moneyUnit = "美元";
                                    break;
                                case "人":
                                    moneyUnit = "元";
                                    break;
                                case "港":
                                    moneyUnit = "港币";
                                    break;
                            }
                            String balance = moneySelectBean.getEnable_balance();


                            mFragment.onGetCanUseBalance(balance + " "+moneyUnit);

                        }

                        @Override
                        public void onFailed(Context context, Bundle bundle) {
                            String error = bundle.getString(Request301504.BUNDLE_KEY_MYHOLD_HEAD);
                            ToastUtil.showToast(error);
                        }
                    }).request();
                }
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, context.getResources().getString(R.string.trade_get_linkage_stock_failed) + bundle.getString(Request301514.ERROR_INFO));
            }
        }).request();
    }

    /**
     * 委托交易请求，一般在“买入”、“卖出”按钮被单击时执行
     */
    public void requestBuyOrSell(CodeTableBean bean,String entrustBs) {
        loadingDialogUtil.showLoadingDialog(0);
        HashMap<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("stock_account", LoginInfo.getSelectHolderAccount());
        paramMap.put("entrust_bs", entrustBs);
        paramMap.put("entrust_price", mFragment.getEntrustPrice());
        paramMap.put("entrust_amount", mFragment.getEntrustAmount());
        if(bean != null){
            paramMap.put("exchange_type", bean.getExchange_type());
            paramMap.put("stock_account", bean.getStock_account());
            paramMap.put("stock_code", bean.getCode());
        }
        new Request301501(paramMap, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                loadingDialogUtil.hideDialog();
                mFragment.onSuccessEntrustTrade(bundle.getString(Request301501.BUNDLE_KEY_RESULT));
                // 委托成功后，清空界面上的数据
                mFragment.clearDataInViews();
                // 委托成功后，重新请求持仓列表以更新数据
                getHoldList();
                //成功后跳转到撤单
                mFragment.jumpToRevation();
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                loadingDialogUtil.hideDialog();
                ToastUtils.toast(context, bundle.getString(Request301501.ERROR_INFO));
                mFragment.clearDataInViews();
            }
        }).request();
    }
}
