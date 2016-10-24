package com.thinkive.android.trade_bz.a_stock.bll;

import android.content.Context;
import android.os.Bundle;

import com.thinkive.android.trade_bz.a_stock.bean.SignAgreementBean;
import com.thinkive.android.trade_bz.a_stock.bean.SignStockAccountLimitBean;
import com.thinkive.android.trade_bz.a_stock.fragment.SignAgreementFragment;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.request.Request300112;
import com.thinkive.android.trade_bz.request.Request300113;
import com.thinkive.android.trade_bz.request.Request300115;
import com.thinkive.android.trade_bz.request.Request301512;
import com.thinkive.android.trade_bz.utils.LoadingDialogUtil;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *  退市板块协议签署
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/8/1
 */

public class SignAgreementImpl {
    private SignAgreementFragment mFragment =null;
    private LoadingDialogUtil loadingDialogUtil;//请求数据状态框 工具类

    public SignAgreementImpl(SignAgreementFragment mFragment) {
        this.mFragment =mFragment;
        loadingDialogUtil = new LoadingDialogUtil(mFragment.getContext());
    }

    /**
     * 请求股东列表
     */
    public void requestStockAccountList(){
        HashMap<String, String> map = new HashMap<String, String>();
        new Request301512(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                ArrayList<SignAgreementBean> dataList = bundle.getParcelableArrayList(Request301512.BUNDLE_KEY_STOCK_ACCOUNT);
                mFragment.onGetStockAccountList(dataList);
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(Request301512.ERROR_INFO));
            }
        }).request();
    }

    /**
     * 客户证券账户权限查询
     */
    public void requestStockAccountLimit(String stockAccount,String exchangeType){
        loadingDialogUtil.showLoadingDialog(0);
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("stock_account",stockAccount);
        map.put("exchange_type",exchangeType);
        new Request300115(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                loadingDialogUtil.hideDialog();
                ArrayList<SignStockAccountLimitBean> dataList = bundle.getParcelableArrayList(Request300115.BUNDLE_KEY_STOCK_ACCOUNT);
                mFragment.onGetAccountLimit(dataList);
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                loadingDialogUtil.hideDialog();
                ToastUtils.toast(context, bundle.getString(Request300115.ERROR_INFO));
            }
        }).request();
    }

    /**
     * 提交业务
     */
    public void requestLimitCommit(SignStockAccountLimitBean bean){
        loadingDialogUtil.showLoadingDialog(0);
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("client_rights",bean.getBusiness_type());
        map.put("stock_account",bean.getStock_account());
        if(bean.getHas_delist().equals("0")){ //未开通 即开通
            new Request300112(map, new IRequestAction() {
                @Override
                public void onSuccess(Context context, Bundle bundle) {
                    loadingDialogUtil.hideDialog();
                    ToastUtils.toast(context, bundle.getString(Request300112.ERROR_INFO));
                    mFragment.clearAllData();
                }
                @Override
                public void onFailed(Context context, Bundle bundle) {
                    loadingDialogUtil.hideDialog();
                    ToastUtils.toast(context, bundle.getString(Request300112.ERROR_INFO));
                }
            }).request();
        }else if(bean.getHas_delist().equals("1")){ // 已开通 即取消
            new Request300113(map, new IRequestAction() {
                @Override
                public void onSuccess(Context context, Bundle bundle) {
                    loadingDialogUtil.hideDialog();
                    ToastUtils.toast(context, bundle.getString(Request300113.ERROR_INFO));
                    mFragment.clearAllData();
                }
                @Override
                public void onFailed(Context context, Bundle bundle) {
                    loadingDialogUtil.hideDialog();
                    ToastUtils.toast(context, bundle.getString(Request300113.ERROR_INFO));
                }
            }).request();
        }
    }
}
