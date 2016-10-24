package com.thinkive.android.trade_bz.a_stock.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;

import com.thinkive.android.trade_bz.R;

import me.yokeyword.fragmentation_swipeback.SwipeBackActivity;

/**
 * 描述：本类实现了Activity之间切换时使用了动画效果，主要效果有LEFT_RIGHT_ANIM左右动画，
 * TOP_BOTTOM_ANIM上下动画和ALPHA_FADE_ANIM透明度动画，在进入和退出需要使用哪种动画
 * 只需要在执行startActivity或者finish方法之前调用setAnimType方法先设置动画方法就是可了。
 *
 * @author 王志鸿
 * @version 1.0
 * @corporation 深圳市思迪信息科技有限公司
 * @date 2015/6/2
 */
public abstract class AbsBasicActivity extends SwipeBackActivity {
    /**
     * 左右动画
     */
    public static final int LEFT_RIGHT_ANIM = 0;

    /**
     * 上下动画
     */
    public static final int TOP_BOTTOM_ANIM = 1;

    /**
     * 透明度渐变动画
     */
    public static final int ALPHA_FADE_ANIM = 2;

    /**
     * 用于存储动画类型
     */
    private static int mAnimType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        // 根据savedInstanceState是否为空判断Activity是第一次启动，还是从后台重启
//        if (savedInstanceState != null) { // 如果是从后台重启，那么重启整个App
//            // 必须先关闭，否则重启App时还是从后台重启Activity的效果
//            finish();
//            // 重新启动App
//            final Intent intent = getPackageManager().getLaunchIntentForPackage(getPackageName());
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            startActivity(intent);
//        }
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        execAnimOnStartActivity();
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
        execAnimOnStartActivity();
    }

    /**
     * 在启动Activity的时候，应该执行的动画
     */
    private void execAnimOnStartActivity() {
        switch (mAnimType) {
            case LEFT_RIGHT_ANIM:
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
                break;
            case TOP_BOTTOM_ANIM:
                overridePendingTransition(R.anim.bottom_in, R.anim.top_out);
                break;
            case ALPHA_FADE_ANIM:
                overridePendingTransition(R.anim.fade_in, R.anim.fade);
                break;
        }
    }

    @Override
    public void finish() {
        super.finish();
        switch (mAnimType) {
            case LEFT_RIGHT_ANIM:
                overridePendingTransition(R.anim.left_in, R.anim.right_out);
                break;
            case TOP_BOTTOM_ANIM:
                overridePendingTransition(R.anim.top_in, R.anim.bottom_out);
                break;
            case ALPHA_FADE_ANIM:
                overridePendingTransition(R.anim.fade, R.anim.fade_out);
                break;
        }

    }

    /**
     * 设置activity进入或者退出时的动画类型
     * 该方法在执行startActivity或finish方法时调用
     *
     * @param animType
     *         动画类型，目前主要以下三个值：
     *         LEFT_RIGHT_ANIM 左右动画
     *         TOP_BOTTOM_ANIM 上下动画
     *         ALPHA_FADE_ANIM 透明度渐变动画
     */
    public void setAnimType(int animType) {
        this.mAnimType = animType;
    }

    /**
     * 重写此方法目的是app不随系统字体变化
     *
     * @return Resources
     */
    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        Configuration config = new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config, res.getDisplayMetrics());
        return res;
    }

    @Override
    protected void setListeners() {
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void findViews() {
    }


    @Override
    protected void initViews() {

    }
}
