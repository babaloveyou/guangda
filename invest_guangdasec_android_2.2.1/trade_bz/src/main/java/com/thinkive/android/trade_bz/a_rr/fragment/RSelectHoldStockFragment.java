package com.thinkive.android.trade_bz.a_rr.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_rr.activity.RSelectHoldStockActivity;
import com.thinkive.android.trade_bz.a_rr.bll.RSelectHoldStockServiceImpl;
import com.thinkive.android.trade_bz.a_stock.adapter.MyStoreListViewAdapterForBuysale;
import com.thinkive.android.trade_bz.a_stock.bean.MyStoreStockBean;
import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;
import com.thinkive.android.trade_bz.a_stock.fragment.AbsBaseFragment;
import com.thinkive.android.trade_bz.utils.DateUtils;
import com.thinkive.android.trade_bz.views.PullToRefresh.PullToRefreshBase;
import com.thinkive.android.trade_bz.views.PullToRefresh.PullToRefreshListView;

import java.util.ArrayList;


/**
 *   融资融券--查询--股份持仓
 * @author 张雪梅
 * @company Thinkive
 * @date 15/8/17
 */
public class RSelectHoldStockFragment extends AbsBaseFragment {
    /**
     * 我的持仓数据的LisView的适配器
     */
    private MyStoreListViewAdapterForBuysale mAdapter;
    /**
     *Fragment的宿主
     */
    private RSelectHoldStockActivity mActivity;
    /**
     *该Fragement的业务类
     */
    private RSelectHoldStockServiceImpl mServices;
    /**
     * 该类的控制器
     */
    private RSelectHoldStockController mController;
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
        mActivity = (RSelectHoldStockActivity) getActivity();
        mAdapter=new MyStoreListViewAdapterForBuysale(mActivity);
        mController = new RSelectHoldStockController(this);
        mServices = new RSelectHoldStockServiceImpl(this);

    }

    @Override
    protected void initViews() {
        mListView.addHeaderView(LayoutInflater.from(mActivity).inflate(R.layout.head_a_my_hold_store, null));
        mServices.requestAllHoldList();
        //设置禁止上拉加载更多
        mRefreshListView.setPullLoadEnabled(false);
        setTheme();
    }

    @Override
    protected void setTheme() {

    }

    /**
     * 得到当前持仓的数据列表
     * @param datalist
     */
    public void getCurrentHoldData(ArrayList<MyStoreStockBean> datalist){
        if(datalist == null || datalist.size()==0){
            mLinNoData.setVisibility(View.VISIBLE);
            mLinLoading.setVisibility(View.GONE);
            mRefreshListView.setVisibility(View.GONE);
        }else{
            mLinNoData.setVisibility(View.GONE);
            mLinLoading.setVisibility(View.GONE);
            mRefreshListView.setVisibility(View.VISIBLE);
            mAdapter.setListData(datalist);
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
        mServices.requestAllHoldList();
    }
}

/**
 * 控制类
 */
class RSelectHoldStockController extends AbsBaseController implements
        PullToRefreshListView.OnRefreshListener {
    private RSelectHoldStockFragment mFragment;

    public RSelectHoldStockController(RSelectHoldStockFragment fragment) {
        mFragment = fragment;
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
