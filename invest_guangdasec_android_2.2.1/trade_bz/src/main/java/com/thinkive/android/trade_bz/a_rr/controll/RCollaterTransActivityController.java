package com.thinkive.android.trade_bz.a_rr.controll;

import android.view.View;

import com.thinkive.android.trade_bz.a_rr.activity.RCollaterTransActivity;
import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;
import com.thinkive.android.trade_bz.views.HorizontalSlideLinearLayout;
import com.thinkive.android.trade_bz.views.NavigatorView;

/**
 * Created by Administrator on 2016/11/16.
 */
public class RCollaterTransActivityController extends AbsBaseController implements
        NavigatorView.OnTabClickListener, NavigatorView.OnTabLightChangeListener,
        HorizontalSlideLinearLayout.OnSlideListener {

    private RCollaterTransActivity mActivity = null;

    public RCollaterTransActivityController(RCollaterTransActivity activity) {
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


    @Override
    public void onToLeftSlide(HorizontalSlideLinearLayout horizontalSlideLinearLayout) {


        mActivity.onLeftSlide();

    }

    @Override
    public void onToRightSlide(HorizontalSlideLinearLayout horizontalSlideLinearLayout) {
        mActivity.onRightSlide();
    }
}

