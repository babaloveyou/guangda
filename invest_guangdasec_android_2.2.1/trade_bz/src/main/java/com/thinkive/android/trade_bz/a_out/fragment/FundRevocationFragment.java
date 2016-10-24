package com.thinkive.android.trade_bz.a_out.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_out.bean.FundRevocationBean;
import com.thinkive.android.trade_bz.a_out.bll.FundRevocationServicesImpl;
import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;
import com.thinkive.android.trade_bz.a_out.activity.FundRevocationActivity;
import com.thinkive.android.trade_bz.a_out.adapter.FundRevocationAdapter;
import com.thinkive.android.trade_bz.a_stock.fragment.AbsBaseFragment;
import com.thinkive.android.trade_bz.views.PullToRefresh.PullToRefreshBase;
import com.thinkive.android.trade_bz.views.PullToRefresh.PullToRefreshListView;
import com.thinkive.android.trade_bz.utils.DateUtils;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.util.ArrayList;

/**
 * 基金交易--查询--撤单
 * Announcements：
 *
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/7/24
 */

public class FundRevocationFragment extends AbsBaseFragment {
    /**
     * 今日委托 Fragment的宿主
     */
    private FundRevocationActivity mActivity;
    /**
     * 控制器
     */
    private FundRevocationController mController;
    /**
     * 基金撤单业务类
     */
    private FundRevocationServicesImpl mServices;
    /**
     * 撤单数据适配器
     */
    private FundRevocationAdapter mAdapter;
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
        mActivity = (FundRevocationActivity) getActivity();
        mController = new FundRevocationController(this);
        mServices = new FundRevocationServicesImpl(this);
        mAdapter = new FundRevocationAdapter(mActivity, mServices);
    }

    @Override
    protected void initViews() {
        //调用业务类中，初始化基金撤单数据的方法
        mServices.requestRevocation();
        //设置禁止上拉加载更多
        mRefreshListView.setPullLoadEnabled(false);
        setTheme();
    }

    @Override
    protected void setTheme() {
    }

//    /**
//     * 撤单列表项单击事件
//     */
//    public void onItemClickRevocationList(int position) {
//        FundRevocationBean bean = mAdapter.getItem(position);
//        mServices.requestExecRevocation(bean.getFund_code(), bean.getEntrust_date(), bean.getEntrust_no());
//    }

    /**
     * 获得基金撤单的数据列表
     *
     * @param dataList
     */
    public void onGetRevocationData(ArrayList<FundRevocationBean> dataList) {
        if (dataList == null || dataList.size() == 0) {
            mLinNoData.setVisibility(View.VISIBLE);
            mLinLoading.setVisibility(View.GONE);
            mRefreshListView.setVisibility(View.GONE);
        } else {
            mLinNoData.setVisibility(View.GONE);
            mLinLoading.setVisibility(View.GONE);
            mRefreshListView.setVisibility(View.VISIBLE);
            mAdapter.setListData(dataList);
            mListView.setAdapter(mAdapter);
            refreshComplete();
        }
    }

    /**
     * 撤单成功
     */
    public void onExecRevocationSuccess(String msg) {
        ToastUtils.toast(mActivity, msg);
        mServices.requestRevocation();
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
     * 被下拉时执行
     */
    public void onDownRefresh() {
        mServices.requestRevocation();
    }
    /**
     * 左滑时间监听
     */
    public void onSwipe() {
        mActivity.finish();
    }
}


/**
 * FSelectRevocationFragment的控制器
 */
class FundRevocationController extends AbsBaseController implements
        PullToRefreshListView.OnRefreshListener {

    private FundRevocationFragment mFragment = null;

    public FundRevocationController(FundRevocationFragment Fragment) {
        mFragment = Fragment;
    }

    @Override
    public void register(int i, View view) {
        switch (i) {
            case ON_LISTVIEW_REFLASH:
                ((PullToRefreshListView) view).setOnRefreshListener(this);
                break;
//            case ON_ITEM_CLICK:
//                ((AdapterView) view).setOnItemClickListener(this);
//                break;
        }
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        mFragment.onDownRefresh();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {

    }

//    @Override
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        mFragment.onItemClickRevocationList(position);
//    }
}
