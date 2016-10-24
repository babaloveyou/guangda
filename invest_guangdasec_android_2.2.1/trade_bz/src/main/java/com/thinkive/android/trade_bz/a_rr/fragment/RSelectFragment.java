package com.thinkive.android.trade_bz.a_rr.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.thinkive.framework.compatible.ListenerControllerAdapter;
import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_rr.activity.RSecuritiesMarginActivity;
import com.thinkive.android.trade_bz.a_rr.activity.RSelectCollateralSecurityActivity;
import com.thinkive.android.trade_bz.a_rr.activity.RSelectContractNotActivity;
import com.thinkive.android.trade_bz.a_rr.activity.RSelectContractYesActivity;
import com.thinkive.android.trade_bz.a_rr.activity.RSelectCreditLimitActivity;
import com.thinkive.android.trade_bz.a_rr.activity.RSelectDOselectActivity;
import com.thinkive.android.trade_bz.a_rr.activity.RSelectHistoryActivity;
import com.thinkive.android.trade_bz.a_rr.activity.RSelectHoldStockActivity;
import com.thinkive.android.trade_bz.a_rr.activity.RSelectObjectSecurityActivity;
import com.thinkive.android.trade_bz.a_rr.activity.RSelectPropertyActivity;
import com.thinkive.android.trade_bz.a_rr.activity.RSelectTodayActivity;
import com.thinkive.android.trade_bz.a_rr.activity.RSelectWaterMoneyActivity;
import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;
import com.thinkive.android.trade_bz.a_stock.fragment.AbsBaseFragment;
import com.thinkive.android.trade_bz.utils.TradeUtils;

/**
 * 融资融券--查询
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/7/29
 */
public class RSelectFragment extends AbsBaseFragment {
    /**
     * 融资融券的Activity
     */
    private RSecuritiesMarginActivity mActivity;
    /**
     * 根布局
     */
    private ScrollView mScrollView;
    /**
     * 嵌套于ScrollView的布局
     */
    private View mChildScrollView;
    /**
     * 控制器
     */
    private RSelectViewController mController;
    /**
     *资产负债
     */
    private TextView mTvPropert;
    /**
     *股份持仓
     */
    private TextView mTvStockHold;
    /**
     *当日委托
     */
    private TextView mTvTodayEntrust;
    /**
     *当日成交
     */
    private TextView mTvTodayTrade;
    /**
     *未偿还合约
     */
    private TextView mTvOutstanding;
    /**
     *已了结合约
     */
    private TextView mTvInstanding;
    /**
     *历史委托
     */
    private TextView mTvHistoryEntrust;
    /**
     *历史成绩
     */
    private TextView mTvHistoryTrade;
    /**
     *历史资金流水
     */
    private TextView mTvMoneyWater;
    /**
     *标的证券
     */
    private TextView mTvObjectStock;
    /**
     *担保品证券
     */
    private TextView mTvCollater;
    /**
     *授信额度查询
     */
    private TextView mTvLimitSelect;
    /**
     *交割单
     */
    private TextView mTvDeliveryOrder;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_common_scrollview, null);
        mChildScrollView =inflater.inflate(R.layout.fragment_r_select, null);
        findViews(rootView);
        initData();
        setListeners();
        initViews();
        return rootView;
    }

    @Override
    protected void findViews(View view) {
        mScrollView=(ScrollView)view.findViewById(R.id.sll_layout);
        mScrollView.addView(mChildScrollView);

        mTvPropert=(TextView)view.findViewById(R.id.tv_nstock);
        mTvStockHold=(TextView)view.findViewById(R.id.tv_new_tock);
        mTvTodayEntrust=(TextView)view.findViewById(R.id.t_new_stck);
        mTvTodayTrade=(TextView)view.findViewById(R.id.tv_newstock);
        mTvOutstanding=(TextView)view.findViewById(R.id.tv_ne_ock);
        mTvInstanding=(TextView)view.findViewById(R.id.tv_ne_stock);
        mTvHistoryEntrust=(TextView)view.findViewById(R.id.t_new_stock);
        mTvHistoryTrade=(TextView)view.findViewById(R.id.tv_new_sock);
        mTvMoneyWater=(TextView)view.findViewById(R.id.tv_nw_stock);
        mTvObjectStock=(TextView)view.findViewById(R.id.tv_new_stck);
        mTvCollater=(TextView)view.findViewById(R.id.tv_nestock);
        mTvLimitSelect=(TextView)view.findViewById(R.id.tvew_stock);
        mTvDeliveryOrder=(TextView)view.findViewById(R.id.tv_newtock);
    }

    @Override
    protected void setListeners() {
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvPropert, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvStockHold, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvTodayEntrust, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvTodayTrade, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvOutstanding, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvInstanding, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvHistoryEntrust, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvHistoryTrade, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvMoneyWater, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvObjectStock, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvCollater, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvLimitSelect, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvDeliveryOrder, mController);
    }

    @Override
    protected void initData() {
        mActivity=(RSecuritiesMarginActivity)getActivity();
        mController=new RSelectViewController(this);

    }

    @Override
    protected void initViews() {
        setTheme();
    }

    @Override
    protected void setTheme() {

    }

    /**
     * 点击“ 资产负债 ”所执行的操作
     */
    public void clickPropert(){
        startActivity(new Intent(mActivity, RSelectPropertyActivity.class));
    }
    /**
     * 点击“ 股份持仓 ”所执行的操作
     */
    public void clickHoldStock(){
        startActivity(new Intent(mActivity, RSelectHoldStockActivity.class));
    }
    /**
     * 点击“ 当日委托”所执行的操作
     */
    public void clickTodayEntrust(){
        Intent intent=new Intent(mActivity, RSelectTodayActivity.class);
        Bundle bundle=new Bundle();
        bundle.putInt("ViewPagerFragmentPos", 0);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    /**
     * 点击“ 当日成交 ”所执行的操作
     */
    public void clickTodayTrade(){
        Intent intent=new Intent(mActivity, RSelectTodayActivity.class);
        Bundle bundle=new Bundle();
        bundle.putInt("ViewPagerFragmentPos", 1);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    /**
     * 点击“ 历史委托 ”所执行的操作
     */
    public void clickHistoryEntrust(){
        Intent intent=new Intent(mActivity, RSelectHistoryActivity.class);
        Bundle bundle=new Bundle();
        bundle.putInt("ViewPagerFragmentPos", 0);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    /**
     * 点击“ 历史成交 ”所执行的操作
     */
    public void clickHistoryTrade(){
        Intent intent=new Intent(mActivity, RSelectHistoryActivity.class);
        Bundle bundle=new Bundle();
        bundle.putInt("ViewPagerFragmentPos", 1);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * 点击“ 未偿还合约 ”所执行的操作
     */
    public void clickOutstanding(){
        startActivity(new Intent(mActivity, RSelectContractNotActivity.class));
    }
    /**
     * 点击“ 已了结合约 ”所执行的操作
     */
    public void clickInstanding(){
        startActivity(new Intent(mActivity, RSelectContractYesActivity.class));
    }
    /**
     * 点击“ 历史资金流水 ”所执行的操作
     */
    public void clickMoneyWater(){
        startActivity(new Intent(mActivity, RSelectWaterMoneyActivity.class));
    }
    /**
     * 点击“ 标的证券 ”所执行的操作
     */
    public void clickObjectStock(){
        startActivity(new Intent(mActivity, RSelectObjectSecurityActivity.class));
    }/**
     * 点击“ 担保品证券 ”所执行的操作
     */
    public void clickCollater(){
        startActivity(new Intent(mActivity, RSelectCollateralSecurityActivity.class));
    }
    /**
     * 点击“ 授信额度查询  ”所执行的操作
     */
    public void clickLimitSelect(){
        startActivity(new Intent(mActivity, RSelectCreditLimitActivity.class));
    }
    /**
     * 点击“ 交割单 ”所执行的操作
     */
    public void clickDeliveryOrder(){
        startActivity(new Intent(mActivity, RSelectDOselectActivity.class));
    }
}

class RSelectViewController extends AbsBaseController implements View.OnClickListener {

    private RSelectFragment mSelectFragment;

    public RSelectViewController(RSelectFragment mFragment) {
        mSelectFragment = mFragment;
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
        if (resId == R.id.tv_nstock) { //资产负债
            mSelectFragment.clickPropert();
        } else if (resId == R.id.tv_new_tock) { //股份持仓
            mSelectFragment.clickHoldStock();
        } else if (resId == R.id.t_new_stck) {//当日委托
            mSelectFragment.clickTodayEntrust();
        }else if (resId== R.id.tv_newstock){//当日成交
            mSelectFragment.clickTodayTrade();
        }else if (resId== R.id.tv_ne_ock){//未偿还合约
            mSelectFragment.clickOutstanding();
        }else if (resId== R.id.tv_ne_stock){//已了结合约
            mSelectFragment.clickInstanding();
        }else if (resId== R.id.t_new_stock){//历史委托
            mSelectFragment.clickHistoryEntrust();
        }else if (resId== R.id.tv_new_sock){//历史成交
            mSelectFragment.clickHistoryTrade();
        }else if (resId== R.id.tv_nw_stock){//历史资金流水
            mSelectFragment.clickMoneyWater();
        }else if (resId== R.id.tv_new_stck){//标的证券
            mSelectFragment.clickObjectStock();
        }else if (resId== R.id.tv_nestock){//担保品证券
            mSelectFragment.clickCollater();
        }else if (resId== R.id.tvew_stock){//授信额度查询
            mSelectFragment.clickLimitSelect();
        }else if (resId== R.id.tv_newtock){//交割单
            mSelectFragment.clickDeliveryOrder();
        }
    }
}
