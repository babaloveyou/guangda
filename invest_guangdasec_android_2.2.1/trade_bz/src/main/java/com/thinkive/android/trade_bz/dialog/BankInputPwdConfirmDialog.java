package com.thinkive.android.trade_bz.dialog;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.android.thinkive.framework.compatible.TKFragmentActivity;
import com.android.thinkive.framework.keyboard.BaseKeyboard;
import com.thinkive.android.trade_bz.keyboard.KeyboardManager;
import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.bll.BankInServicesImpl;
import com.thinkive.android.trade_bz.others.tools.TradeTools;
import com.thinkive.android.trade_bz.utils.ToastUtils;

/**
 * 银证转账查询余额时，输入银行密码
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/4/20
 */
public class BankInputPwdConfirmDialog extends AbsTradeDialog {

    private EditText mEdtInputPwd;
    private BankInServicesImpl mServices;
    private TKFragmentActivity mActivity;
    private String bankCode = "";
    private String moneyType = "";
    private KeyboardManager mKeyboardForNumIn;

    public BankInputPwdConfirmDialog(Context context, BankInServicesImpl services, TKFragmentActivity activity) {
        super(context);
        mServices = services;
        mActivity = activity;
        initDialogLayout();
        setLayout();
    }

    /**
     *初始化
     */
    public void initDialogLayout() {
        super.initDialogLayout();
        setTitleText(R.string.bank_dialog_title);
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_bank_input_pwd, null);
        mEdtInputPwd=(EditText)view.findViewById(R.id.edt_bank_input_pwd);
        mKeyboardForNumIn= TradeTools.initKeyBoard(mActivity, mEdtInputPwd, KeyboardManager.KEYBOARD_TYPE_RANDOM_DIGITAL, BaseKeyboard.THEME_LIGHT);
        setSubViewToParent(view);
    }

    /**
     * 供外界设置数据
     */
    public void setRequestData(String code, String type){
        bankCode = code;
        moneyType = type;
    }
    @Override
    void onClickCancel() {
        mKeyboardForNumIn.dismiss();
        dismiss();
    }

    @Override
    void onClickOk() {
        String pwd = mEdtInputPwd.getText().toString().trim();
        if(TextUtils.isEmpty(pwd)){
            ToastUtils.toast(mContext, mContext.getResources().getString(R.string.bank_dialog_title));
        }else{
            mServices.requestOtherMoney(bankCode,moneyType,pwd);
            mKeyboardForNumIn.dismiss();
            dismiss();
        }
    }
}
