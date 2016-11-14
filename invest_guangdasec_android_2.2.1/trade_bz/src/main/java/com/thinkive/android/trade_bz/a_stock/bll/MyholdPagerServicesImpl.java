package com.thinkive.android.trade_bz.a_stock.bll;

import android.content.Context;
import android.os.Bundle;

import com.thinkive.android.trade_bz.a_stock.bean.MoneyBean;
import com.thinkive.android.trade_bz.a_stock.fragment.HoldPagerFragment;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.request.CRequest301504;
import com.thinkive.android.trade_bz.request.Request301504;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.util.HashMap;

/**
 *   资金账户查询的业务类（我的持仓头部的货币信息）
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/6/29
 */

public class MyholdPagerServicesImpl  {
    private HoldPagerFragment mHoldPagerFragment = null;

    public MyholdPagerServicesImpl(HoldPagerFragment mFragment) {
        mHoldPagerFragment = mFragment;
    }

    /**
     * 初始化请求到的资金账户数据 出入货币类型
     * @param page
     */
    public void requestMyHoldPager(int page) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("page", page+"");
        new CRequest301504(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                MoneyBean data = (MoneyBean)bundle.getSerializable(CRequest301504.BUNDLE_KEY_MYHOLD_HEAD);
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
