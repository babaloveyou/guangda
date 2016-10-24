package com.thinkive.android.trade_bz.a_stock.activity;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.thinkive.framework.compatible.ListenerControllerAdapter;
import com.thinkive.android.trade_bz.R;

import me.yokeyword.fragmentation.SwipeBackLayout;

/**
 * 描述：该类是有标题栏的activity，该标题栏含有三个内容，一个是返回按钮
 * 一个是标题文本，另一个是注销按钮。你如果继承了该类，并需要有标题栏
 * 那么你需要在onCreate方法里使用setContentView(int layoutResID,boolean isNavBar)方法来加载布局
 * 将isNavBar传入true值就可以了。如果你不想要标题栏的返回按钮，你只需要使用setBackBtnVisibility()方法来设置就可以
 * 当然你也可在调用setBackBtnRightText()方法来设置按钮右边出现的文本。设置标题栏标题你需要使用setTitleText()方法来设置
 *
 * @author 黎丝军、王志鸿
 * @version 1.0
 * @corporation 深圳市思迪信息科技有限公司
 * @date 2015/6/2
 */
public abstract class AbsNavBarActivity extends AbsBasicActivity {
    /**
     * 用于返回上一页
     */
    private TextView mBackBtn = null;
    /**
     * 用于显示每一页的标题
     */
    private TextView mTitleTv = null;
    /**
     * 用于注销或其他显示信息
     */
    private Button mLogoutBtn = null;

    /**
     * 用于注销或其他显示信息 ImageView
     */
    private ImageView imIcon;

    private TextView tvTitleBelow;
    /**
     * 标题栏
     */
    private RelativeLayout mRealTitleBar = null;
    /**
     * 用于加载子布局
     */
    private FrameLayout mMainLayout = null;

    private FragmentManager mFragmentManager;
    /**
     * 控件事件控制器，本抽象父类专用
     */
    private AbsNavBarController mController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_fragment_with_narbar_trade);
        initDataInAbs();
        findViewsInAbs();
        setListenersInAbs();
        // 和滑动退出有关
        getSwipeBackLayout().setEdgeOrientation(SwipeBackLayout.EDGE_ALL);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    @Override
    protected int setContainerId() {
        return R.id.fl_container;
    }

    /**
     * 重写此方法目的是app不随系统字体变化
     *
     * @return Resources
     */
    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        Configuration config = new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config, res.getDisplayMetrics());
        return res;
    }

    @Override
    protected void setListeners() {
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void findViews() {
    }

    @Override
    protected void initViews() {

    }

    private void initDataInAbs() {
        mController = new AbsNavBarController(this);
        mFragmentManager = getSupportFragmentManager();
        mRealTitleBar = (RelativeLayout)findViewById(R.id.real_base_title);
    }

    /**
     * 用于初始化控件视图
     */
    private void findViewsInAbs() {
        mBackBtn = (TextView) findViewById(R.id.btn_back);
        mTitleTv = (TextView) findViewById(R.id.tv_title);
        mLogoutBtn = (Button) findViewById(R.id.btn_logout);
        mMainLayout = (FrameLayout) findViewById(R.id.fl_container);
        imIcon = (ImageView) findViewById(R.id.im_right);
        tvTitleBelow = (TextView) findViewById(R.id.tv_title_below);
    }

    private void setListenersInAbs() {
        registerListener(ListenerControllerAdapter.ON_CLICK, mBackBtn, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mLogoutBtn, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, imIcon, mController);
    }

    /**
     * 顶部状态栏是否可见
     */
    protected void setTitleBarVisible(int visibility){
        mRealTitleBar.setVisibility(visibility);
    }


    /**
     * 用于在子类去实现返回按钮事件的具体处理方法
     * 该返回事件默认是结束当前Activity，子类可以去重写
     */
    protected void onBackEvent() {
        finish();
    }

    /**
     * 单击 Title栏右侧按钮
     * 子类可以去重写
     */
    protected void onLogouBtEvent() {
    }

    /**
     * 单击 Title栏右侧按钮无操作
     * 子类可以去重写  图片
     */
    protected void onLogouImEvent() {

    }

    /**
     * 获得对象
     *
     * @return
     */
    public View getViews() {
        return imIcon;
    }

    /**
     * 获取对象
     *
     * @return
     */
    public View getTitliBelow() {
        return tvTitleBelow;
    }

    /**
     * 如果你继承了该类，你必须要使用此方法来加载你的布局
     * 否则你的视图肯定不含有头部标题栏。要想有标题栏只需
     * isNavBar值传true
     *
     * @param layoutResID 布局资源ID
     * @param isNavBar    Boolean
     */
    protected void setContentView(int layoutResID, boolean isNavBar) {
        if (isNavBar) {
            LayoutInflater.from(this).inflate(layoutResID, mMainLayout);
        } else {
            setContentView(layoutResID);
        }
    }

    /**
     * 向指定的容器里添加碎片
     *
     * @param containId 容器Id
     * @param fragment  需要添加的碎片
     */
    @Deprecated
    public void addFragment(int containId, Fragment fragment) {
        mFragmentManager.beginTransaction().add(containId, fragment).commit();
    }

    /**
     * 移除某个碎片
     *
     * @param fragment 碎片
     */
    public void removeFragment(Fragment fragment) {
        mFragmentManager.beginTransaction().remove(fragment).commit();
    }

    /**
     * 替换指定容器里的碎片
     *
     * @param containId 容器Id
     * @param fragment  碎片
     */
    public void replaceFragment(int containId, Fragment fragment) {
        mFragmentManager.beginTransaction().replace(containId, fragment).commit();
    }

    /**
     * 将某个碎片隐藏
     *
     * @param fragment 需要隐藏的碎片
     */
    public void hideFragment(Fragment fragment) {
        mFragmentManager.beginTransaction().hide(fragment).commit();
    }

    /**
     * 通过Id来获取某一个碎片
     *
     * @param id 资源id
     * @return Fragment
     */
    protected Fragment findFragmentById(int id) {
        return mFragmentManager.findFragmentById(id);
    }

    /**
     * 用于设置返回按钮是否可见
     * 默认返回按钮是可见的
     *
     * @param visibility 该值你可以传如下三个：
     *                   View.VISIBLE
     *                   View.INVISIBLE
     *                   View.GONE
     */
    protected void setBackBtnVisibility(int visibility) {
        mBackBtn.setVisibility(visibility);
    }

    /**
     * 用于设置返回按钮右边的文本信息
     *
     * @param resId 资源Id
     */
    protected void setBackBtnRightText(int resId) {
        mBackBtn.setText(resId);
    }

    /**
     * 用于设置返回按钮右边的文本信息
     *
     * @param rightText 文本字符串
     */
    protected void setBackBtnRightText(String rightText) {
        mBackBtn.setText(rightText);
    }

    /**
     * 用于设置注销按钮是否可见
     * 默认注销按钮是不可见的
     *
     * @param visibility 该值你可以传如下三个：
     *                   View.VISIBLE
     *                   View.INVISIBLE
     *                   View.GONE
     */
    protected void setLogoutBtnVisibility(int visibility) {
        mLogoutBtn.setVisibility(visibility);
    }

    /**
     * 用于设置注销图片是否可见
     * 默认注销按钮是不可见的
     *
     * @param visibility 该值你可以传如下三个：
     *                   View.VISIBLE
     *                   View.INVISIBLE
     *                   View.GONE
     */
    protected void setLogoImVisibility(int visibility) {
        imIcon.setVisibility(visibility);
    }

    /**
     * 设置标题栏下方的字体可见值
     *
     * @param visibility 该值你可以传如下三个：
     *                   View.VISIBLE
     *                   View.INVISIBLE
     *                   View.GONE
     */
    protected void setTitleBelowVisibility(int visibility) {
        tvTitleBelow.setVisibility(visibility);
    }


    /**
     * 用于设置注销按钮的背景资源图
     *
     * @param drawableId 资源图片ID
     */
    protected void setLogoutBtnBackground(int drawableId) {
        mLogoutBtn.setBackgroundResource(drawableId);
    }

    /**
     * 用于设置注销按钮的背景资源图
     *
     * @param drawableId 资源图片ID
     */
    protected void setLogoImBackground(int drawableId) {
        imIcon.setBackgroundResource(drawableId);
    }

    /**
     * 用于设置注销按钮的文本信息
     *
     * @param resId 资源Id
     */
    protected void setLogoutBtnText(int resId) {
        mLogoutBtn.setText(resId);
    }

    /**
     * 用于设置标题栏标题
     *
     * @param resId 资源Id
     */
    public void setTitleText(int resId) {
        mTitleTv.setText(resId);
    }

    /**
     * 用于设置标题栏标题
     *
     * @param titleText 标题信息
     */
    public void setTitleText(String titleText) {
        mTitleTv.setText(titleText);
    }

    /**
     * 用于设置标题栏标题栏下方文本信息
     *
     * @param resId 标题信息
     */
    public void setTitleBelowText(int resId) {
        tvTitleBelow.setText(resId);
    }
}

/**
 * 本文件主类的控件事件监听器
 */
class AbsNavBarController extends ListenerControllerAdapter implements View.OnClickListener {

    private AbsNavBarActivity mActivity;

    public AbsNavBarController(AbsNavBarActivity activity) {
        mActivity = activity;
    }

    @Override
    public void register(int i, View view) {
        switch (i) {
            case ListenerControllerAdapter.ON_CLICK:
                view.setOnClickListener(this);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        int resId = v.getId();
        if (resId == R.id.btn_back) { // 如果是单击返回按钮
            mActivity.onBackEvent();
        } else if (resId == R.id.btn_logout) {  //如果单击右侧按钮
            mActivity.onLogouBtEvent();
        } else if (resId == R.id.im_right) {  //如果单击右侧按钮 图片
            mActivity.onLogouImEvent();
        }
    }
}
