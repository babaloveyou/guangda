package com.thinkive.android.trade_bz.a_trans.controll;

import android.support.v4.view.ViewPager;
import android.view.View;

import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;
import com.thinkive.android.trade_bz.a_trans.activity.TransStockMainActivityTrade;
import com.thinkive.android.trade_bz.views.NavigatorView;

/**
 * 转股交易主页面
 * @author 张雪梅
 * @company Thinkive
 * @date 15/12/30
 */

public class TransStockActivityController extends AbsBaseController implements
        NavigatorView.OnTabClickListener, NavigatorView.OnTabLightChangeListener,
        ViewPager.OnPageChangeListener{

    private TransStockMainActivityTrade mActivity = null;

    public TransStockActivityController(TransStockMainActivityTrade activity) {
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
            case ON_PAGER_CHANGED:
                ((ViewPager) view).setOnPageChangeListener(this);
                break;
            default:
                break;
        }
    }

    @Override
    public void onTabClick(int index, String str) {
        mActivity.getViewPager().setCurrentItem(index);
    }

    @Override
    public void onTabLightChange(int index, String str) {
        mActivity.notifyFragmentIsResume(index);
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        mActivity.getNavSlide().setCurrentIndex(i);
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

}
