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
import com.thinkive.android.trade_bz.a_rr.adapter.CreditHistoryTradeAdapter;
import com.thinkive.android.trade_bz.a_rr.bean.RSelectHistoryTradeBean;
import com.thinkive.android.trade_bz.a_rr.bll.CreditHistoryTradeServicesImpl;
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
public class CreditHistoryTradeFragment extends AbsBaseFragment implements OnDateSetListener {
    /**
     * 承载历史成交数据的ListView
     */
    private ExpandableLayoutListView mListView;
    /**
     * 历史成交数据适配器
     */
    private CreditHistoryTradeAdapter mAdapter;
    /**
     * 历史成交Fragment的宿主
     */
    private CreditHistoryEntrustOrTradeActivity mActivity;
    /**
     * 该Fragement的业务类
     */
    private CreditHistoryTradeServicesImpl mServices;
    /**
     * 如果没有数据就显示该图片
     */
    private LinearLayout mLiNoData;
    /**
     * 该类的控制器
     */
    private CreditHistoryTradeViewController mController;
    /**
     * 查询对账单开始时间
     */
    private String mBegin;
    /**
     * 查询队长单结束时间
     */
    private String mEnd;
    /**
     * 自定义的listView对象
     */
    private ExpandRefreshListView mPullToRefreshListView;
    /**
     * 正在加载的旋转进度条
     */
    private LinearLayout mLlLoading;
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
        View rootView = inflater.inflate(R.layout.fragment_a_history_trade, null);
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
        mLlLoading = (LinearLayout) view.findViewById(R.id.lin_loading_set);
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
        mAdapter = new CreditHistoryTradeAdapter(mActivity);
        mServices = new CreditHistoryTradeServicesImpl(this);
        mController = new CreditHistoryTradeViewController(this);
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
            mServices.reuqestHistoryTrade(mBegin, mEnd);
        } else {
            mBegin = TradeUtils.getLastWeek();
            mEnd = TradeUtils.getYesterday();
            mTvDateBegin.setText(mBegin);
            mTvDateEnd.setText(mEnd);
            mServices.reuqestHistoryTrade(mBegin, mEnd);
        }
        //设置禁止上拉加载更多
        mPullToRefreshListView.setPullLoadEnabled(false);
        setTheme();
    }

    @Override
    protected void setTheme() {

    }

    /**
     * 接收业务类传递过来的历史成交数据
     *
     * @param dataList
     */
    public void onGetHistoryTradeData(ArrayList<RSelectHistoryTradeBean> dataList) {
        if (dataList == null || dataList.size() == 0) {
            mLlLoading.setVisibility(View.GONE);
            mLiNoData.setVisibility(View.VISIBLE);
            mPullToRefreshListView.setVisibility(View.GONE);
        } else {
            mLlLoading.setVisibility(View.GONE);
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
            mPullToRefreshListView.setVisibility(View.GONE);
            mLiNoData.setVisibility(View.GONE);
            mLlLoading.setVisibility(View.VISIBLE);
            mServices.reuqestHistoryTrade(mBegin, mEnd);
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
        mServices.reuqestHistoryTrade(mBegin, mEnd);
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
 * 历史成交控制器
 */
class CreditHistoryTradeViewController extends AbsBaseController implements
        ExpandRefreshListView.OnRefreshListener, View.OnClickListener {

    private CreditHistoryTradeFragment mFragment;

    public CreditHistoryTradeViewController(CreditHistoryTradeFragment tradeFragment) {
        this.mFragment = tradeFragment;
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

