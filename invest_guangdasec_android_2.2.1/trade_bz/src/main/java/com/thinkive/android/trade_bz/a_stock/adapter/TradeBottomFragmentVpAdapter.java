package com.thinkive.android.trade_bz.a_stock.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.ViewGroup;

import com.thinkive.android.trade_bz.a_stock.activity.MultiCreditTradeActivity;
import com.thinkive.android.trade_bz.a_stock.activity.MultiTradeActivity;
import com.thinkive.android.trade_bz.a_stock.bean.MyStoreStockBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/17.
 */
public class TradeBottomFragmentVpAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragments;
    private MultiTradeActivity mMultiTradeActivity;
    private MultiCreditTradeActivity mMultiCreditTradeActivity;
    private ArrayList<MyStoreStockBean> mListData;
    private FragmentManager mFragmentManager;
    private boolean isNormal = true;
    public TradeBottomFragmentVpAdapter(FragmentManager fm, MultiTradeActivity activity, List<Fragment> fragments) {
        super(fm);
        this.mMultiTradeActivity = activity;
        mFragments = fragments;
        mFragmentManager = fm;
        isNormal = true;
    }
    public TradeBottomFragmentVpAdapter(FragmentManager fm, MultiCreditTradeActivity activity, List<Fragment> fragments) {
        super(fm);
        this.mMultiCreditTradeActivity = activity;
        mFragments = fragments;
        mFragmentManager = fm;
        isNormal = false;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments != null ? mFragments.get(position) : null;

    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    @Override
    public int getCount() {
        return mFragments != null ? mFragments.size() : 0;
    }

    @Override
    public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
    }
}
