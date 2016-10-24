package com.thinkive.android.trade_bz.dialog;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_new.bean.NewOneKeyBean;
import com.thinkive.android.trade_bz.utils.ToastUtils;

/**
 * 一键申购时，输入申购数量
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/6/22
 */
public class NewStockInputNumDialog extends AbsTradeDialog {

    private EditText mEdtInputNum;
    private TextView mTvMax;
    private NewOneKeyBean mDataBean;

    public NewStockInputNumDialog(Context context) {
        super(context);
        initDialogLayout();
        setLayout();
    }

    /**
     *初始化
     */
    public void initDialogLayout() {
        super.initDialogLayout();
        setTitleText(R.string.new_stock12);
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_new_stock_input_num, null);
        mTvMax = (TextView)view.findViewById(R.id.tv_new_max);
        mEdtInputNum =(EditText)view.findViewById(R.id.edt_new_num);
        setSubViewToParent(view);
    }

    /**
     * 供外界设置数据
     */
    public void setMaxNum(NewOneKeyBean bean){
        mDataBean = bean;
        mTvMax.setText(mDataBean.getMaxamount());
    }
    @Override
    void onClickCancel() {
        dismiss();
    }

    @Override
    void onClickOk() {
        String num = mEdtInputNum.getText().toString().trim();
        if(TextUtils.isEmpty(num)){
            ToastUtils.toast(mContext,mContext.getResources().getString(R.string.new_stock13));
        }else{
            mDataBean.setInput_num(num);
            dismiss();
        }
    }
}
