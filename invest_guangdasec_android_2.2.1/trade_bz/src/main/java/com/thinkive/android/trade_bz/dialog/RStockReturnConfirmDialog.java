package com.thinkive.android.trade_bz.dialog;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_rr.bean.RStockToStockLinkBean;
import com.thinkive.android.trade_bz.a_rr.bll.RStockReturnOrderStockServiceImpl;

/**
 * Created by Administrator on 2016/12/7.
 */
public class RStockReturnConfirmDialog extends AbsTradeDialog {


    /**
     * 调用方的业务类
     */
    private RStockReturnOrderStockServiceImpl mServices;
    private RStockToStockLinkBean mDataBean;
    private String mNum;
    private TextView mTv1;
    private TextView mTv2;
    private TextView mTv3;

    public RStockReturnConfirmDialog(Activity context, RStockReturnOrderStockServiceImpl services) {
        super(context);
        mServices = services;
        initDialogLayout();
        setLayout();
    }
    public void initDialogLayout() {
        super.initDialogLayout();
        setTitleText("现券还券");
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_crash_return_stock, null);
        mTv1 = (TextView) view.findViewById(R.id.tv1);
        mTv2 = (TextView) view.findViewById(R.id.tv2);
        mTv3 = (TextView) view.findViewById(R.id.tv3);
        setSubViewToParent(view);
    }

    public void setDataBean(RStockToStockLinkBean dataBean) {
        mDataBean = dataBean;
    }
    public void setDataToViews() {
        mTv1.setText(mDataBean.getStock_name());
        mTv2.setText(mDataBean.getStock_code());
        mTv3.setText(mNum);
    }

    @Override
    void onClickCancel() {
        dismiss();
    }

    @Override
    void onClickOk() {
        mServices.requestCommit(mDataBean,mNum);
        dismiss();
    }

    public void setEntrustNum(String balance) {
        mNum = balance;
    }
}


