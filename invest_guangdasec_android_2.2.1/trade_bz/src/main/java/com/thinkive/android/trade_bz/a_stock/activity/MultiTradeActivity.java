package com.thinkive.android.trade_bz.a_stock.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.controll.MultiTradeViewController;
import com.thinkive.android.trade_bz.a_stock.fragment.AbsBaseFragment;
import com.thinkive.android.trade_bz.a_stock.fragment.BuyOrSellFragment;
import com.thinkive.android.trade_bz.a_stock.fragment.MyHoldStockFragment;
import com.thinkive.android.trade_bz.a_stock.fragment.RevocationFragment;
import com.thinkive.android.trade_bz.a_stock.fragment.TodayEntrustOrTradeFragment;
import com.thinkive.android.trade_bz.others.RadioTabs;
import com.thinkive.android.trade_bz.others.tools.TradeTools;
import com.thinkive.android.trade_bz.views.HorizontalSlideLinearLayout;
import com.thinkive.android.trade_bz.views.NavigatorView;

import java.util.ArrayList;

/**
 * 普通交易Activity
 * 目前有：买入、卖出、持仓、撤单、查询五个Fragment标签页
 *
 * @author 王志鸿
 * @version 1.0
 * @date 2015/6/5
 */
public class MultiTradeActivity extends AbsNavBarActivity {
    private MultiTradeViewController mController = null;
    private NavigatorView mNavSlide = null;
    private RadioTabs mRadioTabs;

    private HorizontalSlideLinearLayout mHorizontalSlideLinearLayout;
    private ArrayList<AbsBaseFragment> mFragmentList = null;

    private BuyOrSellFragment mBuyFragment;
    private BuyOrSellFragment mSaleFragment;

    private int defaultViewPagerPos;
    private int mChildePos;

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
        mChildePos = bundle.getInt("childePos", 0);
        final String stock_code = bundle.getString("stock_code");
        mFragmentList = new ArrayList<AbsBaseFragment>();
        RevocationFragment revocationFragment = new RevocationFragment();
        MyHoldStockFragment myHoldStockFragment = new MyHoldStockFragment();
        TodayEntrustOrTradeFragment mTodayEntrustOrTradeFragment = new TodayEntrustOrTradeFragment();
        Bundle bundle1 = new Bundle();
        bundle1.putInt("childePos", mChildePos);
        mTodayEntrustOrTradeFragment.setArguments(bundle1);
        // 初始化“买入”、“卖出”两个Fragment
        mBuyFragment = new BuyOrSellFragment();
        mSaleFragment = new BuyOrSellFragment();
        // 设置“买入”滑动页Fragment的属性
        mBuyFragment.setName(getResources().getString(R.string.home_buying_title));
        mBuyFragment.setBuyOrSell(0);
        // 设置“卖出”滑动页Fragment的属性
        mSaleFragment.setName(getResources().getString(R.string.home_sell_title));
        mSaleFragment.setBuyOrSell(1);
        // 设置“撤单”滑动页Fragment的属性
        revocationFragment.setName(getResources().getString(R.string.revocation_actionbar_text));
        // 设置“委托”滑动页Fragment的属性
        mTodayEntrustOrTradeFragment.setName(getResources().getString(R.string.hk_entrust));
        // 设置“个人”滑动页Fragment的属性
        myHoldStockFragment.setName(getResources().getString(R.string.hk_mine));
        // 将各个Fragment对象添加到mFragmentList，以便添加到ViewPager中
        mFragmentList.add(mBuyFragment);
        mFragmentList.add(mSaleFragment);
        mFragmentList.add(revocationFragment);
        mFragmentList.add(mTodayEntrustOrTradeFragment);
        mFragmentList.add(myHoldStockFragment);
        mController = new MultiTradeViewController(this);
        mRadioTabs = new RadioTabs(this, mHorizontalSlideLinearLayout);
        mRadioTabs.setFragments(mFragmentList);
        if (stock_code != null & (defaultViewPagerPos == 0 || defaultViewPagerPos == 1)) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    transferFragmentToBuySaleFromOthers(stock_code, defaultViewPagerPos);
                }
            }).start();

        }
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
        setTitleText(R.string.trade_multi_title);

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

    /**
     * 当其他Fragment中的列表的item展开布局中的“买入”、“卖出”按钮
     *
     * @param stockCode 在持仓列表中点击的那支股票的股票代码
     * @param buyOrSale 0:单击的是“买入”；1：单机的是“卖出”
     */
    public void transferFragmentToBuySaleFromOthers(String stockCode, final int buyOrSale) {
        BuyOrSellFragment buyOrSellFragment = null;
        if (buyOrSale == 0) { // 如果单击的是“买入”
            buyOrSellFragment = mBuyFragment;
        } else if (buyOrSale == 1) { // 如果单击的是“卖出”
            buyOrSellFragment = mSaleFragment;
        }
        Bundle bundle = buyOrSellFragment.getArguments();
        if (bundle == null) {
            bundle = new Bundle();
            bundle.putString("hold_stock_code", stockCode);
            buyOrSellFragment.setArguments(bundle);
        } else {
            buyOrSellFragment.setStockCodeFromOther(stockCode);
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mRadioTabs.setCurTab(buyOrSale);
            }
        });

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
}
