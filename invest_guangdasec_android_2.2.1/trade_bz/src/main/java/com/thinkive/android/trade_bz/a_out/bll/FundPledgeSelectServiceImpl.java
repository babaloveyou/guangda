package com.thinkive.android.trade_bz.a_out.bll;

import android.content.Context;
import android.os.Bundle;

import com.thinkive.android.trade_bz.a_out.bean.FundPledgeSelectBean;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.request.Fund302050;
import com.thinkive.android.trade_bz.request.Fund302051;
import com.thinkive.android.trade_bz.a_out.fragment.FundPledgeSelectFragment;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 场外基金定投查询
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/10/27.
 */
public class FundPledgeSelectServiceImpl {

    private FundPledgeSelectFragment mFragment;
    public FundPledgeSelectServiceImpl(FundPledgeSelectFragment fragment) {
        mFragment=fragment;
    }

    /**
     * 请求基金定投数据
     */
    public void requestFundPledgeSelect() {
        HashMap<String, String> map = new HashMap<String, String>();
        new Fund302051(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                ArrayList<FundPledgeSelectBean> dataList = bundle.getParcelableArrayList(Fund302051.BUNDLE_KEY_PLEDGE_SELECT);
                mFragment.onGetPledgeSelectData(dataList);
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(Fund302051.ERROR_INFO));
            }
        }).request();
    }

    /**
     * 终止定投
     */
    public void requestCutPledge(FundPledgeSelectBean bean) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("stock_account", bean.getStock_account()); //证券账号
        map.put("allotno", bean.getAllotno()); // 申请编号
        map.put("fund_company", bean.getFund_company()); // 基金公司
        map.put("fund_code", bean.getFund_code()); //  基金代码
        map.put("en_fund_date", bean.getEn_fund_date()); // 扣款允许日

        new Fund302050(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(Fund302050.BUNDLE_KEY_REVOCATION_DIALOG));
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(Fund302050.ERROR_INFO));
            }
        }).request();
    }
}
