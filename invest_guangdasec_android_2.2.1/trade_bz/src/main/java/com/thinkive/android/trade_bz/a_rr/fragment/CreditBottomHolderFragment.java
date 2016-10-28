package com.thinkive.android.trade_bz.a_rr.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_rr.adapter.CreditBottomHoldLvAdapter;
import com.thinkive.android.trade_bz.a_rr.bll.CreditBottomHolderServicesImpl;
import com.thinkive.android.trade_bz.a_stock.bean.MyStoreStockBean;
import com.thinkive.android.trade_bz.a_stock.fragment.BaseLazzyFragment;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/26.
 */
public class CreditBottomHolderFragment extends BaseLazzyFragment {
    public static final String TITLE = "我的持仓";
    private CreditBottomHolderServicesImpl mServices;
    private RCreditBuyFragment mRCreditBuyFragment;
    private RCreditSaleFragment mRCreditSaleFragment;
    private RCollaterBuyOrSellFragment mRCollaterBuyOrSellFragment;
    private View mView;
    private ListView mLv;
    private LinearLayout mNoDataLl;
    private LinearLayout mLoadingLl;
    private CreditBottomHoldLvAdapter mAdapter;
    private String mCurrentFragment = null;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView != null) {
            return mView;
        }
        mView = inflater.inflate(R.layout.fragment_trade_bottom_hold, null);
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
        mServices.getHoldList();
    }

    private void setLoading() {
        mNoDataLl.setVisibility(View.GONE);
        mLv.setVisibility(View.GONE);
        mLoadingLl.setVisibility(View.VISIBLE);
    }

    private void initData() {
        mServices = new CreditBottomHolderServicesImpl(this);
        mAdapter = new CreditBottomHoldLvAdapter(getContext(), mServices);
    }

    private void initListener() {
        mNoDataLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLoading();
                lazyLoad();
            }
        });
        mLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ArrayList<MyStoreStockBean> dataList = mAdapter.getListData();
                MyStoreStockBean bean = dataList.get(position);
                if (mCurrentFragment == null) {
                    return;
                } else if(mCurrentFragment.equals(RCreditBuyFragment.class.getSimpleName())){
                    mRCreditBuyFragment.getHolderStock(bean);
                } else if(mCurrentFragment.equals(RCreditSaleFragment.class.getSimpleName())){
                    mRCreditSaleFragment.getHolderStock(bean);
                }else if(mCurrentFragment.equals(RCollaterBuyOrSellFragment.class.getSimpleName())){
                    mRCollaterBuyOrSellFragment.getHolderStock(bean);
                }
            }
        });
    }

    private void initView() {
        mLv.setDivider(null);
    }

    private void findViews(View view) {
        mLv = (ListView) view.findViewById(R.id.lv_hold);
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
        } else if (fragment instanceof RCreditSaleFragment) {
            mRCreditSaleFragment = (RCreditSaleFragment) fragment;
            mCurrentFragment = RCreditSaleFragment.class.getSimpleName();
        } else if(fragment instanceof RCollaterBuyOrSellFragment){
            mRCollaterBuyOrSellFragment = (RCollaterBuyOrSellFragment) fragment;
            mCurrentFragment = RCollaterBuyOrSellFragment.class.getSimpleName();
        }
    }

    public void refreshAdapter() {
        mAdapter.notifyDataSetChanged();
    }

    public void requestNewData() {
        lazyLoad();
    }

    //获取数据方法
    public void getStoreData(ArrayList<MyStoreStockBean> dataList) {
        if (dataList == null || dataList.size() == 0) {
            mLv.setVisibility(View.GONE);
            mNoDataLl.setVisibility(View.VISIBLE);
            mLoadingLl.setVisibility(View.GONE);
        } else {
            mLoadingLl.setVisibility(View.GONE);
            mLv.setVisibility(View.VISIBLE);
            mNoDataLl.setVisibility(View.GONE);
            mAdapter.setListData(dataList);
            mLv.setAdapter(mAdapter);
        }

    }
}