package com.android.thinkive.invest_sd.widget;

import android.content.Context;
import android.os.Build;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.thinkive.framework.theme.ThemeManager;
import com.android.thinkive.framework.util.Log;
import com.android.thinkive.invest_sd.R;

/**
 *
 * 可滑动的底部导航栏
 *
 * 默认@SCREEN_COUNT = 5, 即5个或者5个以下的item 则不滑动，5个以上的item支持滑动。
 *
 * Created by zhuduanchang on 2015/7/16.
 */
public class IndicatorScrollView extends HorizontalScrollView{

    private LayoutInflater mInflater;

    private Context mContext;

    private int mScreenWitdh;

    private int mChildWidth;

    private int mChildHeight;

    private int mChildCount;

    private int mChildCountnew;

    private int lastposition = 0;

    //默认底部5个不滑动  可修改
    private static final int SCREEN_COUNT = 6;

    private OnItemClickListener mOnClickListener;

    private ViewPager mViewpager;

    private LinearLayout mLayout;

    private int[] normalIds,selectedIds;

    private int textColorSelected = R.color.primary_text_color;
    private int textColorNormal = R.color.secondary_text_color;

    public IndicatorScrollView(Context context) {
        super(context, null);
    }

    public IndicatorScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        this.mInflater = LayoutInflater.from(mContext);
        mLayout = new LinearLayout(mContext);
        // 获得屏幕宽度
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        mScreenWitdh = outMetrics.widthPixels;
    }

    public interface OnItemClickListener {
        void onClick(int pos);
    }

    /**
     * 初始化菜单的资源
     *
     * @param textIds
     * @param normalIds
     * @param selectedIds
     */
    public void initTitleAndIcons(ViewPager viewPager,int[] textIds,int[] normalIds,int[] selectedIds) {
        PagerAdapter adapter = viewPager.getAdapter();

        if (adapter == null) {
            Log.e("adapter is null!!");
            return;
        }

//        if (adapter.getCount()==0 ||adapter.getCount() > textIds.length || adapter.getCount() > normalIds.length
//                || adapter.getCount() > selectedIds.length) {
//            Log.e("resids error !!");
        if (adapter.getCount()==0){
            return;
        }
        this.mChildCount = adapter.getCount();
        mChildCountnew =textIds.length;
        this.mChildWidth = textIds.length<=SCREEN_COUNT? mScreenWitdh/textIds.length : mScreenWitdh/SCREEN_COUNT;
        this.mViewpager =viewPager;
        this.normalIds = normalIds;
        this.selectedIds = selectedIds;

        for(int i = 0;i<textIds.length;i++){
            final int index = i;
            View view = mInflater.inflate(R.layout.indicator_scroll_item, null);
            ImageView imageView = (ImageView)view.findViewById(R.id.item_image);
            TextView textView = (TextView)view.findViewById(R.id.item_text);
            textView.setText(textIds[i]);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                imageView.setBackground(ThemeManager.getNormalDrawable(mContext,lastposition != i ? normalIds[i]: selectedIds[i],ThemeManager.SUFFIX_PNG));
            } else {
                imageView.setBackgroundDrawable(ThemeManager.getNormalDrawable(mContext, lastposition != i ? normalIds[i] : selectedIds[i], ThemeManager.SUFFIX_PNG));
            }
            textView.setTextColor(ThemeManager.getThemeColor(mContext, lastposition != i ? textColorNormal : textColorSelected));
            LinearLayout.LayoutParams mLayoutParams = new LinearLayout.LayoutParams(mChildWidth,LayoutParams.MATCH_PARENT);
            mLayoutParams.gravity = Gravity.CENTER;
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnClickListener != null)
                    {
                        mOnClickListener.onClick(index);
                    }
                }
            });
            mLayout.addView(view, mLayoutParams);
        }
        addView(mLayout);
    }


    public void setOnItemClickListener(OnItemClickListener mOnClickListener)
        {
        this.mOnClickListener = mOnClickListener;
    }

    public void onPageScrolled(int position){

        if(mChildCount>0){
            setItemsChange(position);
        }
    }

    private void setItemsChange(int realPosition){
        if(realPosition>mChildCountnew-1){
            return;
        }
        if(lastposition!=realPosition){
            View view = (View)mLayout.getChildAt(lastposition);
            ImageView imageView = (ImageView)view.findViewById(R.id.item_image);
            TextView textView = (TextView)view.findViewById(R.id.item_text);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                imageView.setBackground(ThemeManager.getNormalDrawable(mContext, normalIds[lastposition], ThemeManager.SUFFIX_PNG));
            } else {
                imageView.setBackgroundDrawable(ThemeManager.getNormalDrawable(mContext, normalIds[lastposition], ThemeManager.SUFFIX_PNG));
            }
            textView.setTextColor(ThemeManager.getThemeColor(mContext, textColorNormal));

            lastposition = realPosition;
            view = (View)mLayout.getChildAt(lastposition);
            imageView = (ImageView)view.findViewById(R.id.item_image);
            textView = (TextView)view.findViewById(R.id.item_text);
            textView.setTextColor(ThemeManager.getThemeColor(mContext, textColorSelected));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                imageView.setBackground(ThemeManager.getNormalDrawable(mContext, selectedIds[lastposition], ThemeManager.SUFFIX_PNG));
            } else {
                imageView.setBackgroundDrawable(ThemeManager.getNormalDrawable(mContext, selectedIds[lastposition], ThemeManager.SUFFIX_PNG));
            }

        }
    }

    public void setTheme(){
        setBackgroundColor(ThemeManager.getThemeColor(mContext, R.color.indicator_background));
        for(int i=0; i<mChildCountnew;i++){
            View view = (View)mLayout.getChildAt(i);
            ImageView imageView = (ImageView)view.findViewById(R.id.item_image);
            TextView textView = (TextView)view.findViewById(R.id.item_text);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                imageView.setBackground(ThemeManager.getNormalDrawable(mContext,lastposition != i ? normalIds[i]: selectedIds[i],ThemeManager.SUFFIX_PNG));
            } else {
                imageView.setBackgroundDrawable(ThemeManager.getNormalDrawable(mContext,lastposition != i ? normalIds[i]: selectedIds[i],ThemeManager.SUFFIX_PNG));
            }
            textView.setTextColor(ThemeManager.getThemeColor(mContext, lastposition != i ? textColorNormal : textColorSelected));
        }
    }

}
