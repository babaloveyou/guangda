package com.thinkive.android.trade_bz.a_stock.bll;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;

import com.android.thinkive.framework.ThinkiveInitializer;
import com.android.thinkive.framework.config.ConfigManager;
import com.android.thinkive.framework.network.http.HttpService;
import com.android.thinkive.framework.storage.MemoryStorage;
import com.android.thinkive.framework.util.FormatUtil;
import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.bean.MoneySelectBean;
import com.thinkive.android.trade_bz.a_stock.fragment.CreditTradeFragment;
import com.thinkive.android.trade_bz.a_stock.fragment.NormalTradeFragment;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.others.constants.Constants;
import com.thinkive.android.trade_bz.others.tools.TradeFlags;
import com.thinkive.android.trade_bz.others.tools.TradeLoginManager;
import com.thinkive.android.trade_bz.others.tools.TradeTools;
import com.thinkive.android.trade_bz.receivers.TradeBaseBroadcastReceiver;
import com.thinkive.android.trade_bz.request.Request301504;
import com.thinkive.android.trade_bz.request.Request600002;
import com.thinkive.android.trade_bz.request.Request600003;
import com.thinkive.android.trade_bz.utils.LogUtil;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.util.HashMap;

/**
 * 手机验证页面、主页功能业务类
 * Announcements：手机验证页面和交易mActivity都在{@link NormalTradeFragment}类中
 * @author 王志鸿
 * @company Thinkive
 * @date 2015/6/12
 */
public class TradeMainServicesImpl {
    private NormalTradeFragment mNormalFragment;
    private CreditTradeFragment mCreditFragment;
    private Resources mResources;
    private long mCurrentTimeMillis;
    private int mCreateSessionCount = 0;
    private boolean isNormal = true;
    private Activity mActivity = null;
    /**
     * 主页业务类构造方法，初始化外部环境和统一登录管理器
     * @param fragment 外部调用环境
     */
    public TradeMainServicesImpl(NormalTradeFragment fragment) {
        mNormalFragment = fragment;
        mResources = ThinkiveInitializer.getInstance().getContext().getResources();
        mActivity = mNormalFragment.getActivity();
        isNormal = true;
    }
    public TradeMainServicesImpl(CreditTradeFragment fragment) {
        mCreditFragment = fragment;
        mResources = ThinkiveInitializer.getInstance().getContext().getResources();
        mActivity = mCreditFragment.getActivity();
        isNormal = false;
    }

    /**
     * 请求我的持仓
     */
    public void requestMyHoldPager() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("money_type", "0");
        new Request301504(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                MoneySelectBean data = (MoneySelectBean) bundle.getSerializable(Request301504.BUNDLE_KEY_MYHOLD_HEAD);
                if (data != null) {
                    if (isNormal) {
                        mNormalFragment.onGetMyHoldData(data);
                    }
                } else {
                    //// TODO: 2016/10/24
                }
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                if (isNormal) {
                    mNormalFragment.onGetMyHoldDataFail();
                    ToastUtils.toast(context, bundle.getString(Request301504.ERROR_INFO));
                } else {
                    //// TODO: 2016/10/24
                }
            }
        }).request();
    }
    /**
     * 注销账号
     * 不是统一登录的话直接返回，
     * 是统一登录的话，用统一登录模块提供的方法进行注销，以通知其他模块。
     */
    public void requestLogout() {
        // 如果不是统一登录，那么本方法不需要执行
        if (TradeLoginManager.IF_UNITY_LOGIN) {
            TradeBaseBroadcastReceiver.sendBroadcast(mActivity, new Intent(), TradeBaseBroadcastReceiver.ACTION_LOGOUT_FUND_ACCOUNT);
        }
    }

    /**
     * 建立会话
     * 使用token，开启会话
     * @param tempToken 临时登录结果信息，用于传给服务器，以获取用户的登录信息
     */
    public void startServerSession(String tempToken) {
        //小于1分钟多次请求600002则认定为服务器异常   原因可能是重复请求600003,600003一直报-999
        if (System.currentTimeMillis() - mCurrentTimeMillis < 30000) {
            mCurrentTimeMillis = 0;
            mCreateSessionCount++;
            if (mCreateSessionCount > 3) {
                mCreateSessionCount = 0;
                ToastUtils.toast(ThinkiveInitializer.getInstance().getContext(), mResources.getString(R.string.unity_login_failed2));
                TradeTools.sendMsgToLoginForSubmitFinish(mActivity);
                return;
            }
        }
        //初始化会话的标志位
        TradeFlags.removeFlag(TradeFlags.FLAG_CREATE_SESSION_SUCCESS);
        HashMap<String, String> paramsMap = new HashMap<String, String>();
        paramsMap.put("temp_token", tempToken);
        // merid的值取决于各个模块的公认模块名
        paramsMap.put("merchant_id", Constants.MODULE_NAME_TRADE);
        new Request600002(paramsMap, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                TradeFlags.addFlag(TradeFlags.FLAG_CREATE_SESSION_SUCCESS);
                LogUtil.printLog("d", "600002模拟登录成功");
                mCurrentTimeMillis = System.currentTimeMillis();//600002只要调用就记录,短时间内调用多次及视为服务器异常,终止循环
                //判断是否需要在600002成功后直接调用600003
                requestUserInfoAfterSession();
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                mCurrentTimeMillis= System.currentTimeMillis();//600002只要调用就记录,短时间内调用多次及视为服务器异常,终止循环
                TradeLoginManager.getInstance().reGetToken();//600002失败  重构token
                TradeFlags.removeFlag(TradeFlags.FLAG_CREATE_SESSION_SUCCESS);

                ToastUtils.toast(context, mResources. getString(R.string.unity_login_failed0)
                        + bundle.getString(Request600002.ERROR_INFO));

                LogUtil.printLog("d", "600002模拟登录失败！原因是：" + bundle.getString(Request600002.ERROR_INFO));
            }
        }).request();
    }

    /**
     * 6000003登录
     */
    public void requestUserMsgFromServer(final String account, final String acct_type) {
        //设置入参
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("entrust_way", TradeLoginManager.ENTRUST_WAY);
        params.put("op_station", TradeLoginManager.OP_STATION_2);
        params.put("acct_type",acct_type);

        if (TradeLoginManager.LOGIN_TYPE_FUND_ACCOUNT.equals(acct_type) ||
                TradeLoginManager.LOGIN_TYPE_CREDIT_ACCOUNT.equals(acct_type)||
                TradeLoginManager.LOGIN_TYPE_OPTION_ACCOUNT.equals(acct_type)) {
            params.put("fund_account", account);
        }

        //保存账号及账户类型
        TradeLoginManager.getInstance().saveAccountAndType(account,acct_type);

        new Request600003(acct_type,params, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                LogUtil.printLog("d", "600003请求成功！");
                if (isNormal) {
                    mNormalFragment.onGetUserInfo(account, acct_type);
                    mNormalFragment.setFuncNo999(null);//置空funcNo  保证不受干扰
                } else {
                    //// TODO: 2016/10/24
                }
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                LogUtil.printLog("d", "600003请求报错，error_no：" + bundle.getString(Request600003.ERROR_NO) +
                        "，error_info：" + bundle.getString(Request600003.ERROR_INFO));
                // TODO: 2016/7/22 项目中需要移除
                ToastUtils.toast(context, mResources.getString(R.string.unity_login_failed1)
                        + bundle.getString(Request600003.ERROR_INFO));

                //移除获取用户信息成功的标志位
                TradeFlags.removeFlag(getUserInfoFlagByAcctType(acct_type));

                String error_no = bundle.getString(Request600003.ERROR_NO);
                String errorInfo = bundle.getString(Request600003.ERROR_INFO);

                if (!"-999".equals(error_no) && !"-919".equals(error_no)) {  //600003报-999时不通知跳转
                    ToastUtils.toast(context, errorInfo);
                    TradeTools.sendMsgToLoginForSubmitFinish(mActivity);
                    if ("-18".equals(error_no)) { //获取用户信息失败
                        if (isNormal) {
                            mNormalFragment.setLogout();
                        } else {
                            //// TODO: 2016/10/24
                        }
                    }
                }

            }
        }, account).request();
    }

    /**
     * 会话建立成功之后如果有已经校验过的账户, 遍历请求600003
     */
    private void requestUserInfoAfterSession(){
        if (TradeFlags.check(TradeFlags.FLAG_NORMAL_ACCOUNT_CHECK_SUCCESS)){
            requestUserMsgFromServer(TradeLoginManager.sNormalLoginAccount, TradeLoginManager.sNormalLoginType);
        }
        if (TradeFlags.check(TradeFlags.FLAG_CREDIT_ACCOUNT_CHECK_SUCCESS)){
            requestUserMsgFromServer(TradeLoginManager.sCreditLoginAccount, TradeLoginManager.sCreditLoginType);
        }
        if (TradeFlags.check(TradeFlags.FLAG_OPTION_ACCOUNT_CHECK_SUCCESS)){
            requestUserMsgFromServer(TradeLoginManager.sOptionLoginAccount, TradeLoginManager.sOptionLoginType);
        }
    }

    /**
     * 通过账户类型判断用户信息的Flag
     * @param acctType 账户类型
     * @return 用户信息Flag
     */
    public int getUserInfoFlagByAcctType(String acctType){
        int flag;
        switch (acctType) {
            case TradeLoginManager.LOGIN_TYPE_CREDIT_ACCOUNT:
                flag = TradeFlags.FLAG_GET_CREDIT_USERINFO_SUCCESS;
                break;
            case TradeLoginManager.LOGIN_TYPE_OPTION_ACCOUNT:
                flag = TradeFlags.FLAG_GET_OPTION_USERINFO_SUCCESS;
                break;
            default:
                flag = TradeFlags.FLAG_GET_NORMAL_USERINFO_SUCCESS;
        }
        return flag;
    }

    /**
     * 清除会话信息
     */
    public void requestClearSession() {
        String cookieKey;
        String address = ConfigManager.getInstance().getAddressConfigValue(Constants.URL_TRADE);
        String domain = FormatUtil.formatUrlToDomain(address);
        String ip = FormatUtil.formatUrlToIp(address);
        int port = FormatUtil.formatUrlToPort(address);
        if (!TextUtils.isEmpty(domain)) {
            cookieKey = domain + ":" + port;
        } else {
            cookieKey = ip + ":" + port;
        }
        HttpService.sCookieMap.remove(cookieKey);
        MemoryStorage memoryStorage = new MemoryStorage();
        memoryStorage.removeData(cookieKey);
        //        new Request1000013(new IRequestAction() {
        //            @Override
        //            public void onSuccess(Context context, Bundle bundle) {
        //            }
        //
        //            @Override
        //            public void onFailed(Context context, Bundle bundle) {
        //                ToastUtils.toast(context, bundle.getString(BaseRequest.ERROR_INFO));
        //            }
        //        }).request();
    }
}
