package com.thinkive.android.trade_bz.dialog;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_rr.bean.RCollaterLinkBean;
import com.thinkive.android.trade_bz.a_rr.bll.RCollaterOutServiceImpl;
import com.thinkive.android.trade_bz.a_stock.bean.MyStoreStockBean;
import com.thinkive.android.trade_bz.utils.ToastUtils;

/**
 * 担保品转出对话框
 *
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/4/21
 */
public class RCollaterOutDialog extends AbsTradeDialog {


    private RCollaterOutServiceImpl mServices;
    private Context mContext;
    private RCollaterLinkBean mDataBean;
    private TextView mNameAndCodeTv;
    private EditText mAmountEdt;
    private TextView mUnitTv;
    private TextView mOutTv;
    private TextView mCancleTv;
    private MyStoreStockBean mNameAndCodeBean;

    public RCollaterOutDialog(Context context, RCollaterOutServiceImpl  services) {
        super(context);
        mContext = context;
        mServices = services;
        initDialogLayout();
        setListener();
        setLayout();
    }

    private void setListener() {
        mOutTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num = mAmountEdt.getText().toString().trim();
                if (TextUtils.isEmpty(num) && !num.equals("0")) {
                    ToastUtils.toast(mContext, R.string.r_collater6);
                } else {
                    mServices.requestTranferredResult(mDataBean,mNameAndCodeBean, num);
                    dismiss();
                }
            }
        });
        mCancleTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        mAmountEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(mAmountEdt.getText())) {
                    mUnitTv.setVisibility(View.GONE);
                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) mAmountEdt.getLayoutParams();
                    layoutParams.setMargins(0, 0, 0, 0);
                    mAmountEdt.setLayoutParams(layoutParams);
                    mAmountEdt.setHint("最大可转出"+mDataBean.getEnable_amount()+"股");
                } else {
                    mUnitTv.setVisibility(View.VISIBLE);
                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) mAmountEdt.getLayoutParams();
                    layoutParams.setMargins(27, 0, 0, 0);
                    mAmountEdt.setLayoutParams(layoutParams);
                }
            }
        });
    }

    /**
     * 初始化
     */
    public void initDialogLayout() {
        super.initDialogLayout();
        setTitleBarVisiable(false);
        setButtonBarVisiable(false);
        showBottomViewVisiable();
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_r_collater_out, null);
        mNameAndCodeTv = (TextView) view.findViewById(R.id.tv_name_code);
        mAmountEdt = (EditText) view.findViewById(R.id.edt_amount);
        mUnitTv = (TextView) view.findViewById(R.id.tv_unit);
        mOutTv = (TextView) view.findViewById(R.id.tv_out);
        mCancleTv = (TextView) view.findViewById(R.id.tv_cancle);
        setSubViewToParent(view);
    }

    /**
     * 供外界传值
     *  @param bean
     * @param stockBean
     */
    public void setDataBean(RCollaterLinkBean bean, MyStoreStockBean stockBean) {
        mNameAndCodeBean = stockBean;
        mDataBean = bean;
        mAmountEdt.setHint("最大可转出"+mDataBean.getEnable_amount()+"股");
        mNameAndCodeTv.setText("转出担保品证券:" +mNameAndCodeBean.getStock_name()+"("+ mNameAndCodeBean.getStock_code()+")");
    }

}
