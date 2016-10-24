package com.thinkive.android.trade_bz.dialog;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.thinkive.framework.CoreApplication;
import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_new.bean.NewNumberWinningBean;
import com.thinkive.android.trade_bz.a_new.bll.NewWinningNumServicesImpl;
import com.thinkive.android.trade_bz.utils.ToastUtils;

/**
 * 设置中签缴款顺序
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/6/29
 */
public class NewStockWiningDialog extends AbsTradeDialog {

    private TextView mTvStockName;
    private TextView mTvStockCode;
    private TextView mTvWiningNum;
    private TextView mTvCurSort;
    private EditText mEdtChangeSort;

    /**
     * 调用方的业务类
     */
    private NewWinningNumServicesImpl mServices;
    private NewNumberWinningBean mDataBean;
    private Context mContext;

    public NewStockWiningDialog(Context context, NewWinningNumServicesImpl services) {
        super(context);
        mContext = context;
        mServices = services;
        initDialogLayout();
        setLayout();
    }

    /**
     *
     */
    public void initDialogLayout() {
        super.initDialogLayout();
        setTitleText(R.string.number_winning_text);
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_new_stock_wining, null);
        // 显示股票名称
        mTvStockName = (TextView)view.findViewById(R.id.tv_win_name);
        // 显示股票代码
        mTvStockCode = (TextView)view.findViewById(R.id.tv_win_code);
        // 中签数量
        mTvWiningNum = (TextView)view.findViewById(R.id.tv_win_count);
        // 当前排序
        mTvCurSort = (TextView)view.findViewById(R.id.tv_win_cur_sort);
        // 修改排序
        mEdtChangeSort = (EditText)view.findViewById(R.id.edt_win_change_sort);

        setSubViewToParent(view);
    }

    public void setDataBean(NewNumberWinningBean dataBean) {
        mDataBean = dataBean;
    }

    /**
     * 设置数据到对应的View
     */
    public void setDataToViews() {
        mTvStockName.setText(mDataBean.getStock_name());
        mTvStockCode.setText(mDataBean.getStock_code());
        mTvWiningNum.setText(mDataBean.getMatchqty());
        mTvCurSort.setText(mDataBean.getClearsno());
    }

    @Override
    void onClickCancel() {
        dismiss();
    }

    @Override
    void onClickOk() {
        String str = mEdtChangeSort.getText().toString().trim();
        if(TextUtils.isEmpty(str) || str.equals("0")){
            ToastUtils.toast(mContext, CoreApplication.getInstance().getString(R.string.dialog_account_win));
        }else{
            mServices.execWiningSet(mDataBean.getExchange_type(),
                    mDataBean.getStock_account(),str,mDataBean.getStock_code());
            dismiss();
        }
    }
}
