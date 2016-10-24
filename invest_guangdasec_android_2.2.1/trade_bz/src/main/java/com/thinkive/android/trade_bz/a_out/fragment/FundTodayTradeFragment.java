package com.thinkive.android.trade_bz.a_out.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.android.thinkive.framework.compatible.TKFragmentActivity;
import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_out.bean.FundTodayTradeBean;
import com.thinkive.android.trade_bz.a_out.bll.FundTodayTradeServicesImpl;
import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;
import com.thinkive.android.trade_bz.a_out.adapter.FundTodayTradeAdapter;
import com.thinkive.android.trade_bz.a_stock.fragment.AbsBaseFragment;
import com.thinkive.android.trade_bz.views.PullToRefresh.PullToRefreshBase;
import com.thinkive.android.trade_bz.views.PullToRefresh.PullToRefreshListView;
import com.thinkive.android.trade_bz.utils.DateUtils;

import java.util.ArrayList;

/**
 * 基金交易--查询--今日成交
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/11/13
 */
public class FundTodayTradeFragment extends AbsBaseFragment {
    /**
     *  外部调用环境
     */
    private TKFragmentActivity mActivity;
    /**
     *  控制器
     */
    private FundTodayTradeController mController;
    /**
     * 数据业务类
     */
    private FundTodayTradeServicesImpl mServices;
    /**
     * listview适配器器
     */
    private FundTodayTradeAdapter mAdapter;
    /**
     * ListView
     */
    private ListView mListView;
    /**
     * 自定义的listView对象
     */
    private PullToRefreshListView mRefreshListView;
    /**
     *   正在加载的旋转进度条
     */
    private LinearLayout mLinLoading;
    /**
     * 如果没有数据就显示该图片
     */
    private LinearLayout mLinNoDataSet;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_common_refresh_list, null);
        findViews(rootView);
        initData();
        setListeners();
        initViews();
        return rootView;
    }

    @Override
    protected void findViews(View view) {
        mRefreshListView = (PullToRefreshListView) view.findViewById(R.id.lv_refresh_list);
        mListView = mRefreshListView.getRefreshableView();
        mListView.setDivider(null);
        mLinLoading = (LinearLayout) view.findViewById(R.id.lin_loading_set);
        mLinNoDataSet = (LinearLayout) view.findViewById(R.id.lin_not_data_set);
    }

    @Override
    protected void setListeners() {
        registerListener(AbsBaseController.ON_LISTVIEW_REFLASH, mRefreshListView, mController);
    }

    @Override
    protected void initData() {
        mActivity = (TKFragmentActivity) getActivity();
        mController = new FundTodayTradeController(this);
        mServices=new FundTodayTradeServicesImpl(this);
        mAdapter=new FundTodayTradeAdapter(mActivity);
    }

    @Override
    protected void initViews() {
        //调用业务类中，初始化今日成交数据的方法
        mServices.requestTodayTrade();
        //设置禁止上拉加载更多
        mRefreshListView.setPullLoadEnabled(false);
        setTheme();
    }

    @Override
    protected void setTheme() {

    }

    /**
     * 刷新完成,收回下拉联
     */
    public void refreshComplete() {
        mRefreshListView.onPullDownRefreshComplete();
        mRefreshListView.onPullUpRefreshComplete();
        mRefreshListView.setLastUpdatedLabel(DateUtils.getDateString("HH:mm:ss"));
    }
    /**
     * 获得今日成交的数据列表
     * @param dataList
     */
    public void getTodayTradeData(ArrayList<FundTodayTradeBean> dataList){
        if(dataList == null || dataList.size()==0){
            mLinNoDataSet.setVisibility(View.VISIBLE);
            mLinLoading.setVisibility(View.GONE);
            mRefreshListView.setVisibility(View.GONE);
        }else{
            mLinNoDataSet.setVisibility(View.GONE);
            mLinLoading.setVisibility(View.GONE);
            mRefreshListView.setVisibility(View.VISIBLE);
            mAdapter.setListData(dataList);
            mListView.setAdapter(mAdapter);
        }
        refreshComplete();
    }
    /**
     * 被下拉时执行
     */
    public void onDownRefresh() {
        mServices.requestTodayTrade();
    }
    /**
     * 左滑时间监听
     */
    public void onSwipe() {
        mActivity.finish();
    }
}

class FundTodayTradeController extends AbsBaseController implements
        PullToRefreshListView.OnRefreshListener {

    private FundTodayTradeFragment mFragment = null;

    public FundTodayTradeController(FundTodayTradeFragment Fragment) {
        mFragment = Fragment;
    }

    @Override
    public void register(int i, View view) {
        switch (i) {
            case ON_LISTVIEW_REFLASH:
                ((PullToRefreshListView) view).setOnRefreshListener(this);
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

