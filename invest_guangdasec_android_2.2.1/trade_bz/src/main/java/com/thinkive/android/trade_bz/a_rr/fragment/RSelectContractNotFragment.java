package com.thinkive.android.trade_bz.a_rr.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_rr.activity.RSelectContractNotActivity;
import com.thinkive.android.trade_bz.a_rr.adapter.RSelectContractNotAdapter;
import com.thinkive.android.trade_bz.a_rr.bean.RSelectContractBean;
import com.thinkive.android.trade_bz.a_rr.bll.RSelectContractNotServiceImpl;
import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;
import com.thinkive.android.trade_bz.a_stock.fragment.AbsBaseFragment;
import com.thinkive.android.trade_bz.utils.DateUtils;
import com.thinkive.android.trade_bz.views.PullToRefresh.PullToRefreshBase;
import com.thinkive.android.trade_bz.views.PullToRefresh.PullToRefreshListView;

import java.util.ArrayList;


/**
 * 融资融券--查询--合约查询（303035）
 *    未偿还合约
 * Announcements：
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/8/18
 */
public class RSelectContractNotFragment extends AbsBaseFragment {
    /**
     *数据适配器
     */
    private RSelectContractNotAdapter mAdapter;
    /**
     * Fragment的宿主
     */
    private RSelectContractNotActivity mActivity;
    /**
     *该Fragement的业务类
     */
    private RSelectContractNotServiceImpl mServices;
    /**
     * 该类的控制器
     */
    private RContractNotController mController;
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
        mActivity = (RSelectContractNotActivity) getActivity();
        mAdapter = new RSelectContractNotAdapter(mActivity);
        mServices = new RSelectContractNotServiceImpl(this);
        mController = new RContractNotController(this);
    }

    @Override
    protected void initViews() {
        //调用业务类中，初始化数据的方法
        mServices.requestContractNotData();
        //设置禁止上拉加载更多
        mRefreshListView.setPullLoadEnabled(false);
        setTheme();
    }

    @Override
    protected void setTheme() {
    }

    /**
     * 获得数据
     * @param dataList
     */
    public void getContractNotData(ArrayList<RSelectContractBean> dataList) {
        if (dataList == null || dataList.size() == 0) {
            mRefreshListView.setVisibility(View.GONE);
            mLinLoading.setVisibility(View.GONE);
            mLinNoData.setVisibility(View.VISIBLE);
        }else {
            mAdapter.setListData(dataList);
            mListView.setAdapter(mAdapter);
            mLinLoading.setVisibility(View.GONE);
            mLinNoData.setVisibility(View.GONE);
            mRefreshListView.setVisibility(View.VISIBLE);
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
        mServices.requestContractNotData();
    }
}

/**
 * 控制类
 */
class RContractNotController extends AbsBaseController implements
        PullToRefreshListView.OnRefreshListener {

    private RSelectContractNotFragment mFragment;

    public RContractNotController(RSelectContractNotFragment fragment) {
        this.mFragment = fragment;
    }
    @Override
    public void register(int eventType, View view) {
        switch (eventType) {
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



