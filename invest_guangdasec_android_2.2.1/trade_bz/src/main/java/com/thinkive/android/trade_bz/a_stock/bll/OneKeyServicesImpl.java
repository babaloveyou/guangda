package com.thinkive.android.trade_bz.a_stock.bll;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;

import com.thinkive.android.trade_bz.a_stock.bean.OneKeyBean;
import com.thinkive.android.trade_bz.a_stock.fragment.OneKeyFragment;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.others.tools.EncryptManager;
import com.thinkive.android.trade_bz.request.BaseRequest;
import com.thinkive.android.trade_bz.request.Request300207;
import com.thinkive.android.trade_bz.request.Request300208;
import com.thinkive.android.trade_bz.utils.LoadingDialogUtil;
import com.thinkive.android.trade_bz.utils.ToastUtil;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 一键归集
 *
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/8/12
 */

public class OneKeyServicesImpl {
    private OneKeyFragment mFragment = null;
    private EncryptManager mEncryptManager;
    private LoadingDialogUtil loadingDialogUtil;//请求数据状态框 工具类

    public OneKeyServicesImpl(OneKeyFragment fragment) {
        mFragment = fragment;
        mEncryptManager = EncryptManager.getInstance();
        loadingDialogUtil = new LoadingDialogUtil(fragment.getContext());
    }

    /**
     * 请求当前账户归集数据列表
     */
    public void requestOneKeyMessage() {
        System.out.println("一键归集init300208");
        HashMap<String, String> map = new HashMap<String, String>();
        new Request300208(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                ArrayList<OneKeyBean> dataList = bundle.getParcelableArrayList(Request300208.BUNDLE_KEY_ONE_KEY_SELECT);
                mFragment.onGetMoneySelectList(dataList);
            }

            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(Request300208.ERROR_INFO));
            }
        }).request();
    }

    /**
     * 请求一键归集
     */
    public void requestOneKey(ArrayList<OneKeyBean> oneKeyBeans, String moneyType, String fundAccount) {
        String out_fundid = "";
        String in_fundid = "";
        String tranamt = "";
        for (OneKeyBean bean : oneKeyBeans) {
            if ("0".equals(bean.getFundseq())) {
                in_fundid = bean.getFundid();
            } else {
                out_fundid = out_fundid.concat(";" + bean.getFundid());
                tranamt = tranamt.concat(";" + bean.getFetch_balance());
            }
        }
            if (!TextUtils.isEmpty(tranamt)) {
                tranamt = tranamt.substring(1, tranamt.length());
            }
            if (!TextUtils.isEmpty(out_fundid)) {
                out_fundid = out_fundid.substring(1, out_fundid.length());
            }

            loadingDialogUtil.showLoadingDialog(0);
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("out_fundid", out_fundid);
            map.put("in_fundid", in_fundid);
            map.put("tranamt", tranamt);
            map.put("fund_account", fundAccount);
            map.put("money_type", moneyType);
            new Request300207(map, new IRequestAction() {
                @Override
                public void onSuccess(Context context, Bundle bundle) {
                    loadingDialogUtil.hideDialog();
                }

                @Override
                public void onFailed(Context context, Bundle bundle) {
                    loadingDialogUtil.hideDialog();
                    String string = bundle.getString(BaseRequest.ERROR_INFO);
                    ToastUtil.showToast(string);

                }
            }).request();
        }
}
