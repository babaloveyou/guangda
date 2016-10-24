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
import android.widget.TextView;

import com.android.thinkive.framework.compatible.ListenerControllerAdapter;
import com.android.thinkive.framework.compatible.TKFragmentActivity;
import com.android.thinkive.framework.util.ScreenUtil;
import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.controll.AbsBaseController;

/**
 * RadioButton 左右切换的标题栏（带标题栏）
 * 使用本类，就需要屏蔽activity的标题栏
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/5/4
 */
public class RadioButtonFragment2 extends AbsBaseFragment {
    private TKFragmentActivity mActivity;
    private FragmentManager mFragmentManager;
    private AbsBaseFragment[] mFragments;
    private int currentIndex;
    private RadioGroup mRadioGroup;
    private TextView mTvBack;
    private TextView mTvRight;
    /**
     * 进入默认选中位置
     */
    private int mCheckAt = 0;
    /**
     * 设置到title右侧的文字
     */
    private String mRightText = "";
    /**
     * 给右侧按钮添加监听方法
     */
    private View.OnClickListener  mRightListener;

    public RadioButtonFragment2() {

    }

    public RadioButtonFragment2(AbsBaseFragment... fragments) {
        mFragments = fragments;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_common_radio_button2, null);
        findViews(rootView);
        initData();
        setListeners();
        initViews();
        return rootView;
    }

    @Override
    protected void findViews(View view) {
        mRadioGroup = (RadioGroup) view.findViewById(R.id.rg_fragment_group);
        mTvBack = (TextView)view.findViewById(R.id.tv_radio_back);
        mTvRight = (TextView)view.findViewById(R.id.tv_radio_right);
    }

    @Override
    protected void setListeners() {
        registerListener(AbsBaseController.ON_CHECK_CHANGE, mRadioGroup, new RRadioFragmentController());
        registerListener(ListenerControllerAdapter.ON_CLICK, mTvBack, new RRadioFragmentController());
    }

    @Override
    protected void initData() {
        mActivity = (TKFragmentActivity) getActivity();
        mFragmentManager = getActivity().getSupportFragmentManager();
        //得到设置到右侧按钮的文字
        Bundle bundle = getArguments();
        if (bundle != null) {
            mRightText = bundle.getString("fragmentRightText");
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
        int width = (int) (ScreenWidth * 0.6);
        //设置 RadioGroup 宽度
        mRadioGroup.setMinimumWidth(width);
        // 设置radioButton 的大小
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((width - 40) / mFragments.length, ViewGroup.LayoutParams.MATCH_PARENT, 1);
        params.gravity = Gravity.CENTER;
        // 根据fragment的个数，动态添加radioButton个数
        for (int i = 0; i < mFragments.length; i++) {
            tempRadioButton = new RadioButton(mActivity);
            tempRadioButton.setButtonDrawable(android.R.color.transparent);
            tempRadioButton.setLayoutParams(params);
            tempRadioButton.setGravity(Gravity.CENTER);
            tempRadioButton.setBackgroundResource(R.drawable.common_selector_center2);
            tempRadioButton.setText(mFragments[i].getName());
            mRadioGroup.addView(tempRadioButton);
        }
        //默认选中第一个RadioButton
        mRadioGroup.check(mRadioGroup.getChildAt(mCheckAt).getId());
        // 设置最左边一个资源
        mRadioGroup.getChildAt(0).setBackgroundResource(R.drawable.common_selector_left2);
        // 设置最右边一个资源
        mRadioGroup.getChildAt(mRadioGroup.getChildCount() - 1).setBackgroundResource(R.drawable.common_selector_right2);
        // 给右侧按钮添加监听
        mTvRight.setOnClickListener(mRightListener);
        //给右侧按钮设置文字
        mTvRight.setText(mRightText);
    }

    @Override
    protected void setTheme() {
    }

    /**
     * 当radioButton 被选中时执行
     */
    public void onCheckedRb() {
        RadioButton radioButton = null;
        for (int i = 0; i < mRadioGroup.getChildCount(); i++) {
            radioButton = (RadioButton) mRadioGroup.getChildAt(i);
            if (radioButton.isChecked()) {
                radioButton.setTextColor(mActivity.getResources().getColor(R.color.trade_color1));
                showFragment(i);
                mCheckAt = i;
            } else {
                radioButton.setTextColor(mActivity.getResources().getColor(R.color.trade_color_white));
            }
        }
    }

    /**
     * 按照Fragment在{@link #mFragments}中的序号index，展示Fragment
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
    /**
     *点击左侧按钮
     */
    protected void onBackEvent() {
        mActivity.finish();
    }

    /**
     * 右侧按钮设置监听
     * @param mRightListener
     */
    public void setRightListener(View.OnClickListener mRightListener) {
        this.mRightListener = mRightListener;
    }


    class RRadioFragmentController extends AbsBaseController implements
            RadioGroup.OnCheckedChangeListener,View.OnClickListener{

        public RRadioFragmentController() {

        }

        @Override
        public void register(int i, View view) {
            switch (i) {
                case ON_CHECK_CHANGE:
                    ((RadioGroup) view).setOnCheckedChangeListener(this);
                    break;
                case ON_CLICK:
                    view.setOnClickListener(this);
                    break;
            }
        }
        @Override
        public void onClick(View v) {
            int resId = v.getId();
            if (resId == R.id.tv_radio_back) { // 如果是单击返回按钮
                onBackEvent();
            }
        }
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            onCheckedRb();
        }
    }
}
