package com.android.thinkive.invest_sd.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.thinkive.framework.util.PreferencesUtil;
import com.android.thinkive.invest_sd.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by zhuduanchang on 2015/6/19.
 * 引导页
 */
public class GuideActivity extends AppCompatActivity {
    ViewPager mViewpager;
    SectionsPagerAdapter mPagerAdapter;
    private ImageView[] dots ;
    private List<View> views;
    private int currentIndex;
    private  Button button;
    public int[] images = new int[]{R.drawable.ic_start_a, R.drawable.ic_start_b, R.drawable.ic_start_c, R.drawable.ic_start_d,};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initView();
    }

    protected void initView() {
        mViewpager = (ViewPager) findViewById(R.id.guide_pager);
        mPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewpager.setAdapter(mPagerAdapter);
        button = (Button) findViewById(R.id.but_goto_main);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PreferencesUtil.putBoolean(GuideActivity.this, LauncherActivity.IS_FIRST_LAUNCHER, false);
                Intent intent = new Intent(GuideActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        mViewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                setCurDot(i);
                if (i == mPagerAdapter.getCount() - 1) {
                    button.setVisibility(View.VISIBLE);
                } else {
                    button.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        views = new ArrayList<View>();
        ViewGroup.LayoutParams mParams = new ViewGroup.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        for (int i = 0; i < images.length; i++) {
            ImageView iv = new ImageView(this);
            iv.setLayoutParams(mParams);
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            iv.setImageResource(images[i]);
//                if (i == pics.length-1) {
//                    iv.setListener(new StartItemImageView.Startlistener() {
//                        @Override
//                        public void start() {
//                          finish();
//                        }
//                    });
//                }
            views.add(iv);
            initDots(true);
        }
    }
    private void initDots(boolean isneed) {

        LinearLayout ll = (LinearLayout) findViewById(R.id.ll);
        if(isneed) {
            dots = new ImageView[images.length];

            for (int i = 0; i < images.length; i++) {
                dots[i] = (ImageView) ll.getChildAt(i);
                dots[i].setEnabled(true);
                dots[i].setTag(i);
            }

            currentIndex = 0;
            dots[currentIndex].setEnabled(false);
        }else{
            ll.setVisibility(View.GONE);
        }
    }


    private void setCurDot(int positon)
    {
        if (positon < 0 || positon > images.length - 1 || currentIndex == positon) {
            return;
        }

        dots[positon].setEnabled(false);
        dots[currentIndex].setEnabled(true);

        currentIndex = positon;
    }


    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = new GuideFragment(GuideActivity.this, position);
            return fragment;
        }

        @Override
        public int getCount() {
            return images != null ? images.length : 0;
        }
    }

    @SuppressLint("ValidFragment")
    public class GuideFragment extends Fragment{
        int index;
        private Context mContext;
        public GuideFragment(Context context,int position) {
            this.mContext = context;
            this.index = position;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            final View containerView = inflater.inflate(R.layout.fragment_guide, container, false);
            ImageView mImageView = (ImageView)containerView.findViewById(R.id.imageview);
            mImageView.setImageResource(images[index]);
            return containerView;
        }
    }

}
