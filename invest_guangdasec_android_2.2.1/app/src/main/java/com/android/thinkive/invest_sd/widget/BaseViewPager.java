package com.android.thinkive.invest_sd.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by zhuduanchang on 2015/6/19.
 */
public class BaseViewPager extends ViewPager {
    
	private boolean isCanScroll = true;
	
	public void setCanScroll(boolean scroll){
		this.isCanScroll = scroll;
	}
	
	public boolean isCanScroll(){
		return this.isCanScroll;
	}
	
	public BaseViewPager(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	public BaseViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean onInterceptTouchEvent(MotionEvent arg0) {
		// TODO Auto-generated method stub
		if (!isCanScroll) {
			return false;
		}
		return super.onInterceptTouchEvent(arg0);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent arg0) {
		// TODO Auto-generated method stub
		if (!isCanScroll) {
			return false;
		}
		return super.onTouchEvent(arg0);
	}
	
	@SuppressLint("NewApi")
	@Override
	public boolean canScrollHorizontally(int direction) {
		// TODO Auto-generated method stub
		if (!isCanScroll) {
			return false;
		}
		return super.canScrollHorizontally(direction);
	}
	
}
