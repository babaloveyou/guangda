package com.thinkive.android.trade_bz.a_rr.controll;

import android.view.View;

import com.thinkive.android.trade_bz.a_rr.activity.BalanceDetailActivity;
import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;
import com.thinkive.android.trade_bz.views.HorizontalSlideLinearLayout;
import com.thinkive.android.trade_bz.views.NavigatorView;

/**
 * Created by Administrator on 2016/11/26.
 */
public class BalanceDetailViewController extends AbsBaseController implements
        NavigatorView.OnTabClickListener, NavigatorView.OnTabLightChangeListener,
        HorizontalSlideLinearLayout.OnSlideListener {

    private BalanceDetailActivity mActivity = null;
    private boolean isCredit;

    public BalanceDetailViewController(BalanceDetailActivity activity) {
        mActivity = activity;
        isCredit = false;
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
        if (isCredit) {

            mActivity.onTabClick(index);
        } else {
            mActivity.onTabClick(index);
        }
    }

    @Override
    public void onTabLightChange(int index, String str) {
        if (isCredit) {

            mActivity.onTabLightChange(index, str);
        } else {
            mActivity.onTabLightChange(index, str);
        }

    }


    @Override
    public void onToLeftSlide(HorizontalSlideLinearLayout horizontalSlideLinearLayout) {

        if (isCredit) {

            mActivity.onLeftSlide();
        } else {
            mActivity.onLeftSlide();
        }
    }

    @Override
    public void onToRightSlide(HorizontalSlideLinearLayout horizontalSlideLinearLayout) {
        if (isCredit) {
            mActivity.onRightSlide();
        } else {
            mActivity.onRightSlide();
        }
    }
}

