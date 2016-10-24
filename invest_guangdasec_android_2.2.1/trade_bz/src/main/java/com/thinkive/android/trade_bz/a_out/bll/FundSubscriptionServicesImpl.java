package com.thinkive.android.trade_bz.a_out.bll;

import android.content.Context;
import android.os.Bundle;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_out.bean.FundInfoBean;
import com.thinkive.android.trade_bz.a_out.fragment.FundSbscriptionFragment;
import com.thinkive.android.trade_bz.a_stock.bean.MoneySelectBean;
import com.thinkive.android.trade_bz.a_stock.bean.PublicUseBean;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.others.tools.TradeLoginManager;
import com.thinkive.android.trade_bz.request.Fund302005;
import com.thinkive.android.trade_bz.request.Fund302006;
import com.thinkive.android.trade_bz.request.Fund302030;
import com.thinkive.android.trade_bz.request.Request301504;
import com.thinkive.android.trade_bz.utils.LoadingDialogUtil;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.util.HashMap;

/**
 * 基金交易--认购
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/7/30
 */

public class FundSubscriptionServicesImpl {
    private FundSbscriptionFragment mFragment = null;
    private LoadingDialogUtil loadingDialogUtil;//请求数据状态框 工具类

    public FundSubscriptionServicesImpl(FundSbscriptionFragment fragment) {
        mFragment = fragment;
        loadingDialogUtil = new LoadingDialogUtil(fragment.getContext());
    }

    /**
     * 请求资金账户数据 当前可用金额
     */
    public void requestCurrentMoney() {
        loadingDialogUtil.showLoadingDialog(0);
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("money_type", "0");
        new Request301504(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                loadingDialogUtil.hideDialog();
                MoneySelectBean data = (MoneySelectBean) bundle.getSerializable(Request301504.BUNDLE_KEY_MYHOLD_HEAD);
                mFragment.getMaxSubscribe(data);
            }

            @Override
            public void onFailed(Context context, Bundle bundle) {
                loadingDialogUtil.hideDialog();
                ToastUtils.toast(context, bundle.getString(Request301504.ERROR_INFO));
            }
        }).request();
    }

    /**
     * 请求基金信息和详情
     * @param code
     */
    public void requestFundMessage(String code) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("fund_code",code);
        new Fund302005(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                FundInfoBean data = (FundInfoBean)bundle.getSerializable(Fund302005.BUNDLE_KEY_FUND);
                mFragment.getFundMessage(data);
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                mFragment.getFundMessage();
                ToastUtils.toast(context, bundle.getString(Fund302005.ERROR_INFO));
            }
        }).request();
    }

    /**
     * 请求认购的返回结果
     * @param code
     * @param money
     */
    public void requestSbuscriptionData(String code, String money,String company,String isFlag) {
        loadingDialogUtil.showLoadingDialog(0);
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("fund_code", code);
        map.put("balance", money);
        map.put("fund_company",company);
        map.put("flag", isFlag);
        new Fund302006(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                loadingDialogUtil.hideDialog();
                PublicUseBean data = (PublicUseBean) bundle.getSerializable(Fund302006.BUNDLE_KEY_SUBTRPTION);
                mFragment.getResultForSub(data);
            }

            @Override
            public void onFailed(Context context, Bundle bundle) {
                loadingDialogUtil.hideDialog();
                String errorString = bundle.getString(Fund302006.ERROR_INFO);
                String errorNum =  bundle.getString(Fund302006.ERROR_NO);
                onGetErrorNum(context, errorNum, errorString);
            }
        }).request();
    }

    /**
     * 根据错误号判定
     */
    public void onGetErrorNum(Context context,String errorNum,String errorString){
        if(errorNum.equals("-30200610")){ // 未开户
            mFragment.openFundAccountDialog("-30200610",errorString);
        }else if(errorNum.equals("-30200611")){ // 风险预警
            mFragment.openFundAccountDialog("-30200611",errorString);
        }else{
            ToastUtils.toast(context, errorString);
        }
    }

    /**
     * 请求基金开户
     * @param company
     */
    public void requestOpenAccount(String company) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("stock_account",TradeLoginManager.sNormalUserInfo_shen_A.getStock_account());
        map.put("fund_company", company);
        new Fund302030(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                ToastUtils.toast(context, context.getResources().getString(R.string.fund_open_account));
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(Fund302030.ERROR_INFO));
            }
        }).request();
    }
}
