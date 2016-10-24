package com.android.thinkive.invest_sd.fragment;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;

import com.android.thinkive.framework.CoreApplication;
import com.android.thinkive.framework.compatible.ListenerController;
import com.android.thinkive.framework.compatible.TKFragmentActivity;

/**
 * 描述：该类是抽象类，用于供所有的碎片子类继承，
 *       该类实现了视图监听的注册方法和启动一个Activity的方法
 *
 * @author 王志鸿
 * @corporation 深圳市思迪信息科技有限公司
 * @date 2015/6/3
 */
public abstract class AbsBaseFragment extends Fragment {

    private TKFragmentActivity mActivity = null;
    String mName;

    public AbsBaseFragment(){
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

    protected void registerListener(int flag, View view, ListenerController listenerController){
        mActivity.registerListener(flag,view,listenerController);
    }

    public void startActivity(Class<?> activity) {
        final Intent intent = new Intent(mActivity,activity);
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

    public void setName(String name) {
        mName = name;
    }

    abstract void setListeners();

    abstract void initData();

    abstract void initViews();

    /**
     * 设置主题 需要在initView之后调用
     */
    protected abstract void setTheme();

    /**
     *
     * @param resId
     * @return
     */
    public final String getResString(int resId) {
        return CoreApplication.getInstance().getString(resId);
    }
}
