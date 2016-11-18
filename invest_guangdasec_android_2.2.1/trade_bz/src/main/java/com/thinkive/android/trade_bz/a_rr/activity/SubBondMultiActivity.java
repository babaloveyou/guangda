package com.thinkive.android.trade_bz.a_rr.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_rr.bean.RSelectCollaterSecurityBean;
import com.thinkive.android.trade_bz.a_rr.controll.SubBondMultiActivityController;
import com.thinkive.android.trade_bz.a_rr.fragment.SubFinancingFragment;
import com.thinkive.android.trade_bz.a_rr.fragment.SubSecuritiesFragment;
import com.thinkive.android.trade_bz.a_stock.activity.AbsNavBarActivity;
import com.thinkive.android.trade_bz.a_stock.controll.MultiTradeViewController;
import com.thinkive.android.trade_bz.a_stock.fragment.AbsBaseFragment;
import com.thinkive.android.trade_bz.others.RadioTabs;
import com.thinkive.android.trade_bz.others.tools.TradeTools;
import com.thinkive.android.trade_bz.views.HorizontalSlideLinearLayout;
import com.thinkive.android.trade_bz.views.NavigatorView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/17.
 */
public class SubBondMultiActivity extends AbsNavBarActivity {
    private SubBondMultiActivityController mController = null;
    private NavigatorView mNavSlide = null;
    private RadioTabs mRadioTabs;
    private List<RSelectCollaterSecurityBean> dataListFinancing = new ArrayList<>();
    private List<RSelectCollaterSecurityBean> dataListSecurities = new ArrayList<>();
    private HorizontalSlideLinearLayout mHorizontalSlideLinearLayout;
    private ArrayList<AbsBaseFragment> mFragmentList = null;
    private int defaultViewPagerPos=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_sub_bond, true);
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
//        Bundle bundle = getIntent().getExtras();
//        defaultViewPagerPos = bundle.getInt("ViewPagerFragmentPos", 0);
        mFragmentList = new ArrayList<AbsBaseFragment>();


        SubFinancingFragment fragment1 = new SubFinancingFragment();
        fragment1.setName(this.getResources().getString(R.string.r_sub_financing_title));

        SubSecuritiesFragment fragment2 = new SubSecuritiesFragment();
        fragment2.setName(this.getResources().getString(R.string.r_sub_securities_title));


        mFragmentList.add(fragment1);
        mFragmentList.add(fragment2);


        mController = new SubBondMultiActivityController(this);

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
        setTitleText(R.string.r_subbond_title);
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
    public void saveFinancingData(List<RSelectCollaterSecurityBean> list) {
        dataListFinancing.clear();
        dataListFinancing.addAll(list);
    }

    public List<RSelectCollaterSecurityBean> getFinancingData() {
        return  dataListFinancing;
    }

    public void saveSecuritiesData(List<RSelectCollaterSecurityBean> list) {
        dataListSecurities.clear();
        dataListSecurities.addAll(list);
    }

    public List<RSelectCollaterSecurityBean> getSecuritiesData() {
        return  dataListSecurities;
    }
    @Override
    protected void onResume() {
        super.onResume();
        dataListSecurities.clear();
        dataListFinancing.clear();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dataListSecurities.clear();
        dataListFinancing.clear();
    }
}
