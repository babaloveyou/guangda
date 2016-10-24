package com.thinkive.android.trade_bz.views;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 描述：滚动条导航栏
 * @author xuhao
 * @version 1.0
 * @corporation 深圳市思迪信息科技有限公司
 * @date 2015-02-04
 */
public class NavigatorView extends HorizontalScrollView {
    //根布局
    private LinearLayout mRootLayout = null;
    //标签列表
    private List<Tab> tabList = new ArrayList<Tab>();
    //导航栏上下文
    private Context barContext = null;
    //标签间隙
    private int tabSpace = 0;
    //自动修复标签间隙
    private boolean isAutoFixSpace = false;
    //导航栏的宽度
    private int barViewWidth = 0;
    //高亮画笔
    private Paint lightPaint = new Paint();
    /**
     * 底部线条画笔
     */
    private Paint mBottomLinePaint = new Paint();
    //当前高亮的标签下标
    private int lightTabIndex = -1;
    //默认的高亮颜色值
    private int lightTabTextColor = Color.BLACK;
    //默认的字体颜色值
    private int normalTabTextColor = Color.BLACK;
    //默认的高亮标签背景色
    private int lightTabBGColor = Color.WHITE;
    /**
     * 底部线条的颜色
     */
    private int nurmalTabBGColor = Color.LTGRAY;
    //点击事件
    private OnTabClickListener onTabClickListener = null;
    //高亮切换事件
    private OnTabLightChangeListener onTabLightChangeListener = null;
    //标签高亮是否改变
    private boolean isTabLightChanged = false;
    //高亮滑块的高度
    private float lightHeight = 0f;
    //高亮滑块的宽度
    private float lightWidth = 0f;
    //高亮滑块的坐标位置
    private PointF lightPoint = new PointF();
    //导航条背景色渐变开始
    private int backgroundColorStart = 0;
    //导航条背景色渐变结束
    private int backgroundColorEnd = 0;
    private int mBottomLineHeight = 1;
    //线性渐变对象
    private LinearGradient backgroundLG = null;
    //背景画笔
    private Paint backgroundPaint = new Paint();

    public NavigatorView(Context context) {
        super(context);
        init(context);
    }

    public NavigatorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public NavigatorView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    /**
     * 初始化ScrollView,并设置其根
     *
     * @param context
     */
    private void init(Context context) {
        this.barContext = context;
        //使得LinearLayout能够充满滚动条布局
        this.setFillViewport(true);
        //去掉白色提示
        this.setHorizontalFadingEdgeEnabled(false);
        mRootLayout = new LinearLayout(context);
        mRootLayout.setOrientation(LinearLayout.HORIZONTAL);
        mRootLayout.setGravity(Gravity.CENTER_VERTICAL);
        //添加根布局
        this.addView(mRootLayout);
        //去掉滚动条
        this.setHorizontalScrollBarEnabled(false);
        //设置默认的Tab间距
        setTabSpace(60);
        //初始化背景画笔
        backgroundPaint.setColor(Color.WHITE);
        backgroundPaint.setStyle(Paint.Style.FILL);
        backgroundPaint.setAntiAlias(true);
    }

    /**
     * 设置添加摁扭
     *
     * @param text 摁扭的文字
     */
    public void addTab(final String text) {
        final Tab tab = new Tab(barContext, text);
        mRootLayout.addView(tab);
        tabList.add(tab);
        tab.setIndex(tabList.size() - 1);
        tab.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tab.getIndex() != tabList.get(lightTabIndex).getIndex()) {//防止重复点击
                    onTabClick(tab.getIndex(), text);
                }
            }
        });
        this.invalidate();
    }

    /**
     * 设置添加摁扭
     *
     * @param text  摁扭的文字
     * @param index 添加的位置
     */
    public void addTab(final String text, final int index) {
        final Tab tab = new Tab(barContext, text);
        if (index < tabList.size()) {
            for (int i = index; i < tabList.size(); i++) {
                tabList.get(i).setIndex(i + 1);
            }
            mRootLayout.addView(tab, index);
            tabList.add(index, tab);
            tab.setIndex(index);
        } else {
            mRootLayout.addView(tab);
            tabList.add(tab);
            tab.setIndex(tabList.size() - 1);
        }
        tab.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tab.getIndex() != tabList.get(lightTabIndex).getIndex()) {//防止重复点击
                    onTabClick(tab.getIndex(), text);
                }
            }
        });
        this.invalidate();
    }

    /**
     * 删除Tab
     *
     * @param text Tab名字
     */
    public void removeTab(final String text) {
        Iterator<Tab> it = tabList.iterator();
        while (it.hasNext()) {
            Tab tab = it.next();
            if (tab.getText().equals(text)) {
                int index = tab.index;
                mRootLayout.removeViewAt(index);
                it.remove();
                if (this.lightTabIndex >= index && this.lightTabIndex > 0) {
                    this.lightTabIndex -= 1;
                    isTabLightChanged = true;
                }
                for (int i = index; i < tabList.size(); i++) {
                    Tab tabOverRemove = tabList.get(i);
                    tabOverRemove.index--;
                }

                break;
            }
        }
        this.invalidate();
    }

    /**
     * 删除Tab
     *
     * @param index Tab下标
     */
    public void removeTab(final int index) {
        Iterator<Tab> it = tabList.iterator();
        while (it.hasNext()) {
            Tab tab = it.next();
            if (tab.getIndex() == index) {
                mRootLayout.removeViewAt(index);
                it.remove();
                if (this.lightTabIndex >= index && this.lightTabIndex > 0) {
                    this.lightTabIndex -= 1;
                    isTabLightChanged = true;
                }
                for (int i = index; i < tabList.size(); i++) {
                    Tab tabOverRemove = tabList.get(i);
                    tabOverRemove.index--;
                }
                break;
            }
        }
        this.invalidate();
    }

    /**
     * 设置摁扭的宽度(dp),仅当isAutoFixSpace为false时有效
     *
     * @param space dp值
     */
    public void setTabSpace(int space) {
        this.tabSpace = getDP(barContext, space);
    }

    /**
     * 是否自动设置Tab的宽度,调用此方法SetTabWidth作废
     *
     * @param isAuto boolean
     */
    public void setAutoFixSpace(boolean isAuto) {
        this.isAutoFixSpace = isAuto;
    }

    /**
     * 设置默认的字体颜色
     *
     * @param color 颜色
     */
    public void setTabNormalTextColor(int color) {
        this.normalTabTextColor = color;
        Iterator<Tab> it = this.tabList.iterator();
        while (it.hasNext()) {
            Tab t = it.next();
            t.setColor(color);
            t.invalidate();
        }
    }

    /**
     * 获得当前高亮的下标
     *
     * @return
     */
    public int getCurrentIndex() {
        return lightTabIndex;
    }

    /**
     * 获得所有的标签数量
     *
     * @return
     */
    public int getTabCount() {
        return this.tabList.size();
    }

    /**
     * 设置高亮的选中下标值
     *
     * @param lightTabIndex
     */
    public void setCurrentIndex(final int lightTabIndex) {
        if (lightTabIndex != this.lightTabIndex) {
            //判断是向左滑动还是向右滑动
            final boolean isScrollLeft = lightTabIndex - Math.abs(NavigatorView.this.lightTabIndex) > 0 ? true:false;
            this.lightTabIndex = lightTabIndex;
            isTabLightChanged = true;
            //滑动到当前高亮位置
            this.invalidate();
            //进行显示移动处理
            Tab tab = tabList.get(lightTabIndex);
            if (isScrollLeft) {
                this.smoothScrollTo(this.getScrollX() + tab.getWidth(), 0);
            } else {
                this.smoothScrollTo(this.getScrollX() - tab.getWidth(), 0);
            }
        }
    }

    /**
     * 设置高亮的标签字体显示颜色
     *
     * @param color
     */
    public void setTabLightTextColor(int color) {
        this.lightTabTextColor = color;
    }

    /**
     * 设置高亮的标签字体背景显示颜色
     *
     * @param color
     */
    public void setTabLightBackGroundColor(int color) {
        this.lightTabBGColor = color;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //获得父布局宽度
        barViewWidth = MeasureSpec.getSize(widthMeasureSpec);
        //设置渐变色属性
        backgroundLG = new LinearGradient(0, 0, 0, this.getHeight() + this.getHeight() / 3, backgroundColorStart, backgroundColorEnd, Shader.TileMode.MIRROR);
        backgroundPaint.setShader(backgroundLG);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBackGroundColor(canvas);
        drawBottomLine(canvas);
        drawHightLight(canvas);
    }

    private void drawBackGroundColor(Canvas canvas) {
        canvas.drawPaint(backgroundPaint);
    }

    private void drawHightLight(Canvas canvas) {
        if (tabList.size() > 0) {
            lightPaint.setStyle(Paint.Style.FILL);//充满
            lightPaint.setColor(lightTabBGColor);
            lightPaint.setAntiAlias(true);// 设置画笔的锯齿效果
            if (lightTabIndex < 0) {
                lightTabIndex = 0;
                isTabLightChanged = true;
            }
            Tab tab = tabList.get(lightTabIndex);
            float tabSpaceTemp;
            if (isAutoFixSpace) {
                tabSpaceTemp = getDP(barContext, 20);
            } else {
                tabSpaceTemp = (tab.getMeasuredWidth() / 2 - tab.getTextWidth() / 2);
            }
            float scale = 0f;
            for (int i = 0; i < lightTabIndex; i++) {
                scale += tabList.get(i).getMeasuredWidth();
            }

            //标准样式
            lightPoint.x = (tab.getMeasuredWidth() / 2f - tab.getTextWidth() / 2f - tabSpaceTemp / 2f) + scale;
            lightPoint.y = tab.getMeasuredHeight() / 2f - tab.getTextHeight() / 2f - (this.getMeasuredHeight() - tab.getTextHeight()) / 4f;
            lightWidth = lightPoint.x + tabSpaceTemp / 2f + tab.getTextWidth() + tabSpaceTemp / 2f;
            lightHeight = lightPoint.y + tab.getTextHeight() + (this.getMeasuredHeight() - tab.getTextHeight()) / 2f + 5f;

            //细条样式(如果不需要细条样式,请注释以下三行代码)
            lightHeight = 5;
            lightPoint.y = this.getMeasuredHeight() - lightHeight;
            lightHeight = lightPoint.y + lightHeight;

            RectF oval3 = new RectF(lightPoint.x, lightPoint.y, lightWidth, lightHeight);// 设置个新的长方形
//            canvas.drawRoundRect(oval3, 20, 20, lightPaint);//第二个参数是x半径，第三个参数是y半径
            canvas.drawRect(oval3,lightPaint);//调试用框
            //设置高亮字体
            Iterator<Tab> it = tabList.iterator();
            while (it.hasNext()) {
                Tab t = it.next();
                t.setColor(normalTabTextColor);
                t.invalidate();
            }
            tabList.get(lightTabIndex).setColor(lightTabTextColor);
            tabList.get(lightTabIndex).invalidate();
            if (tabList.size() > 0 && isTabLightChanged) {
                //高亮变化事件通知
                onTabLightChange(lightTabIndex, tabList.get(lightTabIndex).getText());
                isTabLightChanged = false;
            }
        }
    }

    /**
     * 绘制底部的一根线
     */
    private void drawBottomLine(Canvas canvas) {
        mBottomLinePaint.setStyle(Paint.Style.FILL);//充满
        mBottomLinePaint.setColor(nurmalTabBGColor);
        mBottomLinePaint.setAntiAlias(true);// 设置画笔的锯齿效果
        RectF oval3 = new RectF(0, this.getMeasuredHeight()- mBottomLineHeight, this.getMeasuredWidth(), this.getMeasuredHeight());
//        RectF oval3 = new RectF(100, 10, 300, 20);
        canvas.drawRect(oval3, mBottomLinePaint);//将这个看起来像一条线的正方形绘制出来，
    }

    public interface OnTabClickListener {
        public void onTabClick(int index, String str);
    }

    public interface OnTabLightChangeListener {
        public void onTabLightChange(int index, String str);
    }

    public void setOnTabClickListener(OnTabClickListener l) {
        this.onTabClickListener = l;
    }

    public void setOnTabLightChangeListener(OnTabLightChangeListener l) {
        this.onTabLightChangeListener = l;
    }

    private void onTabClick(int index, String str) {
        if (this.onTabClickListener != null) {
            setCurrentIndex(index);
            this.onTabClickListener.onTabClick(index, str);
        }
    }

    private void onTabLightChange(int index, String str) {
        if (this.onTabLightChangeListener != null) {
            this.onTabLightChangeListener.onTabLightChange(index, str);
        }
    }

    public void setBackgroundColor(int start,int end) {
        this.backgroundColorStart = start;
        this.backgroundColorEnd = end;
    }

    public void setBackgroundColor(int color) {
        setBackgroundColor(color, color);
    }

    /**
     * 设置底部线条颜色
     */
    public void setBottomLineColor(int color) {
        nurmalTabBGColor = color;
    }

    private class Tab extends View {
        private String text = "";
        private int index = -1;
        private Paint textPaint = null;
        private float textWidth = 0f;
        private float textHeight = 0f;
        private int width = 0;
        private int height = 0;
        private Context tabContext = null;
        private float textX = 0f;
        private float textY = 0f;

        public Tab(Context context, String str) {
            super(context);
            tabContext = context;
            setText(str);
            initPaint(Color.BLACK);
        }

        public Tab(Context context, String str, int color) {
            super(context);
            tabContext = context;
            setText(str);
            initPaint(color);
        }

        /**
         * 初始化画笔
         */
        private void initPaint(int color) {
            textPaint = new Paint();
            textPaint.setTextSize(getSP(tabContext, 13));
            textWidth = textPaint.measureText(text);
            textPaint.setColor(color);
            textPaint.setAntiAlias(true);
            Paint.FontMetrics fm = new Paint.FontMetrics();
            textPaint.getFontMetrics(fm);
            textHeight = Math.abs(fm.ascent);
            textPaint.setStyle(Paint.Style.FILL);
        }

        /**
         * 设置Tab内容
         *
         * @param str
         */
        public void setText(String str) {
            this.text = str;
        }

        /**
         * 得到Tab内容
         *
         * @return string
         */
        public String getText() {
            return text;
        }

        /**
         * 设置文字颜色
         *
         * @param color
         */
        public void setColor(int color) {
            textPaint.setColor(color);
        }

        /**
         * 获得Tab的文字起始X坐标
         *
         * @return
         */
        public float getTextX() {
            return textX;
        }

        /**
         * 获得Tab的文字起始Y坐标
         *
         * @return
         */
        public float getTextY() {
            return textY;
        }

        /**
         * 获得Tab的文字宽度
         *
         * @return
         */
        public float getTextWidth() {
            return textWidth;
        }

        /**
         * 获得Tab的文字高度
         *
         * @return
         */
        public float getTextHeight() {
            return textHeight;
        }

        /**
         * 获得Tab的下标数
         *
         * @return
         */
        public int getIndex() {
            return index;
        }

        /**
         * 设置Tab的下标数
         *
         * @param index
         */
        public void setIndex(int index) {
            this.index = index;
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            drawText(canvas);
//            canvas.drawRect(0,0,this.getWidth(),this.getHeight(),textPaint);//调试用框
        }

        private void drawText(Canvas canvas) {
            canvas.drawText(text, textX, textY, textPaint);
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            if (isAutoFixSpace) {
                if (tabList.size() > 0) {
                    width = (int) (barViewWidth / tabList.size());
                } else {
                    width = 0;
                }
            } else {
                width = (int) (textWidth + tabSpace);
            }

            height = MeasureSpec.getSize(heightMeasureSpec);
            setMeasuredDimension(width, height);
            //初始化文字坐标
            textX = width / 2 - textWidth / 2;
            textY = height / 2 + textHeight / 2;

        }
    }

    private static int getDP(Context c, int px) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, px, c.getResources().getDisplayMetrics());
    }

    private static int getSP(Context c, int px) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, px, c.getResources().getDisplayMetrics());
    }
}
