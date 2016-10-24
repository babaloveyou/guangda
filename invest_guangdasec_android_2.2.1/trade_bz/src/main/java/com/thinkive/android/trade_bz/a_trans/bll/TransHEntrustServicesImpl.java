package com.thinkive.android.trade_bz.a_trans.bll;

import android.content.Context;
import android.os.Bundle;

import com.thinkive.android.trade_bz.a_stock.bll.BasicServiceImpl;
import com.thinkive.android.trade_bz.a_trans.bean.TransSelectBean;
import com.thinkive.android.trade_bz.a_trans.fragment.TransHistoryEntrustFragment;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.request.Trans301707;
import com.thinkive.android.trade_bz.utils.ToastUtils;
import com.thinkive.android.trade_bz.utils.TradeUtils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 转股交易历史委托
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/12/31
 */
public class TransHEntrustServicesImpl extends BasicServiceImpl {
    private TransHistoryEntrustFragment mFragment = null;

    public TransHEntrustServicesImpl(TransHistoryEntrustFragment mFragment) {
        this.mFragment = mFragment;
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onStop() {

    }

    /**
     * 初始化请求到的历史委托数据
     * @param begin
     * @param end
     */
    public void requestHistoryEntrust(String begin, String end) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("begin_time", begin);
        map.put("end_time", end);
        new Trans301707(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                ArrayList<TransSelectBean> dataList = bundle.getParcelableArrayList(Trans301707.BUNDLE_KEY_REVOCATION);
                //保留小数点后两位
                for (TransSelectBean bean : dataList) {
                    bean.setBusiness_price(TradeUtils.formatDouble2(bean.getBusiness_price()));
                    bean.setEntrust_price(TradeUtils.formatDouble2(bean.getEntrust_price()));
                }
                mFragment.getHistoryEntrustData(dataList);
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(Trans301707.ERROR_INFO));
            }

        }).request();
    }
}
