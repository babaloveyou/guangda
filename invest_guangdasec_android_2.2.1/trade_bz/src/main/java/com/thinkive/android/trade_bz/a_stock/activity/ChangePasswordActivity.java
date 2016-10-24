package com.thinkive.android.trade_bz.a_stock.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.others.tools.TradeTools;
import com.thinkive.android.trade_bz.a_stock.fragment.ChangePasswordFragment;

/**
 * 修改密码的Activity
 * @author 张雪梅
 * @company Thinkive
 * @date 15/7/24
 */
public class ChangePasswordActivity extends AbsNavBarActivity {

    private ChangePasswordFragment mFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 发消息，让登录页关闭
        TradeTools.sendMsgToLoginForSubmitFinish(this);
        initData();
        initViews();
        Log.d("TAG","Activity  onCreate ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("TAG","Activity onResume  ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("TAG","Activity onPause  ");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d("TAG","Activity  onNewIntent ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("TAG","Activity  onDestroy ");
    }

    @Override
    protected void initViews() {
        addFragment(R.id.fl_container, mFragment);
        setBackBtnVisibility(View.VISIBLE);
    }

    @Override
    protected void initData() {
        mFragment = new ChangePasswordFragment();
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            mFragment.setArguments(bundle);
            String useType =  bundle.getString("userType");
            if(useType != null && useType.equals("1")){
                setTitleText(R.string.r_hold_pager28);
            }else if(useType != null && useType.equals("2")){
                setTitleText(R.string.option_main28);
            }else{
                setTitleText(R.string.change_pwd_title);
            }
        }
    }
}
