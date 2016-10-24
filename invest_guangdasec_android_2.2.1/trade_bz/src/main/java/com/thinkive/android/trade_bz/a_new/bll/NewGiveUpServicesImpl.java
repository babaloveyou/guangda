package com.thinkive.android.trade_bz.a_new.bll;

import android.content.Context;
import android.os.Bundle;

import com.thinkive.android.trade_bz.a_new.bean.NewDistributeNumBean;
import com.thinkive.android.trade_bz.a_new.fragment.NewGiveUpFragment;
import com.thinkive.android.trade_bz.a_stock.bll.BasicServiceImpl;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.others.tools.TradeLoginManager;
import com.thinkive.android.trade_bz.request.NewStock301517;
import com.thinkive.android.trade_bz.request.NewStock303029;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * 放弃认购申报
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/6/22
 */

public class NewGiveUpServicesImpl extends BasicServiceImpl {
    private NewGiveUpFragment mFragment = null;
    /**
     * 用户类型
     */
    private String mUserType;

    public NewGiveUpServicesImpl(NewGiveUpFragment mFragment, String userType) {
        this.mFragment = mFragment;
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
    public void requestDisbute(String begin, String end) {
        HashMap<String, String> paramMap = new HashMap<String, String>();
        //普通账号
        if(mUserType != null && mUserType.equals(TradeLoginManager.LOGIN_TYPE_NORMAL)) {
            paramMap.put("begin_time", begin.replaceAll("-", ""));
            paramMap.put("end_time", end.replaceAll("-", ""));
            new NewStock301517(paramMap, new IRequestAction() {
                @Override
                public void onSuccess(Context context, Bundle bundle) {
                    ArrayList<NewDistributeNumBean> dataList = bundle.getParcelableArrayList(NewStock301517.BUNDLE_KEY_DISTRIBUTE_NUMBER);
//                    mFragment.getDistributeData(dataList);
                }
                @Override
                public void onFailed(Context context, Bundle bundle) {
                    ToastUtils.toast(context, bundle.getString(NewStock301517.ERROR_INFO));
                }
            }).request();
        //信用账号
        }else if(mUserType != null && mUserType.equals(TradeLoginManager.LOGIN_TYPE_CREDIT)){
            paramMap.put("begin_time", begin.replaceAll("-", ""));
            paramMap.put("end_time", end.replaceAll("-", ""));
            new NewStock303029(paramMap, new IRequestAction() {
                @Override
                public void onSuccess(Context context, Bundle bundle) {
                    ArrayList<NewDistributeNumBean> dataList = bundle.getParcelableArrayList(NewStock303029.BUNDLE_KEY_DISTRIBUTE_NUMBER);
//                    mFragment.getDistributeData(dataList);
                }
                @Override
                public void onFailed(Context context, Bundle bundle) {
                    ToastUtils.toast(context, bundle.getString(NewStock303029.ERROR_INFO));
                }
            }).request();
        }
    }
}


