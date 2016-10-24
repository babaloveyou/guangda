package com.thinkive.android.trade_bz.a_rr.bll;

import android.content.Context;
import android.os.Bundle;

import com.thinkive.android.trade_bz.a_rr.bean.RChooseContractBean;
import com.thinkive.android.trade_bz.a_rr.fragment.RContractExpandFragment;
import com.thinkive.android.trade_bz.a_stock.bll.BasicServiceImpl;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.request.RR303021;
import com.thinkive.android.trade_bz.request.RR303055;
import com.thinkive.android.trade_bz.utils.LoadingDialogUtil;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 融资融券——下单——合约展期——选择合约
 *
 * @author 王延龙
 * @company ThinkIve
 * @date 2016/4/19.
 */
public class RChooseContractExpandServiceImpl extends BasicServiceImpl {

    private RContractExpandFragment mFragment;
    private LoadingDialogUtil loadingDialogUtil;//请求数据状态框 工具类

    public RChooseContractExpandServiceImpl(RContractExpandFragment fragment) {
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
     * 合约请求
     */
    public void getContract() {
        HashMap<String, String> paramMap = new HashMap<String, String>();
//        paramMap.put("compact_type","1"); //合约类型(0-融资，1-融券)
        paramMap.put("query_type","0"); //查询模式(0-未了结，1-当日已了结)
        new RR303021(paramMap, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                ArrayList<RChooseContractBean> dataList = bundle.getParcelableArrayList(RR303021.BUNDLE_KEY_R_REVOCATION);
                mFragment.getChooseContractData(dataList);
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(RR303021.ERROR_INFO));
            }
        }).request();
    }


    /**
     * 请求合约数据，接口功能号303055
     * 注：这个接口是在委托提交的时候调用
     */
    public void requestChooseContractExtension(RChooseContractBean bean) {
        loadingDialogUtil.showLoadingDialog(0);
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("exchange_type",bean.getExchange_type());//市场交易类别exchange_type
        map.put("open_date",bean.getOpen_date());//合约开仓日期open_date
        map.put("end_date",bean.getRet_end_date());//合约截止日期ret_end_date
        map.put("compact_id",bean.getCompact_id());//合约合同号compact_id
        new RR303055(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                loadingDialogUtil.hideDialog();
                ToastUtils.toast(context, bundle.getString(RR303055.BUNDLE_KEY_CHOOSE_EXTENSION));
                mFragment.clearAllData();
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                loadingDialogUtil.hideDialog();
                ToastUtils.toast(context, bundle.getString(RR303055.ERROR_INFO));
            }
        }).request();
    }
}
