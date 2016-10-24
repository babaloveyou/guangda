package com.thinkive.android.trade_bz.a_rr.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_rr.fragment.RCashReturnFragment;
import com.thinkive.android.trade_bz.a_stock.activity.AbsNavBarActivity;

/**
 * 融资融券--现金还款（直接还款）
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/7/20
 */
public class RCashReturnActivity extends AbsNavBarActivity {

    private RCashReturnFragment mFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initViews();
    }

    @Override
    protected void initViews() {
        addFragment(R.id.fl_container, mFragment);
        setTitleText(R.string.r_order_two);
        setBackBtnVisibility(View.VISIBLE);
        setLogoImVisibility(View.VISIBLE);
        setLogoImBackground(R.drawable.seach);
    }

    @Override
    protected void initData() {
        mFragment = new RCashReturnFragment();
    }
    @Override
    protected void onLogouImEvent() {
        //点击右侧按钮所执行的操作
        Intent intent = new Intent(this, RSelectPropertyActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("checkId", R.id.rb_r_liability);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
