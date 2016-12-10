package com.thinkive.android.trade_bz.views.slideexpandlistview;

import android.widget.AbsListView;

import com.thinkive.android.trade_bz.views.ExpandableLayoutItem;
import com.thinkive.android.trade_bz.views.ExpandableLayoutListView;

/**
 * Created by Administrator on 2016/12/9.
 */

public class OnExpandableLayoutScrollListener implements AbsListView.OnScrollListener
{
    private final Integer mPos;
    private int scrollState = 0;
    private  ExpandableLayoutListView mListView;
    public OnExpandableLayoutScrollListener(ExpandableLayoutListView expandableLayoutListView, Integer position) {
        mListView = expandableLayoutListView;
        mPos = position;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState)
    {
        this.scrollState = scrollState;
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount)
    {
        if (scrollState != SCROLL_STATE_IDLE)
        {
            for (int index = 0; index < view.getChildCount(); ++index)
            {
                ExpandableLayoutItem currentExpandableLayout = (ExpandableLayoutItem) view.getChildAt(index).findViewWithTag(ExpandableLayoutItem.class.getName());
                if (currentExpandableLayout.isOpened() && index != (mPos - mListView.getFirstVisiblePosition()))
                {
                    currentExpandableLayout.hideNow();
                }
                else if (!currentExpandableLayout.getCloseByUser() && !currentExpandableLayout.isOpened() && index == (mPos - mListView.getFirstVisiblePosition()))
                {
                    currentExpandableLayout.showNow();
                }
            }
        }
    }
}

