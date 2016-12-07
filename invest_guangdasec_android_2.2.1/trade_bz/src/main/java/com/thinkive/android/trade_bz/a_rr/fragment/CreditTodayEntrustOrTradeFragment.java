package com.thinkive.android.trade_bz.a_rr.fragment;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.fragment.AbsBaseFragment;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.others.constants.Constants;
import com.thinkive.android.trade_bz.request.Request306000;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by Administrator on 2016/11/28.
 */
public class CreditTodayEntrustOrTradeFragment extends AbsBaseFragment implements View.OnClickListener {
    private View mView;
    private TextView mLeftTab;
    private TextView mRightTab;
    private boolean isLeft = true;
    private CreditTodayEntrustFragment fragment1 = null;
    private CreditTodayTradeFragment fragment2 = null;

    private Drawable mBgLeftSelect;
    private Drawable mBgRightSelect;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_today_entrust_or_trade, null);
            findViews(mView);
            initData();
            setListeners();
            initViews();
        }
        return mView;
    }

    @Override
    public void onResume() {
        super.onResume();
        processRequst360000();
    }


    private void processRequst360000() {
        HashMap<String, String> map306000 = new HashMap<String, String>();
        new Request306000(map306000, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                String date = bundle.getString(Request306000.BUNDLE_KEY_306000);
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                String beginTime = null;
                try {
                    Date dateTimeStamp = format.parse(date);
                    Calendar beginCalendar = Calendar.getInstance();
                    beginCalendar.setTime(dateTimeStamp);
                    beginCalendar.add(Calendar.HOUR, -Constants.TIME_LIMIT * 24);
                    beginTime = format.format(beginCalendar.getTime());
                    Constants.CREDIT_BEGIN_DATE =beginTime;
                    Constants.CREDIT_TOTAY_DATE =date;
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(Request306000.ERROR_INFO));
            }
        }).request();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_tab_left) {
            showLeft();
        } else if (v.getId() == R.id.tv_tab_right) {
            showRight();
        }
    }

    @Override
    protected void findViews(View view) {
        mLeftTab = (TextView) mView.findViewById(R.id.tv_tab_left);
        mRightTab = (TextView) mView.findViewById(R.id.tv_tab_right);
    }

    @Override
    protected void setListeners() {
        mLeftTab.setOnClickListener(this);
        mRightTab.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initViews() {
        mBgLeftSelect = getResources().getDrawable(R.drawable.shape_bg_tab_trade_left_selected);
        mBgRightSelect = getResources().getDrawable(R.drawable.shape_bg_tab_trade_right_selected);
        fragment1 = new CreditTodayEntrustFragment();
        fragment1.setName(this.getResources().getString(R.string.today_entrust));
        fragment2 = new CreditTodayTradeFragment();
        fragment2.setName(this.getResources().getString(R.string.today_trade));
        getChildFragmentManager().beginTransaction().add(R.id.fl_container, fragment1).commit();
        getChildFragmentManager().beginTransaction().add(R.id.fl_container, fragment2).commit();
        Bundle arguments = getArguments();
        int childePos = 0;
        if (arguments != null) {
            childePos = arguments.getInt("childePos");
        }
        if (childePos == 0) {
            getChildFragmentManager().beginTransaction().hide(fragment2).commit();
            mLeftTab.setTextColor(Color.WHITE);
            mRightTab.setTextColor(getResources().getColor(R.color.statusbar_main));
            mLeftTab.setBackgroundDrawable(mBgLeftSelect);
        }
        if (childePos == 1) {
            getChildFragmentManager().beginTransaction().hide(fragment1).commit();
            mRightTab.setTextColor(Color.WHITE);
            mLeftTab.setTextColor(getResources().getColor(R.color.statusbar_main));
            mRightTab.setBackgroundDrawable(mBgRightSelect);
        }

    }

    public boolean isLeft() {
        return isLeft;
    }

    @Override
    protected void setTheme() {

    }

    private void showRight() {
        isLeft = false;
        mRightTab.setBackgroundDrawable(mBgRightSelect);
        mLeftTab.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_bg_tab_trade_left_normal));
        mLeftTab.setTextColor(getResources().getColor(R.color.statusbar_main));
        mRightTab.setTextColor(Color.WHITE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            getChildFragmentManager().beginTransaction().show(fragment2).commit();
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            getChildFragmentManager().beginTransaction().hide(fragment1).commit();
        }
    }

    private void showLeft() {
        isLeft = true;
        mLeftTab.setBackgroundDrawable(mBgLeftSelect);
        mRightTab.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_bg_tab_trade_right_normal));
        mRightTab.setTextColor(getResources().getColor(R.color.statusbar_main));
        mLeftTab.setTextColor(Color.WHITE);
        getChildFragmentManager().beginTransaction().show(fragment1).commit();
        getChildFragmentManager().beginTransaction().hide(fragment2).commit();
    }

}

