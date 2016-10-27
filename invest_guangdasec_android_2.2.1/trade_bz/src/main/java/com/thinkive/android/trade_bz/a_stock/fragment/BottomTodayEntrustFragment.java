package com.thinkive.android.trade_bz.a_stock.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.activity.MultiTradeActivity;
import com.thinkive.android.trade_bz.a_stock.adapter.BottomTodayEntrustAdapter;
import com.thinkive.android.trade_bz.a_stock.bean.RevocationBean;
import com.thinkive.android.trade_bz.a_stock.bll.TodayEntrustServicesImpl;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/19.
 */
public class BottomTodayEntrustFragment extends BaseLazzyFragment {
    public static final String TITLE = "今日委托";
    private TodayEntrustServicesImpl mServices;
    private MultiTradeActivity mActivity;
    private BuyOrSellFragment mFragment;
    private View mView;
    private ListView mLv;
    private LinearLayout mNoDataLl;
    private LinearLayout mLoadingLl;
    private BottomTodayEntrustAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView != null) {
            return mView;
        }
        mView = inflater.inflate(R.layout.fragment_trade_bottom_today_ectrust, null);
        if (mView.getParent() != null) {
            ViewGroup parent = (ViewGroup) mView.getParent();
            parent.removeView(mView);
        }
        mActivity = (MultiTradeActivity) mFragment.getActivity();
        findViews(mView);
        initView();
        initListener();
        initData();
        isPrepared = true;
        return mView;
    }

    @Override
    protected void lazyLoad() {
        //懒加载标记
        if (!isPrepared || !isVisible) {
            return;
        }
        mServices.requestTodayEntrust();
    }

    private void setLoading() {
        mNoDataLl.setVisibility(View.GONE);
        mLv.setVisibility(View.GONE);
        mLoadingLl.setVisibility(View.VISIBLE);
    }

    private void initData() {
        mActivity = (MultiTradeActivity) mFragment.getActivity();
        mServices = new TodayEntrustServicesImpl(this);
        mAdapter = new BottomTodayEntrustAdapter(getContext(), mServices);
    }

    private void initListener() {
        mNoDataLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLoading();
                lazyLoad();
            }
        });
    }

    private void initView() {
        mLv.setDivider(null);
    }

    private void findViews(View view) {
        mLv = (ListView) view.findViewById(R.id.lv_today_entrust);
        mNoDataLl = (LinearLayout) view.findViewById(R.id.rl_no_data);
        mLoadingLl = (LinearLayout) view.findViewById(R.id.bottom_list_loading);
    }

    @Override
    public void onResume() {
        super.onResume();
        // 调用业务类中，初始化撤单数据的方法
        lazyLoad();
    }

    public void setFragment(BuyOrSellFragment fragment) {
        mFragment = fragment;
        mActivity = (MultiTradeActivity) mFragment.getActivity();
    }

    public void refreshAdapter() {
        mAdapter.notifyDataSetChanged();
    }

    public void onGetTodayEntrustData(ArrayList<RevocationBean> dataList) {
        if (dataList == null || dataList.size() == 0) {
            mLv.setVisibility(View.GONE);
            mNoDataLl.setVisibility(View.VISIBLE);
            mLoadingLl.setVisibility(View.GONE);
        } else {
            //买入卖出通过entrust_bs筛选数据
            ArrayList<RevocationBean> revocationBeensBuy = new ArrayList<>();
            ArrayList<RevocationBean> revocationBeensSell = new ArrayList<>();


            for (RevocationBean bean : dataList) {
                String entrust_bs = bean.getEntrust_bs();
                if ("0".equals(entrust_bs)) {
                    revocationBeensBuy.add(bean);
                } else if ("1".equals(entrust_bs)) {
                    revocationBeensSell.add(bean);
                }
            }
            if (mFragment.getBuyOrSell()==0) {//如果是买
                if (revocationBeensBuy.size() == 0) {
                    mLoadingLl.setVisibility(View.GONE);
                    mLv.setVisibility(View.GONE);
                    mNoDataLl.setVisibility(View.VISIBLE);
                }else {
                    mLoadingLl.setVisibility(View.GONE);
                    mLv.setVisibility(View.VISIBLE);
                    mNoDataLl.setVisibility(View.GONE);
                }
                mAdapter.setListData(revocationBeensBuy);
                revocationBeensSell = null;
            } else {//卖
                if (revocationBeensSell.size() == 0) {
                    mLoadingLl.setVisibility(View.GONE);
                    mLv.setVisibility(View.GONE);
                    mNoDataLl.setVisibility(View.VISIBLE);
                }else {
                    mLoadingLl.setVisibility(View.GONE);
                    mLv.setVisibility(View.VISIBLE);
                    mNoDataLl.setVisibility(View.GONE);
                }
                mAdapter.setListData(revocationBeensSell);
                revocationBeensBuy = null;
            }
            mLv.setAdapter(mAdapter);

        }
    }

    public void requestNewData() {
        lazyLoad();
    }
}
