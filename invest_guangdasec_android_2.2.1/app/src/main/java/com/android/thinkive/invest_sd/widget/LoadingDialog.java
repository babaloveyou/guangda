package com.android.thinkive.invest_sd.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.thinkive.invest_sd.R;


public class LoadingDialog extends Dialog {
    private TextView tv;
    private String text;
    public LoadingDialog(Context context) {
        super(context, R.style.loadingDialogStyle);
    }

    private LoadingDialog(Context context, int theme) {
        super(context, theme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_loading);
        tv = (TextView)this.findViewById(R.id.tv);
        tv.setText(text);
        LinearLayout linearLayout = (LinearLayout)this.findViewById(R.id.LinearLayout);
        linearLayout.getBackground().setAlpha(210);
    }

    public void show(String text) {
        this.text = text;
        if(tv!=null){
            tv.setText(text);
        }
        this.show();
    }
}