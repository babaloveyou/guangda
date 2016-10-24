package com.thinkive.android.trade_bz.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

/**
 * 相关注释待本类代码基本稳定后添加，敬请期待！
 *
 * Announcements：
 *
 * @author 王志鸿
 * @company Thinkive
 * @date 2015/7/15
 */
public abstract class TradeBaseBroadcastReceiver extends BroadcastReceiver {

    public static final String ACTION_START_ACTIVITY = "action_start_activity";

    // TODO: 后续优化
    public static final String ACTION_H5_UPDATE_FINISH = "com.thinkive.android.h5.upgrade.complete";

    public static final String ACTION_H5_BACK = "action_h5_back";

    public static final String ACTION_CHANGE_PWD_SUCCESS = "action_change_pwd";
    /**
     * 收到这条广播时，发起资金账号退出校验状态，需做同步处理
     */
    public static final String ACTION_LOGOUT_FUND_ACCOUNT = "action_logout_fund_account_finish";

    public static final String ACTION_ERROR_999 = "action_error_-999";
    /**
     * 收到这条广播时，说明用户在进行统一账户登录时，突然想进行资金账号单独登录了。
     */
    public static final String ACTION_TRANSFORM_TRADE_LOGIN = "action_transform_trade_login";
    /**
     * 交易单独登录成功完成时
     */
    public static final String ACTION_TRADE_LOGIN_SUCCESS = "action_trade_login_success";

    private IntentFilter mFilter;

    private Context mContext;

    public TradeBaseBroadcastReceiver(Context context) {
        mFilter = new IntentFilter();
        mContext = context;
    }

    public void setActions(String... actions) {
        for (String action: actions) {
            mFilter.addAction(mContext.getPackageName() + "_" + action);
        }
    }

    public void register() {
        mContext.registerReceiver(this, mFilter);
    }

    public void unregister() {
        mContext.unregisterReceiver(this);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        intent.setAction(action.replaceAll(mContext.getPackageName() + "_",""));
    }

    public static void sendBroadcast(Context context,Intent intent, String action) {
        intent.setAction(context.getPackageName() + "_" + action);
        context.sendBroadcast(intent);
    }
}
