package com.thinkive.android.trade_bz.a_hk.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_hk.adapter.HKCapitalStockAdapter;
import com.thinkive.android.trade_bz.a_hk.bean.HKHoldStockBean;
import com.thinkive.android.trade_bz.a_stock.bll.BasicServiceImpl;
import com.thinkive.android.trade_bz.a_stock.fragment.AbsBaseFragment;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.request.HK301605;
import com.thinkive.android.trade_bz.utils.DateUtils;
import com.thinkive.android.trade_bz.utils.ToastUtils;
import com.thinkive.android.trade_bz.views.PullToRefresh.PullToRefreshBase;
import com.thinkive.android.trade_bz.views.PullToRefresh.PullToRefreshListView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 创建者     舒旺
 * 创建时间   2016/5/27 18:04
 * 描述	     资金股份查询
 * 更新者     $Author$
 * 更新时间   $Date$
 */
public class HKCapitalStockFragment extends AbsBaseFragment implements PullToRefreshListView.OnRefreshListener {

    private HKCapitalStockServicesImpl mHkCapitalStockServices;
    private Context mContext;
    private HKCapitalStockAdapter mHkCapitalStockAdapter;
    private ListView mRefreshableView;

    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        mContext = getActivity();
        View rootView = inflater.inflate(R.layout.fragment_common_refresh_list, null);
        findViews(rootView);
        initData();
        initViews();
        setListeners();
        return rootView;
    }

    private LinearLayout mLinLoadingSet;
    private PullToRefreshListView mLvRefreshList;
    private LinearLayout mLinNotDataSet;

    @Override
    protected void findViews(View view) {
        mLinLoadingSet = (LinearLayout) view.findViewById(R.id.lin_loading_set);
        mLvRefreshList = (PullToRefreshListView) view.findViewById(R.id.lv_refresh_list);
        mLvRefreshList.setOnRefreshListener(this);
        mRefreshableView = mLvRefreshList.getRefreshableView();
        mRefreshableView.setDivider(null);
        mLinNotDataSet = (LinearLayout) view.findViewById(R.id.lin_not_data_set);
    }

    @Override
    protected void setListeners() {

    }

    @Override
    protected void initData() {
        mHkCapitalStockServices = new HKCapitalStockServicesImpl(this);
        mHkCapitalStockAdapter = new HKCapitalStockAdapter(mContext);
    }

    @Override
    protected void initViews() {
        mHkCapitalStockServices.reuqestHKCapitalNoTrade();

    }

    @Override
    protected void setTheme() {

    }

    /**
     * 请求持仓数据  回调给主线程
     *
     * @param dataList
     */
    public void getStoreData(ArrayList<HKHoldStockBean> dataList) {
        if (dataList == null || dataList.size() == 0) {
            //没有数据
            mLinLoadingSet.setVisibility(View.GONE);
            mLinNotDataSet.setVisibility(View.VISIBLE);
            mLvRefreshList.setVisibility(View.GONE);
        } else {
            mLinLoadingSet.setVisibility(View.GONE);
            mLinNotDataSet.setVisibility(View.GONE);
            mLvRefreshList.setVisibility(View.VISIBLE);
            mHkCapitalStockAdapter.setListData(dataList);
            mRefreshableView.setAdapter(mHkCapitalStockAdapter);
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
        mHkCapitalStockServices.reuqestHKCapitalNoTrade();
    }


    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        onDownRefresh();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {

    }
}

class HKCapitalStockServicesImpl extends BasicServiceImpl {
    HKCapitalStockFragment mHKCapitalNoTradeFragment;

    public HKCapitalStockServicesImpl(HKCapitalStockFragment hkCapitalNoTradeFragment) {
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
    public void reuqestHKCapitalNoTrade() {
        HashMap<String, String> map = new HashMap<String, String>();
        new HK301605(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                ArrayList<HKHoldStockBean> dataList = bundle.getParcelableArrayList(HK301605.BUNDLE_KEY_RESULT);
                mHKCapitalNoTradeFragment.getStoreData(dataList);
            }

            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(HK301605.ERROR_INFO));
            }
        }).request();
    }
}
