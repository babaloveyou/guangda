package com.thinkive.android.trade_bz.a_stock.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.android.thinkive.framework.compatible.TKFragmentActivity;
import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.bean.RevocationBean;
import com.thinkive.android.trade_bz.a_stock.bll.TodayEntrustServicesImpl;
import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;
import com.thinkive.android.trade_bz.a_stock.adapter.TodayEntrustAdapter;
import com.thinkive.android.trade_bz.views.PullToRefresh.PullToRefreshBase;
import com.thinkive.android.trade_bz.views.PullToRefresh.PullToRefreshListView;
import com.thinkive.android.trade_bz.utils.DateUtils;

import java.util.ArrayList;

/**
 * 今日委托
 * Announcements：
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/6/19
 */
public class TodayEntrustFragment extends AbsBaseFragment {
    /**
     * 承载今日委托数据的ListView
     */
    private ListView mListView;
    /**
     *今日委托数据适配器
     */
    private TodayEntrustAdapter mTodayEntrustAdapter;
    /**
     *今日委托 Fragment的宿主
     */
    private TKFragmentActivity mActivity;
    /**
     *该Fragement的业务类
     */
    private TodayEntrustServicesImpl mServices;
    /**
     *如果没有数据就显示该图片
     */
    private LinearLayout mLiNoData;
    /**
     *自定义的listView对象
     */
    private PullToRefreshListView mPullToRefreshListView;
    /**
     *正在加载的旋转进度条
     */
    private LinearLayout mLoading;
    /**
     *控制今日委托
     */
    private TodayEntrustController mController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_a_today_entrust, null);
        findViews(rootView);
        initData();
        setListeners();
        initViews();
        return rootView;
    }

    @Override
    protected void findViews(View view) {
        mPullToRefreshListView=(PullToRefreshListView)view.findViewById(R.id.lv_refresh_list);
        mListView=mPullToRefreshListView.getRefreshableView();
        mListView.setDivider(null);
        mLoading = (LinearLayout) view.findViewById(R.id.lin_loading_set);
        mLiNoData =(LinearLayout)view.findViewById(R.id.lin_not_data_set);
    }

    @Override
    protected void setListeners() {
        registerListener(AbsBaseController.ON_LISTVIEW_REFLASH, mPullToRefreshListView, mController);
    }

    @Override
    protected void initData() {
        mActivity = (TKFragmentActivity) getActivity();
        mTodayEntrustAdapter=new TodayEntrustAdapter(mActivity);
        mServices=new TodayEntrustServicesImpl(this);
        mController=new TodayEntrustController(this);
    }

    @Override
    protected void initViews() {
        //调用业务类中，初始化今日委托数据的方法
        mServices.requestTodayEntrust();
        //设置禁止上拉加载更多
        mPullToRefreshListView.setPullLoadEnabled(false);
        setTheme();
    }

    @Override
    protected void setTheme() {

    }

    /**
     * 接收业务类传递过来的今日委托数据
     * @param dataList
     */
    public void onGetTodayEntrustData(ArrayList<RevocationBean> dataList) {
        if(dataList == null || dataList.size()==0){
            mPullToRefreshListView.setVisibility(View.GONE);
            mLoading.setVisibility(View.GONE);
            mLiNoData.setVisibility(View.VISIBLE);
        } else {
            mLoading.setVisibility(View.GONE);
            mLiNoData.setVisibility(View.GONE);
            mPullToRefreshListView.setVisibility(View.VISIBLE);
            mTodayEntrustAdapter.setListData(dataList);
            mListView.setAdapter(mTodayEntrustAdapter);
        }
        refreshComplete();
    }
    /**
     * 刷新完成,收回下拉联
     */
    public void refreshComplete() {
        mPullToRefreshListView.onPullDownRefreshComplete();
        mPullToRefreshListView.onPullUpRefreshComplete();
        mPullToRefreshListView.setLastUpdatedLabel(DateUtils.getDateString("HH:mm:ss"));
    }

    /**
     * {@link #mPullToRefreshListView} 被下拉时执行
     */
    public void onDownRefresh() {
        mServices.requestTodayEntrust();
    }
}

class TodayEntrustController extends AbsBaseController implements
        PullToRefreshListView.OnRefreshListener{

    private TodayEntrustFragment mFragment = null;

    public TodayEntrustController(TodayEntrustFragment Fragment) {
        mFragment = Fragment;
    }

    @Override
    public void register(int i, View view) {
        switch (i) {
            case ON_LISTVIEW_REFLASH:
                ((PullToRefreshListView)view).setOnRefreshListener(this);
                break;
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
