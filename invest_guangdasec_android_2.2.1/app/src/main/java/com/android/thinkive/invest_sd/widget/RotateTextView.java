package com.android.thinkive.invest_sd.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.TextView;

import com.android.thinkive.invest_sd.R;


/**
 * Created by liqi on 2016/7/12.
 */
public class RotateTextView extends TextView{
    private static final int DEFAULT_DEGREES = 0;
    private int mDegrees;
    public RotateTextView(Context context) {
        super(context,null);
    }

    public RotateTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setGravity(Gravity.CENTER);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RotateTextView);
        mDegrees = a.getDimensionPixelSize(R.styleable.RotateTextView_degree, DEFAULT_DEGREES);
        a.recycle();
    }

    public RotateTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getMeasuredWidth(), getMeasuredWidth());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.save();
        canvas.translate(getCompoundPaddingLeft(), getExtendedPaddingTop());
        canvas.rotate(360-mDegrees, this.getWidth() / 2f, this.getHeight() / 2f);
        super.onDraw(canvas);
        canvas.restore();
    }

    public void setDegrees(int degrees) {
        mDegrees = degrees;
    }
}
