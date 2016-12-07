package com.thinkive.android.trade_bz.a_stock.bll;

import android.content.Context;
import android.os.Bundle;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.fragment.ChangePasswordFragment;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.others.tools.EncryptManager;
import com.thinkive.android.trade_bz.others.tools.TradeLoginManager;
import com.thinkive.android.trade_bz.request.Request300101;
import com.thinkive.android.trade_bz.request.Request303042;
import com.thinkive.android.trade_bz.utils.LoadingDialogUtil;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.util.HashMap;

/**
 *   修改密码
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/8/11
 */
public class ChangePassWordServicesImpl {

    /**
     * 外部调用环境
     */
    private ChangePasswordFragment mFragment;
    private String mUserType;
    private EncryptManager mEncryptManager;
    private LoadingDialogUtil loadingDialogUtil;//请求数据状态框 工具类

    public ChangePassWordServicesImpl(ChangePasswordFragment fragment,String userType) {
        mFragment = fragment;
        mUserType =userType;
        mEncryptManager=EncryptManager.getInstance();
        loadingDialogUtil = new LoadingDialogUtil(fragment.getContext());
    }


    /**
     * 请求修改密码
     * @param newPwd
     */
    public void requestChangePwd(String oldPwd,String newPwd) {
        loadingDialogUtil.showLoadingDialog(0);
        HashMap<String, String> paramMap = new HashMap<String, String>();
        if (mUserType == null || mUserType.equals(TradeLoginManager.LOGIN_TYPE_NORMAL)) {
            paramMap.put("passowrd_type", "1");
            paramMap.put("password_old",mEncryptManager.getRsaEncryptStr(oldPwd));
            paramMap.put("password_new", mEncryptManager.getRsaEncryptStr(newPwd));
            new Request300101(paramMap, new IRequestAction() {
                @Override
                public void onSuccess(Context context, Bundle bundle) {
                    loadingDialogUtil.hideDialog();
                    ToastUtils.toast(context, context.getResources().getString(R.string.change_pwd_ok));
                    mFragment.onGetChangePwdResult(mUserType);
                }
                @Override
                public void onFailed(Context context, Bundle bundle) {
                    loadingDialogUtil.hideDialog();
                    ToastUtils.toast(context, bundle.getString(Request300101.ERROR_INFO));
                    mFragment.onGetChangePwdResultError();
                }
            }).request();
        }else if (mUserType.equals(TradeLoginManager.LOGIN_TYPE_CREDIT)) {  //融资融券账户
            paramMap.put("passowrd_type", "1");
            paramMap.put("password_old",mEncryptManager.getRsaEncryptStr(oldPwd));
            paramMap.put("password_new", mEncryptManager.getRsaEncryptStr(newPwd));
            new Request303042(paramMap, new IRequestAction() {
                @Override
                public void onSuccess(Context context, Bundle bundle) {
                    loadingDialogUtil.hideDialog();
                    ToastUtils.toast(context, context.getResources().getString(R.string.change_pwd_ok));
                    mFragment.onGetChangePwdResult(mUserType);
                }
                @Override
                public void onFailed(Context context, Bundle bundle) {
                    loadingDialogUtil.hideDialog();
                    ToastUtils.toast(context, bundle.getString(Request303042.ERROR_INFO));
                    mFragment.onGetChangePwdResultError();
                }
            }).request();
        }else if (mUserType != null && mUserType.equals(TradeLoginManager.LOGIN_TYPE_OPTION)) {  //个股期权账户
                loadingDialogUtil.hideDialog();
//            paramMap.put("passowrd_type", "1");
//            paramMap.put("password_old",mEncryptManager.getRsaEncryptStr(oldPwd));
//            paramMap.put("password_new", mEncryptManager.getRsaEncryptStr(newPwd));
//            new Request303042(paramMap, new IRequestAction() {
//                @Override
//                public void onSuccess(Context context, Bundle bundle) {
//                    ToastUtils.toast(context, context.getResources().getString(R.string.change_pwd_ok));
//                    mFragment.onGetChangePwdResult(mUserType);
//                }
//                @Override
//                public void onFailed(Context context, Bundle bundle) {
//                    ToastUtils.toast(context, bundle.getString(Request303042.ERROR_INFO));
//                }
//            }).request();
        }
    }
}
