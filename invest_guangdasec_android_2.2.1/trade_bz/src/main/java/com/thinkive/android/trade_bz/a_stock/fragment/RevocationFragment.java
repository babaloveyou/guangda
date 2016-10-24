package com.thinkive.android.trade_bz.a_stock.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.bean.RevocationBean;
import com.thinkive.android.trade_bz.a_stock.bll.RevocationServicesImpl;
import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;
import com.thinkive.android.trade_bz.a_stock.activity.MultiTradeActivity;
import com.thinkive.android.trade_bz.a_stock.adapter.RevocationAdapter;
import com.thinkive.android.trade_bz.views.PullToRefresh.PullToRefreshBase;
import com.thinkive.android.trade_bz.views.PullToRefresh.PullToRefreshListView;
import com.thinkive.android.trade_bz.utils.DateUtils;

import java.util.ArrayList;

/**
 * 撤单Fragment
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/6/16
 */

public class RevocationFragment extends AbsBaseFragment {
    /**
     * 撤单数据适配器
     */
    private RevocationAdapter mRevocationAdapter;
    /**
     * 撤单Fragment的宿主
     */
    private MultiTradeActivity mMultiTradeActivity;
    /**
     * 该Fragment的业务类
     */
    private RevocationServicesImpl mRevocationServices;
    /**
     * 撤单的控制器
     */
    private RevocationViewController mController;
    /**
     * 界面的根布局
     */
    private View mRootView;
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
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.fragment_common_refresh_list, null);
            findViews(mRootView);
            initData();
            setListeners();
            initViews();
        }
        return mRootView;
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
        mMultiTradeActivity = (MultiTradeActivity) getActivity();
        mRevocationServices = new RevocationServicesImpl(this);
        mRevocationAdapter = new RevocationAdapter(mMultiTradeActivity, mRevocationServices);
        mController = new RevocationViewController(this);
    }

    @Override
    protected void initViews() {
        //加载更多
        mRefreshListView.setPullLoadEnabled(false);
        setTheme();
    }

    @Override
    protected void setTheme() {

    }

    @Override
    public void onResume() {
        super.onResume();
        // 调用业务类中，初始化撤单数据的方法
        mRevocationServices.requestRevocation();
    }

    /**
     * 接收RevocationServicesImpl 业务类传递过来的撤单数据
     *
     * @param dataList
     *         从请求类传来的持仓数据集合
     */
    public void onGetStoreData(ArrayList<RevocationBean> dataList) {
        if (dataList == null || dataList.size() == 0) {
            mRefreshListView.setVisibility(View.GONE);
            mLinNoData.setVisibility(View.VISIBLE);
            mLinLoading.setVisibility(View.GONE);
        } else {
            mLinLoading.setVisibility(View.GONE);
            mRefreshListView.setVisibility(View.VISIBLE);
            mLinNoData.setVisibility(View.GONE);
            mRevocationAdapter.setListData(dataList);
            mListView.setAdapter(mRevocationAdapter);
            refreshComplete();
        }
    }

    /**
     * 撤单后刷新数据
     */
    public void refreshAdapter() {
        mRevocationAdapter.notifyDataSetChanged();
    }

    /**
     * 下拉刷新完成,收回下拉联
     */
    public void refreshComplete() {
        mRefreshListView.onPullDownRefreshComplete();
        mRefreshListView.onPullUpRefreshComplete();
        mRefreshListView.setLastUpdatedLabel(DateUtils.getDateString("HH:mm:ss"));
    }

    /**
     * 刷新请求完执行
     */
    public void onDownRefresh() {
        mRevocationServices.requestRevocation();
    }
}

class RevocationViewController extends AbsBaseController implements
        PullToRefreshListView.OnRefreshListener, ListView.OnItemClickListener {

    private RevocationFragment mFragment = null;

    public RevocationViewController(RevocationFragment Fragment) {
        mFragment = Fragment;
    }

    @Override
    public void register(int i, View view) {
        switch (i) {
            case ON_LISTVIEW_REFLASH:
                ((PullToRefreshListView) view).setOnRefreshListener(this);
                break;
            case ON_ITEM_CLICK:
                ((ListView) view).setOnItemClickListener(this);
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //  mFragment.onItemClick();
    }
}
