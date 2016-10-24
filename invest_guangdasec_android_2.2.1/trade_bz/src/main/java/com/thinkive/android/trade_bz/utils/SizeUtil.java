package com.thinkive.android.trade_bz.utils;

import android.content.Context;

/**
 * Created by Administrator on 2016/10/9.
 */

public class SizeUtil {
    /**
     * dp转px
     */public static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;

        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * px转dp
     */public static int px2dp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        System.out.println(("像素密度=" + scale));

        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * sp转px
     */public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * px转sp
     */public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }
}
