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
import com.thinkive.android.trade_bz.others.constants.Constants;
import com.thinkive.android.trade_bz.others.tools.TradeLoginManager;


/**
 * Created by Administrator on 2016/10/9.
 */
public class LoginParentFragment extends Fragment implements View.OnClickListener {
    private RelativeLayout mBgOfHeader;//toolbar位置布局背景
    private TextView mFundLoginTv;//资金账号登录tab
    private TextView mSuneLoginTv;//阳光E账通登录Tab
    private View mIndicLeft;//左边tab底部指示器
    private View mIndicRight;//右边指示器
    private FundLoginFragment mFundLoginFragment;//普通登录碎片
    private SunEFragment mSunEFragment;//信用登陆碎片
    private TextView mHeaderText;//toolbar显示文字
    private ImageView mBackIv;//顶部返回按钮
    private FrameLayout mFlContainer;//碎片容器
    protected GradientDrawable mBgDrawable;//顶部toobar位置布局背景
    private View mView;
    private Bundle mBundle;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_login_parent, container, false);
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
        mFundLoginTv = (TextView) view.findViewById(R.id.tv_login_fund);
        mFundLoginTv.setTextColor(getResources().getColor(R.color.statusbar_main));
        mSuneLoginTv = (TextView) view.findViewById(R.id.tv_login_sun_e);
        mIndicLeft = view.findViewById(R.id.indcator_left);
        mIndicRight = view.findViewById(R.id.indcator_right);
        mIndicRight.setVisibility(View.INVISIBLE);
        mFlContainer = (FrameLayout) view.findViewById(R.id.fl_container);
        mFundLoginFragment = new FundLoginFragment();
        mSunEFragment = new SunEFragment();
        mBundle = new Bundle();
        mBundle.putString(Constants.LOGIN_TYPE,getArguments().getString("loginType"));
        mBundle.putString("clickIdBeforeLogin",getArguments().getString("clickIdBeforeLogin"));
        mFundLoginFragment.setArguments(mBundle);
        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.fl_container_childe, mFundLoginFragment, getActivity().getResources().getString(R.string.fund_login)).commit();
        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.fl_container_childe, mSunEFragment, getActivity().getResources().getString(R.string.sune_login)).commit();
        getActivity().getSupportFragmentManager().beginTransaction().hide(mSunEFragment).commit();
        switch (getArguments().getString("loginType")) {
            case TradeLoginManager.LOGIN_TYPE_NORMAL:
                mHeaderText.setText(getResources().getString(R.string.normal_trade_login));
                break;
            case TradeLoginManager.LOGIN_TYPE_CREDIT:
                mHeaderText.setText(getResources().getString(R.string.credit_trade_login));
                break;
        }
    }


    private void initListener() {
        mFundLoginTv.setOnClickListener(this);
        mSuneLoginTv.setOnClickListener(this);
        mBackIv.setOnClickListener(this);
    }

    //// TODO: 2016/10/12
    private void processLogic() {
    }

    public FundLoginFragment getNormalLoginFragment() {
        return mFundLoginFragment;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_login_fund) {
            showLeft();
        }
        if (v.getId() == R.id.tv_login_sun_e) {

            showRight();
        }
        if (v.getId() == R.id.iv_back) {

            getActivity().finish();
        }
    }

    private void showRight() {
        mHeaderText.setText("信用登录");
        mIndicLeft.setVisibility(View.INVISIBLE);
        mIndicRight.setVisibility(View.VISIBLE);
        mSuneLoginTv.setTextColor(getResources().getColor(R.color.statusbar_main));
        mFundLoginTv.setTextColor(getResources().getColor(R.color.trade_top_tab_dark_gray));
        getActivity().getSupportFragmentManager().beginTransaction().show(mSunEFragment).commit();
        getActivity().getSupportFragmentManager().beginTransaction().hide(mFundLoginFragment).commit();

    }

    private void showLeft() {
        mHeaderText.setText("普通登录");
        mFundLoginTv.setTextColor(getResources().getColor(R.color.statusbar_main));
        mSuneLoginTv.setTextColor(getResources().getColor(R.color.trade_top_tab_dark_gray));
        mIndicLeft.setVisibility(View.VISIBLE);
        mIndicRight.setVisibility(View.INVISIBLE);
        getActivity().getSupportFragmentManager().beginTransaction().show(mFundLoginFragment).commit();
        getActivity().getSupportFragmentManager().beginTransaction().hide(mSunEFragment).commit();
    }


}
