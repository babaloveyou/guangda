package com.thinkive.android.trade_bz.a_rr.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.thinkive.framework.compatible.ListenerControllerAdapter;
import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_rr.activity.BalanceDetailActivity;
import com.thinkive.android.trade_bz.a_rr.bean.RSelectPropertBean;
import com.thinkive.android.trade_bz.a_rr.bll.RRHolderPagerServicesImpl;
import com.thinkive.android.trade_bz.a_stock.activity.MultiCreditTradeActivity;
import com.thinkive.android.trade_bz.a_stock.activity.TransferBanktActivity;
import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;
import com.thinkive.android.trade_bz.a_stock.fragment.AbsBaseFragment;
import com.thinkive.android.trade_bz.others.tools.TradeLoginManager;
import com.thinkive.android.trade_bz.utils.TradeUtils;

/**
 * Created by Administrator on 2016/11/26.
 */
public class RRHolderPagerFragment extends AbsBaseFragment {
    /**
     * 头部pager的业务类
     */
    private RRHolderPagerServicesImpl mServices;
    /**
     * 关联的Activity
     */
    private MultiCreditTradeActivity mActivity;
    /**
     * 控制器
     */
    private PagerHoldViewController mPagerHoldViewController;

    private int mResIdMoneyNumId;
    private int mResIdImgIcon;
    /**
     * 银行转账按钮
     */
    private TextView mTvBlankMoney;
    private TextView mTotalAssetsTv;
    private TextView mNetAssetsTv;
    private TextView mTotalIndebtednessTv;
    private TextView mQuerryIndebtednessTv;
    private TextView mKeepTv;
    private RSelectPropertBean mSelectPropertBean;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_a_rr_header_page, null);
        findViews(rootView);
        initData();

        initViews();
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setListeners();
    }

    @Override
    protected void findViews(View view) {
        mKeepTv = (TextView) view.findViewById(R.id.tv_keep);
        mTvBlankMoney = (TextView) view.findViewById(R.id.tv_holdpager_bank);
        mTotalAssetsTv = (TextView) view.findViewById(R.id.tv_total_assets);
        mNetAssetsTv = (TextView) view.findViewById(R.id.tv_net_assets);
        mTotalIndebtednessTv = (TextView) view.findViewById(R.id.tv_total_indebtedness);
        mQuerryIndebtednessTv = (TextView) view.findViewById(R.id.tv_querry_indebtedness);


    }

    @Override
    protected void setListeners() {
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvBlankMoney, mPagerHoldViewController);



    }

    @Override
    protected void initData() {
        mActivity = (MultiCreditTradeActivity) getActivity();
        mPagerHoldViewController = new PagerHoldViewController(this);
        mServices = new RRHolderPagerServicesImpl(this);
    }

    @Override
    protected void initViews() {
        mServices.requestMyHoldPager();
        setTheme();
    }


    @Override
    protected void setTheme() {

    }

    /**
     * 设置通用的头部信息
     */
    public void setOriginalViews(int moneyNumId, int iconId) {
        mResIdMoneyNumId = moneyNumId;
        mResIdImgIcon = iconId;
    }

    /**
     * 接收业务类传递过来的数据,并设置
     *
     * @param bean
     */
    public void getMoneyAccountData(RSelectPropertBean bean) {
        mSelectPropertBean = bean;
        mKeepTv.setText(bean.getPer_assurescale_value());
        mTotalAssetsTv.setText(bean.getAssert_val());
        mNetAssetsTv.setText(bean.getNet_asset());
        mTotalIndebtednessTv.setText(bean.getTotal_debit());
    }


    /**
     * 点击银证转账按钮所执行的操作
     * 使用普通账户进入模块
     */
    public void onClickBank() {
        if (!TradeUtils.isFastClick()) {
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putString("userType", TradeLoginManager.LOGIN_TYPE_CREDIT);
            intent.setClass(mActivity, TransferBanktActivity.class);
            intent.putExtras(bundle);
            mActivity.startActivity(intent);
        }
    }
    /**
     * 资产负债汇总查询
     */
    public void onclickQuerry() {
        Intent intent = new Intent(getActivity(), BalanceDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("bean", mSelectPropertBean);
        intent.putExtras(bundle);
        getActivity().startActivity(intent);
    }

}

class PagerHoldViewController extends AbsBaseController implements View.OnClickListener {

    private RRHolderPagerFragment mHoldPagerFragment;

    public PagerHoldViewController(RRHolderPagerFragment mFragment) {
        mHoldPagerFragment = mFragment;
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
        if (resId == R.id.tv_holdpager_bank) {
            mHoldPagerFragment.onClickBank();
        } else if (resId == R.id.tv_querry_indebtedness) {
            mHoldPagerFragment.onclickQuerry();
        }
    }
}



