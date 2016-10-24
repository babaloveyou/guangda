package com.thinkive.android.trade_bz.a_stock.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.bean.TodayMoneyBean;
import com.thinkive.android.trade_bz.a_stock.bll.ToadyMoneyServicesImpl;
import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;
import com.thinkive.android.trade_bz.a_stock.activity.TodayMoneyActivity;
import com.thinkive.android.trade_bz.a_stock.adapter.TodayMoneyAdapter;
import com.thinkive.android.trade_bz.views.PullToRefresh.PullToRefreshBase;
import com.thinkive.android.trade_bz.views.PullToRefresh.PullToRefreshListView;
import com.thinkive.android.trade_bz.utils.DateUtils;

import java.util.ArrayList;

/**
 * 当日资金流水
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/6/25
 */
public class ToadyMoneyFragment extends AbsBaseFragment {
    /**
     * 资金流水数据适配器
     */
    private TodayMoneyAdapter mTodayMoneyAdapter;
    /**
     * 资金流水Fragment的宿主
     */
    private TodayMoneyActivity mTodayMoneyActivity;
    /**
     * 该Fragement的业务类
     */
    private ToadyMoneyServicesImpl mServices;
    /**
     * 控制当日资金流失
     */
    private TodayMoneyViewController mController;
    /**
     *  ListView
     */
    private ListView mListView;
    /**
     * 自定义的listView对象
     */
    private PullToRefreshListView mRefreshListView;
    /**
     * 如果没有数据就显示该图片
     */
    private LinearLayout mLinNoData;
    /**
     *   正在加载的旋转进度条
     */
    private LinearLayout mLinLoading;

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
        mLinNoData = (LinearLayout) view.findViewById(R.id.lin_not_data_set);
    }

    @Override
    protected void setListeners() {
        registerListener(AbsBaseController.ON_LISTVIEW_REFLASH, mRefreshListView, mController);
    }

    @Override
    protected void initData() {
        mTodayMoneyActivity = (TodayMoneyActivity) getActivity();
        mTodayMoneyAdapter = new TodayMoneyAdapter(mTodayMoneyActivity);
        mServices = new ToadyMoneyServicesImpl(this);
        mController = new TodayMoneyViewController(this);

    }

    @Override
    protected void initViews() {
        //调用业务类中，初始化当日资金流水的方法
        mServices.requestTodayMoney();
        //设置禁止上拉加载更多
        mRefreshListView.setPullLoadEnabled(false);
        setTheme();
    }

    @Override
    protected void setTheme() {

    }

    /**
     * 接收 ToadyMoneyServicesImp 业务类传递过来的当日资金流水数据
     *
     * @param dataList
     */
    public void onGetTodayMoneyWater(ArrayList<TodayMoneyBean> dataList) {
        if (dataList == null || dataList.size() == 0) {
            mRefreshListView.setVisibility(View.GONE);
            mLinNoData.setVisibility(View.VISIBLE);
            mLinLoading.setVisibility(View.GONE);
        } else {
            mLinLoading.setVisibility(View.GONE);
            mRefreshListView.setVisibility(View.VISIBLE);
            mLinNoData.setVisibility(View.GONE);
            mTodayMoneyAdapter.setListData(dataList);
            mListView.setAdapter(mTodayMoneyAdapter);
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
        mServices.requestTodayMoney();
    }

    /**
     * 左滑时间监听
     */
    public void onSwipe() {
        mTodayMoneyActivity.finish();
    }
}

class TodayMoneyViewController extends AbsBaseController implements
        PullToRefreshListView.OnRefreshListener {

    private ToadyMoneyFragment mFragment = null;

    public TodayMoneyViewController(ToadyMoneyFragment Fragment) {
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
