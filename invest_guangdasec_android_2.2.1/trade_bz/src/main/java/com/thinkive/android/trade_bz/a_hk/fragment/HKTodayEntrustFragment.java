package com.thinkive.android.trade_bz.a_hk.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.android.thinkive.framework.compatible.TKFragmentActivity;
import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_hk.adapter.HKTodayEntrustAdapter;
import com.thinkive.android.trade_bz.a_hk.bean.HKTodayEntrustBean;
import com.thinkive.android.trade_bz.a_hk.bll.HKTodayEntrustServicesImpl;
import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;
import com.thinkive.android.trade_bz.a_stock.fragment.AbsBaseFragment;
import com.thinkive.android.trade_bz.utils.DateUtils;
import com.thinkive.android.trade_bz.views.PullToRefresh.PullToRefreshBase;
import com.thinkive.android.trade_bz.views.PullToRefresh.PullToRefreshListView;

import java.util.ArrayList;

/**
 * 港股通 今日委托
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/11/2
 */
public class HKTodayEntrustFragment extends AbsBaseFragment {
    /**
     *今日委托数据适配器
     */
    private HKTodayEntrustAdapter mTodayEntrustAdapter;
    /**
     *今日委托 Fragment的宿主
     */
    private TKFragmentActivity mActivity;
    /**
     *该Fragement的业务类
     */
    private HKTodayEntrustServicesImpl mServices;
    /**
     *控制今日委托
     */
    private HKTodayEntrustController mController;
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
    private LinearLayout mLinNoData;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_hk_today_entrust, null);
        findViews(rootView);
        initData();
        setListeners();
        initViews();
        return rootView;
    }

    @Override
    protected void findViews(View view) {
        mRefreshListView=(PullToRefreshListView)view.findViewById(R.id.lv_refresh_list);
        mListView=mRefreshListView.getRefreshableView();
        mListView.setDivider(null);
        mLinLoading = (LinearLayout) view.findViewById(R.id.lin_loading_set);
        mLinNoData =(LinearLayout)view.findViewById(R.id.lin_not_data_set);
    }

    @Override
    protected void setListeners() {
        registerListener(AbsBaseController.ON_LISTVIEW_REFLASH, mRefreshListView, mController);
    }

    @Override
    protected void initData() {
        mActivity = (TKFragmentActivity) getActivity();
        mTodayEntrustAdapter=new HKTodayEntrustAdapter(mActivity);
        mServices=new HKTodayEntrustServicesImpl(this);
        mController=new HKTodayEntrustController(this);
    }

    @Override
    protected void initViews() {
        //调用业务类中，初始化今日委托数据的方法
        mServices.requestTodayEntrust();
        //设置禁止上拉加载更多
        mRefreshListView.setPullLoadEnabled(false);
        setTheme();
    }

    @Override
    protected void setTheme() {

    }
    /**
     * 当日委托的数据
     * @param dataList
     */
    public void onGetTodayEntrustData(ArrayList<HKTodayEntrustBean> dataList) {
        if (dataList == null || dataList.size()==0){
            mLinLoading.setVisibility(View.GONE);
            mLinNoData.setVisibility(View.VISIBLE);
            mRefreshListView.setVisibility(View.GONE);
        } else {
            mLinLoading.setVisibility(View.GONE);
            mLinNoData.setVisibility(View.GONE);
            mRefreshListView.setVisibility(View.VISIBLE);
            mTodayEntrustAdapter.setListData(dataList);
            mListView.setAdapter(mTodayEntrustAdapter);
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
     * 被下拉时执行
     */
    public void onDownRefresh() {
        mServices.requestTodayEntrust();
    }
}

class HKTodayEntrustController extends AbsBaseController implements
        PullToRefreshListView.OnRefreshListener{

    private HKTodayEntrustFragment mFragment = null;

    public HKTodayEntrustController(HKTodayEntrustFragment Fragment) {
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


