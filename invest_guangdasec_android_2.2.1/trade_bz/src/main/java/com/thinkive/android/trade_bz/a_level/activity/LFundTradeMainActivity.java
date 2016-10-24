package com.thinkive.android.trade_bz.a_level.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;
import com.thinkive.android.trade_bz.a_level.controll.LFundActivityController;
import com.thinkive.android.trade_bz.others.tools.TradeTools;
import com.thinkive.android.trade_bz.a_stock.activity.AbsNavBarActivity;
import com.thinkive.android.trade_bz.a_stock.adapter.BasePagerAdapter;
import com.thinkive.android.trade_bz.a_stock.fragment.AbsBaseFragment;
import com.thinkive.android.trade_bz.a_level.fragment.LFundBuyFragment;
import com.thinkive.android.trade_bz.a_level.fragment.LFundSelectFragment;
import com.thinkive.android.trade_bz.views.NavigatorView;

import java.util.ArrayList;
import java.util.List;

/**
 *  分级基金交易
 * @author 张雪梅
 * @company Thinkive
 * @date 15/11/16
 */
public class LFundTradeMainActivity extends AbsNavBarActivity {
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
    private LFundActivityController mController=null;
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
        setTitleText(R.string.home_fun_trade_level);
        for (AbsBaseFragment fragment : mFragmentList) {
            mNavSlide.addTab(fragment.getName());
        }
    }

    @Override
    protected void initData() {
        mFragmentList = new ArrayList();
        mPagerAdapter = new BasePagerAdapter(getSupportFragmentManager());

        LFundBuyFragment fransomFragment=new LFundBuyFragment();
        fransomFragment.setName(getResources().getString(R.string.r_title_two));

        LFundSelectFragment fselectFragment=new LFundSelectFragment();
        fselectFragment.setName(getResources().getString(R.string.fund_trade_select_title));

        mFragmentList.add(fransomFragment);
        mFragmentList.add(fselectFragment);
        mController=new LFundActivityController(this);

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
