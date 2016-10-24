package com.thinkive.android.trade_bz.a_trans.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.android.thinkive.framework.CoreApplication;
import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.activity.AbsNavBarActivity;
import com.thinkive.android.trade_bz.a_stock.adapter.BasePagerAdapter;
import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;
import com.thinkive.android.trade_bz.a_stock.fragment.AbsBaseFragment;
import com.thinkive.android.trade_bz.a_trans.controll.TransStockActivityController;
import com.thinkive.android.trade_bz.a_trans.fragment.TransStockBuyFragment;
import com.thinkive.android.trade_bz.a_trans.fragment.TransStockHoldFragment;
import com.thinkive.android.trade_bz.a_trans.fragment.TransStockSelectFragment;
import com.thinkive.android.trade_bz.others.tools.TradeTools;
import com.thinkive.android.trade_bz.views.NavigatorView;

import java.util.ArrayList;
import java.util.List;

/**
 * 转股交易主页面
 * @author 张雪梅
 * @company Thinkive
 * @date 15/12/30
 */
public class TransStockMainActivityTrade extends AbsNavBarActivity {
    /**
     * 控制器
     */
    private TransStockActivityController mController = null;
    /**
     * 头部引导栏
     */
    private NavigatorView mNavSlide = null;
    /**
     * ViewPager
     */
    private ViewPager mViewPager = null;
    /**
     * Fragment适配器
     */
    private BasePagerAdapter mPagerAdapter = null;
    /**
     * 装载Fragment的集合类
     */
    private List<AbsBaseFragment> mFragmentList = null;
    /**
     * 获得默认加载页面的TitleBar
     */
    private int defaultViewPagerPos=0;

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
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void initViews() {
        mNavSlide.setTabNormalTextColor(getResources().getColor(R.color.trade_text_color9));
        mNavSlide.setTabLightTextColor(getResources().getColor(R.color.trade_color1));
        mNavSlide.setTabLightBackGroundColor(getResources().getColor(R.color.trade_color1));
        mNavSlide.setBackgroundColor(Color.WHITE, Color.WHITE);
        mNavSlide.setAutoFixSpace(true);
        mViewPager.setAdapter(null);
        mViewPager.setOffscreenPageLimit(mFragmentList.size() - 1);
        mPagerAdapter.notifyDataSetChanged();
        mViewPager.setAdapter(mPagerAdapter);
        // 遍历mFragmentList，将其中的Fragment中的name都加入到mNavSlide的tab中
        for (AbsBaseFragment fragment : mFragmentList) {
            mNavSlide.addTab(fragment.getName());
        }
        // 设置返回按钮可见
        setBackBtnVisibility(View.VISIBLE);
        mViewPager.setCurrentItem(defaultViewPagerPos);
    }

    @Override
    protected void initData() {
        mFragmentList = new ArrayList();

        TransStockHoldFragment holdFragment = new TransStockHoldFragment();
        holdFragment.setName(CoreApplication.getInstance().getString(R.string.trans_hold5));

        TransStockBuyFragment transBuy = new TransStockBuyFragment();
        transBuy.setName(CoreApplication.getInstance().getString(R.string.trans_hold6));

        TransStockSelectFragment transSelect = new TransStockSelectFragment();
        transSelect.setName(CoreApplication.getInstance().getString(R.string.home_query));


        mFragmentList.add(holdFragment);
        mFragmentList.add(transBuy);
        mFragmentList.add(transSelect);

        mController = new TransStockActivityController(this);
        mPagerAdapter = new BasePagerAdapter(getSupportFragmentManager());
        mPagerAdapter.setFragmentsData(mFragmentList);
        setTitleText(R.string.home_trans_stock);
    }

    @Override
    protected void findViews() {
        super.findViews();
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

    public NavigatorView getNavSlide() {
        return mNavSlide;
    }

    /**
     * 通知所有的Fragment唤醒,休眠
     * @param index 需要唤醒的下标,如果全体休眠请传入小于0的数
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

    public void setTitleStr(String titleStr) {
        setTitleText(titleStr);
    }

    public ViewPager getViewPager() {
        return mViewPager;
    }
}
