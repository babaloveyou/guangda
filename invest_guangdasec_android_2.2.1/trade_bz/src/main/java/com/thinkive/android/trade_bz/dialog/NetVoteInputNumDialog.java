package com.thinkive.android.trade_bz.dialog;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_net.bean.NetVoteEntrustBean;
import com.thinkive.android.trade_bz.utils.ToastUtils;

/**
 * 网络投票-累计议案投票，输入投票数量
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/8/18
 */
public class NetVoteInputNumDialog extends AbsTradeDialog {

    private EditText mEdtInputNum;
    private NetVoteEntrustBean mDataBean;

    public NetVoteInputNumDialog(Context context) {
        super(context);
        initDialogLayout();
        setLayout();
    }

    /**
     *初始化
     */
    public void initDialogLayout() {
        super.initDialogLayout();
        setTitleText(R.string.net_vote35);
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_net_vote_input_num, null);
        mEdtInputNum =(EditText)view.findViewById(R.id.edt_new_num);
        setSubViewToParent(view);
    }

    /**
     * 供外界设置数据
     */
    public void setMaxNum(NetVoteEntrustBean bean){
        mDataBean = bean;
    }
    @Override
    void onClickCancel() {
        dismiss();
    }

    @Override
    void onClickOk() {
        String num = mEdtInputNum.getText().toString().trim();
        if(TextUtils.isEmpty(num)){
            ToastUtils.toast(mContext,mContext.getResources().getString(R.string.net_vote37));
        }else{
            mDataBean.setVote_number(num);
            dismiss();
        }
    }
}
