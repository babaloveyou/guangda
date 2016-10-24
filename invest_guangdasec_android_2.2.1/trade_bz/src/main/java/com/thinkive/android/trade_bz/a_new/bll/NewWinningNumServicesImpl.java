package com.thinkive.android.trade_bz.a_new.bll;

import android.content.Context;
import android.os.Bundle;

import com.thinkive.android.trade_bz.a_new.bean.NewNumberWinningBean;
import com.thinkive.android.trade_bz.a_new.fragment.NumberWinningFragment;
import com.thinkive.android.trade_bz.a_stock.bll.BasicServiceImpl;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.others.tools.TradeLoginManager;
import com.thinkive.android.trade_bz.request.NewStock301518;
import com.thinkive.android.trade_bz.request.NewStock301522;
import com.thinkive.android.trade_bz.request.NewStock303030;
import com.thinkive.android.trade_bz.request.NewStock303070;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 中签的业务类
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/7/2
 *
 */

public class NewWinningNumServicesImpl extends BasicServiceImpl {
    private NumberWinningFragment mFragment = null;
    /**
     * 用户类型
     */
    private String mUserType;
    private String mBegin;
    private String mEnd;

    public NewWinningNumServicesImpl(NumberWinningFragment fragment, String userType) {
        this.mFragment = fragment;
        mUserType=userType;
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onStop() {

    }

    /**
     * 请求数据
     */
    public void requestWinnigNum(String begin, String end) {
        mBegin = begin;
        mEnd = end;
        HashMap<String, String> map = new HashMap<String, String>();
        //普通账号
        if(mUserType != null && mUserType.equals(TradeLoginManager.LOGIN_TYPE_NORMAL)) {
            map.put("begin_date", begin.replaceAll("-", ""));
            map.put("end_date", end.replaceAll("-", ""));
            new NewStock301518(map, new IRequestAction() {
                @Override
                public void onSuccess(Context context, Bundle bundle) {
                    ArrayList<NewNumberWinningBean> dataList = bundle.getParcelableArrayList(NewStock301518.BUNDLE_KEY_WINNING_NUMBER);
                    mFragment.getWiningNumData(dataList);
                }
                @Override
                public void onFailed(Context context, Bundle bundle) {
                    ToastUtils.toast(context, bundle.getString(NewStock301518.ERROR_INFO));
                }
            }).request();
         //信用账号
        }else if(mUserType != null && mUserType.equals(TradeLoginManager.LOGIN_TYPE_CREDIT)){
            map.put("begin_date", begin.replaceAll("-", ""));
            map.put("end_date", end.replaceAll("-", ""));
            new NewStock303030(map, new IRequestAction() {
                @Override
                public void onSuccess(Context context, Bundle bundle) {
                    ArrayList<NewNumberWinningBean> dataList = bundle.getParcelableArrayList(NewStock303030.BUNDLE_KEY_WINNING_NUMBER);
                    mFragment.getWiningNumData(dataList);
                }
                @Override
                public void onFailed(Context context, Bundle bundle) {
                    ToastUtils.toast(context, bundle.getString(NewStock303030.ERROR_INFO));
                }
            }).request();
        }
    }

    /**
     * 中签缴款优先级别设置
     */
    public void execWiningSet(String exchangType, String stockAccount, String sort, String stockCode) {
        HashMap<String, String> map = new HashMap<String, String>();
        //普通账号
        if (mUserType != null && mUserType.equals(TradeLoginManager.LOGIN_TYPE_NORMAL)) {
            map.put("exchange_type",exchangType);
            map.put("stock_account",stockAccount);
            map.put("giveupqty",sort);
            map.put("stock_code",stockCode);
            new NewStock301522(map, new IRequestAction() {
                @Override
                public void onSuccess(Context context, Bundle bundle) {
                    //显示撤单结果
                    ToastUtils.toast(context, bundle.getString(NewStock301522.BUNDLE_KEY_WINING));
                    requestWinnigNum(mBegin,mEnd);
                }
                @Override
                public void onFailed(Context context, Bundle bundle) {
                    ToastUtils.toast(context, bundle.getString(NewStock301522.ERROR_INFO));
                }
            }).request();
        //信用账号
        }else if(mUserType != null && mUserType.equals(TradeLoginManager.LOGIN_TYPE_CREDIT)){
            map.put("exchange_type",exchangType);
            map.put("stock_account",stockAccount);
            map.put("giveupqty",sort);
            map.put("stock_code",stockCode);
            new NewStock303070(map, new IRequestAction() {
                @Override
                public void onSuccess(Context context, Bundle bundle) {
                    //显示撤单结果
                    ToastUtils.toast(context, bundle.getString(NewStock303070.BUNDLE_KEY_WINING));
                    requestWinnigNum(mBegin,mEnd);
                }
                @Override
                public void onFailed(Context context, Bundle bundle) {
                    ToastUtils.toast(context, bundle.getString(NewStock303070.ERROR_INFO));
                }
            }).request();
        }
    }
}


