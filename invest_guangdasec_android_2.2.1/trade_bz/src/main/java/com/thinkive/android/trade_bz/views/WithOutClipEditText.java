package com.thinkive.android.trade_bz.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ContextMenu;
import android.widget.EditText;

/**
 * Created by Administrator on 2016/10/22.
 */

public class WithOutClipEditText extends EditText {
    public WithOutClipEditText(Context context) {
        super(context);
    }

    public WithOutClipEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WithOutClipEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onCreateContextMenu(ContextMenu menu) {
        super.onCreateContextMenu(menu);
    }
}
