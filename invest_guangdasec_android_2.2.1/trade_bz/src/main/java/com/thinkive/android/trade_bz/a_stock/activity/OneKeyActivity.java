package com.thinkive.android.trade_bz.a_stock.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.fragment.OneKeyParentFragment;

/**
 *   一键归集的Activity
 * @author 张雪梅
 * @company Thinkive
 * @date 15/7/14
 */
public class OneKeyActivity extends AbsNavBarActivity {
    /**
     * 交易登录碎片，用于显示交易登录的界面元素
     */
    private OneKeyParentFragment mParentFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitleBarVisible(View.GONE);
        initData();
        initViews();
    }

    @Override
    protected void initData() {
        mParentFragment = new OneKeyParentFragment();

    }

    @Override
    protected void initViews() {
        super.initViews();
        setBackBtnVisibility(View.VISIBLE);
        mParentFragment.setArguments(getIntent().getExtras());
        replaceFragment(R.id.fl_container, mParentFragment);
    }
    public static void hideSystemKeyBoard(Activity activity) {
        // 收起系统键盘
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(activity.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(activity.getWindow().getDecorView().getWindowToken(), 0);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        hideSystemKeyBoard(this);
    }


}
