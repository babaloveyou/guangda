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
import com.thinkive.android.trade_bz.a_hk.adapter.HKCapitalSecutitieseAdapter;
import com.thinkive.android.trade_bz.a_hk.bean.HKSecutitieseBean;
import com.thinkive.android.trade_bz.a_stock.bll.BasicServiceImpl;
import com.thinkive.android.trade_bz.a_stock.fragment.AbsBaseFragment;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.request.HK301630;
import com.thinkive.android.trade_bz.utils.DateUtils;
import com.thinkive.android.trade_bz.utils.ToastUtils;
import com.thinkive.android.trade_bz.views.PullToRefresh.PullToRefreshBase;
import com.thinkive.android.trade_bz.views.PullToRefresh.PullToRefreshListView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 创建者     舒旺
 * 创建时间   2016/5/27 20:46
 * 描述	     标的证券查询
 * 更新者     $Author$
 * 更新时间   $Date$
 */
public class HKCapitalSecutitieseFragment extends AbsBaseFragment implements PullToRefreshListView.OnRefreshListener {
    private LinearLayout mLinLoadingSet;
    private PullToRefreshListView mLvRefreshList;
    private LinearLayout mLinNotDataSet;
    private HKCapitalSecutitieseServiceImpl mHkCapitalSecutitieseService;
    private Context mContext;
    private HKCapitalSecutitieseAdapter mHkCapitalSecutitieseAdapter;
    private ListView mRefreshableView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        mContext = getActivity();
        View rootViw = inflater.inflate(R.layout.fragment_common_refresh_list, null);
        findViews(rootViw);
        initData();
        initViews();
        return rootViw;
    }

    @Override
    protected void findViews(View view) {
        mLinLoadingSet = (LinearLayout) view.findViewById(R.id.lin_loading_set);
        mLvRefreshList = (PullToRefreshListView) view.findViewById(R.id.lv_refresh_list);
        mLvRefreshList.setOnRefreshListener(this);
        //        mLvRefreshList.setPullLoadEnabled(true);
        mRefreshableView = mLvRefreshList.getRefreshableView();
        mRefreshableView.setDivider(null);
        mLinNotDataSet = (LinearLayout) view.findViewById(R.id.lin_not_data_set);
    }


    @Override
    protected void setListeners() {

    }

    @Override
    protected void initData() {
        mHkCapitalSecutitieseService = new HKCapitalSecutitieseServiceImpl(this);
        mHkCapitalSecutitieseAdapter = new HKCapitalSecutitieseAdapter(mContext);
    }

    @Override
    protected void initViews() {
        mHkCapitalSecutitieseService.reuqestHKSecutitiese();
    }

    @Override
    protected void setTheme() {

    }

    /**
     * 请求数据成功后  回调给主线程  改变请求状态  设置适配器
     */
    public void setNoTradeData(ArrayList<HKSecutitieseBean> noTradeData) {
        if (null == noTradeData || noTradeData.size() <= 0) {
            //没有数据
            mLinLoadingSet.setVisibility(View.GONE);
            mLinNotDataSet.setVisibility(View.VISIBLE);
            mLvRefreshList.setVisibility(View.GONE);
        } else {
            mLinLoadingSet.setVisibility(View.GONE);
            mLinNotDataSet.setVisibility(View.GONE);
            mLvRefreshList.setVisibility(View.VISIBLE);
            mHkCapitalSecutitieseAdapter.setListData(noTradeData);
            mRefreshableView.setAdapter(mHkCapitalSecutitieseAdapter);
            refreshComplete();
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
        mHkCapitalSecutitieseService.reuqestHKSecutitiese();
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        onDownRefresh();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {

    }
}

class HKCapitalSecutitieseServiceImpl extends BasicServiceImpl {

    private HKCapitalSecutitieseFragment mHKCapitalSecutitieseFragment;

    public HKCapitalSecutitieseServiceImpl(HKCapitalSecutitieseFragment hkCapitalSecutitieseFragment) {
        this.mHKCapitalSecutitieseFragment = hkCapitalSecutitieseFragment;
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onStop() {

    }

    public void reuqestHKSecutitiese() {
        HashMap<String, String> map = new HashMap<>();
        new HK301630(map, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                ArrayList<HKSecutitieseBean> dataList = bundle.getParcelableArrayList(HK301630.BUNDLE_KEY_HK_HISTORY_TRADE);
                mHKCapitalSecutitieseFragment.setNoTradeData(dataList);
            }

            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(HK301630.ERROR_INFO));
            }
        }).request();
    }

}
