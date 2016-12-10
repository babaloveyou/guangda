package com.thinkive.android.trade_bz.a_rr.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;
import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_rr.activity.CreditHistoryEntrustOrTradeActivity;
import com.thinkive.android.trade_bz.a_rr.adapter.CreditHistoryEntrustAdapter;
import com.thinkive.android.trade_bz.a_rr.bean.RSelectHistoryEntrustBean;
import com.thinkive.android.trade_bz.a_rr.bll.CreditHistoryEntrustServicesImpl;
import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;
import com.thinkive.android.trade_bz.a_stock.fragment.AbsBaseFragment;
import com.thinkive.android.trade_bz.others.constants.Constants;
import com.thinkive.android.trade_bz.utils.DateUtils;
import com.thinkive.android.trade_bz.utils.ToastUtils;
import com.thinkive.android.trade_bz.utils.TradeUtils;
import com.thinkive.android.trade_bz.views.ExpandRefreshListView;
import com.thinkive.android.trade_bz.views.ExpandableLayoutListView;
import com.thinkive.android.trade_bz.views.PullToRefresh.PullToRefreshBase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2016/12/2.
 */
public class CreditHistoryEntrustFragment  extends AbsBaseFragment implements OnDateSetListener {
    /**
     * 自定义的listView对象
     */
    private ExpandRefreshListView mPullToRefreshListView;
    /**
     * 承载历史委托数据的ListView
     */
    private ExpandableLayoutListView mListView;
    /**
     * 历史委托数据适配器
     */
    private CreditHistoryEntrustAdapter mHistoryEntrustAdapter;
    /**
     * 历史委托 Fragment的宿主
     */
    private CreditHistoryEntrustOrTradeActivity mActivity;
    /**
     * 该Fragement的业务类
     */
    private CreditHistoryEntrustServicesImpl mServices;
    /**
     * 如果没有数据就显示该图片
     */
    private LinearLayout mLiNoData;
    /**
     * 该类的控制器
     */
    private CreditHistoryEntrustViewController mController;
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
     * 时间选择器
     */
    TimePickerDialog mDialogYearMonthDay;
    private int mCurrentPickerTarget;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_a_history_entrust, null);
        findViews(rootView);
        initData();
        setListeners();
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        initViews();
    }

    @Override
    protected void findViews(View view) {
        mPullToRefreshListView = (ExpandRefreshListView) view.findViewById(R.id.lv_refresh_list);
        mListView = mPullToRefreshListView.getRefreshableView();
        mListView.setDivider(null);
        mLoading = (LinearLayout) view.findViewById(R.id.lin_loading_set);
        mLiNoData = (LinearLayout) view.findViewById(R.id.lin_not_data_set);
        mLinDateBegin = (LinearLayout) view.findViewById(R.id.lin_select_data_start);
        mLinDateEnd = (LinearLayout) view.findViewById(R.id.lin_select_data_end);
        mTvDateBegin = (TextView) view.findViewById(R.id.tv_set_data_start);
        mTvDateEnd = (TextView) view.findViewById(R.id.tv_set_data_end);
        mTvDateSelect = (TextView) view.findViewById(R.id.tv_select_data);
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
        mActivity = (CreditHistoryEntrustOrTradeActivity) getActivity();
        mHistoryEntrustAdapter = new CreditHistoryEntrustAdapter(mActivity);
        mServices = new CreditHistoryEntrustServicesImpl(this);
        mController = new CreditHistoryEntrustViewController(this);
        mDialogYearMonthDay = new TimePickerDialog.Builder()
                .setType(Type.YEAR_MONTH_DAY)
                .setTitleStringId("选择日期")
                .setCallBack(this)
                .build();
    }

    @Override
    protected void initViews() {
        if (!TextUtils.isEmpty(Constants.CREDIT_BEGIN_DATE)) {
            mBegin = Constants.CREDIT_BEGIN_DATE;
            mEnd = Constants.CREDIT_TOTAY_DATE;
            mTvDateBegin.setText(mBegin);
            mTvDateEnd.setText(mEnd);
            mServices.requestHistoryEntrust(mBegin, mEnd);
        } else {
            mBegin = TradeUtils.getLastWeek();
            mEnd = TradeUtils.getYesterday();
            mTvDateBegin.setText(mBegin);
            mTvDateEnd.setText(mEnd);
            mServices.requestHistoryEntrust(mBegin, mEnd);
        }

        //调用业务类中，初始化历史委托数据的方法
        //设置禁止上拉加载更多
        mPullToRefreshListView.setPullLoadEnabled(false);
        setTheme();
    }

    @Override
    protected void setTheme() {
    }

    /**
     * 接收业务类传递过来的数据
     *
     * @param dataList
     */
    public void onGetHistoryEntrustData(ArrayList<RSelectHistoryEntrustBean> dataList) {
        if (dataList == null || dataList.size() == 0) {
            mLoading.setVisibility(View.GONE);
            mPullToRefreshListView.setVisibility(View.GONE);
            mLiNoData.setVisibility(View.VISIBLE);
        } else {
            mLoading.setVisibility(View.GONE);
            mLiNoData.setVisibility(View.GONE);
            mPullToRefreshListView.setVisibility(View.VISIBLE);
            mHistoryEntrustAdapter.setListData(dataList);
            mListView.setAdapter(mHistoryEntrustAdapter);
        }
        refreshComplete();
    }

    /**
     * 开始日期
     */
    public void onClickBeginDate() {
        mDialogYearMonthDay.show(getActivity().getSupportFragmentManager(), "year_month_day");
        mCurrentPickerTarget = 0;
    }

    /**
     * 结束日期
     */
    public void onClickEndDate() {
        mDialogYearMonthDay.show(getActivity().getSupportFragmentManager(), "year_month_day");
        mCurrentPickerTarget = 1;
    }

    /**
     * 查询
     */
    public void onClickSelectDate() {
        if (TradeUtils.isFastClick()) {
            return;
        }
        mBegin = mTvDateBegin.getText().toString().trim();
        mEnd = mTvDateEnd.getText().toString().trim();
        if (TradeUtils.checkOutDate(mBegin, mEnd)) {
            ToastUtils.toast(mActivity, mActivity.getResources().getString(R.string.date_select_error));
        } else {
            mLiNoData.setVisibility(View.GONE);
            mLoading.setVisibility(View.VISIBLE);
            mPullToRefreshListView.setVisibility(View.GONE);
            mServices.requestHistoryEntrust(mBegin, mEnd);
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
        mServices.requestHistoryEntrust(mBegin, mEnd);
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
}

/**
 * 历史委托的控制类
 */
class CreditHistoryEntrustViewController extends AbsBaseController implements
        ExpandRefreshListView.OnRefreshListener, View.OnClickListener {

    private CreditHistoryEntrustFragment mFragment;

    public CreditHistoryEntrustViewController(CreditHistoryEntrustFragment fragment) {
        this.mFragment = fragment;
    }

    @Override
    public void register(int eventType, View view) {
        switch (eventType) {
            case ON_LISTVIEW_REFLASH:
                ((ExpandRefreshListView) view).setOnRefreshListener(this);
                break;
            case ON_CLICK:
                view.setOnClickListener(this);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        int clickId = v.getId();
        if (clickId == R.id.lin_select_data_start) {
            mFragment.onClickBeginDate();
        } else if (clickId == R.id.lin_select_data_end) {
            mFragment.onClickEndDate();
        } else if (clickId == R.id.tv_select_data) {
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

