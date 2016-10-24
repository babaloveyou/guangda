package com.thinkive.android.trade_bz.dialog;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.thinkive.android.trade_bz.R;

/**
 * 文本+确定+取消按钮的对话框
 * 提供“OK”按钮的点击事件监听接口，单击OK后都会关闭本对话框，取消按钮的点击事件就是关闭本对话框。
 * @author 王志鸿
 * @company Thinkive
 * @date 2015/6/29
 */
public class MessageOkCancelDialog extends AbsTradeDialog {

    /**
     * 提示信息文本
     */
    private TextView mTvMsg;
    /**
     * “OK”按钮点击事件监听
     */
    private IOkListener mOkListener;

    /**
     * 构造方法，给成员属性传值，初始化并设置本对话框布局
     * @param context 外部调用环境
     * @param okListener OK按钮外部单击事件监听，外部可用它设置OK按钮的单击事件。单击确认按钮后，对话框的关闭操作都会执行。
     */
    public MessageOkCancelDialog(Context context, @Nullable IOkListener okListener) {
        super(context);
        initDialogLayout();
        mOkListener = okListener;
        setLayout();
    }

    /**
     * 初始化对话框界面
     */
    @Override
    public void initDialogLayout() {
        super.initDialogLayout();
        // 设置顶部标题栏不显示
        setTitleBarVisiable(false);
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_msg_ok, null);
        // 提示框显示的信息
        mTvMsg = (TextView)view.findViewById(R.id.tv_pop_msg);
        setSubViewToParent(view);
    }

    /**
     * 设置对话框显示内容
     * @param msg 对话框显示的内容字符串
     */
    public void setMsgText(String msg) {
        mTvMsg.setText(msg);
    }

    /**
     * 设置对话框显示内容
     * @param msgId 对话框显示的内容字符串的资源Id
     */
    public void setMsgText(int msgId) {
        setMsgText(mContext.getResources().getString(msgId));
    }

    @Override
    void onClickOk() {
        dismiss();
        if (mOkListener != null) {
            mOkListener.onClickOk();
        }
    }

    public interface IOkListener {
        void onClickOk();
    }
}
