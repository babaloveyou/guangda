package com.thinkive.android.trade_bz.dialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.bean.RevocationBean;
import com.thinkive.android.trade_bz.a_stock.bll.RevocationServicesImpl;

/**
 * 普通交易撤单
 * @author 王志鸿
 * @company Thinkive
 * @date 2015/6/29
 */
public class ARevocationConfirmDialog extends AbsTradeDialog {

    private TextView stockNameTextView;
    private TextView stockCodeTextView;
    private TextView entrustPriceTextView;
    private TextView entrustNumTextView;
    private TextView entrustTimeTextView;
    /**
     * 调用方的业务类
     */
    private RevocationServicesImpl mServices;
    private RevocationBean mDataBean;

    public ARevocationConfirmDialog(Context context, RevocationServicesImpl services) {
        super(context);
        mServices = services;
        initDialogLayout();
        setLayout();
    }

    public void initDialogLayout() {
        super.initDialogLayout();
        setTitleText(R.string.dialog_entrust_buy);
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_revocation, null);
        // 显示股票名称
        stockNameTextView = (TextView) view.findViewById(R.id.tv_pop_name);
        // 显示股票代码
        stockCodeTextView = (TextView) view.findViewById(R.id.tv_pop_code);
        //时间
        entrustTimeTextView = (TextView) view.findViewById(R.id.tv_pop_time);
        // 显示委托价格
        entrustPriceTextView = (TextView) view.findViewById(R.id.tv_pop_price);
        // 显示委托数量
        entrustNumTextView = (TextView) view.findViewById(R.id.tv_pop_entrust_number);
        setSubViewToParent(view);
    }

    public void setDataBean(RevocationBean dataBean) {
        mDataBean = dataBean;
    }

    public void setDataToViews() {
        stockNameTextView.setText(mDataBean.getStock_name());
        stockCodeTextView.setText(mDataBean.getStock_code());
        entrustPriceTextView.setText(mDataBean.getEntrust_price());
        entrustNumTextView.setText(mDataBean.getEntrust_amount());
        entrustTimeTextView.setText(mDataBean.getEntrust_time());
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
