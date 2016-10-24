package com.thinkive.android.trade_bz.a_stock.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.bean.BankTransferSelectBean;
import com.thinkive.android.trade_bz.a_stock.bll.BankSlectServicesImpl;
import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;
import com.thinkive.android.trade_bz.a_stock.activity.TransferBankSelectActivity;
import com.thinkive.android.trade_bz.a_stock.adapter.TransferSelectAdapter;
import com.thinkive.android.trade_bz.utils.ToastUtils;
import com.thinkive.android.trade_bz.views.DatePickerSelect;
import com.thinkive.android.trade_bz.views.PullToRefresh.PullToRefreshBase;
import com.thinkive.android.trade_bz.views.PullToRefresh.PullToRefreshListView;
import com.thinkive.android.trade_bz.utils.DateUtils;
import com.thinkive.android.trade_bz.utils.TradeUtils;

import java.util.ArrayList;

/**
 * 银证转账--转账查询
 *
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/7/24
 */
public class TransferBankSelectFragment extends AbsBaseFragment {
    /**
     * 转账查询的宿主
     */
    private TransferBankSelectActivity mActivity;
    /**
     * 控制转账查询
     */
    private BankTransferSelectController mController;
    /**
     * 业务类
     */
    private BankSlectServicesImpl mServices;
    /**
     * 数据适配器
     */
    private TransferSelectAdapter mAdapter;
    /**
     * 查询开始时间
     */
    private String mBegin;
    /**
     * 查询结束时间
     */
    private String mEnd;
    /**
     * ListView
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
     * 正在加载的旋转进度条
     */
    private LinearLayout mLinLoading;
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
     * 以那种账户进入
     */
    private String mUseType = "";

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
        mLinNoData = (LinearLayout) view.findViewById(R.id.lin_not_data_set);
        mLinDateBegin = (LinearLayout) view.findViewById(R.id.lin_select_data_start);
        mLinDateEnd = (LinearLayout) view.findViewById(R.id.lin_select_data_end);
        mTvDateBegin = (TextView) view.findViewById(R.id.tv_set_data_start);
        mTvDateEnd = (TextView) view.findViewById(R.id.tv_set_data_end);
        mTvDateSelect = (TextView) view.findViewById(R.id.tv_select_data);
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
        mActivity = (TransferBankSelectActivity) getActivity();
        mController = new BankTransferSelectController(this);
        Bundle bundle = getArguments();
        if(bundle != null){
            mUseType = bundle.getString("userType");
        }
        mServices = new BankSlectServicesImpl(this, mUseType);
        mAdapter = new TransferSelectAdapter(mActivity);
        mDateSelect = new DatePickerSelect(mActivity);
    }

    @Override
    protected void initViews() {
        //设置禁止上拉加载更多
        mRefreshListView.setPullLoadEnabled(false);
        mBegin = TradeUtils.getLastWeek();
        mEnd = TradeUtils.getCurrentDate();
        //请求转账列表数据
        mServices.requestTransferSelect(mBegin, mEnd);
        mTvDateBegin.setText(mBegin);
        mTvDateEnd.setText(mEnd);
        setTheme();
    }

    @Override
    protected void setTheme() {
    }

    /**
     * 转账数据列表
     */
    public void onGetTransferList(ArrayList<BankTransferSelectBean> dataList) {
        if (dataList == null || dataList.size() == 0) {
            mLinNoData.setVisibility(View.VISIBLE);
            mLinLoading.setVisibility(View.GONE);
            mRefreshListView.setVisibility(View.GONE);
        } else {
            mLinNoData.setVisibility(View.GONE);
            mLinLoading.setVisibility(View.GONE);
            mRefreshListView.setVisibility(View.VISIBLE);
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
        }else {
            mRefreshListView.setVisibility(View.GONE);
            mLinNoData.setVisibility(View.GONE);
            mLinLoading.setVisibility(View.VISIBLE);
            mServices.requestTransferSelect(mBegin, mEnd);
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
     * 被下拉时执行
     */
    public void onDownRefresh() {
        mServices.requestTransferSelect(mBegin, mEnd);
    }

    /**
     * 左滑时间监听
     */
    public void onSwipe() {
        mActivity.finish();
    }
}

/**
 * 控制转账查询
 */
class BankTransferSelectController extends AbsBaseController implements
        PullToRefreshListView.OnRefreshListener,
        View.OnClickListener {
    private TransferBankSelectFragment mFragment;

    public BankTransferSelectController(TransferBankSelectFragment fragment) {
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


