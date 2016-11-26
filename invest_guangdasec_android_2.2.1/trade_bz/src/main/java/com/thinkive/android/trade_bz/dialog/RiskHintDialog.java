package com.thinkive.android.trade_bz.dialog;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.fragment.BuyOrSellFragment;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/24.
 */
public class RiskHintDialog extends AbsTradeDialog {
    private Fragment mFragment;
    private TextView mContentTv;
    private ArrayList<String> mDataList = null;
    private int index=-1;
    public RiskHintDialog(Context context, Fragment fragment, ArrayList<String> list, int i) {
        super(context);
        mFragment = fragment;
        mDataList = list;
        index = i;
        initDialogLayout();
        setLayout();
    }

    /**
     * 初始化对话框布局
     */
    public void initDialogLayout() {
        super.initDialogLayout();
        setTitleText("风险提示");
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_risk_hint, null);
        mContentTv = (TextView) view.findViewById(R.id.tv_content);
        mContentTv.setText(mDataList.get(index));
        setSubViewToParent(view);
    }


    @Override
    void onClickCancel() {
        dismiss();
    }

    @Override
    void onClickOk() {
        if (mFragment instanceof BuyOrSellFragment) {
            BuyOrSellFragment fragment = (BuyOrSellFragment) mFragment;
            if (index < mDataList.size()) {
                fragment.onDialogClick(mDataList, index+1);
            }
        }
        dismiss();
    }
}
