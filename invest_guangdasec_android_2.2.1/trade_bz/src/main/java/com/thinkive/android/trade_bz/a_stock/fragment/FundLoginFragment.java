package com.thinkive.android.trade_bz.a_stock.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.thinkive.framework.compatible.ListenerController;
import com.android.thinkive.framework.config.ConfigManager;
import com.android.thinkive.framework.keyboard.BaseKeyboard;
import com.android.thinkive.framework.network.NetWorkService;
import com.android.thinkive.framework.util.CommonUtil;
import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.activity.TradeLoginActivity;
import com.thinkive.android.trade_bz.a_stock.bll.TradeLoginServiceImpl;
import com.thinkive.android.trade_bz.a_stock.controll.TradeLoginViewController;
import com.thinkive.android.trade_bz.keyboard.KeyboardManager;
import com.thinkive.android.trade_bz.others.constants.Constants;
import com.thinkive.android.trade_bz.others.tools.TradeFlags;
import com.thinkive.android.trade_bz.others.tools.TradeLoginManager;
import com.thinkive.android.trade_bz.others.tools.TradeTools;
import com.thinkive.android.trade_bz.receivers.TradeBaseBroadcastReceiver;
import com.thinkive.android.trade_bz.utils.PreferencesUtils;
import com.thinkive.android.trade_bz.utils.ToastUtil;
import com.thinkive.android.trade_bz.utils.ToastUtils;
import com.thinkive.android.trade_bz.utils.TradeUtils;
import com.thinkive.android.trade_bz.views.SqqSwitchButton;

import java.util.ArrayList;
import java.util.TimerTask;

/**
 * 交易单独登陆，不走统一登陆
 * 描述：交易登录界面碎片，本类仅限于登录使用
 *
 * @author 王志鸿
 * @version 1.0
 * @company 深圳市思迪信息科技有限公司
 * @date 2015/6/3
 */
public class FundLoginFragment extends AbsBaseFragment {
    private TradeLoginActivity mActivity;
    private TradeLoginViewController mController;
    private TradeLoginServiceImpl mService;
    /**
     * 密码输入键盘
     */
    private KeyboardManager mKeyboardManager;
    /**
     * 主布局
     */
    private View mView;
    /**
     * 用于输入账号信息
     */
    private EditText mInputAccountEdt;
    /**
     * 用于输入密码
     */
    private EditText mInputPasswordEdt;
    /**
     * 用于输入验证码
     */
    private EditText mInputVerifyCodeEdt;
    /**
     * 用于显示验证码图片
     */
    private ImageView mShowVerifyCodeIv;
    /**
     * 用于用户点击登录按钮
     */
    private Button mLoginBtn;
    /**
     * 用于请求验证码图片时的转转
     */
    private ProgressBar mLoadVerifyImagePb;

    private LinearLayout mParentLl;//探索出键盘时改变大小的父布局
    private TextView mFaqTv;// 弹出faq
    private TextView mOpenAccountTv;//开户
    private SqqSwitchButton mSwitchBtn;//保存账号的开关
    private View mDecorView;
    private FrameLayout.LayoutParams mLlParam;//弹出键盘时顶上去的父布局
    private boolean mKeyBoardVisible = false;//是否显示软键盘
    private InputMethodManager mInputManager;
    /**
     * 登录类型数据集
     */
    private ArrayList<String> mAccTypeTexts;
    /**
     * 登录前，用户单击的控件的ID
     */
    public int mClickIdBeforeLogin = -2;
    /**
     * 用户在进行手机验证步骤时使用的手机号
     */
    private String mPhoneNum = "";


    /**
     * 从行情模块的个股详情页面中，点击“买入”或“卖出”按钮后，从行情传来的Json字串。
     */
    public String mJsonDataFromHq = "";

    /**
     * 从缓存中取到的账号 num
     */
    private String mSaveAccount = "";
    private String mLoginType;
    private String mToH5Page;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_fund, null);
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        findViews(mView);
        initData();
        getNecessary();
        setListeners();
        initViews();
    }

    private void getNecessary() {
        mInputManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        mLlParam = (FrameLayout.LayoutParams) mParentLl.getLayoutParams();
    }

    @Override
    public void onResume() {
        super.onResume();
        //隐藏系统键盘
        TradeUtils.hideSystemKeyBoard(mActivity);
        mSwitchBtn.setToggleOn(false);
        mInputAccountEdt.setText("");
        if (TradeLoginManager.LOGIN_TYPE_NORMAL.equals(mLoginType)) {
            //是否记住账号
            if (PreferencesUtils.getBoolean(mActivity, Constants.IS_SAVE_NORMAL_ACCOUNT_KEY)) {
                mSwitchBtn.setToggleOn(true);
                TradeUtils.showKeyBoard(mActivity, mInputPasswordEdt, false);
                mInputAccountEdt.setText(PreferencesUtils.getString(mActivity, Constants.USER_NORMAL_ACCOUNT_KEY));
            } else {
                mSwitchBtn.setToggleOn(false);
                TradeUtils.showKeyBoard(mActivity, mInputAccountEdt, true);
            }
        }
        if (TradeLoginManager.LOGIN_TYPE_CREDIT.equals(mLoginType)) {
            //是否记住账号
            if (PreferencesUtils.getBoolean(mActivity, Constants.IS_SAVE_CREDIT_ACCOUNT_KEY)) {
                mSwitchBtn.setToggleOn(true);
                TradeUtils.showKeyBoard(mActivity, mInputPasswordEdt, false);
                mInputAccountEdt.setText(PreferencesUtils.getString(mActivity, Constants.USER_CREDIT_ACCOUNT_KEY));
            } else {
                mSwitchBtn.setToggleOn(false);
                TradeUtils.showKeyBoard(mActivity, mInputAccountEdt, true);
            }
            mSwitchBtn.setToggleOn(PreferencesUtils.getBoolean(mActivity, Constants.IS_SAVE_CREDIT_ACCOUNT_KEY));
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mKeyboardManager != null && mKeyboardManager.isShowing()) {
            mKeyboardManager.dismiss();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (TradeLoginManager.LOGIN_TYPE_NORMAL.equals(mLoginType)) {
            // 普通账户登录时
            if (PreferencesUtils.getBoolean(mActivity, Constants.IS_SAVE_NORMAL_ACCOUNT_KEY)) {
                PreferencesUtils.putString(mActivity, Constants.USER_NORMAL_ACCOUNT_KEY, getLoginAccount());
                PreferencesUtils.putString(mActivity, "acctType", TradeLoginManager.sNormalLoginType);
                if (TextUtils.isEmpty(mInputAccountEdt.getText())) {
                    mSwitchBtn.setToggleOn(false);
                    PreferencesUtils.putBoolean(mActivity, Constants.IS_SAVE_NORMAL_ACCOUNT_KEY, false);
                }
            } else {
                PreferencesUtils.putString(mActivity, Constants.USER_NORMAL_ACCOUNT_KEY, "");
            }
        }
        if (TradeLoginManager.LOGIN_TYPE_CREDIT.equals(mLoginType)) {
            // 信用账户登录时
            if (PreferencesUtils.getBoolean(mActivity, Constants.IS_SAVE_CREDIT_ACCOUNT_KEY)) {
                PreferencesUtils.putString(mActivity, Constants.USER_CREDIT_ACCOUNT_KEY, getLoginAccount());
                PreferencesUtils.putString(mActivity, "acctType", TradeLoginManager.sCreditLoginType);
                if (TextUtils.isEmpty(mInputAccountEdt.getText())) {
                    mSwitchBtn.setToggleOn(false);
                    PreferencesUtils.putBoolean(mActivity, Constants.IS_SAVE_CREDIT_ACCOUNT_KEY, false);
                }
            } else {
                PreferencesUtils.putString(mActivity, Constants.USER_CREDIT_ACCOUNT_KEY, "");
            }
        }
    }

    @Override
    protected void initData() {
        mActivity = (TradeLoginActivity) getActivity();
        Bundle bundle = getArguments();
        if (bundle != null) {
            mClickIdBeforeLogin = bundle.getInt(NormalTradeFragment.MainBroadcastReceiver.INTENT_KEY_CLICK_VIEW_ID);
            mJsonDataFromHq = bundle.getString("jsonDataFormHq");
            mLoginType = bundle.getString(Constants.LOGIN_TYPE);
            mToH5Page = bundle.getString(Constants.TOH5PAGE);
        }
        if (TradeLoginManager.LOGIN_TYPE_NORMAL.equals(mLoginType)) {

            mPhoneNum = PreferencesUtils.getString(mActivity, NormalTradeFragment.PREFERENCE_KEY_PHONE_NUMBER);
        }
        if (TradeLoginManager.LOGIN_TYPE_CREDIT.equals(mLoginType)) {
            mPhoneNum = PreferencesUtils.getString(mActivity, CreditTradeFragment.PREFERENCE_KEY_PHONE_NUMBER);
        }
        mController = new TradeLoginViewController(mActivity, TradeLoginManager.LOGIN_TYPE_NORMAL);
        mService = new TradeLoginServiceImpl(this, getArguments().getString(Constants.LOGIN_TYPE));
        mAccTypeTexts = new ArrayList<>();
        mKeyboardManager = TradeTools.initKeyBoard(mActivity, mInputPasswordEdt,
                KeyboardManager.KEYBOARD_TYPE_RANDOM_DIGITAL, BaseKeyboard.THEME_LIGHT);
    }

    @Override
    protected void findViews(View view) {
        mInputAccountEdt = (EditText) view.findViewById(R.id.tv_username);
        mInputPasswordEdt = (EditText) view.findViewById(R.id.et_password);
        mInputVerifyCodeEdt = (EditText) view.findViewById(R.id.et_verify);
        mShowVerifyCodeIv = (ImageView) view.findViewById(R.id.iv_show_security_code);
        mLoginBtn = (Button) view.findViewById(R.id.btn_login);
        mLoadVerifyImagePb = (ProgressBar) view.findViewById(R.id.pb_loadImage);
        mParentLl = (LinearLayout) view.findViewById(R.id.ll_parent);
        mFaqTv = (TextView) view.findViewById(R.id.tv_show_faq);
        mOpenAccountTv = (TextView) view.findViewById(R.id.tv_toopen_account);
        mSwitchBtn = (SqqSwitchButton) view.findViewById(R.id.btn_switch);
        mDecorView = getActivity().getWindow().getDecorView();
    }

    @Override
    protected void setListeners() {
        registerListener(ListenerController.ON_CLICK, mLoginBtn, mController);
        registerListener(ListenerController.ON_CLICK, mShowVerifyCodeIv, mController);
        registerListener(ListenerController.ON_TOUCH, mSwitchBtn, mController);
        registerListener(ListenerController.ON_CLICK, mFaqTv, mController);
        registerListener(ListenerController.ON_CLICK, mOpenAccountTv, mController);
        mSwitchBtn.setOnToggleChanged(new SqqSwitchButton.OnToggleChanged() {
            @Override
            public void onToggle(boolean on) {
                //保存账号生效需要在登录成功回调中实现
                if (!on) {
                    if (TradeLoginManager.LOGIN_TYPE_NORMAL.equals(mLoginType)) {
                        PreferencesUtils.putBoolean(mActivity, Constants.IS_SAVE_NORMAL_ACCOUNT_KEY, on);
                        PreferencesUtils.putString(mActivity, Constants.USER_NORMAL_ACCOUNT_KEY, "");
                    }
                    if (TradeLoginManager.LOGIN_TYPE_CREDIT.equals(mLoginType)) {
                        PreferencesUtils.putBoolean(mActivity, Constants.IS_SAVE_CREDIT_ACCOUNT_KEY, on);
                        PreferencesUtils.putString(mActivity, Constants.USER_CREDIT_ACCOUNT_KEY, "");
                    }
                }
            }
        });
        //输入验证码的时候讲布局顶上去看到登录按钮
        mShowVerifyCodeIv.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus && mKeyBoardVisible == true) {
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
                if (mKeyBoardVisible == false) {
                    //隐藏键盘的时候,讲margintop还原
                    resetMarginTop();
                } else {
                    if (mInputVerifyCodeEdt.hasFocus()) {
                        cutTopMargin();
                    }
                }
            }
        });
    }


    @Override
    protected void initViews() {
        setTheme();
        // 请求第一张验证码
        mService.requestVerifyImage();
        // 当普通账户登录时
        if (TradeLoginManager.LOGIN_TYPE_NORMAL.equals(mLoginType)) {
            mActivity.setTitleText(R.string.login_title_normal);
            mSaveAccount = PreferencesUtils.getString(getActivity(), Constants.USER_NORMAL_ACCOUNT_KEY, "");
            mInputAccountEdt.setText(mSaveAccount);
        }
        //信用登录时
        if (TradeLoginManager.LOGIN_TYPE_CREDIT.equals(mLoginType)) {
            mActivity.setTitleText(R.string.login_title_credit);
            mSaveAccount = PreferencesUtils.getString(getActivity(), Constants.USER_CREDIT_ACCOUNT_KEY, "");
            mInputAccountEdt.setText(mSaveAccount);
        }
    }

    @Override
    protected void setTheme() {

    }


    /**
     * 将请求下来的验证码显示出来
     *
     * @param bitmap 验证码位图
     */
    public void onGetVerifyCode(Bitmap bitmap) {
        if (bitmap != null) {
            mShowVerifyCodeIv.setImageBitmap(bitmap);
            setProgressVisibility(View.GONE);
        }
    }

    /**
     * 设置进度条是否显示
     *
     * @param visibility 是否可见值
     */
    public void setProgressVisibility(int visibility) {
        // 验证码图片的显示值，永远和进度条相反，进度条显示时它不显示，进度条不显示时它显示
        int verifyVisibility = View.GONE;
        // 进度条不显示时，验证码图片要显示
        if (visibility == View.GONE || visibility == View.INVISIBLE) {
            verifyVisibility = View.VISIBLE;
        }
        mLoadVerifyImagePb.setVisibility(visibility);
        mShowVerifyCodeIv.setVisibility(verifyVisibility);
    }

    /**
     * 登录按钮单击事件
     */
    public void onClickLogin() {
        // 获取用户的输入信息
        String account = getLoginAccount();
        String password = getLoginPassword();
        String securityCode = getSecurityCode();
        // 判断用户的输入信息是否合法
        if (TextUtils.isEmpty(account)) { // 用户名为空
            ToastUtils.toast(mActivity, getString(R.string.login_account_hint));
            resetAccountEdt();
        } else if (TextUtils.isEmpty(password)) { // 密码为空
            ToastUtils.toast(mActivity, getString(R.string.login_password_hint));
            resetPwdEdt();
        } else if (TextUtils.isEmpty(securityCode)) { // 验证码为空
            ToastUtils.toast(mActivity, getString(R.string.login_verify_code_hint));
            resetVerifyEdt();
        } else { // 一切输入正常时
            System.out.println("1:mService.requestLogin();");
            mService.requestLogin();
        }
    }

    /**
     * 验证码图片单击事件
     */
    public void onClickShowVerifyCode() {
        mService.requestVerifyImage();
    }


    /**
     * 开始登录请求时执行
     */
    public void onLoginStart() {
        mLoginBtn.setEnabled(false);
    }

    /**
     * 登录请求以失败而告终时执行
     */
    public void onLoginFailed() {
        mLoginBtn.setEnabled(true);
        mInputVerifyCodeEdt.setText("");
    }


    /**
     * 登录请求成功
     */
    public void onLoginSuccess(String account, String loginType) {
        System.out.println("5: fundLoginFragment回调方法onLoginSuccess()");
        Intent intent = new Intent();
        //普通账户登录
        switch (loginType) {
            case TradeLoginManager.LOGIN_TYPE_NORMAL:
                System.out.println("6:  case TradeLoginManager.LOGIN_TYPE_NORMAL");
                TradeFlags.addFlag(TradeFlags.FLAG_NORMAL_TRADE_YES);
                TradeLoginManager.sNormalLoginAccount = account;
                //普通登录
                PreferencesUtils.putString(mActivity, Constants.USER_NORMAL_ACCOUNT_KEY, getLoginAccount());
                PreferencesUtils.putBoolean(mActivity, Constants.IS_SAVE_NORMAL_ACCOUNT_KEY, mSwitchBtn.toggleOn());
                // 登录成功后，利用之前记录的登录入口（就是用户从哪个按钮触发的登录），通知主页做跳转
                intent.putExtra(NormalTradeFragment.MainBroadcastReceiver.INTENT_KEY_CLICK_VIEW_ID,
                        mClickIdBeforeLogin);
                if (mClickIdBeforeLogin == -1) {
                    intent.putExtra(NormalTradeFragment.MainBroadcastReceiver.INTENT_KEY_JSON_FORM_HQ,
                            mJsonDataFromHq);
                }
                //普通登录会话同步
                String url1 = "http://10.84.132.63:9999/servlet/json?funcNo=303028&entrust_way=SJWT&branch_no=" + TradeLoginManager.sNormalUserInfo.getBranch_no() + "&fund_account=" + TradeLoginManager.sNormalUserInfo.getFund_account() + "&cust_code=" + TradeLoginManager.sNormalUserInfo.getCust_code() + "&password=&sessionid=&jsessionid=&exchange_type=&op_station=" + TradeLoginManager.sNormalUserInfo.getOp_station();
                String cookie1 = NetWorkService.getInstance().getCookie(url1);
                System.out.println("普通登录的cookiel====" + cookie1);
                CommonUtil.syncWebviewCookies(getActivity(), ConfigManager.getInstance().getAddressConfigValue("NORMAL_NEWSTOCK_URL"), cookie1);
                break;
            case TradeLoginManager.LOGIN_TYPE_CREDIT:
                TradeFlags.addFlag(TradeFlags.FLAG_CREDIT_TRADE_YES);
                TradeLoginManager.sCreditLoginAccount = account;
                PreferencesUtils.putString(mActivity, Constants.USER_CREDIT_ACCOUNT_KEY, getLoginAccount());
                PreferencesUtils.putBoolean(mActivity, Constants.IS_SAVE_CREDIT_ACCOUNT_KEY, mSwitchBtn.toggleOn());
                // 登录成功后，利用之前记录的登录入口（就是用户从哪个按钮触发的登录），通知主页做跳转
                intent.putExtra(CreditTradeFragment.MainBroadcastReceiver.INTENT_KEY_CLICK_VIEW_ID,
                        mClickIdBeforeLogin);
                if (mClickIdBeforeLogin == -1) {
                    intent.putExtra(CreditTradeFragment.MainBroadcastReceiver.INTENT_KEY_JSON_FORM_HQ,
                            mJsonDataFromHq);
                }
                //信用登录会话
                String url2 = "http://10.84.132.63:9999/servlet/json?funcNo=303028&entrust_way=SJWT&branch_no=" + TradeLoginManager.sCreditUserInfo.getBranch_no() + "&fund_account=" + TradeLoginManager.sCreditUserInfo.getFund_account() + "&cust_code=" + TradeLoginManager.sCreditUserInfo.getCust_code() + "&password=&sessionid=&jsessionid=&exchange_type=&op_station=" + TradeLoginManager.sCreditUserInfo.getOp_station();
                String cookie2 = NetWorkService.getInstance().getCookie(url2);
                System.out.println("信用登录的cookie2====" + cookie2);
                CommonUtil.syncWebviewCookies(getActivity(),ConfigManager.getInstance().getAddressConfigValue("CREDIT_NEWSTOCK_URL"), cookie2);
                break;
            case TradeLoginManager.LOGIN_TYPE_OPTION:
                TradeFlags.addFlag(TradeFlags.FLAG_OPTION_TRADE_YES);
                TradeLoginManager.sOptionLoginAccount = account;
                break;
        }

        // 期望接收广播的代码在TradeMainFragment.java中
        System.out.println("7:   TradeBaseBroadcastReceiver.sendBroadcast(mActivity, intent, TradeBaseBroadcastReceiver.ACTION_START_ACTIVITY);");
        TradeBaseBroadcastReceiver.sendBroadcast(mActivity, intent, TradeBaseBroadcastReceiver.ACTION_START_ACTIVITY);
        Intent uumsIntent = new Intent();
        TradeBaseBroadcastReceiver.sendBroadcast(mActivity, uumsIntent, TradeBaseBroadcastReceiver.ACTION_TRADE_LOGIN_SUCCESS);

        if (mToH5Page != null) {
            Intent broadIntent = new Intent(Constants.ACTION_TO_H5_PAGE);
            broadIntent.putExtra(Constants.TOH5PAGE, mToH5Page);
            if (TradeLoginManager.LOGIN_TYPE_NORMAL.equals(mLoginType)) {
                broadIntent.putExtra(Constants.STOCK_CREDIT_FLAG, "stock_userInfo");
            }
            if (TradeLoginManager.LOGIN_TYPE_CREDIT.equals(mLoginType)) {
                broadIntent.putExtra(Constants.STOCK_CREDIT_FLAG, "credit_userInfo");
            }
            getContext().sendBroadcast(broadIntent);
            mToH5Page = null;
        }
        mActivity.finish();
    }


    /**
     * 获取输入框里的账号
     *
     * @return 账号字符串
     */
    public String getLoginAccount() {
        return mInputAccountEdt.getText().toString().trim();
    }

    /**
     * 获取输入框里的密码
     *
     * @return 密码字符串
     */
    public String getLoginPassword() {
        return mInputPasswordEdt.getText().toString().trim();
    }

    /**
     * 重置密码输入框，并让其获取焦点以方便用户输入
     */
    public void resetPwdEdt() {
        mInputPasswordEdt.setText("");
        TradeUtils.showKeyBoard(mActivity, mInputPasswordEdt, false);
    }

    /**
     * 重置密码输入框，并让其获取焦点以方便用户输入
     */
    public void resetAccountEdt() {
        mInputAccountEdt.setText("");
        TradeUtils.showKeyBoard(mActivity, mInputAccountEdt, true);
    }

    /**
     * 重置密码输入框，并让其获取焦点以方便用户输入
     */
    public void resetVerifyEdt() {
        mInputVerifyCodeEdt.setText("");
        TradeUtils.showKeyBoard(mActivity, mInputVerifyCodeEdt, true);
    }


    /**
     * 获取输入框里的验证码
     *
     * @return 验证码字符串
     */
    public String getSecurityCode() {
        return mInputVerifyCodeEdt.getText().toString().trim();
    }

    /**
     * 获取用户在进行手机验证时输入的手机号
     *
     * @return 用户在进行手机验证时输入的手机号
     */
    public String getPhoneNum() {
        return mPhoneNum;
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
            if (TextUtils.isEmpty(mInputAccountEdt.getText())) {
                mSwitchBtn.setToggleOn(false);
                if (TradeLoginManager.LOGIN_TYPE_NORMAL.equals(mLoginType)) {

                    PreferencesUtils.putBoolean(mActivity, Constants.IS_SAVE_NORMAL_ACCOUNT_KEY, false);
                }
                if (TradeLoginManager.LOGIN_TYPE_CREDIT.equals(mLoginType)) {
                    PreferencesUtils.putBoolean(mActivity, Constants.IS_SAVE_CREDIT_ACCOUNT_KEY, false);
                }
            }
        }
    }

    /*
    * 弹出键盘时削减布局marginTop显示登录按钮
    */
    private void cutTopMargin() {
        mLlParam.setMargins(0, (int) getResources().getDimension(R.dimen.negative_top_margin), 0, 0);
        mParentLl.setLayoutParams(mLlParam);
    }

    /*
    * 隐藏键盘时布局复位
    */
    private void resetMarginTop() {
        mLlParam.setMargins(0, 0, 0, 0);
        mParentLl.setLayoutParams(mLlParam);
    }

    //faq
    public void onClickShowFaq() {
        ToastUtil.showToast("faq");
    }

    public void onClickOpenAccount() {
        ToastUtil.showToast("开户");
    }

    public String getInputType() {
        return "0";
    }
}
