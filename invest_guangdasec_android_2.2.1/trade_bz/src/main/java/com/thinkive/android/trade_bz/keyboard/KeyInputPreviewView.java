package com.thinkive.android.trade_bz.keyboard;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.android.thinkive.framework.util.ScreenUtil;

/**
 * 描述：键盘按键输入预览视图
 * 预览视图的各部位比例关系：上半部分跟下半部分的高度比例为6:5，宽度比例为5:3
 *
 * Created by zhuduanchang
 */
public final class KeyInputPreviewView extends View {
    private int mTextColor = Color.WHITE; //字体颜色
    private String mText = "";
    private int mLargeTextSize = 40;  //大字的字体大小
    private int mSmallTextSize = 20;  //小字的字体大小

    public KeyInputPreviewView(final Context context) {
        this(context, null, 0);
    }

    public KeyInputPreviewView(final Context context, final AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public KeyInputPreviewView(final Context context, final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(final Canvas canvas) {
        int x, y;
        Rect textBounds = new Rect();
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(mTextColor);
        paint.setTextSize(ScreenUtil.spToPx(getContext(), mLargeTextSize));
        paint.getTextBounds(mText, 0, mText.length(), textBounds);
        x = (getWidth() - textBounds.width()) / 2;
        y = getHeight() * 6 / 11 - (getHeight() * 6 / 11 - textBounds.height()) / 2;
        canvas.drawText(mText, x, y, paint);

        paint.setTextSize(ScreenUtil.spToPx(getContext(), mSmallTextSize));
        paint.getTextBounds(mText, 0, mText.length(), textBounds);
        x = (getWidth() - textBounds.width()) / 2;
        y = getHeight() - (getHeight() * 5 / 11 - textBounds.height()) / 2;
        canvas.drawText(mText, x, y, paint);
    }

    public void setTextColor(final int textColor) {
        mTextColor = textColor;
    }

    public void setText(final String text) {
        mText = text;
    }

    public void setLargeTextSize(int largeTextSize) {
        mLargeTextSize = largeTextSize;
    }

    public void setSmallTextSize(int smallTextSize) {
        mSmallTextSize = smallTextSize;
    }
}
