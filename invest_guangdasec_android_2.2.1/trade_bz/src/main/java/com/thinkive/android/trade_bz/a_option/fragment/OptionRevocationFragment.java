package com.thinkive.android.trade_bz.a_option.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.android.thinkive.framework.compatible.TKFragmentActivity;
import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_option.adapter.OptionRevocationAdapter;
import com.thinkive.android.trade_bz.a_option.bean.OptionRevocationBean;
import com.thinkive.android.trade_bz.a_option.bean.OptionRevocationResultBean;
import com.thinkive.android.trade_bz.a_option.bll.OptionRevocationServicesImpl;
import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;
import com.thinkive.android.trade_bz.a_stock.fragment.AbsBaseFragment;
import com.thinkive.android.trade_bz.utils.DateUtils;
import com.thinkive.android.trade_bz.utils.ToastUtils;
import com.thinkive.android.trade_bz.utils.TradeUtils;
import com.thinkive.android.trade_bz.views.PullToRefresh.PullToRefreshBase;
import com.thinkive.android.trade_bz.views.PullToRefresh.PullToRefreshListView;

import java.util.ArrayList;

/**
 * 个股期权--撤单
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/6/15
 */
public class OptionRevocationFragment extends AbsBaseFragment {
    private TKFragmentActivity mActivity;
    private OptionRevocationServicesImpl mServices;
    private OptionRevocationController mController;
    private OptionRevocationAdapter mAdapter;
    private ListView mListView;
    /**
     *如果没有数据就显示该图片
     */
    private LinearLayout mLiNoData;
    /**
     *自定义的listView对象
     */
    private PullToRefreshListView mPullToRefreshListView;
    /**
     *正在加载的旋转进度条
     */
    private LinearLayout mLoading;
    /**
     * 可撤单数据集
     */
    private ArrayList<OptionRevocationBean> mDataList;
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
        mPullToRefreshListView=(PullToRefreshListView)view.findViewById(R.id.lv_refresh_list);
        mListView=mPullToRefreshListView.getRefreshableView();
        mListView.setDivider(null);
        mLoading = (LinearLayout) view.findViewById(R.id.lin_loading_set);
        mLiNoData =(LinearLayout)view.findViewById(R.id.lin_not_data_set);
    }

    @Override
    protected void setListeners() {
        registerListener(AbsBaseController.ON_LISTVIEW_REFLASH, mPullToRefreshListView, mController);
    }

    @Override
    protected void initData() {
        mActivity = (TKFragmentActivity) getActivity();
        mServices=new OptionRevocationServicesImpl(this);
        mAdapter =new OptionRevocationAdapter(mActivity,mServices);
        mController=new OptionRevocationController(this);
    }

    @Override
    protected void initViews() {
        //调用业务类中，初始化今日委托数据的方法
        mServices.requestRevocationList();
        //设置禁止上拉加载更多
        mPullToRefreshListView.setPullLoadEnabled(false);
        setTheme();
    }

    @Override
    protected void setTheme() {

    }

    /**
     * 点击全部撤单
     */
    public void onClickAllRevocation(){
        if (TradeUtils.isFastClick()) {
            return;
        }
        StringBuilder entrustNo = new StringBuilder();
        StringBuilder exchangeType = new StringBuilder();
        if(mDataList != null && mDataList.size() > 0){
            for(OptionRevocationBean bean : mDataList){
                entrustNo.append(bean.getEntrust_no());
                entrustNo.append("|");
                exchangeType.append(bean.getExchange_type());
                exchangeType.append("|");
            }
            entrustNo.deleteCharAt(entrustNo.lastIndexOf("|"));
            exchangeType.deleteCharAt(entrustNo.lastIndexOf("|"));
            mServices.execRevocation(entrustNo.toString(), exchangeType.toString());
        }
    }

    /**
     * 接收业务类传递过来的今日委托数据
     * @param dataList
     */
    public void onGetRevocationData(ArrayList<OptionRevocationBean> dataList) {
        if(dataList == null || dataList.size()==0){

        }else{mLiNoData.setVisibility(View.VISIBLE);
            mLoading.setVisibility(View.GONE);
            mPullToRefreshListView.setVisibility(View.GONE);
            mDataList = dataList;
            mLiNoData.setVisibility(View.GONE);
            mLoading.setVisibility(View.GONE);
            mPullToRefreshListView.setVisibility(View.VISIBLE);
            mAdapter.setListData(dataList);
            mListView.setAdapter(mAdapter);
        }
        refreshComplete();
    }

    /**
     * 得到测单结果
     * @param data
     */
    public void onGetRevocationResult(OptionRevocationResultBean data) {
        ToastUtils.toast(mActivity,data.getEntrust_no());
    }
    /**
     * 刷新完成,收回下拉联
     */
    public void refreshComplete() {
        mPullToRefreshListView.onPullDownRefreshComplete();
        mPullToRefreshListView.onPullUpRefreshComplete();
        mPullToRefreshListView.setLastUpdatedLabel(DateUtils.getDateString("HH:mm:ss"));
    }

    /**
     * 被下拉时执行
     */
    public void onDownRefresh() {
        mServices.requestRevocationList();
    }
}

class OptionRevocationController extends AbsBaseController implements
        PullToRefreshListView.OnRefreshListener{

    private OptionRevocationFragment mFragment = null;

    public OptionRevocationController(OptionRevocationFragment Fragment) {
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


