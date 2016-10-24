package com.thinkive.android.trade_bz.a_rr.bll;

import android.content.Context;
import android.os.Bundle;

import com.thinkive.android.trade_bz.a_rr.bean.RCollaterRevocationBean;
import com.thinkive.android.trade_bz.a_rr.fragment.RCollaterRevocationFragment;
import com.thinkive.android.trade_bz.a_stock.bll.BasicServiceImpl;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.request.RR303018;
import com.thinkive.android.trade_bz.request.RR303024;
import com.thinkive.android.trade_bz.utils.LoadingDialogUtil;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *  融资融券--划转--撤单
 * @author 张雪梅
 * @company Thinkive
 * @date 15/8/13
 */
public class RCollaterRevocationServiceImpl extends BasicServiceImpl {

    private RCollaterRevocationFragment mFragment =null;
    private LoadingDialogUtil loadingDialogUtil;//请求数据状态框 工具类
    public RCollaterRevocationServiceImpl(RCollaterRevocationFragment fragment) {
        mFragment = fragment;
        loadingDialogUtil = new LoadingDialogUtil(fragment.getContext());
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onStop() {

    }
    /**
     * 请求可撤单数据列表
     */
    public void requestRevocationList() {
        HashMap<String, String> map = new HashMap<String, String>();
        new RR303024(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                ArrayList<RCollaterRevocationBean> dataList = bundle.getParcelableArrayList(RR303024.BUNDLE_KEY_RREVOCATION);
                mFragment.getRollaterRevocationList(dataList);
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(RR303024.ERROR_INFO));
            }
        }).request();
    }
    /**
     * 请求撤单
     */
    public void requestRavocationResult(String report) {
        loadingDialogUtil.showLoadingDialog(0);
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("entrust_no",report);
        new RR303018(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                loadingDialogUtil.hideDialog();
                ToastUtils.toast(context, bundle.getString(RR303018.BUNDLE_KEY_REVOCATION_DIALOG));
                requestRevocationList();
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                loadingDialogUtil.hideDialog();
                ToastUtils.toast(context, bundle.getString(RR303018.ERROR_INFO));
            }
        }).request();
    }
}
