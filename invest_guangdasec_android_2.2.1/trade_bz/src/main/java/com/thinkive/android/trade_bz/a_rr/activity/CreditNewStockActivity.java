package com.thinkive.android.trade_bz.a_rr.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_rr.fragment.CreditNewStockFragment;

/**
 * Created by Administrator on 2016/12/7.
 */

public class CreditNewStockActivity extends AppCompatActivity {
    private FragmentManager mFragmentManager;
    private CreditNewStockFragment mCreditNewStockFragment = new CreditNewStockFragment();

    @Override
    public void onBackPressed() {
        mCreditNewStockFragment.sendMessage50107();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newstockweb);
        mFragmentManager = getSupportFragmentManager();
        mFragmentManager.beginTransaction().add(R.id.fl_container, mCreditNewStockFragment).commit();
    }
}


