package com.thinkive.android.trade_bz.a_stock.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.android.thinkive.framework.fragment.BaseWebFragment;
import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_rr.fragment.CreditNewStockFragment;
import com.thinkive.android.trade_bz.a_stock.fragment.NormalNewStockFragment;

/**
 * Created by Administrator on 2016/11/9.
 */
public class NewStockWebActivity extends AppCompatActivity {
    private String mSourceModule;
    private BaseWebFragment webFragment = null;
    private String mLoginType;
    public final static String NORMAL = "normal";
    public final static String CREDIT = "credit";
    private FragmentManager mFragmentManager;
    private static NormalNewStockFragment sNormalNewStockFragment = new NormalNewStockFragment();
    private static CreditNewStockFragment sCreditNewStockFragment = new CreditNewStockFragment();

    public static NormalNewStockFragment getNormalNewStockFragment() {
        return sNormalNewStockFragment;
    }

    public static CreditNewStockFragment getCreditNewStockFragment() {
        return sCreditNewStockFragment;
    }

    @Override
    public void onBackPressed() {
        if (webFragment == sNormalNewStockFragment) {
            sNormalNewStockFragment.sendMessage50107();
        }if(webFragment == sCreditNewStockFragment) {
            sCreditNewStockFragment.sendMessage50107();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newstockweb);
        Intent intent = getIntent();
        if (intent != null) {
            mLoginType = intent.getStringExtra("loginType");
        }
        if (NORMAL.equals(mLoginType)) {
            webFragment = sNormalNewStockFragment;
        }
        if (CREDIT.equals(mLoginType)) {
            webFragment = sCreditNewStockFragment;
        }
        mFragmentManager = getSupportFragmentManager();
        mFragmentManager.beginTransaction().replace(R.id.fl_container, webFragment).commit();
    }

}
