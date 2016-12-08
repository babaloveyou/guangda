package com.thinkive.android.trade_bz.a_stock.bll;

import android.content.Context;
import android.os.Bundle;

import com.thinkive.android.trade_bz.a_stock.bean.OneKeyBean;
import com.thinkive.android.trade_bz.a_stock.fragment.OneKeyMoneyFragment;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.others.tools.EncryptManager;
import com.thinkive.android.trade_bz.others.tools.TradeLoginManager;
import com.thinkive.android.trade_bz.request.BaseRequest;
import com.thinkive.android.trade_bz.request.Request300207;
import com.thinkive.android.trade_bz.request.Request300208;
import com.thinkive.android.trade_bz.utils.LoadingDialogUtil;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *  一键归集--资金转账
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/8/12
 */

public class OneKeySelectServicesImpl {
    private OneKeyMoneyFragment mFragment = null;
    private EncryptManager mEncryptManager;
    private LoadingDialogUtil loadingDialogUtil;//请求数据状态框 工具类

    public OneKeySelectServicesImpl(OneKeyMoneyFragment fragment) {
        mFragment = fragment;
        mEncryptManager= EncryptManager.getInstance();
        loadingDialogUtil = new LoadingDialogUtil(fragment.getContext());
    }

    /**
     * 请求当前账户归集数据列表
     */
    public void requestOneKeyMessage() {
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
     * 请求资金转账
     */
    public void requestTransferMoney(String moneyType,String money,String outAccount,String inAccount) {
        loadingDialogUtil.showLoadingDialog(0);
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("out_password",mEncryptManager.getRsaEncryptStr(TradeLoginManager.sNormalUserInfo.getPassword()));
        map.put("money_type", moneyType); //主资金账号的币种类型
        map.put("tranamt",money);
        map.put("out_fundid",outAccount);
        map.put("in_fundid",inAccount);
        new Request300207(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                loadingDialogUtil.hideDialog();
                String string = bundle.getString(BaseRequest.ERROR_INFO);
                mFragment.getTransferMoneyResult(string);
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                loadingDialogUtil.hideDialog();
                String string = bundle.getString(BaseRequest.ERROR_INFO);
                mFragment.getTransferMoneyResult(string);
            }
        }).request();
    }
}
