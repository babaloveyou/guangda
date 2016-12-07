package com.thinkive.android.trade_bz.a_stock.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.android.thinkive.framework.compatible.ListenerControllerAdapter;
import com.android.thinkive.framework.keyboard.BaseKeyboard;
import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.activity.ChangePasswordActivity;
import com.thinkive.android.trade_bz.a_stock.bll.ChangePassWordServicesImpl;
import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;
import com.thinkive.android.trade_bz.keyboard.KeyboardManager;
import com.thinkive.android.trade_bz.others.tools.TradeTools;
import com.thinkive.android.trade_bz.receivers.TradeBaseBroadcastReceiver;
import com.thinkive.android.trade_bz.utils.ToastUtils;
import com.thinkive.android.trade_bz.utils.TradeUtils;
import com.thinkive.android.trade_bz.views.ClearEditText;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 修改交易密码
 * <p/>
 * Announcements：
 *
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/7/24
 */

public class ChangePasswordFragment extends AbsBaseFragment {
    /**
     * 依赖的Activity
     */
    private ChangePasswordActivity mActivity;
    /**
     * 原密码
     */
    private ClearEditText mEdtOldPwd;
    /**
     * 新密码
     */
    private ClearEditText mEdtNewPwd;
    /**
     * 确认新密码
     */
    private ClearEditText mEdtSureNewPwd;
    /**
     * 确认按钮
     */
    private Button mBtnClickSure;
    /**
     * 控制修改密码类
     */
    private ChangePwdController mController;
    /**
     * 业务类
     */
    private ChangePassWordServicesImpl mServices;
    private KeyboardManager mKeyboardManagerOld;
    private KeyboardManager mKeyboardManagerNew;
    private KeyboardManager mKeyboardManagerSure;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_a_change_password, null);
        findViews(rootView);
        initData();
        setListeners();
        initViews();
        return rootView;
    }

    @Override
    public void closeFrameworkKeyBroad() {
        super.closeFrameworkKeyBroad();
        if (mKeyboardManagerOld.isShowing()) {
            mKeyboardManagerOld.dismiss();
        }
        if (mKeyboardManagerNew.isShowing()) {
            mKeyboardManagerNew.dismiss();
        }
        if (mKeyboardManagerSure.isShowing()) {
            mKeyboardManagerSure.dismiss();
        }
    }

    @Override
    protected void findViews(View view) {
        mEdtOldPwd = (ClearEditText) view.findViewById(R.id.edt_pwd_old);
        mEdtNewPwd = (ClearEditText) view.findViewById(R.id.edt_pwd_new);
        mEdtSureNewPwd = (ClearEditText) view.findViewById(R.id.edt_pwd_sure);
        mBtnClickSure = (Button) view.findViewById(R.id.btn_pwd_click);
    }

    @Override
    protected void setListeners() {
        registerListener(ListenerControllerAdapter.ON_CLICK, mBtnClickSure, mController);
        mEdtOldPwd.addTextChangedListener(new TextListener() {
            @Override
            void onTextChange() {
                if (!TextUtils.isEmpty(mEdtOldPwd.getText()) ) {
                    mEdtOldPwd.setClearIconVisible(true);
                    mEdtOldPwd.invalidate();
                } else {
                    mEdtOldPwd.setClearIconVisible(false);
                    mEdtOldPwd.invalidate();
                }
            }
        });
        mEdtNewPwd.addTextChangedListener(new TextListener() {
            @Override
            void onTextChange() {
                if (!TextUtils.isEmpty(mEdtNewPwd.getText()) ) {
                    mEdtNewPwd.setClearIconVisible(true);
                    mEdtNewPwd.invalidate();
                } else {
                    mEdtNewPwd.setClearIconVisible(false);
                    mEdtNewPwd.invalidate();
                }
            }
        });
        mEdtSureNewPwd.addTextChangedListener(new TextListener() {
            @Override
            void onTextChange() {
                if (!TextUtils.isEmpty(mEdtSureNewPwd.getText()) ) {
                    mEdtSureNewPwd.setClearIconVisible(true);
                    mEdtSureNewPwd.invalidate();
                } else {
                    mEdtSureNewPwd.setClearIconVisible(false);
                    mEdtSureNewPwd.invalidate();
                }
            }
        });
    }

    @Override
    protected void initData() {
        mActivity = (ChangePasswordActivity) getActivity();
        mController = new ChangePwdController(this);
        Bundle bundle=getArguments();
        mServices = new ChangePassWordServicesImpl(this,bundle.getString("userType"));
    }

    @Override
    protected void initViews() {
        //添加自绘键盘
        mKeyboardManagerOld = TradeTools.initKeyBoard(mActivity, mEdtOldPwd,KeyboardManager.KEYBOARD_TYPE_RANDOM_DIGITAL, BaseKeyboard.THEME_LIGHT);
        mKeyboardManagerNew = TradeTools.initKeyBoard(mActivity, mEdtNewPwd,KeyboardManager.KEYBOARD_TYPE_RANDOM_DIGITAL, BaseKeyboard.THEME_LIGHT);
        mKeyboardManagerSure = TradeTools.initKeyBoard(mActivity, mEdtSureNewPwd,KeyboardManager.KEYBOARD_TYPE_RANDOM_DIGITAL, BaseKeyboard.THEME_LIGHT);
        setTheme();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mKeyboardManagerOld.dismiss();
        mKeyboardManagerNew.dismiss();
        mKeyboardManagerSure.dismiss();
    }

    @Override
    protected void setTheme() {

    }
    /**
     * 点击确认按钮所执行的操作
     */
    public void onClickSure() {
        if (TradeUtils.isFastClick()) {
            return;
        }
        String oldPwd = mEdtOldPwd.getText().toString();
        String surePwd = mEdtSureNewPwd.getText().toString();
        String newPwd = mEdtNewPwd.getText().toString();
        if(TextUtils.isEmpty(oldPwd)){ //原密码不能为空
            ToastUtils.toast(mActivity,this.getString(R.string.change_pwd_old_hint));
            return;
        }
        if(TextUtils.isEmpty(newPwd)){ //新密码不能为空
            ToastUtils.toast(mActivity,this.getString(R.string.change_pwd_new_hint));
            return;
        }
        if(TextUtils.isEmpty(surePwd)){ //确认密码不能为空
            ToastUtils.toast(mActivity,this.getString(R.string.change_pwd_sure_hint));
            return;
        }
        if(!newPwd.equals(surePwd)){ // 新密码与确认密码不一致
            ToastUtils.toast(mActivity, this.getString(R.string.change_pwd_error));
            return;
        }
        if(newPwd.equals(oldPwd)){ //新密码和原密码不能相同
            ToastUtils.toast(mActivity, this.getString(R.string.change_pwd_error2));
            return;
        }
        mServices.requestChangePwd(oldPwd,newPwd);
    }

    /**
     * 修改密码成功执行
     */
    public void onGetChangePwdResult(String useType){
        // 给主页发广播，通知主页执行注销操作
        Intent intent = new Intent();
        intent.putExtra(NormalTradeFragment.MainBroadcastReceiver.CHANGE_PWD_TYPE_RESULT, useType);
        if (mKeyboardManagerOld.isShowing()) {
            mKeyboardManagerOld.dismiss();
        }
        if (mKeyboardManagerNew.isShowing()) {
            mKeyboardManagerNew.dismiss();
        }
        if (mKeyboardManagerSure.isShowing()) {
            mKeyboardManagerSure.dismiss();
        }
        TradeBaseBroadcastReceiver.sendBroadcast(mActivity,intent,TradeBaseBroadcastReceiver.ACTION_CHANGE_PWD_SUCCESS);
        mEdtOldPwd.setText("");
        mEdtSureNewPwd.setText("");
        mEdtNewPwd.setText("");
        mActivity.finish();
    }

    /**
     * 简单密码控制 -不能为递增或递减数字组合
     * @param str
     * @return
     */
    public static boolean test1(String str) {
        boolean flag = false;
        if (str.length() >= 3) {
            for (int i = 0; i < str.length() - 2; i++) {
                int num = Integer.parseInt(String.valueOf(str.charAt(i)));
                int num1 = Integer.parseInt(String.valueOf(str.charAt(i + 1)));
                int num2 = Integer.parseInt(String.valueOf(str.charAt(i + 2)));
                if ((num + 1) == num1 && (num + 2) == num2) {
                    flag = true;
                    break;
                }
                if ((num - 1) == num1 && (num - 2) == num2) {
                    flag = true;
                    break;
                }
            }
        }
        return flag;
    }

    /**
     * 简单密码控制 -重复数字超过3位
     * @param str
     * @return
     */
    public static boolean test2(String str) {
        boolean flag = false;
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            if (map.containsKey(String.valueOf(str.charAt(i)))) {
                Integer num = map.get(String.valueOf(str.charAt(i)));
                map.put(String.valueOf(str.charAt(i)), num + 1);
            } else {
                map.put(String.valueOf(str.charAt(i)), 1);
            }
        }
        Set<Map.Entry<String, Integer>> set = map.entrySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> entry = (Map.Entry<String, Integer>) iterator.next();
            Integer num = entry.getValue();
            if (num >= 3) {
                flag = true;
            }
        }
        return flag;
    }

    public void onGetChangePwdResultError() {
        mEdtNewPwd.setText("");
        mEdtOldPwd.setText("");
        mEdtSureNewPwd.setText("");
    }
}

abstract class TextListener implements TextWatcher {
    abstract void onTextChange();
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
/**
 * ChangePasswordFragment的控制类
 */
class ChangePwdController extends AbsBaseController implements View.OnClickListener {

    private ChangePasswordFragment mFragment;

    public ChangePwdController(ChangePasswordFragment Fragment) {
        mFragment = Fragment;
    }

    @Override
    public void register(int i, View view) {
        switch (i) {
            case ON_CLICK:
                view.setOnClickListener(this);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        int resId = v.getId();
        if (resId == R.id.btn_pwd_click) {
            mFragment.onClickSure();
        }
    }
}
