package com.thinkive.android.trade_bz.dialog;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_rr.bean.RCollaterLinkBean;
import com.thinkive.android.trade_bz.a_rr.bll.RCollaterOutServiceImpl;
import com.thinkive.android.trade_bz.utils.ToastUtils;

/**
 * 担保品转出对话框
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/4/21
 */
public class RCollaterOutDialog extends AbsTradeDialog {

    private TextView mTvMax;
    private EditText mEdtNum;
    private RCollaterOutServiceImpl mServices;
    private Context mContext;
    private RCollaterLinkBean mDataBean;

    public RCollaterOutDialog(Context context, RCollaterOutServiceImpl services) {
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
        setTitleText(R.string.r_layout_rb3);
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_r_collater_out, null);
        mTvMax = (TextView) view.findViewById(R.id.tv_collater_out_max);
        mEdtNum = (EditText) view.findViewById(R.id.edt_collater_out_num);
        setSubViewToParent(view);
    }

    /**
     * 供外界传值
     * @param bean
     */
    public void setDataBean(RCollaterLinkBean bean){
        mDataBean = bean;
        mTvMax.setText(mDataBean.getEnable_amount());
    }
    @Override
    void onClickCancel() {
        dismiss();
    }

    @Override
    void onClickOk() {
        String num = mEdtNum.getText().toString().trim();
        if(TextUtils.isEmpty(num) && !num.equals("0")){
            ToastUtils.toast(mContext, R.string.r_trans_num);
        }else {
            mServices.requestTranferredResult(mDataBean, num);
//            mServices.requestTranferredResult(mDataBean.getStock_Code(),
//                    mDataBean.getLast_price(),mDataBean.getExchange_type(),
//                    mDataBean.getSeat_no(), mDataBean.getSeat_no_crdt(), num);
            dismiss();
        }
    }
}
