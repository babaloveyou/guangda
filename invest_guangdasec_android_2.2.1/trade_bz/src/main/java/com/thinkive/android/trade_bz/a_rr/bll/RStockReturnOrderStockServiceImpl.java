package com.thinkive.android.trade_bz.a_rr.bll;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;

import com.thinkive.android.trade_bz.a_rr.bean.RChooseContractBean;
import com.thinkive.android.trade_bz.a_rr.bean.RStockToStockLinkBean;
import com.thinkive.android.trade_bz.a_rr.fragment.RStockReturnOrderStockFragment;
import com.thinkive.android.trade_bz.a_stock.bean.MyStoreStockBean;
import com.thinkive.android.trade_bz.a_stock.bll.BasicServiceImpl;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.request.RR303003;
import com.thinkive.android.trade_bz.request.RR303011;
import com.thinkive.android.trade_bz.request.RR303013;
import com.thinkive.android.trade_bz.request.RR303021;
import com.thinkive.android.trade_bz.utils.LoadingDialogUtil;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 融资融券现券还券（直接还券）
 * @author 张雪梅
 * @date 2016/7/18
 */
public class RStockReturnOrderStockServiceImpl extends BasicServiceImpl {

    private RStockReturnOrderStockFragment mFragment = null;
    private boolean mIsContractEntrust = false;
    private LoadingDialogUtil loadingDialogUtil;//请求数据状态框 工具类
    public RStockReturnOrderStockServiceImpl(RStockReturnOrderStockFragment fragment){
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
     * 发起请求，获取持仓列表
     */
    public void getHoldList() {
        HashMap<String, String> paramMap = new HashMap<String, String>();
        new RR303003(paramMap, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                ArrayList<MyStoreStockBean> dataList = bundle.getParcelableArrayList(RR303003.BUNDLE_KEY_ROLLATER);
                mFragment.getStoreData(dataList);
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(RR303003.ERROR_INFO));
            }
        }).request();
    }

    /**
     * 请求合约数据，接口功能号303021
     */
    public void requestChooseContract(String code) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("compact_type","1"); //合约类型(0-融资，1-融券)
        map.put("query_type","0"); //查询模式(0-未了结，1-当日已了结)
        if(code != null && !TextUtils.isEmpty(code)){
            map.put("stock_code",code);
        }
        new RR303021(map, new IRequestAction() {
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
     * 联动
     */
    public void requestStockLink(String stockCode) {
        HashMap<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("stock_code",stockCode);
        new RR303013(paramMap, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                RStockToStockLinkBean data = (RStockToStockLinkBean)bundle.getSerializable(RR303013.BUNDLE_KEY_STOCK_TO_STOCK_LIAN);
                mFragment.onGetLinkResult(data);
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(RR303013.ERROR_INFO));
            }
        }).request();
    }

    /**
     * 现券还券提交委托
     */
    public void requestCommit(RStockToStockLinkBean bean, String num) {
        loadingDialogUtil.showLoadingDialog(0);
        HashMap<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("exchange_type",bean.getExchange_type());
        paramMap.put("stock_account",bean.getStock_account());
        paramMap.put("stock_code",bean.getStock_code());
        paramMap.put("entrust_amount",num);
        if(mIsContractEntrust){ //合约模式才会使用
            paramMap.put("compact_id",getCompactIdForMate(mFragment.getSelectContractList()));
        }
        new RR303011(paramMap, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                loadingDialogUtil.hideDialog();
                ToastUtils.toast(context, bundle.getString(RR303011.BUNDLE_KEY_STOCK_RETURN));
                mFragment.clearAllData();
                getHoldList();
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                loadingDialogUtil.hideDialog();
                ToastUtils.toast(context, bundle.getString(RR303011.ERROR_INFO));
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
