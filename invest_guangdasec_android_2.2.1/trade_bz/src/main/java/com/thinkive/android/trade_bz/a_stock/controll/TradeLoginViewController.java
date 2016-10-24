package com.thinkive.android.trade_bz.a_stock.controll;

import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.thinkive.framework.compatible.ListenerControllerAdapter;
import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.activity.TradeLoginActivity;
import com.thinkive.android.trade_bz.a_stock.fragment.FundLoginFragment;
import com.thinkive.android.trade_bz.others.constants.Constants;
import com.thinkive.android.trade_bz.others.tools.TradeLoginManager;
import com.thinkive.android.trade_bz.utils.PreferencesUtils;

/**
 * <p>
 * 描述：交易登录视图控制器
 * </p>
 *
 * @author 黎丝军、王志鸿
 * @version 1.0
 * @corporation 深圳市思迪信息科技有限公司
 * @date 2015/6/3
 */
public class TradeLoginViewController extends ListenerControllerAdapter implements
        View.OnClickListener, CheckBox.OnCheckedChangeListener, Spinner.OnItemSelectedListener {
    public static final int                ON_FOCUS_CHANGE = 7974922;
    /**
     * 交易登录界面实例
     */
    private             TradeLoginActivity mLoginActivity  = null;
    private FundLoginFragment mNormalLoginFragment;
    private String             mLoginType;

    public TradeLoginViewController(TradeLoginActivity activity, String loginType) {
        mLoginActivity = activity;
        mNormalLoginFragment = activity.getFundLoginFragment();
        mLoginType = loginType;
    }

    @Override
    public void register(int eventType, View view) {
        switch (eventType) {
            case ON_CLICK:
                view.setOnClickListener(this);
                break;
            case ON_CHECK:
                ((CheckBox) view).setOnCheckedChangeListener(this);
                break;
            case ON_SELECT:
                ((Spinner) view).setOnItemSelectedListener(this);
            default:
                break;
        }
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
//         首先判断，是不是单击了“请选择登录方式”文本
//                if (viewId == R.id.tv_selectedLoginWay) { // “请选择登录方式”文本单击事件
//                    mLoginFragment.onClickSelectLoginWay();
//                } else { // 如果不是的话，那么可以收回登录方式选择菜单
//                    mLoginFragment.onClickSomePlaceExpectLoginWayText();
//                    if (viewId == R.id.iv_show_security_code) { // 验证码图片单击事件监听
//                        mLoginFragment.onClickShowVerifyCode();
//                    } else if (viewId == R.id.btn_login) {  // 登录按钮单击事件监听
//                        mLoginFragment.onClickLogin();
//                    } /*else if (viewId == R.id.tv_login_way_fund_account ||    // “资金账号登录”单击事件
//                            viewId == R.id.tv_login_way_client ||             // “客户号登录”单击事件
//                            viewId == R.id.tv_login_way_huA_share_holder ||   // “沪A股东账号登录”单击事件
//                            viewId == R.id.tv_login_way_shenA_share_holder ||
//                            viewId == R.id.tv_login_way_huB_share_holder||
//                            viewId == R.id.tv_login_way_huB_share_holder) { // “深A股东账号登录”单击事件
//                        mLoginFragment.onClickLoginWayMenu(viewId);
//                    }*/
//                }

        if (viewId == R.id.iv_show_security_code) { // 验证码图片单击事件监听
            mNormalLoginFragment.onClickShowVerifyCode();
        } else if (viewId == R.id.btn_login) {  // 登录按钮单击事件监听
            mNormalLoginFragment.onClickLogin();
        }else if (viewId == R.id.tv_show_faq) {  // faq
            mNormalLoginFragment.onClickShowFaq();
        }else if (viewId == R.id.tv_toopen_account) {  // 开户
            mNormalLoginFragment.onClickOpenAccount();
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (mLoginType.equals(TradeLoginManager.LOGIN_TYPE_NORMAL)) { // 普通账户登录时
            PreferencesUtils.putBoolean(mLoginActivity, Constants.IS_SAVE_NORMAL_ACCOUNT_KEY, isChecked);
        } else if (mLoginType.equals(TradeLoginManager.LOGIN_TYPE_CREDIT)) { // 融资融券账户登录时
            PreferencesUtils.putBoolean(mLoginActivity, Constants.IS_SAVE_CREDIT_ACCOUNT_KEY, isChecked);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        final String accountType = ((TextView) view).getText().toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
