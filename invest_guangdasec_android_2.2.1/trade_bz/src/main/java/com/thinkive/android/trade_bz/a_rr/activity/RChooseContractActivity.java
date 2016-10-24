package com.thinkive.android.trade_bz.a_rr.activity;

import android.os.Bundle;
import android.view.View;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_rr.fragment.RChooseContractFragment;
import com.thinkive.android.trade_bz.a_stock.activity.AbsNavBarActivity;
/**
 * 融资融券_指定合约_选择合约（多选）
 * 注：ONE_TYPE  只允许选择同类证券  ANY_ONE 可任意选择证券
 * @author 张雪梅
 * @company ThinkIve
 * @date 2016/7/20
 */
public class RChooseContractActivity extends AbsNavBarActivity {

    private RChooseContractFragment mFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initViews();
    }

    @Override
    protected void initData() {
        mFragment = new RChooseContractFragment();
        addFragment(R.id.fl_container, mFragment);
        mFragment.setArguments(getIntent().getExtras());
    }

    @Override
    protected void initViews() {
        setTitleText(R.string.r_choose_contract_title);//设置标题
        setBackBtnVisibility(View.VISIBLE);//显示返回按钮
        setLogoutBtnVisibility(View.VISIBLE);//显示右侧按钮
        setLogoutBtnText(R.string.r_title_bar_save);
    }

    /**
     * 标题栏保存点击事件
     */
    @Override
    protected void onLogouBtEvent() {
        mFragment.onClickSave();
    }

    /**
     * 取消按钮点击事件
     */
    @Override
    protected void onBackEvent() {
       mFragment.onClickCancel();
    }
}

