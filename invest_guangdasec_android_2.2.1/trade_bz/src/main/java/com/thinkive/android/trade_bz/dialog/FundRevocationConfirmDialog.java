package com.thinkive.android.trade_bz.dialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_out.bean.FundRevocationBean;
import com.thinkive.android.trade_bz.a_out.bll.FundRevocationServicesImpl;

/**
 * 基金撤单确认对话框
 * Announcements：
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/11/4
 */
public class FundRevocationConfirmDialog extends AbsTradeDialog {

    private TextView stockNameTextView;
    private TextView stockCodeTextView;
    private TextView entrustPriceTextView;
    private TextView entrustNumTextView;
//    private TextView buyOrSellTextView;

    /**
     * 调用方的业务类
     */
    private FundRevocationServicesImpl mServices;
    private FundRevocationBean mDataBean;

    public FundRevocationConfirmDialog(Context context, FundRevocationServicesImpl services) {
        super(context);
        mServices = services;
        initDialogLayout();
        setLayout();
    }

    /**
     * 初始化撤单列表数据
     */
    public void initDialogLayout() {
        super.initDialogLayout();
        setTitleText(R.string.dialog_entrust_buy);
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_revocation, null);
        // 显示股票名称
        stockNameTextView = (TextView) view.findViewById(R.id.tv_pop_name);
        // 显示股票代码
        stockCodeTextView = (TextView) view.findViewById(R.id.tv_pop_code);
        // 显示买卖方向
//        buyOrSellTextView = (TextView) view.findViewById(R.id.tv_pop_buy);
        // 显示委托价格
        entrustPriceTextView = (TextView) view.findViewById(R.id.tv_pop_price);
        // 显示委托数量
        entrustNumTextView = (TextView) view.findViewById(R.id.tv_pop_entrust_number);
        setSubViewToParent(view);
    }

    public void setDataBean(FundRevocationBean dataBean) {
        mDataBean = dataBean;
    }

    /**
     * 设置对话框需要的数据
     */
    public void setDataToViews() {
        stockNameTextView.setText(mDataBean.getFund_name());
        stockCodeTextView.setText(mDataBean.getFund_code());
        entrustPriceTextView.setText(mDataBean.getDeal_balance());
        entrustNumTextView.setText(mDataBean.getShares());
//        buyOrSellTextView.setText(mDataBean.getBusiness_name());
    }

    @Override
    void onClickCancel() {
        dismiss();
    }

    @Override
    void onClickOk() {
        mServices.requestExecRevocation(mDataBean.getFund_code(), mDataBean.getEntrust_date(),
                mDataBean.getEntrust_no(),mDataBean.getFund_company());
        dismiss();
    }
}
