package com.thinkive.android.trade_bz.a_rr.bll;

import android.content.Context;
import android.os.Bundle;

import com.thinkive.android.trade_bz.a_rr.fragment.RSelectHoldStockFragment;
import com.thinkive.android.trade_bz.a_stock.bean.MyStoreStockBean;
import com.thinkive.android.trade_bz.a_stock.bll.BasicServiceImpl;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.request.RR303003;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 融资融券--查询--股份持仓
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/8/17
 */
public class RSelectHoldStockServiceImpl extends BasicServiceImpl {

    private RSelectHoldStockFragment mHoldFragment;

    public RSelectHoldStockServiceImpl(RSelectHoldStockFragment fragment) {
        mHoldFragment = fragment;
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onStop() {

    }
    /**
     * 发起请求，获取持仓列表
     */
    public void requestAllHoldList() {
        HashMap<String, String> map = new HashMap<String, String>();
        new RR303003(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                ArrayList<MyStoreStockBean> dataList = bundle.getParcelableArrayList(RR303003.BUNDLE_KEY_ROLLATER);
                mHoldFragment.getCurrentHoldData(dataList);
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(RR303003.ERROR_INFO));
            }
        }).request();
    }
}
