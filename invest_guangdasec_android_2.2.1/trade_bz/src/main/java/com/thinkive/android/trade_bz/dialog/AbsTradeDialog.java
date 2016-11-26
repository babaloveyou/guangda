package com.thinkive.android.trade_bz.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.thinkive.android.trade_bz.R;

/**
 * 交易模块对话框基类，
 * @author 王志鸿
 * @company Thinkive
 * @date 2015/6/30
 */
public abstract class AbsTradeDialog extends Dialog {

    Context mContext;
    /**
     * 标题栏字体
     */
    private TextView mTvTitle;
    /**
     * 标题栏整个布局
     */
    private LinearLayout mLlTitle;
    /**
     * 确定按钮
     */
    private TextView mTvOk;
    /**
     * 取消按钮
     */
    private TextView mTvCancel;

    public AbsTradeDialog(Context context) {
            super(context, R.style.dialogstyle);
            mContext = context;
    }

    /**
     * 初始化对话框
     */
    public void initDialogLayout() {
        setContentView(R.layout.dialog_base_tarde);
        mTvTitle = (TextView) findViewById(R.id.tv_dialog_title);
        mLlTitle = (LinearLayout) findViewById(R.id.ll_dialog_title);
        mTvOk = (TextView) findViewById(R.id.tv_pop_ok);
        mTvCancel = (TextView) findViewById(R.id.tv_pop_cancel);
        // 设置对话框确认按钮事件监听
        mTvOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickOk();
            }
        });
        mTvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickCancel();
            }
        });
    }

    /**
     * 把个性布局添加到相应容器当中
     * @param subView  布局对象
     */
    void setSubViewToParent(View subView) {
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.fl_dialog_container);
        frameLayout.addView(subView);

    }

    /**
     * 设置弹出框的相对于屏幕的显示位置
     */
    public void setLayout() {
        Window dialogWindow = getWindow();
        dialogWindow.setGravity(Gravity.CENTER);
    }

    /**
     * 设置标题头栏是否显示
     * @param isVisiable
     */
    public void setTitleBarVisiable(boolean isVisiable) {
        if (isVisiable) {
            mLlTitle.setVisibility(View.VISIBLE);
        } else {
            mLlTitle.setVisibility(View.GONE);
        }
    }

    public void showBottomViewVisiable() {
     findViewById(R.id.view_bottom).setVisibility(View.VISIBLE);
    }
    /**
     * 设置取消按钮是否可见
     * @param isVisiable
     */
    public void setCancelBtnVisiable(boolean isVisiable) {
        if (isVisiable) {
            mTvCancel.setVisibility(View.VISIBLE);
        } else {
            mTvCancel.setVisibility(View.GONE);
        }
    }

    /**
     * 设置下方“确认”、“取消”两个按钮，以及对应分隔线的可见性
     * @param isVisiable 是否可见
     */
    public void setButtonBarVisiable(boolean isVisiable) {
        if (isVisiable) {
            findViewById(R.id.ll_dialog_button_bar).setVisibility(View.VISIBLE);
            findViewById(R.id.dialog_line1).setVisibility(View.VISIBLE);
        } else {
            findViewById(R.id.ll_dialog_button_bar).setVisibility(View.GONE);
            findViewById(R.id.dialog_line1).setVisibility(View.GONE);
        }
    }

    /**
     * 给标题栏设值
     * @param text
     */
    public void setTitleText(String text) {
        mTvTitle.setText(text);
    }

    /**
     *  给标题栏设值
     * @param resIdText
     */
    public void setTitleText(int resIdText) {
        setTitleText(mContext.getResources().getString(resIdText));
    }

    /**
     * OK按钮单击事件，等待子类实现，但子类有权不实现
     */
    void onClickOk() {

    }

    /**
     * 点击取消
     */
    void onClickCancel() {
        dismiss();
    }
}
