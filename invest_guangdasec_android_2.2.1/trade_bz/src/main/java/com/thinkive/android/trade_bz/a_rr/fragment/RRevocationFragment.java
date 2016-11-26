package com.thinkive.android.trade_bz.a_rr.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_rr.adapter.RRevocationAdapter;
import com.thinkive.android.trade_bz.a_rr.bean.RRevocationBean;
import com.thinkive.android.trade_bz.a_rr.bll.RRevocationServiceImpl;
import com.thinkive.android.trade_bz.a_stock.activity.MultiCreditTradeActivity;
import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;
import com.thinkive.android.trade_bz.a_stock.fragment.AbsBaseFragment;
import com.thinkive.android.trade_bz.utils.DateUtils;
import com.thinkive.android.trade_bz.views.PullToRefresh.PullToRefreshBase;
import com.thinkive.android.trade_bz.views.PullToRefresh.PullToRefreshListView;

import java.util.ArrayList;

/**
 *  融资融券--下单--撤单（303017）
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/8/24
 */
public class RRevocationFragment extends AbsBaseFragment {
    /**
     * 宿主
     */
    private MultiCreditTradeActivity mActivity;
    /**
     * 控制器
     */
    private RRevocationController mController;
    /**
     * 业务类
     */
    private RRevocationServiceImpl mServices;
    /**
     * 撤单列表适配器
     */
    private RRevocationAdapter mAdapter;
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
    private ArrayList<RRevocationBean> mDataList;
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
        mActivity = (MultiCreditTradeActivity) getActivity();
        mController = new RRevocationController(this);
        mServices = new RRevocationServiceImpl(this);
        mAdapter=new RRevocationAdapter(mActivity,mServices);
    }

    @Override
    protected void initViews() {
        //设置禁止上拉加载更多
        mRefreshListView.setPullLoadEnabled(false);
        //请求撤单数据
        mServices.requestRevocationData();
        setTheme();
    }

    @Override
    protected void setTheme() {

    }

    /**
     * 得到撤单数据列表
     */
    public void getRevocationData(ArrayList<RRevocationBean> dataList) {
        //若没有数据
        if (dataList == null || dataList.size() == 0) {
            mLinNoData.setVisibility(View.VISIBLE);
            mLinLoading.setVisibility(View.GONE);
            mRefreshListView.setVisibility(View.GONE);
        } else {
            mDataList = dataList;
            mLinNoData.setVisibility(View.GONE);
            mLinLoading.setVisibility(View.GONE);
            mRefreshListView.setVisibility(View.VISIBLE);
            mAdapter.setListData(dataList);
            mListView.setAdapter(mAdapter);
        }
        refreshComplete();
    }

    /**
     * 点击title栏右侧按钮"全部撤单"
     */
    public void clickTitleRightText(){
        StringBuilder builder = new StringBuilder();
        if(mDataList != null && mDataList.size() > 0){
            for(RRevocationBean bean : mDataList){
                builder.append(bean.getEntrust_no());
                builder.append("|");
            }
            builder.deleteCharAt(builder.lastIndexOf("|"));
            mServices.execRevocation(builder.toString(), "", "1");
        }
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
        mServices.requestRevocationData();
    }

    public void refreshAdapter() {
        mAdapter.notifyDataSetChanged();
    }
}

/**
 * 控制类
 */
class RRevocationController extends AbsBaseController implements
        PullToRefreshListView.OnRefreshListener {

    private RRevocationFragment mFragment;

    public RRevocationController(RRevocationFragment fragment) {
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
