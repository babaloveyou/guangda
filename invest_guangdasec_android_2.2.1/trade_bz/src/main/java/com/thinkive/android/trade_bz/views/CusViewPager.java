package com.thinkive.android.trade_bz.views;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Administrator on 2016/10/25.
 */

public class CusViewPager extends ViewPager {
    private boolean mNoFocus = false; //if true, keep View don't move
    public CusViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public CusViewPager(Context context){
        this(context,null);
    }

    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (mNoFocus) {
            return false;
        }
        return super.onInterceptTouchEvent(event);
    }

    public void setNoFocus(boolean b){
        mNoFocus = b;
    }
}
