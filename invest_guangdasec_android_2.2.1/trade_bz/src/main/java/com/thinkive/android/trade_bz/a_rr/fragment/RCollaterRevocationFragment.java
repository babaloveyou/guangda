package com.thinkive.android.trade_bz.a_rr.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.android.thinkive.framework.compatible.TKFragmentActivity;
import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_rr.adapter.RCollaterRevocationAdapter;
import com.thinkive.android.trade_bz.a_rr.bean.RCollaterRevocationBean;
import com.thinkive.android.trade_bz.a_rr.bll.RCollaterRevocationServiceImpl;
import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;
import com.thinkive.android.trade_bz.a_stock.fragment.AbsBaseFragment;
import com.thinkive.android.trade_bz.utils.DateUtils;
import com.thinkive.android.trade_bz.views.PullToRefresh.PullToRefreshBase;
import com.thinkive.android.trade_bz.views.PullToRefresh.PullToRefreshListView;

import java.util.ArrayList;

/**
 *  融资融券--划转--撤单
 * @author 张雪梅
 * @company Thinkive
 * @date 15/8/13
 */
public class RCollaterRevocationFragment extends AbsBaseFragment {
    /**
     * 融资融券的Activity
     */
    private TKFragmentActivity mActivity;
    /**
     * 控制器
     */
    private RCollaterRevocationViewController mController;
    /**
     * 当前持仓适配器
     */
    private RCollaterRevocationAdapter mAdapter;
    /**
     * 业务类
     */
    private RCollaterRevocationServiceImpl mServices;
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
        mActivity = (TKFragmentActivity) getActivity();
        mController=new RCollaterRevocationViewController(this);
        mServices=new RCollaterRevocationServiceImpl(this);
        mAdapter=new RCollaterRevocationAdapter(mActivity,mServices);
    }

    @Override
    protected void initViews() {
        //设置禁止上拉加载更多
        mRefreshListView.setPullLoadEnabled(false);
        //请求数据
        mServices.requestRevocationList();
        setTheme();
    }

    @Override
    protected void setTheme() {

    }
    /**
     * 得到撤单列表数据
     */
    public void getRollaterRevocationList(ArrayList<RCollaterRevocationBean> dataList){
        if(dataList == null || dataList.size()==0){
            mLinLoading.setVisibility(View.GONE);
            mLinNoData.setVisibility(View.VISIBLE);
            mRefreshListView.setVisibility(View.GONE);
        }else{
            mLinLoading.setVisibility(View.GONE);
            mLinNoData.setVisibility(View.GONE);
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
     * {@link #mRefreshListView} 被下拉时执行
     */
    public void onDownRefresh() {
        mServices.requestRevocationList();
    }
}

class RCollaterRevocationViewController extends AbsBaseController implements PullToRefreshListView.OnRefreshListener{

    private RCollaterRevocationFragment mFragment;

    public RCollaterRevocationViewController(RCollaterRevocationFragment Fragment) {
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

