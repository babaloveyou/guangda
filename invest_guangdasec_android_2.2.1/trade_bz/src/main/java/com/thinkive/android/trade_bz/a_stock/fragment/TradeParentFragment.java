package com.thinkive.android.trade_bz.a_stock.fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.others.tools.TradeFlags;

/**
 * Created by Administrator on 2016/10/10.
 */
public class TradeParentFragment extends Fragment implements View.OnClickListener {
    private View mView;
    private RelativeLayout mParentOfTabRl;
    private TextView mNormalTabTv;
    private TextView mCreditTabTv;
    private FrameLayout mFlContainer;



    private boolean isNormalTrade = true;
    private NormalTradeFragment mNormalTradeFragment;
    private CreditTradeFragment mCreditTradeFragment;
    private Drawable mBgNormalSelect;
    private Drawable mBgCreditSelect;
    private TextView mLoginTv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_tradeparent, container, false);
        }
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        findViewById();
        initView();
        initListener();
    }

    public void setLogTvClickable(boolean clickable) {
        mLoginTv.setClickable(clickable);
    }

    private void initView() {
        mNormalTradeFragment = new NormalTradeFragment();
        mNormalTradeFragment.setParent(this);
        mCreditTradeFragment = new CreditTradeFragment();
        mCreditTradeFragment.setParent(this);
        mNormalTradeFragment.init(getActivity());
        mCreditTradeFragment.init(getActivity());
        getChildFragmentManager().beginTransaction().add(R.id.fl_container, mNormalTradeFragment).commit();
        getChildFragmentManager().beginTransaction().add(R.id.fl_container, mCreditTradeFragment).commit();
        getChildFragmentManager().beginTransaction().hide(mCreditTradeFragment).commit();
        mBgNormalSelect = getResources().getDrawable(R.drawable.shape_bg_trade_tab_normal_select);
        mBgCreditSelect = getResources().getDrawable(R.drawable.shape_bg_trade_tab_credit_select);
        mNormalTabTv.setBackgroundDrawable(mBgNormalSelect);
    }


    private void initListener() {
        mNormalTabTv.setOnClickListener(this);
        mCreditTabTv.setOnClickListener(this);
    }

    private void findViewById() {
        mParentOfTabRl = (RelativeLayout) mView.findViewById(R.id.tab_replace_toolbar);
        mFlContainer = (FrameLayout) mView.findViewById(R.id.fl_container);
        mNormalTabTv = (TextView) mView.findViewById(R.id.tv_tab_normal);
        mCreditTabTv = (TextView) mView.findViewById(R.id.tv_tab_credit);
        mLoginTv = (TextView) mView.findViewById(R.id.tv_login);
    }
    public boolean isNormalTrade() {
        return isNormalTrade;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_tab_normal) {
            showNormal();
            if (!TradeFlags.check(TradeFlags.FLAG_NORMAL_TRADE_YES)) {
                setExitTologin();
            } else {
                setLoginToExit();
            }
        } else if (v.getId() == R.id.tv_tab_credit) {
            showCredit();
            if (!TradeFlags.check(TradeFlags.FLAG_CREDIT_TRADE_YES)) {
                setExitTologin();
            } else {
                setLoginToExit();
            }
        }
    }

    public void initLoginTvListener() {
        mLoginTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNormalTrade) {
                    if (!TradeFlags.check(TradeFlags.FLAG_NORMAL_TRADE_YES)) {
                        mNormalTradeFragment.tradeMainLogin();
                    } else {
                        mNormalTradeFragment.tradeMainLogout();
                    }
                } else {
                    //// TODO: 2016/10/22
                    if (!TradeFlags.check(TradeFlags.FLAG_CREDIT_TRADE_YES)) {
                        mCreditTradeFragment.tradeMainLogin();
                    } else {
                        mCreditTradeFragment.tradeMainLogout();
                    }
                }

            }
        });
    }

    private void showCredit() {
        isNormalTrade = false;
        mCreditTabTv.setBackgroundDrawable(mBgCreditSelect);
        mNormalTabTv.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_bg_trade_tab_normal));
        getChildFragmentManager().beginTransaction().show(mCreditTradeFragment).commit();
        getChildFragmentManager().beginTransaction().hide(mNormalTradeFragment).commit();
        mCreditTradeFragment.scrollToTop();
    }

    private void showNormal() {
        mNormalTradeFragment.init(getActivity());
        isNormalTrade = true;
        mNormalTabTv.setBackgroundDrawable(mBgNormalSelect);
        mCreditTabTv.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_bg_trade_tab_credit));
        getChildFragmentManager().beginTransaction().show(mNormalTradeFragment).commit();
        getChildFragmentManager().beginTransaction().hide(mCreditTradeFragment).commit();
        mNormalTradeFragment.scrollToTop();
    }


    public void setLoginToExit() {
        mLoginTv.setVisibility(View.GONE);
//        mLoginTv.setText("注销");
    }

    public void setExitTologin() {
        mLoginTv.setVisibility(View.VISIBLE);
        mLoginTv.setText("登录");
    }

}
