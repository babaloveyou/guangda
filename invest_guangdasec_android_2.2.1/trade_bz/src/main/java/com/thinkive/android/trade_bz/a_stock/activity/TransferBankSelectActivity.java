package com.thinkive.android.trade_bz.a_stock.activity;

import android.os.Bundle;
import android.view.View;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.fragment.TransferBankSelectFragment;

/**
 *   银证转账--转账查询
 * @author 张雪梅
 * @company Thinkive
 * @date 15/7/24
 */
public class TransferBankSelectActivity extends AbsNavBarActivity {

    private TransferBankSelectFragment mFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initViews();
    }

    @Override
    protected void initViews() {
        addFragment(R.id.fl_container, mFragment);
        //左边按钮
        setTitleText(R.string.bank_select_title);
        setBackBtnVisibility(View.VISIBLE);
    }

    @Override
    protected void initData() {
        mFragment = new TransferBankSelectFragment();
        mFragment.setArguments(getIntent().getExtras());
    }
}
