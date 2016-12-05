package com.thinkive.android.trade_bz.utils;

import android.text.Selection;
import android.text.Spannable;
import android.text.TextUtils;
import android.widget.EditText;

/**
 * Created by Administrator on 2016/12/1.
 */

public class EditUtils {

    public static void setEdtCursor(EditText et) {
        if (et == null) {
            return;
        }
        CharSequence text = et.getText();
        if (TextUtils.isEmpty(text)) {
            return;
        }
        if (text instanceof Spannable) {
            Spannable spanText = (Spannable) text;
            Selection.setSelection(spanText, text.length());
        }
    }
}
