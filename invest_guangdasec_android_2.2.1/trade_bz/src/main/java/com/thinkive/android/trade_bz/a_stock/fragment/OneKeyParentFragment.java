package com.thinkive.android.trade_bz.a_stock.fragment;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;

/**
 * Created by Administrator on 2016/12/6.
 */
public class OneKeyParentFragment  extends Fragment implements View.OnClickListener {
    private RelativeLayout mBgOfHeader;//toolbar位置布局背景
    private TextView mOneKey;//资金账号登录tab
    private TextView mOneKeyMoney;//阳光E账通登录Tab
    private View mIndicLeft;//左边tab底部指示器
    private View mIndicRight;//右边指示器
    private OneKeyFragment  mOneKeyFragment;//普通登录碎片
    private OneKeyMoneyFragment  mOneKeyMoneyFragment;//信用登陆碎片
    private TextView mHeaderText;//toolbar显示文字
    private ImageView mBackIv;//顶部返回按钮
    private FrameLayout mFlContainer;//碎片容器
    protected GradientDrawable mBgDrawable;//顶部toobar位置布局背景
    private View mView;
    private Bundle mBundle;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_onekey_parent, container, false);
        initView(mView);
        initListener();
        processLogic();
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    private void initView(View view) {
        mBackIv = (ImageView) view.findViewById(R.id.iv_back);
        mHeaderText = (TextView) view.findViewById(R.id.tb_showname);
        mBgOfHeader = (RelativeLayout) view.findViewById(R.id.rl_bgoftoolbar);
        mOneKey = (TextView) view.findViewById(R.id.tv_onekey);
        mOneKey.setTextColor(getResources().getColor(R.color.statusbar_main));
        mOneKeyMoney = (TextView) view.findViewById(R.id.tv_onekey_money);
        mIndicLeft = view.findViewById(R.id.indcator_left);
        mIndicRight = view.findViewById(R.id.indcator_right);
        mIndicRight.setVisibility(View.INVISIBLE);
        mFlContainer = (FrameLayout) view.findViewById(R.id.fl_container);
        mOneKeyFragment = new OneKeyFragment ();
        mOneKeyMoneyFragment = new OneKeyMoneyFragment ();
        mOneKeyFragment.setArguments(getArguments());
        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.fl_container_childe, mOneKeyFragment, getActivity().getResources().getString(R.string.fund_login)).commit();
//        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.fl_container_childe, mOneKeyMoneyFragment, getActivity().getResources().getString(R.string.sune_login)).commit();
//        getActivity().getSupportFragmentManager().beginTransaction().hide(mOneKeyMoneyFragment).commit();
    }


    private void initListener() {
        mOneKey.setOnClickListener(this);
        mOneKeyMoney.setOnClickListener(this);
        mBackIv.setOnClickListener(this);
    }

    //// TODO: 2016/10/12
    private void processLogic() {
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_onekey) {
            showLeft();
        }
        if (v.getId() == R.id.tv_onekey_money) {

            showRight();
        }
        if (v.getId() == R.id.iv_back) {

            getActivity().finish();
        }
    }

    private void showRight() {
        mIndicLeft.setVisibility(View.INVISIBLE);
        mIndicRight.setVisibility(View.VISIBLE);
        mOneKeyMoney.setTextColor(getResources().getColor(R.color.statusbar_main));
        mOneKey.setTextColor(getResources().getColor(R.color.trade_top_tab_dark_gray));
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fl_container_childe,mOneKeyMoneyFragment).commit();
//        getActivity().getSupportFragmentManager().beginTransaction().hide(mOneKeyFragment).commit();

    }

    private void showLeft() {
        mOneKey.setTextColor(getResources().getColor(R.color.statusbar_main));
        mOneKeyMoney.setTextColor(getResources().getColor(R.color.trade_top_tab_dark_gray));
        mIndicLeft.setVisibility(View.VISIBLE);
        mIndicRight.setVisibility(View.INVISIBLE);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fl_container_childe,mOneKeyFragment).commit();
//        getActivity().getSupportFragmentManager().beginTransaction().hide(mOneKeyMoneyFragment).commit();
    }


}

