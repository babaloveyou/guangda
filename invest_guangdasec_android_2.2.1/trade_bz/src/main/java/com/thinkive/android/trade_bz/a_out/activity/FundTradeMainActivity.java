package com.thinkive.android.trade_bz.a_out.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;
import com.thinkive.android.trade_bz.a_stock.fragment.AbsBaseFragment;
import com.thinkive.android.trade_bz.a_out.controll.FundActivityController;
import com.thinkive.android.trade_bz.others.tools.TradeTools;
import com.thinkive.android.trade_bz.a_stock.activity.AbsNavBarActivity;
import com.thinkive.android.trade_bz.a_stock.adapter.BasePagerAdapter;
import com.thinkive.android.trade_bz.a_out.fragment.FundBuyFragment;
import com.thinkive.android.trade_bz.a_out.fragment.FundHoldeCountFragment;
import com.thinkive.android.trade_bz.a_out.fragment.FundSelectFragment;
import com.thinkive.android.trade_bz.views.NavigatorView;

import java.util.ArrayList;
import java.util.List;

/**
 *  基金交易
 * @author 张雪梅
 * @company Thinkive
 * @date 15/7/3
 */
public class FundTradeMainActivity extends AbsNavBarActivity {
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
    private FundActivityController mController=null;
    /**
     * 是不是第一次进入，默认是的
     */
    private boolean isFirstIn = true;

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
        //设置页面上方中间的标题
        setTitleText(R.string.fund_trade_title);
        setBackBtnVisibility(View.VISIBLE);
        //遍历mFragmentList，将其中Fragment的名字都加入到mNavSlide的tab中
        for (AbsBaseFragment fragment : mFragmentList) {
            mNavSlide.addTab(fragment.getName());
        }
    }

    @Override
    protected void initData() {
        mFragmentList = new ArrayList();
        mPagerAdapter = new BasePagerAdapter(getSupportFragmentManager());

        FundHoldeCountFragment fholdeStockFragment=new FundHoldeCountFragment();
        fholdeStockFragment.setName(getResources().getString(R.string.fund_trade_hold_stock_title));

        FundBuyFragment buyFragement=new FundBuyFragment();
        buyFragement.setName(getResources().getString(R.string.r_title_two));

        FundSelectFragment fselectFragment=new FundSelectFragment();
        fselectFragment.setName(getResources().getString(R.string.fund_trade_select_title));

        mFragmentList.add(fholdeStockFragment);
        mFragmentList.add(buyFragement);
        mFragmentList.add(fselectFragment);
        mController=new FundActivityController(this);

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
        //如果是第一次进入fragment时，默认会调用所有的fragment的onResume方法，不需要重复调用，所以判断下
        if(!isFirstIn){
            for (int i = 0; i < mFragmentList.size(); i++) {
                if (index != i) {
                    mFragmentList.get(i).onPause();
                } else {
                    mFragmentList.get(i).onResume();
                }
            }
        }else{
            //第一次进来后，将这个值，置为false
            isFirstIn = false;
        }
    }
}
