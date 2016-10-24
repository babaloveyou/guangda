package com.thinkive.android.trade_bz.a_trans.bll;

import android.content.Context;
import android.os.Bundle;

import com.thinkive.android.trade_bz.a_stock.bll.BasicServiceImpl;
import com.thinkive.android.trade_bz.a_trans.bean.TransSelectBean;
import com.thinkive.android.trade_bz.a_trans.fragment.TransRevocationFragment;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.request.Trans301704;
import com.thinkive.android.trade_bz.request.Trans301705;
import com.thinkive.android.trade_bz.utils.LoadingDialogUtil;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 转股交易撤单查询
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/1/5
 */
public class TransRevocationServicesImpl extends BasicServiceImpl {
    private TransRevocationFragment mFragment = null;
    private LoadingDialogUtil loadingDialogUtil;//请求数据状态框 工具类

    public TransRevocationServicesImpl(TransRevocationFragment mFragment) {
        this.mFragment = mFragment;
        loadingDialogUtil = new LoadingDialogUtil(mFragment.getContext());
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onStop() {

    }

    /**
     * 请求可撤单列表
     */
    public void requestRevocationList() {
        HashMap<String, String> map = new HashMap<String, String>();
        new Trans301704(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                ArrayList<TransSelectBean> dataList = bundle.getParcelableArrayList(Trans301704.BUNDLE_KEY_REVOCATION);
                mFragment.getRevocationData(dataList);
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(Trans301704.ERROR_INFO));
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
        new Trans301705(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                loadingDialogUtil.hideDialog();
                //显示撤单结果
                ToastUtils.toast(context, bundle.getString(Trans301705.BUNDLE_KEY_REVO_TRANS));
                //请求成功后刷新数据
                requestRevocationList();
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                loadingDialogUtil.hideDialog();
                ToastUtils.toast(context, bundle.getString(Trans301705.ERROR_INFO));
            }
        }).request();
    }
}
