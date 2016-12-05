package com.thinkive.android.trade_bz.a_rr.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_rr.activity.RSelectCreditLimitActivity;
import com.thinkive.android.trade_bz.a_rr.bean.RSelectPropertBean;
import com.thinkive.android.trade_bz.a_rr.bll.RSelectCraditLimitServiceImpl;
import com.thinkive.android.trade_bz.a_stock.fragment.AbsBaseFragment;

/**
 * 融资融券--查询--授信额度查询
 * Announcements：
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/8/19
 */

public class RSelectCreditLimitFragment extends AbsBaseFragment {
    /**
     *Fragment的宿主
     */
    private RSelectCreditLimitActivity mActivity;
    /**
     *该Fragement的业务类
     */
    private RSelectCraditLimitServiceImpl mServices;
    /**
     * 授信总额度
     */
    private TextView mTvAllLimit;
    /**
     *融资剩余额度
     */
    private TextView mTvOtherZi;
    /**
     *融券剩余额度
     */
    private TextView mTvOtherQuan;
    /**
     * 正在加载
     */
    private LinearLayout mLinLoading;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_r_select_limit,null);
        findViews(rootView);
        initData();
        setListeners();
        initViews();
        return rootView;
    }

    @Override
    protected void findViews(View view) {
        mTvAllLimit=(TextView)view.findViewById(R.id.tv_limit_all);
        mTvOtherZi=(TextView)view.findViewById(R.id.tv_limit_other_zi);
        mTvOtherQuan=(TextView)view.findViewById(R.id.tv_limit_other_quan);
        mLinLoading = (LinearLayout)view.findViewById(R.id.lin_loading_set);
    }

    @Override
    protected void setListeners() {
    }

    @Override
    protected void initData() {
        mActivity = (RSelectCreditLimitActivity) getActivity();
        mServices = new RSelectCraditLimitServiceImpl(this);

    }

    @Override
    protected void initViews() {
        mServices.requestCreditLimit();
        setTheme();
    }

    @Override
    protected void setTheme() {

    }

    /**
     * 接收业务类传递过来的授信额度数据
     * @param data
     */
    public void getCreditLimit(RSelectPropertBean data) {
        mLinLoading.setVisibility(View.GONE);
        mTvAllLimit.setText(data.getAcreditavl());
        mTvOtherZi.setText(data.getFcreditavl());
        mTvOtherQuan.setText(data.getDcreditavl());
    }
}

