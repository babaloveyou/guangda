package com.thinkive.android.trade_bz.a_stock.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;
import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.activity.HistoryMoneyActivity;
import com.thinkive.android.trade_bz.a_stock.adapter.HistoryMoneyAdapter;
import com.thinkive.android.trade_bz.a_stock.bean.StatementAccountBean;
import com.thinkive.android.trade_bz.a_stock.bll.HistoryMoneyServiceImpl;
import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;
import com.thinkive.android.trade_bz.utils.DateUtils;
import com.thinkive.android.trade_bz.utils.ToastUtils;
import com.thinkive.android.trade_bz.utils.TradeUtils;
import com.thinkive.android.trade_bz.views.PullToRefresh.PullToRefreshBase;
import com.thinkive.android.trade_bz.views.PullToRefresh.PullToRefreshListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * 当日资金流水
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/6/25
 */
public class HistoryMoneyFragment extends AbsBaseFragment implements OnDateSetListener {
    /**
     * 资金流水数据适配器
     */
    private HistoryMoneyAdapter mTodayMoneyAdapter;
    /**
     * 资金流水Fragment的宿主
     */
    private HistoryMoneyActivity mTodayMoneyActivity;
    /**
     * 该Fragement的业务类
     */
    private HistoryMoneyServiceImpl mServices;
    /**
     * 控制当日资金流失
     */
    private TodayMoneyViewController mController;
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
     * 时间选择器
     */
    TimePickerDialog mDialogYearMonthDay;
    private int mCurrentPickerTarget;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_common_refresh_list_with_time_picker, null);
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
        mTodayMoneyActivity = (HistoryMoneyActivity) getActivity();
        mTodayMoneyAdapter = new HistoryMoneyAdapter(mTodayMoneyActivity);
        mServices = new HistoryMoneyServiceImpl(this);
        mController = new TodayMoneyViewController(this);
        mDialogYearMonthDay = new TimePickerDialog.Builder()
                .setType(Type.YEAR_MONTH_DAY)
                .setTitleStringId("选择日期")
                .setCallBack(this)
                .build();
    }

    @Override
    protected void initViews() {
        mBegin = TradeUtils.getLastWeek();
        mEnd = TradeUtils.getYesterday();
        mTvDateBegin.setText(mBegin);
        mTvDateEnd.setText(mEnd);
        //调用业务类中，初始化当日资金流水的方法
        mServices.requestHistoryMoney(mBegin,mEnd);
        //设置禁止上拉加载更多
        mRefreshListView.setPullLoadEnabled(false);
        setTheme();
    }

    @Override
    protected void setTheme() {

    }

    /**
     * 接收 ToadyMoneyServicesImp 业务类传递过来的当日资金流水数据
     *
     * @param dataList
     */
    public void onGetTodayMoneyWater(ArrayList<StatementAccountBean> dataList) {
        if (dataList == null || dataList.size() == 0) {
            mRefreshListView.setVisibility(View.GONE);
            mLinNoData.setVisibility(View.VISIBLE);
            mLinLoading.setVisibility(View.GONE);
        } else {
            mLinLoading.setVisibility(View.GONE);
            mRefreshListView.setVisibility(View.VISIBLE);
            mLinNoData.setVisibility(View.GONE);
            mTodayMoneyAdapter.setListData(dataList);
            mListView.setAdapter(mTodayMoneyAdapter);
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
     *被下拉时执行
     */
    public void onDownRefresh() {
        mServices.requestHistoryMoney(mBegin,mEnd);
    }

    /**
     * 左滑时间监听
     */
    public void onSwipe() {
        mTodayMoneyActivity.finish();
    }

    @Override
    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        Date date = new Date(millseconds);
        c.setTime(date);
        String dataString = df.format(c.getTime());
        if (mCurrentPickerTarget == 0) {
            mTvDateBegin.setText(dataString.trim());
        } else {
            mTvDateEnd.setText(dataString.trim());
        }
    }

    public void onClickBeginDate() {
        mDialogYearMonthDay.show(getActivity().getSupportFragmentManager(), "year_month_day");
        mCurrentPickerTarget = 0;
    }

    public void onClickEndDate() {
        mDialogYearMonthDay.show(getActivity().getSupportFragmentManager(), "year_month_day");
        mCurrentPickerTarget = 1;
    }

    public void onClickSelectDate() {
        if (TradeUtils.isFastClick()) {
            return;
        }
        mBegin=mTvDateBegin.getText().toString().trim();
        mEnd=mTvDateEnd.getText().toString().trim();
        if(TradeUtils.checkOutDate(mBegin,mEnd)){
            ToastUtils.toast(getActivity(),getActivity().getResources().getString(R.string.date_select_error));
        }else{
            mLinNoData.setVisibility(View.GONE);
            mLinLoading.setVisibility(View.VISIBLE);
            mRefreshListView.setVisibility(View.GONE);
            mServices.requestHistoryMoney(mBegin,mEnd);
        }
    }
}

class TodayMoneyViewController extends AbsBaseController implements
        PullToRefreshListView.OnRefreshListener, View.OnClickListener {

    private HistoryMoneyFragment mFragment = null;

    public TodayMoneyViewController(HistoryMoneyFragment Fragment) {
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
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        mFragment.onDownRefresh();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {

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
}
