package com.thinkive.android.trade_bz.keyboard;

import android.content.Context;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.thinkive.framework.util.RandomUtil;
import com.android.thinkive.framework.util.ResourceUtil;
import com.android.thinkive.framework.util.ScreenUtil;
import com.android.thinkive.framework.view.RoundedCornerImageView;
import com.android.thinkive.framework.view.RoundedCornerTextView;

/**
 * 描述：随机数字键盘
 * 详细：只能输入数字字符，适用于输入纯数字输入场景
 *
 * Created by zhuduanchang
 */
public class DigitalKeyboard extends BaseKeyboard implements View.OnClickListener {
    private LinearLayout mNumberLayout;
    //数字键盘及功能按键布局
    private LinearLayout mNumLine1Layout, mNumLine2Layout, mNumLine3Layout,mNumLine4Layout;
    //数字按键
    private RoundedCornerTextView m0, m1, m2, m3, m4, m5, m6, m7, m8, m9;
    //功能按键
    private RoundedCornerTextView mConfirm;
    private RoundedCornerTextView mDelete;
    private RoundedCornerTextView mHide;
    private RoundedCornerTextView mClear;
    private RoundedCornerTextView mDot;
    private Context mContext;
    //按键监听回调接口
    private KeyboardEventListener mActionListener;
    //标识每个按键字符的Tag值，使用ASCII
    private int[] mASCIITagValue = new int[] {
      48, 49, 50, 51, 52, 53, 54, 55, 56, 57
    };
    private boolean mIsRandom = false;  //是否对键位进行随机

    public DigitalKeyboard(Context context) {
        mContext = context;
        init();
    }

    public DigitalKeyboard(Context context, boolean isRandom) {
        mContext = context;
        mIsRandom = isRandom;
        init();
    }

    @Override
    public void setTheme(short theme) {
        super.setTheme(theme);
        setListenersAndOthers(mNumberLayout);
    }

    private void init() {
        createLine1Layout();
        createLine2Layout();
        createLine3Layout();
        createLine4Layout();
        layout();

        if (mIsRandom) {
            //对按键顺序进行洗牌
            RandomUtil.shuffle(mASCIITagValue);
        }
        initDefaultTagValue();
        setListenersAndOthers(mNumberLayout);
    }

    private void layout() {
        mNumberLayout = new LinearLayout(mContext);
        mNumberLayout.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                (int) ScreenUtil.dpToPx(mContext, KeyboardManager.KEYBOARD_HEIGHT)
        ));
        mNumberLayout.setOrientation(LinearLayout.VERTICAL);
        mNumberLayout.setBackgroundColor(sColorKeyboardBg);
        mNumberLayout.setPadding(2, 2, 2, 2);

        LinearLayout.LayoutParams lpNumChild = new LinearLayout.LayoutParams(
          ViewGroup.LayoutParams.MATCH_PARENT, 0
        );
        lpNumChild.weight = 1;
        mNumberLayout.addView(mNumLine1Layout, lpNumChild);
        mNumberLayout.addView(mNumLine2Layout, lpNumChild);
        mNumberLayout.addView(mNumLine3Layout, lpNumChild);
        mNumberLayout.addView(mNumLine4Layout, lpNumChild);
    }


    private void initDefaultTagValue() {
        m0.setTag(mASCIITagValue[0]);
        m1.setTag(mASCIITagValue[1]);
        m2.setTag(mASCIITagValue[2]);
        m3.setTag(mASCIITagValue[3]);
        m4.setTag(mASCIITagValue[4]);
        m5.setTag(mASCIITagValue[5]);
        m6.setTag(mASCIITagValue[6]);
        m7.setTag(mASCIITagValue[7]);
        m8.setTag(mASCIITagValue[8]);
        m9.setTag(mASCIITagValue[9]);

        mConfirm.setTag(-3);
        mDelete.setTag(-5);
        mHide.setTag(-4);
        mDot.setTag(KeyboardEventListener.KEY_CODE_POINT);
        mClear.setTag(KeyboardEventListener.KEY_CODE_CLEAR);

    }

    /**
     * 设置监听器、样式、按钮文字
     *
     * @param viewGroup
     */
    private void setListenersAndOthers(ViewGroup viewGroup) {
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View item = viewGroup.getChildAt(i);
            if (item instanceof ViewGroup) {
                setListenersAndOthers((ViewGroup) item);
            } else if (item instanceof RoundedCornerTextView) {
                final RoundedCornerTextView rctv = (RoundedCornerTextView) item;
                rctv.setGravity(Gravity.CENTER);
                rctv.setTextSize(20);
                rctv.setTextColor(sColorDefaultFont);
                rctv.setCornerRadius(dp2Px(1));
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

                //设置文字
                int tag = Integer.valueOf(rctv.getTag().toString());
                if (tag > 0) {
                    rctv.setText(String.valueOf((char) tag));
                } else if (KeyboardEventListener.KEY_CODE_DONE == tag) {
                    rctv.setText("确定");

                } else if (KeyboardEventListener.KEY_CODE_DELETE == tag){
                    rctv.setText("退格");
                } else if (KeyboardEventListener.KEY_CODE_CLEAR == tag){
                    rctv.setText("清空");
                } else if (KeyboardEventListener.KEY_CODE_POINT == tag){
                    rctv.setText(".");
                } else if (KeyboardEventListener.KEY_CODE_HIDE == tag){
                    rctv.setText("隐藏");
                }
            } /*else if (item instanceof RoundedCornerImageView) {
                initImageKey((RoundedCornerImageView) item);
            }*/
        }//end for
    }

    private void initImageKey(final RoundedCornerImageView view) {
        view.setImageResource(
          ResourceUtil.getResourceID(mContext, DRAWABLE, sResNameDeleteBig));
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                onClick(v);
            }
        });
        view.setCornerRadius(dp2Px(1));
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
                        RES_NAME_DELETE_BIG_WHITE));
                } else if (MotionEvent.ACTION_MOVE != event.getAction()) {
                    view.setImageResource(
                            ResourceUtil.getResourceID(
                        mContext,
                        DRAWABLE,
                        sResNameDeleteBig));
                    view.setRoundedCornerBgColor(sColorDefaultFuncKeyBg);
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
            } else {
                view.setRoundedCornerBgColor(sColorDefaultKeyBg);
            }
            view.setTextColor(sColorDefaultFont);
        }
    }

    private void createLine1Layout() {
        mNumLine1Layout = new LinearLayout(mContext);
        mNumLine1Layout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT
        ));
        mNumLine1Layout.setOrientation(LinearLayout.HORIZONTAL);
        mNumLine1Layout.setPadding(0, 0, 0, dp2Px(2));

        LinearLayout.LayoutParams lpFirstChild = new LinearLayout.LayoutParams(
          0, LinearLayout.LayoutParams.MATCH_PARENT
        );
        lpFirstChild.weight = 1;
        LinearLayout.LayoutParams lpChild = new LinearLayout.LayoutParams(
          0, LinearLayout.LayoutParams.MATCH_PARENT
        );
        lpChild.weight = 1;
        lpChild.leftMargin = dp2Px(2);

        m1 = new RoundedCornerTextView(mContext);
        m2 = new RoundedCornerTextView(mContext);
        m3 = new RoundedCornerTextView(mContext);
        //m0 = new RoundedCornerTextView(mContext);
        mDelete = new RoundedCornerTextView(mContext);


        m1.setLayoutParams(lpFirstChild);
        m2.setLayoutParams(lpChild);
        m3.setLayoutParams(lpChild);
        mDelete.setLayoutParams(lpChild);

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
        mNumLine2Layout.setPadding(0, 0, 0, dp2Px(2));

        LinearLayout.LayoutParams lpFirstChild = new LinearLayout.LayoutParams(
          0, LinearLayout.LayoutParams.MATCH_PARENT
        );
        lpFirstChild.weight = 1;
        LinearLayout.LayoutParams lpChild = new LinearLayout.LayoutParams(
          0, LinearLayout.LayoutParams.MATCH_PARENT
        );
        lpChild.weight = 1;
        lpChild.leftMargin = dp2Px(2);

        m4 = new RoundedCornerTextView(mContext);
        m5 = new RoundedCornerTextView(mContext);
        m6 = new RoundedCornerTextView(mContext);
        mClear = new RoundedCornerTextView(mContext);

        m4.setLayoutParams(lpFirstChild);
        m5.setLayoutParams(lpChild);
        m6.setLayoutParams(lpChild);
        mClear.setLayoutParams(lpChild);
//        mDelete.getPaint().setFakeBoldText(true);  //设置粗体

        mNumLine2Layout.addView(m4);
        mNumLine2Layout.addView(m5);
        mNumLine2Layout.addView(m6);
        mNumLine2Layout.addView(mClear);
    }

    private void createLine3Layout() {
        mNumLine3Layout = new LinearLayout(mContext);
        mNumLine3Layout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT
        ));
        mNumLine3Layout.setOrientation(LinearLayout.HORIZONTAL);
        mNumLine3Layout.setPadding(0, 0, 0, dp2Px(2));

        LinearLayout.LayoutParams lpFirstChild = new LinearLayout.LayoutParams(
          0, LinearLayout.LayoutParams.MATCH_PARENT
        );
        lpFirstChild.weight = 1;
        LinearLayout.LayoutParams lpChild = new LinearLayout.LayoutParams(
          0, LinearLayout.LayoutParams.MATCH_PARENT
        );
        lpChild.weight = 1;
        lpChild.leftMargin = dp2Px(2);

        m7 = new RoundedCornerTextView(mContext);
        m8 = new RoundedCornerTextView(mContext);
        m9 = new RoundedCornerTextView(mContext);
        mHide = new RoundedCornerTextView(mContext);

        m7.setLayoutParams(lpFirstChild);
        m8.setLayoutParams(lpChild);
        m9.setLayoutParams(lpChild);
        mHide.setLayoutParams(lpChild);
//        mConfirm.getPaint().setFakeBoldText(true);  //设置粗体

        mNumLine3Layout.addView(m7);
        mNumLine3Layout.addView(m8);
        mNumLine3Layout.addView(m9);
        mNumLine3Layout.addView(mHide);
    }

    private void createLine4Layout() {
        mNumLine4Layout = new LinearLayout(mContext);
        mNumLine4Layout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT
        ));
        mNumLine4Layout.setOrientation(LinearLayout.HORIZONTAL);

        LinearLayout.LayoutParams lpFirstChild = new LinearLayout.LayoutParams(
                0, LinearLayout.LayoutParams.MATCH_PARENT
        );
        lpFirstChild.weight = 1;
        LinearLayout.LayoutParams lpChild = new LinearLayout.LayoutParams(
                0, LinearLayout.LayoutParams.MATCH_PARENT
        );
        lpChild.weight = 1;
        lpChild.leftMargin = dp2Px(2);

        LinearLayout.LayoutParams comfirmChild = new LinearLayout.LayoutParams(
                0, LinearLayout.LayoutParams.MATCH_PARENT
        );
        comfirmChild.weight = 2;
        comfirmChild.leftMargin = dp2Px(2);

        mDot = new RoundedCornerTextView(mContext);
        m0 = new RoundedCornerTextView(mContext);
        mConfirm = new RoundedCornerTextView(mContext);

        mDot.setLayoutParams(lpFirstChild);
        m0.setLayoutParams(lpChild);
        mConfirm.setLayoutParams(comfirmChild);
//        mConfirm.getPaint().setFakeBoldText(true);  //设置粗体

        mNumLine4Layout.addView(mDot);
        mNumLine4Layout.addView(m0);
        mNumLine4Layout.addView(mConfirm);
    }

    private int dp2Px(int dp) {
        return (int) ScreenUtil.dpToPx(mContext, dp);
    }

    public void setOnKeyboardActionListener(KeyboardEventListener actionListener) {
        mActionListener = actionListener;
    }

    public void show() {
        mNumberLayout.setVisibility(View.VISIBLE);
    }

    public void hide() {
        mNumberLayout.setVisibility(View.GONE);
    }

    protected View getView() {
        return mNumberLayout;
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
}
