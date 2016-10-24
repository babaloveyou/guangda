package com.thinkive.android.trade_bz.a_stock.bll;

import android.content.Context;
import android.os.Bundle;

import com.thinkive.android.trade_bz.a_stock.bean.MyStoreStockBean;
import com.thinkive.android.trade_bz.a_stock.fragment.MyHoldStockFragment;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.request.Request301503;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 我的持仓ListView业务类
 * @author 张雪梅
 * @version 1.0
 * @corporation
 * @date 2015/6/26
 */
public class MyHoldStockServiceImpl {

    private MyHoldStockFragment mFragment =null;
    public MyHoldStockServiceImpl(MyHoldStockFragment fragment) {
        mFragment = fragment;
    }

    /**
     * 发起请求，获取持仓列表
     */
    public void requestMyHoldStock() {
        HashMap<String, String> paramMap = new HashMap<String, String>();
        new Request301503(paramMap, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                ArrayList<MyStoreStockBean> dataList = bundle.getParcelableArrayList(Request301503.BUNDLE_KEY_RESULT);
                mFragment.getStoreData(dataList);
            }

            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(Request301503.ERROR_INFO));
            }
        }).request();
    }
}
