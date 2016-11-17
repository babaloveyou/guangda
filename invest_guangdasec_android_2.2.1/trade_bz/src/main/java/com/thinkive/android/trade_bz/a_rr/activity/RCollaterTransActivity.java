package com.thinkive.android.trade_bz.a_rr.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_rr.controll.RCollaterTransActivityController;
import com.thinkive.android.trade_bz.a_rr.fragment.RCollaterInFragment;
import com.thinkive.android.trade_bz.a_rr.fragment.RCollaterOutFragment;
import com.thinkive.android.trade_bz.a_rr.fragment.RCollaterRevocationFragment;
import com.thinkive.android.trade_bz.a_rr.fragment.RCollaterSearchFragment;
import com.thinkive.android.trade_bz.a_stock.activity.AbsNavBarActivity;
import com.thinkive.android.trade_bz.a_stock.controll.MultiTradeViewController;
import com.thinkive.android.trade_bz.a_stock.fragment.AbsBaseFragment;
import com.thinkive.android.trade_bz.others.RadioTabs;
import com.thinkive.android.trade_bz.others.tools.TradeTools;
import com.thinkive.android.trade_bz.views.HorizontalSlideLinearLayout;
import com.thinkive.android.trade_bz.views.NavigatorView;

import java.util.ArrayList;

/**
 * 融资融券--划转--担保品
 * Announcements：
 *
 * @author 张雪梅
 * @company Thinkive
 * @date 15/8/13
 */
public class RCollaterTransActivity extends AbsNavBarActivity {
    private RCollaterTransActivityController mController = null;
    private NavigatorView mNavSlide = null;
    private RadioTabs mRadioTabs;

    private HorizontalSlideLinearLayout mHorizontalSlideLinearLayout;
    private ArrayList<AbsBaseFragment> mFragmentList = null;
    private int defaultViewPagerPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_trade, true);
        // 发消息，让登录页关闭
        TradeTools.sendMsgToLoginForSubmitFinish(this);
        findViews();
        initData();
        setListeners();
        initViews();
    }

    @Override
    protected void findViews() {
        super.findViews();
        mHorizontalSlideLinearLayout = (HorizontalSlideLinearLayout) findViewById(R.id.hsll_content);
        mNavSlide = (NavigatorView) findViewById(R.id.nsv_multi_trade);
    }

    @Override
    protected void initData() {
        Bundle bundle = getIntent().getExtras();
        defaultViewPagerPos = bundle.getInt("ViewPagerFragmentPos", 0);
        mFragmentList = new ArrayList<AbsBaseFragment>();


        RCollaterInFragment fragment1 = new RCollaterInFragment();
        fragment1.setName(this.getResources().getString(R.string.r_layout_rb1));

        RCollaterOutFragment fragment2 = new RCollaterOutFragment();
        fragment2.setName(this.getResources().getString(R.string.r_layout_rb3));

        RCollaterRevocationFragment fragment3 = new RCollaterRevocationFragment();
        fragment3.setName(this.getResources().getString(R.string.r_layout_rb2));


        RCollaterSearchFragment fragment4 = new RCollaterSearchFragment();
        fragment4.setName(this.getResources().getString(R.string.r_layout_rb4));

        mFragmentList.add(fragment1);
        mFragmentList.add(fragment2);
        mFragmentList.add(fragment3);
        mFragmentList.add(fragment4);


        mController = new RCollaterTransActivityController(this);

        mRadioTabs = new RadioTabs(this, mHorizontalSlideLinearLayout);
    }

    @Override
    protected void setListeners() {
        super.setListeners();
        registerListener(MultiTradeViewController.ON_TAB_LIGHT_CHANGED, mNavSlide, mController);
        registerListener(MultiTradeViewController.ON_TAB_CLICK, mNavSlide, mController);
        registerListener(MultiTradeViewController.ON_SLIDE, mHorizontalSlideLinearLayout, mController);
    }

    @Override
    protected void initViews() {
        mHorizontalSlideLinearLayout.initslideStandard(this);
        mNavSlide.setTabNormalTextColor(getResources().getColor(R.color.trade_text_color9));
        mNavSlide.setTabLightTextColor(getResources().getColor(R.color.trade_color1));
        mNavSlide.setTabLightBackGroundColor(getResources().getColor(R.color.trade_color1));
        mNavSlide.setBackgroundColor(Color.WHITE, Color.WHITE);
        mNavSlide.setBottomLineColor(getResources().getColor(R.color.trade_text_color1));
        mNavSlide.setAutoFixSpace(true);
        // 遍历mFragmentList，将其中的Fragment中的name都加入到mNavSlide的tab中
        for (AbsBaseFragment fragment : mFragmentList) {
            mNavSlide.addTab(fragment.getName());
        }
        // 设置返回按钮可见
        setBackBtnVisibility(View.VISIBLE);
        // 设置页面上方中间的标题
        setTitleText(R.string.r_transfer_title);
        setLogoImVisibility(View.VISIBLE);
        setLogoImBackground(R.drawable.seach);
        mRadioTabs.setFragments(mFragmentList);
        mRadioTabs.initViews();
        // mRadioTabs对象的类不是View子类，所以这个监听器的设置不能写在mController中
        mRadioTabs.setTabChangeListener(new RadioTabs.OnTabChangeListener() {
            @Override
            public void onTabChange(int position) {
                mNavSlide.setCurrentIndex(position);
            }
        });
        mRadioTabs.setCurTab(defaultViewPagerPos);
        //        setTitleStr(mFragmentList.get(defaultViewPagerPos).getName());
    }

    /**
     * 向右滑动监听
     */
    public void onRightSlide() {
        mRadioTabs.lastTab();
    }

    /**
     * 向左滑动监听
     */
    public void onLeftSlide() {
        mRadioTabs.nextTab();
    }

    /**
     * tab标签被单击时的监听
     */
    public void onTabClick(int clickIndex) {
        mRadioTabs.setCurTab(clickIndex);
    }

    /**
     * tab页高亮标签被改变时
     *
     * @param index 改变后，高亮的标签页序号
     * @param str   改变后，高亮的文本
     */
    public void onTabLightChange(int index, String str) {
        //设置标题栏标题
        //        setTitleStr(str);
    }


    public NavigatorView getNavSlide() {
        return mNavSlide;
    }

    public void setTitleStr(String titleStr) {
        setTitleText(titleStr);
    }

    public HorizontalSlideLinearLayout getHorizontalSlideLinearLayout() {
        return mHorizontalSlideLinearLayout;
    }

    @Override
    protected void onLogouImEvent() {
        //点击右侧按钮所执行的操作
        startActivity(new Intent(this, RSelectCollateralSecurityActivity.class));
    }
}
