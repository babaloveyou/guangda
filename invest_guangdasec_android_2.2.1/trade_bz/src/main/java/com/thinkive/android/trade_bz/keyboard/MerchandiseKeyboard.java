package com.thinkive.android.trade_bz.keyboard;

import android.content.Context;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.thinkive.framework.util.ResourceUtil;
import com.android.thinkive.framework.util.ScreenUtil;
import com.android.thinkive.framework.view.RoundedCornerImageView;
import com.android.thinkive.framework.view.RoundedCornerTextView;

/**
 * Created by liujianwei on 15/7/23.
 * 交易买卖键盘
 */
public class MerchandiseKeyboard extends BaseKeyboard implements View.OnClickListener {
    //外层布局
    private LinearLayout mRootLayout, mStockPrefixLayout, mNumberLayout;
    //数字键盘及功能按键布局
    private LinearLayout mNumLine1Layout, mNumLine2Layout, mNumLine3Layout, mNumLine4Layout;
    //全仓
    private RoundedCornerTextView mAllPosition;
    //半仓
    private RoundedCornerTextView mHalfPosition;
    //1/3仓
    private RoundedCornerTextView mOneThirdPosition;
    //1/4仓
    private RoundedCornerTextView mOneFourthPosition;
    //数字按键
    private RoundedCornerTextView m0, m1, m2, m3, m4, m5, m6, m7, m8, m9;
    //功能按键
    private RoundedCornerTextView mDot, mConfirm, mHide;
    //增量，减量
    private RoundedCornerTextView mIncrement, mDecrement;
    private RoundedCornerImageView mDelete;
    private Context mContext;
    //按键监听回调接口
    private KeyboardEventListener mActionListener;

    public MerchandiseKeyboard(Context context) {
        mContext = context;
        createLine1Layout();
        createLine2Layout();
        createLine3Layout();
        createLine4Layout();
        createStockPrefixLayout();

        layout();

        initDefaultTagValue();
        initDefaultText();
        setListenersAndStyles(mRootLayout);

    }

    @Override
    public void setTheme(short theme) {
        super.setTheme(theme);
        setListenersAndStyles(mRootLayout);
        mDot.setRoundedCornerBgColor(sColorDefaultKeyBg);
        mDecrement.setRoundedCornerBgColor(sColorDefaultKeyBg);
        mIncrement.setRoundedCornerBgColor(sColorDefaultKeyBg);
    }

    private void layout() {
        mRootLayout = new LinearLayout(mContext);
        mRootLayout.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, dp2Px(KeyboardManager.KEYBOARD_HEIGHT)
        ));
        mRootLayout.setOrientation(LinearLayout.HORIZONTAL);
        mRootLayout.setBackgroundColor(sColorKeyboardBg);
        mRootLayout.setPadding(2, 2, 2, 2);
        mNumberLayout = new LinearLayout(mContext);
        mNumberLayout.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
        ));
        mNumberLayout.setOrientation(LinearLayout.VERTICAL);

        LinearLayout.LayoutParams lpNumChild = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
        );
        lpNumChild.weight = 1;
        mNumberLayout.addView(mNumLine1Layout, lpNumChild);
        mNumberLayout.addView(mNumLine2Layout, lpNumChild);
        mNumberLayout.addView(mNumLine3Layout, lpNumChild);
        lpNumChild.bottomMargin = 0;
        mNumberLayout.addView(mNumLine4Layout, lpNumChild);

        LinearLayout.LayoutParams lpStockLayout = new LinearLayout.LayoutParams(
                dp2Px(70), dp2Px(KeyboardManager.KEYBOARD_HEIGHT)
        );
        mRootLayout.addView(mStockPrefixLayout, lpStockLayout);
        mRootLayout.addView(mNumberLayout, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, dp2Px(KeyboardManager.KEYBOARD_HEIGHT)
        ));
    }

    private void createStockPrefixLayout() {
        mStockPrefixLayout = new LinearLayout(mContext);
        mStockPrefixLayout.setLayoutParams(new ViewGroup.LayoutParams(
                dp2Px(70), ViewGroup.LayoutParams.MATCH_PARENT
        ));
        mStockPrefixLayout.setOrientation(LinearLayout.VERTICAL);

        LinearLayout.LayoutParams lpLastChild = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 0
        );
        lpLastChild.weight = 1;
        LinearLayout.LayoutParams lpChild = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 0
        );
        lpChild.weight = 1;
        lpChild.bottomMargin = dp2Px(1);

        mAllPosition = new RoundedCornerTextView(mContext);
        mHalfPosition = new RoundedCornerTextView(mContext);
        mOneThirdPosition = new RoundedCornerTextView(mContext);
        mOneFourthPosition = new RoundedCornerTextView(mContext);

        mOneFourthPosition.setLayoutParams(lpChild);
        mOneThirdPosition.setLayoutParams(lpChild);
        mHalfPosition.setLayoutParams(lpChild);
        mAllPosition.setLayoutParams(lpChild);

        mStockPrefixLayout.addView(mOneFourthPosition);
        mStockPrefixLayout.addView(mOneThirdPosition);
        mStockPrefixLayout.addView(mHalfPosition);
        mStockPrefixLayout.addView(mAllPosition);

    }

    private void createLine1Layout() {
        mNumLine1Layout = new LinearLayout(mContext);
        mNumLine1Layout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT
        ));
        mNumLine1Layout.setOrientation(LinearLayout.HORIZONTAL);
        mNumLine1Layout.setPadding(0, 0, 0, dp2Px(1));
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                0, LinearLayout.LayoutParams.MATCH_PARENT
        );
        params.weight = 1;
        params.leftMargin = dp2Px(1);

        m1 = new RoundedCornerTextView(mContext);
        m2 = new RoundedCornerTextView(mContext);
        m3 = new RoundedCornerTextView(mContext);
        mDelete = new RoundedCornerImageView(mContext);

        m1.setLayoutParams(params);
        m2.setLayoutParams(params);
        m3.setLayoutParams(params);
        mDelete.setLayoutParams(params);

        mNumLine1Layout.addView(m1);
        mNumLine1Layout.addView(m2);
        mNumLine1Layout.addView(m3);
        mNumLine1Layout.addView(mDelete);
    }

    private void createLine2Layout() {
        mNumLine2Layout = new LinearLayout(mContext);
        mNumLine2Layout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT
        ));
        mNumLine2Layout.setOrientation(LinearLayout.HORIZONTAL);
        mNumLine2Layout.setPadding(0, 0, 0, dp2Px(1));
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                0, LinearLayout.LayoutParams.MATCH_PARENT
        );
        params.weight = 1;
        params.leftMargin = dp2Px(1);

        m4 = new RoundedCornerTextView(mContext);
        m5 = new RoundedCornerTextView(mContext);
        m6 = new RoundedCornerTextView(mContext);
        mIncrement = new RoundedCornerTextView(mContext);

        m4.setLayoutParams(params);
        m5.setLayoutParams(params);
        m6.setLayoutParams(params);
        mIncrement.setLayoutParams(params);

        mNumLine2Layout.addView(m4);
        mNumLine2Layout.addView(m5);
        mNumLine2Layout.addView(m6);
        mNumLine2Layout.addView(mIncrement);
    }

    private void createLine3Layout() {
        mNumLine3Layout = new LinearLayout(mContext);
        mNumLine3Layout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT
        ));
        mNumLine3Layout.setOrientation(LinearLayout.HORIZONTAL);
        mNumLine3Layout.setPadding(0, 0, 0, dp2Px(1));
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                0, LinearLayout.LayoutParams.MATCH_PARENT
        );
        params.weight = 1;
        params.leftMargin = dp2Px(1);

        m7 = new RoundedCornerTextView(mContext);
        m8 = new RoundedCornerTextView(mContext);
        m9 = new RoundedCornerTextView(mContext);
        mDecrement = new RoundedCornerTextView(mContext);


        m7.setLayoutParams(params);
        m8.setLayoutParams(params);
        m9.setLayoutParams(params);
        mDecrement.setLayoutParams(params);

        mNumLine3Layout.addView(m7);
        mNumLine3Layout.addView(m8);
        mNumLine3Layout.addView(m9);
        mNumLine3Layout.addView(mDecrement);
    }

    private void createLine4Layout() {
        mNumLine4Layout = new LinearLayout(mContext);
        mNumLine4Layout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT
        ));
        mNumLine4Layout.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                0, LinearLayout.LayoutParams.MATCH_PARENT
        );
        params.weight = 1;
        params.leftMargin = dp2Px(1);

        mDot = new RoundedCornerTextView(mContext);
        m0 = new RoundedCornerTextView(mContext);
        mHide = new RoundedCornerTextView(mContext);
        mConfirm = new RoundedCornerTextView(mContext);

        mDot.setLayoutParams(params);
        m0.setLayoutParams(params);
        mHide.setLayoutParams(params);

        params = new LinearLayout.LayoutParams(
                0, LinearLayout.LayoutParams.MATCH_PARENT
        );
        params.weight = 1;
        params.leftMargin = dp2Px(1);
        mConfirm.setLayoutParams(params);
//        mConfirm.getPaint().setFakeBoldText(true);  //设置粗体

        mNumLine4Layout.addView(mDot);
        mNumLine4Layout.addView(m0);
        mNumLine4Layout.addView(mConfirm);
        mNumLine4Layout.addView(mHide);
    }

    private void setListenersAndStyles(ViewGroup viewGroup) {
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View item = viewGroup.getChildAt(i);
            if (item instanceof ViewGroup) {
                setListenersAndStyles((ViewGroup) item);
            } else if (item instanceof RoundedCornerTextView) {
                setRCTV(item);
            } else if (item instanceof RoundedCornerImageView) {
                initImageKey((RoundedCornerImageView) item);
            }
        }
    }

    private void initImageKey(final RoundedCornerImageView view) {
        view.setImageResource(
                ResourceUtil.getResourceID(mContext, DRAWABLE, sResNameDeleteSmall));
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                onClick(v);
            }
        });
//        view.setCornerRadius(dp2Px(1));
        view.setRoundedCornerBgColor(sColorDefaultFuncKeyBg);
        view.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        view.setLongClickable(true);
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
                    view.setImageResource(
                            ResourceUtil.getResourceID(
                                    mContext,
                                    DRAWABLE,
                                    sResNameDeleteSmall));
                    view.setRoundedCornerBgColor(sColorDefaultFuncKeyBg);
                }
                if (MotionEvent.ACTION_UP == event.getAction()) {
                    onClick(v);
                }

                return true;
            }
        });
    }

    private void setRCTV(View view) {
        final RoundedCornerTextView rctv = (RoundedCornerTextView) view;
        rctv.setGravity(Gravity.CENTER);
        rctv.setTextSize(20);
        rctv.setTextColor(sColorDefaultFont);
//        rctv.setCornerRadius(dp2Px(1));
        setKeyBackgroundAndFontColor(rctv, false);

        rctv.setLongClickable(true);
        rctv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEvent.ACTION_DOWN == event.getAction()) {
                    setKeyBackgroundAndFontColor(((RoundedCornerTextView) v), true);
                } else if (MotionEvent.ACTION_MOVE != event.getAction()) {
                    setKeyBackgroundAndFontColor(((RoundedCornerTextView) v), false);
                }
                if (MotionEvent.ACTION_UP == event.getAction()) {
                    onClick(v);
                }
                return true;
            }
        });
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
                if(tag == KeyboardEventListener.KEY_CODE_INCREMENT || tag == KeyboardEventListener.KEY_CODE_DECREMENT){
                    view.setRoundedCornerBgColor(sColorDefaultKeyBg);
                }
            } else {
                view.setRoundedCornerBgColor(sColorDefaultKeyBg);
            }
            view.setTextColor(sColorDefaultFont);
        }
    }

    private void initDefaultTagValue() {
        m0.setTag(48);
        m1.setTag(49);
        m2.setTag(50);
        m3.setTag(51);
        m4.setTag(52);
        m5.setTag(53);
        m6.setTag(54);
        m7.setTag(55);
        m8.setTag(56);
        m9.setTag(57);

        mOneFourthPosition.setTag(KeyboardEventListener.KEY_CODE_ONE_FOURTH_POSTION);
        mOneThirdPosition.setTag(KeyboardEventListener.KEY_CODE_ONE_THIRD_POSTION);
        mHalfPosition.setTag(KeyboardEventListener.KEY_CODE_HALF_POSTION);
        mAllPosition.setTag(KeyboardEventListener.KEY_CODE_ALL_POSTION);

        mDot.setTag(KeyboardEventListener.KEY_CODE_POINT);
        mIncrement.setTag(KeyboardEventListener.KEY_CODE_INCREMENT);
        mDecrement.setTag(KeyboardEventListener.KEY_CODE_DECREMENT);
        mConfirm.setTag(KeyboardEventListener.KEY_CODE_DONE);
        mHide.setTag(KeyboardEventListener.KEY_CODE_HIDE);
        mDelete.setTag(KeyboardEventListener.KEY_CODE_DELETE);
    }

    /**
     * 设置默认文本
     */
    private void initDefaultText() {
        m0.setText("0");
        m1.setText("1");
        m2.setText("2");
        m3.setText("3");
        m4.setText("4");
        m5.setText("5");
        m6.setText("6");
        m7.setText("7");
        m8.setText("8");
        m9.setText("9");

        mOneFourthPosition.setText("1/4仓");
        mOneThirdPosition.setText("1/3仓");
        mHalfPosition.setText("半仓");
        mAllPosition.setText("全仓");

        mDot.setText(".");
        mConfirm.setText("确 定");
        mIncrement.setText("+100");
        mDecrement.setText("-100");
        mHide.setText("隐藏");
//        mDelete.setText("删除");
    }

    private int dp2Px(int dp) {
        return (int) ScreenUtil.dpToPx(mContext, dp);
    }

    protected void hide() {
        mRootLayout.setVisibility(View.GONE);
    }

    protected void show() {
        mRootLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View v) {
        if (null == v.getTag()) {
            return;
        }
        if (null != mActionListener) {
            mActionListener.onKeyEvent((Integer) v.getTag());
        }
    }

    protected LinearLayout getView() {
        return mRootLayout;
    }

    public void setOnKeyboardActionListener(KeyboardEventListener actionListener) {
        mActionListener = actionListener;
    }
}
