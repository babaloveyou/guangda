package com.thinkive.android.trade_bz.dialog;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_rr.bll.RTransMainServiceImpl;
import com.thinkive.android.trade_bz.utils.ToastUtils;

/**
 * 融资融券划转，未登录普通交易时
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/1/21
 */
public class RTransLoginNormalDialog extends AbsTradeDialog {

    private EditText mLoginPwd;
    private EditText mAccount;
    private RTransMainServiceImpl mServices;
    private Context mContext;

    public RTransLoginNormalDialog(Context context, RTransMainServiceImpl services) {
        super(context);
        mContext = context;
        mServices = services;
        initDialogLayout();
        setLayout();
    }

    /**
     *初始化
     */
    public void initDialogLayout() {
        super.initDialogLayout();
        setTitleText(R.string.r_property_list12);
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_r_normal_login, null);
        // 密码
        mLoginPwd = (EditText) view.findViewById(R.id.edt_r_trans_pwd);
        //账户
        mAccount = (EditText) view.findViewById(R.id.edt_r_trans_account);
        setSubViewToParent(view);
    }

    /**
     * 给控件设置数据
     */
    public void setDataToViews(String account) {
        mAccount.setText(account);
    }

    @Override
    void onClickCancel() {
        dismiss();
    }

    @Override
    void onClickOk() {
        String account = mAccount.getText().toString().trim();
        String pwd = mLoginPwd.getText().toString().trim();
        if(TextUtils.isEmpty(account)){
            ToastUtils.toast(mContext, R.string.login_account);
        }else if(TextUtils.isEmpty(pwd)){
            ToastUtils.toast(mContext, R.string.bank_money_pwd_hint);
        }else{
            mServices.requestNormalLogin(account,pwd);
            dismiss();
        }
    }
}
