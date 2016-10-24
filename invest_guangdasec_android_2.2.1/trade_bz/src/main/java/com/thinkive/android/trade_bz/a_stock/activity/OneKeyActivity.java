package com.thinkive.android.trade_bz.a_stock.activity;

import android.os.Bundle;
import android.view.View;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.fragment.RadioButtonFragment2;
import com.thinkive.android.trade_bz.a_stock.fragment.OneKeyFragment;
import com.thinkive.android.trade_bz.a_stock.fragment.OneKeyMoneyFragment;
import com.thinkive.android.trade_bz.others.tools.TradeTools;

/**
 *   一键归集的Activity
 * @author 张雪梅
 * @company Thinkive
 * @date 15/7/14
 */
public class OneKeyActivity extends AbsNavBarActivity {
    private RadioButtonFragment2 mFragment = null;

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
        //隐藏Activity的标题栏
        setTitleBarVisible(View.GONE);
    }
    @Override
    protected void initData() {
        OneKeyFragment fragment1 = new OneKeyFragment();
        fragment1.setName(this.getResources().getString(R.string.one_key_title));

        OneKeyMoneyFragment fragment2 = new OneKeyMoneyFragment();
        fragment2.setName(this.getResources().getString(R.string.one_key_fragment_title));

        mFragment = new RadioButtonFragment2(fragment1, fragment2);
    }
}
