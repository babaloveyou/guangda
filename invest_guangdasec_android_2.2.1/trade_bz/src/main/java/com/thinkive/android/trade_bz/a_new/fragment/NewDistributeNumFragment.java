package com.thinkive.android.trade_bz.a_new.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_new.activity.NewStockMainActivity;
import com.thinkive.android.trade_bz.a_new.adapter.NewDistributeAdapter;
import com.thinkive.android.trade_bz.a_new.bean.NewDistributeNumBean;
import com.thinkive.android.trade_bz.a_new.bll.NewDistributeServicesImpl;
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
 * 新股配号Fragment
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/6/25
 */
public class NewDistributeNumFragment extends AbsBaseFragment {
    /**
     * 新股配号的业务类
     */
    private NewDistributeServicesImpl mServices;
    /**
     *新股配号的宿主
     */
    private NewStockMainActivity mActivity;
    /**
     *新股配号的适配器
     */
    private NewDistributeAdapter mAdapter;
    /**
     *控制新股配号
     */
    private NewDistributeViewController mController;
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
    /**
     * 用户类型，用户是用普通账户还是融资融券账户进入的本类
     */
    private String mUserType;
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
    /**
     * 开始时间
     */
    private String mBegin;
    /**
     * 结束时间
     */
    private String mEnd;
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
        mRefreshListView = (PullToRefreshListView) view.findViewById(R.id.lv_refresh_list);
        mListView = mRefreshListView.getRefreshableView();
        mListView.setDivider(null);
        mLinLoading = (LinearLayout) view.findViewById(R.id.lin_loading_set);
        mLinNoDataSet = (LinearLayout) view.findViewById(R.id.lin_not_data_set);
        mLinDateBegin = (LinearLayout) view.findViewById(R.id.lin_select_data_start);
        mLinDateEnd = (LinearLayout) view.findViewById(R.id.lin_select_data_end);
        mTvDateBegin=(TextView)view.findViewById(R.id.tv_set_data_start);
        mTvDateEnd=(TextView)view.findViewById(R.id.tv_set_data_end);
        mTvDateSelect=(TextView)view.findViewById(R.id.tv_select_data);
    }

    @Override
    protected void setListeners() {
        registerListener(AbsBaseController.ON_LISTVIEW_REFLASH, mRefreshListView, mController);
        registerListener(AbsBaseController.ON_CLICK, mLinDateBegin, mController);
        registerListener(AbsBaseController.ON_CLICK, mLinDateEnd, mController);
        registerListener(AbsBaseController.ON_CLICK, mTvDateSelect, mController);
    }

    @Override
    protected void initData() {
        Bundle bundle = getArguments();
        if(bundle != null){
            mUserType = bundle.getString("userType");
        }
        mActivity =(NewStockMainActivity)getActivity();
        mServices =new NewDistributeServicesImpl(this,mUserType);
        mAdapter =new NewDistributeAdapter(mActivity);
        mController=new NewDistributeViewController(this);
        mDateSelect=new DatePickerSelect(mActivity);
    }

    @Override
    protected void initViews() {
        mBegin = TradeUtils.getLastWeek();
        mEnd = TradeUtils.getCurrentDate();
        //调用业务类中，初始化新股配号数据的方法
        mServices.requestDisbute(mBegin,mEnd);
        //设置禁止上拉加载更多
        mRefreshListView.setPullLoadEnabled(false);
        mTvDateBegin.setText(mBegin);
        mTvDateEnd.setText(mEnd);
        setTheme();
    }


    @Override
    protected void setTheme() {

    }

    /**
     * 业务类传递过来的新股配号数据
     * @param dataList
     */
    public void getDistributeData(ArrayList<NewDistributeNumBean> dataList) {
        if (dataList == null || dataList.size()==0){
            mLinNoDataSet.setVisibility(View.VISIBLE);
            mRefreshListView.setVisibility(View.GONE);
            mLinLoading.setVisibility(View.GONE);
        }else {
            mRefreshListView.setVisibility(View.VISIBLE);
            mLinNoDataSet.setVisibility(View.GONE);
            mLinLoading.setVisibility(View.GONE);
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
        }else {
            mServices.requestDisbute(mBegin, mEnd);
            mRefreshListView.setVisibility(View.GONE);
            mLinNoDataSet.setVisibility(View.GONE);
            mLinLoading.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 被下拉时执行
     */
    public void onDownRefresh() {
        mServices.requestDisbute(mBegin,mEnd);
    }

}

class NewDistributeViewController extends AbsBaseController implements PullToRefreshListView.OnRefreshListener,
       View.OnClickListener{

    private NewDistributeNumFragment mFragment = null;

    public NewDistributeViewController(NewDistributeNumFragment Fragment) {
        mFragment = Fragment;
    }

    @Override
    public void register(int i, View view) {
        switch (i) {
            case ON_LISTVIEW_REFLASH:
                ((PullToRefreshListView)view).setOnRefreshListener(this);
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
