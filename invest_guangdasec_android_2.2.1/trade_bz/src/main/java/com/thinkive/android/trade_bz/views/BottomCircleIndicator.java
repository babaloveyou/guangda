package com.thinkive.android.trade_bz.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.thinkive.android.trade_bz.R;

/**
 * Created by Administrator on 2016/10/17.
 */

public class BottomCircleIndicator extends LinearLayout implements ViewPager.OnPageChangeListener {
    private int mCircleNum;
    private int mCurrentEnableCircle;

    public BottomCircleIndicator(Context context) {
        super(context);
    }

    public BottomCircleIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        int resourceId = -1;
        TypedArray typedArray = context.obtainStyledAttributes(attrs,
                R.styleable.BottomCircleIndicator);
        int N = typedArray.getIndexCount();
        for (int i = 0; i < N; i++) {
            int attr = typedArray.getIndex(i);
            if (attr == R.styleable.BottomCircleIndicator_circleNum) {
                resourceId = typedArray.getResourceId(
                        R.styleable.BottomCircleIndicator_circleNum, 0);
               setCircleNum(resourceId);
            }
        }
        typedArray.recycle();
        addCircle(context);
    }

    private void addCircle(Context context) {
        for (int i = 0; i < mCircleNum; i++) {
            ImageView imageView = new ImageView(context);
            imageView.setBackgroundResource(R.drawable.little_circle_bg);
            LayoutParams layoutparams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            if (i != mCircleNum - 1) {
                layoutparams.rightMargin = 15;
            }
            imageView.setLayoutParams(layoutparams);
            if (i == mCurrentEnableCircle) {
                imageView.setEnabled(true);
            } else {
                imageView.setEnabled(false);
            }
            addView(imageView);
        }

    }

    public void setCircleNum(int circleNum) {
        mCircleNum = circleNum;
        invalidate();
    }

    public void setCurrentEnableCircle(int circleNum) {
        mCurrentEnableCircle = circleNum;
        invalidate();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        for(int i=0;i<getChildCount();i++) {
            if (i == position) {
                getChildAt(i).setEnabled(true);
            } else {
                getChildAt(i).setEnabled(false);
            }
        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
