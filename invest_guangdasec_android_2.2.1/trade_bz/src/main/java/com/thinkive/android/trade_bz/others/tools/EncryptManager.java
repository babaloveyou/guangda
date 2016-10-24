package com.thinkive.android.trade_bz.others.tools;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;

import com.android.thinkive.framework.CoreApplication;
import com.android.thinkive.framework.util.RSAUtil;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.request.Request1000000;
import com.thinkive.android.trade_bz.utils.LogUtil;
import com.thinkive.android.trade_bz.utils.PreferencesUtils;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import org.bouncycastle.util.encoders.Hex;

import java.util.HashMap;

/**
 * 加密处理管理器，目前只负责RSA的加密
 * Announcements：类名后期可能会改成和RSA有关的名称
 *                本类设计失误，建议移除。
 *
 * @author 王志鸿
 * @company Thinkive
 * @date 2015/8/24
 */
public class EncryptManager {

    public static final String PREFERENCE_KEY_RSA_PUBLIC_EXPONENT = "rsa_public_exponent";

    public static final String PREFERENCE_KEY_RSA_MODULUS = "rsa_modulus";

    /**
     * 本类的单例模式对象
     */
    private static EncryptManager sInstance;

    /**
     * RSA加密的公共密钥
     */
    private String mRsaPublicExponent;

    /**
     * RSA加密的模数
     */
    private String mRsaModulus;

    private Context mContext;

    private EncryptManager() {
        mContext = CoreApplication.getInstance();
    }

    /**
     * 获取本类单例对象
     * @return 本类的单例模式对象
     */
    public synchronized static EncryptManager getInstance() {
        if (sInstance == null) {
            sInstance = new EncryptManager();
        }
        return sInstance;
    }

    /**
     * 检查是否能从Preference中取得加密参数
     * 如果Preference中没有保存加密参数，那么，发起请求，获取RSA加密的公共密钥和加密的模数，保存在临时文件里
     * 这个方法在模块启动时，就调用。在获取不到加密参数时，也可以调用
     */
    public void requestRsaParam() {
        mRsaPublicExponent = PreferencesUtils.getString(mContext, PREFERENCE_KEY_RSA_PUBLIC_EXPONENT, "");
        mRsaModulus = PreferencesUtils.getString(mContext, PREFERENCE_KEY_RSA_MODULUS, "");
        // 当Preference中取不到值的时候
        if (mRsaPublicExponent.equals("") || mRsaModulus.equals("")) {
            new Request1000000(new HashMap<String, String>(), new IRequestAction() {
                @Override
                public void onSuccess(Context context, Bundle bundle) {
                    mRsaModulus = bundle.getString(Request1000000.BUNDLE_KEY_MODULUS);
                    mRsaPublicExponent = bundle.getString(Request1000000.BUNDLE_KEY_PUBLIC_EXPONENT);
                    PreferencesUtils.putString(context, PREFERENCE_KEY_RSA_PUBLIC_EXPONENT, mRsaPublicExponent);
                    PreferencesUtils.putString(context, PREFERENCE_KEY_RSA_MODULUS, mRsaModulus);
                }

                @Override
                public void onFailed(Context context, Bundle bundle) {
                    ToastUtils.toast(context, bundle.getString(Request1000000.ERROR_INFO));
                }
            }).request();
        }
    }

    /**
     * 获取经过RSA加密后的字符串
     * @param content 要被加密的字符串
     * @return 加密后的字符串
     */
    public String getRsaEncryptStr(String content) {
        String result ="";
        // 判断是否请求到加密参数（公共密匙和模数）
        if (TextUtils.isEmpty(mRsaModulus) || TextUtils.isEmpty(mRsaPublicExponent)) {
            LogUtil.printLog("e", "加密失败，因为加密参数（公共密钥或模数）为空！");
            requestRsaParam();
        } else {
            try {
                byte[] bt = Hex.encode(RSAUtil.encryptByPublicKey(mRsaModulus, mRsaPublicExponent, " "+content));
                result = "encrypt_rsa:"+new String(bt, "UTF-8");

            } catch (Exception e) {
                LogUtil.printLog("e", "加密时出现错误，错误信息：" + e.getMessage());
            }
        }
        return result;
    }

    public String getRsaPublicExponent() {
        return mRsaPublicExponent;
    }

    public String getRsaModulus() {
        return mRsaModulus;
    }
}
