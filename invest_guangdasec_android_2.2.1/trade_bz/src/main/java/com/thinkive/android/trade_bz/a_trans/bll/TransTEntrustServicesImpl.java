package com.thinkive.android.trade_bz.a_trans.bll;

import android.content.Context;
import android.os.Bundle;

import com.thinkive.android.trade_bz.a_stock.bll.BasicServiceImpl;
import com.thinkive.android.trade_bz.a_trans.bean.TransSelectBean;
import com.thinkive.android.trade_bz.a_trans.fragment.TransTodayEntrustFragment;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.request.Trans301706;
import com.thinkive.android.trade_bz.utils.ToastUtils;
import com.thinkive.android.trade_bz.utils.TradeUtils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *  转股交易 今日委托的业务类
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/12/31
 */

public class TransTEntrustServicesImpl extends BasicServiceImpl {
    private TransTodayEntrustFragment mFragment =null;

    public TransTEntrustServicesImpl(TransTodayEntrustFragment mFragment) {
        this.mFragment =mFragment;
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onStop() {

    }

    public void requestTodayEntrust(){
        HashMap<String, String> map = new HashMap<String, String>();
        new Trans301706(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                ArrayList<TransSelectBean> dataList = bundle.getParcelableArrayList(Trans301706.BUNDLE_KEY_REVOCATION);
                //保留小数点后两位
                for (TransSelectBean bean : dataList) {
                    bean.setBusiness_price(TradeUtils.formatDouble2(bean.getBusiness_price()));
                    bean.setEntrust_price(TradeUtils.formatDouble2(bean.getEntrust_price()));
                }
                mFragment.getTodayEntrustData(dataList);
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(Trans301706.ERROR_INFO));
            }
        }).request();
    }
}
