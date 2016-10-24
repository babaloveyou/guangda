package com.android.thinkive.invest_sd.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.thinkive.framework.util.CommonUtil;
import com.android.thinkive.framework.util.Log;

/**
 *
 * 基础Fragment  设置主题 动画等
 *
 * Created by zhuduanchang on 2015/6/26.
 */
public abstract class BaseFragment extends Fragment {
    private final static int INTERVAL_TIME = 1000;
    private final static int SHOW_VERSION_TIMES = 3;
    private int mClickTimes=0;
    private long mFirstClickTime = 0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =container.getRootView();
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("getView11111111111   " + mClickTimes);
            }
        });
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View view = getView();
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mClickTimes < SHOW_VERSION_TIMES) {
                    Log.d("mClickTimes " +mClickTimes);
                    long secondClickTime = System.currentTimeMillis();
                    if (secondClickTime - mFirstClickTime < INTERVAL_TIME || mClickTimes == 0){
                        mFirstClickTime = secondClickTime;
                    }else {
                        mClickTimes = 0;
                        mFirstClickTime = 0;
                        return;
                    }
                    mClickTimes++;
                } else {
                    Toast.makeText(getActivity(), "当前framework版本号："+ CommonUtil.getThinkiveFrameworkVersionName(), Toast.LENGTH_SHORT).show();
                    mFirstClickTime = 0;
                    mClickTimes = 0;
                }
            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        setTheme();
    }

    /**
     * 初始化控件
     */
    protected abstract void initView(View view);

    /**
     * 设置主题 需要在initView之后调用
     */
    protected abstract void setTheme();
}
