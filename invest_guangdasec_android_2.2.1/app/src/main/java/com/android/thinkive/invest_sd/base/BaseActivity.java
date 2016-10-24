package com.android.thinkive.invest_sd.base;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.thinkive.framework.compatible.TKFragmentActivity;
import com.android.thinkive.framework.util.CommonUtil;
import com.android.thinkive.framework.util.Log;
import com.android.thinkive.invest_sd.R;
import com.umeng.analytics.MobclickAgent;

/**
 *
 * 基础activity  设置主题 动画等
 *
 * Created by zhuduanchang on 2015/6/26.
 */
public abstract class BaseActivity extends TKFragmentActivity {
    View parentView;
    private final static int INTERVAL_TIME = 1000;
    private final static int SHOW_VERSION_TIMES = 3;
    private int mClickTimes=0;
    private long mFirstClickTime = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        parentView = getWindow().getDecorView().findViewById(android.R.id.content);
//        parentView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (mClickTimes < SHOW_VERSION_TIMES) {
//                    long secondClickTime = System.currentTimeMillis();
//                    if (secondClickTime - mFirstClickTime < INTERVAL_TIME || mClickTimes == 0){
//
//                        mFirstClickTime = secondClickTime;
//                    }else {
//                        mClickTimes = 0;
//                        mFirstClickTime = 0;
//                        return;
//                    }
//                    mClickTimes++;
//                } else {
//                    Toast.makeText(BaseActivity.this, "当前framework版本号："+CommonUtil.getThinkiveFrameworkVersionName(), Toast.LENGTH_SHORT).show();
//
//                    mFirstClickTime = 0;
//                    mClickTimes = 0;
//                }
//            }
//        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("activity onResume()");
        MobclickAgent.onResume(this);
        setTheme();
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.overridePendingTransition(R.anim.in_from_left, R.anim.out_to_right);
    }

    /**
     * 设置主题 需要在initView之后调用
     */
    protected abstract void setTheme();
}
