package com.thinkive.android.trade_bz.a_stock.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.android.thinkive.framework.compatible.ListenerControllerAdapter;
import com.android.thinkive.framework.compatible.TKFragmentActivity;
import com.thinkive.android.trade_bz.R;

/**
 * 带头部标题栏的Fragment
 * 标题栏为悦投资应用标准蓝色，左右边各有一个按钮。
 * @author 王志鸿
 * @company Thinkive
 * @date 2015/7/1
 */
public abstract class AbsNavbarFragment extends AbsBaseFragment {

    /**
     * 用于返回上一页
     */
    private Button mBuBack = null;
    /**
     * 用于显示每一页的标题
     */
    private TextView mTvTitle = null;
    /**
     * 用于注销或其他显示信息
     */
    Button mBuLogout = null;

    /**
     * fragment页面根布局
     */
    private View mRootView;

    /**
     * 给子类提供的容器，子类视图都装在这里
     */
    private FrameLayout mSubContainer;

    /**
     * 本类对象的宿主Activity
     */
    TKFragmentActivity mActivity;
    /**
     * 本类视图控件监听事件的控制器
     */
    private AbsNavBarController mController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_base_navbar_trade, null);
        initDataInAbs();
        findViewsInAbs();
        setListenerInAbs();
        return mRootView;
    }

    /**
     * 给本类视图控件赋值
     */
    private void findViewsInAbs() {
        mSubContainer = (FrameLayout) mRootView.findViewById(R.id.fl_fragment_sub_container);
        mBuBack = (Button) mRootView.findViewById(R.id.btn_back_in_fragment);
        mTvTitle = (TextView)mRootView.findViewById(R.id.tv_title_in_fragment);
        mBuLogout = (Button)mRootView.findViewById(R.id.btn_logout_in_fragment);
    }

    /**
     * 初始化本类变量中的数据
     */
    private void initDataInAbs() {
        mActivity = (TKFragmentActivity) getActivity();
        mController = new AbsNavBarController(this);
    }

    /**
     * 设置本类控件的事件监听
     */
    private void setListenerInAbs() {
        registerListener(ListenerControllerAdapter.ON_CLICK, mBuBack, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mBuLogout, mController);
    }
    /**
     * 将子类视图放入本基类容器中
     */
    void setSubViewToContainer(View subView) {
        mSubContainer.addView(subView);
    }

    /**
     * 用于在子类去实现返回按钮事件的具体处理方法
     * 该返回事件默认是结束当前Activity，子类可以去重写
     */
    void onClickBack(){
        mActivity.finish();
    }

    /**
     * 用户单击标题栏右边按钮时的动作监听，等待子类重写。当然，子类可以不充写。
     */
    void onClickLogout() {

    }

    //------------------------------头部标题栏控件操作暴露方法定义，开始--------------------------------
    /**
     * 用于设置返回按钮是否可见
     * 默认返回按钮是可见的
     * @param visibility 该值你可以传如下三个：
     *                   View.VISIBLE
     *                   View.INVISIBLE
     *                   View.GONE
     */
    protected void setBackBtnVisibility(int visibility) {
        mBuBack.setVisibility(visibility);
    }

    /**
     * 用于设置注销按钮是否可见
     * 默认注销按钮是不可见的
     * @param visibility 该值你可以传如下三个：
     *                   View.VISIBLE
     *                   View.INVISIBLE
     *                   View.GONE
     */
    protected void setLogoutBtnVisibility(int visibility) {
        if (mBuLogout != null) {
            mBuLogout.setVisibility(visibility);
        }
    }

    /**
     * 用于设置注销按钮的背景资源图
     * @param drawableId 资源图片ID
     */
    protected void setLogoutBtnBackground(int drawableId) {
        if (mBuLogout != null) {
            mBuLogout.setBackgroundResource(drawableId);
        }
    }

    /**
     * 用于设置注销按钮的文本信息
     * @param resId 资源Id
     */
    protected void setLogoutBtnText(int resId) {
        if (mBuLogout != null) {
            mBuLogout.setText(resId);
        }
    }

    /**
     * 用于设置标题栏标题
     * @param resId 资源Id
     */
    protected void setTitleText(int resId) {
        mTvTitle.setText(resId);
    }

    /**
     * 用于设置标题栏标题
     * @param titleText 标题信息
     */
    protected void setTitleText(String titleText) {
        mTvTitle.setText(titleText);
    }

    /**
     * 用于设置返回按钮右边的文本信息
     * @param resId 资源Id
     */
    protected void setBackBtnRightText(int resId) {
        mBuBack.setText(resId);
    }

    /**
     * 用于设置返回按钮右边的文本信息
     * @param rightText 文本字符串
     */
    protected void setBackBtnRightText(String rightText) {
        mBuBack.setText(rightText);
    }
    //------------------------------头部标题栏控件操作暴露方法定义，结束--------------------------------

}

/**
 * 本文件主类的控件事件监听器
 */
class AbsNavBarController extends ListenerControllerAdapter implements View.OnClickListener {

    private AbsNavbarFragment mFragment;

    public AbsNavBarController(AbsNavbarFragment fragment) {
        mFragment = fragment;
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
        if (resId == R.id.btn_back_in_fragment) { // 如果是单击返回按钮
            mFragment.onClickBack();
        } else if (resId == R.id.btn_logout_in_fragment) { // 如果是单击注销按钮
            mFragment.onClickLogout();
        }
    }
}
