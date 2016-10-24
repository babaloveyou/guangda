package com.thinkive.android.trade_bz.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;

/**
 * 加载状态悬浮框
 */

public class LoadingDialog extends Dialog {
    private TextView tv;
    private String text;
    private Context mContext;
    public LoadingDialog(Context context) {
        super(context, R.style.loadingDialogStyle);
        mContext = context;
    }

    private LoadingDialog(Context context, int theme) {
        super(context, theme);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_loading);
        tv = (TextView)this.findViewById(R.id.tv);
        tv.setText(text);
        LinearLayout linearLayout = (LinearLayout)this.findViewById(R.id.LinearLayout);
        linearLayout.getBackground().setAlpha(210);
        setCancelable(false);
    }

    public void show(String text) {
        this.text = text;
        if(tv!=null){
            tv.setText(text);
        }
        this.show();
    }

    public void show(int resText) {
        show(mContext.getString(resText));
    }
}