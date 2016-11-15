package com.thinkive.android.trade_bz.a_stock.bll;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;

import com.thinkive.android.trade_bz.a_stock.bean.RevocationBean;
import com.thinkive.android.trade_bz.a_stock.fragment.BottomTodayEntrustFragment;
import com.thinkive.android.trade_bz.a_stock.fragment.TodayEntrustFragment;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.others.constants.Constants;
import com.thinkive.android.trade_bz.request.Request301508;
import com.thinkive.android.trade_bz.request.Request306001;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;

import static com.thinkive.android.trade_bz.request.Request301508.BUNDLE_KEY_TOADY_ENTRUST;

/**
 * 今日委托的业务类
 * 与撤单共用实体类
 *
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/6/19
 */

public class TodayEntrustServicesImpl {
    private TodayEntrustFragment mTodayEntrustFragment = null;
    private BottomTodayEntrustFragment mBottomTodayEntrustFragment = null;
    private boolean isBottom = false;

    public TodayEntrustServicesImpl(TodayEntrustFragment mFragment) {
        mTodayEntrustFragment = mFragment;
    }

    public TodayEntrustServicesImpl(BottomTodayEntrustFragment mFragment) {
        mBottomTodayEntrustFragment = mFragment;
        isBottom = true;
    }

    public void requestTodayEntrust() {
        if (!TextUtils.isEmpty(Constants.TODAY_DATE)) {
            HashMap<String, String> map301508 = new HashMap<>();
            map301508.put("date", Constants.TODAY_DATE);
            new Request301508(map301508, new IRequestAction() {
                @Override
                public void onSuccess(Context context, Bundle bundle) {
                    ArrayList<RevocationBean> dataList = bundle.getParcelableArrayList(BUNDLE_KEY_TOADY_ENTRUST);
                    //保留小数点后两位
                    //                for (RevocationBean bean : dataList) {
                    //                    bean.setBusiness_price(TradeUtils.formatDouble2(bean.getBusiness_price()));
                    //                    bean.setEntrust_price(TradeUtils.formatDouble2(bean.getEntrust_price()));
                    //                }
                    //将数据传到TodayEntrustFragment
                    if (isBottom) {
                        mBottomTodayEntrustFragment.onGetTodayEntrustData(dataList);
                    } else {
                        mTodayEntrustFragment.onGetTodayEntrustData(dataList);
                    }
                }

                @Override
                public void onFailed(Context context, Bundle bundle) {
                    ToastUtils.toast(context, bundle.getString(Request301508.ERROR_INFO));
                }
            }).request();
        } else {
            //今日委托需传入data字段
            HashMap<String, String> map306001 = new HashMap<String, String>();
            new Request306001(map306001, new IRequestAction() {
                @Override
                public void onSuccess(Context context, Bundle bundle) {
                    String date = bundle.getString(Request306001.BUNDLE_KEY_306001);
                    HashMap<String, String> map301508 = new HashMap<>();
                    map301508.put("date", date);
                    new Request301508(map301508, new IRequestAction() {
                        @Override
                        public void onSuccess(Context context, Bundle bundle) {
                            ArrayList<RevocationBean> dataList = bundle.getParcelableArrayList(BUNDLE_KEY_TOADY_ENTRUST);
                            //保留小数点后两位
                            //                for (RevocationBean bean : dataList) {
                            //                    bean.setBusiness_price(TradeUtils.formatDouble2(bean.getBusiness_price()));
                            //                    bean.setEntrust_price(TradeUtils.formatDouble2(bean.getEntrust_price()));
                            //                }
                            //将数据传到TodayEntrustFragment
                            if (isBottom) {
                                mBottomTodayEntrustFragment.onGetTodayEntrustData(dataList);
                            } else {
                                mTodayEntrustFragment.onGetTodayEntrustData(dataList);
                            }
                        }

                        @Override
                        public void onFailed(Context context, Bundle bundle) {
                            ToastUtils.toast(context, bundle.getString(Request301508.ERROR_INFO));
                        }
                    }).request();

                }
                @Override
                public void onFailed(Context context, Bundle bundle) {
                    ToastUtils.toast(context, bundle.getString(Request306001.ERROR_INFO));
                }
            }).request();
        }


    }
}
