package com.thinkive.android.trade_bz.a_hk.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_hk.adapter.HKCapitalNotificationAdapter;
import com.thinkive.android.trade_bz.a_hk.bean.HKCapitalNotificationBean;
import com.thinkive.android.trade_bz.a_stock.bll.BasicServiceImpl;
import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;
import com.thinkive.android.trade_bz.a_stock.fragment.AbsBaseFragment;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.request.HK301638;
import com.thinkive.android.trade_bz.utils.DateUtils;
import com.thinkive.android.trade_bz.utils.ToastUtils;
import com.thinkive.android.trade_bz.utils.TradeUtils;
import com.thinkive.android.trade_bz.views.DatePickerSelect;
import com.thinkive.android.trade_bz.views.PullToRefresh.PullToRefreshBase;
import com.thinkive.android.trade_bz.views.PullToRefresh.PullToRefreshListView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 创建者     舒旺
 * 创建时间   2016/5/28 19:48
 * 描述	   通知信息查询
 * 更新者     $Author$
 * 更新时间   $Date$
 */
public class HKCapitalNotificationFragment extends AbsBaseFragment implements PullToRefreshListView.OnRefreshListener {

    private HKCapitalNotificationController mHkCapitalNotificationController;
    private DatePickerSelect mDateSelect;
    private Context mContext;
    private String mBegin;
    private String mEnd;
    private HKCapitalNotificationServiceImpl mHkCapitalNotificationService;
    private ListView mRefreshableView;
    private HKCapitalNotificationAdapter mHkCapitalNotificationAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        mContext = getActivity();
        View rootViwe = inflater.inflate(R.layout.fragment_common_refresh_list_date, null);
        findViews(rootViwe);
        initData();
        initViews();
        setListeners();
        return rootViwe;
    }

    private LinearLayout mLinSelectDataStart;
    private TextView mTvDateBegin;
    private LinearLayout mLinSelectDataEnd;
    private TextView mTvDateEnd;
    private TextView mTvSelectData;
    private LinearLayout mLinLoadingSet;
    private PullToRefreshListView mLvRefreshList;
    private LinearLayout mLinNotDataSet;
    private TextView mTvNoDataSet;

    @Override
    protected void findViews(View view) {
        mLinSelectDataStart = (LinearLayout) view.findViewById(R.id.lin_select_data_start);
        mTvDateBegin = (TextView) view.findViewById(R.id.tv_set_data_start);
        mLinSelectDataEnd = (LinearLayout) view.findViewById(R.id.lin_select_data_end);
        mTvDateEnd = (TextView) view.findViewById(R.id.tv_set_data_end);
        mTvSelectData = (TextView) view.findViewById(R.id.tv_select_data);
        mLinLoadingSet = (LinearLayout) view.findViewById(R.id.lin_loading_set);
        mLvRefreshList = (PullToRefreshListView) view.findViewById(R.id.lv_refresh_list);
        mLvRefreshList.setOnRefreshListener(this);
        mRefreshableView = mLvRefreshList.getRefreshableView();
        mRefreshableView.setDivider(null);
        mLinNotDataSet = (LinearLayout) view.findViewById(R.id.lin_not_data_set);
        mTvNoDataSet = (TextView) view.findViewById(R.id.tv_no_data_set);
    }

    @Override
    protected void setListeners() {
        registerListener(AbsBaseController.ON_CLICK, mLinSelectDataStart, mHkCapitalNotificationController);
        registerListener(AbsBaseController.ON_CLICK, mLinSelectDataEnd, mHkCapitalNotificationController);
        registerListener(AbsBaseController.ON_CLICK, mTvSelectData, mHkCapitalNotificationController);
    }

    @Override
    protected void initData() {
        mHkCapitalNotificationController = new HKCapitalNotificationController(this);
        mDateSelect = new DatePickerSelect(mContext);
        mHkCapitalNotificationService = new HKCapitalNotificationServiceImpl(this);
        mHkCapitalNotificationAdapter = new HKCapitalNotificationAdapter(mContext);
    }

    @Override
    protected void initViews() {
        //调用业务类中，初始化历史成交数据的方法
        mBegin = TradeUtils.getLastWeek();
        mEnd = TradeUtils.getYesterday();
        mTvDateBegin.setText(mBegin);
        mTvDateEnd.setText(mEnd);
        //设置禁止上拉加载更多
        mLvRefreshList.setPullLoadEnabled(false);

        mHkCapitalNotificationService.reuqestDeliveryServiceImpl(mBegin, mEnd);
    }

    @Override
    protected void setTheme() {

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
            ToastUtils.toast(mContext,mContext.getResources().getString(R.string.date_select_error));
        }else {
            mLinLoadingSet.setVisibility(View.VISIBLE);
            mHkCapitalNotificationService.reuqestDeliveryServiceImpl(mBegin, mEnd);
        }
    }

    /**
     * 请求数据成功后回调给主线程
     */
    public void setNotificationData(ArrayList<HKCapitalNotificationBean> notificationData) {
        if (null == notificationData || notificationData.size() <= 0) {
            //没有数据
            mLinLoadingSet.setVisibility(View.GONE);
            mLinNotDataSet.setVisibility(View.VISIBLE);
            mLvRefreshList.setVisibility(View.GONE);
        } else {
            mLinLoadingSet.setVisibility(View.GONE);
            mLinNotDataSet.setVisibility(View.GONE);
            mLvRefreshList.setVisibility(View.VISIBLE);
            mHkCapitalNotificationAdapter.setListData(notificationData);
            mRefreshableView.setAdapter(mHkCapitalNotificationAdapter);
        }
        refreshComplete();
    }


    /**
     * 刷新完成,收回下拉联
     */
    public void refreshComplete() {
        mLvRefreshList.onPullDownRefreshComplete();
        mLvRefreshList.onPullUpRefreshComplete();
        mLvRefreshList.setLastUpdatedLabel(DateUtils.getDateString("HH:mm:ss"));
    }

    /**
     * 被下拉时执行
     */
    public void onDownRefresh() {
        mHkCapitalNotificationService.reuqestDeliveryServiceImpl(mBegin, mEnd);
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        onDownRefresh();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {

    }
}


class HKCapitalNotificationController extends AbsBaseController implements View.OnClickListener {

    private HKCapitalNotificationFragment mHKCapitalNotificationFragment;

    public HKCapitalNotificationController(HKCapitalNotificationFragment hkCapitalNotificationFragment) {
        this.mHKCapitalNotificationFragment = hkCapitalNotificationFragment;
    }

    @Override
    public void register(int eventType, View view) {
        switch (eventType) {
            case ON_CLICK:
                view.setOnClickListener(this);
                break;
        }

    }

    @Override
    public void onClick(View v) {
        int clickId = v.getId();
        if (clickId == R.id.lin_select_data_start) { //调用系统日期  开始时间
            mHKCapitalNotificationFragment.onClickBeginDate();
        } else if (clickId == R.id.lin_select_data_end) {//调用系统日期  结束时间
            mHKCapitalNotificationFragment.onClickEndDate();
        } else if (clickId == R.id.tv_select_data) {
            mHKCapitalNotificationFragment.onClickSelectDate();
        }
    }
}


class HKCapitalNotificationServiceImpl extends BasicServiceImpl {

    private HKCapitalNotificationFragment mHKCapitalLiabilitiesFragment;

    public HKCapitalNotificationServiceImpl(HKCapitalNotificationFragment hkCapitalLiabilitiesFragment) {
        this.mHKCapitalLiabilitiesFragment = hkCapitalLiabilitiesFragment;
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onStop() {

    }

    public void reuqestDeliveryServiceImpl(String begin, String end) {
        HashMap<String, String> map = new HashMap<>();
        map.put("begin_date", begin);
        map.put("end_date", end);
        new HK301638(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                ArrayList<HKCapitalNotificationBean> dataList = bundle.getParcelableArrayList(HK301638.BUNDLE_KEY_HK_HISTORY_TRADE);
                mHKCapitalLiabilitiesFragment.setNotificationData(dataList);
            }

            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(HK301638.ERROR_INFO));
            }
        }).request();
    }
}