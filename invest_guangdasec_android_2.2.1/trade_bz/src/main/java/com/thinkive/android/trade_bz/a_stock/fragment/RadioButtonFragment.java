package com.thinkive.android.trade_bz.a_stock.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.android.thinkive.framework.compatible.TKFragmentActivity;
import com.android.thinkive.framework.util.ScreenUtil;
import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;

/**
 * 动态添加fragment,通过radioButton进行切换
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/3/29
 */
public class RadioButtonFragment extends AbsBaseFragment {
    private TKFragmentActivity mActivity;
    private FragmentManager mFragmentManager;
    private AbsBaseFragment[] mFragments;
    private int currentIndex;
    private RadioGroup mRadioGroup;
    private int mCheckAt = 0;

    public RadioButtonFragment() {

    }

    public RadioButtonFragment(AbsBaseFragment... fragments) {
        mFragments = fragments;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_common_radio_button, null);
        findViews(rootView);
        initData();
        setListeners();
        initViews();
        return rootView;
    }

    @Override
    protected void findViews(View view) {
        mRadioGroup = (RadioGroup) view.findViewById(R.id.rg_fragment_group);
    }

    @Override
    protected void setListeners() {
        registerListener(AbsBaseController.ON_CHECK_CHANGE, mRadioGroup, new RRadioFragmentController());
    }



    @Override
    protected void initData() {
        mActivity = (TKFragmentActivity) getActivity();
        mFragmentManager = getActivity().getSupportFragmentManager();
        Bundle bundle = getArguments();
        if(bundle != null){
            mCheckAt = bundle.getInt("ViewPagerFragmentPos",0);
        }
    }

    @Override
    protected void initViews() {
        setTheme();
        RadioButton tempRadioButton = null;
        // 默认展现第一个fragment
        mFragmentManager.beginTransaction().replace(R.id.trade_container, mFragments[0]).commit();
        // 当前展现fragment的序号
        currentIndex = 0;
        //获取屏幕的宽度
        float ScreenWidth = ScreenUtil.getScreenWidth(mActivity);
        int width = (int) (ScreenWidth * 0.9);
        //设置 RadioGroup 宽度
        mRadioGroup.setMinimumWidth(width);
        // 设置radioButton 的大小
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((width - 20) / mFragments.length, ViewGroup.LayoutParams.MATCH_PARENT, 1);
        params.gravity = Gravity.CENTER;
        // 根据fragment的个数，动态添加radioButton个数
        for (int i = 0; i < mFragments.length; i++) {
            tempRadioButton = new RadioButton(mActivity);
            tempRadioButton.setButtonDrawable(android.R.color.transparent);
            tempRadioButton.setLayoutParams(params);
            tempRadioButton.setGravity(Gravity.CENTER);
            tempRadioButton.setBackgroundResource(R.drawable.common_selector_center1);
            tempRadioButton.setText(mFragments[i].getName());
            mRadioGroup.addView(tempRadioButton);
        }
        //默认选中第一个RadioButton
        mRadioGroup.check(mRadioGroup.getChildAt(mCheckAt).getId());
        // 设置最左边一个资源
        mRadioGroup.getChildAt(0).setBackgroundResource(R.drawable.common_selector_left1);
        // 设置最右边一个资源
        mRadioGroup.getChildAt(mRadioGroup.getChildCount() - 1).setBackgroundResource(R.drawable.common_selector_right1);
    }

    @Override
    protected void setTheme() {
    }

    public int getCheckAt() {
        return mCheckAt;
    }
    /**
     * 当radioButton 被选中时执行
     */
    public void onCheckedRb() {
        RadioButton radioButton = null;
        for (int i = 0; i < mRadioGroup.getChildCount(); i++) {
            radioButton = (RadioButton) mRadioGroup.getChildAt(i);
            if (radioButton.isChecked()) {
                radioButton.setTextColor(mActivity.getResources().getColor(R.color.trade_color_white));
                showFragment(i);
                mCheckAt = i;
            } else {
                radioButton.setTextColor(mActivity.getResources().getColor(R.color.trade_color1));
            }
        }
    }

    /**
     * 按照Fragment在{@link #mFragments}中的序号index，展示Fragment
     *
     * @param showIndex 要被显示的Fragment的序号index
     */
    public void showFragment(int showIndex) {
        try {
            if (currentIndex != showIndex) { // 当前显示的Fragment，不是即将显示的Fragment时
                FragmentTransaction trx = mFragmentManager.beginTransaction();
                trx.hide(mFragments[currentIndex]);
                mFragments[currentIndex].onStop();
                if (!mFragments[showIndex].isAdded()) {
                    trx.add(R.id.trade_container, mFragments[showIndex]);
                }
                trx.show(mFragments[showIndex]).commit();
                currentIndex = showIndex;
            }
            if (mFragments[showIndex].isAdded()) {
                mFragments[currentIndex].onResume();
            }
        }catch(IllegalStateException e){
            e.printStackTrace();
        }
    }



    class RRadioFragmentController extends AbsBaseController implements RadioGroup.OnCheckedChangeListener {

        public RRadioFragmentController() {

        }

        @Override
        public void register(int i, View view) {
            switch (i) {
                case ON_CHECK_CHANGE:
                    ((RadioGroup) view).setOnCheckedChangeListener(this);
                    break;
            }
        }

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            onCheckedRb();
        }
    }
}

