package com.android.thinkive.invest_sd.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * 此listview高度自适应
 * Created by WELL on 2015/7/3.
 */
public class MyListView extends ListView {
    public MyListView(Context context) {
        super(context);
    }
    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public MyListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        int heightSpec;
//        if (getLayoutParams().height == LayoutParams.WRAP_CONTENT) {
//            heightSpec = MeasureSpec.makeMeasureSpec(
//                    Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
//        } else {
//            heightSpec = heightMeasureSpec;
//        }
//        super.onMeasure(widthMeasureSpec, heightSpec);
//    }
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
//                MeasureSpec.AT_MOST);
//        super.onMeasure(widthMeasureSpec, expandSpec);
//    }shoujimeiy lianjshang yunxingchengx三shouji

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