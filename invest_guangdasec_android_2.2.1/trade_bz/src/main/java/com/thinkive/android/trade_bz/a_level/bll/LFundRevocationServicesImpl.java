package com.thinkive.android.trade_bz.a_level.bll;

import android.content.Context;
import android.os.Bundle;

import com.thinkive.android.trade_bz.a_level.bean.LFundRevocationBean;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.request.Request301502;
import com.thinkive.android.trade_bz.request.Level302063;
import com.thinkive.android.trade_bz.a_level.fragment.LFundRevocationFragment;
import com.thinkive.android.trade_bz.utils.LoadingDialogUtil;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;
/**
 * 分级基金  撤单
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/11/12
 */

public class LFundRevocationServicesImpl {

    private LFundRevocationFragment mFragment;
    private LoadingDialogUtil loadingDialogUtil;//请求数据状态框 工具类

    public LFundRevocationServicesImpl(LFundRevocationFragment fragment) {
        mFragment=fragment;
        loadingDialogUtil = new LoadingDialogUtil(fragment.getContext());
    }

    /**
     * 请求当日可撤单数据
     */
    public void requestRevocation() {
        HashMap<String, String> map = new HashMap<String, String>();
        new Level302063(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                ArrayList<LFundRevocationBean> dataList = bundle.getParcelableArrayList(Level302063.BUNDLE_KEY_LEVEL_REVOCATION);
                mFragment.onGetRevocationDataList(dataList);
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(Level302063.ERROR_INFO));
            }
        }).request();
    }

    /**
     * 点击撤单按钮后，返回的撤单结果
     */
    public void execRevocation(String entrustNum, String exchange_type) {
        loadingDialogUtil.showLoadingDialog(0);
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("entrust_no", entrustNum);
        map.put("exchange_type", exchange_type);
        new Request301502(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                loadingDialogUtil.hideDialog();
                //显示撤单结果
                ToastUtils.toast(context, bundle.getString(Request301502.BUNDLE_KEY_REVOCATION_DIALOG));
                //请求成功后刷新数据
                requestRevocation();
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                loadingDialogUtil.hideDialog();
                ToastUtils.toast(context, bundle.getString(Request301502.ERROR_INFO));
            }
        }).request();
    }
}
