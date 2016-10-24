package com.thinkive.android.trade_bz.a_rr.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.thinkive.framework.compatible.ListenerControllerAdapter;
import com.android.thinkive.framework.compatible.TKFragmentActivity;
import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_new.activity.NewStockMainActivity;
import com.thinkive.android.trade_bz.a_rr.activity.RBuyStockToStockActivity;
import com.thinkive.android.trade_bz.a_rr.activity.RCreditBuyActivity;
import com.thinkive.android.trade_bz.a_rr.activity.RCashReturnActivity;
import com.thinkive.android.trade_bz.a_rr.activity.RCollaterBuyOrSaleActivity;
import com.thinkive.android.trade_bz.a_rr.activity.RContractExpandActivity;
import com.thinkive.android.trade_bz.a_rr.activity.RCreditSaleActivity;
import com.thinkive.android.trade_bz.a_rr.activity.RSaleStockToMoneyActivity;
import com.thinkive.android.trade_bz.a_rr.activity.RRevocationActivity;
import com.thinkive.android.trade_bz.a_rr.activity.RStockReturnStockActivity;
import com.thinkive.android.trade_bz.a_stock.activity.ChangePasswordActivity;
import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;
import com.thinkive.android.trade_bz.a_stock.fragment.AbsBaseFragment;
import com.thinkive.android.trade_bz.others.tools.TradeLoginManager;
import com.thinkive.android.trade_bz.utils.ToastUtils;
import com.thinkive.android.trade_bz.utils.TradeUtils;

/**
 * 融资融券--下单
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/7/29
 */
public class ROrdersFragment extends AbsBaseFragment {
    /**
     * 融资融券的Activity
     */
    private TKFragmentActivity mActivity;
    /**
     * 控制 融资融券--下单
     */
    private ROrdersController mController;
    /**
     * 融资买入
     */
    private TextView mTvBuyIn;
    /**
     *融券卖出
     */
    private TextView mTvSellOut;
    /**
     *担保品买入
     */
    private TextView mTvGuaranteeIn;
    /**
     *担保品卖出
     */
    private TextView mTvGuaranteeOut;
    /**
     *卖券还款
     */
    private TextView mTvSellTicket;
    /**
     *现金还款
     */
    private TextView mTvCashToMonty;
    /**
     *买券还券
     */
    private TextView mTvSellTicketToTicket;
    /**
     *现券还券
     */
    private TextView mTvTheTicket;
    /**
     *融资打新
     */
    private TextView mTvEquityTrade;
    /**
     *撤单
     */
    private TextView mTvRevocation;
    /**
     *更多
     */
    private TextView mTvMore;
    /**
     * 合约展期
     */
    private TextView mTvContract;
    /**
     * 修改密码
     */
    private TextView mTvChangePwd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_r_orders, null);
        findViews(rootView);
        initData();
        setListeners();
        initViews();
        return rootView;
    }

    @Override
    protected void findViews(View view) {
        mTvBuyIn =(TextView)view.findViewById(R.id.tv_r_buy_in);
        mTvSellOut =(TextView)view.findViewById(R.id.tv_r_sell_out);
        mTvGuaranteeIn =(TextView)view.findViewById(R.id.tv_r_assure_buy);
        mTvGuaranteeOut =(TextView)view.findViewById(R.id.tv_r_assure_sell);
        mTvSellTicket =(TextView)view.findViewById(R.id.tv_r_sell_coupon_payments);
        mTvCashToMonty =(TextView)view.findViewById(R.id.tv_r_money_payments);
        mTvSellTicketToTicket =(TextView)view.findViewById(R.id.tv_r_buy_coupon_payments);
        mTvTheTicket =(TextView)view.findViewById(R.id.tv_r_payments);
        mTvEquityTrade =(TextView)view.findViewById(R.id.tv_r_equity_trading);
        mTvRevocation =(TextView)view.findViewById(R.id.tv_r_revocation);
        mTvMore =(TextView)view.findViewById(R.id.tv_r_more);
        mTvContract = (TextView)view.findViewById(R.id.tv_r_contract);
        mTvChangePwd = (TextView)view.findViewById(R.id.tv_r_change_pwd);
    }

    @Override
    protected void setListeners() {
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvBuyIn, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvSellOut, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvGuaranteeIn, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvGuaranteeOut, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvSellTicket, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvCashToMonty, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvSellTicketToTicket, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvTheTicket, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvEquityTrade, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvRevocation, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvMore, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvContract, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvChangePwd, mController);
    }

    @Override
    protected void initData() {
        mActivity=(TKFragmentActivity)getActivity();
        mController=new ROrdersController(this);

    }

    @Override
    protected void initViews() {
        setTheme();
    }

    @Override
    protected void setTheme() {
    }

    /**
     * 融资买入
     */
    public void clickBuyIn(){
        Intent intent = new Intent(mActivity, RCreditBuyActivity.class);
        mActivity.startActivity(intent);
    }
    /**
     *融券卖出
     */
    public void clickSellOut(){
        Intent intent = new Intent(mActivity, RCreditSaleActivity.class);
        mActivity.startActivity(intent);
    }
    /**
     *担保品买入
     */
    public void clickGuaranteeIn(){
        Intent intent = new Intent(mActivity, RCollaterBuyOrSaleActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("buyOrSale",0);
        intent.putExtras(bundle);
        mActivity.startActivity(intent);
    }
    /**
     *担保品卖出
     */
    public void clickGuaranteeOut(){
        Intent intent = new Intent(mActivity, RCollaterBuyOrSaleActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("buyOrSale",1);
        intent.putExtras(bundle);
        mActivity.startActivity(intent);
    }
    /**
     *卖券还款
     */
    public void clickSellTicket(){
        Intent intent = new Intent(mActivity, RSaleStockToMoneyActivity.class);
        mActivity.startActivity(intent);
    }
    /**
     *现金还款
     */
    public void clickCashToMonty(){
        Intent intent = new Intent(mActivity, RCashReturnActivity.class);
        mActivity.startActivity(intent);
    }
    /**
     *买券还券
     */
    public void clickSellTicketToTicket(){
        Intent intent = new Intent(mActivity, RBuyStockToStockActivity.class);
        mActivity.startActivity(intent);
    }
    /**
     *现券还券
     */
    public void clickTheTicket(){
        Intent intent = new Intent(mActivity, RStockReturnStockActivity.class);
        mActivity.startActivity(intent);
    }
    /**
     *融资打新
     */
    public void clickEquityTrading(){
        Intent intent = new Intent(mActivity, NewStockMainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("userType", TradeLoginManager.LOGIN_TYPE_CREDIT);
        bundle.putInt("ViewPagerFragmentPos", 0);
        intent.putExtras(bundle);
        mActivity.startActivity(intent);
    }
    /**
     *撤单
     */
    public void clickRevocation(){
        startActivity(new Intent(mActivity, RRevocationActivity.class));
    }
    /**
     *更多
     */
    public void clickMore(){
        ToastUtils.toast(mActivity, R.string.contract_not19);
    }

    /**
     *合约展期
     */
    public void clickContractExtension(){
        Intent intent = new Intent(mActivity,RContractExpandActivity.class);
        mActivity.startActivity(intent);
    }
    /**
     *修改密码
     */
    public void clickChangePwd(){
        Intent intent = new Intent(mActivity,ChangePasswordActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("userType","1");
        intent.putExtras(bundle);
        mActivity.startActivity(intent);
    }
}

class ROrdersController extends AbsBaseController implements View.OnClickListener{

    private ROrdersFragment mFragment = null;

    public ROrdersController(ROrdersFragment ragment) {
        mFragment = ragment;
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
        if (resId == R.id.tv_r_buy_in) { //融资买入
            mFragment.clickBuyIn();
        }else if (resId == R.id.tv_r_sell_out){ //融券卖出
            mFragment.clickSellOut();
        }else if(resId == R.id.tv_r_assure_buy){ //担保品买入
            mFragment.clickGuaranteeIn();
        }else if(resId == R.id.tv_r_assure_sell){ //担保品卖出
            mFragment.clickGuaranteeOut();
        }else if (resId == R.id.tv_r_sell_coupon_payments){ //卖券还款
            mFragment.clickSellTicket();
        }else if(resId == R.id.tv_r_money_payments){ //现金还款
            mFragment.clickCashToMonty();
        }else if(resId == R.id.tv_r_buy_coupon_payments){ //买券还券
            mFragment.clickSellTicketToTicket();
        }else if(resId == R.id.tv_r_payments){ //现券还券
            mFragment.clickTheTicket();
        }else if (resId == R.id.tv_r_equity_trading){ //融资打新
            mFragment.clickEquityTrading();
        }else if(resId == R.id.tv_r_revocation){ //撤单
            mFragment.clickRevocation();
        }else if(resId == R.id.tv_r_more){ //更多
            mFragment.clickMore();
        }else if(resId == R.id.tv_r_contract){ //合约展期
            mFragment.clickContractExtension();
        }else if(resId == R.id.tv_r_change_pwd){ //修改密码
            mFragment.clickChangePwd();
        }
    }
}

