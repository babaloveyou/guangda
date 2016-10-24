package com.thinkive.android.trade_bz.a_out.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.bean.PublicUseBean;
import com.thinkive.android.trade_bz.a_out.bean.FundHoldBean;
import com.thinkive.android.trade_bz.a_out.bll.FundShareSetServiceImpl;
import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;
import com.thinkive.android.trade_bz.a_out.adapter.FundShareSetAdapter;
import com.thinkive.android.trade_bz.a_stock.fragment.AbsBaseFragment;
import com.thinkive.android.trade_bz.views.PullToRefresh.PullToRefreshBase;
import com.thinkive.android.trade_bz.views.PullToRefresh.PullToRefreshListView;
import com.thinkive.android.trade_bz.utils.DateUtils;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.util.ArrayList;

/**
 * 场外基金分红设置
 * @author 张雪梅
 * @company Thinkive
 * @date 15/10/27
 */
public class FundShareSetFragment extends AbsBaseFragment {
    private AppCompatActivity mActivity;
    private FundShareSetServiceImpl mServices;
    private FundShareSetController mController;
    /**
     * 持仓数据的适配器
     */
    private FundShareSetAdapter mAdapter;
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

    @Nullable
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
        mActivity=(AppCompatActivity)getActivity();
        mServices=new FundShareSetServiceImpl(this);
        mController=new FundShareSetController(this);
        mAdapter=new FundShareSetAdapter(mActivity,this);
    }

    @Override
    protected void initViews() {
        //设置禁止上拉加载更多
        mRefreshListView.setPullLoadEnabled(false);
        //请求基金持仓数据
        mServices.requestHoldStockList();
    }

    @Override
    protected void setTheme() {

    }
    /**
     * 获得基金的持仓数据
     * @param dataList
     */
    public void onGetFundHoldList(ArrayList<FundHoldBean> dataList) {
        if(dataList == null || dataList.size() == 0){
            mRefreshListView.setVisibility(View.GONE);
            mLinLoading.setVisibility(View.GONE);
            mLinNoDataSet.setVisibility(View.VISIBLE);
        }else{
            mRefreshListView.setVisibility(View.VISIBLE);
            mLinLoading.setVisibility(View.GONE);
            mLinNoDataSet.setVisibility(View.GONE);
            mAdapter.setListData(dataList);
            mListView.setAdapter(mAdapter);
        }
        refreshComplete();
    }

    /**
     * 得到修改分红方式的结果
     * @param result
     */
    public void onGetShareSetResult(PublicUseBean result) {
        ToastUtils.toast(mActivity, getResources().getString(R.string.fund_share_tip0)+result.getSerial_no());
    }

    /**
     * 修改分红方式时执行
     */
    public void onSetShareSet(String shareSet,String fundCode,String company){
        mServices.requestShareSet(shareSet, fundCode,company);
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
        mServices.requestHoldStockList();
    }
    /**
     * 左滑时间监听
     */
    public void onSwipe() {
        mActivity.finish();
    }
}


class FundShareSetController extends AbsBaseController implements
        PullToRefreshListView.OnRefreshListener{

    private FundShareSetFragment mFragment = null;

    public FundShareSetController(FundShareSetFragment Fragment) {
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



