package com.thinkive.android.trade_bz.a_stock.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.fragment.RadioButtonFragment2;
import com.thinkive.android.trade_bz.a_stock.fragment.TransferBankInFragment;
import com.thinkive.android.trade_bz.a_stock.fragment.TransferBankOutFragment;
import com.thinkive.android.trade_bz.others.tools.TradeLoginManager;
import com.thinkive.android.trade_bz.others.tools.TradeTools;

/**
 *   银证转账的Activity
 * @author 张雪梅
 * @company Thinkive
 * @date 15/7/10
 */
public class TransferBanktActivity extends AbsNavBarActivity {
    private RadioButtonFragment2 mFragment = null;

    private View.OnClickListener mRightClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            onClickRightTv();
        }
    };

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

        String fragmentRightText = this.getResources().getString(R.string.bank_title_right);
        Bundle bundle = mFragment.getArguments() == null ? new Bundle() : mFragment.getArguments();
        bundle.putString("fragmentRightText", fragmentRightText);
        mFragment.setArguments(bundle);
        mFragment.setRightListener(mRightClickListener);
    }
    @Override
    protected void initData() {
        getIntent().getExtras();
        TransferBankInFragment fragment1 = new TransferBankInFragment();
        TransferBankOutFragment fragment2 = new TransferBankOutFragment();
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            String userType = bundle.getString("userType");
            if (userType != null && userType.equals(TradeLoginManager.LOGIN_TYPE_NORMAL)) {
                fragment1.setName(this.getResources().getString(R.string.bank_radio_in));
                fragment2.setName(this.getResources().getString(R.string.bank_radio_out));
            }else if (userType != null && userType.equals(TradeLoginManager.LOGIN_TYPE_CREDIT)) {
                fragment1.setName(this.getResources().getString(R.string.r_bank_radio_in));
                fragment2.setName(this.getResources().getString(R.string.r_bank_radio_out));
            }else if (userType != null && userType.equals(TradeLoginManager.LOGIN_TYPE_OPTION)) {
                fragment1.setName(this.getResources().getString(R.string.option_bank_radio_in));
                fragment2.setName(this.getResources().getString(R.string.option_bank_radio_out));
            }
        }else{
            fragment1.setName(this.getResources().getString(R.string.bank_radio_in));
            fragment2.setName(this.getResources().getString(R.string.bank_radio_out));
        }

        fragment1.setArguments(getIntent().getExtras());
        fragment2.setArguments(getIntent().getExtras());

        mFragment = new RadioButtonFragment2(fragment1, fragment2);
    }

    /**
     * 点击title栏右侧按钮
     */
    private void onClickRightTv(){
        Intent intent = new Intent(this,TransferBankSelectActivity.class);
        intent.putExtras(getIntent().getExtras());
        startActivity(intent);
    }
}
