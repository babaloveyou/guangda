package com.thinkive.android.trade_bz.a_hk.controll;

import android.view.View;

import com.thinkive.android.trade_bz.a_hk.activity.HKMultiTradeActivity;
import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;
import com.thinkive.android.trade_bz.views.HorizontalSlideLinearLayout;
import com.thinkive.android.trade_bz.views.NavigatorView;

/** 港股通 主activity 控制器
 * @author
 * @version 1.0
 * @corporation
 * @date 2015/6/5
 */
public class HKMultiTradeActivityController extends AbsBaseController implements
        NavigatorView.OnTabClickListener, NavigatorView.OnTabLightChangeListener,
        HorizontalSlideLinearLayout.OnSlideListener {

    private HKMultiTradeActivity mActivity = null;

    public HKMultiTradeActivityController(HKMultiTradeActivity activity) {
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
