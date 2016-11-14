package com.thinkive.android.trade_bz.views;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * 自定义ViewPager 实现监听不拦截
 *
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/6/29
 */

public class ChildViewPager extends ViewPager {
    private boolean isSelf = true;
    /**
     * 父布局
     */
    ScrollView mParentScrollView;
    private int mStartX;
    private int mStartY;
    private int mCurrentX;
    private int mCurrentY;
    //    int mLastMotionY;
    //    int mLastMotionX;

    /**
     * 获取父布局对象
     *
     * @return
     */
    public ScrollView getmParentScrollView() {
        return mParentScrollView;
    }

    /**
     * 设置父布局
     *
     * @param mParentScrollView
     */
    public void setmParentScrollView(ScrollView mParentScrollView) {
        this.mParentScrollView = mParentScrollView;
    }

    public ChildViewPager(Context context) {
        super(context);
    }

    public ChildViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
//
//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        switch (ev.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                mStartX = (int) ev.getX();
//                mStartY = (int) ev.getY();
//                break;
//            case MotionEvent.ACTION_MOVE:
//                mCurrentX = (int) ev.getX();
//                mCurrentY = (int) ev.getY();
//                if ((mCurrentY - mStartY) > 1.5*(mCurrentX - mStartX)) {
//                    isSelf = false;
//                } else {
//                    isSelf = true;
//                }
//                mStartX = mCurrentX;
//                mStartY = mCurrentY;
//                break;
//            case MotionEvent.ACTION_UP:
//                break;
//        }
//        //让父类不拦截触摸事件
//        if (mParentScrollView != null)
//            mParentScrollView.requestDisallowInterceptTouchEvent(isSelf);
//        return super.dispatchTouchEvent(ev);
//    }




   /* *//** 触摸时按下的点 **//*
    PointF downP = new PointF();
    *//** 触摸时当前的点 **//*
    PointF curP = new PointF();
    OnSingleTouchListener onSingleTouchListener;

    public ChildViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ChildViewPager(Context context) {
        super(context);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        //当拦截触摸事件到达此位置的时候，返回true，
        //说明将onTouch拦截在此控件，进而执行此控件的onTouchEvent
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        //每次进行onTouch事件都记录当前的按下的坐标
        curP.x = arg0.getX();
        curP.y = arg0.getY();

        if(arg0.getAction() == MotionEvent.ACTION_DOWN){
            //记录按下时候的坐标
            //切记不可用 downP = curP ，这样在改变curP的时候，downP也会改变
            downP.x = arg0.getX();
            downP.y = arg0.getY();
            //此句代码是为了通知他的父ViewPager现在进行的是本控件的操作，不要对我的操作进行干扰
            getParent().requestDisallowInterceptTouchEvent(true);
        }
        if(arg0.getAction() == MotionEvent.ACTION_MOVE){
            //此句代码是为了通知他的父ViewPager现在进行的是本控件的操作，不要对我的操作进行干扰
            getParent().requestDisallowInterceptTouchEvent(true);
        }

        if(arg0.getAction() == MotionEvent.ACTION_UP){
            //在up时判断是否按下和松手的坐标为一个点
            //如果是一个点，将执行点击事件，这是我自己写的点击事件，而不是onclick
            if(downP.x==curP.x && downP.y==curP.y){
                onSingleTouch();
                return true;
            }
        }

        return super.onTouchEvent(arg0);
    }

    *//**
     * 单击
     *//*
    public void onSingleTouch() {
        if (onSingleTouchListener!= null) {

            onSingleTouchListener.onSingleTouch();
        }
    }

    *//**
     * 创建点击事件接口
     * @author wanpg
     *
     *//*
    public interface OnSingleTouchListener {
        public void onSingleTouch();
    }

    public void setOnSingleTouchListener(OnSingleTouchListener onSingleTouchListener) {
        this.onSingleTouchListener = onSingleTouchListener;
    }*/

}