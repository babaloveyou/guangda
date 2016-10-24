package com.thinkive.android.trade_bz.others;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.fragment.AbsBaseFragment;

import java.util.ArrayList;


/**
 * RadioGroup + Fragment模式下的标签页
 * 外部传入Fragments、布局ViewGroup容器；本类实现RadioGroup Tab效果
 * 提供RadioGroup的checkChange回调接口给外部
 * Announcements：实现本类的标签页效果，必须实现{@link #initViews()}方法
 *
 * @author 王志鸿
 * @company Thinkive
 * @date 2015/9/3
 */
public class RadioTabs {

    /**
     * 外部调用环境
     */
    private AppCompatActivity mActivity;
    /**
     * 实现选项卡切换功能的RadioGroup
     * 可以由外部传入，当外部没有传入时，则使用本类创建的隐形的RadioGroup对象，只有切换功能，没有视图
     */
    private RadioGroup mRadioGroup;
    /**
     * 实现tabs集合的Fragments集合
     */
    private ArrayList<AbsBaseFragment> mFragments;
    /**
     * tabs页集合填充的目标视图容器，
     * 由外部传入，想将tabs页集合放在哪个视图容器里，就传哪个视图容器进来
     */
    private ViewGroup mViewGroup;
    /**
     * Fragment管理器
     */
    private FragmentManager mFragmentManager;
    /**
     * tab页切换监听接口
     */
    private OnTabChangeListener mTabChangeListener;
    /**
     * 记录上次点击的RadioButton
     */
    private int checkRadioButtonId;
    public AbsBaseFragment mCurrentFragment;

    /**
     * 构造方法，初始化本类的部分变量
     *
     * @param activity  外部调用环境
     * @param viewGroup tabs页集合视图填充目标对象
     */
    public RadioTabs(AppCompatActivity activity, ViewGroup viewGroup) {
        mActivity = activity;
        mViewGroup = viewGroup;
        mFragmentManager = activity.getSupportFragmentManager();
        mRadioGroup = new RadioGroup(mActivity);
        checkRadioButtonId = 0;
    }

    /**
     * 初始化本类视图，实现本类标签页效果的必须方法
     */
    public void initViews() {
        int fragmentSize = mFragments.size();
        RadioButton tempRadioButton;
        //        AbsBaseFragment tempFragment;
        for (int i = 0; i < fragmentSize; i++) {
            tempRadioButton = new RadioButton(mActivity);
            tempRadioButton.setId(i);
        }

        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {


            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentTransaction transaction = mFragmentManager.beginTransaction();
                AbsBaseFragment fragment = mFragments.get(checkedId);
                // TODO：下面的动画效果会造成tab切换时卡顿，先去掉，后面再想办法
                //                if (checkRadioButtonId < checkedId) {
                //                    transitionToLeft(transaction);
                //                    checkRadioButtonId = checkedId;
                //                } else if (checkRadioButtonId > checkedId) {
                //                    transitionToRight(transaction);
                //                    checkRadioButtonId = checkedId;
                //                }
                //                transaction.replace(mViewGroup.getId(), fragment);
                // 在有动画效果的时候，这行代码如果没有的话，那么快速切换tab时，
                // FragmentManager会抛出java.lang.IllegalStateException: no activity异常，然后崩溃
                //                transaction.addToBackStack(null);
                if (mCurrentFragment != null) {
                    transaction.detach(mCurrentFragment);
                }
                if (fragment.isAdded) {
                    transaction.attach(fragment);
                } else {
                    transaction.add(mViewGroup.getId(), fragment);

                }
                mCurrentFragment = fragment;

                transaction.commit();
                // 已有tab监听接口时
                if (mTabChangeListener != null) {
                    mTabChangeListener.onTabChange(checkedId);
                }
            }
        });
    }

    /**
     * 向左滑动
     *
     * @param transaction
     */
    private void transitionToLeft(FragmentTransaction transaction) {
        transaction.setCustomAnimations(R.anim.right_in, R.anim.left_out, R.anim.left_in, R.anim.right_out);
    }

    /**
     * 向右滑动
     *
     * @param transaction
     */
    private void transitionToRight(FragmentTransaction transaction) {
        transaction.setCustomAnimations(R.anim.left_in, R.anim.right_out, R.anim.right_in, R.anim.left_out);
    }

    /**
     * 获取当前tab的index
     *
     * @return 当前tab的index
     */
    public int getCurTabIndex() {
        return mRadioGroup.getCheckedRadioButtonId();
    }

    /**
     * 设置当前tab为下标为index的tab
     *
     * @param index 设置后，当前tab的index
     */
    public void setCurTab(int index) {
        if (index >= 0 && index < mFragments.size()) {
            mRadioGroup.check(index);
        }
    }

    /**
     * 进入下一个（右边一个）tab
     */
    public void nextTab() {
        // 求横滑后的当前tab的index
        int curCheckIndex = getCurTabIndex() + 1;
        // 预防curCheckIndex越界
        if (curCheckIndex >= getTabsCount()) {
            curCheckIndex = 0;
        }
        setCurTab(curCheckIndex);
    }

    /**
     * 进入上一个（左边一个）tab
     */
    public void lastTab() {
        // 求横滑后的当前tab的index
        int curCheckIndex = getCurTabIndex() - 1;
        // 预防curCheckIndex越界
        if (curCheckIndex < 0) {
            curCheckIndex = getTabsCount() - 1;
        }
        setCurTab(curCheckIndex);
    }

    public void setTabChangeListener(OnTabChangeListener tabChangeListener) {
        mTabChangeListener = tabChangeListener;
    }

    public ArrayList<AbsBaseFragment> getFragments() {
        return mFragments;
    }

    public void setFragments(ArrayList<AbsBaseFragment> fragments) {
        mFragments = fragments;
    }

    public int getTabsCount() {
        return mFragments.size();
    }

    public RadioGroup getRadioGroup() {
        return mRadioGroup;
    }

    public void setRadioGroup(RadioGroup radioGroup) {
        mRadioGroup = radioGroup;
    }

    public interface OnTabChangeListener {
        void onTabChange(int position);
    }
}
