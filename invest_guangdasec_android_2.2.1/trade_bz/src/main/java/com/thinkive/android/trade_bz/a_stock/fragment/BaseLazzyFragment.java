package com.thinkive.android.trade_bz.a_stock.fragment;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/10/20.
 */

public class BaseLazzyFragment extends Fragment {
    // 标志位，标志已经初始化完成。
    protected boolean isPrepared;
    //标志为，当前fragment是否可见;
    protected boolean isVisible;
    //根布局；
    protected View mRootView;
    private Toast mToast;

    /**
     * 在这里实现Fragment数据的缓加载.
     *
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    protected void onVisible() {
        lazyLoad();
    }

    protected void lazyLoad() {

    }

    protected void onInvisible() {
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        onInvisible();
    }

}
