package com.thinkive.android.trade_bz.views;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.widget.FrameLayout;

/**
 * 实现左右滑动手势监听的FrameLayout
 * 左右滑动判断标准：横向滑动距离大于{@link #mSlideXStandard}，纵向滑动距离小于{@link #mSlideYStandard}
 *
 * @author 王志鸿
 * @corporation thinkive
 * @date 2015-09-3
 */
public class HorizontalSlideLinearLayout extends FrameLayout {

    /**
     * 纵向滑动距离低于本变量，才算有效，才会触发横向滑动效果
     */
    private double mSlideYStandard = 100;

    /**
     * 横向滑动超过本变量，才算有效，才会触发横向滑动效果
     */
    private double mSlideXStandard = 240;

    /**
     * 按下动作的X坐标
     */
    private int mDownX;
    /**
     * 按下动作的Y坐标
     */
    private int mDownY;
    /**
     * 横向移动距离
     */
    private int mMoveX;
    private boolean mLeftSlideable;
    private boolean mRightSlideable;
    /**
     * 实现滑动接口的实例
     */
    private OnSlideListener mOnSlideListener;

    public HorizontalSlideLinearLayout(Context context) {
        this(context, null);
    }

    public HorizontalSlideLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 初始化滑动手势判断标准，如果没有执行此方法，则滑动判断临界值取固定值；
     * 执行此方法后，滑动临界判断变量将会根据屏幕尺寸进行取值。
     * @param activity 外部调用环境，用来取得屏幕尺寸
     */
    public void initslideStandard(AppCompatActivity activity) {
        // 获取屏幕宽度，将滑动有效判断标准值mDegreeSlideStandard设为屏幕宽度的0.3
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        mSlideXStandard = displayMetrics.widthPixels * 0.3;
        mSlideYStandard = displayMetrics.heightPixels * 0.1;
        mLeftSlideable = true;
        mRightSlideable = true;
    }

//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        switch (ev.getAction()) { // 判断触摸事件种类
//            case MotionEvent.ACTION_DOWN: // 按下
//                mDownX = (int) ev.getRawX(); // 这里使用的getRawX()、getRawY()方法获取的是相对于屏幕的坐标
//                mDownY = (int) ev.getRawY(); // 而getX()、getY()方法获取的是相对于控件的坐标
//                break;
//            case MotionEvent.ACTION_UP: // 抬起
//                mMoveX = (int) ev.getRawX();
//                // 判断本次滑动是否有效
//                if (Math.abs(mMoveX - mDownX) > mSlideXStandard
//                        && Math.abs((int) ev.getRawY() - mDownY) < mSlideYStandard) { // 如果滑动有效
//                    if (mOnSlideListener!=null) { // 确定滑动监听器不为空
//                        if (mMoveX > mDownX) { // 如果是从左向右滑动
//                            if (mRightSlideable) {
//                                mOnSlideListener.onToRightSlide(this);
//                            }
//                        } else { // 如果是从右向左滑动
//                            if (mLeftSlideable) {
//                                mOnSlideListener.onToLeftSlide(this);
//                            }
//                        }
//                    }
//                }
//                break;
//        }
//        super.dispatchTouchEvent(ev);
//        return true;
//    }



    /**
     * 设置滑动监听器
     * @param slideListener 滑动监听器
     */
    public void setOnSlideListener(OnSlideListener slideListener) {
        mOnSlideListener = slideListener;
    }

    /**
     * 获取本类对象是否可以向右滑动
     * @return 本类对象是否可以向右滑动
     */
    public boolean isRightSlideable() {
        return mRightSlideable;
    }

    /**
     * 设置本类对象是否可以向右滑动
      * @param rightSlideable 本类对象是否可以向右滑动
     */
    public void setRightSlideable(boolean rightSlideable) {
        mRightSlideable = rightSlideable;
    }

    /**
     * 获取本类对象是否可以向左滑动
     * @return 本类对象是否可以向左滑动
     */
    public boolean isLeftSlideable() {
        return mLeftSlideable;
    }
    /**
     * 设置本类对象是否可以向左滑动
     * @param leftSlideable 本类对象是否可以向左滑动
     */
    public void setLeftSlideable(boolean leftSlideable) {
        mLeftSlideable = leftSlideable;
    }

    /**
     * 横向滑动监听回调接口
     */
    public interface OnSlideListener {
        /**
         * 从右向左滑动监听
         * @param horizontalSlideLinearLayout 被滑动的本类对象
         */
        void onToLeftSlide(HorizontalSlideLinearLayout horizontalSlideLinearLayout);

        /**
         * 从左向右滑动监听
         * @param horizontalSlideLinearLayout 被滑动的本类对象
         */
        void onToRightSlide(HorizontalSlideLinearLayout horizontalSlideLinearLayout);
    }
}
