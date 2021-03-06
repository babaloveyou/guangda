package com.thinkive.android.trade_bz.dialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_hk.bean.HkRevocationBean;
import com.thinkive.android.trade_bz.a_hk.bll.HKRevocationServicesImpl;

/**
 * 港股通委托撤单操作确认对话框
 * @author 王志鸿
 * @company Thinkive
 * @date 2015/6/29
 */
public class HKRevocationConfirmDialog extends AbsTradeDialog {

    private TextView stockNameTextView;
    private TextView stockCodeTextView;
    private TextView entrustPriceTextView;
    private TextView entrustNumTextView;
    private TextView tradeCompleteNumTextView;
    private TextView buyOrSellTextView;

    /**
     * 调用方的业务类
     */
    private HKRevocationServicesImpl mServices;
    private HkRevocationBean mDataBean;
    private TextView mEntrustTimeTextView;

    public HKRevocationConfirmDialog(Context context, HKRevocationServicesImpl services) {
        super(context);
        mServices = services;
        initDialogLayout();
        setLayout();
    }

    /**
     *
     */
    public void initDialogLayout() {
        super.initDialogLayout();
        setTitleText(R.string.dialog_entrust_buy);
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_revocation, null);
        stockNameTextView = (TextView) view.findViewById(R.id.tv_pop_name);
        // 显示股票代码
        stockCodeTextView = (TextView) view.findViewById(R.id.tv_pop_code);
        //时间
        mEntrustTimeTextView = (TextView) view.findViewById(R.id.tv_pop_time);
        // 显示委托价格
        entrustPriceTextView = (TextView) view.findViewById(R.id.tv_pop_price);
        // 显示委托数量
        entrustNumTextView = (TextView) view.findViewById(R.id.tv_pop_entrust_number);
        setSubViewToParent(view);
    }

    public void setDataBean(HkRevocationBean dataBean) {
        mDataBean = dataBean;
    }

    /**
     *
     */
    public void setDataToViews() {

        stockNameTextView.setText(mDataBean.getStock_name());
        stockCodeTextView.setText(mDataBean.getStock_code());
        entrustPriceTextView.setText(mDataBean.getEntrust_price());
        entrustNumTextView.setText(mDataBean.getEntrust_amount());
        mEntrustTimeTextView.setText(mDataBean.getEntrust_time());
    }

    @Override
    void onClickCancel() {
        dismiss();
    }

    @Override
    void onClickOk() {
        mServices.execRevocation(mDataBean.getEntrust_no(), mDataBean.getExchange_type());
        dismiss();
    }
}
