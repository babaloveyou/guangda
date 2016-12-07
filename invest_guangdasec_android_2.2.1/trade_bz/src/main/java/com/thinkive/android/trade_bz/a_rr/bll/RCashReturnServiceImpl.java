package com.thinkive.android.trade_bz.a_rr.bll;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;

import com.thinkive.android.trade_bz.a_rr.bean.RChooseContractBean;
import com.thinkive.android.trade_bz.a_rr.bean.RStockToStockLinkBean;
import com.thinkive.android.trade_bz.a_rr.fragment.RCashReturnFragment;
import com.thinkive.android.trade_bz.a_stock.bll.BasicServiceImpl;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.request.RR303010;
import com.thinkive.android.trade_bz.request.RR303012;
import com.thinkive.android.trade_bz.utils.LoadingDialogUtil;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;


/**
 *  融资融券--现金还款
 * @author 张雪梅
 * @company Thinkive
 * @date 16/1/20
 */
public class RCashReturnServiceImpl extends BasicServiceImpl {

    private RCashReturnFragment mFragment = null;
    private boolean mIsContractEntrust = false;
    private LoadingDialogUtil loadingDialogUtil;//请求数据状态框 工具类

    public RCashReturnServiceImpl(RCashReturnFragment fragment) {
        mFragment = fragment;
        loadingDialogUtil = new LoadingDialogUtil(fragment.getContext());
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onStop() {

    }
//
//    /**
//     * 请求合约数据，接口功能号303021
//     */
//    public void requestChooseContract(String code) {
//        HashMap<String, String> map = new HashMap<String, String>();
//        map.put("compact_type","0"); //合约类型(0-融资，1-融券)
//        map.put("query_type","0"); //查询模式(0-未了结，1-当日已了结)
//        if(code != null && !TextUtils.isEmpty(code)){
//            map.put("stock_code",code);
//        }
//        new RR303021(map, new IRequestAction() {
//            @Override
//            public void onSuccess(Context context, Bundle bundle) {
//                ArrayList<RChooseContractBean> dataList = bundle.getParcelableArrayList(RR303021.BUNDLE_KEY_R_REVOCATION);
//                mFragment.getChooseContractData(dataList);
//            }
//
//            @Override
//            public void onFailed(Context context, Bundle bundle) {
//                ToastUtils.toast(context, bundle.getString(RR303021.ERROR_INFO));
//            }
//        }).request();
//    }

    /**
     * 联动获得可用金额和需还金额
     */
    public void requestStockLink() {
        HashMap<String, String> paramMap = new HashMap<String, String>();
        new RR303012(paramMap, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                RStockToStockLinkBean data = (RStockToStockLinkBean)bundle.getSerializable(RR303012.BUNDLE_KEY_LINK_RETURN);
                mFragment.onGetCanUseMoney(data);
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(RR303012.ERROR_INFO));
            }
        }).request();
    }

    /**
     * 现金还款提交委托
     */
    public void requrstCommit(String balance) {
        loadingDialogUtil.showLoadingDialog(0);
        HashMap<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("occur_balance",balance);
        new RR303010(paramMap, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                loadingDialogUtil.hideDialog();
                mFragment.onSuccessEntrust(bundle.getString(RR303010.BUNDLE_KEY_ENTRUST_COMMIT));
                mFragment.clearAllData();
//                requestChooseContract("");//请求合约
                requestStockLink(); // 联动获得可用金额和需还金额
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                loadingDialogUtil.hideDialog();
                ToastUtils.toast(context, bundle.getString(RR303010.ERROR_INFO));
            }
        }).request();
    }
    /**
     * 获取合约Id
     * @param dataList
     */
    private String getCompactIdForMate(ArrayList<RChooseContractBean> dataList){
        StringBuilder stockMsg = new StringBuilder();
        String result = "";
        if(dataList != null && dataList.size() > 0) {
            for (RChooseContractBean bean : dataList) {
                if(bean.isChecked()){
                    String tempStr = "";
                    tempStr = bean.getCompact_id() +"|";
                    stockMsg.append(tempStr);
                }
            }
            if(!TextUtils.isEmpty(stockMsg) && stockMsg.length() > 0){
                result = stockMsg.deleteCharAt(stockMsg.lastIndexOf("|")).toString();
            }else{
                result ="";
            }
        }
        return result;
    }
    public void setIsContractEntrust(boolean mIsContractEntrust) {
        this.mIsContractEntrust = mIsContractEntrust;
    }
    public boolean isIsContractEntrust() {
        return mIsContractEntrust;
    }
}
