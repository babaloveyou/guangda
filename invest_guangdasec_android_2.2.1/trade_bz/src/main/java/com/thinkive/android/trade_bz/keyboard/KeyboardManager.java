package com.thinkive.android.trade_bz.keyboard;

import android.app.Activity;
import android.content.Context;
import android.graphics.PixelFormat;
import android.text.InputFilter;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.android.thinkive.framework.util.Log;
import com.android.thinkive.framework.util.ScreenUtil;

import java.lang.reflect.Field;

/**
 * 描述：键盘管理器
 * 详细：此管理器用来控制开发者与键盘的所有交互、接口
 * <p/>
 * Created by zhuduanchang
 * modified by liujianwei 2015-6-25
 */
public class KeyboardManager {
    public static final short KEYBOARD_TYPE_ENGLISH = 1;
    public static final short KEYBOARD_TYPE_STOCK = 2;
    public static final short KEYBOARD_TYPE_DIGITAL = 3;
    public static final short KEYBOARD_TYPE_RANDOM_DIGITAL = 4;
    public static final short KEYBOARD_TYPE_RANDOM_COMMON = 5;
    public static final short KEYBOARD_TYPE_MERCHANDISE = 6;
    public static final short KEYBOARD_TYPE_COMMON = 7;

    public static /*final*/ int KEYBOARD_HEIGHT = 250; //键盘高度为250dp

    private WindowManager mWindowManager;
    //暴露按键动作的监听器，用户根据按键编码自行处理按键动作，适用于H5
    private KeyboardEventListener mExportActionListener;
    private EnglishKeyboard mEnglishKeyboard;
    private DigitalKeyboard mRandomDigitalKeyboard;
    private StockKeyboard mStockKeyboard;
    private CommonKeyboard mCommonKeyboard;
    private MerchandiseKeyboard mMerchandiseKeyboard;

    private View mKeyboardView;
    private EditText mEditText; //与输入法绑定的输入框
    private short mKeyboardType = KEYBOARD_TYPE_ENGLISH;
    private Context mContext;
    private boolean mIsKeyboardAddedToWindow = false;   //键盘是否添加到了window
    //输入完成监听器，原生需要监听这些个事件
    private OnInputCompleteListener mInputCompleteListener;
    private static KeyboardManager sInstance;
    private KeyCodeListener mKeyCodeListener;

    //默认的监听器，内部处理按键动作
    private KeyboardEventListener mKeyboardActionListener = new KeyboardEventListener() {
        @Override
        public void onKeyEvent(int keyCode) {
            if (keyCode < 0) {
                if (mInputCompleteListener != null) {
                    if (KeyboardEventListener.KEY_CODE_DONE == keyCode
                            || KeyboardEventListener.KEY_CODE_HIDE == keyCode) {
                        dismiss();
                        mInputCompleteListener.onInputComplete();
                    }
                }
                //如果EditText为Null，则清空也需要传出去让开发者处理
                if (mEditText == null
                        && KeyboardEventListener.KEY_CODE_CLEAR == keyCode) {
                    mExportActionListener.onKeyEvent(keyCode);
                }
                if (mExportActionListener != null
                        && (KeyboardEventListener.KEY_CODE_DONE == keyCode
                        || KeyboardEventListener.KEY_CODE_DELETE == keyCode
                        || KeyboardEventListener.KEY_CODE_HIDE == keyCode
                        || keyCode <= KeyboardEventListener.KEY_CODE_600)) {
                    if (mExportActionListener != null) {
                        mExportActionListener.onKeyEvent(keyCode);
                    }
                }
                if (mExportActionListener != null
                        && (KeyboardEventListener.KEY_CODE_ALL_POSTION == keyCode
                        || KeyboardEventListener.KEY_CODE_HALF_POSTION == keyCode
                        || KeyboardEventListener.KEY_CODE_ONE_FOURTH_POSTION == keyCode
                        || KeyboardEventListener.KEY_CODE_ONE_THIRD_POSTION == keyCode
                )) {
                    if (mExportActionListener != null) {
                        mExportActionListener.onKeyEvent(keyCode);
                    }
                } else {
                    onFunctionCode(keyCode);
                }
            } else {
                if (mExportActionListener != null) {
                    mExportActionListener.onKeyEvent(keyCode);
                } else if (mEditText != null) {
//                    int cursorIndex = mEditText.getSelectionStart();
//                    mEditText.getText().insert(cursorIndex, (char) keyCode + "");
                    String lastStr = mEditText.getText().toString();
                    int maxLength = getMaxLength();
                    Log.d("exittext maxlength = " + maxLength);
                    if (maxLength != 0 && lastStr.length() >= maxLength) {
                        return;
                    }
                    String curStr = lastStr + (char) keyCode;
                    mEditText.setText(curStr);
                    try {
                        mEditText.setSelection(curStr.length());
                    } catch (Exception e) {
                    }
                }
            }
        }
    };
    private OnKeyCodeDownListener mOnKeyCodeDownListener;

    public int getMaxLength() {
        int length = 0;
        try {
            InputFilter[] inputFilters = mEditText.getFilters();
            for (InputFilter filter : inputFilters) {
                Class<?> c = filter.getClass();
                if (c.getName().equals("android.text.InputFilter$LengthFilter")) {
                    Field[] f = c.getDeclaredFields();
                    for (Field field : f) {
                        if (field.getName().equals("mMax")) {
                            field.setAccessible(true);
                            length = (Integer) field.get(filter);
                            break;
                        }
                    }

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return length;
    }

    /**
     * 构造keyboardType类型的键盘，并传入editText让管理器内部处理输入操作;
     * 调用者只需要调用show或者dismiss方法即可;
     *
     * @param context      上下文
     * @param editText     输入框
     * @param keyboardType 键盘类型
     */
    public KeyboardManager(Context context, EditText editText, short keyboardType) {
        mContext = context;
        mEditText = editText;
        mKeyboardType = keyboardType;
        init();
        sInstance = this;
    }

    /**
     * 构造keyboardType类型的键盘，不传入EditText对象;
     * 此时需要调用setExportActionListener方法设置动作输出的接口，调用者外部进行按键的事件处理;
     *
     * @param context      上下文
     * @param keyboardType 键盘类型
     */
    public KeyboardManager(Context context, short keyboardType) {
        mContext = context;
        mKeyboardType = keyboardType;
        init();
        sInstance = this;
    }

    private void init() {
        //解决在小米3等手机上系统禁用system_alert_dialog的问题。
        if (mContext instanceof Activity) {
            Activity activity = (Activity) mContext;
            mWindowManager = (WindowManager) activity.getSystemService(Context.WINDOW_SERVICE);
        } else {
            mWindowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        }
        switch (mKeyboardType) {
            case KEYBOARD_TYPE_STOCK:
            case KEYBOARD_TYPE_ENGLISH:
                initStockKeyboard();
                break;
            case KEYBOARD_TYPE_DIGITAL:
                initDigitalKeyboard(false);
                break;
            case KEYBOARD_TYPE_RANDOM_DIGITAL:
                initDigitalKeyboard(true);
                break;
            case KEYBOARD_TYPE_RANDOM_COMMON:
                initCommonKeyboard(true);
                break;
            case KEYBOARD_TYPE_COMMON:
                initCommonKeyboard(false);
                break;
            case KEYBOARD_TYPE_MERCHANDISE:
                initMerchandiseKeyboard();
                break;
            default:
                break;
        }
    }

    private void initDigitalKeyboard(boolean isRandom) {
        mRandomDigitalKeyboard = new DigitalKeyboard(mContext, isRandom);
        mRandomDigitalKeyboard.setOnKeyboardActionListener(mKeyboardActionListener);
        mKeyboardView = mRandomDigitalKeyboard.getView();
    }


    private void initCommonKeyboard(boolean isRandom) {
        LinearLayout layout = new LinearLayout(mContext);
        layout.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                (int) ScreenUtil.dpToPx(mContext, KEYBOARD_HEIGHT)
        ));
        layout.setOrientation(LinearLayout.HORIZONTAL);
        mEnglishKeyboard = new EnglishKeyboard(mContext);
        mCommonKeyboard = new CommonKeyboard(mContext, isRandom);

        mEnglishKeyboard.setOnKeyboardActionListener(mKeyboardActionListener);
        mCommonKeyboard.setOnKeyboardActionListener(mKeyboardActionListener);

        switch (mKeyboardType) {
            case KEYBOARD_TYPE_ENGLISH:
                layout.addView(mEnglishKeyboard.getView());
                layout.addView(mCommonKeyboard.getView());

                break;
            case KEYBOARD_TYPE_RANDOM_COMMON:
                layout.addView(mCommonKeyboard.getView());
                layout.addView(mEnglishKeyboard.getView());
                break;
            case KEYBOARD_TYPE_COMMON:
                layout.addView(mCommonKeyboard.getView());
                layout.addView(mEnglishKeyboard.getView());
                break;
            default:
                break;
        }
        mKeyboardView = layout;
    }

    private void initStockKeyboard() {
        LinearLayout layout = new LinearLayout(mContext);
        layout.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                (int) ScreenUtil.dpToPx(mContext, KEYBOARD_HEIGHT)
        ));
        layout.setOrientation(LinearLayout.HORIZONTAL);
        mEnglishKeyboard = new EnglishKeyboard(mContext);
        mStockKeyboard = new StockKeyboard(mContext);

        mEnglishKeyboard.setOnKeyboardActionListener(mKeyboardActionListener);
        mStockKeyboard.setOnKeyboardActionListener(mKeyboardActionListener);

        switch (mKeyboardType) {
            case KEYBOARD_TYPE_ENGLISH:
                layout.addView(mEnglishKeyboard.getView());
                layout.addView(mStockKeyboard.getView());

                break;
            case KEYBOARD_TYPE_STOCK:
                layout.addView(mStockKeyboard.getView());
                layout.addView(mEnglishKeyboard.getView());

                break;
            default:
                break;
        }
        mKeyboardView = layout;
    }

    private void initMerchandiseKeyboard() {
        mMerchandiseKeyboard = new MerchandiseKeyboard(mContext);
        mMerchandiseKeyboard.setOnKeyboardActionListener(mKeyboardActionListener);
        mKeyboardView = mMerchandiseKeyboard.getView();
    }


    public void show() {
        if (!mIsKeyboardAddedToWindow) {
            WindowManager.LayoutParams wmParam = new WindowManager.LayoutParams();
            //解决在小米3等手机上系统禁用system_alert_dialog的问题。
            if (!(mContext instanceof Activity)) {
                wmParam.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
            }
            wmParam.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS;
            wmParam.format = PixelFormat.TRANSLUCENT;// 半透明效果
            wmParam.width = WindowManager.LayoutParams.MATCH_PARENT;
            wmParam.height = (int) ScreenUtil.dpToPx(mContext, KEYBOARD_HEIGHT);
            wmParam.gravity = Gravity.BOTTOM;
            mWindowManager.addView(mKeyboardView, wmParam);
            mIsKeyboardAddedToWindow = true;
        }
    }

    public void dismiss() {
        if (mIsKeyboardAddedToWindow) {
            mWindowManager.removeView(mKeyboardView);
            mIsKeyboardAddedToWindow = false;
        }
    }

    public boolean isShowing() {
        return mIsKeyboardAddedToWindow;
    }

    private void onFunctionCode(int keyCode) {
        int cursorIndex = 0;
        if (null != mEditText) {
            cursorIndex = mEditText.getSelectionStart();
        }
        switch (keyCode) {
            case KeyboardEventListener.KEY_CODE_DELETE:
                if (null == mEditText) {
                    return;
                }
                if (mEditText.getText().length() > 0 && cursorIndex > 0) {
                    mEditText.getText().delete(cursorIndex - 1, cursorIndex);
                }

                break;
            case KeyboardEventListener.KEY_CODE_DONE:
                dismiss();
                if (mOnKeyCodeDownListener != null) {
                    mOnKeyCodeDownListener.onKeyCodeDown();
                }
                break;
            case KeyboardEventListener.KEY_CODE_SWITCH_NUM_TO_EN:
                mEnglishKeyboard.show();
                if (mKeyboardType == KEYBOARD_TYPE_RANDOM_COMMON || mKeyboardType == KEYBOARD_TYPE_COMMON) {
                    mCommonKeyboard.hide();
                } else if (mKeyboardType == KEYBOARD_TYPE_STOCK) {
                    mStockKeyboard.hide();
                }

                break;
            case KeyboardEventListener.KEY_CODE_CLEAR:
                if (null == mEditText) {
                    return;
                }
                mEditText.getText().clear();

                break;
            case KeyboardEventListener.KEY_CODE_HIDE:
                dismiss();

                break;
            case KeyboardEventListener.KEY_CODE_SWITCH_EN_TO_NUM:
                if (mKeyboardType == KEYBOARD_TYPE_RANDOM_COMMON || mKeyboardType == KEYBOARD_TYPE_COMMON) {
                    mCommonKeyboard.show();
                    mEnglishKeyboard.hide();
                } else if (mKeyboardType == KEYBOARD_TYPE_STOCK || mKeyboardType == KEYBOARD_TYPE_ENGLISH) {
                    mStockKeyboard.show();
                    mEnglishKeyboard.hide();
                } /*else if (mKeyboardType == KEYBOARD_TYPE_IOS_ALPHABET||mKeyboardType == KEYBOARD_TYPE_IOS_DIGITAL) {
                    mIOSSignKeyboard.show();
                    mIOSAlphabetKeyboard.hide();
                    mIOSDigitalKeyboard.hide();
                }*/

                break;

            case KeyboardEventListener.KEY_CODE_000:
                if (null == mEditText) {
                    return;
                }
                mEditText.getText().insert(cursorIndex, "000");

                break;
            case KeyboardEventListener.KEY_CODE_002:
                if (null == mEditText) {
                    return;
                }
                mEditText.getText().insert(cursorIndex, "002");

                break;
            case KeyboardEventListener.KEY_CODE_300:
                if (null == mEditText) {
                    return;
                }
                mEditText.getText().insert(cursorIndex, "300");

                break;
            case KeyboardEventListener.KEY_CODE_600:
                if (null == mEditText) {
                    return;
                }
                mEditText.getText().insert(cursorIndex, "600");

                break;
            case KeyboardEventListener.KEY_CODE_601:
                if (null == mEditText) {
                    return;
                }
                mEditText.getText().insert(cursorIndex, "601");

                break;
            case KeyboardEventListener.KEY_CODE_POINT:
                if (null == mEditText) {
                    return;
                }
                mEditText.getText().insert(cursorIndex, ".");
                break;
            case KeyboardEventListener.KEY_CODE_00:
                if (null == mEditText) {
                    return;
                }
                mEditText.getText().insert(cursorIndex, "00");
                break;
            case KeyboardEventListener.KEY_CODE_ALL_POSTION:
                if (mKeyCodeListener != null) {
                    mKeyCodeListener.onKeyCode(KeyboardEventListener.KEY_CODE_ALL_POSTION);
                }

                break;
            case KeyboardEventListener.KEY_CODE_HALF_POSTION:
                if (mKeyCodeListener != null) {
                    mKeyCodeListener.onKeyCode(KeyboardEventListener.KEY_CODE_HALF_POSTION);
                }

                break;
            case KeyboardEventListener.KEY_CODE_ONE_THIRD_POSTION:
                if (mKeyCodeListener != null) {
                    mKeyCodeListener.onKeyCode(KeyboardEventListener.KEY_CODE_ONE_THIRD_POSTION);
                }

                break;
            case KeyboardEventListener.KEY_CODE_ONE_FOURTH_POSTION:
                if (mKeyCodeListener != null) {
                    mKeyCodeListener.onKeyCode(KeyboardEventListener.KEY_CODE_ONE_FOURTH_POSTION);
                }

                break;
            default:
                if (mKeyCodeListener != null) {
                    mKeyCodeListener.onKeyCode(keyCode);
                }
                break;
        }
    }

    public void setExportActionListener(KeyboardEventListener exportActionListener) {
        if (null == exportActionListener) {
            return;
        }
        mExportActionListener = exportActionListener;
    }

    public void setInputCompleteListener(final OnInputCompleteListener inputCompleteListener) {
        mInputCompleteListener = inputCompleteListener;
    }

    /**
     * 获取弹出键盘组件时输入框需要向上滚动的距离，注意要使用#ScrollView.scrollBy();
     *
     * @param activity 输入框所在的Activity
     * @param editText 输入框对象
     * @return 需要向上滚动的距离
     */
    public static int getEditTextScrollHeight(Activity activity, EditText editText) {
        if (null == editText || null == activity) {
            return 0;
        }
        int[] location = new int[2];
        editText.getLocationOnScreen(location);
        int scrollHeight = (int) (location[1] + editText.getHeight()
                - (ScreenUtil.getScreenHeight(activity) - ScreenUtil.dpToPx(activity, KEYBOARD_HEIGHT)));
        return scrollHeight;
    }

    public static KeyboardManager getInstance() {
        return sInstance;
    }

    public void setOnKeyCodeDownListener(OnKeyCodeDownListener onKeyCodeDownListener) {
        mOnKeyCodeDownListener = onKeyCodeDownListener;
    }

    /**
     * 输入完成监听器
     */
    public interface OnInputCompleteListener {
        public void onInputComplete();
    }

    public BaseKeyboard getKeyBoard() {
        switch (mKeyboardType) {
            case KEYBOARD_TYPE_STOCK:
            case KEYBOARD_TYPE_ENGLISH:
                return mStockKeyboard;
            case KEYBOARD_TYPE_DIGITAL:
                return mRandomDigitalKeyboard;
            case KEYBOARD_TYPE_RANDOM_DIGITAL:
                return mRandomDigitalKeyboard;
            case KEYBOARD_TYPE_RANDOM_COMMON:
                return mCommonKeyboard;
            case KEYBOARD_TYPE_COMMON:
                return mCommonKeyboard;
            case KEYBOARD_TYPE_MERCHANDISE:
                return mMerchandiseKeyboard;
            default:
                return mCommonKeyboard;
        }
    }

    public void setTheme(short theme) {
        if (mCommonKeyboard != null) {
            mCommonKeyboard.setTheme(theme);
        }
        if (mEnglishKeyboard != null) {
            mEnglishKeyboard.setTheme(theme);
        }
        if (mStockKeyboard != null) {
            mStockKeyboard.setTheme(theme);
        }
        if (mRandomDigitalKeyboard != null) {
            mRandomDigitalKeyboard.setTheme(theme);
        }
        if (mMerchandiseKeyboard != null) {
            mMerchandiseKeyboard.setTheme(theme);
        }

    }

    public void setKeyCodeListener(KeyCodeListener listener) {
        this.mKeyCodeListener = listener;
    }

    /**
     * 用户交易需要用到买卖交易键盘的地方
     * 满仓，半仓等键盘按键的回调
     */
    public interface KeyCodeListener {
        public void onKeyCode(int keyCode);
    }

    public interface OnKeyCodeDownListener {
        public void onKeyCodeDown();
    }
}
