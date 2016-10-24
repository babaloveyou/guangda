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
import com.thinkive.android.trade_bz.a_hk.adapter.HKCapitalNoTradeAdapter;
import com.thinkive.android.trade_bz.a_hk.bean.HKNoTradeBean;
import com.thinkive.android.trade_bz.a_stock.bll.BasicServiceImpl;
import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;
import com.thinkive.android.trade_bz.a_stock.fragment.AbsBaseFragment;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.request.HK301629;
import com.thinkive.android.trade_bz.utils.ToastUtils;
import com.thinkive.android.trade_bz.utils.TradeUtils;
import com.thinkive.android.trade_bz.views.DatePickerSelect;
import com.thinkive.android.trade_bz.views.PullToRefresh.PullToRefreshListView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 创建者     舒旺
 * 创建时间   2016/5/27 20:45
 * 描述	     未交明细查询
 * 更新者     $Author$
 * 更新时间   $Date$
 */
public class HKCapitalNoTradeFragment extends AbsBaseFragment {
    private HKCapitalNoServicesImpl mServices;
    private Context mContext;
    private DatePickerSelect mDateSelect;
    private HKCapitalNoTradeController mHkCapitalNoTradeController;
    private String mBegin;
    private String mEnd;

    private LinearLayout mLinSelectDataStart;
    private TextView mTvDateBegin;
    private LinearLayout mLinSelectDataEnd;
    private TextView mTvDateEnd;
    private TextView mTvSelectData;
    private LinearLayout mLinLoadingSet;
    private PullToRefreshListView mLvRefreshList;
    private LinearLayout mLinNotDataSet;
    private HKCapitalNoTradeAdapter mHkCapitalNoTradeAdapter;
    private ListView mRefreshableView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        mContext = getActivity();
        View rootview = inflater.inflate(R.layout.fragment_common_refresh_list_date, null);
        initData();
        findViews(rootview);
        setListeners();
        initViews();

        return rootview;
    }


    @Override
    protected void setListeners() {
        registerListener(AbsBaseController.ON_CLICK, mLinSelectDataStart, mHkCapitalNoTradeController);
        registerListener(AbsBaseController.ON_CLICK, mLinSelectDataEnd, mHkCapitalNoTradeController);
        registerListener(AbsBaseController.ON_CLICK, mTvSelectData, mHkCapitalNoTradeController);
    }

    @Override
    protected void initData() {
        mDateSelect = new DatePickerSelect(mContext);
        mHkCapitalNoTradeController = new HKCapitalNoTradeController(this);
        mServices = new HKCapitalNoServicesImpl(this);
        mHkCapitalNoTradeAdapter = new HKCapitalNoTradeAdapter(mContext);
    }

    @Override
    protected void initViews() {
        //调用业务类中，初始化历史成交数据的方法
        mBegin = TradeUtils.getLastWeek();
        mEnd = TradeUtils.getYesterday();
        mTvDateBegin.setText(mBegin);
        mTvDateEnd.setText(mEnd);
        mServices.reuqestHKCapitalNoTrade(mBegin, mEnd);
        //设置禁止上拉加载更多
        mLvRefreshList.setPullLoadEnabled(false);
    }

    @Override
    protected void setTheme() {

    }


    @Override
    protected void findViews(View view) {
        mLinSelectDataStart = (LinearLayout) view.findViewById(R.id.lin_select_data_start);
        mTvDateBegin = (TextView) view.findViewById(R.id.tv_set_data_start);
        mLinSelectDataEnd = (LinearLayout) view.findViewById(R.id.lin_select_data_end);
        mTvDateEnd = (TextView) view.findViewById(R.id.tv_set_data_end);
        mTvSelectData = (TextView) view.findViewById(R.id.tv_select_data);
        mLinLoadingSet = (LinearLayout) view.findViewById(R.id.lin_loading_set);
        mLvRefreshList = (PullToRefreshListView) view.findViewById(R.id.lv_refresh_list);
        mRefreshableView = mLvRefreshList.getRefreshableView();
        mRefreshableView.setDivider(null);
        mLinNotDataSet = (LinearLayout) view.findViewById(R.id.lin_not_data_set);

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
            mServices.reuqestHKCapitalNoTrade(mBegin, mEnd);
        }
    }

    /**
     * 请求数据成功 回到给主线程  设置适配器
     */
    public void setNoTradeData(ArrayList<HKNoTradeBean> beanList) {
        if (null == beanList || beanList.size() <= 0) {
            //没有数据
            mLinLoadingSet.setVisibility(View.GONE);
            mLinNotDataSet.setVisibility(View.VISIBLE);
            mLvRefreshList.setVisibility(View.GONE);
        } else {
            mLinLoadingSet.setVisibility(View.GONE);
            mLinNotDataSet.setVisibility(View.GONE);
            mLvRefreshList.setVisibility(View.VISIBLE);
            mHkCapitalNoTradeAdapter.setListData(beanList);
            mRefreshableView.setAdapter(mHkCapitalNoTradeAdapter);
        }

    }
}

/**
 * 请求数据
 */
class HKCapitalNoServicesImpl extends BasicServiceImpl {
    HKCapitalNoTradeFragment mHKCapitalNoTradeFragment;

    public HKCapitalNoServicesImpl(HKCapitalNoTradeFragment hkCapitalNoTradeFragment) {
        this.mHKCapitalNoTradeFragment = hkCapitalNoTradeFragment;
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onStop() {

    }

    /**
     * 请求未交明细
     */
    public void reuqestHKCapitalNoTrade(String begin, String end) {

        HashMap<String, String> map = new HashMap<String, String>();
        map.put("begin_date", begin);
        map.put("end_date", end);
        new HK301629(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                ArrayList<HKNoTradeBean> dataList = bundle.getParcelableArrayList(HK301629.BUNDLE_KEY_HK_HISTORY_TRADE);
                mHKCapitalNoTradeFragment.setNoTradeData(dataList);
            }

            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(HK301629.ERROR_INFO));
            }
        }).request();
    }
}


/**
 * 未交明细查询控制器
 */
class HKCapitalNoTradeController extends AbsBaseController implements View.OnClickListener {

    private HKCapitalNoTradeFragment mHKCapitalNoTradeFragment;

    public HKCapitalNoTradeController(HKCapitalNoTradeFragment hkCapitalNoTradeFragment) {

        this.mHKCapitalNoTradeFragment = hkCapitalNoTradeFragment;
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
            mHKCapitalNoTradeFragment.onClickBeginDate();
        } else if (clickId == R.id.lin_select_data_end) {//调用系统日期  结束时间
            mHKCapitalNoTradeFragment.onClickEndDate();
        } else if (clickId == R.id.tv_select_data) {
            mHKCapitalNoTradeFragment.onClickSelectDate();
        }
    }
}