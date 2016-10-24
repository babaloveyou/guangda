package com.thinkive.android.trade_bz.a_option.bll;

import android.content.Context;
import android.os.Bundle;

import com.thinkive.android.trade_bz.a_option.bean.OptionContractLockBean;
import com.thinkive.android.trade_bz.a_option.bean.OptionContractOpenBean;
import com.thinkive.android.trade_bz.a_option.bean.OptionCoveredSecuritiesTransferBean;
import com.thinkive.android.trade_bz.a_option.fragment.OptionTicketOpenOrLockFragment;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.request.Option305020;
import com.thinkive.android.trade_bz.request.Option305032;
import com.thinkive.android.trade_bz.request.Option305033;
import com.thinkive.android.trade_bz.utils.LoadingDialogUtil;
import com.thinkive.android.trade_bz.utils.LogUtil;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 备兑锁定/解锁
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/6/16
 */

public class OptionTicketOpenOrLockServicesImpl {
    private OptionTicketOpenOrLockFragment mFragment = null;
    private LoadingDialogUtil loadingDialogUtil;//请求数据状态框 工具类

    public OptionTicketOpenOrLockServicesImpl(OptionTicketOpenOrLockFragment fragment) {
        mFragment = fragment;
        loadingDialogUtil = new LoadingDialogUtil(fragment.getContext());
    }

    /**
     * 备兑锁定 请求合约
     */
    public void requestOptionContractLock(HashMap<String,String> params) {
        HashMap<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("stock_account", params.get("stockAccount")); // 股东账户
        paramMap.put("trade_sector", params.get("tradeSector")); // 交易板块
        //备兑锁定查询合约
        new Option305032(paramMap, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                if(bundle != null){
                    ArrayList<OptionContractLockBean> optionContractLockBeens = bundle.getParcelableArrayList(Option305032.BUNDLE_KEY_CONTRACT_LOCK);
                    mFragment.getOptionContractLockResult(optionContractLockBeens);
                }
            }

            @Override
            public void onFailed(Context context, Bundle bundle) {
                String errorNo=bundle.getString(Option305032.ERROR_NO);
                String errorInfo=bundle.getString(Option305032.ERROR_INFO);
                LogUtil.printLog("e","Option305032出错,error_no:"+errorNo+"||error_info:"+errorInfo);
                ToastUtils.toast(mFragment.getContext(),errorInfo);
            }
        }).request();
    }

    /**
     * 备兑解锁 请求合约
     */
    public void requestOptionContractOpen(HashMap<String,String> params) {
        HashMap<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("stock_account", params.get("stockAccount")); // 股东账户
        paramMap.put("trade_sector", params.get("tradeSector")); // 交易板块
        //备兑解锁查询合约
        new Option305033(paramMap, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                if(bundle != null){
                    ArrayList<OptionContractOpenBean> optionContractOpenBeens = bundle.getParcelableArrayList(Option305033.BUNDLE_KEY_CONTRACT_OPEN);
                    mFragment.getOptionContractOpenResult(optionContractOpenBeens);
                }
            }

            @Override
            public void onFailed(Context context, Bundle bundle) {
                String errorNo=bundle.getString(Option305033.ERROR_NO);
                String errorInfo=bundle.getString(Option305033.ERROR_INFO);
                LogUtil.printLog("e","Option305033出错,error_no:"+errorNo+"||error_info:"+errorInfo);
                ToastUtils.toast(mFragment.getContext(),errorInfo);
            }
        }).request();
    }

    /**
     * 备兑解锁/锁定 提交
     * @param params
     */
    public void requestOptionCoveredSecuritiesTransfer(HashMap<String,String> params){
        loadingDialogUtil.showLoadingDialog(0);
        HashMap<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("fund_account_opt", params.get("fundAccountOpt")); //衍生品资金账户
        paramMap.put("stock_account", params.get("stockAccount"));//证券账号
        paramMap.put("exchange_type", params.get("exchangeType")); //交易类别
        paramMap.put("stock_code", params.get("stockCode")); //标的证券代码
        paramMap.put("lock_direction", params.get("lockDirection")); //锁定方向（'1'-锁定 '2'-解锁）
        paramMap.put("entrust_amount", params.get("entrustAmount")); //划转数量
        new Option305020(paramMap, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                loadingDialogUtil.hideDialog();
                ArrayList<OptionCoveredSecuritiesTransferBean> optionCoveredSecuritiesTransferBeens =null;
                if(bundle != null){
                    optionCoveredSecuritiesTransferBeens = bundle.getParcelableArrayList(Option305020.OPTION_BUNDLE_COVERED_SERCURITIES_TRANSFER);
                }
                mFragment.getOptionCoveredSercuritiesTransferResult(optionCoveredSecuritiesTransferBeens);
            }

            @Override
            public void onFailed(Context context, Bundle bundle) {
                loadingDialogUtil.hideDialog();
                String errorNo=bundle.getString(Option305020.ERROR_NO);
                String errorInfo=bundle.getString(Option305020.ERROR_INFO);
                LogUtil.printLog("e","Option305020出错,error_no:"+errorNo+"||error_info:"+errorInfo);
                ToastUtils.toast(mFragment.getContext(),errorInfo);
            }
        }).request();
    }
}
