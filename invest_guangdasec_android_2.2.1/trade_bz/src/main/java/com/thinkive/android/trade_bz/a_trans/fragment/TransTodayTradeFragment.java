package com.thinkive.android.trade_bz.a_trans.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.android.thinkive.framework.compatible.TKFragmentActivity;
import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;
import com.thinkive.android.trade_bz.a_stock.fragment.AbsBaseFragment;
import com.thinkive.android.trade_bz.a_trans.adapter.TransTodayTradeAdapter;
import com.thinkive.android.trade_bz.a_trans.bean.TransSelectBean;
import com.thinkive.android.trade_bz.a_trans.bll.TransTTradeServicesImpl;
import com.thinkive.android.trade_bz.utils.DateUtils;
import com.thinkive.android.trade_bz.views.PullToRefresh.PullToRefreshBase;
import com.thinkive.android.trade_bz.views.PullToRefresh.PullToRefreshListView;

import java.util.ArrayList;

/**
 * 转股交易今日成交
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/12/31
 */
public class TransTodayTradeFragment extends AbsBaseFragment {
    /**
     *今日成交数据适配器
     */
    private TransTodayTradeAdapter mAdapter;
    /**
     *今日成交Fragment的宿主
     */
    private TKFragmentActivity mActivity;
    /**
     *该Fragement的业务类
     */
    private TransTTradeServicesImpl mServices;
    /**
     *控制今日成交
     */
    private TransTodayTradeController mController;
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
        mAdapter=new TransTodayTradeAdapter(mActivity);
        mServices=new TransTTradeServicesImpl(this);
        mController=new TransTodayTradeController(this);
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
     * 接收今日成交数据
     * @param dataList
     */
    public void getTodayTradeData(ArrayList<TransSelectBean> dataList) {
        if (dataList == null || dataList.size()==0){
            mLinLoading.setVisibility(View.GONE);
            mLinNoDataSet.setVisibility(View.VISIBLE);
            mRefreshListView.setVisibility(View.GONE);
        } else {
            mLinLoading.setVisibility(View.GONE);
            mLinNoDataSet.setVisibility(View.GONE);
            mRefreshListView.setVisibility(View.VISIBLE);
            mAdapter.setListData(dataList);
            mListView.setAdapter(mAdapter);
        }
        refreshComplete();
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
     *被下拉时执行
     */
    public void onDownRefresh() {
        mServices.requestTodayTrade();
    }
}

class TransTodayTradeController extends AbsBaseController implements
        PullToRefreshListView.OnRefreshListener{

    private TransTodayTradeFragment mFragment = null;

    public TransTodayTradeController(TransTodayTradeFragment Fragment) {
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
