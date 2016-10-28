package com.thinkive.android.trade_bz.a_stock.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.thinkive.framework.compatible.ListenerController;
import com.android.thinkive.framework.compatible.TKFragmentActivity;
import com.thinkive.android.trade_bz.a_stock.activity.AbsNavBarActivity;

import me.yokeyword.fragmentation.SwipeBackLayout;

/**
 * 描述：该类是抽象类，用于供所有的碎片子类继承，
 * 该类实现了视图监听的注册方法和启动一个Activity的方法
 *
 * @author 黎丝军，王志鸿
 * @version 1.0
 * @corporation 深圳市思迪信息科技有限公司
 * @date 2015/6/3
 */
public abstract class AbsBaseFragment extends Fragment {

    private TKFragmentActivity mActivity = null;
    String mName;
    public boolean isAdded;

    public AbsBaseFragment() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        isAdded = true;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mActivity instanceof AbsNavBarActivity) {
            AbsNavBarActivity absNavBarActivity = (AbsNavBarActivity) mActivity;
            absNavBarActivity.getSwipeBackLayout().addSwipeListener(new SwipeBackLayout.OnSwipeListener() {
                @Override
                public void onDragStateChange(int i) {
                }

                @Override
                public void onEdgeTouch(int i) {
                    closeFrameworkKeyBroad();
                }

                @Override
                public void onDragScrolled(float v) {
                }
            });
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Deprecated
    protected void findViews() {
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = (TKFragmentActivity) activity;
    }

    protected abstract void findViews(View view);

    protected void registerListener(int flag, View view, ListenerController listenerController) {
        mActivity.registerListener(flag, view, listenerController);
    }

    public void startActivity(Class<?> activity) {
        final Intent intent = new Intent(mActivity, activity);
        mActivity.startActivity(intent);
    }

    @Override
    public void startActivity(Intent intent) {
        mActivity.startActivity(intent);
    }

    public String getName() {
        if (mName == null) {
            mName = "--";
        }
        return mName;
    }

    /**
     * 暴露给子类实现的框架自绘键盘关闭方法，
     * 凡是用到框架自绘键盘的Fragment建议都复写下这个方法，
     * 然后在这里写上相关关闭框架自绘键盘的代码。
     */
    public void closeFrameworkKeyBroad() {
    }

    public void setName(String name) {
        mName = name;
    }

    protected abstract void setListeners();

    protected abstract void initData();

    protected abstract void initViews();

    /**
     * 设置主题 需要在initView之后调用
     * 请先关联Activity后再调用本方法
     */
    protected abstract void setTheme();



}
