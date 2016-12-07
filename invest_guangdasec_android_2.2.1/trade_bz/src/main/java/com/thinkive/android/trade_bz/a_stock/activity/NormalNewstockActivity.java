package com.thinkive.android.trade_bz.a_stock.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.fragment.NormalNewStockFragment;

/**
 * Created by Administrator on 2016/12/7.
 */

public class NormalNewstockActivity extends AppCompatActivity {
    private FragmentManager mFragmentManager;
    private NormalNewStockFragment mNormalNewStockFragment = new NormalNewStockFragment();

    @Override
    public void onBackPressed() {
        mNormalNewStockFragment.sendMessage50107();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newstockweb);
        mFragmentManager = getSupportFragmentManager();
        mFragmentManager.beginTransaction().add(R.id.fl_container, mNormalNewStockFragment).commit();
    }

}

