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
import com.thinkive.android.trade_bz.a_hk.adapter.HKCapitalTrustAdapter;
import com.thinkive.android.trade_bz.a_hk.bean.HKCapitalTrustBean;
import com.thinkive.android.trade_bz.a_stock.bll.BasicServiceImpl;
import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;
import com.thinkive.android.trade_bz.a_stock.fragment.AbsBaseFragment;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.request.HK301622;
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
 * 创建时间   2016/5/27 21:01
 * 描述	     投票委托查询
 * 更新者     $Author$
 * 更新时间   $Date$
 */
public class HKCapitalTrustFragment extends AbsBaseFragment {
    private LinearLayout mLinLoadingSet;
    private PullToRefreshListView mLvRefreshList;
    private CapitalTrustController mController;
    private LinearLayout mLinNotDataSet;
    private ListView mRefreshableView;
    private HKCapitalTrustServiceImpl mServices;
    private Context mContext;
    private HKCapitalTrustAdapter mHkCapitalTrustAdapter;
    private LinearLayout mLinDateBegin;
    private LinearLayout mLinDateEnd;
    private TextView mTvDateBegin;
    private TextView mTvDateEnd;
    private TextView mTvDateSelect;
    private DatePickerSelect mDateSelect;
    private String mBegin;
    private String mEnd;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        mContext = getActivity();
        View rootViw = inflater.inflate(R.layout.fragment_common_refresh_list_date, null);
        findViews(rootViw);
        initData();
        initViews();
        setListeners();
        return rootViw;
    }

    @Override
    protected void findViews(View view) {
        mLinLoadingSet = (LinearLayout) view.findViewById(R.id.lin_loading_set);
        mLvRefreshList = (PullToRefreshListView) view.findViewById(R.id.lv_refresh_list);
        mRefreshableView = mLvRefreshList.getRefreshableView();
        mRefreshableView.setDivider(null);
        mLinNotDataSet = (LinearLayout) view.findViewById(R.id.lin_not_data_set);
        mLinDateBegin = (LinearLayout) view.findViewById(R.id.lin_select_data_start);
        mLinDateEnd = (LinearLayout) view.findViewById(R.id.lin_select_data_end);
        mTvDateBegin=(TextView)view.findViewById(R.id.tv_set_data_start);
        mTvDateEnd=(TextView)view.findViewById(R.id.tv_set_data_end);
        mTvDateSelect=(TextView)view.findViewById(R.id.tv_select_data);
    }

    @Override
    protected void setListeners() {
        registerListener(AbsBaseController.ON_LISTVIEW_REFLASH, mLvRefreshList, mController);
        registerListener(AbsBaseController.ON_CLICK, mLinDateBegin, mController);
        registerListener(AbsBaseController.ON_CLICK, mLinDateEnd, mController);
        registerListener(AbsBaseController.ON_CLICK, mTvDateSelect, mController);
    }

    @Override
    protected void initData() {
        mServices = new HKCapitalTrustServiceImpl(this);
        mHkCapitalTrustAdapter = new HKCapitalTrustAdapter(mContext);
        mController = new CapitalTrustController(this);
        mDateSelect=new DatePickerSelect(mContext);

    }

    @Override
    protected void initViews() {
        //调用业务类中，初始化历史委托数据的方法
        mBegin = TradeUtils.getLastWeek();
        mEnd = TradeUtils.getCurrentDate();
        mServices.reuqeCorporateServiceImpl(mBegin, mEnd);
        //设置禁止上拉加载更多
        mLvRefreshList.setPullLoadEnabled(false);
        mTvDateBegin.setText(mBegin);
        mTvDateEnd.setText(mEnd);
        setTheme();
    }

    @Override
    protected void setTheme() {

    }

    /**
     * 请求网络回调给主线程
     * @param mData
     */
    public void setTrustData(ArrayList<HKCapitalTrustBean> mData) {
        if (null == mData || mData.size() <= 0) {
            //没有数据
            mLinLoadingSet.setVisibility(View.GONE);
            mLinNotDataSet.setVisibility(View.VISIBLE);
            mLvRefreshList.setVisibility(View.GONE);
        } else {
            mLinLoadingSet.setVisibility(View.GONE);
            mLinNotDataSet.setVisibility(View.GONE);
            mLvRefreshList.setVisibility(View.VISIBLE);
            mHkCapitalTrustAdapter.setListData(mData);
            mRefreshableView.setAdapter(mHkCapitalTrustAdapter);
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
            ToastUtils.toast(mContext,mContext.getResources().getString(R.string.date_select_error));
        }else {
            mServices.reuqeCorporateServiceImpl(mBegin, mEnd);
            mLvRefreshList.setVisibility(View.GONE);
            mLinNotDataSet.setVisibility(View.GONE);
            mLinLoadingSet.setVisibility(View.VISIBLE);
        }
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
        mServices.reuqeCorporateServiceImpl(mBegin, mEnd);
    }
}

class HKCapitalTrustServiceImpl extends BasicServiceImpl {


    private HKCapitalTrustFragment mHKCapitalLiabilitiesFragment;

    public HKCapitalTrustServiceImpl(HKCapitalTrustFragment hkCapitalLimitFragment) {
        this.mHKCapitalLiabilitiesFragment = hkCapitalLimitFragment;
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onStop() {

    }

    public void reuqeCorporateServiceImpl(String begin, String end) {
        HashMap<String, String> map = new HashMap<>();
        map.put("begin_date", begin);
        map.put("end_date", end);
        new HK301622(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                ArrayList<HKCapitalTrustBean> dataList = bundle.getParcelableArrayList(HK301622.BUNDLE_KEY_LIMIT);
                mHKCapitalLiabilitiesFragment.setTrustData(dataList);
            }

            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(HK301622.ERROR_INFO));
            }
        }).request();
    }
}

class CapitalTrustController extends AbsBaseController implements
        PullToRefreshListView.OnRefreshListener, View.OnClickListener {

    private HKCapitalTrustFragment mFragment;

    public CapitalTrustController(HKCapitalTrustFragment fragment) {
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