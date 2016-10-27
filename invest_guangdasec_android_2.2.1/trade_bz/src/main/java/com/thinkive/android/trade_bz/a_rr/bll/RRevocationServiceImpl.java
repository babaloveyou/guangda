package com.thinkive.android.trade_bz.a_rr.bll;

import android.content.Context;
import android.os.Bundle;

import com.thinkive.android.trade_bz.a_rr.bean.RRevocationBean;
import com.thinkive.android.trade_bz.a_rr.fragment.CreditBottomRevocationFragment;
import com.thinkive.android.trade_bz.a_rr.fragment.RRevocationFragment;
import com.thinkive.android.trade_bz.a_stock.bll.BasicServiceImpl;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.request.RR303018;
import com.thinkive.android.trade_bz.request.Request303017;
import com.thinkive.android.trade_bz.request.Request306000;
import com.thinkive.android.trade_bz.request.Request306001;
import com.thinkive.android.trade_bz.utils.LoadingDialogUtil;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 融资融券--下单--撤单（303017）
 *
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/8/24
 */
public class RRevocationServiceImpl extends BasicServiceImpl {

    private RRevocationFragment mRRevocationFragmentt;
    private CreditBottomRevocationFragment mCreditBottomRevocationFragment;
    private LoadingDialogUtil loadingDialogUtil;//请求数据状态框 工具类
    private boolean isBottom;

    public RRevocationServiceImpl(RRevocationFragment fragment) {
        mRRevocationFragmentt = fragment;
        loadingDialogUtil = new LoadingDialogUtil(fragment.getContext());
        isBottom = false;
    }

    public RRevocationServiceImpl(CreditBottomRevocationFragment fragment) {
        mCreditBottomRevocationFragment = fragment;
        loadingDialogUtil = new LoadingDialogUtil(fragment.getContext());
        isBottom = true;
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onStop() {

    }

    /**
     * 请求撤单数据列表
     */
    public void requestRevocationData() {
        //今日委托需传入data字段
        HashMap<String, String> map306000 = new HashMap<String, String>();
        new Request306000(map306000, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                String date = bundle.getString(Request306000.BUNDLE_KEY_306000);
                HashMap<String, String> map303017 = new HashMap<>();
                map303017.put("start_date", date);

                new Request303017(map303017, new IRequestAction() {
                    @Override
                    public void onSuccess(Context context, Bundle bundle) {
                        ArrayList<RRevocationBean> dataList = bundle.getParcelableArrayList(Request303017.BUNDLE_KEY_REVOCATION);
                        if (isBottom) {
                            mCreditBottomRevocationFragment.onGetRevocationData(dataList);
                        } else {
                            mRRevocationFragmentt.getRevocationData(dataList);
                        }
                    }

                    @Override
                    public void onFailed(Context context, Bundle bundle) {
                        ToastUtils.toast(context, bundle.getString(Request303017.ERROR_INFO));
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
    public void execRevocation(String entrustNum, String exchange_type, String batch_flag) {
        loadingDialogUtil.showLoadingDialog(0);
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("batch_flag", batch_flag);
        map.put("entrust_no", entrustNum);
        map.put("exchange_type", exchange_type);
        new RR303018(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                loadingDialogUtil.hideDialog();
                //显示撤单结果
                // ToastUtils.toast(context, bundle.getString(RR303018.BUNDLE_KEY_REVOCATION_DIALOG));
                //请求成功后刷新数据
                requestRevocationData();
                if (isBottom) {
                    mCreditBottomRevocationFragment.refreshAdapter();
                } else {
                    mRRevocationFragmentt.refreshComplete();
                }
            }

            @Override
            public void onFailed(Context context, Bundle bundle) {
                loadingDialogUtil.hideDialog();
                ToastUtils.toast(context, bundle.getString(RR303018.ERROR_INFO));
            }
        }).request();
    }
}
