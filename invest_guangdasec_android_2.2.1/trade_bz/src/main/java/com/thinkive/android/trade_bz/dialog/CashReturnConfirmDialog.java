package com.thinkive.android.trade_bz.dialog;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_rr.bean.RStockToStockLinkBean;
import com.thinkive.android.trade_bz.a_rr.bll.RCashReturnServiceImpl;
import com.thinkive.android.trade_bz.others.tools.TradeLoginManager;

/**
 * Created by Administrator on 2016/12/7.
 */
public class CashReturnConfirmDialog extends AbsTradeDialog {


    /**
     * 调用方的业务类
     */
    private RCashReturnServiceImpl mServices;
    private RStockToStockLinkBean mDataBean;
    private String mBalance;
    private TextView mTv1;
    private TextView mTv2;
    private TextView mTv3;
    private TextView mTv4;

    public CashReturnConfirmDialog(Activity context, RCashReturnServiceImpl services) {
        super(context);
        mServices = services;
        initDialogLayout();
        setLayout();
    }
    public void initDialogLayout() {
        super.initDialogLayout();
        setTitleText("现金还款");
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_crash_return, null);
        mTv1 = (TextView) view.findViewById(R.id.tv1);
        mTv2 = (TextView) view.findViewById(R.id.tv2);
        mTv3 = (TextView) view.findViewById(R.id.tv3);
        mTv4 = (TextView) view.findViewById(R.id.tv4);
        setSubViewToParent(view);
    }

    public void setDataBean(RStockToStockLinkBean dataBean) {
        mDataBean = dataBean;
    }
    public void setDataToViews() {
        mTv1.setText(TradeLoginManager.sCreditUserInfo.getFund_account());
        mTv2.setText("现金还款");
        mTv3.setText(mDataBean.getReal_compact_balance());
        mTv4.setText(mBalance);
    }

    @Override
    void onClickCancel() {
        dismiss();
    }

    @Override
    void onClickOk() {
        mServices.requrstCommit(mBalance);
        dismiss();
    }

    public void setBalance(String balance) {
        mBalance = balance;
    }
}

