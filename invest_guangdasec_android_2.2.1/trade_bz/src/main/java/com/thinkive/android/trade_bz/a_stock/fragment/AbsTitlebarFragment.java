package com.thinkive.android.trade_bz.a_stock.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.thinkive.framework.compatible.ListenerControllerAdapter;
import com.android.thinkive.framework.compatible.TKFragmentActivity;
import com.thinkive.android.trade_bz.R;

/**
 * 带头部标题栏的Fragment
 * 改类用于交易主页，显示用户登陆状态
 *
 * @author 王志鸿
 * @company Thinkive
 * @date 2015/7/1
 */
public abstract class AbsTitlebarFragment extends AbsBaseFragment {
    /**
     * 包裹标题栏的容器
     */
    private RelativeLayout mRealvRoot = null;
    /**
     * 标题栏左侧按钮，用于返回
     */
    private Button mBtnBack = null;
    /**
     * 标题栏左侧按钮，用于显示已登录的账户
     */
    private Button mBtnAccount = null;
    /**
     * 标题栏正中的页面标题
     */
    private TextView mTvTitle = null;
    /**
     * 标题栏右侧字体按钮，用于注销或退出
     */
    Button mBtnExit = null;
    /**
     * fragment页面根布局
     */
    private View mSuperRootView;

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
    private AbsTitleBarController mController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mSuperRootView = inflater.inflate(R.layout.fragment_base_titlebar, null);
        initDataInAbs();
        findViewsInAbs();
        setListenerInAbs();
        return mSuperRootView;
    }

    /**
     * 给本类视图控件赋值
     */
    private void findViewsInAbs() {
        mSubContainer = (FrameLayout) mSuperRootView.findViewById(R.id.fl_fragment_sub_container);
        mRealvRoot = (RelativeLayout) mSuperRootView.findViewById(R.id.re_root_layout);
        mBtnBack = (Button) mSuperRootView.findViewById(R.id.btn_back_in_fragment);
        mBtnAccount = (Button) mSuperRootView.findViewById(R.id.btn_account_fragment);
        mTvTitle = (TextView) mSuperRootView.findViewById(R.id.tv_title_in_fragment);
        mBtnExit = (Button) mSuperRootView.findViewById(R.id.btn_logout_in_fragment);
    }

    /**
     * 初始化本类变量中的数据
     */
    private void initDataInAbs() {
        mActivity = (TKFragmentActivity) getActivity();
        mController = new AbsTitleBarController(this);
    }

    /**
     * 设置本类控件的事件监听
     */
    private void setListenerInAbs() {
        registerListener(ListenerControllerAdapter.ON_CLICK, mBtnBack, mController);
        registerListener(ListenerControllerAdapter.ON_CLICK, mBtnExit, mController);
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
    void onClickBack() {
        mActivity.finish();
    }

    /**
     * 用户单击标题栏右边按钮时的动作监听，等待子类重写。当然，子类可以不充写。
     */
    void onClickBtnExit() {

    }

    //------------------------------头部标题栏控件操作暴露方法定义，开始--------------------------------

    /**
     * 用于设置返回按钮是否可见
     * 默认返回按钮是可见的
     *
     * @param visibility
     */
    protected void setBackBtnVisibility(int visibility) {
        if (mBtnBack != null) {
            mBtnBack.setVisibility(visibility);
        }
    }

    /**
     * 用于设置注销按钮是否可见
     * 默认注销按钮是不可见的
     *
     * @param visibility
     */
    protected void setExitBtnVisibility(int visibility) {
        if (mBtnExit != null) {
            mBtnExit.setVisibility(visibility);
        }
    }

    /**
     * 用于设置标题栏标题是否可见
     */
    protected void setTitleVisibility(int visibility) {
        if (mTvTitle!=null) {
            mTvTitle.setVisibility(visibility);
        }
    }

    /**
     * 用于设置标题栏标否可见
     */
    protected void setTitleRootVisibility(int visibility) {
        if (mRealvRoot!=null) {
            mRealvRoot.setVisibility(visibility);
        }
    }

    /**
     * 用于设置左侧 已登录账户是否可见
     */
    protected void setAccountBtnVisibility(int visibility) {
        if (mBtnAccount != null) {
            mBtnAccount.setVisibility(visibility);
        }
    }

    /**
     * 用于设置左侧 已登录账户名称
     */
    protected void setAccountBtnText(String str) {
        if (mBtnAccount != null) {
            mBtnAccount.setText(str);
        }

    }


    /**
     * 用于设置注销按钮的背景资源图
     *
     * @param drawableId 资源图片ID
     */
    protected void setExitBtnBackground(int drawableId) {
        if (mBtnExit != null) {
            mBtnExit.setBackgroundResource(drawableId);
        }
    }

    /**
     * 用于设置注销按钮的文本信息
     *
     * @param resId 资源Id
     */
    protected void setExitBtnText(int resId) {
        if (mBtnExit != null) {
            mBtnExit.setText(resId);
        }
    }

    /**
     * 用于设置标题栏标题
     *
     * @param resId 资源Id
     */
    protected void setTitleText(int resId) {
        if (mTvTitle != null) {
            mTvTitle.setText(resId);
        }
    }

    /**
     * 用于设置标题栏标题
     *
     * @param titleText 标题信息
     */
    protected void setTitleText(String titleText) {
        if (mTvTitle != null) {
            mTvTitle.setText(titleText);
        }
    }

    /**
     * 用于设置返回按钮右边的文本信息
     *
     * @param resId 资源Id
     */
    protected void setBackBtnText(int resId) {
        if (mBtnBack != null) {
            mBtnBack.setText(resId);
        }
    }

    /**
     * 用于设置返回按钮右边的文本信息
     *
     * @param rightText 文本字符串
     */
    protected void setBackBtnText(String rightText) {
        if (mBtnBack != null) {
            mBtnBack.setText(rightText);
        }
    }
    //------------------------------头部标题栏控件操作暴露方法定义，结束--------------------------------

}

/**
 * 本文件主类的控件事件监听器
 */
class AbsTitleBarController extends ListenerControllerAdapter implements View.OnClickListener {

    private AbsTitlebarFragment mFragment;

    public AbsTitleBarController(AbsTitlebarFragment fragment) {
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
            mFragment.onClickBtnExit();
        }
    }
}
