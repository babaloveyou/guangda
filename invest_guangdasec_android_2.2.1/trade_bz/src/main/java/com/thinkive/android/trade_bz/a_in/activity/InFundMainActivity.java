package com.thinkive.android.trade_bz.a_in.activity;

import android.os.Bundle;
import android.view.View;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_in.fragment.InFundMainFragment;
import com.thinkive.android.trade_bz.a_stock.activity.AbsNavBarActivity;
import com.thinkive.android.trade_bz.others.tools.TradeTools;

/**
 *  场内基金交易的主页面
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/7/28
 */
public class InFundMainActivity extends AbsNavBarActivity {

    private InFundMainFragment mFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_fragment_trade,true);
        // 发消息，让登录页关闭
        TradeTools.sendMsgToLoginForSubmitFinish(this);
        initData();
        initViews();
    }

    @Override
    protected void initViews() {
        setTitleText(R.string.home_fun_trade_in);
        setBackBtnVisibility(View.VISIBLE);
        addFragment(R.id.fl_container, mFragment);
        mFragment.setArguments(getIntent().getExtras());
    }

    @Override
    protected void initData() {
        mFragment = new InFundMainFragment();
    }

}
