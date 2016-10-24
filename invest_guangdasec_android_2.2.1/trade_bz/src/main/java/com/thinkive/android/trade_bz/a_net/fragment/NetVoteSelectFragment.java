package com.thinkive.android.trade_bz.a_net.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.thinkive.framework.compatible.TKFragmentActivity;
import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_net.adapter.NetVoteEntrustSelectAdapter;
import com.thinkive.android.trade_bz.a_net.bean.NetVoteEntrustSelectBean;
import com.thinkive.android.trade_bz.a_net.bll.NetVoteSelectServicesImpl;
import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;
import com.thinkive.android.trade_bz.a_stock.fragment.AbsBaseFragment;
import com.thinkive.android.trade_bz.utils.DateUtils;
import com.thinkive.android.trade_bz.utils.ToastUtils;
import com.thinkive.android.trade_bz.utils.TradeUtils;
import com.thinkive.android.trade_bz.views.DatePickerSelect;
import com.thinkive.android.trade_bz.views.PullToRefresh.PullToRefreshBase;
import com.thinkive.android.trade_bz.views.PullToRefresh.PullToRefreshListView;

import java.util.ArrayList;

/**
 * 网络投票查询页面
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/8/2
 */
public class NetVoteSelectFragment extends AbsBaseFragment {
    private TKFragmentActivity mActivity;
    private NetVoteSelectServicesImpl mServices;
    private NetVoteSelectController mController;
    private NetVoteEntrustSelectAdapter mAdapter;
    /**
     * 自定义的listView对象
     */
    private PullToRefreshListView mPullToRefreshListView;
    /**
     *承载数据的ListView
     */
    private ListView mListView;
    /**
     *如果没有数据就显示该图片
     */
    private LinearLayout mLiNoData;
    /**
     * 正在加载的旋转进度条
     */
    private LinearLayout mLoading;
    /**
     * 开始时间
     */
    private String mBegin;
    /**
     * 结束时间
     */
    private String mEnd;
    /**
     * 开始日期点击区域
     */
    private LinearLayout mLinDateBegin;
    /**
     * 结束日期点击区域
     */
    private LinearLayout mLinDateEnd;
    /**
     * 开始日期控件
     */
    private TextView mTvDateBegin;
    /**
     * 结束日期控件
     */
    private TextView mTvDateEnd;
    /**
     * 点击查询
     */
    private TextView mTvDateSelect;
    /**
     * 日期选择器
     */
    private DatePickerSelect mDateSelect;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_common_refresh_list_date, null);
        findViews(rootView);
        initData();
        setListeners();
        initViews();
        return rootView;
    }

    @Override
    protected void findViews(View view) {
        mPullToRefreshListView = (PullToRefreshListView) view.findViewById(R.id.lv_refresh_list);
        mListView = mPullToRefreshListView.getRefreshableView();
        mListView.setDivider(null);
        mLoading = (LinearLayout) view.findViewById(R.id.lin_loading_set);
        mLiNoData = (LinearLayout) view.findViewById(R.id.lin_not_data_set);
        mLinDateBegin = (LinearLayout) view.findViewById(R.id.lin_select_data_start);
        mLinDateEnd = (LinearLayout) view.findViewById(R.id.lin_select_data_end);
        mTvDateBegin=(TextView)view.findViewById(R.id.tv_set_data_start);
        mTvDateEnd=(TextView)view.findViewById(R.id.tv_set_data_end);
        mTvDateSelect=(TextView)view.findViewById(R.id.tv_select_data);
    }

    @Override
    protected void setListeners() {
        registerListener(AbsBaseController.ON_LISTVIEW_REFLASH, mPullToRefreshListView, mController);
        registerListener(AbsBaseController.ON_CLICK, mLinDateBegin, mController);
        registerListener(AbsBaseController.ON_CLICK, mLinDateEnd, mController);
        registerListener(AbsBaseController.ON_CLICK, mTvDateSelect, mController);
    }

    @Override
    protected void initData() {
        mActivity = (TKFragmentActivity) getActivity();
        mAdapter = new NetVoteEntrustSelectAdapter(mActivity);
        mServices = new NetVoteSelectServicesImpl(this);
        mController = new NetVoteSelectController(this);
        mDateSelect=new DatePickerSelect(mActivity);
    }

    @Override
    protected void initViews() {
        //调用业务类中，初始化历史委托数据的方法
        mBegin = TradeUtils.getLastWeek();
        mEnd = TradeUtils.getCurrentDate();
        mServices.requestVoteEntrustList(mBegin, mEnd);
        mTvDateBegin.setText(mBegin);
        mTvDateEnd.setText(mEnd);
        //设置禁止上拉加载更多
        mPullToRefreshListView.setPullLoadEnabled(false);
        setTheme();
    }

    @Override
    protected void setTheme() {}

    /**
     * 接收业务类传递过来的数据
     * @param dataList
     */
    public void onGetVoteEntrustList(ArrayList<NetVoteEntrustSelectBean> dataList) {
        if(dataList == null || dataList.size()==0){
            mLoading.setVisibility(View.GONE);
            mPullToRefreshListView.setVisibility(View.GONE);
            mLiNoData.setVisibility(View.VISIBLE);
        }else {
            mLoading.setVisibility(View.GONE);
            mLiNoData.setVisibility(View.GONE);
            mPullToRefreshListView.setVisibility(View.VISIBLE);
            mAdapter.setListData(dataList);
            mListView.setAdapter(mAdapter);
        }
        refreshComplete();
    }

    /**
     * 开始日期
     */
    public void onClickBeginDate(){
        mDateSelect.showDateDialog(mTvDateBegin,mTvDateBegin.getText().toString().trim());
    }

    /**
     * 结束日期
     */
    public void onClickEndDate(){
        mDateSelect.showDateDialog(mTvDateEnd,mTvDateEnd.getText().toString().trim());
    }

    /**
     * 查询
     */
    public void onClickSelectDate(){
        if (TradeUtils.isFastClick()) {
            return;
        }
        mBegin=mTvDateBegin.getText().toString().trim();
        mEnd=mTvDateEnd.getText().toString().trim();
        if(TradeUtils.checkOutDate(mBegin,mEnd)){
            ToastUtils.toast(mActivity,mActivity.getResources().getString(R.string.date_select_error));
        }else{
            mLiNoData.setVisibility(View.GONE);
            mLoading.setVisibility(View.VISIBLE);
            mPullToRefreshListView.setVisibility(View.GONE);
            mServices.requestVoteEntrustList(mBegin, mEnd);
        }
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
        mServices.requestVoteEntrustList(mBegin, mEnd);
    }
}

class NetVoteSelectController extends AbsBaseController implements
        PullToRefreshListView.OnRefreshListener, View.OnClickListener {

    private NetVoteSelectFragment mFragment;

    public NetVoteSelectController(NetVoteSelectFragment fragment) {
        this.mFragment = fragment;
    }

    @Override
    public void register(int eventType, View view) {
        switch (eventType) {
            case ON_LISTVIEW_REFLASH:
                ((PullToRefreshListView) view).setOnRefreshListener(this);
                break;
            case ON_CLICK:
                view.setOnClickListener(this);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        int clickId=v.getId();
        if(clickId == R.id.lin_select_data_start){
            mFragment.onClickBeginDate();
        }else if(clickId == R.id.lin_select_data_end){
            mFragment.onClickEndDate();
        }else if(clickId == R.id.tv_select_data){
            mFragment.onClickSelectDate();
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

