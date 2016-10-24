package com.thinkive.android.trade_bz.a_hk.bll;

import android.content.Context;
import android.os.Bundle;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_hk.bean.HKStockMessageBean;
import com.thinkive.android.trade_bz.a_hk.fragment.HKCompanyActionFragment;
import com.thinkive.android.trade_bz.a_stock.bll.BasicServiceImpl;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.others.tools.TradeLoginManager;
import com.thinkive.android.trade_bz.request.HK301624;
import com.thinkive.android.trade_bz.request.RequestHQ50000;
import com.thinkive.android.trade_bz.utils.LoadingDialogUtil;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.util.HashMap;

/**
 *  公司行为
 * @author 张雪梅
 * @company Thinkive
 * @date 16/5/23
 */


public class HKCompanyActionServicesImpl extends BasicServiceImpl {
    private HKCompanyActionFragment mFragment = null;
    private LoadingDialogUtil loadingDialogUtil;//请求数据状态框 工具类

    public HKCompanyActionServicesImpl(HKCompanyActionFragment mFragment) {
        this.mFragment = mFragment;
        loadingDialogUtil = new LoadingDialogUtil(mFragment.getContext());
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onStop() {

    }

    /**
     * 从行情联动证券
     * @param stockCode
     */
    public void requestVoteSubLink(String stockCode) {
        HashMap<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("version","1");
        paramMap.put("field","4:5:22:24:41:42:43:48:2");
        if(stockCode != null && stockCode.length() == 5){
            paramMap.put("stock_list","HK"+":"+"0"+stockCode);
        }else {
            paramMap.put("stock_list","HK"+":"+stockCode);
        }
        new RequestHQ50000(paramMap, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                HKStockMessageBean data = (HKStockMessageBean)bundle.getSerializable(RequestHQ50000.BUNDLE_KEY_SOCKET_HK);
                if(data.getIs_stop().equals("1")){//停牌
                    ToastUtils.toast(context, R.string.hk_order26);
                }else if(data.getIs_stop().equals("2")) {//未停牌
                    mFragment.onGetVoteSubLinkData(data);
                }
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(RequestHQ50000.ERROR_INFO));
            }
        }).request();
    }

    /**
     * 公司行为
     */
    public void requestActionCommit(String actionCode, String businessType,
                                    String stockCode, String entrustNum,String reportType) {
        loadingDialogUtil.showLoadingDialog(0);
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("stock_account", TradeLoginManager.sNormalUserInfo_hk.getStock_account());
        map.put("corpbehavior_code",actionCode); //公司行为代码
        map.put("business_type",businessType); //业务类型(见数据字典)
        map.put("report_type",reportType); //申报类型 0查询   1申报   2撤单
        map.put("stock_code",stockCode); //证券代码
        map.put("report_amount",entrustNum); //申报数量
        new HK301624(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                loadingDialogUtil.hideDialog();
                ToastUtils.toast(context, bundle.getString(HK301624.BUNDLE_KEY_RESULT));
                mFragment.clearAllData();//委托成功后清除数据
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                loadingDialogUtil.hideDialog();
                ToastUtils.toast(context, bundle.getString(HK301624.ERROR_INFO));
            }
        }).request();
    }
}
