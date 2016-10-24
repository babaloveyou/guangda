package com.thinkive.android.trade_bz.a_rr.bll;

import android.content.Context;
import android.os.Bundle;

import com.thinkive.android.trade_bz.a_rr.bean.RRevocationBean;
import com.thinkive.android.trade_bz.a_rr.fragment.RRevocationFragment;
import com.thinkive.android.trade_bz.a_stock.bll.BasicServiceImpl;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.request.RR303017;
import com.thinkive.android.trade_bz.request.RR303018;
import com.thinkive.android.trade_bz.utils.LoadingDialogUtil;
import com.thinkive.android.trade_bz.utils.ToastUtils;
import com.thinkive.android.trade_bz.utils.TradeUtils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *  融资融券--下单--撤单（303017）
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/8/24
 */
public class RRevocationServiceImpl extends BasicServiceImpl {

    private RRevocationFragment mFragment;
    private LoadingDialogUtil loadingDialogUtil;//请求数据状态框 工具类
    public RRevocationServiceImpl(RRevocationFragment fragment) {
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
     *请求撤单数据列表
     */
    public void requestRevocationData() {
        HashMap<String, String> map = new HashMap<String, String>();
        new RR303017(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                ArrayList<RRevocationBean> dataList = bundle.getParcelableArrayList(RR303017.BUNDLE_KEY_R_REVOCATION);
                for(RRevocationBean bean : dataList){
                    bean.setEntrust_price(TradeUtils.formatDouble2(bean.getEntrust_price()));
                    bean.setBusiness_price(TradeUtils.formatDouble2(bean.getBusiness_price()));
                }
                mFragment.getRevocationData(dataList);
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(RR303017.ERROR_INFO));
            }
        }).request();
    }

    /**
     * 点击撤单按钮后，返回的撤单结果
     */
    public void execRevocation(String entrustNum, String exchange_type, String batch_flag) {
        loadingDialogUtil.showLoadingDialog(0);
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("batch_flag",batch_flag);
        map.put("entrust_no", entrustNum);
        map.put("exchange_type", exchange_type);
        new RR303018(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                loadingDialogUtil.hideDialog();
                //显示撤单结果
                ToastUtils.toast(context, bundle.getString(RR303018.BUNDLE_KEY_REVOCATION_DIALOG));
                //请求成功后刷新数据
                requestRevocationData();
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                loadingDialogUtil.hideDialog();
                ToastUtils.toast(context, bundle.getString(RR303018.ERROR_INFO));
            }
        }).request();
    }
}
