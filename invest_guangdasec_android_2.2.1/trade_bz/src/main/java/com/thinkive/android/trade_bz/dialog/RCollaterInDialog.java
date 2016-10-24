package com.thinkive.android.trade_bz.dialog;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_rr.bean.RCollaterLinkBean;
import com.thinkive.android.trade_bz.a_rr.bll.RCollaterInServiceImpl;
import com.thinkive.android.trade_bz.utils.ToastUtils;

/**
 * 担保品转入对话框
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/4/21
 */
public class RCollaterInDialog extends AbsTradeDialog {

    private TextView mTvMax;
    private EditText mEdtNum;
    private RCollaterInServiceImpl mServices;
    private Context mContext;
    private RCollaterLinkBean mDataBean;

    public RCollaterInDialog(Context context, RCollaterInServiceImpl services) {
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
        setTitleText(R.string.r_layout_rb1);
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_r_collater_in, null);
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
            ToastUtils.toast(mContext, R.string.r_collater6);
        }else {
            mServices.requestTranferredResult(mDataBean,num);
            dismiss();
        }
    }
}
