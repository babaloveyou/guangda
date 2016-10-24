package com.thinkive.android.trade_bz.dialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_option.bean.OptionRevocationBean;
import com.thinkive.android.trade_bz.a_option.bll.OptionRevocationServicesImpl;

/**
 * 个股期权撤单确认
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/6/15
 */
public class OptionRevocationDialog extends AbsTradeDialog {

    private TextView stockNameTextView;
    private TextView stockCodeTextView;
    private TextView entrustPriceTextView;
    private TextView entrustNumTextView;
    private TextView tradeCompleteNumTextView;
    private TextView buyOrSellTextView;

    /**
     * 调用方的业务类
     */
    private OptionRevocationServicesImpl mServices;
    private OptionRevocationBean mDataBean;

    public OptionRevocationDialog(Context context, OptionRevocationServicesImpl services) {
        super(context);
        mServices = services;
        initDialogLayout();
        setLayout();
    }

    /**
     * 初始化
     */
    public void initDialogLayout() {
        super.initDialogLayout();
        setTitleText(R.string.dialog_entrust_buy);
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_revocation, null);
        // 显示股票名称
        stockNameTextView = (TextView)view.findViewById(R.id.tv_pop_name);
        // 显示股票代码
        stockCodeTextView = (TextView)view.findViewById(R.id.tv_pop_code);
        // 显示委托价格
        entrustPriceTextView = (TextView)view.findViewById(R.id.tv_pop_price);
        // 显示委托数量
        entrustNumTextView = (TextView)view.findViewById(R.id.tv_pop_entrust_number);
        //
        tradeCompleteNumTextView = (TextView)view.findViewById(R.id.tv_pop_trade);
        //
        buyOrSellTextView=(TextView)view.findViewById(R.id.tv_pop_buy);
        setSubViewToParent(view);
    }

    public void setDataBean(OptionRevocationBean dataBean) {
        mDataBean = dataBean;
    }

    /**
     *
     */
    public void setDataToViews() {
        stockNameTextView.setText(mDataBean.getOption_name());
        stockCodeTextView.setText(mDataBean.getOption_code());
        entrustPriceTextView.setText(mDataBean.getOpt_entrust_price());
        entrustNumTextView.setText(mDataBean.getEntrust_amount());
        tradeCompleteNumTextView.setText(mDataBean.getBusiness_amount());
        buyOrSellTextView.setText(mDataBean.getEntrust_type_name());
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
