package com.thinkive.android.trade_bz.a_option.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.thinkive.framework.compatible.ListenerControllerAdapter;
import com.android.thinkive.framework.compatible.TKFragmentActivity;
import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_option.activity.OptionBuyOrSaleCloseActivity;
import com.thinkive.android.trade_bz.a_option.activity.OptionBuyOrSaleOpenActivity;
import com.thinkive.android.trade_bz.a_option.activity.OptionTicketOpenOrLockActivity;
import com.thinkive.android.trade_bz.a_option.activity.OptionRevocationActivity;
import com.thinkive.android.trade_bz.a_stock.activity.ChangePasswordActivity;
import com.thinkive.android.trade_bz.a_stock.bean.UserInfo;
import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;
import com.thinkive.android.trade_bz.a_stock.fragment.AbsBaseFragment;
import com.thinkive.android.trade_bz.others.tools.TradeLoginManager;
import com.thinkive.android.trade_bz.utils.ToastUtils;

/**
 * 个股期权下单
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/6/14
 */
public class OptionStockBuyFragment extends AbsBaseFragment {
    private OptionStockBuyController mController;
    private TKFragmentActivity mActivity;
    private View mChildScrollView;
    private ScrollView mScrollView;
    private TextView mTvBuyOpen;
    private TextView mTvSaleOpen;
    private TextView mTvBuyClose;
    private TextView mTvSaleClose;
    private TextView mTvCoveredOpen;
    private TextView mTvCoveredClose;
    private TextView mTvExercise;
    private TextView mTvCoveredLock;
    private TextView mTvCoveredOpenLock;
    private TextView mTvOptionRevocation;
    private TextView mTvOptionChangePwd;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_common_scrollview, null);
        mChildScrollView =inflater.inflate(R.layout.fragment_option_buy, null);
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
        mTvBuyOpen = (TextView)mChildScrollView.findViewById(R.id.tv_buy_open);
        mTvSaleOpen = (TextView)mChildScrollView.findViewById(R.id.tv_sale_open);
        mTvBuyClose = (TextView)mChildScrollView.findViewById(R.id.tv_buy_close);
        mTvSaleClose = (TextView)mChildScrollView.findViewById(R.id.tv_sale_close);
        mTvCoveredOpen = (TextView)mChildScrollView.findViewById(R.id.tv_covered_open);
        mTvCoveredClose = (TextView)mChildScrollView.findViewById(R.id.tv_covered_close);
        mTvExercise = (TextView)mChildScrollView.findViewById(R.id.tv_exercise);
        mTvCoveredLock = (TextView)mChildScrollView.findViewById(R.id.tv_covered_lock);
        mTvCoveredOpenLock = (TextView)mChildScrollView.findViewById(R.id.tv_covered_open_lock);
        mTvOptionRevocation = (TextView)mChildScrollView.findViewById(R.id.tv_option_revocation);
        mTvOptionChangePwd = (TextView)mChildScrollView.findViewById(R.id.tv_option_change_pwd);
    }


    @Override
    protected void setListeners() {
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvBuyOpen, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvSaleOpen, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvBuyClose, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvSaleClose, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvCoveredOpen, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvCoveredClose, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvExercise, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvCoveredLock, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvCoveredOpenLock, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvOptionRevocation, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvOptionChangePwd, mController);
    }

    @Override
    protected void initData() {
        mActivity = (TKFragmentActivity) getActivity();
        mController = new OptionStockBuyController(this);
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void setTheme() {

    }

    /**
     * 买入开仓
     */
    public void onClickBuyOpen() {
        Intent intent = new Intent(mActivity, OptionBuyOrSaleOpenActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("buyOrSale", 0);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * 卖出开仓
     */
    public void onClickSaleOpen() {
        Intent intent = new Intent(mActivity, OptionBuyOrSaleOpenActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("buyOrSale", 1);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * 买入平仓
     */
    public void onClickBuyClose() {
        Intent intent = new Intent(mActivity, OptionBuyOrSaleCloseActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("buyOrSale", 0);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * 卖出平仓
     */
    public void onClickSaleClose() {
        Intent intent = new Intent(mActivity, OptionBuyOrSaleCloseActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("buyOrSale", 1);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    /**
     * 备兑开仓
     */
    public void onClickCoveredOpen() {
        Intent intent = new Intent(mActivity, OptionBuyOrSaleOpenActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("buyOrSale", 2);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * 备兑平仓
     */
    public void onClickCoveredClose() {
       Intent intent = new Intent(mActivity, OptionBuyOrSaleCloseActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("buyOrSale", 2);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * 行权
     */
    public void onClickExercise() {
        Intent intent = new Intent(mActivity, OptionBuyOrSaleCloseActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("buyOrSale", 3);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * 备兑券锁定
     */
    public void onClickCoveredLock() {
        Intent intent = new Intent(mActivity, OptionTicketOpenOrLockActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("buyOrSale", 0);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    /**
     * 备兑券解锁
     */
    public void onClickCoveredOpenLock() {
        Intent intent = new Intent(mActivity, OptionTicketOpenOrLockActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("buyOrSale", 1);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * 撤单
     */
    public void onClickRevocation() {
        Intent intent = new Intent(mActivity, OptionRevocationActivity.class);
        startActivity(intent);
    }

    /**
     *修改密码
     */
    public void clickChangePwd(){
        Intent intent = new Intent(mActivity,ChangePasswordActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("userType","2");
        intent.putExtras(bundle);
        mActivity.startActivity(intent);
    }
}

class OptionStockBuyController extends AbsBaseController implements View.OnClickListener {

    private OptionStockBuyFragment mFragment;

    public OptionStockBuyController(OptionStockBuyFragment mFragment) {
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
        if (resId == R.id.tv_buy_open) {
            mFragment.onClickBuyOpen();
        } else if (resId == R.id.tv_sale_open) {
            mFragment.onClickSaleOpen();
        } else if (resId == R.id.tv_buy_close) {
            mFragment.onClickBuyClose();
        } else if (resId == R.id.tv_sale_close) {
            mFragment.onClickSaleClose();
        } else if (resId == R.id.tv_covered_open) {
            //只有有沪市账户的才有备兑券的业务，所以要判断下
            UserInfo sOptionUserInfo_hu = TradeLoginManager.sOptionUserInfo_hu;
            String fund_account_hu = sOptionUserInfo_hu.getFund_account();
            if(!"".equals(fund_account_hu)){
                mFragment.onClickCoveredOpen();
            }else{
                ToastUtils.toast(getContext(),R.string.option_error18);
            }
        } else if (resId == R.id.tv_covered_close) {
            //只有有沪市账户的才有备兑券的业务，所以要判断下
            UserInfo sOptionUserInfo_hu = TradeLoginManager.sOptionUserInfo_hu;
            String fund_account_hu = sOptionUserInfo_hu.getFund_account();
            if(!"".equals(fund_account_hu)){
                mFragment.onClickCoveredClose();
            }else{
                ToastUtils.toast(getContext(),R.string.option_error18);
            }
        } else if (resId == R.id.tv_exercise) {
            mFragment.onClickExercise();
        } else if (resId == R.id.tv_covered_lock) {
            //只有有沪市账户的才有备兑券的业务，所以要判断下
            UserInfo sOptionUserInfo_hu = TradeLoginManager.sOptionUserInfo_hu;
            String fund_account_hu = sOptionUserInfo_hu.getFund_account();
            if(!"".equals(fund_account_hu)){
                mFragment.onClickCoveredLock();
            }else{
                ToastUtils.toast(getContext(),R.string.option_error18);
            }
        } else if (resId == R.id.tv_covered_open_lock) {
            //只有有沪市账户的才有备兑券的业务，所以要判断下
            UserInfo sOptionUserInfo_hu = TradeLoginManager.sOptionUserInfo_hu;
            String fund_account_hu = sOptionUserInfo_hu.getFund_account();
            if(!"".equals(fund_account_hu)){
                mFragment.onClickCoveredOpenLock();
            }else{
                ToastUtils.toast(getContext(),R.string.option_error18);
            }
        } else if (resId == R.id.tv_option_revocation) {
            mFragment.onClickRevocation();
        }else if (resId == R.id.tv_option_change_pwd) {
            mFragment.clickChangePwd();
        }
    }
}

