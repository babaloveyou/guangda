package com.thinkive.android.trade_bz.a_option.bll;

import android.content.Context;
import android.os.Bundle;

import com.thinkive.android.trade_bz.a_option.bean.OptionRevocationBean;
import com.thinkive.android.trade_bz.a_option.bean.OptionRevocationResultBean;
import com.thinkive.android.trade_bz.a_option.fragment.OptionRevocationFragment;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.request.Option305006;
import com.thinkive.android.trade_bz.request.Option305021;
import com.thinkive.android.trade_bz.utils.LoadingDialogUtil;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 个股期权--撤单
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/6/15
 */

public class OptionRevocationServicesImpl {
    private OptionRevocationFragment mFragment =null;
    private LoadingDialogUtil loadingDialogUtil;//请求数据状态框 工具类

    public OptionRevocationServicesImpl(OptionRevocationFragment mFragment) {
        this.mFragment =mFragment;
        loadingDialogUtil = new LoadingDialogUtil(mFragment.getContext());
    }

    /**
     * 请求撤单列表
     */
    public void requestRevocationList(){
        HashMap<String, String> map = new HashMap<String, String>();
        new Option305021(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                ArrayList<OptionRevocationBean> dataList = bundle.getParcelableArrayList(Option305021.BUNDLE_KEY_OPTION_REVOCATION);
                //保留小数点后两位
                for (OptionRevocationBean bean : dataList) {
//                    bean.setBusiness_price(TradeUtils.formatDouble2(bean.getBusiness_price()));
//                    bean.setEntrust_price(TradeUtils.formatDouble2(bean.getEntrust_price()));
                }
                mFragment.onGetRevocationData(dataList);
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(Option305021.ERROR_INFO));
            }
        }).request();
    }

    /**
     * 撤单
     */
    public void execRevocation(String entrustNum, String exchange_type){
        loadingDialogUtil.showLoadingDialog(0);
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("entrust_no",entrustNum);
        map.put("exchange_type",exchange_type);
        new Option305006(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                loadingDialogUtil.hideDialog();
                OptionRevocationResultBean data = (OptionRevocationResultBean)bundle.getSerializable(Option305006.BUNDLE_KEY_REVOCATION_RESULT);
                mFragment.onGetRevocationResult(data);
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                loadingDialogUtil.hideDialog();
                ToastUtils.toast(context, bundle.getString(Option305006.ERROR_INFO));
            }
        }).request();
    }
}
