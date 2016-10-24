package com.thinkive.android.trade_bz.a_stock.fragment;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.utils.ToastUtil;
import com.thinkive.android.trade_bz.views.SqqSwitchButton;

import java.util.TimerTask;

/**
 * Created by Administrator on 2016/10/9.
 */
public class SunEFragment extends Fragment implements View.OnClickListener {
    private View mView;
    private EditText mUNameTv;
    private EditText mPwdEt;
    private EditText mVerifyEt;
    private Button mLoginBtn;
    private TextView mFaqTv;
    private TextView mOpenAccountTv;
    private SqqSwitchButton mSwitchBtn;
    private LinearLayout mParentLl;
    private FrameLayout.LayoutParams mParamOfLl;
    private View mDecorView;
    private boolean mKeyBoardVisible=false;
    private InputMethodManager mInputManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_sune, container, false);
        }
        return mView;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        findViewById();
        initListener();
    }

    private void initListener() {
        mInputManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        mParamOfLl = (FrameLayout.LayoutParams) mParentLl.getLayoutParams();
        mLoginBtn.setOnClickListener(this);
        mFaqTv.setOnClickListener(this);
        mOpenAccountTv.setOnClickListener(this);
        mSwitchBtn.setToggleOn(false);
        mSwitchBtn.setOnToggleChanged(new SqqSwitchButton.OnToggleChanged() {

            @Override
            public void onToggle(boolean on) {
                ToastUtil.showToast("toggle is " + on);
            }
        });
        //输入验证码的时候讲布局顶上去看到登录按钮
        mVerifyEt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus&& mKeyBoardVisible==true) {
                    cutTopMargin();
                }
            }
        });
        //监听键盘隐藏状态
        mDecorView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            private TimerTask mTask;

            @Override
            public void onGlobalLayout() {
                Rect rect = new Rect();
                mDecorView.getWindowVisibleDisplayFrame(rect);
                int displayHight = rect.bottom - rect.top;
                int hight = mDecorView.getHeight();
                mKeyBoardVisible = (double) displayHight / hight < 0.8;
                //execute the task
                if (mKeyBoardVisible==false) {
                    //隐藏键盘的时候,讲margintop还原
                    resetMarginTop();
                } else {
                    if (mVerifyEt.hasFocus()) {
                        cutTopMargin();
                    }
                }
            }
        });
    }


    private void findViewById() {
        mParentLl = (LinearLayout) mView.findViewById(R.id.ll_parent);
        mUNameTv = (EditText) mView.findViewById(R.id.tv_username);
        mPwdEt = (EditText) mView.findViewById(R.id.et_password);
        mVerifyEt = (EditText) mView.findViewById(R.id.et_verify);
        mLoginBtn = (Button) mView.findViewById(R.id.btn_login);
        mFaqTv = (TextView) mView.findViewById(R.id.tv_show_faq);
        mOpenAccountTv = (TextView) mView.findViewById(R.id.tv_toopen_account);
        mSwitchBtn = (SqqSwitchButton) mView.findViewById(R.id.btn_switch);
        mDecorView = getActivity().getWindow().getDecorView();

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_login) {
            if (TextUtils.isEmpty(mUNameTv.getText())) {
                ToastUtil.showToast("请输入账号");
            } else if (TextUtils.isEmpty(mPwdEt.getText())) {
                ToastUtil.showToast("密码不能为空");
            } else if (TextUtils.isEmpty(mVerifyEt.getText())) {
                ToastUtil.showToast("请输入验证码");
            } else {
                ToastUtil.showToast("登录成功");
                //// TODO: 2016/10/9
            }
        }
        if (v.getId() == R.id.tv_show_faq) {

            ToastUtil.showToast("afq");
        }
        if (v.getId() == R.id.tv_toopen_account) {

            ToastUtil.showToast("afq");
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            super.onStop();
            resetMarginTop();
            if (mInputManager.isActive()) {
                mInputManager.hideSoftInputFromWindow(mDecorView.getWindowToken(), 0);
            }
        }
    }

    private void cutTopMargin() {
        mParamOfLl.setMargins(0, (int) getResources().getDimension(R.dimen.negative_top_margin), 0, 0);
        mParentLl.setLayoutParams(mParamOfLl);
    }

    private void resetMarginTop() {
        mParamOfLl.setMargins(0, 0, 0, 0);
        mParentLl.setLayoutParams(mParamOfLl);
    }
}
