package com.thinkive.android.trade_bz.a_rr.activity;

import android.os.Bundle;
import android.view.View;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_rr.bean.RSelectCollaterSecurityBean;
import com.thinkive.android.trade_bz.a_rr.fragment.RSelectCollaterSecurityFragment;
import com.thinkive.android.trade_bz.a_stock.activity.AbsNavBarActivity;

import java.util.ArrayList;

/**
 * 融资融券--查询--担保品证券
 * Announcements：
 *
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/8/19
 */

public class RSelectCollateralSecurityActivity extends AbsNavBarActivity {
    private RSelectCollaterSecurityFragment mFragment = null;
    private ArrayList<RSelectCollaterSecurityBean> dataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initViews();
    }

    @Override
    protected void initViews() {
        addFragment(R.id.fl_container, mFragment);
        setTitleText(R.string.r_collater_title);
        setBackBtnVisibility(View.VISIBLE);
    }

    @Override
    protected void initData() {
        mFragment = new RSelectCollaterSecurityFragment();
    }

    public void saveData(ArrayList<RSelectCollaterSecurityBean> list) {
        dataList.clear();
        dataList.addAll(list);
    }

    public ArrayList<RSelectCollaterSecurityBean> getData() {
        return  dataList;
    }

    @Override
    protected void onResume() {
        super.onResume();
        dataList.clear();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dataList.clear();
    }
}


