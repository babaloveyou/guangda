package com.thinkive.android.trade_bz.dialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_rr.bean.RCollaterRevocationBean;
import com.thinkive.android.trade_bz.a_rr.bll.RCollaterRevocationServiceImpl;

/**
 * 担保品撤单对话框
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/4/21
 */
public class RCollaterRevocationDialog extends AbsTradeDialog {

    private TextView stockNameTextView;
    private TextView stockCodeTextView;
    private TextView entrustPriceTextView;
    private TextView entrustNumTextView;
    private TextView tradeNum;
    private TextView buyOrSellBs;
    private RCollaterRevocationServiceImpl mServices;
    private Context mContext;
    private RCollaterRevocationBean mDataBean;

    public RCollaterRevocationDialog(Context context, RCollaterRevocationServiceImpl services) {
        super(context);
        mContext = context;
        mServices = services;
        initDialogLayout();
        setLayout();
    }

    /**
     *初始化
     */
    public void initDialogLayout() {
        super.initDialogLayout();
        setTitleText(R.string.dialog_entrust_buy);
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_revocation, null);
        // 显示股票名称
        stockNameTextView = (TextView) view.findViewById(R.id.tv_pop_name);
        // 显示股票代码
        stockCodeTextView = (TextView) view.findViewById(R.id.tv_pop_code);
        // 显示委托价格
        entrustPriceTextView = (TextView) view.findViewById(R.id.tv_pop_price);
        // 显示委托数量
        entrustNumTextView = (TextView) view.findViewById(R.id.tv_pop_entrust_number);
        //成交数量
        tradeNum = (TextView) view.findViewById(R.id.tv_pop_trade);
        //买卖标志
        buyOrSellBs = (TextView) view.findViewById(R.id.tv_pop_buy);
        setSubViewToParent(view);
    }

    /**
     * 将数据设置到按钮上
     */
    public void setDataToViews() {
        stockNameTextView.setText(mDataBean.getStock_name());
        stockCodeTextView.setText(mDataBean.getStock_code());
        entrustPriceTextView.setText(mDataBean.getEntrust_price());
        entrustNumTextView.setText(mDataBean.getEntrust_amount());
        tradeNum.setText(mDataBean.getBusiness_amount());
        buyOrSellBs.setText(mDataBean.getEntrust_type_name());
    }
    /**
     * 供外界传值
     * @param bean
     */
    public void setDataBean(RCollaterRevocationBean bean){
        mDataBean = bean;
    }
    @Override
    void onClickCancel() {
        dismiss();
    }

    @Override
    void onClickOk() {
        mServices.requestRavocationResult(mDataBean.getEntrust_no());
        dismiss();
    }
}
