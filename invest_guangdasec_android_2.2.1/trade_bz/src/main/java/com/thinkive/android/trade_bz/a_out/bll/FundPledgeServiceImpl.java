package com.thinkive.android.trade_bz.a_out.bll;

import android.content.Context;
import android.os.Bundle;

import com.thinkive.android.trade_bz.request.Fund302048;
import com.thinkive.android.trade_bz.a_out.bean.FundInfoBean;
import com.thinkive.android.trade_bz.a_out.bean.FundReturnMoneyDateBean;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.request.Fund302005;
import com.thinkive.android.trade_bz.request.Fund302057;
import com.thinkive.android.trade_bz.a_out.fragment.FundPledgeFragment;
import com.thinkive.android.trade_bz.utils.LoadingDialogUtil;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 基金定投
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/11/6
 */
public class FundPledgeServiceImpl {

    private FundPledgeFragment mFragment;
    private LoadingDialogUtil loadingDialogUtil;//请求数据状态框 工具类

    public FundPledgeServiceImpl(FundPledgeFragment fragment) {
        mFragment=fragment;
        loadingDialogUtil = new LoadingDialogUtil(fragment.getContext());
    }

    /**
     * 请求还款日期
     */
    public void requestReturnMoneyDate(String company) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("fund_company",company);
        new Fund302057(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                ArrayList<FundReturnMoneyDateBean> dataList = bundle.getParcelableArrayList(Fund302057.BUNDLE_KEY_RETURN_DATE);
                mFragment.onGetFundReturnDate(dataList);
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(Fund302057.ERROR_INFO));
            }
        }).request();
    }

    /**
     * 请求基金信息和详情
     * @param code
     */
    public void requestFundMessage(String code) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("fund_code", code);
        new Fund302005(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                FundInfoBean data = (FundInfoBean)bundle.getSerializable(Fund302005.BUNDLE_KEY_FUND);
                mFragment.getFundMessage(data);
                requestReturnMoneyDate(data.getFund_company());
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                mFragment.getFundMessage();
                ToastUtils.toast(context, bundle.getString(Fund302005.ERROR_INFO));
            }
        }).request();
    }
    /**
     * 基金定投下单
     */
    public void requestSetDriection(String code,String balance,String start,String end,
                                    String day,String company,String save,String allotno) {
        loadingDialogUtil.showLoadingDialog(0);
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("fund_code",code);//基金代码
        map.put("balance",balance);//定投金额
        map.put("start_date",start);//开始日期
        map.put("end_date",end);//结束日期
        map.put("en_fund_date",day);//每月还款日
        map.put("save_plan_cls",save);//定时定额种类
        map.put("allotno",allotno);//协议编号
        if(company != null){
            map.put("fund_company",company);
        }
        new Fund302048(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                loadingDialogUtil.hideDialog();
                String data = bundle.getString(Fund302048.BUNDLE_KEY_DIRECTION);
                mFragment.onGetDirectionResult(data);
            }

            @Override
            public void onFailed(Context context, Bundle bundle) {
                loadingDialogUtil.hideDialog();
                ToastUtils.toast(context, bundle.getString(Fund302048.ERROR_INFO));
            }
        }).request();
    }
}
