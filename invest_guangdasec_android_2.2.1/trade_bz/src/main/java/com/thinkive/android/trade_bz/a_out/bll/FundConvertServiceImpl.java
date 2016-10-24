package com.thinkive.android.trade_bz.a_out.bll;

import android.content.Context;
import android.os.Bundle;

import com.thinkive.android.trade_bz.a_stock.bean.PublicUseBean;
import com.thinkive.android.trade_bz.a_out.bean.FundInfoBean;
import com.thinkive.android.trade_bz.a_out.bean.FundHoldBean;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.request.Fund302005;
import com.thinkive.android.trade_bz.request.Fund302010;
import com.thinkive.android.trade_bz.request.Fund302012;
import com.thinkive.android.trade_bz.a_out.fragment.FundConvertFragment;
import com.thinkive.android.trade_bz.utils.LoadingDialogUtil;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 场外基金转换
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/10/27.
 */
public class FundConvertServiceImpl {

    private FundConvertFragment mFragment;
    private LoadingDialogUtil loadingDialogUtil;//请求数据状态框 工具类

    public FundConvertServiceImpl(FundConvertFragment fragment) {
        mFragment=fragment;
        loadingDialogUtil = new LoadingDialogUtil(fragment.getContext());
    }

    /**
     * 请求基金转换（点击转换按钮时执行）
     */
    public void requestFundConvert(String outFund,String inFund,String num,String company) {
        loadingDialogUtil.showLoadingDialog(0);
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("fund_code", outFund);//转出基金代码
        map.put("trans_code", inFund);//转入基金代码
        map.put("trans_amount",num);//转换份额
        map.put("fund_company",company);//转出基金的基金公司
        new Fund302010(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                loadingDialogUtil.hideDialog();
                PublicUseBean data = (PublicUseBean)bundle.getSerializable(Fund302010.BUNDLE_KEY_ONE_KEY_CONVERT);
                mFragment.onGetConvertData(data);
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                loadingDialogUtil.hideDialog();
                ToastUtils.toast(context, bundle.getString(Fund302010.ERROR_INFO));
            }
        }).request();
    }
    /**
     * 请求当持仓基金列表
     */
    public void requestHoldStockList() {
        HashMap<String, String> map = new HashMap<String, String>();
        new Fund302012(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                ArrayList<FundHoldBean> dataList = bundle.getParcelableArrayList(Fund302012.BUNDLE_KEY_FUND_RANSOM);
                mFragment.onGetCanTransDataList(dataList);
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(Fund302012.ERROR_INFO));
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
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                mFragment.getFundMessage();
                ToastUtils.toast(context, bundle.getString(Fund302005.ERROR_INFO));
            }
        }).request();
    }
}
