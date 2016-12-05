package com.thinkive.android.trade_bz.a_stock.activity;


import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_rr.fragment.CreditTodayEntrustOrTradeFragment;
import com.thinkive.android.trade_bz.a_rr.fragment.MyRRHoldStockFragment;
import com.thinkive.android.trade_bz.a_rr.fragment.RCreditBuyFragment;
import com.thinkive.android.trade_bz.a_rr.fragment.RCreditSaleFragment;
import com.thinkive.android.trade_bz.a_rr.fragment.RRevocationFragment;
import com.thinkive.android.trade_bz.a_stock.controll.MultiTradeViewController;
import com.thinkive.android.trade_bz.a_stock.fragment.AbsBaseFragment;
import com.thinkive.android.trade_bz.others.RadioTabs;
import com.thinkive.android.trade_bz.others.tools.TradeTools;
import com.thinkive.android.trade_bz.views.HorizontalSlideLinearLayout;
import com.thinkive.android.trade_bz.views.NavigatorView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/25.
 */
public class MultiCreditTradeActivity extends AbsNavBarActivity{
    private MultiTradeViewController mController = null;
    private NavigatorView mNavSlide = null;
    private RadioTabs mRadioTabs;

    private HorizontalSlideLinearLayout mHorizontalSlideLinearLayout;
    private ArrayList<AbsBaseFragment> mFragmentList = null;


    private int defaultViewPagerPos;
    private RCreditBuyFragment mRCreditBuyFragment;
    private RCreditSaleFragment mRCreditSaleFragment;
    private TextView mBackTv;
    private RRevocationFragment mRRevocationFragment;
    private MyRRHoldStockFragment mMyRRHoldStockFragment;
    private CreditTodayEntrustOrTradeFragment mCreditTodayEntrustOrTradeFragment;
    private int mChildePos=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multicredittrade, true);
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
        mBackTv = (TextView) findViewById(R.id.tv_back);
        mNavSlide = (NavigatorView) findViewById(R.id.nsv_multi_trade);
    }

    @Override
    protected void initData() {
        Bundle bundle = getIntent().getExtras();
        defaultViewPagerPos = bundle.getInt("pos", 0);
        mChildePos = bundle.getInt("childePos", 0);
        mFragmentList = new ArrayList<AbsBaseFragment>();
        mRCreditBuyFragment = new RCreditBuyFragment();
        mRCreditBuyFragment.setName("融买");
        mRCreditSaleFragment = new RCreditSaleFragment();
        mRCreditSaleFragment.setName("融卖");
        mRRevocationFragment = new RRevocationFragment();
        mRRevocationFragment.setName("撤单");
        mCreditTodayEntrustOrTradeFragment = new   CreditTodayEntrustOrTradeFragment();
        mCreditTodayEntrustOrTradeFragment.setName("委托");
        Bundle bundle1 = new Bundle();
        bundle1.putInt("childePos",mChildePos);
        mCreditTodayEntrustOrTradeFragment.setArguments(bundle1);
        mMyRRHoldStockFragment = new MyRRHoldStockFragment();
        mMyRRHoldStockFragment.setName("个人");
        mFragmentList.add(mRCreditBuyFragment);
        mFragmentList.add(mRCreditSaleFragment);
        mFragmentList.add(mRRevocationFragment);
        mFragmentList.add(mCreditTodayEntrustOrTradeFragment);
        mFragmentList.add(mMyRRHoldStockFragment);
        mController = new MultiTradeViewController(this);
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
        setTitleText("信用交易");
//        setTitleDrawableLedt();
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
    /**
     * 当其他Fragment中的列表的item展开布局中的“买入”、“卖出”按钮
     *
     * @param stockCode 在持仓列表中点击的那支股票的股票代码
     * @param buyOrSale 0:单击的是“买入”；1：单机的是“卖出”
     */
    public void transferFragmentToBuySaleFromOthers(String stockCode, int buyOrSale) {
        if (buyOrSale == 0) { // 如果单击的是“买入”

            Bundle bundle = mRCreditBuyFragment.getArguments();
            if (bundle == null) {
                bundle = new Bundle();
                bundle.putString("hold_stock_code", stockCode);
                mRCreditBuyFragment.setArguments(bundle);
            } else {
                mRCreditBuyFragment.setStockCodeFromOther(stockCode);
            }


        } else if (buyOrSale == 1) { // 如果单击的是“卖出”
            Bundle bundle = mRCreditSaleFragment.getArguments();
            if (bundle == null) {
                bundle = new Bundle();
                bundle.putString("hold_stock_code", stockCode);
                mRCreditSaleFragment.setArguments(bundle);
            } else {
                mRCreditSaleFragment.setStockCodeFromOther(stockCode);
            }
        }


        mRadioTabs.setCurTab(buyOrSale);
    }

    public NavigatorView getNavSlide() {
        return mNavSlide;
    }

    public HorizontalSlideLinearLayout getHorizontalSlideLinearLayout() {
        return mHorizontalSlideLinearLayout;
    }

}
