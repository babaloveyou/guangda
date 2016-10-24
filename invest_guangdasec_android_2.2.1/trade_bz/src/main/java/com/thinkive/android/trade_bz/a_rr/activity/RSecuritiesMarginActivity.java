package com.thinkive.android.trade_bz.a_rr.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_rr.controll.RSecuritiesActivityController;
import com.thinkive.android.trade_bz.a_rr.fragment.ROrdersFragment;
import com.thinkive.android.trade_bz.a_rr.fragment.RPropertyFragment;
import com.thinkive.android.trade_bz.a_rr.fragment.RSelectFragment;
import com.thinkive.android.trade_bz.a_rr.fragment.RTransferFragment;
import com.thinkive.android.trade_bz.a_stock.activity.AbsNavBarActivity;
import com.thinkive.android.trade_bz.a_stock.adapter.BasePagerAdapter;
import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;
import com.thinkive.android.trade_bz.a_stock.fragment.AbsBaseFragment;
import com.thinkive.android.trade_bz.others.tools.TradeTools;
import com.thinkive.android.trade_bz.views.NavigatorView;

import java.util.ArrayList;
import java.util.List;

/**
 * 融资融券
 * @author 张雪梅
 * @company Thinkive
 * @date 15/7/29
 */
public class RSecuritiesMarginActivity extends AbsNavBarActivity {
    /**
     * 头部导航栏
     */
    private NavigatorView mNavSlide = null;
    /**
     * fragment适配器
     */
    private BasePagerAdapter mPagerAdapter = null;
    /**
     * fragement集合类
     */
    private List<AbsBaseFragment> mFragmentList = null;
    /**
     * ViewPager
     */
    private ViewPager mViewPager = null;
    /**
     * 该类控制器
     */
    private RSecuritiesActivityController mController = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_title_content, true);
        // 发消息，让登录页关闭
        TradeTools.sendMsgToLoginForSubmitFinish(this);
        findViews();
        initData();
        setListeners();
        initViews();
    }

    @Override
    protected void initViews() {
        mNavSlide.setTabNormalTextColor(getResources().getColor(R.color.trade_text_color9));
        mNavSlide.setTabLightTextColor(getResources().getColor(R.color.trade_color1));
        mNavSlide.setTabLightBackGroundColor(getResources().getColor(R.color.trade_color1));
        mNavSlide.setBackgroundColor(Color.WHITE, Color.WHITE);
        mNavSlide.setAutoFixSpace(true);
        mViewPager.setOffscreenPageLimit(mFragmentList.size() - 1);
        mPagerAdapter.notifyDataSetChanged();
        mViewPager.setAdapter(mPagerAdapter);
        setBackBtnVisibility(View.VISIBLE);
        for (AbsBaseFragment fragment : mFragmentList) {
            mNavSlide.addTab(fragment.getName());
        }
        // 设置页面上方中间的标题
        setTitleText(R.string.r_title);
    }

    @Override
    protected void initData() {
        mFragmentList = new ArrayList();
        mPagerAdapter = new BasePagerAdapter(getSupportFragmentManager());

        RPropertyFragment rpropertyFragment = new RPropertyFragment();
        rpropertyFragment.setName(getResources().getString(R.string.hk_hold));

        ROrdersFragment rordersFragment = new ROrdersFragment();
        rordersFragment.setName(getResources().getString(R.string.r_title_two));

        RTransferFragment rtransferredFragment = new RTransferFragment();
        rtransferredFragment.setName(getResources().getString(R.string.r_title_three));

        RSelectFragment rselectFragment = new RSelectFragment();
        rselectFragment.setName(getResources().getString(R.string.fund_trade_select_title));

        mFragmentList.add(rpropertyFragment);
        mFragmentList.add(rordersFragment);
        mFragmentList.add(rtransferredFragment);
        mFragmentList.add(rselectFragment);
        mController = new RSecuritiesActivityController(this);
        mPagerAdapter.setFragmentsData(mFragmentList);
    }

    @Override
    protected void findViews() {
        mViewPager = (ViewPager) findViewById(R.id.vp_for_activity);
        mNavSlide = (NavigatorView) findViewById(R.id.nsv_for_activity);
    }

    @Override
    protected void setListeners() {
        super.setListeners();
        registerListener(AbsBaseController.ON_TAB_LIGHT_CHANGED, mNavSlide, mController);
        registerListener(AbsBaseController.ON_TAB_CLICK, mNavSlide, mController);
        registerListener(AbsBaseController.ON_PAGER_CHANGED, mViewPager, mController);

    }

    /**
     * 用于获得对象
     * @return
     */
    public NavigatorView getNavSlide() {
        return mNavSlide;
    }

    /**
     * 用于获得对象
     * @return
     */
    public ViewPager getViewPager() {
        return mViewPager;
    }

    /**
     * 当页切换时执行
     */
    public void notifyFragmentIsResume(int index) {
        for (int i = 0; i < mFragmentList.size(); i++) {
            if (index != i) {
                mFragmentList.get(i).onPause();
            } else {
                mFragmentList.get(i).onResume();
            }
        }
    }
}
