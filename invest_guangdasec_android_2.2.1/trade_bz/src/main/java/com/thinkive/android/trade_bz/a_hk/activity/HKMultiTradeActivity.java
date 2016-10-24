package com.thinkive.android.trade_bz.a_hk.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_hk.controll.HKMultiTradeActivityController;
import com.thinkive.android.trade_bz.a_hk.fragment.HKMyHoldStockFragment;
import com.thinkive.android.trade_bz.a_hk.fragment.HKOrderBuyFragment;
import com.thinkive.android.trade_bz.a_hk.fragment.HKSelectFragment;
import com.thinkive.android.trade_bz.a_stock.activity.AbsNavBarActivity;
import com.thinkive.android.trade_bz.a_stock.controll.MultiTradeViewController;
import com.thinkive.android.trade_bz.a_stock.fragment.AbsBaseFragment;
import com.thinkive.android.trade_bz.others.RadioTabs;
import com.thinkive.android.trade_bz.others.tools.TradeTools;
import com.thinkive.android.trade_bz.views.HorizontalSlideLinearLayout;
import com.thinkive.android.trade_bz.views.NavigatorView;

import java.util.ArrayList;

/**
 * 港股通交易Activity
 * 目前有：持仓、下单、查询
 * @author 张雪梅
 * @date 2015/8/9
 */
public class HKMultiTradeActivity extends AbsNavBarActivity {

    private HKMultiTradeActivityController mController = null;
    private NavigatorView mNavSlide = null;
    private RadioTabs mRadioTabs;

    private HorizontalSlideLinearLayout mHorizontalSlideLinearLayout;
    private ArrayList<AbsBaseFragment> mFragmentList = null;

    private int defaultViewPagerPos = 0;

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
        mFragmentList = new ArrayList<AbsBaseFragment>();
        HKOrderBuyFragment orderBuyFragment = new HKOrderBuyFragment();
        HKMyHoldStockFragment myHoldStockFragment = new HKMyHoldStockFragment();
        HKSelectFragment selectFragment = new HKSelectFragment();

        orderBuyFragment.setName(getResources().getString(R.string.hk_company_action13));
        myHoldStockFragment.setName(getResources().getString(R.string.hk_hold));
        selectFragment.setName(getResources().getString(R.string.home_query));

        // 将各个Fragment对象添加到mFragmentList，以便添加到ViewPager中
        mFragmentList.add(myHoldStockFragment);
        mFragmentList.add(orderBuyFragment);
        mFragmentList.add(selectFragment);
        mController = new HKMultiTradeActivityController(this);
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            defaultViewPagerPos = bundle.getInt("ViewPagerFragmentPos", 0);
        }
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
        mNavSlide.setBottomLineColor(getResources().getColor(R.color.trade_divide_line));
        mNavSlide.setAutoFixSpace(true);
        // 设置返回按钮可见
        setBackBtnVisibility(View.VISIBLE);
        // 设置页面上方中间的标题
        setTitleText(R.string.hk_company_action12);
        // 遍历mFragmentList，将其中的Fragment中的name都加入到mNavSlide的tab中
        for (AbsBaseFragment fragment : mFragmentList) {
            mNavSlide.addTab(fragment.getName());
        }
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
     * @param index
     *         改变后，高亮的标签页序号
     * @param str
     *         改变后，高亮的文本
     */
    public void onTabLightChange(int index, String str) {
        //设置标题栏标题
//        setTitleStr(str);
    }

    public NavigatorView getNavSlide() {
        return mNavSlide;
    }

    public void setTitleStr(String titleStr) {
        setTitleText(getString(R.string.hk_pre) + titleStr);
    }

    public HorizontalSlideLinearLayout getHorizontalSlideLinearLayout() {
        return mHorizontalSlideLinearLayout;
    }
}
