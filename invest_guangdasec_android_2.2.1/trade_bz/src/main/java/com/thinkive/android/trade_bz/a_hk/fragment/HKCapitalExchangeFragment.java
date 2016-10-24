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
import com.thinkive.android.trade_bz.a_hk.adapter.HKCapitalExchangeAdapter;
import com.thinkive.android.trade_bz.a_hk.bean.HKCapitalExchangeBean;
import com.thinkive.android.trade_bz.a_stock.bll.BasicServiceImpl;
import com.thinkive.android.trade_bz.a_stock.fragment.AbsBaseFragment;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.request.HK301635;
import com.thinkive.android.trade_bz.utils.DateUtils;
import com.thinkive.android.trade_bz.utils.ToastUtils;
import com.thinkive.android.trade_bz.views.PullToRefresh.PullToRefreshBase;
import com.thinkive.android.trade_bz.views.PullToRefresh.PullToRefreshListView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 创建者     舒旺
 * 创建时间   2016/5/27 20:59
 * 描述	     汇率查询
 * 更新者     $Author$
 * 更新时间   $Date$
 */
public class HKCapitalExchangeFragment extends AbsBaseFragment implements PullToRefreshListView.OnRefreshListener {
    private LinearLayout mLinLoadingSet;
    private PullToRefreshListView mLvRefreshList;
    private LinearLayout mLinNotDataSet;
    private HKCapitalExchangeServiceImpl mHkCapitalExchangeService;
    private ListView mRefreshableView;
    private Context mContext;
    private HKCapitalExchangeAdapter mHkCapitalExchangeAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        mContext = getActivity();
        View rootViw = inflater.inflate(R.layout.fragment_common_refresh_list, null);
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
        mHkCapitalExchangeService = new HKCapitalExchangeServiceImpl(this);
        mHkCapitalExchangeAdapter = new HKCapitalExchangeAdapter(mContext);
    }

    @Override
    protected void initViews() {
        mHkCapitalExchangeService.reuqestExchangeServiceImpl();
    }

    @Override
    protected void setTheme() {

    }

    /**
     * 请求数据成功后回调给主线程
     */
    public void setlLiabilitiesData(ArrayList<HKCapitalExchangeBean> liabilitiesData) {
        if (null == liabilitiesData || liabilitiesData.size() <= 0) {
            //没有数据
            mLinLoadingSet.setVisibility(View.GONE);
            mLinNotDataSet.setVisibility(View.VISIBLE);
            mLvRefreshList.setVisibility(View.GONE);
        } else {
            mLinLoadingSet.setVisibility(View.GONE);
            mLinNotDataSet.setVisibility(View.GONE);
            mLvRefreshList.setVisibility(View.VISIBLE);
            mHkCapitalExchangeAdapter.setListData(liabilitiesData);
            mRefreshableView.setAdapter(mHkCapitalExchangeAdapter);
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
        mHkCapitalExchangeService.reuqestExchangeServiceImpl();
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        onDownRefresh();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {

    }
}

class HKCapitalExchangeServiceImpl extends BasicServiceImpl {

    private HKCapitalExchangeFragment mHKCapitalLiabilitiesFragment;

    public HKCapitalExchangeServiceImpl(HKCapitalExchangeFragment hkCapitalLiabilitiesFragment) {
        this.mHKCapitalLiabilitiesFragment = hkCapitalLiabilitiesFragment;
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onStop() {

    }

    public void reuqestExchangeServiceImpl() {
        HashMap<String, String> map = new HashMap<String, String>();
        new HK301635(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                ArrayList<HKCapitalExchangeBean> dataList = bundle.getParcelableArrayList(HK301635.BUNDLE_KEY_HK_HISTORY_TRADE);
                mHKCapitalLiabilitiesFragment.setlLiabilitiesData(dataList);
            }

            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(HK301635.ERROR_INFO));
            }
        }).request();
    }
}