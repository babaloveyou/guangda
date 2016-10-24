package com.thinkive.android.trade_bz.a_rr.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.thinkive.framework.compatible.ListenerControllerAdapter;
import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_rr.activity.RCollaterTransActivity;
import com.thinkive.android.trade_bz.a_rr.activity.RCollaterSelectActivity;
import com.thinkive.android.trade_bz.a_rr.activity.RSecuritiesMarginActivity;
import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;
import com.thinkive.android.trade_bz.a_stock.fragment.AbsBaseFragment;
import com.thinkive.android.trade_bz.utils.TradeUtils;

/**
 * 融资融券--划转
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/7/29
 */
public class RTransferFragment extends AbsBaseFragment {
    /**
     * 融资融券的Activity
     */
    private RSecuritiesMarginActivity mActivity;
    /**
     * 该类控制器
     */
    private RTransferViewController mController;
    /**
     * 业务类
     */
//    private RTransMainServiceImpl mServices;
    /**
     * 担保品转入
     */
    private TextView mTvCollateralIn;
    /**
     * 担保品转出
     */
    private TextView mTvCollateralOut;
    /**
     * 非交易过户查询
     */
    private TextView mTvCollateralSelect;
    /**
     * 划转撤单
     */
    private TextView mTvCollateralRevocation;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_r_transferred, null);
        findViews(rootView);
        initData();
        setListeners();
        initViews();
        return rootView;
    }

    @Override
    protected void findViews(View view) {
        mTvCollateralIn=(TextView)view.findViewById(R.id.tv_collateral_security_in);
        mTvCollateralOut=(TextView)view.findViewById(R.id.tv_collateral_security_out);
        mTvCollateralSelect=(TextView)view.findViewById(R.id.tv_collateral_security_lookout);
        mTvCollateralRevocation=(TextView)view.findViewById(R.id.tv_collateral_security_revocation);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
       //当fragment可见时执行
        }
    }

    @Override
    protected void setListeners() {
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvCollateralIn, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvCollateralOut, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvCollateralSelect, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvCollateralRevocation, mController);
    }

    @Override
    protected void initData() {
        mActivity=(RSecuritiesMarginActivity)getActivity();
        mController=new RTransferViewController(this);
//        mServices = new RTransMainServiceImpl(this);
    }

    @Override
    protected void initViews() {
        setTheme();
    }

    @Override
    protected void setTheme() {

    }

    /**
     * 金正win 不做普通交易单独登陆
     * 点击按钮，触发监听
     * 1.如果已经在融资融券中单独登录过普通交易，则不需要弹框
     * 2.判断已登录的普通交易客户号与已登录的信用交易客户号是否一致
     * 3.如果是一致的，拿到普通交易用户信息
     * 4.如果不是一致的，说明不是同一个用户所登录的，那么需要用信用客户号登录普通交易
     * 注：某些柜台划转时不需要登陆，此处是按照登录的情况作处理
     */
    public void onClickView(View clickId){
//        String normalCost = TradeLoginManager.sNormalUserInfo.getCust_code();
//        String creditCost = TradeLoginManager.sCreditUserInfo.getCust_code();
//        //如果已经在两融单独登录过普通交易
//        if(TradeLoginManager.sIsNormalLogin_in_credit){
//            onClick(clickId);//进入页面
//        }else{
//            //如果普通交易登录了，并且客户号一致时
//            if (TradeFlags.check(TradeFlags.FLAG_TRADE_YES) && normalCost.equals(creditCost)) {
//                TradeLoginManager.sNormalUserInfo_in_credit = TradeLoginManager.sNormalUserInfo;
//                onClick(clickId);//进入页面
//            } else {
//                //使用信用客户号登录普通交易
//                RTransLoginNormalDialog dialog = new RTransLoginNormalDialog(mActivity, mServices);
//                dialog.setDataToViews(TradeLoginManager.sCreditUserInfo.getCust_code());
//                dialog.show();
//            }
//        }
        onClick(clickId);
    }

    /**
     * 点击跳转至相应页面
     * @param clickId
     */
    private void onClick(View clickId){
        int resId = clickId.getId();
        if (TradeUtils.isFastClick()) {
            return;
        }
        if (resId == R.id.tv_collateral_security_in) { // 担保品转入
            clickTvCollateralIn();
        } else if (resId == R.id.tv_collateral_security_out) { //担保品转出
            clickTvCollateralOut();
        } else if (resId == R.id.tv_collateral_security_lookout) {//担保品划转查询
            clickTvCollateralSelect();
        } else if (resId == R.id.tv_collateral_security_revocation) {//划转撤单
            clickTvCollateralRevocation();
        }
    }

    /**
     * 点击担保品转入
     */
    public void clickTvCollateralIn(){
        Intent intent = new Intent(mActivity, RCollaterTransActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("ViewPagerFragmentPos",0);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * 点击担保品转出
     */
    public void clickTvCollateralOut(){
        Intent intent = new Intent(mActivity, RCollaterTransActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("ViewPagerFragmentPos",1);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * 担保品划转查询
     */
    public void clickTvCollateralSelect(){
        startActivity(new Intent(mActivity, RCollaterSelectActivity.class));
    }

    /**
     * 点击划转撤单
     */
    public void clickTvCollateralRevocation(){
        Intent intent = new Intent(mActivity, RCollaterTransActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("ViewPagerFragmentPos",2);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}

/**
 * 控制类
 */
class RTransferViewController extends AbsBaseController implements View.OnClickListener {

    private RTransferFragment mFragment;

    public RTransferViewController(RTransferFragment mFragment) {
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
        mFragment.onClickView(v);
    }
}
