package com.thinkive.android.trade_bz.views;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.utils.ScreenUtils;

/**
 * 交易模块主页使用的LinearLayout，
 * 本类专为交易模块主页使用，可自由添加、移除一个功能小模块（一键归集、融资融券这种），
 * 也可以方便地增加一块空白区域
 * Announcements：本类设计完全依赖交易主页的设计图
 *
 * @author 王志鸿
 * @company Thinkive
 * @date 2015/8/14
 */
public class HomeLinearLayout extends LinearLayout {

    /**
     * 外部调用环境
     */
    private Activity mActivity;

    /**
     * 0：画长线；1：画短线
     */
    private short mAddLineState = 0;

    private int mItemHeightPx;

    public HomeLinearLayout(Activity activity) {
        super(activity);
        mActivity = activity;
    }

    public HomeLinearLayout(Activity activity, AttributeSet attrs) {
        super(activity, attrs);
        mActivity = activity;
        
    }

    /**
     * 初始化本类，设置本类基本属性，以及头部空隙
     */
    public void initHomeLinearLayout() {
        setOrientation(LinearLayout.VERTICAL);
        setBackgroundColor(getResources().getColor(R.color.trade_color_white));
        mAddLineState = 0;
        mItemHeightPx = (int)(ScreenUtils.getScreenWidth(mActivity)/9);
        View view = new View(mActivity);
        ViewGroup.LayoutParams params = new LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, (int)ScreenUtils.dpToPx(mActivity, 10));
        view.setLayoutParams(params);
        view.setBackgroundColor(getResources().getColor(R.color.trade_normal_background));
        addView(view);
    }

    /**
     * 增加一个小模块
     * @param textviewId 小模块模块名的文本控件id
     * @param textResId 小模块模块名的字符串资源id
     * @param leftDrawableResId 左侧图标图片资源id
     * @param clickListener 单击事件监听接口
     */
    public void addModuleItem(int textviewId, int textResId, int leftDrawableResId, OnClickListener clickListener) {
//        addLine();
//        mAddLineState = 1;
        TextView textView = (TextView)LayoutInflater.from(mActivity).
                inflate(R.layout.textview_home_moduleitem, null);
        LinearLayout.LayoutParams params = new LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, mItemHeightPx);
        params.gravity = Gravity.CENTER_VERTICAL;
        textView.setLayoutParams(params);
        textView.setText(textResId);
        textView.setBackgroundResource(R.drawable.selector_press_list_item2);
        Drawable rightDrawable = getResources().getDrawable(R.drawable.arrows_right_gray);
        Drawable leftDrawable = getResources().getDrawable(leftDrawableResId);
        if (rightDrawable != null && leftDrawable != null) {
            rightDrawable.setBounds(0, 0, rightDrawable.getMinimumWidth(), rightDrawable.getMinimumHeight());
            leftDrawable.setBounds(0, 0, leftDrawable.getMinimumWidth(), leftDrawable.getMinimumHeight());
            textView.setCompoundDrawables(leftDrawable, null, rightDrawable, null);
        }
        textView.setId(textviewId);
        textView.setOnClickListener(clickListener);
//        mTextViews.add(textView);
        addView(textView);
    }

    /**
     * 添加空隙，中断那一块
     */
    public void addBlank() {
//        mAddLineState = 0;
//        addLine();
        View view = new View(mActivity);
        ViewGroup.LayoutParams params = new LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, (int)ScreenUtils.dpToPx(mActivity, 10));
        view.setLayoutParams(params);
        view.setBackgroundColor(getResources().getColor(R.color.trade_normal_background));
        addView(view);
    }

    /**
     * 根据现在的情况，添加一条线。可能是长线，也可能是短线
     */
    private void addLine() {
        switch (mAddLineState) {
            case 0:
                addLongLine();
                break;
            case 1:
                addShortLine();
                break;
            default:
                addShortLine();
                break;
        }
    }

    /**
     * 添加一条长线。
     */
    private void addLongLine() {
        View view = new View(mActivity);
        ViewGroup.LayoutParams params = new LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, (int)ScreenUtils.dpToPx(mActivity, 1));
        view.setLayoutParams(params);
        view.setBackgroundColor(getResources().getColor(R.color.trade_divide_line));
        addView(view);
    }

    /**
     * 添加一条短线，就是只延伸到文字的那种
     */
   public void addShortLine() {
        View view = new View(mActivity);
        LinearLayout.LayoutParams params = new LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, (int)ScreenUtils.dpToPx(mActivity, 1));
        // 注意：这个方法单位为px
        params.setMargins((int)ScreenUtils.dpToPx(mActivity,50), 0, 0, 0);
        view.setLayoutParams(params);
        view.setBackgroundColor(getResources().getColor(R.color.trade_normal_background));
        addView(view);
    }
}
