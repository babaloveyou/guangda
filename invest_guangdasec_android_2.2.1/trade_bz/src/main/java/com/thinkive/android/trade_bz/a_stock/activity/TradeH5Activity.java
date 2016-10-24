package com.thinkive.android.trade_bz.a_stock.activity;

import android.content.Intent;
import android.os.Bundle;
import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.others.tools.TradeTools;
import com.thinkive.android.trade_bz.others.tools.TradeWebFragmentManager;

/**
 * 专门装载一个WebFragment，使原生交易和H5交易结合
 * @author 王志鸿
 * @company Thinkive
 * @date 2015/6/16
 */
public class TradeH5Activity extends AbsNavBarActivity {

    /**
     * 交易入口地址
     */
    private Bundle mBundle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_fragment_trade, false);
        // 发消息，让登录页关闭
        TradeTools.sendMsgToLoginForSubmitFinish(this);
        initData();
        initViews();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void findViews() {

    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        mBundle = intent.getExtras();
        TradeWebFragmentManager.sWebCacheFragment.setArguments(mBundle);
    }

    @Override
    protected void initViews() {
        addFragment(R.id.fl_container,  TradeWebFragmentManager.sWebCacheFragment);
    }

    @Override
    protected void setListeners() {

   }
}
