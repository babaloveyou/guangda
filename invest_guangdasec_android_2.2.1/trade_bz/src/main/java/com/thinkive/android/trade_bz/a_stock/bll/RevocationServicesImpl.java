package com.thinkive.android.trade_bz.a_stock.bll;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;

import com.thinkive.android.trade_bz.a_stock.bean.RevocationBean;
import com.thinkive.android.trade_bz.a_stock.fragment.BottomRevocationFragment;
import com.thinkive.android.trade_bz.a_stock.fragment.RevocationFragment;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.request.Request301502;
import com.thinkive.android.trade_bz.request.Request301515;
import com.thinkive.android.trade_bz.request.Request306001;
import com.thinkive.android.trade_bz.utils.LoadingDialogUtil;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * （1）撤单列表的业务类
 * （2）点击撤单按钮后，返回的撤单结果业务类
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/6/17
 */

public class RevocationServicesImpl {
    private boolean isBottom = false;
    private RevocationFragment mRevocationFragment=null;
    private BottomRevocationFragment mBottomRevocationFragment=null;
    private LoadingDialogUtil loadingDialogUtil;//请求数据状态框 工具类
    public RevocationServicesImpl(RevocationFragment mFragment) {
        mRevocationFragment=mFragment;
        loadingDialogUtil = new LoadingDialogUtil(mFragment.getContext());
    }
    public RevocationServicesImpl(BottomRevocationFragment mFragment) {
        mBottomRevocationFragment=mFragment;
        loadingDialogUtil = new LoadingDialogUtil(mFragment.getContext());
        isBottom = true;
    }

    /**
     * 发起请求，查询撤单列表
     */
    public void requestRevocation(){
        HashMap<String, String> map306001 = new HashMap<String, String>();
        new Request306001(map306001, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                String date = bundle.getString(Request306001.BUNDLE_KEY_306001);
                HashMap<String, String> map301515 = new HashMap<>();
                map301515.put("start_date", date);
                new Request301515(map301515, new IRequestAction() {
                    @Override
                    public void onSuccess(Context context, Bundle bundle) {
                        ArrayList<RevocationBean> dataList = bundle.getParcelableArrayList(Request301515.BUNDLE_KEY_REVOCATION);
                        //保留小数点后两位
                        //                for (RevocationBean bean : dataList) {
                        //                    bean.setBusiness_price(TradeUtils.formatDouble2(bean.getBusiness_price()));
                        //                    bean.setEntrust_price(TradeUtils.formatDouble2(bean.getEntrust_price()));
                        //                }
                        if (isBottom) {
                            mBottomRevocationFragment.onGetStoreData(dataList);
                        } else {
                            mRevocationFragment.onGetStoreData(dataList);
                        }
                    }
                    @Override
                    public void onFailed(Context context, Bundle bundle) {
                        ToastUtils.toast(context, bundle.getString(Request301515.ERROR_INFO));
                    }
                }).request();
            }

            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(Request306001.ERROR_INFO));
            }
        }).request();

    }

    /**
     * 点击撤单按钮后，返回的撤单结果
     */
    public void execRevocation(String entrustNum, String exchange_type) {
        loadingDialogUtil.showLoadingDialog(0);//显示请求状态框
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("entrust_no", entrustNum);
        map.put("exchange_type", exchange_type);
        new Request301502(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                loadingDialogUtil.hideDialog();//隐藏状态显示框
                if (!TextUtils.isEmpty(bundle.getString(Request301502.BUNDLE_KEY_REVOCATION_DIALOG))) {
                    ToastUtils.toast(context, bundle.getString(Request301502.BUNDLE_KEY_REVOCATION_DIALOG));
                } else {
                    ToastUtils.toast(context,"撤单成功");
                }
                //请求成功后刷新数据
                requestRevocation();
                if (isBottom) {
                    mBottomRevocationFragment.refreshAdapter();
                } else {
                    mRevocationFragment.refreshAdapter();
                }
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                loadingDialogUtil.hideDialog();//隐藏状态显示框
                ToastUtils.toast(context, bundle.getString(Request301502.ERROR_INFO));
            }
        }).request();
    }
}
