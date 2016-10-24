package com.thinkive.android.trade_bz.a_hk.bll;

import android.content.Context;
import android.os.Bundle;

import com.thinkive.android.trade_bz.a_hk.fragment.HKHoldPagerFragment;
import com.thinkive.android.trade_bz.a_stock.bean.MoneySelectBean;
import com.thinkive.android.trade_bz.a_stock.bll.BasicServiceImpl;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.request.Request301504;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.util.HashMap;

/**
 *   港股通 资金账户查询的业务类（我的持仓头部的货币信息）
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/6/29
 */

public class HKMyholdPagerServicesImpl extends BasicServiceImpl {
    private HKHoldPagerFragment mHoldPagerFragment = null;

    public HKMyholdPagerServicesImpl(HKHoldPagerFragment mFragment) {
        mHoldPagerFragment = mFragment;
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onStop() {

    }

    /**
     * 请求资金账户数据
     * @param moneyType
     */
    public void requestMyHoldPager(int moneyType) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("money_type", String.valueOf(moneyType));
        new Request301504(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                MoneySelectBean data = (MoneySelectBean)bundle.getSerializable(Request301504.BUNDLE_KEY_MYHOLD_HEAD);
                if (data != null) {
                    mHoldPagerFragment.getMoneyAccountData(data);
                }
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(Request301504.ERROR_INFO));
            }
        }).request();
    }
}
