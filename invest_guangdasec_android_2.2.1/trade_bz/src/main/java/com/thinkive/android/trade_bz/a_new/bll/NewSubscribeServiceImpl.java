package com.thinkive.android.trade_bz.a_new.bll;

import android.content.Context;
import android.os.Bundle;

import com.thinkive.android.trade_bz.a_new.bean.NewStockBean;
import com.thinkive.android.trade_bz.a_new.bean.NewSuscribeBean;
import com.thinkive.android.trade_bz.a_new.fragment.NewSubscribeFragment;
import com.thinkive.android.trade_bz.a_stock.bll.BasicServiceImpl;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.others.tools.TradeLoginManager;
import com.thinkive.android.trade_bz.request.NewStock301519;
import com.thinkive.android.trade_bz.request.NewStock301535;
import com.thinkive.android.trade_bz.request.NewStock303028;
import com.thinkive.android.trade_bz.request.NewStock303053;
import com.thinkive.android.trade_bz.request.Request301501;
import com.thinkive.android.trade_bz.request.Request303001;
import com.thinkive.android.trade_bz.utils.LoadingDialogUtil;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 新股申购
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/8/21
 */
public class NewSubscribeServiceImpl extends BasicServiceImpl {

    public static final String ENTRUST_BS_NEW_SUBSCRIBE = "0";

    private NewSubscribeFragment mFragment = null;
    /**
     * 用户类型
     */
    private String mUserType;
    private LoadingDialogUtil loadingDialogUtil;//请求数据状态框 工具类

    public NewSubscribeServiceImpl(NewSubscribeFragment fragment, String userType) {
        mFragment = fragment;
        mUserType=userType;
        loadingDialogUtil = new LoadingDialogUtil(fragment.getContext());
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onStop() {

    }

    /**
     * 请求可申购额度
     */
    public void requestSubscribeLimit() {
        HashMap<String, String> map = new HashMap<String, String>();
        //普通账号
        if(mUserType != null && mUserType.equals(TradeLoginManager.LOGIN_TYPE_NORMAL)){
            new NewStock301519(map, new IRequestAction() {
                @Override
                public void onSuccess(Context context, Bundle bundle) {
                    ArrayList<NewSuscribeBean> dataList = bundle.getParcelableArrayList(NewStock301519.BUNDLE_KEY_SUBSDRIBE);
                    mFragment.onGetSubscribeLimitData(dataList);
                }
                @Override
                public void onFailed(Context context, Bundle bundle) {
                    ToastUtils.toast(context, bundle.getString(NewStock301519.ERROR_INFO));
                }
            }).request();
            //信用账号
        }else if(mUserType != null && mUserType.equals(TradeLoginManager.LOGIN_TYPE_CREDIT)){
            new NewStock303028(map, new IRequestAction() {
                @Override
                public void onSuccess(Context context, Bundle bundle) {
                    ArrayList<NewSuscribeBean> dataList = bundle.getParcelableArrayList(NewStock303028.BUNDLE_KEY_SUBSDRIBE);
                    mFragment.onGetSubscribeLimitData(dataList);
                }
                @Override
                public void onFailed(Context context, Bundle bundle) {
                    ToastUtils.toast(context, bundle.getString(NewStock303028.ERROR_INFO));
                }
            }).request();
        }
    }

    /**
     * 请求今日新股数据
     */
    public void requestTodayNew() {
        HashMap<String, String> paramMap = new HashMap<String, String>();
        //普通账号
        if(mUserType != null && mUserType.equals(TradeLoginManager.LOGIN_TYPE_NORMAL)){
            new NewStock301535(paramMap, new IRequestAction() {
                @Override
                public void onSuccess(Context context, Bundle bundle) {
                    ArrayList<NewStockBean> dataList = bundle.getParcelableArrayList(NewStock301535.BUNDLE_KEY_TODAY_NEW_STOCK);
                    if (dataList != null) {
                        mFragment.onGetTodayNew(dataList);
                    }
                }
                @Override
                public void onFailed(Context context, Bundle bundle) {
                    ToastUtils.toast(context, bundle.getString(NewStock301535.ERROR_INFO));
                }
            }).request();
            //信用账号
        } else if(mUserType != null && mUserType.equals(TradeLoginManager.LOGIN_TYPE_CREDIT)){
            new NewStock303053(paramMap, new IRequestAction() {
                @Override
                public void onSuccess(Context context, Bundle bundle) {
                    ArrayList<NewStockBean> dataList = bundle.getParcelableArrayList(NewStock303053.BUNDLE_KEY_TODAY_NEW_STOCK);
                    if (dataList != null) {
                        mFragment.onGetTodayNew(dataList);
                    }
                }
                @Override
                public void onFailed(Context context, Bundle bundle) {
                    ToastUtils.toast(context, bundle.getString(NewStock303053.ERROR_INFO));
                }
            }).request();
        }
    }
    /**
     * 委托申购请求，单击申购按钮执行
     */
    public void requestNewSubscribe(NewStockBean bean,String entrust_amount) {
        loadingDialogUtil.showLoadingDialog(0);
        HashMap<String, String> paramMap = new HashMap<String, String>();
        //普通账户
        if(mUserType != null && mUserType.equals(TradeLoginManager.LOGIN_TYPE_NORMAL)){
            paramMap.put("entrust_bs", ENTRUST_BS_NEW_SUBSCRIBE);
            paramMap.put("exchange_type", bean.getExchange_type());
            paramMap.put("stock_account", bean.getStock_account());
            paramMap.put("stock_code", bean.getStock_code());
            paramMap.put("entrust_price", bean.getIssue_price());
            paramMap.put("entrust_amount", entrust_amount);
            new Request301501(paramMap, new IRequestAction() {
                @Override
                public void onSuccess(Context context, Bundle bundle) {
                    loadingDialogUtil.hideDialog();
                    mFragment.getEnturstResult(bundle.getString(Request301501.BUNDLE_KEY_RESULT));
                }
                @Override
                public void onFailed(Context context, Bundle bundle) {
                    loadingDialogUtil.hideDialog();
                    ToastUtils.toast(context, bundle.getString(Request301501.ERROR_INFO));
                    mFragment.clearDataForView();
                }
            }).request();
            //信用账户
        }else if(mUserType != null && mUserType.equals(TradeLoginManager.LOGIN_TYPE_CREDIT)){
            paramMap.put("entrust_bs", ENTRUST_BS_NEW_SUBSCRIBE);
            paramMap.put("entrust_type", "0");
            paramMap.put("exchange_type", bean.getExchange_type());
            paramMap.put("stock_account", bean.getStock_account());
            paramMap.put("stock_code", bean.getStock_code());
            paramMap.put("entrust_price", bean.getIssue_price());
            paramMap.put("entrust_amount", entrust_amount);
            new Request303001(paramMap, new IRequestAction() {
                @Override
                public void onSuccess(Context context, Bundle bundle) {
                    loadingDialogUtil.hideDialog();
                    mFragment.getEnturstResult(bundle.getString(Request303001.BUNDLE_KEY_ENTRUST_ORDER));
                }
                @Override
                public void onFailed(Context context, Bundle bundle) {
                    loadingDialogUtil.hideDialog();
                    ToastUtils.toast(context, bundle.getString(Request303001.ERROR_INFO));
                    mFragment.clearDataForView();
                }
            }).request();
        }
    }


//    /**
//     * 股票数据联动数据请求
//     */
//    public void requestLinkageData(String stockCode, final String entrustPrice) {
//        HashMap<String, String> paramMap = new HashMap<String, String>();
//        //普通账号
//        if(mUserType != null && mUserType.equals(TradeLoginManager.LOGIN_TYPE_NORMAL)){
//            paramMap.put("entrust_bs", ENTRUST_BS_NEW_SUBSCRIBE);
//            paramMap.put("stock_code", stockCode);
//            paramMap.put("entrust_price", entrustPrice);
//            new Request301514(paramMap, new IRequestAction() {
//                @Override
//                public void onSuccess(Context context, Bundle bundle) {
//                    if(mFragment == null || mFragment.getActivity() == null ||
//                            mFragment.getActivity().isFinishing()){
//                        return;
//                    }
//                    StockLinkageBean stockLinkageBean = (StockLinkageBean) bundle.getSerializable(Request301514.BUNDLE_KEY_LINKAGE);
//                    stockLinkageBean.setIssue_price(entrustPrice);
////                    mFragment.onGetStockLinkAgeData(stockLinkageBean);
//                }
//                @Override
//                public void onFailed(Context context, Bundle bundle) {
//                    mFragment.clearDataForView();
//                    ToastUtils.toast(context, bundle.getString(Request301514.ERROR_INFO));
//                }
//            }).request();
//            //信用账号
//        }else if(mUserType != null && mUserType.equals(TradeLoginManager.LOGIN_TYPE_CREDIT)){
//            paramMap.put("entrust_bs", ENTRUST_BS_NEW_SUBSCRIBE);
//            paramMap.put("stock_code", stockCode);
//            paramMap.put("entrust_price", entrustPrice);
//            new Request303000(paramMap, new IRequestAction() {
//                @Override
//                public void onSuccess(Context context, Bundle bundle) {
//                    if(mFragment == null || mFragment.getActivity() == null ||
//                            mFragment.getActivity().isFinishing()){
//                        return;
//                    }
//                    StockLinkageBean stockLinkageBean = (StockLinkageBean) bundle.getSerializable(Request303000.BUNDLE_KEY_LINKAGE);
//                    stockLinkageBean.setIssue_price(entrustPrice);
////                    mFragment.onGetStockLinkAgeData(stockLinkageBean);
//                }
//                @Override
//                public void onFailed(Context context, Bundle bundle) {
//                    mFragment.clearDataForView();
//                    ToastUtils.toast(context, bundle.getString(Request303000.ERROR_INFO));
//                }
//            }).request();
//        }
//    }

}
