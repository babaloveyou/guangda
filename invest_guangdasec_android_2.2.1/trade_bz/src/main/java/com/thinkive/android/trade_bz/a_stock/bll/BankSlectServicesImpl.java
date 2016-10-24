package com.thinkive.android.trade_bz.a_stock.bll;

import android.content.Context;
import android.os.Bundle;

import com.thinkive.android.trade_bz.a_stock.bean.BankTransferSelectBean;
import com.thinkive.android.trade_bz.a_stock.fragment.TransferBankSelectFragment;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.others.tools.TradeLoginManager;
import com.thinkive.android.trade_bz.request.Option305027;
import com.thinkive.android.trade_bz.request.Request300203;
import com.thinkive.android.trade_bz.request.Request303040;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *  银证转账--流水--转账查询
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/8/10
 */

public class BankSlectServicesImpl {
    private TransferBankSelectFragment mFragment = null;
    private String mUserType;
    public BankSlectServicesImpl(TransferBankSelectFragment fragment, String userType) {
        mFragment = fragment;
        mUserType = userType;
    }

    /**
     * 请求转账列表数据
     */
    public void requestTransferSelect(String begin,String end) {
        HashMap<String, String> map = new HashMap<String, String>();
        if (mUserType != null && mUserType.equals(TradeLoginManager.LOGIN_TYPE_NORMAL)) {  //普通账户
            map.put("begin_time", begin);
            map.put("end_time", end);
            new Request300203(map, new IRequestAction() {
                @Override
                public void onSuccess(Context context, Bundle bundle) {
                    ArrayList<BankTransferSelectBean> dataList = bundle.getParcelableArrayList(Request300203.BUNDLE_KEY_BANK_SELECT);
                    mFragment.onGetTransferList(dataList);
                }
                @Override
                public void onFailed(Context context, Bundle bundle) {
                    ToastUtils.toast(context, bundle.getString(Request300203.ERROR_INFO));
                }
            }).request();
        }else if (mUserType != null && mUserType.equals(TradeLoginManager.LOGIN_TYPE_CREDIT)) {  //融资融券账户
            map.put("begin_time", begin);
            map.put("end_time", end);
            new Request303040(map, new IRequestAction() {
                @Override
                public void onSuccess(Context context, Bundle bundle) {
                    ArrayList<BankTransferSelectBean> dataList = bundle.getParcelableArrayList(Request303040.BUNDLE_KEY_BANK_R_SELECT);
                    mFragment.onGetTransferList(dataList);
                }
                @Override
                public void onFailed(Context context, Bundle bundle) {
                    ToastUtils.toast(context, bundle.getString(Request303040.ERROR_INFO));
                }
            }).request();
        } else if (mUserType != null && mUserType.equals(TradeLoginManager.LOGIN_TYPE_OPTION)) {  //个股期权账户
            map.put("begin_time", begin);
            map.put("end_time", end);
            new Option305027(map, new IRequestAction() {
                @Override
                public void onSuccess(Context context, Bundle bundle) {
                    ArrayList<BankTransferSelectBean> dataList = bundle.getParcelableArrayList(Option305027.BUNDLE_KEY_BANK_R_SELECT);
                    mFragment.onGetTransferList(dataList);
                }
                @Override
                public void onFailed(Context context, Bundle bundle) {
                    ToastUtils.toast(context, bundle.getString(Option305027.ERROR_INFO));
                }
            }).request();
        }
    }
}
