package com.android.thinkive.invest_sd.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;

/**
 * Created by xuemei on 2015/9/18.
 * listview嵌套于ScrollView时，动态设置其高度，拦截其滑动效果
 */
public class ListViewInScrollView extends ListView {

    /**
     * 父布局
     */
    ScrollView mParentScrollView;

    private int mTouchSlop;

    public ListViewInScrollView(Context context) {
        super(context);
    }

    public ListViewInScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    /**
     * 获取父布局对象
     * @return
     */
    public ScrollView getmParentScrollView() {
        return mParentScrollView;
    }

    /**
     * 设置父布局
     * @param mParentScrollView
     */
    public void setmParentScrollView(ScrollView mParentScrollView) {
        this.mParentScrollView = mParentScrollView;
    }

    private void init(Context context) {
        mTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

    /**
     * 不允许滑动
     * @param ev
     * @return
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //让父类拦截子类监听
                setParentScrollAble(true);
            case MotionEvent.ACTION_MOVE:

                break;
            case MotionEvent.ACTION_UP:

            case MotionEvent.ACTION_CANCEL:
                setParentScrollAble(true);
                break;
            default:
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    /**
     * @param flag
     */
    public void setParentScrollAble(boolean flag) {
        mParentScrollView.requestDisallowInterceptTouchEvent(!flag);
    }

    /**
     * 动态设置listview的高度
     * @param listView
     */
    public static void setListViewHeight(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
            // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);
            // 计算子项View 的宽高
            listItem.measure(0, 0);
            // 统计所有子项的总高度
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        listView.setLayoutParams(params);
    }
}
