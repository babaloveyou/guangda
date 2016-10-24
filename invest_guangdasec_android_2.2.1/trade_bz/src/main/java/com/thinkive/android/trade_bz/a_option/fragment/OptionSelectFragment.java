package com.thinkive.android.trade_bz.a_option.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.thinkive.framework.compatible.ListenerControllerAdapter;
import com.android.thinkive.framework.compatible.TKFragmentActivity;
import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_option.activity.OptionHistoryActivity;
import com.thinkive.android.trade_bz.a_option.activity.OptionHistoryListActivity;
import com.thinkive.android.trade_bz.a_option.activity.OptionHistoryRiskActivity;
import com.thinkive.android.trade_bz.a_option.activity.OptionHoldSelectActivity;
import com.thinkive.android.trade_bz.a_option.activity.OptionTodayActivity;
import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;
import com.thinkive.android.trade_bz.a_stock.fragment.AbsBaseFragment;

/**
 * 个股期权查询
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/6/14
 */
public class OptionSelectFragment extends AbsBaseFragment {
    private TKFragmentActivity mActivity;
    private OptionSelectController mController;
    private TextView mTvTodayEntrust;
    private TextView mTvTodayTrade;
    private TextView mTvHistoryEntrust;
    private TextView mTvHistoryTrade;
    private TextView mTvHoldStock;
    private TextView mTvHistoryList;
    private TextView mTvOptionCode;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_option_select, null);
        findViews(rootView);
        initData();
        setListeners();
        initViews();
        return rootView;
    }

    @Override
    protected void findViews(View view) {
        mTvTodayEntrust = (TextView)view.findViewById(R.id.tv_today_entrust);
        mTvTodayTrade = (TextView)view.findViewById(R.id.tv_today_trade);
        mTvHistoryEntrust = (TextView)view.findViewById(R.id.tv_history_entrust);
        mTvHistoryTrade = (TextView)view.findViewById(R.id.tv_history_trade);
        mTvHoldStock = (TextView)view.findViewById(R.id.tv_hold_stock);
        mTvHistoryList = (TextView)view.findViewById(R.id.tv_history_list);
        mTvOptionCode = (TextView)view.findViewById(R.id.tv_option_code);
    }



    @Override
    protected void setListeners() {
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvTodayEntrust, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvTodayTrade, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvHistoryEntrust, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvHistoryTrade, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvHoldStock, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvHistoryList, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvOptionCode, mController);
    }

    @Override
    protected void initData() {
        mActivity=(TKFragmentActivity)getActivity();
        mController=new OptionSelectController(this);
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void setTheme() {

    }

    /**
     * 今日委托
     */
    public void onClickTodayEntrust(){
        Intent intent=new Intent(mActivity,OptionTodayActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("ViewPagerFragmentPos", 0);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * 今日成交
     */
    public void onClickTodayTrade(){
        Intent intent=new Intent(mActivity,OptionTodayActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("ViewPagerFragmentPos", 1);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * 历史委托
     */
    public void onClickHistoryEntrust(){
        Intent intent=new Intent(mActivity,OptionHistoryActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("ViewPagerFragmentPos", 0);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * 历史成交
     */
    public void onClickHistoryTrade(){
        Intent intent=new Intent(mActivity,OptionHistoryActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("ViewPagerFragmentPos", 1);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * 持仓查询
     */
    public void onClickHoldStock(){
        Intent intent=new Intent(mActivity,OptionHoldSelectActivity.class);
        startActivity(intent);
    }
    /**
     * 历史对账单查询
     */
    public void onClickHistoryList(){
        Intent intent=new Intent(mActivity,OptionHistoryListActivity.class);
        startActivity(intent);
    }

    /**
     * 期权代码查询
     */
    public void onClickOptionCode(){
        Intent intent=new Intent(mActivity,OptionHistoryRiskActivity.class);
        startActivity(intent);
    }
}

class OptionSelectController extends AbsBaseController implements View.OnClickListener {

    private OptionSelectFragment mFragment;

    public OptionSelectController(OptionSelectFragment mFragment) {
        this.mFragment = mFragment;
    }

    @Override
    public void register(int i, View view) {
        switch (i) {
            case ON_CLICK:
                view.setOnClickListener(this);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        int resId = v.getId();
        if (resId == R.id.tv_today_entrust) {
            mFragment.onClickTodayEntrust();
        } else if (resId == R.id.tv_today_trade) {
            mFragment.onClickTodayTrade();
        } else if (resId == R.id.tv_history_entrust) {
            mFragment.onClickHistoryEntrust();
        } else if (resId == R.id.tv_history_trade) {
            mFragment.onClickHistoryTrade();
        } else if (resId == R.id.tv_hold_stock) {
            mFragment.onClickHoldStock();
        } else if (resId == R.id.tv_history_list) {
            mFragment.onClickHistoryList();
        } else if (resId == R.id.tv_option_code) {
            mFragment.onClickOptionCode();
        }
    }
}



