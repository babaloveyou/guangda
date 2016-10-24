package com.thinkive.android.trade_bz.a_level.bll;

import android.content.Context;
import android.os.Bundle;

import com.thinkive.android.trade_bz.a_level.bean.LFundDivideOrMergerBean;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.request.Level302055;
import com.thinkive.android.trade_bz.request.Level302056;
import com.thinkive.android.trade_bz.a_level.fragment.LFundMergerFragment;
import com.thinkive.android.trade_bz.utils.LoadingDialogUtil;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.util.HashMap;

/**
 * 分级基金合并
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/10/27.
 */

public class LFundMergerServicesImpl {

    private LFundMergerFragment mFragment;
    private LoadingDialogUtil loadingDialogUtil;//请求数据状态框 工具类

    public LFundMergerServicesImpl(LFundMergerFragment fragment) {
        mFragment=fragment;
        loadingDialogUtil = new LoadingDialogUtil(fragment.getContext());
    }

    /**
     * 请求当前基金最大可合并数
     */
    public void requestFundMaxMerger(String fundCode) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("fund_opertype","27");//合并
//        map.put("entrust_bs","27");//合并
        map.put("fund_code",fundCode);
        new Level302056(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                LFundDivideOrMergerBean data = (LFundDivideOrMergerBean)bundle.getSerializable(Level302056.BUNDLE_KEY_OTHER_MONEY);
                mFragment.onGetFundMaxMergerNum(data);
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                mFragment.onGetFailFundMessage();
                ToastUtils.toast(context, bundle.getString(Level302056.ERROR_INFO));
            }
        }).request();
    }
    /**
     * 请求合并
     */
    public void requestFundMerger(String fundCode,String stockAccount,String entrustNum,String changeType) {
        loadingDialogUtil.showLoadingDialog(0);
        HashMap<String, String> map = new HashMap<String, String>();
//        map.put("fund_opertype","27");//合并
        map.put("entrust_bs","27");//合并
        map.put("fund_code",fundCode);
        map.put("entrust_amount",entrustNum);
        if(changeType != null){
            map.put("exchange_type",changeType);
        }
        if(stockAccount != null){
            map.put("stock_account",stockAccount);
        }
        new Level302055(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                loadingDialogUtil.hideDialog();
                LFundDivideOrMergerBean data = (LFundDivideOrMergerBean)bundle.getSerializable(Level302055.BUNDLE_KEY_LEVEL_FUND);
                mFragment.onGetFundMergerResult(data);
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                loadingDialogUtil.hideDialog();
                ToastUtils.toast(context, bundle.getString(Level302055.ERROR_INFO));
            }
        }).request();
    }
}
