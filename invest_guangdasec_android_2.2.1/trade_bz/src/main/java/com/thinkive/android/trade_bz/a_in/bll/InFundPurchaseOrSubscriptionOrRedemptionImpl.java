package com.thinkive.android.trade_bz.a_in.bll;

import android.content.Context;
import android.os.Bundle;

import com.thinkive.android.trade_bz.a_in.bean.InFundOrderBean;
import com.thinkive.android.trade_bz.a_in.bean.InFundQueryBean;
import com.thinkive.android.trade_bz.a_in.bean.InFundSercuritiesPositionsQueryBean;
import com.thinkive.android.trade_bz.a_in.fragment.InFundPurchaseOrSubscriptionFragment;
import com.thinkive.android.trade_bz.a_in.fragment.InFundRedemptionFragment;
import com.thinkive.android.trade_bz.a_stock.bean.MoneySelectBean;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.request.InFund301501;
import com.thinkive.android.trade_bz.request.InFund301503;
import com.thinkive.android.trade_bz.request.InFund301514;
import com.thinkive.android.trade_bz.request.Request301504;
import com.thinkive.android.trade_bz.utils.LoadingDialogUtil;
import com.thinkive.android.trade_bz.utils.LogUtil;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * Description：场内货币基金认、场内货币基金申购、场内货币基金赎回、LOF基金认购、LOF基金申购、LOF基金赎回 <br>
 * Author：晏政清 <br>
 * Corporation：深圳市思迪信息技术股份有限公司 <br>
 * Date：2016/8/11 <br>
 */

public class InFundPurchaseOrSubscriptionOrRedemptionImpl {
    private InFundPurchaseOrSubscriptionFragment inFundPurchaseOrSubscriptionFragment;
    private InFundRedemptionFragment inFundRedemptionFragment;
    private LoadingDialogUtil loadingDialogUtil;//请求数据状态框 工具类
    public InFundPurchaseOrSubscriptionOrRedemptionImpl(InFundPurchaseOrSubscriptionFragment inFundPurchaseOrSubscriptionFragment,InFundRedemptionFragment inFundRedemptionFragment) {
        this.inFundPurchaseOrSubscriptionFragment = inFundPurchaseOrSubscriptionFragment;
        this.inFundRedemptionFragment = inFundRedemptionFragment;
        if(inFundPurchaseOrSubscriptionFragment != null){
            loadingDialogUtil = new LoadingDialogUtil(inFundPurchaseOrSubscriptionFragment.getContext());
        }else if(inFundRedemptionFragment != null){
            loadingDialogUtil = new LoadingDialogUtil(inFundRedemptionFragment.getContext());
        }
    }

    /**
     * 场内基金，查询基金信息301514
     * @param params
     */
    public void inFundQuery(HashMap<String,String> params){
        loadingDialogUtil.showLoadingDialog(0);//显示请求状态框
        HashMap<String,String> paramMap = new HashMap<String,String>();
        if(params != null){
            if(params.containsKey("entrustBs")){
                paramMap.put("entrust_bs",params.get("entrustBs"));
            }
            if(params.containsKey("stockCode")){
                paramMap.put("stock_code",params.get("stockCode"));
            }
        }

        new InFund301514(paramMap, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                loadingDialogUtil.hideDialog();//隐藏状态显示框
                if(bundle != null){
                    ArrayList<InFundQueryBean> inFundQueryBeanArrayList = bundle.getParcelableArrayList(InFund301514.BUNDLE_KEY_301514);
                    if(inFundPurchaseOrSubscriptionFragment != null){
                        inFundPurchaseOrSubscriptionFragment.inFundQueryResult(inFundQueryBeanArrayList);
                    }else if(inFundRedemptionFragment != null){
                        inFundRedemptionFragment.inFundQueryResult(inFundQueryBeanArrayList);
                    }
                }
            }

            @Override
            public void onFailed(Context context, Bundle bundle) {
                loadingDialogUtil.hideDialog();//隐藏状态显示框
                String errorNo=bundle.getString(InFund301514.ERROR_NO);
                String errorInfo=bundle.getString(InFund301514.ERROR_INFO);
                LogUtil.printLog("e", "InFund301514出错,error_no:" + errorNo + "||error_info:" + errorInfo);
                ToastUtils.toast(context,errorInfo);
            }
        }).request();
    }

    /**
     * 场内基金，委托下单 301501
     * @param params
     */
    public void inFundOrderSubmit(HashMap<String,String> params){
        loadingDialogUtil.showLoadingDialog(0);//显示请求状态框
        HashMap<String,String> paramMap = new HashMap<String,String>();
        if(params != null){
            if(params.containsKey("entrustBs")){
                paramMap.put("entrust_bs",params.get("entrustBs"));
            }
            if(params.containsKey("exchangeType")){
                paramMap.put("exchange_type",params.get("exchangeType"));
            }
            if(params.containsKey("stockAccount")){
                paramMap.put("stock_account",params.get("stockAccount"));
            }
            if(params.containsKey("stockCode")){
                paramMap.put("stock_code",params.get("stockCode"));
            }
            if(params.containsKey("entrustPrice")){
                paramMap.put("entrust_price", params.get("entrustPrice"));
            }
            if(params.containsKey("entrustAmount")){
                paramMap.put("entrust_amount", params.get("entrustAmount"));
            }
        }

        new InFund301501(paramMap, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                loadingDialogUtil.hideDialog();//隐藏状态显示框
                if(bundle != null){
                    ArrayList<InFundOrderBean> inFundOrderBeanArrayList = bundle.getParcelableArrayList(InFund301501.BUNDLE_KEY_301501);
                    if(inFundPurchaseOrSubscriptionFragment != null){
                        inFundPurchaseOrSubscriptionFragment.inFundOrderSubmitReuslt(inFundOrderBeanArrayList);
                    }else if(inFundRedemptionFragment != null){
                        inFundRedemptionFragment.inFundOrderSubmitReuslt(inFundOrderBeanArrayList);
                    }
                }
            }

            @Override
            public void onFailed(Context context, Bundle bundle) {
                loadingDialogUtil.hideDialog();//隐藏状态显示框
                String errorNo=bundle.getString(InFund301501.ERROR_NO);
                String errorInfo=bundle.getString(InFund301501.ERROR_INFO);
                LogUtil.printLog("e", "InFund301501出错,error_no:" + errorNo + "||error_info:" + errorInfo);
                ToastUtils.toast(context,errorInfo);
            }
        }).request();
    }

    /**
     * 查询持仓列表（301503）
     * @param params
     */
    public void inFundSercuritiesPositionsQuery(HashMap<String,String> params){
        loadingDialogUtil.showLoadingDialog(0);//显示请求状态框
        HashMap<String,String> paramMap = new HashMap<String,String>();
        if(params != null && params.containsKey("islof")){
            paramMap.put("islof",params.get("islof"));
        }
        new InFund301503(paramMap, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                loadingDialogUtil.hideDialog();//隐藏状态显示框
                if(bundle != null){
                    ArrayList<InFundSercuritiesPositionsQueryBean> inFundSercuritiesPositionsQueryBeanArrayList = bundle.getParcelableArrayList(InFund301503.BUNDLE_KEY_301503);
                    if(inFundPurchaseOrSubscriptionFragment != null){

                    }else if(inFundRedemptionFragment != null){
                        inFundRedemptionFragment.InFundSercuritiesPositionsQueryReuslt(inFundSercuritiesPositionsQueryBeanArrayList);

                    }
                }
            }

            @Override
            public void onFailed(Context context, Bundle bundle) {
                loadingDialogUtil.hideDialog();//隐藏状态显示框
                String errorNo=bundle.getString(InFund301503.ERROR_NO);
                String errorInfo=bundle.getString(InFund301503.ERROR_INFO);
                LogUtil.printLog("e", "InFund301503出错,error_no:" + errorNo + "||error_info:" + errorInfo);
                ToastUtils.toast(context,errorInfo);
            }
        }).request();
    }

    /**
     * 持有资金查询(该方法只能被认、申购调用，赎回不需要调用)
     */
    public void requestHoldFund() {
        loadingDialogUtil.showLoadingDialog(0);
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("money_type", "0");
        new Request301504(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                loadingDialogUtil.hideDialog();
                MoneySelectBean data = (MoneySelectBean)bundle.getSerializable(Request301504.BUNDLE_KEY_MYHOLD_HEAD);
                if(inFundPurchaseOrSubscriptionFragment != null){
                    inFundPurchaseOrSubscriptionFragment.getHoldFund(data);
                }else if(inFundRedemptionFragment != null){
                    inFundRedemptionFragment.getHoldFund(data);
                }
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                loadingDialogUtil.hideDialog();
                ToastUtils.toast(context, bundle.getString(Request301504.ERROR_INFO));
            }
        }).request();
    }
}
