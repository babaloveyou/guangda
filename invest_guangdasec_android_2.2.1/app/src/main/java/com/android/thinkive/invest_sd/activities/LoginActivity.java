package com.android.thinkive.invest_sd.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.inputmethodservice.Keyboard;
import android.os.Bundle;
import android.text.*;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.widget.*;

import com.android.thinkive.framework.CoreApplication;
import com.android.thinkive.framework.compatible.ListenerController;
import com.android.thinkive.framework.compatible.TKActivity;
import com.android.thinkive.framework.config.ConfigManager;
import com.android.thinkive.framework.keyboard.KeyboardManager;
import com.android.thinkive.framework.network.NetWorkService;
import com.android.thinkive.framework.network.RequestBean;
import com.android.thinkive.framework.network.ResponseListener;
import com.android.thinkive.framework.storage.MemoryStorage;
import com.android.thinkive.framework.util.DeviceUtil;
import com.android.thinkive.framework.util.JsonParseUtil;
import com.android.thinkive.framework.util.PreferencesUtil;
import com.android.thinkive.invest_sd.R;
import com.android.thinkive.invest_sd.beans.AccountBean;
import com.android.thinkive.invest_sd.beans.LoginTokenBean;
import com.android.thinkive.invest_sd.constants.Constant;
import com.android.thinkive.invest_sd.jsonbean.BaseJsonbean;
import com.android.thinkive.invest_sd.util.AppUtil;
import com.android.thinkive.invest_sd.util.BitmapCache;
import com.android.thinkive.invest_sd.widget.LoadingDialog;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.thinkive.aqf.info.utils.ToastUtils;
import com.umeng.analytics.MobclickAgent;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.regex.Pattern;


/**
 * <p>
 * 描述：用于用户登录界面
 * </p>
 *
 * @author 黎丝军
 * @version 1.0
 * @corporation 深圳市思迪信息科技有限公司
 * @date 2015/4/7
 */
public class LoginActivity extends TKActivity{
    /**
     * 资金登录
     */
    public static final int FUND_LOGIN_TYPE = 1;
    /**
     * e账通登录
     */
    public static final int E_LOGIN_TYPE = 2;
    /**
     * 返回按钮
     */
    private ImageView mLoginBackIv = null;
    /**
     * 用于选择登录方式
     */
    private RadioGroup mSelectLoginRdg = null;
    /**
     * 用于选择登录方式
     */
    private CheckBox mCheckrembpassword = null;
    /**
     * 用于选择E账通时的提示
     */
    private TextView mLoginEHintTv = null;
    /**
     * 用于用于输入登录账号
     */
    private EditText mLoginAccountEdt = null;
    /**
     * 用于输入用户的登录密码
     */
    private EditText mLoginPasswordEdt = null;
    /**
     * 用于输入验证码
     */
    private EditText mVerifyCodeEdt = null;
    /**
     * 用于显示验证码图片
     */
    private ImageView mShowVerifyCodeIv = null;
    /**
     * 用于用户点击登录
     */
    private Button mLoginBtn = null;
    /**
     * 用于用户点击开户或转户
     */
    private View mOpenAccountBtn = null;
    /**
     * 用于加载图片的等待进度
     */
    private ProgressBar mLoadImagePgb;

    /**
     * 会话超时标志，当h5会话超时进入登录界面，点击back，需要跳转到主页；
     */
    private boolean isSessionTimeOut = false;
    private KeyboardManager mKeyboard;

    private Button btn_normal_login;
    private Button btn_yzyq_login;
    private int mLoinType = LoginActivity.FUND_LOGIN_TYPE;
    public static final int LOGIN_TYPE_ACCOUNT = 0;
    public static final int LOGIN_TYPE_E_ACCOUNT = 1;
    private String userName;
    public static final String USER_NAME = "user_name";
    public static final String BRANCH_NAME = "branch_name";
    public static final String FUND_ACCOUNT = "fund_account";
    public static final String ORG_NAME = "org_name";
    public static final String LOAN_ACCOUNT = "loan_account";
    public static final String OTC_ACCOUNT = "otc_account";
    public static final String ACCOUNT_LIST= "accountList";
    public static final String CLIENT_NO = "client_no";
    public static final int REQUEST_ACTIVITE = 10010;
    private LoadingDialog loadingDialog;
    private  TextView tv_version;
    /**
     * 用于保存检验随机数
     */
    private String mMobileKey = null;

    private TextView tv_hint_account;

    private TextView tv_tradePassword;

    private View but_clear_account;

    private View but_clear_pawss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkActivationState();
        setContentView(R.layout.activity_login);
        findViews();
        initViews();
        initData();
        setListeners();
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if(mKeyboard!=null) {
            mKeyboard.show();
        }

    }

    /**
     * 检测激活状态，没有激活剂就跳转到激活界面
     */
    private void checkActivationState() {
        try {
            String phone =  PreferencesUtil.getString(LoginActivity.this, Constant.MOBILE_NUMBER);
            if(TextUtils.isEmpty(phone)){
                Intent intent = new Intent(this,ActivateActivity.class);
                startActivityForResult(intent,REQUEST_ACTIVITE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void findViews() {
        mSelectLoginRdg = (RadioGroup) findViewById(R.id.rdg_selectLogin);
        mLoginAccountEdt = (EditText) findViewById(R.id.edt_login_account);
        mLoginPasswordEdt = (EditText) findViewById(R.id.edt_trade_password);
        mVerifyCodeEdt = (EditText) findViewById(R.id.edt_verifyCode);
        mShowVerifyCodeIv = (ImageView) findViewById(R.id.iv_verifyCodeIcon);
        mLoginBtn = (Button) findViewById(R.id.btn_login);
        mOpenAccountBtn =  findViewById(R.id.btn_openAccount);
        mLoginBackIv = (ImageView)findViewById(R.id.iv_login_back);
        mLoginEHintTv = (TextView) findViewById(R.id.tv_loginEHint);
        mLoadImagePgb = (ProgressBar) findViewById(R.id.pb_loadImage);
        mCheckrembpassword = (CheckBox) findViewById(R.id.cb_remember_password);
        btn_normal_login = (Button) findViewById(R.id.btn_normal_login);
        btn_yzyq_login = (Button) findViewById(R.id.btn_yzyq_login);

        tv_hint_account = (TextView) findViewById(R.id.tv_hint_account);
        tv_tradePassword = (TextView) findViewById(R.id.tv_tradePassword);
        but_clear_account =  findViewById(R.id.but_clear_account);
        but_clear_pawss =  findViewById(R.id.but_clear_pawss);
        findViewById(R.id.cant_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,CommWebActivity.class);
                intent.putExtra("url","http://116.236.247.175:84/m/commen_problem/index.html");
                intent.putExtra("title","常见问题");
                intent.putExtra("statusColor","#4359aa");
                startActivity(intent);
            }
        });
    }

    @Override
    protected void setListeners() {
        but_clear_pawss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLoginPasswordEdt.setText("");
            }
        });

        but_clear_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLoginAccountEdt.setText("");
            }
        });
        mLoginAccountEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(mLoginAccountEdt!=null&&mLoginAccountEdt.getText()!=null&&mLoginAccountEdt.getText().toString().length()>0){
                    but_clear_account.setVisibility(View.VISIBLE);
                }else{
                    but_clear_account.setVisibility(View.GONE);
                }
            }
        });


        mLoginPasswordEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(mLoginPasswordEdt!=null&&mLoginPasswordEdt.getText()!=null&&mLoginPasswordEdt.getText().toString().length()>0){
                    but_clear_pawss.setVisibility(View.VISIBLE);
                }else{
                    but_clear_pawss.setVisibility(View.GONE);
                }
            }
        });



        mSelectLoginRdg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    //资金登录
                    case R.id.rBtn_fund_login:
                        mLoinType = LoginActivity.FUND_LOGIN_TYPE;
                        setLoginAccountType(View.GONE, LOGIN_TYPE_ACCOUNT);
                        break;
                    //阳光E账通登录
                    case R.id.rBtn_sunshine_login:
                        mLoinType = LoginActivity.E_LOGIN_TYPE;
                        setLoginAccountType(View.VISIBLE, LOGIN_TYPE_E_ACCOUNT);
                    default:
                        break;
                }
            }
        });
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startRequestLogin(mLoinType);
            }
        });
        btn_normal_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.Toast(LoginActivity.this, "功能暂未开放");
//                jumpNormalTrade();
            }
        });
        btn_yzyq_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.Toast(LoginActivity.this, "功能暂未开放");
//                jumpRzrqTrade();
            }
        });
        mLoginBackIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //发广播通知2级界面去刷新
                Intent it = new Intent(Constant.ACTION_LOGINEND);
                it.putExtra("islogin", false);
                sendBroadcast(it);
                finish();
            }
        });
        mShowVerifyCodeIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestVerify();
            }
        });
        mOpenAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String url =  "m/open/index.html";
//                AppUtil.startModel("open-account",url,LoginActivity.this);
                Intent intent = new Intent(LoginActivity.this,OpenAccountHomeActivity.class);
                startActivity(intent);
                Intent it = new Intent(Constant.ACTION_LOGINEND);
                it.putExtra("islogin", false);
                sendBroadcast(it);
                finish();
            }
        });
        mLoginAccountEdt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus||!mKeyboard.isShowing()){
                    if(!LoginActivity.this.isFinishing() && mLoginAccountEdt.getWindowToken() != null) {
                        hideSystemKeyboard(mLoginAccountEdt,KeyboardManager.KEYBOARD_TYPE_COMMON);
                        mKeyboard.show();
                    }

                }
            }
        });

        mLoginPasswordEdt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    if(!LoginActivity.this.isFinishing() &&  mLoginPasswordEdt.getWindowToken() !=null ) {
                        hideSystemKeyboard(mLoginPasswordEdt,KeyboardManager.KEYBOARD_TYPE_RANDOM_COMMON );
                        mKeyboard.show();
                    }

                }
            }
        });

        mVerifyCodeEdt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    if(!LoginActivity.this.isFinishing() &&  mVerifyCodeEdt.getWindowToken() !=null ) {
                        hideSystemKeyboard(mVerifyCodeEdt,KeyboardManager.KEYBOARD_TYPE_COMMON );
                        mKeyboard.show();
                    }

                }
            }
        });
        mLoginAccountEdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.isFocusable()&&!mKeyboard.isShowing()){
                    if(!LoginActivity.this.isFinishing() &&  mLoginAccountEdt.getWindowToken() !=null ) {
                        hideSystemKeyboard(mLoginAccountEdt,KeyboardManager.KEYBOARD_TYPE_COMMON );
                        mKeyboard.show();
                    }
                }
            }
        });
        mLoginPasswordEdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.isFocusable()&&!mKeyboard.isShowing()){
                    if(!LoginActivity.this.isFinishing() &&  mLoginPasswordEdt.getWindowToken() !=null ) {
                        hideSystemKeyboard(mLoginPasswordEdt,KeyboardManager.KEYBOARD_TYPE_RANDOM_COMMON );
                        mKeyboard.show();
                    }
                }
            }
        });
        mVerifyCodeEdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.isFocusable()&&!mKeyboard.isShowing()){
                    if(!LoginActivity.this.isFinishing() &&  mVerifyCodeEdt.getWindowToken() !=null ) {
                        hideSystemKeyboard(mVerifyCodeEdt,KeyboardManager.KEYBOARD_TYPE_COMMON );
                        mKeyboard.show();
                    }
                }
            }
        });
//        mVerifyCodeEdt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if (hasFocus) {
//                    //初始化键盘
//                    if (mKeyboard != null && mKeyboard.isShowing()) {
//                        mKeyboard.dismiss();
//                        mKeyboard = null;
//                    }
//                } else {
//                    dismissSystemKeyBoard(mVerifyCodeEdt);
//                }
//            }
//        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
    public void jumpNormalTrade(){
        JSONObject data = new JSONObject();
        try {
            data.put("loginType",0);
        } catch (JSONException e) {
            e.printStackTrace();
        }
//        saveDataToCache(Constant.LOGIN_STATE,"2");
//        AppTool.startModel(getIAppControl(), Constant.MODEL_TRADE_MALL);
//        AppMsg msg = new AppMsg(Constant.MODEL_TRADE_MALL, "", "60252", data);
//        IAppControl appControl = getIAppControl();
//        appControl.sendMessage(msg);
        finish();
    }

    public void jumpRzrqTrade(){
        JSONObject data = new JSONObject();
        try {
            data.put("loginType",1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
//        saveDataToCache(Constant.LOGIN_STATE,"2");
//        AppTool.startModel(getIAppControl(), Constant.MODEL_TRADE_MALL);
//        AppMsg msg = new AppMsg(Constant.MODEL_TRADE_MALL, "", "60252", data);
//        IAppControl appControl = getIAppControl();
//        appControl.sendMessage(msg);
        finish();
    }
    @Override
    protected void initData() {
//        mLoginAccountEdt.setText("");
//        mLoginPasswordEdt.setText("");
        isSessionTimeOut = getIntent().getBooleanExtra("session_timeout",false);
        requestVerify();
    }




    @Override
    protected void initViews() {
//        KeyboardManager.setTheme(KeyboardManager.KEYBOARD_TYPE_RANDOM_COMMON);
        loadingDialog = new LoadingDialog(this);
        hideSystemKeyboard(mLoginAccountEdt, KeyboardManager.KEYBOARD_TYPE_COMMON);
        mLoginAccountEdt.setHint(R.string.account_hint);
        String username =  PreferencesUtil.getString(this, Constant.REMBER_USERNAME, "");
        if(!TextUtils.isEmpty(username)){
            mLoginAccountEdt.setText(username);
            mCheckrembpassword.setChecked(true);
        }else{
            mCheckrembpassword.setChecked(false);
        }
        tv_version= (TextView) findViewById(R.id.tv_version);
        tv_version.setText("Ver "+ com.android.thinkive.framework.util.AppUtil.getVersionName(this));
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            try {
                if(mKeyboard != null) {
                    Field field = mKeyboard.getClass().getDeclaredField("mIsKeyboardAddedToWindow");
                    if(field != null) {
                        field.setAccessible(true);
                        boolean isKeyboardShow = field.getBoolean(mKeyboard);
                        //如果键盘显示,则隐藏键盘.反之finish activity
                        if (isKeyboardShow) {
                            mKeyboard.dismiss();
                        } else {
                            PreferencesUtil.putBoolean(this, "Islogin", false);
                            //发广播通知2级界面去刷新
                            Intent it = new Intent(Constant.ACTION_LOGINEND);
                            it.putExtra("islogin", false);
                            sendBroadcast(it);
                            finish();
                        }
                    }
                }
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return true;

    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mKeyboard != null && mKeyboard.isShowing() ) {
            mKeyboard.dismiss();
        }
    }

    private void hideSystemKeyboard(EditText edt,short type){
        //初始化键盘
        if (mKeyboard != null && mKeyboard.isShowing()) {
            mKeyboard.dismiss();
            mKeyboard = null;
        }
        if (getParent() == null) {
//            KEYBOARD_TYPE_COMMON KEYBOARD_TYPE_DIGITAL
            mKeyboard = new KeyboardManager(this, edt, type);
        } else {
            mKeyboard = new KeyboardManager(getParent(), edt, type);
        }
        //设置不显示系统键盘
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        try {
            Method setShowSoftInputOnFocus =
                    edt.getClass().getMethod("setShowSoftInputOnFocus", boolean.class);
            setShowSoftInputOnFocus.setAccessible(true);
            setShowSoftInputOnFocus.invoke(edt, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dismissSystemKeyBoard(View v) {
        InputMethodManager imm = (InputMethodManager)this
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }


    /**
     * 用于点击登录类型时显示不同账号提示
     * @param visibility 提示是否可见
     */
    public void setLoginAccountType(int visibility,int loginType) {
        mLoginAccountEdt.setText("");
        mLoginPasswordEdt.setText("");
        mVerifyCodeEdt.setText("");
        if(loginType == LOGIN_TYPE_ACCOUNT){
            tv_hint_account.setText("资金账号");
            tv_tradePassword.setText("交易密码");
            mLoginAccountEdt.setHint(R.string.account_hint);
            mLoginPasswordEdt.setHint(R.string.password_hint);
            mLoginAccountEdt.setInputType(InputType.TYPE_CLASS_NUMBER);
            mLoginAccountEdt.setFilters(new InputFilter[]{new InputFilter.LengthFilter(32)});
            mLoginPasswordEdt.setFilters(new InputFilter[]{new InputFilter.LengthFilter(6)});
            String username =  PreferencesUtil.getString(this, Constant.REMBER_USERNAME, "");
            if(!TextUtils.isEmpty(username)){
                mLoginAccountEdt.setText(username);
                mCheckrembpassword.setChecked(true);
            }else{
                mCheckrembpassword.setChecked(false);
            }
        }else if(loginType == LOGIN_TYPE_E_ACCOUNT){
            tv_hint_account.setText("E账通号");
            tv_tradePassword.setText("登录密码");
            mLoginAccountEdt.setHint(R.string.account_e_hint);
            mLoginAccountEdt.setInputType(InputType.TYPE_CLASS_TEXT);
            mLoginAccountEdt.setFilters(new InputFilter[]{new InputFilter.LengthFilter(32)});
            mLoginPasswordEdt.setHint(R.string.e_password_hint);
            mLoginPasswordEdt.setFilters(new InputFilter[]{new InputFilter.LengthFilter(12)});
            String username =  PreferencesUtil.getString(this, Constant.REMBER_USERNAME_EZT, "");
            if(!TextUtils.isEmpty(username)){
                mLoginAccountEdt.setText(username);
                mCheckrembpassword.setChecked(true);
            }else{
                mCheckrembpassword.setChecked(false);
            }
        }
        mVerifyCodeEdt.setHint(R.string.verify_hint);
//        mLoginEHintTv.setVisibility(visibility);
    }
    // 判断是否数字类型
    public static boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }
    /**
     * 开始登录请求
     * @param loginType 登录类型值
     */
    public void startRequestLogin(int loginType) {
        userName = mLoginAccountEdt.getText().toString();
        final String userPassword = mLoginPasswordEdt.getText().toString();
        final String verifyCode = mVerifyCodeEdt.getText().toString();
        if(TextUtils.isEmpty(userName)) {
            ToastUtils.Toast(this,"账号不能为空");
            return;
        }
        if(TextUtils.isEmpty(userPassword)) {
            ToastUtils.Toast(this,"密码不能为空");
            return;
        }
        if(TextUtils.isEmpty(verifyCode)) {
            ToastUtils.Toast(this,"验证码不能为空");
            return;
        }
        loadingDialog.show("正在登录");
        HashMap<String,String> params = new HashMap<>();
        if(loginType == FUND_LOGIN_TYPE) {
            params.put("funcNo", "100006");
//            params.put("funcNo", "600002");
            params.put("fund_account", userName);
            params.put("trade_pwd", userPassword);
            params.put("ticket", verifyCode);
        } else if(loginType == E_LOGIN_TYPE) {
//            params.put("funcNo", "600001");
            params.put("funcNo", "100007");
            params.put("user_id", userName);
            params.put("trade_pwd", userPassword);
            params.put("ticket", verifyCode);
            if (userName.startsWith("88") && isNumeric(userName) && userName.length() == 12) {
                params.put("user_id_type", "E");
            }else {
                params.put("user_id_type", "N");
            }
        }
        params.put("ip", DeviceUtil.getIpAddress(this));
        params.put("phone", "");
        params.put("mac", DeviceUtil.getMacAddress(this));
        params.put("mobileKey", DeviceUtil.getIpAddress(this));
        params.put("entrust_way", "SJWT");
        params.put("mobileKey", mMobileKey  );
        RequestBean request = new RequestBean();
        request.setParam(params);
        request.setUrlName("URL_HTTP_LOGIN");
        NetWorkService.getInstance().request(request, new ResponseListener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                loadingDialog.dismiss();
                BaseJsonbean result = JsonParseUtil.parseJsonToObject(jsonObject,BaseJsonbean.class);
                if(result.getError_no()==0){
                    setLoginSuccessInfo(jsonObject);
                   remberUsername();
                    Intent intent =new Intent();
                    intent.putExtra("islogin", true);
                    setResult(RESULT_OK, intent);
                    //发广播通知2级界面去刷新
                    Intent it = new Intent(Constant.ACTION_LOGINEND);
                    it.putExtra("islogin", true);
                    sendBroadcast(it);
                    finish();
                }else{
                    requestVerify();
                    ToastUtils.Toast(LoginActivity.this,"登陆失败:"+result.getError_info());
                }
            }

            @Override
            public void onErrorResponse(Exception e) {
                loadingDialog.dismiss();
                requestVerify();
                ToastUtils.Toast(LoginActivity.this,"登陆失败,请检查网络" );
            }
        });
    }

    /**
     * 保存登陆账号
     */
    public void remberUsername() {
        if(mCheckrembpassword.isChecked()){
            if(mLoinType == FUND_LOGIN_TYPE){
                PreferencesUtil.putString(this,Constant.REMBER_USERNAME,userName);
            }else if(mLoinType == E_LOGIN_TYPE){
                PreferencesUtil.putString(this,Constant.REMBER_USERNAME_EZT,userName);
            }
        }else{
            if(mLoinType == FUND_LOGIN_TYPE){
                PreferencesUtil.putString(this,Constant.REMBER_USERNAME,"");
            }else if(mLoinType == E_LOGIN_TYPE){
                PreferencesUtil.putString(this,Constant.REMBER_USERNAME_EZT,"");
            }
        }
    }

    private void requestVerify(){
        setProgressVisibility(View.VISIBLE);
        mMobileKey = new Date().getTime()+"";
        String url = ConfigManager.getInstance(this).getAddressConfigValue("LOGIN_VERIFY_ICON_URL")+ "mobileKey="+mMobileKey;
        ImageLoader imageLoader = new ImageLoader(CoreApplication.getInstance().getRequestQueue(), BitmapCache.getInstance(LoginActivity.this));
        ImageLoader.ImageListener listener = new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer imageContainer, boolean b) {
                setVerifyBitmap(imageContainer.getBitmap());
                setProgressVisibility(View.GONE);
            }

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                ToastUtils.Toast(LoginActivity.this, "验证码加载失败:请检查网络");
            }
        };
        imageLoader.get(url, listener);
    }
    /**
     * 设置验证码
     * @param bitmap 验证码位图
     */
    public void setVerifyBitmap(Bitmap bitmap) {
        mShowVerifyCodeIv.setImageBitmap(bitmap);
    }

    /**
     * 设置进度条是否显示
     * @param visibility
     */
    public void setProgressVisibility(int  visibility) {
        int verifyVisibility = View.GONE;
        if(visibility == View.GONE || visibility == View.INVISIBLE) {
            verifyVisibility = View.VISIBLE;
        } else {
        }
        mLoadImagePgb.setVisibility(visibility);
        mShowVerifyCodeIv.setVisibility(verifyVisibility);
    }

    /**
     * 登陆成功保存信息
     */
    public void setLoginSuccessInfo(JSONObject results) {
        MemoryStorage storage = new MemoryStorage();
        if(mLoinType == FUND_LOGIN_TYPE) {
            storage.saveData(Constant.RESULTS_OF_ACCOUNT, String.valueOf(results.optJSONArray(Constant.LOGIN_RESULTS)));
        } else if(mLoinType == E_LOGIN_TYPE) {
            storage.saveData(Constant.RESULTS_OF_ACCOUNT_E, String.valueOf(results.optJSONArray(Constant.LOGIN_RESULTS)));
        }


        JSONObject accountsResult =null;
        try {
            accountsResult = results.optJSONArray(Constant.LOGIN_RESULTS).getJSONObject(0);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        final JSONObject data = new JSONObject();
        JSONArray accountList;
        JSONObject account;
        ArrayList<AccountBean> accounts = new ArrayList<AccountBean>();
        int index = 0 ;
        //读取账号信息
        try {
            if(accountsResult!=null) {

                //保存Token值到缓存
                LoginTokenBean tokenBean = JsonParseUtil.parseJsonToObject(accountsResult,LoginTokenBean.class);
                storage.saveData(Constant.APP_FZ_TOKEN, String.valueOf(tokenBean.getToken()));
                storage.saveData(Constant.APP_TRADE_TOKEN, String.valueOf(tokenBean.getTrade_token()));
                storage.saveData(Constant.APP_SC_TOKEN, String.valueOf(tokenBean.getMall_token()));
                storage.saveData(Constant.APP_XD_TOKEN, String.valueOf(tokenBean.getXdt_token()));
                storage.saveData(Constant.APP_GGT_TOKEN, String.valueOf(tokenBean.getHgt_token()));
                storage.saveData(Constant.LOGIN_STATE, "1");

                data.put(USER_NAME, "您好" +
                        accountsResult.optString(USER_NAME, getString(R.string.login_no_know)));
            accountList =  accountsResult.optJSONArray("fundlist");
            AccountBean accountBean = null;
            if(accountList != null) {
                //读取E账通登录后其他账号信息
                for(;index < accountList.length(); index ++) {
                    account = accountList.optJSONObject(index);
                    String accountType = account.optString("ext_syscode");
                    String accountName = account.optString(FUND_ACCOUNT);
                    accountBean = new AccountBean();
                    accountBean.setAccountName(accountName);
                    accountBean.setAccountType(accountType);
                    if (mLoinType == FUND_LOGIN_TYPE) {
                        accountBean.setLoginType(FUND_LOGIN_TYPE);
                    } else if(mLoinType == E_LOGIN_TYPE) {
                        accountBean.setLoginType( E_LOGIN_TYPE);
                    }
                    accounts.add(accountBean);
                    if(index <= 0) {
                        data.put(BRANCH_NAME,account.optString(ORG_NAME));
                    }
                }
            } else {
                //资金账号登录后，读取资金账号
                data.put(BRANCH_NAME,accountsResult.optString(BRANCH_NAME, getString(R.string.login_no_know)));
                accountBean = new AccountBean();
                String accountType = "1";
                String accountName = accountsResult.optString(FUND_ACCOUNT, getString(R.string.login_no_know));
                accountBean.setAccountType(accountType);
                accountBean.setAccountName(accountName);
                if (mLoinType == FUND_LOGIN_TYPE) {
                    accountBean.setLoginType(FUND_LOGIN_TYPE);
                } else if(mLoinType == E_LOGIN_TYPE) {
                    accountBean.setLoginType( E_LOGIN_TYPE);
                }
                accounts.add(accountBean);
            }
                addAccount(accounts,data);
                storage.saveData(ACCOUNT_LIST, accounts.toString());
            }

//            try {
//                accountsResult = results.optJSONArray(Constant.LOGIN_RESULTS).getJSONObject(1);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void addAccount(ArrayList<AccountBean> accounts,JSONObject data){
        if(accounts.size() >0){
            JSONArray jsonArray = new JSONArray();
            try {
                for (AccountBean bean : accounts) {
                    if (bean.getAccountType().equals("1")) {
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put(FUND_ACCOUNT, getString(R.string.login_fund_account) + bean.getAccountName());
                        jsonArray.put(jsonObject);
                    } else if (bean.getAccountType().equals("2")) {
                        data.put(LOAN_ACCOUNT,getString(R.string.login_loan_account) + bean.getAccountName());
                    } else if (bean.getAccountType().equals("3")) {
                        data.put(OTC_ACCOUNT,getString(R.string.login_otc_account) + bean.getAccountName());
                    }
                }
                data.put(FUND_ACCOUNT,jsonArray);
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode != RESULT_OK||REQUEST_ACTIVITE !=requestCode||data==null){
            Intent it = new Intent(Constant.ACTION_LOGINEND);
            it.putExtra("islogin", false);
            sendBroadcast(it);
            finish();
            return;
        }else {
            boolean isactivite = data.getBooleanExtra("isactivite", false);
            if (!isactivite) {
                Intent it = new Intent(Constant.ACTION_LOGINEND);
                it.putExtra("islogin", false);
                sendBroadcast(it);
                finish();
            }
        }
    }
}
