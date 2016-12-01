package com.thinkive.android.trade_bz.dialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_new.bean.NewStockBean;
import com.thinkive.android.trade_bz.a_new.bll.NewSubscribeServiceImpl;

/**
 * 新股申购弹出确认框
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/9/2
 */
public class NewStockConfirmDialog extends AbsTradeDialog {
    /**
     * 股票名称
     */
    private TextView stockNameTextView;
    /**
     * 股票代码
     */
    private TextView stockCodeTextView;
    /**
     * 委托价格
     */
    private TextView entrustPriceTextView;
    /**
     * 委托数量
     */
    private TextView entrustNumTextView;
    /**
     * 调用方的业务类
     */
    private NewSubscribeServiceImpl mService;
    /**
     * 委托标志
     */
    private NewStockBean mNewStockBean;

    public NewStockConfirmDialog(Context context, NewSubscribeServiceImpl service) {
        super(context);
        mService = service;
        initDialogLayout();
        setLayout();
    }

    /**
     * 初始化对话框布局
     */
    public void initDialogLayout() {
        super.initDialogLayout();
        setTitleText(R.string.dialog_entrust_buy);
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_trade_comfirm, null);
//        TextView entrust = (TextView) view.findViewById(R.id.tv_pop_buy);
//        entrust.setText(mContext.getResources().getString(R.string.home_new_stock));
        // 显示股票名称
        stockNameTextView = (TextView)view.findViewById(R.id.tv_pop_name);
        // 显示股票代码
        stockCodeTextView = (TextView)view.findViewById(R.id.tv_pop_code);
        // 显示委托价格
        entrustPriceTextView = (TextView)view.findViewById(R.id.tv_pop_price);
        // 显示委托数量
        entrustNumTextView = (TextView)view.findViewById(R.id.tv_pop_entrust_number);
        setSubViewToParent(view);
    }

    /**
     * 设置数据到对话框的控件中
     */
    public void setDataToViews(NewStockBean bean, String entrustNum) {
        mNewStockBean = bean;
        stockNameTextView.setText(bean.getStock_name());
        stockCodeTextView.setText(bean.getStock_code());
        entrustPriceTextView.setText(bean.getIssue_price());
        entrustNumTextView.setText(entrustNum);
    }

    @Override
    void onClickCancel() {
        dismiss();
    }

    @Override
    void onClickOk() {
        mService.requestNewSubscribe(mNewStockBean,entrustNumTextView.getText().toString());
        dismiss();
    }
}
