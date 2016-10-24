package com.thinkive.android.trade_bz.a_trans.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.thinkive.framework.compatible.TKFragmentActivity;
import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;
import com.thinkive.android.trade_bz.a_stock.fragment.AbsBaseFragment;
import com.thinkive.android.trade_bz.a_trans.activity.TransTradeBuyOrSaleActivityTrade;
import com.thinkive.android.trade_bz.a_trans.bean.TransSubHqBean;

/**
 * 定价申报行情详情
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/12/31
 */
public class TransHqThreeFragment extends AbsBaseFragment {
    private TKFragmentActivity mActivity;
    private TransHqThreeController mController;
    private TextView mTvCode;
    private TextView mTvName;
    private TextView mTvBs;
    private TextView mTvSubNum;
    private TextView mTvSubPrice;
    private TextView mTvYueNum;
    private TextView mTvXiNum;
    private TextView mTvSubTime;
    private TextView mTvSubStatus;
    private TextView mTvClickBuy;
    private TextView mTvClickSale;
    private TextView mTvClickHq;
    private TransSubHqBean mStockMsgBean;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_trans_hq_msg, null);
        findViews(rootView);
        initData();
        setListeners();
        initViews();
        return rootView;
    }

    @Override
    protected void findViews(View view) {
        mTvCode =(TextView)view.findViewById(R.id.tv_trans_msg_code);
        mTvName =(TextView)view.findViewById(R.id.tv_trans_msg_name);
        mTvBs =(TextView)view.findViewById(R.id.tv_trans_msg_bs);
        mTvSubNum =(TextView)view.findViewById(R.id.tv_trans_msg_sub_num);
        mTvSubPrice =(TextView)view.findViewById(R.id.tv_trans_msg_sub_price);
        mTvYueNum =(TextView)view.findViewById(R.id.tv_trans_msg_yue_num);
        mTvXiNum =(TextView)view.findViewById(R.id.tv_trans_msg_xi_num);
        mTvSubTime =(TextView)view.findViewById(R.id.tv_trans_msg_sub_time);
        mTvSubStatus =(TextView)view.findViewById(R.id.tv_trans_msg_sub_status);
        mTvClickBuy =(TextView)view.findViewById(R.id.tv_click_buy);
        mTvClickSale =(TextView)view.findViewById(R.id.tv_click_sale);
        mTvClickHq =(TextView)view.findViewById(R.id.tv_click_hq);
    }

    @Override
    protected void setListeners() {
        registerListener(AbsBaseController.ON_CLICK,mTvClickBuy,mController);
        registerListener(AbsBaseController.ON_CLICK,mTvClickSale,mController);
        registerListener(AbsBaseController.ON_CLICK,mTvClickHq,mController);
    }

    @Override
    protected void initData() {
        mActivity = (TKFragmentActivity) getActivity();
        mController = new TransHqThreeController(this);
        Bundle bundle = getArguments();
        if(bundle != null){
            mStockMsgBean = (TransSubHqBean)bundle.getSerializable("trans_msg");
            if(mStockMsgBean != null){
                mTvCode.setText(mStockMsgBean.getStock_code());
                mTvName.setText(mStockMsgBean.getStock_name());
                mTvBs.setText(mStockMsgBean.getBsflag_name());
                mTvSubNum.setText(mStockMsgBean.getOrderqty());
                mTvSubPrice.setText(mStockMsgBean.getOrderprice());
                mTvYueNum.setText(mStockMsgBean.getConfernum());
                mTvXiNum.setText(mStockMsgBean.getSeat());
                mTvSubTime.setText(mStockMsgBean.getOrdertime());
                mTvSubStatus.setText(mStockMsgBean.getStatus_name());
            }
        }
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void setTheme() {}

    /**
     * 点击买入
     */
    public void onClickBuy(){
        Intent intent = new Intent(mActivity,TransTradeBuyOrSaleActivityTrade.class);
        Bundle bundle = new Bundle();
        bundle.putString("buyOrSale","0");
        bundle.putString("stock_code",mStockMsgBean.getStock_code());
        bundle.putString("yu_num",mStockMsgBean.getConfernum());
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * 点击卖出
     */
    public void onClickSale(){
        Intent intent = new Intent(mActivity,TransTradeBuyOrSaleActivityTrade.class);
        Bundle bundle = new Bundle();
        bundle.putString("buyOrSale","1");
        bundle.putString("stock_code",mStockMsgBean.getStock_code());
        bundle.putString("yu_num",mStockMsgBean.getConfernum());
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * 点击行情
     */
    public void onClickHq(){

    }
}

class TransHqThreeController extends AbsBaseController implements View.OnClickListener {

    private TransHqThreeFragment mFragment;

    public TransHqThreeController(TransHqThreeFragment mFragment) {
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
        if (resId == R.id.tv_click_buy) {
            mFragment.onClickBuy();
        } else if (resId == R.id.tv_click_sale) {
            mFragment.onClickSale();
        } else if (resId == R.id.tv_click_hq) {
            mFragment.onClickHq();
        }
    }
}



