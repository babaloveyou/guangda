package com.thinkive.android.trade_bz.others.tools;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

/**
 * 字体设置管理类，负责提供TextView的设置字体功能。
 * Announcements：需事先将字体文件（.otf文件或.ttf文件）放入外壳项目的assets目录下
 *
 * @author 王志鸿
 * @company Thinkive
 * @date 2015/8/4
 */
public class FontManager {

    //-------------------字体文件路径常量定义，开始-----------------
    public static final String FONT_PATH_DINPRO_BLACK = "fonts/DINPro-Black.otf";
    public static final String FONT_PATH_DINPRO_BOLD = "fonts/DINPro-Bold.otf";
    public static final String FONT_PATH_DINPRO_LIGHT = "fonts/DINPro-Light.otf";
    public static final String FONT_PATH_DINPRO_MEDIUM = "fonts/DINPro-Medium.otf";
    public static final String FONT_PATH_DINPRO_REGULAR = "fonts/DINPro-Regular.otf";
    //-------------------字体文件路径常量定义，结束-----------------

    //-------------------字体标识数字定义，开始------------------
    public static final short FONT_DINPRO_BLACK = 0;
    public static final short FONT_DINPRO_BOLD = 1;
    public static final short FONT_DINPRO_LIGHT = 2;
    public static final short FONT_DINPRO_MEDIUM = 3;
    public static final short FONT_DINPRO_REGULAR = 4;
    //-------------------字体标识数字定义，开始------------------

    //-------------------字体设置对象定义，开始------------------
    private Typeface mBlackTypeface;
    private Typeface mBoldTypeface;
    private Typeface mLightTypeface;
    private Typeface mMediumTypeface;
    private Typeface mRegularTypeface;
    //-------------------字体设置对象定义，开始------------------

    private Context mContext;

    /**
     * 本类唯一单例实例对象
     */
    private static FontManager mInstance;

    private FontManager(Context context) {
        mContext = context;
        mBlackTypeface = Typeface.createFromAsset(context.getAssets(), FONT_PATH_DINPRO_BLACK);
        mBoldTypeface = Typeface.createFromAsset(context.getAssets(), FONT_PATH_DINPRO_BOLD);
        mLightTypeface = Typeface.createFromAsset(context.getAssets(), FONT_PATH_DINPRO_LIGHT);
        mMediumTypeface = Typeface.createFromAsset(context.getAssets(), FONT_PATH_DINPRO_MEDIUM);
        mRegularTypeface = Typeface.createFromAsset(context.getAssets(), FONT_PATH_DINPRO_REGULAR);
    }

    /**
     * 获取单例对象的方法
     * @return 本类的单例对象
     */
    public synchronized static FontManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new FontManager(context);
        }
        return mInstance;
    }

    /**
     * 对外暴露的设置字体的方法，将textView的字体，设置成font对应的字体。
     * 必须事先将字体文件放进assets目录下
     * @param textView 被设置的TextView
     * @param font 要设置的字体的标识数字。
     */
    public void setTextFont(TextView textView, short font) {
        switch (font) {
            case FONT_DINPRO_BLACK:
                textView.setTypeface(mBlackTypeface);
                break;
            case FONT_DINPRO_BOLD:
                textView.setTypeface(mBoldTypeface);
                break;
            case FONT_DINPRO_LIGHT:
                textView.setTypeface(mLightTypeface);
                break;
            case FONT_DINPRO_MEDIUM:
                textView.setTypeface(mMediumTypeface);
                break;
            case FONT_DINPRO_REGULAR:
                textView.setTypeface(mRegularTypeface);
                break;
        }
    }
}
