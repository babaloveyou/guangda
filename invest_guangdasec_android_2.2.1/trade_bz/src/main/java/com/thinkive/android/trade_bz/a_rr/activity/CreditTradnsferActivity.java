package com.thinkive.android.trade_bz.a_rr.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_rr.fragment.CreditTransferFragment;

/**
 * Created by Administrator on 2016/12/7.
 */
public class CreditTradnsferActivity extends AppCompatActivity {
    private FragmentManager mFragmentManager;
    private CreditTransferFragment sCreditTransferFragment = new CreditTransferFragment();


    @Override
    public void onBackPressed() {
        sCreditTransferFragment.sendMessage50107();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yzzz);
        mFragmentManager = getSupportFragmentManager();
        mFragmentManager.beginTransaction().replace(R.id.fl_container, sCreditTransferFragment).commit();
    }

}
