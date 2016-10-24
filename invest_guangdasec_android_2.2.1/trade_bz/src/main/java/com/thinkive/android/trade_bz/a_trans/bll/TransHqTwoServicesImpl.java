package com.thinkive.android.trade_bz.a_trans.bll;

import android.content.Context;
import android.os.Bundle;

import com.thinkive.android.trade_bz.a_stock.bll.BasicServiceImpl;
import com.thinkive.android.trade_bz.a_trans.bean.TransSubHqBean;
import com.thinkive.android.trade_bz.a_trans.fragment.TransHqTwoFragment;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.request.Trans301703;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 定价申报行情列表
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/12/31
 */
public class TransHqTwoServicesImpl extends BasicServiceImpl {
    private TransHqTwoFragment mFragment = null;

    public TransHqTwoServicesImpl(TransHqTwoFragment mFragment) {
        this.mFragment = mFragment;
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onStop() {

    }

    /**
     * 初始化请求到的数据
     */
    public void requestLimitSubData(String code, String bsflag) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("stock_code",code);
        map.put("bsflag",bsflag);
        new Trans301703(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                ArrayList<TransSubHqBean> dataList = bundle.getParcelableArrayList(Trans301703.BUNDLE_KEY_SUB_SELECT);
                mFragment.getTodayTradeData(dataList);
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(Trans301703.ERROR_INFO));
            }
        }).request();
    }
}
