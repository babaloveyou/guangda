package com.thinkive.android.trade_bz.a_stock.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.fragment.CreditTradeFragment;
import com.thinkive.android.trade_bz.a_stock.fragment.NewStockWebFragment;
import com.thinkive.android.trade_bz.a_stock.fragment.NormalTradeFragment;

/**
 * Created by Administrator on 2016/11/9.
 */
public class NewStockWebActivity  extends AppCompatActivity  {
    private String mSourceModule;
    private NewStockWebFragment webFragment = null;
    public static final String NORMAL_URL= "http://10.84.132.63:9999/m/trade/views/shares/sharesIndex.html?stock_credit_flag=stock";
    public static final String CREDIT_URL= "http://10.84.132.63:9999/m/trade/views/shares/sharesIndex.html?stock_credit_flag=credit";
    private String mLoginType;
    public final static String NORMAL = "normal";
    public final static String CREDIT = "credit";
    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newstockweb);
        Intent intent = getIntent();
        if (intent != null) {
            mLoginType = intent.getStringExtra("loginType");
        }

        if (webFragment == null) {
            if (NORMAL.equals(mLoginType)) {
                webFragment = NormalTradeFragment.getNewStockWebFragment();
            }
            if (CREDIT.equals(mLoginType)) {
                webFragment = CreditTradeFragment.getNewStockWebFragment();
                webFragment.setUrl(CREDIT_URL);
            }
        }

        mFragmentManager = getSupportFragmentManager();
        mFragmentManager.beginTransaction().add(R.id.fl_container, webFragment).commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
