package com.thinkive.android.trade_bz.a_hk.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.thinkive.framework.compatible.TKFragmentActivity;
import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_hk.activity.HKBuyOrSaleActivity;
import com.thinkive.android.trade_bz.a_hk.activity.HKCompanyActionActivity;
import com.thinkive.android.trade_bz.a_hk.activity.HKOtherSaleActivity;
import com.thinkive.android.trade_bz.a_hk.activity.HKRevocationActivity;
import com.thinkive.android.trade_bz.a_hk.activity.HKVoteSubActivity;
import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;
import com.thinkive.android.trade_bz.a_stock.fragment.AbsBaseFragment;

/**
 * 港股通 下单
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/6/6
 */
public class HKOrderBuyFragment extends AbsBaseFragment {
    private TKFragmentActivity mActivity;
    private HKOrderBuyController mController;
    /**
     * 买入
     */
    private TextView mTvBuy;
    /**
     * 卖出
     */
    private TextView mTvSale;
    /**
     * 零股卖出
     */
    private TextView mTvOtherSale;
    /**
     * 撤单
     */
    private TextView mTvRevocation;
    /**
     * 投票申报
     */
    private TextView mTvVoteSub;
    /**
     * 公司行为
     */
    private TextView mCompanyAction;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_hk_order_buy, null);
        findViews(rootView);
        initData();
        setListeners();
        initViews();
        return rootView;
    }
    @Override
    protected void findViews(View view) {
        mTvBuy = (TextView) view.findViewById(R.id.tv_hk_buy);
        mTvSale = (TextView) view.findViewById(R.id.tv_hk_sale);
        mTvOtherSale = (TextView) view.findViewById(R.id.tv_hk_other_sale);
        mTvRevocation = (TextView) view.findViewById(R.id.tv_hk_revocation);
        mTvVoteSub = (TextView) view.findViewById(R.id.tv_select_vote_sub);
        mCompanyAction = (TextView) view.findViewById(R.id.tv_select_company_action);
    }

    @Override
    protected void setListeners() {
        registerListener(AbsBaseController.ON_CLICK,mTvBuy,mController);
        registerListener(AbsBaseController.ON_CLICK,mTvSale,mController);
        registerListener(AbsBaseController.ON_CLICK,mTvOtherSale,mController);
        registerListener(AbsBaseController.ON_CLICK,mTvRevocation,mController);
        registerListener(AbsBaseController.ON_CLICK,mTvVoteSub,mController);
        registerListener(AbsBaseController.ON_CLICK,mCompanyAction,mController);
    }

    @Override
    protected void initData() {
        mActivity = (TKFragmentActivity)getActivity();
        mController = new HKOrderBuyController(this);
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void setTheme() {

    }

    /**
     * 买入
     */
    public void onClickBuy() {
        Intent intent = new Intent(mActivity, HKBuyOrSaleActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("buyOrSale", 0);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * 卖出
     */
    public void onClickSale() {
        Intent intent = new Intent(mActivity, HKBuyOrSaleActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("buyOrSale", 1);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     *零股卖出
     */
    public void onClickOtherSale() {
        startActivity(new Intent(mActivity, HKOtherSaleActivity.class));
    }

    /**
     * 撤单
     */
    public void onClickRevocation() {
        startActivity(new Intent(mActivity, HKRevocationActivity.class));
    }

    /**
     * 投票申报
     */
    public void onClickVoteSub() {
        startActivity(new Intent(mActivity, HKVoteSubActivity.class));
    }

    /**
     * 公司行为
     */
    public void onClickCompanyAction() {
        startActivity(new Intent(mActivity, HKCompanyActionActivity.class));
    }
}

class HKOrderBuyController extends AbsBaseController implements View.OnClickListener{

    private HKOrderBuyFragment mFragment;
    public HKOrderBuyController(HKOrderBuyFragment fragment) {
        mFragment = fragment;
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
    public void onClick(View view) {
        int resId = view.getId();
        if (resId == R.id.tv_select_vote_sub) {//投票申报
            mFragment.onClickVoteSub();
        } else if (resId == R.id.tv_select_company_action) {//公司行为
            mFragment.onClickCompanyAction();
        } else if (resId == R.id.tv_hk_buy) {//买入
            mFragment.onClickBuy();
        } else if (resId == R.id.tv_hk_sale) {//卖出
            mFragment.onClickSale();
        } else if (resId == R.id.tv_hk_other_sale) {//零股卖出
            mFragment.onClickOtherSale();
        } else if (resId == R.id.tv_hk_revocation) {//撤单
            mFragment.onClickRevocation();
        }
    }
}
