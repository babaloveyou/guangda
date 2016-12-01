
package com.thinkive.android.trade_bz.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.ScrollView;

import com.thinkive.android.trade_bz.views.slideexpandlistview.ActionSlideExpandableListView;

/**
 * 该类实现将listView嵌套到Scrollview 二者的焦点处理
 * 默认Scrollview不会拦截listView的焦点，
 * 只有当listView滑动到底部或顶部将其监听拦截
 * 这个类还有点击展开功能
 *
 * @author 张雪梅
 * @company Thinkive
 * @date 15/7/18
 */


public class listViewInScrollview extends ActionSlideExpandableListView {
    /**
     * 父布局
     */
    ScrollView mParentScrollView;
    private int scrollY = 0;
    private int mVisibleCount=-1;



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

    /*
    * 从adapter中传递下来的方法,点击可见的最后item时候要向上滑动
    * */
    public void updateLocation() {
        if (mVisibleCount != -1) {
            setSelection(getLastVisiblePosition() - mVisibleCount+1);
        }
        scrollTo(0,scrollY);
    }

    private int maxHeight;

    boolean mDisallow;
    private boolean mDragging = false;
    private float mLastMotionY;
    private float mStartMotionY = 0;
    private int mTouchSlop;

    public int getMaxHeight() {
        return maxHeight;
    }

    public void setMaxHeight(int maxHeight) {
        this.maxHeight = maxHeight;

    }

    public listViewInScrollview(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (maxHeight > -1) {
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(maxHeight, MeasureSpec.EXACTLY);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        mDisallow = true;
        if (mDragging) {
            float slop = mStartMotionY - mLastMotionY;
            if (Math.abs(slop) > mTouchSlop) {
                //如果ListView已经滑动到了最上边，用户还下拉，那么禁止ListView获取事件
                if (slop < 0) {
                    int first = getFirstVisiblePosition();
                    View firstView;
                    if ((first == 0) && ((firstView = getChildAt(0)) != null)) {
                        if (firstView.getTop() == 0) {
                            mDisallow = false;
                        }
                    }
                }
                //如果ListView已经滑动到了最下边(或者数据没有填充满)，用户还上拉，那么禁止ListView获取事件
                if (slop > 0) {
                    int last = getLastVisiblePosition();
                    View lastView;
                    if (getAdapter() != null &&
                            getAdapter().getCount() > 0 &&
                            (last == getAdapter().getCount() - 1) && ((lastView = getChildAt(getChildCount() - 1)) != null)) {
                        if (lastView.getBottom() == getHeight() ||
                                lastView.getBottom() < getHeight()) {
                            mDisallow = false;
                        }
                    }
                }
            }
        }
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastMotionY = ev.getY();
                mStartMotionY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                mLastMotionY = ev.getY();
                if (!mDragging) {
                    mDragging = true;
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                mDragging = false;
                break;
        }
        if (mParentScrollView != null)
            mParentScrollView.requestDisallowInterceptTouchEvent(mDisallow);
        return super.dispatchTouchEvent(ev);
    }

    public void setScrollParam(int i, int i1) {
        scrollY = i1;
        mVisibleCount = i;
    }
}
