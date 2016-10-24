package com.thinkive.android.trade_bz.a_out.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import com.android.thinkive.framework.compatible.ListenerControllerAdapter;
import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;
import com.thinkive.android.trade_bz.a_out.activity.FundAccountActivity;
import com.thinkive.android.trade_bz.a_out.activity.FundHistoryActivity;
import com.thinkive.android.trade_bz.a_out.activity.FundRevocationActivity;
import com.thinkive.android.trade_bz.a_out.activity.FundRiskActivity;
import com.thinkive.android.trade_bz.a_out.activity.FundTodayActivity;
import com.thinkive.android.trade_bz.a_out.activity.FundTradeMainActivity;
import com.thinkive.android.trade_bz.a_stock.fragment.AbsBaseFragment;
import com.thinkive.android.trade_bz.utils.TradeUtils;

/**
 * 基金交易之查询
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/7/6
 */

public class FundSelectFragment extends AbsBaseFragment {
    /**
     *  依赖的Activity
     */
    private FundTradeMainActivity mActivity;
    /**
     * 该类的控制器
     */
    private FundSelsectViewController mController;
    /**
     * 今日成交
     */
    private TextView mTvTodayTrade;
    /**
     * 今日委托
     */
    private TextView mTvodayEntrust;
    /**
     *基金撤单
     */
    private TextView mTvFundRevocation;
    /**
     *历史委托
     */
    private TextView mTvHistoryEntrust;
    /**
     *历史成交
     */
    private TextView mTvHistoryTrade;
    /**
     *风险级别查询
     */
    private TextView mTvSelectRisk;
    /**
     *基金账户查询
     */
    private TextView mTvFundAccount;
    /**
     *  子布局
     */
    private View childScrollView;
    /**
     * 根布局
     */
    private ScrollView mScrollview;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_common_scrollview, null);
        childScrollView = inflater.inflate(R.layout.fragment_fund_select_layout, null);
        findViews(rootView);
        initData();
        setListeners();
        initViews();
        return rootView;
    }



    @Override
    protected void findViews(View view) {
        mScrollview=(ScrollView)view.findViewById(R.id.sll_layout);
        mScrollview.addView(childScrollView);
        mTvTodayTrade=(TextView)childScrollView.findViewById(R.id.tv_sele_today_trade);
        mTvodayEntrust =(TextView)childScrollView.findViewById(R.id.tv_sele_today_entrust);
        mTvFundRevocation =(TextView)childScrollView.findViewById(R.id.tv_sele_revocation);
        mTvHistoryEntrust =(TextView)childScrollView.findViewById(R.id.tv_sele_history_entrust);
        mTvHistoryTrade =(TextView)childScrollView.findViewById(R.id.tv_sele_history_trade);
        mTvSelectRisk =(TextView)childScrollView.findViewById(R.id.tv_sele_ransom_risk);
        mTvFundAccount =(TextView)childScrollView.findViewById(R.id.tv_sele_select);
    }

    @Override
    protected void setListeners() {
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvTodayTrade, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvodayEntrust, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvFundRevocation, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvHistoryEntrust, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvHistoryTrade, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvSelectRisk, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvFundAccount, mController);
    }

    @Override
    protected void initData() {
        mActivity = (FundTradeMainActivity) getActivity();
        mController=new FundSelsectViewController(this);
    }

    @Override
    protected void initViews() {
        setTheme();
    }

    @Override
    protected void setTheme() {

    }

    /**
     * 点击今日成交
     */
    public void onClickTodayTrade(){
        Intent intent=new Intent(mActivity, FundTodayActivity.class);
        Bundle bundle=new Bundle();
        bundle.putInt("ViewPagerFragmentPos",1);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * 点击今日委托所执行的操作
     */
    public void TodayEntrust(){
        Intent intent=new Intent(mActivity, FundTodayActivity.class);
        Bundle bundle=new Bundle();
        bundle.putInt("ViewPagerFragmentPos",0);
        intent.putExtras(bundle);
        startActivity(intent);
    }


    /**
     *点击基金撤单所执行的操作
     */
    public void FoundRevocation(){
        startActivity(new Intent(mActivity, FundRevocationActivity.class));
    }


    /**
     *点击历史委托所执行的操作
     */
    public void HistoryEntrust(){
        Intent intent=new Intent(mActivity, FundHistoryActivity.class);
        Bundle bundle=new Bundle();
        bundle.putInt("ViewPagerFragmentPos", 0);
        intent.putExtras(bundle);
        startActivity(intent);
    }


    /**
     *点击历史成交所执行的操作
     */
    public void HistoryTrade(){
        Intent intent=new Intent(mActivity, FundHistoryActivity.class);
        Bundle bundle=new Bundle();
        bundle.putInt("ViewPagerFragmentPos",1);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     *点击风险级别查询所执行的操作
     */
    public void SelectFundRisk(){
        startActivity(new Intent(mActivity, FundRiskActivity.class));
    }


    /**
     *点击基金账户查询所执行的操作
     */
    public void FundAccountSelsect(){
        startActivity(new Intent(mActivity, FundAccountActivity.class));
    }
}

class FundSelsectViewController extends AbsBaseController implements View.OnClickListener {

    private FundSelectFragment mFselectFragment;

    public FundSelsectViewController(FundSelectFragment mFragment) {
        mFselectFragment = mFragment;
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
        if (resId == R.id.tv_sele_today_entrust) { // 今日委托
            mFselectFragment.TodayEntrust();
        } else if (resId == R.id.tv_sele_revocation) { //基金撤单
            mFselectFragment.FoundRevocation();
        } else if (resId == R.id.tv_sele_history_entrust) {//历史委托
            mFselectFragment.HistoryEntrust();
        }else if (resId==R.id.tv_sele_history_trade){//历史交易
            mFselectFragment.HistoryTrade();
        }else if (resId==R.id.tv_sele_ransom_risk){//风险级别查询
            mFselectFragment.SelectFundRisk();
        }else if (resId==R.id.tv_sele_select){//基金账户查询
            mFselectFragment.FundAccountSelsect();
        }else if(resId == R.id.tv_sele_today_trade){ //今日成交
            mFselectFragment.onClickTodayTrade();
        }
    }
}