package com.thinkive.android.trade_bz.a_stock.bll;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;

import com.android.thinkive.framework.ThinkiveInitializer;
import com.android.thinkive.framework.config.ConfigManager;
import com.android.thinkive.framework.network.http.MyImageRequest;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_rr.bean.CreditLoginInfo;
import com.thinkive.android.trade_bz.a_stock.activity.TradeLoginActivity;
import com.thinkive.android.trade_bz.a_stock.bean.LoginInfo;
import com.thinkive.android.trade_bz.a_stock.fragment.FundLoginFragment;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.others.constants.Constants;
import com.thinkive.android.trade_bz.others.tools.ThinkiveTools;
import com.thinkive.android.trade_bz.others.tools.TradeFlags;
import com.thinkive.android.trade_bz.others.tools.TradeLoginManager;
import com.thinkive.android.trade_bz.request.RequestLogin;
import com.thinkive.android.trade_bz.utils.LogUtil;
import com.thinkive.android.trade_bz.utils.ToastUtils;
import com.thinkive.android.trade_bz.utils.TradeUtils;

import java.util.HashMap;

/**
 * 易单独登陆，不走统一登陆
 * 描述：交易登录业务类
 *
 * @author 黎丝军、王志鸿
 * @version 1.0
 * @corporation 深圳市思迪信息科技有限公司
 * @date 2015/6/3
 */
public class TradeLoginServiceImpl {
    private FundLoginFragment mFragment = null;
    private TradeLoginActivity mActivity = null;
    /**
     * 用于存储请求验证的随机数，好登录时传参
     */
    private String mMobileKey = "";
    /**
     * 登录类型
     * 0：普通账号登录；1：融资融券登录
     */
    private String mLoginType = "";

    public TradeLoginServiceImpl(FundLoginFragment fragment, String loginType) {
        mFragment = fragment;
        mLoginType = loginType;
        mActivity = (TradeLoginActivity) fragment.getActivity();
    }

    /**
     * 请求验证码
     */
    public void requestVerifyImage() {
        mFragment.setProgressVisibility(View.VISIBLE);
        // 都进了这个类，怎么可能是统一登录？？
        requestNormalVerifyImage();
    }

    /**
     * 非统一登录时使用的请求验证码
     */
    public void requestNormalVerifyImage() {
        int maxHeight = 100;
        int maxWidth = 100;
        mMobileKey = TradeUtils.getRandomStr(10);
        final StringBuilder builder = new StringBuilder();
        if (mLoginType.equals(TradeLoginManager.LOGIN_TYPE_CREDIT)) { // 如果是融资融券账户
            builder.append(ConfigManager.getInstance().getAddressConfigValue(Constants.URL_CREDIT_TRADE_CODE));
        } else if (mLoginType.equals(TradeLoginManager.LOGIN_TYPE_OPTION)) { // 如果是个股期权账户
            builder.append(ConfigManager.getInstance().getAddressConfigValue(Constants.URL_TRADE_CODE));
        } else if (mLoginType.equals(TradeLoginManager.LOGIN_TYPE_NORMAL)) { // 如果是普通账号
            builder.append(ConfigManager.getInstance().getAddressConfigValue(Constants.URL_TRADE_CODE));
        }
        builder.append("r=");
        builder.append(mMobileKey);
        builder.append("&mobileKey=");
        builder.append(mMobileKey);
        MyImageRequest imageRequest = new MyImageRequest(builder.toString(), new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap bitmap) {
                mFragment.onGetVerifyCode(bitmap);
            }
        }, maxWidth, maxHeight, Bitmap.Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                LogUtil.printLog("e", "请求交易单独登录验证码出错，错误堆栈：");
                volleyError.printStackTrace();
                mFragment.setProgressVisibility(View.GONE);
            }
        });
        ThinkiveInitializer.getInstance().addToRequestQueue(imageRequest);
    }

    /**
     * 此方法在请求完加密密码方法后执行
     */
    public void requestLogin() {
        String pwd = mFragment.getLoginPassword();
        requestNormalLogin(mFragment.getLoginAccount(), pwd, mFragment.getSecurityCode());
    }

    /**
     * 请求服务器，进行交易模块登录，不是统一登录
     *
     * @param loginAccount  登录账号
     * @param loginPassword 登录密码
     * @param verifyCode    验证码
     */
    public void requestNormalLogin(final String loginAccount, final String loginPassword,
                                   String verifyCode) {
        HashMap<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("input_content", loginAccount);
        paramMap.put("ticket", verifyCode);
        paramMap.put("entrust_way", TradeLoginManager.ENTRUST_WAY);
        paramMap.put("mobileKey", mMobileKey);
        paramMap.put("password", loginPassword);
        paramMap.put("input_type", "0");
        paramMap.put("op_station", TradeLoginManager.OP_STATION_2);
        paramMap.put("phone_no", "18607026105");
        paramMap.put("login_type", mLoginType);
        if (mLoginType.equals(TradeLoginManager.LOGIN_TYPE_NORMAL)) {
            paramMap.put("funcNo", "300100");
        } else if (mLoginType.equals(TradeLoginManager.LOGIN_TYPE_CREDIT)) {// 如果是融资融券登录
            paramMap.put("funcNo", "1000010");
        }
        mFragment.onLoginStart();
        new RequestLogin(paramMap, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                TradeFlags.addFlag(TradeFlags.FLAG_NOT_UNITY_LOGIN_TYPE);
                mFragment.onLoginSuccess(loginAccount, mLoginType);
                ToastUtils.toast(context, R.string.login_success);
                if (mLoginType.equals(TradeLoginManager.LOGIN_TYPE_NORMAL)) {
                    //普通用户登录的用户信息保存
                    LoginInfo.setUsername(loginAccount);
                    LoginInfo.setPassword(loginPassword);
                    //给新股申购提供用户信息
                    String s = bundle.getString(RequestLogin.BUNDLE_KEY_LOGIN);
                    if (s != null) {
                        int i = s.lastIndexOf("results\":[{")+10;
                        s=s.substring(0, i + 1) + "\"loginClass\":\"0\",\"loginType\":\"5\"," + s.substring(i + 1, s.length());
                        System.out.println("sssssssssssssssssssssssssssss" + s.toString());
                        ThinkiveTools.putDataToMemoryByMsg(Constants.NORMAL_LOGIN_USERINFO_FORH5, s);
                    }
                }
                if (mLoginType.equals(TradeLoginManager.LOGIN_TYPE_CREDIT)) {
                    CreditLoginInfo.setPassword(loginPassword);
                    //给新股申购提供用户信息
                    String s = bundle.getString(RequestLogin.BUNDLE_KEY_LOGIN);
                    if (s != null) {
                        int i = s.lastIndexOf("results\":[{")+10;
                        s=s.substring(0, i + 1) + "\"loginClass\":\"1\",\"loginType\":\"b\"," + s.substring(i + 1, s.length());
                        ThinkiveTools.putDataToMemoryByMsg(Constants.CREDIT_LOGIN_USERINFO_FORH5,s);
                    }
                }
            }

            @Override
            public void onFailed(Context context, Bundle bundle) {
                String errorInfo = bundle.getString(RequestLogin.ERROR_INFO);
                ToastUtils.toast(context, context.getResources().getString(R.string.login_fail_because) +
                        errorInfo);
                mFragment.onLoginFailed();
                requestVerifyImage();
                if (errorInfo.contains(context.getResources().getString(R.string.login_hasnot_account_text))) {
                    mFragment.resetAccountEdt();
                } else if (errorInfo.contains(context.getResources().getString(R.string.login_pwd_text)) ||
                        errorInfo.contains(context.getResources().getString(R.string.login_account_text))) {
                    mFragment.resetPwdEdt();
                } else if (errorInfo.contains(context.getResources().getString(R.string.login_verify_text))) {
                    mFragment.resetVerifyEdt();
                }
            }
        }, mLoginType).request();
    }
}
