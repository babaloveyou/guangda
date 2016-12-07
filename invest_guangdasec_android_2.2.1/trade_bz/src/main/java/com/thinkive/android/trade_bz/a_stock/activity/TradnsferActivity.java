package com.thinkive.android.trade_bz.a_stock.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.android.thinkive.framework.fragment.BaseWebFragment;
import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_rr.fragment.CreditTransferFragment;
import com.thinkive.android.trade_bz.a_stock.fragment.NormalTransferFragment;

/**
 * Created by Administrator on 2016/12/5.
 */
public class TradnsferActivity extends AppCompatActivity {
    private String mSourceModule;
    private BaseWebFragment webFragment = null;
    private String mLoginType;
    public final static String NORMAL = "normal";
    public final static String CREDIT = "credit";
    private FragmentManager mFragmentManager;
    private static NormalTransferFragment sNormalTransferFragment = new NormalTransferFragment();
    private static CreditTransferFragment sCreditTransferFragment = new CreditTransferFragment();

    public static NormalTransferFragment getNormalFragment() {
        return sNormalTransferFragment;
    }

    public static CreditTransferFragment getCreditFragment() {
        return sCreditTransferFragment;
    }

    @Override
    public void onBackPressed() {
        if (webFragment == sNormalTransferFragment) {
            sNormalTransferFragment.sendMessage50107();
        }if(webFragment == sCreditTransferFragment) {
            sCreditTransferFragment.sendMessage50107();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yzzz);
        Intent intent = getIntent();
        if (intent != null) {
            mLoginType = intent.getStringExtra("loginType");
        }
        if (NORMAL.equals(mLoginType)) {
            webFragment = sNormalTransferFragment;
        }
        if (CREDIT.equals(mLoginType)) {
            webFragment = sCreditTransferFragment;
        }
        mFragmentManager = getSupportFragmentManager();
        mFragmentManager.beginTransaction().replace(R.id.fl_container, webFragment).commit();
    }

}
