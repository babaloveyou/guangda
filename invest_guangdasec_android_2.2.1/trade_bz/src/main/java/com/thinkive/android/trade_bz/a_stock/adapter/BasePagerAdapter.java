package com.thinkive.android.trade_bz.a_stock.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import com.thinkive.android.trade_bz.a_stock.fragment.AbsBaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 *  Fragment和pager的适配器
 * @version 1.0
 * @corporation
 * @date 2015/6/5
 */
public class BasePagerAdapter extends android.support.v4.app.FragmentPagerAdapter {
    /**
     * Fragment 数据集
     */
    private List<AbsBaseFragment> mFragments = null;

    /**
     * @param fm FragmentManager
     */
    public BasePagerAdapter(FragmentManager fm) {
        super(fm);
        mFragments = new ArrayList<AbsBaseFragment>();
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    /**
     *
     * @param fragments
     */
    public void setFragmentsData(List<AbsBaseFragment> fragments) {
        if(!mFragments.isEmpty()) {
            mFragments.clear();
        }
        mFragments.addAll(fragments);
    }
}
