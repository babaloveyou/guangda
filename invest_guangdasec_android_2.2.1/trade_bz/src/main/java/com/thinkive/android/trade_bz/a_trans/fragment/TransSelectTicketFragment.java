package com.thinkive.android.trade_bz.a_trans.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.thinkive.framework.compatible.TKFragmentActivity;
import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;
import com.thinkive.android.trade_bz.a_stock.fragment.AbsBaseFragment;
import com.thinkive.android.trade_bz.a_trans.adapter.TransSelectTicketAdapter;
import com.thinkive.android.trade_bz.a_trans.bean.TransSelectTicketBean;
import com.thinkive.android.trade_bz.a_trans.bll.TransSelectTicketServiceImpl;
import com.thinkive.android.trade_bz.utils.DateUtils;
import com.thinkive.android.trade_bz.utils.ToastUtils;
import com.thinkive.android.trade_bz.utils.TradeUtils;
import com.thinkive.android.trade_bz.views.PullToRefresh.PullToRefreshBase;
import com.thinkive.android.trade_bz.views.PullToRefresh.PullToRefreshListView;

import java.util.ArrayList;

/**
 * 转股交易 挂牌股票查询
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/7/29
 */
public class TransSelectTicketFragment extends AbsBaseFragment {
    private TKFragmentActivity mActivity;
    private TransSelectTicketController mController;
    private TransSelectTicketServiceImpl mServices;
    private TransSelectTicketAdapter mAdapter;
    /**
     * ListView
     */
    private ListView mListView;
    /**
     * 自定义的listView对象
     */
    private PullToRefreshListView mPullToRefreshListView;
    /**
     * 有数据时显示布局
     */
    private LinearLayout mLiHaveData;
    /**
     * 如果没有数据就显示该图片
     */
    private LinearLayout mLiNoData;
    /**
     * 正在加载的旋转进度条
     */
    private LinearLayout mLoading;
    /**
     * 证券代码
     */
    private EditText mEdtCode;
    /**
     * 查询按钮
     */
    private TextView mTvClick;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_trans_select_ticket, null);
        findViews(rootView);
        initData();
        setListeners();
        initViews();
        return rootView;
    }

    @Override
    protected void findViews(View view) {
        mPullToRefreshListView = (PullToRefreshListView) view.findViewById(R.id.lv_r_select_capital);
        mListView = mPullToRefreshListView.getRefreshableView();
        mListView.setDivider(null);
        mLoading = (LinearLayout) view.findViewById(R.id.ll_capital_list_loading);
        mLiNoData = (LinearLayout) view.findViewById(R.id.lin_not_data_set);
        mLiHaveData = (LinearLayout) view.findViewById(R.id.ll_capital_have_data);
        mEdtCode = (EditText) view.findViewById(R.id.edt_capital_code);
        mTvClick = (TextView) view.findViewById(R.id.tv_capital_click);
    }

    @Override
    protected void setListeners() {
        registerListener(AbsBaseController.ON_LISTVIEW_REFLASH, mPullToRefreshListView, mController);
        registerListener(AbsBaseController.ON_CLICK, mTvClick, mController);
    }

    @Override
    protected void initData() {
        mActivity = (TKFragmentActivity) getActivity();
        mAdapter = new TransSelectTicketAdapter(mActivity);
        mController = new TransSelectTicketController(this);
        mServices = new TransSelectTicketServiceImpl(this);
    }

    @Override
    protected void initViews() {
        //请求数据
        mServices.requestObjectCapital("");
        //设置禁止上拉加载更多
        mPullToRefreshListView.setPullLoadEnabled(false);
        setTheme();
    }

    @Override
    protected void setTheme() {

    }

    /**
     * 得到融资标的的数据列表
     * @param datalist
     */
    public void getObjectCapitalData(ArrayList<TransSelectTicketBean> datalist) {
        if (datalist == null || datalist.size() == 0) {
            mLiNoData.setVisibility(View.VISIBLE);
            mLoading.setVisibility(View.GONE);
            mLiHaveData.setVisibility(View.GONE);
        } else {
            mLiNoData.setVisibility(View.GONE);
            mLoading.setVisibility(View.GONE);
            mLiHaveData.setVisibility(View.VISIBLE);
            mAdapter.setListData(datalist);
            mListView.setAdapter(mAdapter);
        }
        refreshComplete();
    }
    /**
     * 点击‘查询’所执行的操作
     */
    public void clickTvSelect() {
        if (TradeUtils.isFastClick()) {
            return;
        }
        String inputCode = mEdtCode.getText().toString().trim();
        if(TextUtils.isEmpty(inputCode) || inputCode.length() == 6) {
            mServices.requestObjectCapital(inputCode);
            mLiNoData.setVisibility(View.GONE);
            mLoading.setVisibility(View.VISIBLE);
            mLiHaveData.setVisibility(View.GONE);
            TradeUtils.hideSystemKeyBoard(mActivity);
        }else{
            ToastUtils.toast(mActivity,mActivity.getResources().getString(R.string.r_revocation_error));
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
        mServices.requestObjectCapital("");
    }
}

/**
 * 控制类
 */
class TransSelectTicketController extends AbsBaseController implements
        PullToRefreshListView.OnRefreshListener,View.OnClickListener{

    private TransSelectTicketFragment mFragment;

    public TransSelectTicketController(TransSelectTicketFragment fragment) {
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
        int resId = v.getId();
        if (resId == R.id.tv_capital_click) { // 查询
            mFragment.clickTvSelect();
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


