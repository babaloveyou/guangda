package com.thinkive.android.trade_bz.keyboard;

import android.app.Activity;
import android.content.Context;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.thinkive.framework.util.ResourceUtil;
import com.android.thinkive.framework.util.ScreenUtil;
import com.android.thinkive.framework.view.RoundedCornerImageView;
import com.android.thinkive.framework.view.RoundedCornerTextView;

/**
 * 描述：英文键盘
 * 详细：用户获取用户输入的英文字符，为了完全体现效果图的设计及用户体验，按键的宽度使用计算的结果
 * <p/>
 * Created by zhuduanchang
 * modified by liujianwei 2015-6-25
 */
public class EnglishKeyboard extends BaseKeyboard implements View.OnClickListener {
    private Context mContext;
    //按键监听回调接口
    private KeyboardEventListener mActionListener;
    private boolean mIsLowerCase = true;   //是否小写

    //Views
    private RoundedCornerTextView mQ, mW, mE, mR, mT, mY, mU, mI, mO, mP;
    private RoundedCornerTextView mA, mS, mD, mF, mG, mH, mJ, mK, mL;
    private RoundedCornerTextView mZ, mX, mC, mV, mB, mN, mM;
    //功能按键
    private RoundedCornerTextView m123, mConfirm, mHide, mSpace;
    private RoundedCornerTextView mDelete, mShift;
    //布局
    private LinearLayout mRootLayout, mLine1Layout, mLine2Layout, mLine3Layout, mLine4Layout;
    private int mKeyWidth;  //按键宽度
    private int mPadding;   //按键间距
    private KeyInputPreviewView mPreviewView;   //预览视图
    private WindowManager mWindowManager;
    private boolean mIsKeyPreviewAddedToWindow;

    public EnglishKeyboard(Context context) {
        mContext = context;
        mPadding = dp2Px(2);
        //按键宽度=（屏幕宽度-总间隔宽度）/每行按键个数
        mKeyWidth = (int) ((ScreenUtil.getScreenWidth(context)
                - ScreenUtil.dpToPx(mContext, 2) * 9) / 10);
        if (mContext instanceof Activity) {
            Activity activity = (Activity) mContext;
            mWindowManager = (WindowManager) activity.getSystemService(Context.WINDOW_SERVICE);
        } else {
            mWindowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        }
        createLine1Layout();
        createLine2Layout();
        createLine3Layout();
        createLine4Layout();

        layout();

        initTagValue();
        setListenersAndOthers(mRootLayout);

        mPreviewView = new KeyInputPreviewView(mContext);
        mPreviewView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
        ));
        mPreviewView.setBackgroundResource(
                ResourceUtil.getResourceID(mContext, DRAWABLE, getResKeyPreviewBg()));
    }

    @Override
    public void setTheme(short theme){
        super.setTheme(theme);
        setListenersAndOthers(mRootLayout);
        mPreviewView.setBackgroundResource(
                ResourceUtil.getResourceID(mContext, DRAWABLE, getResKeyPreviewBg()));
    }

    private void layout() {
        mRootLayout = new LinearLayout(mContext);
        mRootLayout.setOrientation(LinearLayout.VERTICAL);
        mRootLayout.setBackgroundColor(sColorKeyboardBg);
        mRootLayout.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                (int) ScreenUtil.dpToPx(mContext, KeyboardManager.KEYBOARD_HEIGHT)
        ));
        mRootLayout.setPadding(2, 2, 2, 2);

        LinearLayout.LayoutParams lpChild = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 0
        );
        lpChild.weight = 1;
        mRootLayout.addView(mLine1Layout, lpChild);
        mRootLayout.addView(mLine2Layout, lpChild);
        mRootLayout.addView(mLine3Layout, lpChild);
        mRootLayout.addView(mLine4Layout, lpChild);
    }

    private void createLine1Layout() {
        mLine1Layout = new LinearLayout(mContext);
        mLine1Layout.setOrientation(LinearLayout.HORIZONTAL);
        mLine1Layout.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 0
        ));
        mLine1Layout.setPadding(0, 0, 0, mPadding);

        mQ = new RoundedCornerTextView(mContext);
        mW = new RoundedCornerTextView(mContext);
        mE = new RoundedCornerTextView(mContext);
        mR = new RoundedCornerTextView(mContext);
        mT = new RoundedCornerTextView(mContext);
        mY = new RoundedCornerTextView(mContext);
        mU = new RoundedCornerTextView(mContext);
        mI = new RoundedCornerTextView(mContext);
        mO = new RoundedCornerTextView(mContext);
        mP = new RoundedCornerTextView(mContext);

        LinearLayout.LayoutParams lpChildParams = new LinearLayout.LayoutParams(
                mKeyWidth, ViewGroup.LayoutParams.MATCH_PARENT
        );
        lpChildParams.leftMargin = mPadding;
        LinearLayout.LayoutParams lpFirstChildParams = new LinearLayout.LayoutParams(
                mKeyWidth, ViewGroup.LayoutParams.MATCH_PARENT
        );

        mLine1Layout.addView(mQ, lpFirstChildParams);
        mLine1Layout.addView(mW, lpChildParams);
        mLine1Layout.addView(mE, lpChildParams);
        mLine1Layout.addView(mR, lpChildParams);
        mLine1Layout.addView(mT, lpChildParams);
        mLine1Layout.addView(mY, lpChildParams);
        mLine1Layout.addView(mU, lpChildParams);
        mLine1Layout.addView(mI, lpChildParams);
        mLine1Layout.addView(mO, lpChildParams);
        mLine1Layout.addView(mP, lpChildParams);
    }

    private void createLine2Layout() {
        mLine2Layout = new LinearLayout(mContext);
        mLine2Layout.setOrientation(LinearLayout.HORIZONTAL);
        mLine2Layout.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 0
        ));
        mLine2Layout.setPadding(0, 0, 0, mPadding);

        mA = new RoundedCornerTextView(mContext);
        mS = new RoundedCornerTextView(mContext);
        mD = new RoundedCornerTextView(mContext);
        mF = new RoundedCornerTextView(mContext);
        mG = new RoundedCornerTextView(mContext);
        mH = new RoundedCornerTextView(mContext);
        mJ = new RoundedCornerTextView(mContext);
        mK = new RoundedCornerTextView(mContext);
        mL = new RoundedCornerTextView(mContext);

        LinearLayout.LayoutParams lpChildParams = new LinearLayout.LayoutParams(
                mKeyWidth, ViewGroup.LayoutParams.MATCH_PARENT
        );
        lpChildParams.leftMargin = mPadding;
        LinearLayout.LayoutParams lpFirstChildParams = new LinearLayout.LayoutParams(
                mKeyWidth, ViewGroup.LayoutParams.MATCH_PARENT
        );
        lpFirstChildParams.leftMargin = mKeyWidth / 2 + mPadding;
        LinearLayout.LayoutParams lpLastChildParams = new LinearLayout.LayoutParams(
                mKeyWidth, ViewGroup.LayoutParams.MATCH_PARENT
        );
        lpLastChildParams.rightMargin = mKeyWidth / 2;
        lpLastChildParams.leftMargin = mPadding;

        mLine2Layout.addView(mA, lpFirstChildParams);
        mLine2Layout.addView(mS, lpChildParams);
        mLine2Layout.addView(mD, lpChildParams);
        mLine2Layout.addView(mF, lpChildParams);
        mLine2Layout.addView(mG, lpChildParams);
        mLine2Layout.addView(mH, lpChildParams);
        mLine2Layout.addView(mJ, lpChildParams);
        mLine2Layout.addView(mK, lpChildParams);
        mLine2Layout.addView(mL, lpLastChildParams);
    }

    private void createLine3Layout() {
        mLine3Layout = new LinearLayout(mContext);
        mLine3Layout.setOrientation(LinearLayout.HORIZONTAL);
        mLine3Layout.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 0
        ));
        mLine3Layout.setPadding(0, 0, 0, mPadding);

        mShift = new RoundedCornerTextView(mContext);
        mDelete = new RoundedCornerTextView(mContext);
        mZ = new RoundedCornerTextView(mContext);
        mX = new RoundedCornerTextView(mContext);
        mC = new RoundedCornerTextView(mContext);
        mV = new RoundedCornerTextView(mContext);
        mB = new RoundedCornerTextView(mContext);
        mN = new RoundedCornerTextView(mContext);
        mM = new RoundedCornerTextView(mContext);

        LinearLayout.LayoutParams lpChildParams = new LinearLayout.LayoutParams(
                mKeyWidth, ViewGroup.LayoutParams.MATCH_PARENT
        );
        lpChildParams.leftMargin = mPadding;
        LinearLayout.LayoutParams lpLastChildParams = new LinearLayout.LayoutParams(
                mKeyWidth * 3 / 2 + mPadding, ViewGroup.LayoutParams.MATCH_PARENT
        );
        lpLastChildParams.leftMargin = mPadding;

        mLine3Layout.addView(mShift, new LinearLayout.LayoutParams(
                mKeyWidth * 3 / 2 + mPadding, ViewGroup.LayoutParams.MATCH_PARENT
        ));
        mLine3Layout.addView(mZ, lpChildParams);
        mLine3Layout.addView(mX, lpChildParams);
        mLine3Layout.addView(mC, lpChildParams);
        mLine3Layout.addView(mV, lpChildParams);
        mLine3Layout.addView(mB, lpChildParams);
        mLine3Layout.addView(mN, lpChildParams);
        mLine3Layout.addView(mM, lpChildParams);
        mLine3Layout.addView(mDelete, lpLastChildParams);
    }

    private void createLine4Layout() {
        mLine4Layout = new LinearLayout(mContext);
        mLine4Layout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT
        ));
        mLine4Layout.setOrientation(LinearLayout.HORIZONTAL);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                mKeyWidth * 3 / 2 + mPadding, LinearLayout.LayoutParams.MATCH_PARENT
        );
        params.leftMargin = mPadding;

        m123 = new RoundedCornerTextView(mContext);
        mHide = new RoundedCornerTextView(mContext);
        mConfirm = new RoundedCornerTextView(mContext);
        mSpace = new RoundedCornerTextView(mContext);

        m123.setLayoutParams(new LinearLayout.LayoutParams(
                mKeyWidth * 3 / 2 + mPadding, LinearLayout.LayoutParams.MATCH_PARENT
        ));
        mHide.setLayoutParams(params);
        params = new LinearLayout.LayoutParams(
                0, LinearLayout.LayoutParams.MATCH_PARENT
        );
        params.weight = 1;
        params.leftMargin = mPadding;
        mSpace.setLayoutParams(params);
        params = new LinearLayout.LayoutParams(
                mKeyWidth * 5 / 2 + mPadding, ViewGroup.LayoutParams.MATCH_PARENT
        );
        params.leftMargin = mPadding;
        mConfirm.setLayoutParams(params);
        mConfirm.getPaint().setFakeBoldText(true);  //设置粗体

        mLine4Layout.addView(m123);
        mLine4Layout.addView(mHide);
        mLine4Layout.addView(mSpace);
        mLine4Layout.addView(mConfirm);
    }

    private void setListenersAndOthers(ViewGroup viewGroup) {
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View item = viewGroup.getChildAt(i);
            if (item instanceof ViewGroup) {
                setListenersAndOthers((ViewGroup) item);
            } else if (item instanceof RoundedCornerTextView) {
                initKey(item);
            } else if (item instanceof RoundedCornerImageView) {
                initImageKey((RoundedCornerImageView) item);
            }
        }
    }

    private void initImageKey(final RoundedCornerImageView view) {
        final int tag = (Integer) view.getTag();
        view.setCornerRadius(dp2Px(1));
        view.setRoundedCornerBgColor(sColorDefaultFuncKeyBg);
        view.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        view.setLongClickable(true);
        if (KeyboardEventListener.KEY_CODE_DELETE == tag) {
            view.setImageResource(
                    ResourceUtil.getResourceID(mContext, DRAWABLE, sResNameDeleteSmall));
            view.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(final View v, final MotionEvent event) {
                    if (MotionEvent.ACTION_DOWN == event.getAction()) {
                        view.setRoundedCornerBgColor(sColorSelectedKeyBg);
                        view.setImageResource(
                                ResourceUtil.getResourceID(
                                        mContext,
                                        DRAWABLE,
                                        RES_NAME_DELETE_SMALL_WHITE));
                    } else if (MotionEvent.ACTION_MOVE != event.getAction()) {
                        view.setRoundedCornerBgColor(sColorDefaultFuncKeyBg);
                        view.setImageResource(
                                ResourceUtil.getResourceID(
                                        mContext,
                                        DRAWABLE,
                                        sResNameDeleteSmall));
                    }
                    if (MotionEvent.ACTION_UP == event.getAction()) {
                        onClick(v);
                    }

                    return true;
                }
            });
        } else if (KeyboardEventListener.KEY_CODE_SHIFT == tag) {
            view.setImageResource(
                    ResourceUtil.getResourceID(mContext, DRAWABLE, sResNameShift));
            view.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(final View v, final MotionEvent event) {

                    if (MotionEvent.ACTION_DOWN == event.getAction()) {
                        view.setRoundedCornerBgColor(sColorSelectedKeyBg);
                        view.setImageResource(
                                ResourceUtil.getResourceID(mContext, DRAWABLE, RES_NAME_SHIFT_WHITE));
                    } else if (MotionEvent.ACTION_MOVE != event.getAction()) {
                        view.setRoundedCornerBgColor(sColorDefaultFuncKeyBg);
                        view.setImageResource(
                                ResourceUtil.getResourceID(mContext, DRAWABLE, sResNameShift));
                    }
                    if (MotionEvent.ACTION_UP == event.getAction()) {
                        onClick(v);
                    }

                    return true;
                }
            });
        }
    }

    private void initKey(View view) {
        final RoundedCornerTextView text = (RoundedCornerTextView) view;
        text.setGravity(Gravity.CENTER);
        text.setTextSize(20);
        text.setTextColor(sColorDefaultFont);
        text.setCornerRadius(dp2Px(1));
        setKeyBackgroundAndFontColor(text, false);

        final int tag = Integer.valueOf(text.getTag().toString());
        text.setLongClickable(true);
        text.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEvent.ACTION_DOWN == event.getAction()) {
                    if (tag < 0 || (int) ' ' == tag) {
                        setKeyBackgroundAndFontColor(((RoundedCornerTextView) v), true);
                    } else {
                        mPreviewView.setText(text.getText().toString());
                        showKeyPreview(v);
                    }
                } else if (MotionEvent.ACTION_MOVE != event.getAction()) {
                    if (tag < 0 || (int) ' ' == tag) {
                        setKeyBackgroundAndFontColor(((RoundedCornerTextView) v), false);
                    } else {
                        dismissKeyPreview();
                    }
                }
                if (MotionEvent.ACTION_UP == event.getAction()) {
                    onClick(v);
                }
                return true;
            }
        });

        //设置文字
        if (tag > 0) {
            text.setText(String.valueOf((char) tag));
        } else if (KeyboardEventListener.KEY_CODE_DONE == tag) {
            text.setText("完成");
        } else if (KeyboardEventListener.KEY_CODE_HIDE == tag) {
            text.setText("隐藏");
        } else if (KeyboardEventListener.KEY_CODE_SWITCH_EN_TO_NUM == tag) {
            text.setText("123");
        } else if (KeyboardEventListener.KEY_CODE_SHIFT == tag) {
            text.setText("切换");
        } else if (KeyboardEventListener.KEY_CODE_DELETE == tag) {
            text.setText("删除");
        }
    }

    private void setKeyBackgroundAndFontColor(RoundedCornerTextView view, boolean isSelected) {
        int tag = (Integer) view.getTag();
        //功能按键的默认背景色跟其它键不一样
        if (isSelected) {
            view.setRoundedCornerBgColor(sColorSelectedKeyBg);
            view.setTextColor(sColorSelectedFont);
        } else {
            if (tag < 0) {
                view.setRoundedCornerBgColor(sColorDefaultFuncKeyBg);
            } else {
                view.setRoundedCornerBgColor(sColorDefaultKeyBg);
            }
            view.setTextColor(sColorDefaultFont);
        }
    }

    private void showKeyPreview(View text) {
        if (!mIsKeyPreviewAddedToWindow) {
            //计算出popupWindow的宽高和具体的弹出位置
            //计算公式跟预览视图的比例密切相关
            int[] location = new int[2];
            //location的坐标为View的左上角坐标
            text.getLocationOnScreen(location);

            WindowManager.LayoutParams wmParam = new WindowManager.LayoutParams(
                    WindowManager.LayoutParams.MATCH_PARENT,
                    WindowManager.LayoutParams.WRAP_CONTENT,
                    WindowManager.LayoutParams.TYPE_APPLICATION,
                    WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                            | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, // 没有边界
                    // 半透明效果
                    PixelFormat.TRANSLUCENT);
            //解决在小米3等手机上系统禁用system_alert_dialog的问题。
            if (!(mContext instanceof Activity)) {
                wmParam.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
            }
            wmParam.width = text.getWidth() * 5 / 3 + dp2Px(6);
            wmParam.height = text.getHeight() * 11 / 5;
            wmParam.x = location[0] - text.getWidth() / 3 - dp2Px(3);
            wmParam.y = location[1] - text.getHeight() * 16 / 10;
            wmParam.gravity = Gravity.LEFT | Gravity.TOP;
            mWindowManager.addView(mPreviewView, wmParam);
            mIsKeyPreviewAddedToWindow = true;
        }
    }

    private void dismissKeyPreview() {
        if (mIsKeyPreviewAddedToWindow) {
            mWindowManager.removeView(mPreviewView);
            mIsKeyPreviewAddedToWindow = false;
        }
    }

    private void initTagValue() {
        mQ.setTag((int) 'q');
        mW.setTag((int) 'w');
        mE.setTag((int) 'e');
        mR.setTag((int) 'r');
        mT.setTag((int) 't');
        mY.setTag((int) 'y');
        mU.setTag((int) 'u');
        mI.setTag((int) 'i');
        mO.setTag((int) 'o');
        mP.setTag((int) 'p');

        mA.setTag((int) 'a');
        mS.setTag((int) 's');
        mD.setTag((int) 'd');
        mF.setTag((int) 'f');
        mG.setTag((int) 'g');
        mH.setTag((int) 'h');
        mJ.setTag((int) 'j');
        mK.setTag((int) 'k');
        mL.setTag((int) 'l');

        mZ.setTag((int) 'z');
        mX.setTag((int) 'x');
        mC.setTag((int) 'c');
        mV.setTag((int) 'v');
        mB.setTag((int) 'b');
        mN.setTag((int) 'n');
        mM.setTag((int) 'm');

        mSpace.setTag(32);

        mDelete.setTag(KeyboardEventListener.KEY_CODE_DELETE);
        mHide.setTag(KeyboardEventListener.KEY_CODE_HIDE);
        m123.setTag(KeyboardEventListener.KEY_CODE_SWITCH_EN_TO_NUM);
        mConfirm.setTag(KeyboardEventListener.KEY_CODE_DONE);
        mShift.setTag(KeyboardEventListener.KEY_CODE_SHIFT);
    }

    private void shift(ViewGroup viewGroup) {
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View item = viewGroup.getChildAt(i);
            if (item instanceof ViewGroup) {
                shift((ViewGroup) item);
            } else if (item instanceof RoundedCornerTextView) {
                doShift(item);
            }
        }
    }

    private void doShift(View view) {
        if (null == view.getTag()) {
            return;
        }
        int ascii = (Integer) view.getTag();
        //如果是功能按键或者是空格，无需转换
        if (ascii < 0 || ascii == 32) {
            return;
        }
        RoundedCornerTextView rctv = (RoundedCornerTextView) view;
        //如果是小写就切换成大写，否则切换成小写
        if (mIsLowerCase) {
            rctv.setText(String.valueOf((char) (ascii - 32)));
            rctv.setTag(ascii - 32);
        } else {
            rctv.setTag(ascii + 32);
            rctv.setText(String.valueOf((char) (ascii + 32)));
        }
    }

    private int dp2Px(int dp) {
        return (int) ScreenUtil.dpToPx(mContext, dp);
    }

    public void setOnKeyboardActionListener(KeyboardEventListener actionListener) {
        mActionListener = actionListener;
    }

    @Override
    public void onClick(View v) {
        if (null == v.getTag()) {
            return;
        }
        int ascii = (Integer) v.getTag();
        if (KeyboardEventListener.KEY_CODE_SHIFT == ascii) {
            shift(mRootLayout);
            //切换完成，修改当前的大小写标识
            if (mIsLowerCase) {
                mIsLowerCase = false;
            } else {
                mIsLowerCase = true;
            }
            return;
        }
        if (null != mActionListener) {
            mActionListener.onKeyEvent(ascii);
        }
    }

    public void show() {
        mRootLayout.setVisibility(View.VISIBLE);
    }

    public void hide() {
        mRootLayout.setVisibility(View.GONE);
    }

    protected View getView() {
        return mRootLayout;
    }
}
