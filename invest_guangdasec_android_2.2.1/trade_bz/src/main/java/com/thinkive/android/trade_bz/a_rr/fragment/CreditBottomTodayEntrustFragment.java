package com.thinkive.android.trade_bz.a_rr.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_rr.adapter.CreditBottomTodayEntrustAdapter;
import com.thinkive.android.trade_bz.a_rr.bean.RSelectTodayEntrustBean;
import com.thinkive.android.trade_bz.a_rr.bll.RSelectTodayEntrustServicesImpl;
import com.thinkive.android.trade_bz.a_stock.fragment.BaseLazzyFragment;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/26.
 */
public class CreditBottomTodayEntrustFragment extends BaseLazzyFragment {
    public static final String TITLE = "今日委托";
    private RSelectTodayEntrustServicesImpl mServices;
    private RCreditBuyFragment mRCreditBuyFragment;
    private RCreditSaleFragment mRCreditSaleFragment;
    private RCollaterBuyOrSellFragment mRCollaterBuyOrSellFragment;
    private View mView;
    private ListView mLv;
    private LinearLayout mNoDataLl;
    private LinearLayout mLoadingLl;
    private CreditBottomTodayEntrustAdapter mAdapter;
    private boolean isBuy = true;
    private String mCurrentFragment = null;
    private RSaleStockToMoneyFragment mRSaleStockToMoneyFragment;
    private RBuyStockToStockFragment mRBuyStockToStockFragment;

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
        mServices = new RSelectTodayEntrustServicesImpl(this);
        mAdapter = new CreditBottomTodayEntrustAdapter(getContext(), mServices);
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

    public void setFragment(Fragment fragment) {
        if (fragment instanceof RCreditBuyFragment) {
            mRCreditBuyFragment = (RCreditBuyFragment) fragment;
            mCurrentFragment = RCreditBuyFragment.class.getSimpleName();
            isBuy = true;
        } else if (fragment instanceof RCreditSaleFragment) {
            mRCreditSaleFragment = (RCreditSaleFragment) fragment;
            mCurrentFragment = RCreditSaleFragment.class.getSimpleName();
            isBuy = false;
        } else if(fragment instanceof RCollaterBuyOrSellFragment){
            mCurrentFragment = RCollaterBuyOrSellFragment.class.getSimpleName();
            mRCollaterBuyOrSellFragment = (RCollaterBuyOrSellFragment) fragment;
            int buyOrSell = mRCollaterBuyOrSellFragment.getBuyOrSell();
            if (buyOrSell == 0) {
                isBuy = true;
            } else {
                isBuy = false;
            }
        } else if (fragment instanceof RSaleStockToMoneyFragment) {
            mCurrentFragment = RSaleStockToMoneyFragment.class.getSimpleName();
            mRSaleStockToMoneyFragment = (RSaleStockToMoneyFragment) fragment;
            isBuy = false;
        }else if (fragment instanceof RBuyStockToStockFragment) {
            mCurrentFragment = RBuyStockToStockFragment.class.getSimpleName();
            mRBuyStockToStockFragment = (RBuyStockToStockFragment) fragment;
            isBuy = true;
        }
    }

    public void refreshAdapter() {
        mAdapter.notifyDataSetChanged();
    }

    public void requestNewData() {
        lazyLoad();
    }

    public void onGetTodayEntrustData(ArrayList<RSelectTodayEntrustBean> dataList) {
        if (dataList == null || dataList.size() == 0) {
            mLv.setVisibility(View.GONE);
            mNoDataLl.setVisibility(View.VISIBLE);
            mLoadingLl.setVisibility(View.GONE);
        } else {
            //买入卖出通过entrust_bs筛选数据
            ArrayList<RSelectTodayEntrustBean> revocationBeensBuy = new ArrayList<>();
            ArrayList<RSelectTodayEntrustBean> revocationBeensSell = new ArrayList<>();


            for (RSelectTodayEntrustBean bean : dataList) {
                String entrust_bs = bean.getEntrust_bs();
                if ("0".equals(entrust_bs)) {
                    revocationBeensBuy.add(bean);
                } else if ("1".equals(entrust_bs)) {
                    revocationBeensSell.add(bean);
                }
            }
            if (isBuy) {//如果是买
                if (revocationBeensBuy.size() == 0) {
                    mLoadingLl.setVisibility(View.GONE);
                    mLv.setVisibility(View.GONE);
                    mNoDataLl.setVisibility(View.VISIBLE);
                } else {
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
}
