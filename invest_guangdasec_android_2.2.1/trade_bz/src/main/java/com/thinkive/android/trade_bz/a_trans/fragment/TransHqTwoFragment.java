package com.thinkive.android.trade_bz.a_trans.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.android.thinkive.framework.compatible.TKFragmentActivity;
import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;
import com.thinkive.android.trade_bz.a_stock.fragment.AbsBaseFragment;
import com.thinkive.android.trade_bz.a_trans.activity.TransSubHqThreeActivityTrade;
import com.thinkive.android.trade_bz.a_trans.adapter.TransTradeBuyOrSaleAdapter;
import com.thinkive.android.trade_bz.a_trans.bean.TransSubHqBean;
import com.thinkive.android.trade_bz.views.PullToRefresh.PullToRefreshListView;

import java.util.ArrayList;

/**
 * 定价申报行情列表
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/12/31
 */
public class TransHqTwoFragment extends AbsBaseFragment {
    private TransTradeBuyOrSaleAdapter mAdapter;
    private TKFragmentActivity mActivity;
    private TransHqTwoController mController;
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
        mListView.addHeaderView(LayoutInflater.from(getActivity()).inflate(R.layout.head_trans_hq_msg, null));
        mLinLoading = (LinearLayout) view.findViewById(R.id.lin_loading_set);
        mLinNoDataSet = (LinearLayout) view.findViewById(R.id.lin_not_data_set);
    }

    @Override
    protected void setListeners() {
        registerListener(AbsBaseController.ON_ITEM_CLICK, mListView, mController);
    }

    @Override
    protected void initData() {
        mActivity = (TKFragmentActivity) getActivity();
        mAdapter=new TransTradeBuyOrSaleAdapter(mActivity);
        mController=new TransHqTwoController(this);
        Bundle bundle = getArguments();
        if(bundle != null){
            ArrayList<TransSubHqBean> dataList = bundle.getParcelableArrayList("hq_data");
            if(dataList != null && dataList.size() > 0){
                getTodayTradeData(dataList);
            }
        }
    }

    @Override
    protected void initViews() {
        //设置禁止上拉加载更多
        mRefreshListView.setPullLoadEnabled(false);
        mRefreshListView.setPullRefreshEnabled(false);
    }

    @Override
    protected void setTheme() {}

    /**
     * 点击item
     */
    public void onClickItem(int position){
        if(position > 0){
            position = position -1;
            TransSubHqBean bean = mAdapter.getItem(position);
            Intent intent = new Intent(mActivity,TransSubHqThreeActivityTrade.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("trans_msg",bean);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }

    /**
     * 接收数据
     * @param dataList
     */
    public void getTodayTradeData(ArrayList<TransSubHqBean> dataList) {
        if(dataList == null || dataList.size() == 0){
            mRefreshListView.setVisibility(View.GONE);
            mLinLoading.setVisibility(View.GONE);
            mLinNoDataSet.setVisibility(View.VISIBLE);
        }else {
            mRefreshListView.setVisibility(View.VISIBLE);
            mLinLoading.setVisibility(View.GONE);
            mLinNoDataSet.setVisibility(View.GONE);
            mAdapter.setListData(dataList);
            mListView.setAdapter(mAdapter);
        }
    }
}

class TransHqTwoController extends AbsBaseController implements ListView.OnItemClickListener{

    private TransHqTwoFragment mFragment = null;

    public TransHqTwoController(TransHqTwoFragment Fragment) {
        mFragment = Fragment;
    }

    @Override
    public void register(int i, View view) {
        switch (i) {
            case ON_ITEM_CLICK:
                ((ListView)view).setOnItemClickListener(this);
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mFragment.onClickItem(position);
    }
}

