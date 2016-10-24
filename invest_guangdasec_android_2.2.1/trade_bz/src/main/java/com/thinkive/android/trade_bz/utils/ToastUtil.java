package com.thinkive.android.trade_bz.utils;

import android.widget.Toast;

import com.android.thinkive.framework.CoreApplication;

/**
 * Created by Administrator on 2016/10/9.
 */

public class ToastUtil {
    private static Toast mToast;
    public static void showToast(String toast) {
        if (mToast == null) {
            mToast = Toast.makeText(CoreApplication.getInstance(), "", Toast.LENGTH_SHORT);
        }
        mToast.setText(toast);
        mToast.show();
    }
}
