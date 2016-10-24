package com.thinkive.android.trade_bz.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.util.DisplayMetrics;

public class ScreenUtils {
    public ScreenUtils() {
    }

    public static float dpToPx(Context context, float dp) {
        return context == null?-1.0F:dp * context.getResources().getDisplayMetrics().density;
    }

    public static float pxToDp(Context context, float px) {
        return context == null?-1.0F:px / context.getResources().getDisplayMetrics().density;
    }

    public static float pxToSp(Context context, float pxValue) {
        if(context == null) {
            return -1.0F;
        } else {
            float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
            return pxValue / fontScale + 0.5F;
        }
    }

    public static float spToPx(Context context, float spValue) {
        if(context == null) {
            return -1.0F;
        } else {
            float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
            return spValue * fontScale + 0.5F;
        }
    }

    public static DisplayMetrics getDisplayMetrics(Activity activity) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }

    public static float getScreenWidth(Activity activity) {
        return (float)getDisplayMetrics(activity).widthPixels;
    }

    public static float getScreenHeight(Activity activity) {
        return (float)getDisplayMetrics(activity).heightPixels;
    }

    /**
     * 获得顶部状态栏的高度
     * @param activity
     * @return
     */
    public static int getStatusHeight(Activity activity) {
        int statusHeight;
        Rect localRect = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(localRect);
        statusHeight = localRect.top;
        if (0 == statusHeight) {
            Class<?> localClass;
            try {
                localClass = Class.forName("com.android.internal.R$dimen");
                Object localObject = localClass.newInstance();
                int i5 = Integer.parseInt(localClass.getField("status_bar_height").get(localObject).toString());
                statusHeight = activity.getResources().getDimensionPixelSize(i5);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return statusHeight;
    }
}
