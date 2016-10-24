package com.android.thinkive.invest_sd.widget;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.thinkive.framework.theme.ThemeManager;
import com.android.thinkive.framework.util.Log;
import com.android.thinkive.invest_sd.R;

/**
 *
 * 底部导航栏
 *
 * Created by zhuduanchang on 2015/6/23.
 */
@Deprecated
public class IndicatorView extends LinearLayout implements View.OnClickListener{

    private ViewPager mViewpager;
    private int childCount = 0;
    private Context mContext;
    private int textColorSelected = R.color.primary_text_color;
    private int textColorNormal = R.color.secondary_text_color;
    private int lastposition = 0;
    private final int DRAWABLE_PANDING = 2;
    private float textSize = 13;
    //int eachWidth = 0;
    int windowWidth;
    private OnItemClickListener mOnItemClickListener;
    int[] normalIds;
    int[] selectedIds;

    public IndicatorView(Context context) {
        super(context, null);
        init();
        this.mContext = context;
    }

    public IndicatorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        this.mContext = context;
        setBackgroundColor(ThemeManager.getThemeColor(context, R.color.indicator_background));
    }


    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }
    public interface OnItemClickListener{
         void onClick(int index);
    }


    @Override
    public void onClick(View v) {
        int count = getChildCount();
        for(int i=0; i<count; i++){
            if(v==getChildAt(i)){
                if(lastposition != i){
                    mOnItemClickListener.onClick(i);
                }
                return;
            }
        }
    }

    private void init(){
        DisplayMetrics metric = new DisplayMetrics();
        ((WindowManager)getContext().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(metric);
        windowWidth = metric.widthPixels;
    }

    /**
     * 初始化菜单的资源
     *
     * @param textIds
     * @param normalIds
     * @param selectedIds
     */
    public void initTitleAndIcons(ViewPager viewPager,int[] textIds,int[] normalIds,int[] selectedIds){
        PagerAdapter adapter = viewPager.getAdapter();

        if(adapter == null){
            Log.e("adapter is null!!");
            return;
        }

        if(adapter.getCount() > textIds.length || adapter.getCount() > normalIds.length
                ||adapter.getCount() > selectedIds.length){
            Log.e("resids error !!");
            return;
        }
        this.mViewpager =viewPager;
        this.normalIds = normalIds;
        this.selectedIds = selectedIds;
        childCount = adapter.getCount();
        for(int i=0; i<childCount;i++){
            TextView textview = new TextView(mContext);
            textview.setGravity(Gravity.CENTER);
            textview.setText(textIds[i]);
            textview.setOnClickListener(this);
            textview.setTextSize(textSize);
            textview.setTextColor(ThemeManager.getThemeColor(mContext, lastposition != i ? textColorNormal : textColorSelected));
            textview.setPaddingRelative(0,20,0,0);
            //textview.setCompoundDrawablePadding(DRAWABLE_PANDING);
            textview.setCompoundDrawables(null, getSelectedDrawable(lastposition==i,normalIds[i],selectedIds[i]), null, null);
            LayoutParams mLayoutParams = new LayoutParams(0, LayoutParams.MATCH_PARENT, 1);
            mLayoutParams.gravity = Gravity.CENTER;
            addView(textview, mLayoutParams);
        }
    }

    public void onPageScrolled(int position){


        //eachWidth = windowWidth/childCount;
        if(childCount>0){
            setItemsChange(position);
        }
    }

    private void setItemsChange(int realPosition){
        if(lastposition!=realPosition){
            TextView mTextView = (TextView)getChildAt(lastposition);
            mTextView.setTextColor(ThemeManager.getThemeColor(mContext, textColorNormal));

            mTextView.setCompoundDrawables(null, getSelectedDrawable(false,normalIds[lastposition],selectedIds[lastposition]), null, null);

            lastposition = realPosition;
            mTextView = (TextView)getChildAt(lastposition);
            mTextView.setTextColor(ThemeManager.getThemeColor(mContext, textColorSelected));

            mTextView.setCompoundDrawables(null, getSelectedDrawable(true,normalIds[lastposition],selectedIds[lastposition]),  null, null);

        }
    }

    private Drawable getSelectedDrawable(boolean select,int normalId,int selectedId) {
        int size = (int) getResources().getDimension(R.dimen.title_action_icon_size);
        Drawable mDrawable = ThemeManager.getNormalDrawable(mContext,select ? selectedId : normalId,ThemeManager.SUFFIX_PNG);
        //getResources().getDrawable(select ? selectedId : normalId);
        mDrawable.setBounds(new Rect(0, 0, size, size));
        return mDrawable;
    }

    public void setTheme(){
        setBackgroundColor(ThemeManager.getThemeColor(mContext, R.color.indicator_background));
        for(int i=0; i<childCount;i++){
            TextView mTextView = (TextView)getChildAt(i);
            mTextView.setTextColor(ThemeManager.getThemeColor(mContext,lastposition != i ? textColorNormal : textColorSelected));

            mTextView.setCompoundDrawables(null, getSelectedDrawable(lastposition == i,normalIds[i],selectedIds[i]), null, null);

        }
    }
}
