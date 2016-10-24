package com.android.thinkive.invest_sd.controllers;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.AdapterView;

import com.android.thinkive.invest_sd.R;
import com.android.thinkive.invest_sd.fragment.MainPageFragment;
import com.thinkive.android.quotation.views.pulltorefresh.PullToRefreshBase;
import com.thinkive.android.quotation.views.pulltorefresh.PullToRefreshListView;
import com.thinkive.android.quotation.views.pulltorefresh.PullToRefreshScrollView;


/**
 * Created by xuemei on 2015/9/8.
 */
public class MainFragmentViewController extends AbsBaseController implements
        View.OnClickListener, ViewPager.OnPageChangeListener, AdapterView.OnItemClickListener,
        PullToRefreshListView.OnRefreshListener{

    private MainPageFragment mFragment = null;

    public static final int ON_SCROLLVIEW_REFLASH = 0x000001;

    public MainFragmentViewController(MainPageFragment Fragment) {
        mFragment = Fragment;
    }
    @Override
    public void register(int i, View view) {
        switch (i) {
            case ON_CLICK:
                view.setOnClickListener(this);
                break;
            case ON_PAGER_CHANGED:
                ((ViewPager) view).addOnPageChangeListener(this);
                break;
            case ON_ITEM_CLICK:
                ((AdapterView)view).setOnItemClickListener(this);
                break;
            case ON_SCROLLVIEW_REFLASH:
                ((PullToRefreshScrollView)view).setOnRefreshListener(this);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        int resId = v.getId();
//        if (resId == R.id.im_message_icon) { //短信图标
//            mFragment.onClickMessageIcon();
//        } else if (resId == R.id.im_look_out_icon) { //搜索图标
//            mFragment.onClickLookOutIcon();
      /*else if(resId == R.id.tv_main_lookout){ //搜索栏
            mFragment.onClickLookOutIcon();
        }*/
//       if (resId == R.id.tv_hot_message_more) { //热门资讯更多
//            mFragment.onClickHotMessageMore();
////        }
//        else if (resId == R.id.tv_group_more) { //今日热门组合更多
//
        if (resId == R.id.tv_recomend_more) { //精品推荐更多
            mFragment.onClickRecommendMore();
        }
    }

    //-----------------------ViewPager滑动监听方法，定义开始------------------
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        mFragment.onPagerSelected(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }
    //-----------------------ViewPager滑动监听方法，定义结束------------------、


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        int resId = parent.getId();
        if (resId == R.id.gv_fast_menu) {
            mFragment.onItemClickFastMenu(position);
//        }else if(resId == R.id.ls_hot_group){
//            mFragment.onItemClickHotInvestCounselor(position);
        }else if(resId == R.id.ls_recomend){
            mFragment.onClickRecommendItem(position);
        }
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        mFragment.onDownRefresh();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {

    }
}
