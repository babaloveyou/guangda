package com.thinkive.android.trade_bz.keyboard;

import android.graphics.Color;

/**
 * 描述：键盘基类
 *
 * Created by zhuduanchang
 */
public class BaseKeyboard {
    /**
     * 白天主题
     */
    public static final short THEME_LIGHT = 1;
    /**
     * 黑夜主题
     */
    public static final short THEME_NIGHT = 2;
    //默认按键背景色
    protected static int sColorDefaultKeyBg = getColorDefaultKeyBg();
    //默认功能按键背景色
    protected static int sColorDefaultFuncKeyBg = getColorDefaultFuncKeyBg();
    //按住按钮背景颜色
    protected static int sColorSelectedKeyBg = getColorSelectedKeyBg();
    //默认字体颜色
    protected static int sColorDefaultFont = getColorDefaultFont();
    //选中字体颜色
    protected static int sColorSelectedFont = getColorSelectedFont();
    //键盘背景色
    protected static int sColorKeyboardBg = getColorKeyboardBg();

    protected static final String DRAWABLE = "drawable";
    protected static final String RES_NAME_DELETE_BIG_WHITE = "btn_keyboard_delete_big_white";
    protected static final String RES_NAME_DELETE_SMALL_WHITE = "btn_keyboard_delete_small_white";
    protected static final String RES_NAME_SHIFT_WHITE = "btn_keyboard_shift_white";
    protected static final String RES_NAME_DELETE_BIG = "btn_keyboard_delete_big";
    protected static final String RES_NAME_DELETE_SMALL = "btn_keyboard_delete_small";
    protected static final String RES_NAME_SHIFT = "btn_keyboard_shift";

    protected static String sResNameDeleteBig = getResNameDeleteBig();
    protected static String sResNameDeleteSmall = getResNameDeleteSmall();
    protected static String sResNameShift = getResNameShift();

    public static short sTheme = THEME_NIGHT;

    public void setTheme(short theme) {
        sTheme = theme;
        sColorDefaultKeyBg = getColorDefaultKeyBg();
        sColorDefaultFuncKeyBg = getColorDefaultFuncKeyBg();
        sColorSelectedKeyBg = getColorSelectedKeyBg();
        sColorDefaultFont = getColorDefaultFont();
        sColorSelectedFont = getColorSelectedFont();
        sColorKeyboardBg = getColorKeyboardBg();
        sResNameDeleteBig = getResNameDeleteBig();
        sResNameDeleteSmall = getResNameDeleteSmall();
        sResNameShift = getResNameShift();
    }

    public static int getColorDefaultKeyBg() {
        if (THEME_LIGHT == sTheme) {
            return 0xFFE5E5E5;
        } else {
            return 0xFF4D4D4D;
        }
    }

    public static int getColorDefaultFuncKeyBg() {
        if (THEME_LIGHT == sTheme) {
            return 0xFFCCCCCC;
        } else {
            return 0xFF666666;
        }
    }

    public static int getColorSelectedKeyBg() {
        if (THEME_LIGHT == sTheme) {
            return 0xFFFFAA8E;
        } else {
            return 0xFFE84A3C;
        }
    }

    public static int getColorDefaultFont() {
        if (THEME_LIGHT == sTheme) {
            return 0xFF666666;
        } else {
            return 0xFFCCCCCC;
        }
    }

    public static int getColorSelectedFont() {
        if (THEME_LIGHT == sTheme) {
            return Color.WHITE;
        } else {
            return Color.WHITE;
        }
    }

    public static int getColorKeyboardBg() {
        if (THEME_LIGHT == sTheme) {
            return 0xFFFFFFFF;
        } else {
            return 0xFFBFBFBF;
        }
    }

    public static String getResKeyPreviewBg() {
        if (THEME_LIGHT == sTheme) {
            return "bg_key_preview_light";
        } else {
            return "bg_key_preview_night";
        }
    }

    public static String getResNameDeleteBig() {
        if (THEME_LIGHT == sTheme) {
            return RES_NAME_DELETE_BIG + "_light";
        } else {
            return RES_NAME_DELETE_BIG + "_night";
        }
    }

    public static String getResNameDeleteSmall() {
        if (THEME_LIGHT == sTheme) {
            return RES_NAME_DELETE_SMALL + "_light";
        } else {
            return RES_NAME_DELETE_SMALL + "_night";
        }
    }

    public static String getResNameShift() {
        if (THEME_LIGHT == sTheme) {
            return RES_NAME_SHIFT + "_light";
        } else {
            return RES_NAME_SHIFT + "_night";
        }
    }
}
