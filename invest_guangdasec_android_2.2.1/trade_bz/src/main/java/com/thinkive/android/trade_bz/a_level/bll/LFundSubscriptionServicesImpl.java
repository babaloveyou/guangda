package com.thinkive.android.trade_bz.a_level.bll;

import android.content.Context;
import android.os.Bundle;

import com.thinkive.android.trade_bz.a_stock.bean.MoneySelectBean;
import com.thinkive.android.trade_bz.a_stock.bean.StockLinkageBean;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.request.Request301501;
import com.thinkive.android.trade_bz.request.Request301504;
import com.thinkive.android.trade_bz.request.Request301514;
import com.thinkive.android.trade_bz.a_level.fragment.LFundSubscriptionFragment;
import com.thinkive.android.trade_bz.utils.LoadingDialogUtil;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.util.HashMap;

/**
 * 基金交易--认购
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/7/30
 */

public class LFundSubscriptionServicesImpl {
    private LFundSubscriptionFragment mFragment = null;
    private LoadingDialogUtil loadingDialogUtil;//请求数据状态框 工具类

    public LFundSubscriptionServicesImpl(LFundSubscriptionFragment fragment) {
        mFragment = fragment;
        loadingDialogUtil = new LoadingDialogUtil(fragment.getContext());
    }

    /**
     * 资金账户查询
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
     * 请求基金详情
     * @param code
     */
    public void requestFundMessage(String code) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("stock_code", code);
        map.put("entrust_bs","17");
        new Request301514(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                StockLinkageBean data = (StockLinkageBean) bundle.getSerializable(Request301514.BUNDLE_KEY_LINKAGE);
                mFragment.getFundMessage(data);
            }

            @Override
            public void onFailed(Context context, Bundle bundle) {
                mFragment.getFundMessage();
                ToastUtils.toast(context, bundle.getString(Request301514.ERROR_INFO));
            }
        }).request();
    }


    /**
     * 返回基金申购的结果
     * @param code
     */
    public void requestFundSubscription(String type, String account,String code,String money,String num) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("entrust_bs","17");
        map.put("stock_code", code);
        map.put("entrust_price", money);
        map.put("entrust_amount",num);
        if(type != null){
            map.put("exchange_type",type);
        }
        if(account != null){
            map.put("stock_account",account);
        }
        new Request301501(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                mFragment.getFundSubscription(bundle.getString(Request301501.BUNDLE_KEY_RESULT));
            }

            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(Request301501.ERROR_INFO));
            }
        }).request();
    }
}
