package com.thinkive.android.trade_bz.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtils {
    private static Toast mToast;

    public static void toast(Context context, String msg) {
        if (mToast == null) {
            mToast = Toast.makeText(context, msg, Toast.LENGTH_LONG);
        } else {
            mToast.setText(msg);
        }
        mToast.show();
    }

    public static void toast(Context context, int resMsgId) {
        toast(context, context.getResources().getString(resMsgId));
    }
}