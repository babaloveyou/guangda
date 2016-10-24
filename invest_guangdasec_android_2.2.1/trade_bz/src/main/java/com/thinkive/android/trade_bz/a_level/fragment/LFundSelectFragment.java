package com.thinkive.android.trade_bz.a_level.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.thinkive.framework.compatible.ListenerControllerAdapter;
import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;
import com.thinkive.android.trade_bz.a_level.activity.LFundHistoryActivity;
import com.thinkive.android.trade_bz.a_level.activity.LFundTodayActivity;
import com.thinkive.android.trade_bz.a_stock.fragment.AbsBaseFragment;
import com.thinkive.android.trade_bz.utils.TradeUtils;

/**
 *   分级基金查询
 * @author 张雪梅
 * @company Thinkive
 * @date 15/10/23
 * */
public class LFundSelectFragment extends AbsBaseFragment {
    private AppCompatActivity mActivity;
    private LFundSelectViewController mController;
    /**
     * 今日委托
     */
    private TextView mTvSelectEntrust;
    /**
     *历史成交
     */
    private TextView mTvSelectTrade;
    /**
     * 今日成交
     */
    private TextView mTvTodayTrade;
    /**
     * 历史委托
     */
    private TextView mTvHistoryEntrust;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_level_fund_select, null);
        findViews(rootView);
        initData();
        setListeners();
        initViews();
        return rootView;
    }

    @Override
    protected void findViews(View view) {
        mTvSelectEntrust=(TextView)view.findViewById(R.id.tv_level_select_entrust);
        mTvSelectTrade=(TextView)view.findViewById(R.id.tv_level_select_trade);
        mTvTodayTrade=(TextView)view.findViewById(R.id.tv_level_select_ttrade);
        mTvHistoryEntrust=(TextView)view.findViewById(R.id.tv_level_select_eentrust);
    }

    @Override
    protected void setListeners() {
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvSelectEntrust, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvSelectTrade, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvTodayTrade, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvHistoryEntrust, mController);
    }

    @Override
    protected void initData() {
        mActivity=(AppCompatActivity)getActivity();
        mController=new LFundSelectViewController(this);
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
        Intent intent=new Intent(mActivity, LFundTodayActivity.class);
        Bundle bundle=new Bundle();
        bundle.putInt("ViewPagerFragmentPos",0);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    /**
     *历史成交
     */
    public void onClickHistoryTrade(){
        Intent intent=new Intent(mActivity, LFundHistoryActivity.class);
        Bundle bundle=new Bundle();
        bundle.putInt("ViewPagerFragmentPos",1);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * 今日成交
     */
    public void onClickTodayTrade(){
        Intent intent=new Intent(mActivity, LFundTodayActivity.class);
        Bundle bundle=new Bundle();
        bundle.putInt("ViewPagerFragmentPos",1);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * 历史委托
     */
    public void onClickHistoryEntrust(){
        Intent intent=new Intent(mActivity, LFundHistoryActivity.class);
        Bundle bundle=new Bundle();
        bundle.putInt("ViewPagerFragmentPos",0);
        intent.putExtras(bundle);
        startActivity(intent);
    }

}


class LFundSelectViewController extends AbsBaseController implements View.OnClickListener {

    private LFundSelectFragment mFragment;

    public LFundSelectViewController(LFundSelectFragment mFragment) {
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
        if (TradeUtils.isFastClick()) {
            return;
        }
        if (resId == R.id.tv_level_select_entrust) {
            mFragment.onClickTodayEntrust();
        } else if (resId == R.id.tv_level_select_trade) {
            mFragment.onClickHistoryTrade();
        } else if (resId == R.id.tv_level_select_ttrade) {
            mFragment.onClickTodayTrade();
        } else if (resId == R.id.tv_level_select_eentrust) {
            mFragment.onClickHistoryEntrust();
        }
    }
}

