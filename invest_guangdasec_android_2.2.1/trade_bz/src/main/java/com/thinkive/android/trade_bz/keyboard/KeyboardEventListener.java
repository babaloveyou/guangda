package com.thinkive.android.trade_bz.keyboard;

/**
 * 描述：键盘动作监听接口
 * Created by zhuduanchang
 */
public interface KeyboardEventListener {
    /**
     * 切换英文到数字
     */
    public static final int KEY_CODE_SWITCH_EN_TO_NUM = -1;
    /**
     * 清空
     */
    public static final int KEY_CODE_CLEAR = -2;
    /**
     * 完成
     */
    public static final int KEY_CODE_DONE = -3;
    /**
     * 隐藏
     */
    public static final int KEY_CODE_HIDE = -4;
    /**
     * 删除
     */
    public static final int KEY_CODE_DELETE = -5;
    /**
     * 切换大小写
     */
    public static final int KEY_CODE_SHIFT = -6;
    /**
     * 切换数字键为英文键盘
     */
    public static final int KEY_CODE_SWITCH_NUM_TO_EN = -7;
    /**
     * 买卖交易1/4仓
     */
    public static final int KEY_CODE_ONE_FOURTH_POSTION = -11;
    /**
     *买卖交易1/3仓
     */
    public static final int KEY_CODE_ONE_THIRD_POSTION = -12;
    /**
     * 买卖交易半仓
     */
    public static final int KEY_CODE_HALF_POSTION = -13;
    /**
     * 买卖交易全仓
     */
    public static final int KEY_CODE_ALL_POSTION = -14;

    /**
     * 股票代码前缀600
     */
    public static final int KEY_CODE_600 = -21;
    /**
     * 股票代码前缀601
     */
    public static final int KEY_CODE_601 = -22;
    /**
     * 股票代码前缀000
     */
    public static final int KEY_CODE_000 = -23;
    /**
     * 股票代码前缀002
     */
    public static final int KEY_CODE_002 = -24;
    /**
     * 股票代码前缀300
     */
    public static final int KEY_CODE_300 = -25;
    /**
     * 小数点
     */
    public static final int KEY_CODE_POINT = -26;

    /**
     * 股票代码前缀00
     */
    public static final int KEY_CODE_00 = -27;

    /**
     * 交易键盘增量（如＋100）
     */
    public static final int KEY_CODE_INCREMENT = -28;

    /**
     * 交易键盘减量（如-100）
     */
    public static final int KEY_CODE_DECREMENT = -29;

    /**
     * @param keyCode 按键编码
     */
    public void onKeyEvent(int keyCode);
}
