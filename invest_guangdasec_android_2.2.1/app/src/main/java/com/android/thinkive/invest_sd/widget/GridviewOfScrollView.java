package com.android.thinkive.invest_sd.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by xiangfan on 2015/9/28.
 */
public class GridviewOfScrollView extends GridView {
    public GridviewOfScrollView(Context context) {
        super(context);
    }
    public GridviewOfScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public GridviewOfScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int heightSpec;
        if (getLayoutParams().height == LayoutParams.WRAP_CONTENT) {
            heightSpec = MeasureSpec.makeMeasureSpec(
                    Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        } else {
            heightSpec = heightMeasureSpec;
        }
        super.onMeasure(widthMeasureSpec, heightSpec);
    }
}
