package com.thinkive.android.trade_bz.a_stock.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.android.thinkive.framework.compatible.TKFragmentActivity;
import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.bean.HistoryTradeBean;
import com.thinkive.android.trade_bz.a_stock.bll.TodayTradeServicesImpl;
import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;
import com.thinkive.android.trade_bz.a_stock.adapter.TodayTradeAdapter;
import com.thinkive.android.trade_bz.views.PullToRefresh.PullToRefreshBase;
import com.thinkive.android.trade_bz.views.PullToRefresh.PullToRefreshListView;
import com.thinkive.android.trade_bz.utils.DateUtils;

import java.util.ArrayList;

/**
 *   今日成交Fragment
 * （与历史成交共用适配器）
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/6/23
 */
public class TodayTradeFragment extends AbsBaseFragment {
    /**
     * 承载今日成交数据的ListView
     */
    private ListView mListView;
    /**
     *今日成交数据适配器
     */
    private TodayTradeAdapter mAdapter;
    /**
     *今日成交Fragment的宿主
     */
    private TKFragmentActivity mActivity;
    /**
     *该Fragement的业务类
     */
    private TodayTradeServicesImpl mServices;
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
     *控制今日成交
     */
    private TodayTradeController mController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_a_today_trade, null);
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
        mAdapter=new TodayTradeAdapter(mActivity);
        mServices=new TodayTradeServicesImpl(this);
        mController=new TodayTradeController(this);
    }
    @Override
    protected void initViews() {
        //调用业务类中，初始化今日成交数据的方法
        mServices.requestTodayTrade();
        //设置禁止上拉加载更多
        mPullToRefreshListView.setPullLoadEnabled(false);
        setTheme();
    }

    @Override
    protected void setTheme() {

    }

    /**
     * 接收 TodayTradeServicesImpl 业务类传递过来的今日成交数据
     * @param dataList
     */
    public void onGetTodayTradeData(ArrayList<HistoryTradeBean> dataList) {
        if (dataList == null || dataList.size() == 0) {
            mPullToRefreshListView.setVisibility(View.GONE);
            mLoading.setVisibility(View.GONE);
            mLiNoData.setVisibility(View.VISIBLE);
        } else {
            mLoading.setVisibility(View.GONE);
            mLiNoData.setVisibility(View.GONE);
            mPullToRefreshListView.setVisibility(View.VISIBLE);
            mAdapter.setListData(dataList);
            mListView.setAdapter(mAdapter);
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
        mServices.requestTodayTrade();
    }
}

class TodayTradeController extends AbsBaseController implements
        PullToRefreshListView.OnRefreshListener{

    private TodayTradeFragment mFragment = null;

    public TodayTradeController(TodayTradeFragment Fragment) {
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
