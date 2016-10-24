package com.android.thinkive.invest_sd.controllers;

import android.view.View;

import com.android.thinkive.framework.compatible.ListenerControllerAdapter;

/**
 *  控制基类
 * @author 张雪梅
 * @company Thinkive
 * @date 15/8/5
 */
public abstract class AbsBaseController extends ListenerControllerAdapter {
    /**
     * OnTabLightChangeListener
     */
    public static final int ON_TAB_LIGHT_CHANGED = 0x000001;
    /**
     * OnTabClickListener
     */
    public static final int ON_TAB_CLICK = 0x000002;
    /**
     * PageChangeListener
     */
    public static final int ON_PAGER_CHANGED = 0x000003;
    /**
     * OnSwipeListener
     */
    public static final int ON_SWIPE = 0x000004;
    /**
     * PullToRefreshListView.OnRefreshListener
     */
    public static final int ON_LISTVIEW_REFLASH = 0x000005;
    /**
     * PullToRefreshScrollView.OnRefreshListener
     */
    public static final int ON_SCROLLVIEW_REFLASH = 0x000001;
    /**
     * TextWatcher
     */
    public static final int ON_TEXT_CHANGE = 4972;
    public static final int ON_SEEKBAR_CHANGE = 4973;

    public static final int ON_FOCUS_CHANGE = 4974;
    public static final int ON_CHECK_CHANGE = 4975;

    public static final int ON_SLIDE = 4976;
    public static final int ON_TAB_CHANGE = 4977;

    /**
     * IndicatorScrollView.OnItemClickListener
     */
    public static final int ON_INDICATOR_SCROLLVIEW_ITEM_CLICK = 4978;
    /**
     * NavigationView.OnNavigationItemSelectedListener
     */
    public static final int ON_NAVIGATION_ITEM_SELECTED = 4979;

    @Override
    public void register(int i, View view) {

    }
}
