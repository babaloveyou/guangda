package com.thinkive.android.trade_bz.a_rr.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_rr.bean.RSelectPropertBean;
import com.thinkive.android.trade_bz.a_rr.controll.BalanceDetailViewController;
import com.thinkive.android.trade_bz.a_rr.fragment.BalanceDebtsFragment;
import com.thinkive.android.trade_bz.a_rr.fragment.BalanceFundFragment;
import com.thinkive.android.trade_bz.a_stock.activity.AbsNavBarActivity;
import com.thinkive.android.trade_bz.a_stock.controll.MultiTradeViewController;
import com.thinkive.android.trade_bz.a_stock.fragment.AbsBaseFragment;
import com.thinkive.android.trade_bz.others.RadioTabs;
import com.thinkive.android.trade_bz.others.tools.TradeTools;
import com.thinkive.android.trade_bz.views.HorizontalSlideLinearLayout;
import com.thinkive.android.trade_bz.views.NavigatorView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/26.
 */
public class BalanceDetailActivity  extends AbsNavBarActivity {
    private BalanceDetailViewController mController = null;
    private NavigatorView mNavSlide = null;
    private RadioTabs mRadioTabs;

    private HorizontalSlideLinearLayout mHorizontalSlideLinearLayout;
    private ArrayList<AbsBaseFragment> mFragmentList = null;

    private int defaultViewPagerPos;
    private RSelectPropertBean mBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balace_details, true);
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
        mNavSlide = (NavigatorView) findViewById(R.id.nsv_multi_trade);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            mBean = (RSelectPropertBean)intent.getParcelableExtra("bean");
        }
        Bundle bundle = new Bundle();
        bundle.putParcelable("bean", mBean);
        mFragmentList = new ArrayList<AbsBaseFragment>();
        BalanceFundFragment balanceFundFragment = new BalanceFundFragment();
        balanceFundFragment.setArguments(bundle);
        balanceFundFragment.setName("资产");
        BalanceDebtsFragment balanceDebtsFragment = new BalanceDebtsFragment();
        balanceDebtsFragment.setArguments(bundle);
        balanceDebtsFragment.setName("负债");
        BalanceLimitFragment balanceLimitFragment = new BalanceLimitFragment();
        balanceFundFragment.setArguments(bundle);
        balanceLimitFragment.setName("额度");
        mController = new BalanceDetailViewController(this);
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
        setTitleText("资产负债汇总查询");
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
        defaultViewPagerPos = getIntent().getExtras().getInt("pos");
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

    public HorizontalSlideLinearLayout getHorizontalSlideLinearLayout() {
        return mHorizontalSlideLinearLayout;
    }

}

