package com.thinkive.android.trade_bz.a_stock.controll;

//import android.support.v4.view.ViewPager;

import android.view.View;

import com.thinkive.android.trade_bz.a_stock.activity.MultiTradeActivity;
import com.thinkive.android.trade_bz.views.HorizontalSlideLinearLayout;
import com.thinkive.android.trade_bz.views.NavigatorView;

/**
 * @author
 * @version 1.0
 * @corporation
 * @date 2015/6/5
 */
public class MultiTradeViewController extends AbsBaseController implements
        NavigatorView.OnTabClickListener, NavigatorView.OnTabLightChangeListener,
        HorizontalSlideLinearLayout.OnSlideListener {

    private MultiTradeActivity mActivity = null;

    public MultiTradeViewController(MultiTradeActivity activity) {
        mActivity = activity;
    }

    @Override
    public void register(int eventType, View view) {
        switch (eventType) {
            case ON_TAB_LIGHT_CHANGED:
                ((NavigatorView) view).setOnTabLightChangeListener(this);
                break;
            case ON_TAB_CLICK:
                ((NavigatorView) view).setOnTabClickListener(this);
                break;
//            case ON_PAGER_CHANGED:
//                ((ViewPager) view).setOnPageChangeListener(this);
//                break;
            case ON_TAB_CHANGE:
                break;
            case ON_SLIDE:
                ((HorizontalSlideLinearLayout) view).setOnSlideListener(this);
                break;
            default:
                break;
        }
    }

    @Override
    public void onTabClick(int index, String str) {
//        mActivity.getViewPager().setCurrentItem(index);
        mActivity.onTabClick(index);
    }

    @Override
    public void onTabLightChange(int index, String str) {
        mActivity.onTabLightChange(index, str);

    }

//    @Override
//    public void onPageScrolled(int i, float v, int i1) {
//
//    }
//
//    @Override
//    public void onPageSelected(int i) {
//        mActivity.getNavSlide().setCurrentIndex(i);
//        Log.i("DEBUG", "PageSelected=" + i);
//    }
//
//    @Override
//    public void onPageScrollStateChanged(int i) {
//
//    }


    @Override
    public void onToLeftSlide(HorizontalSlideLinearLayout horizontalSlideLinearLayout) {
        mActivity.onLeftSlide();
    }

    @Override
    public void onToRightSlide(HorizontalSlideLinearLayout horizontalSlideLinearLayout) {
        mActivity.onRightSlide();
    }
}
