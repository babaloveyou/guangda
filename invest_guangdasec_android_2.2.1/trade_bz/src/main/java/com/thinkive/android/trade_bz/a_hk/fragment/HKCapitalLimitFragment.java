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
import com.thinkive.android.trade_bz.a_hk.adapter.HKCapitalLimitAdapter;
import com.thinkive.android.trade_bz.a_hk.bean.HKCapitalLimitBean;
import com.thinkive.android.trade_bz.a_stock.bll.BasicServiceImpl;
import com.thinkive.android.trade_bz.a_stock.fragment.AbsBaseFragment;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.request.HK301601;
import com.thinkive.android.trade_bz.utils.DateUtils;
import com.thinkive.android.trade_bz.utils.ToastUtils;
import com.thinkive.android.trade_bz.views.PullToRefresh.PullToRefreshBase;
import com.thinkive.android.trade_bz.views.PullToRefresh.PullToRefreshListView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 创建者     舒旺
 * 创建时间   2016/5/27 21:00
 * 描述	     额度查询
 * 更新者     $Author$
 * 更新时间   $Date$
 */
public class HKCapitalLimitFragment extends AbsBaseFragment implements PullToRefreshListView.OnRefreshListener {
    private LinearLayout mLinLoadingSet;
    private PullToRefreshListView mLvRefreshList;
    private LinearLayout mLinNotDataSet;
    private TextView mTvNoDataSet;
    private ListView mRefreshableView;
    private HKCapitalLimitServiceImpl mHkCapitalLimitService;
    private Context mContext;
    private HKCapitalLimitAdapter mHkCapitalLimitAdapter;

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
        mTvNoDataSet = (TextView) view.findViewById(R.id.tv_no_data_set);
    }

    @Override
    protected void setListeners() {

    }

    @Override
    protected void initData() {
        mHkCapitalLimitService = new HKCapitalLimitServiceImpl(this);
        mHkCapitalLimitAdapter = new HKCapitalLimitAdapter(mContext);
    }

    @Override
    protected void initViews() {
        mHkCapitalLimitService.reuqestLimitServiceImpl();
    }

    @Override
    protected void setTheme() {

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
        mHkCapitalLimitService.reuqestLimitServiceImpl();
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        onDownRefresh();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {

    }

    /**
     * 请求网络  回调给主线程
     */
    public void setlLimitData(ArrayList<HKCapitalLimitBean> lLimitData) {
        if (null == lLimitData || lLimitData.size()<=0) {
            //没有数据
            mLinLoadingSet.setVisibility(View.GONE);
            mLinNotDataSet.setVisibility(View.VISIBLE);
            mLvRefreshList.setVisibility(View.GONE);
        } else {
            mLinLoadingSet.setVisibility(View.GONE);
            mLinNotDataSet.setVisibility(View.GONE);
            mLvRefreshList.setVisibility(View.VISIBLE);
            mHkCapitalLimitAdapter.setListData(lLimitData);
            mRefreshableView.setAdapter(mHkCapitalLimitAdapter);
        }
        refreshComplete();
    }
}

class HKCapitalLimitServiceImpl extends BasicServiceImpl {

    private HKCapitalLimitFragment mHKCapitalLiabilitiesFragment;

    public HKCapitalLimitServiceImpl(HKCapitalLimitFragment hkCapitalLimitFragment) {
        this.mHKCapitalLiabilitiesFragment = hkCapitalLimitFragment;
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onStop() {

    }

    public void reuqestLimitServiceImpl() {
        HashMap<String, String> map = new HashMap<String, String>();
        new HK301601(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                ArrayList<HKCapitalLimitBean> dataList = bundle.getParcelableArrayList(HK301601.BUNDLE_KEY_LIMIT);
                mHKCapitalLiabilitiesFragment.setlLimitData(dataList);
            }

            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(HK301601.ERROR_INFO));
            }
        }).request();
    }
}