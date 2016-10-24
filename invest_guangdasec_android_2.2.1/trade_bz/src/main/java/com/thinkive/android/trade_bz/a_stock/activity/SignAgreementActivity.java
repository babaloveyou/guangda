package com.thinkive.android.trade_bz.a_stock.activity;

import android.os.Bundle;
import android.view.View;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.fragment.SignAgreementFragment;
import com.thinkive.android.trade_bz.others.tools.TradeTools;

/**
 *  退市板块协议签署
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/8/1
 */
public class SignAgreementActivity extends AbsNavBarActivity {

    private SignAgreementFragment mFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 发消息，让登录页关闭
        TradeTools.sendMsgToLoginForSubmitFinish(this);
        initData();
        initViews();
    }

    @Override
    protected void initViews() {
        addFragment(R.id.fl_container, mFragment);
        //左边按钮
        setTitleText(R.string.home_sign_agreement);
        setBackBtnVisibility(View.VISIBLE);
    }

    @Override
    protected void initData() {
        mFragment = new SignAgreementFragment();
    }
}
