package com.thinkive.android.trade_bz.a_stock.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.fragment.HistoryEntrustFragment;
import com.thinkive.android.trade_bz.a_stock.fragment.HistoryTradeFragment;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.others.constants.Constants;
import com.thinkive.android.trade_bz.request.Request306001;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

/**
 * 历史成交、历史委托所在的Activity
 *
 * @author 张雪梅
 * @company Thinkive
 * @date 15/8/3
 */
public class HistoryEntrustOrTradeActivity extends AbsNavBarActivity implements View.OnClickListener {
    private TextView mLeftTab;
    private TextView mRightTab;
    private boolean isLeft = true;
    private HistoryTradeFragment fragment1 = null;
    private HistoryEntrustFragment fragment2 = null;

    private Drawable mBgLeftSelect;
    private Drawable mBgRightSelect;
    private ImageView mBackIv;
    private FrameLayout mHeaderFl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_entrust_or_trade);
        findViews();
        initData();
        setListeners();
        initViews();
        processRequst360001();
    }

    private void processRequst360001() {
        HashMap<String, String> map306001 = new HashMap<String, String>();
        new Request306001(map306001, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                String date = bundle.getString(Request306001.BUNDLE_KEY_306001);
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                String beginTime = null;
                try {
                    Date dateTimeStamp = format.parse(date);
                    Calendar beginCalendar = Calendar.getInstance();
                    beginCalendar.setTime(dateTimeStamp);
                    beginCalendar.add(Calendar.HOUR, -Constants.TIME_LIMIT * 24);
                    beginTime = format.format(beginCalendar.getTime());
                    Constants.HISTORY_START_DATE =beginTime;
                    Constants.HISTORY_END_DATE =date;
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(Request306001.ERROR_INFO));
            }
        }).request();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Constants.HISTORY_START_DATE =null;
        Constants.HISTORY_END_DATE =null;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_tab_left) {
            showLeft();
        } else if (v.getId() == R.id.tv_tab_right) {
            showRight();
        } else if (v.getId() == R.id.iv_back) {
            finish();
        }
    }

    @Override
    protected void findViews() {
        mLeftTab = (TextView) findViewById(R.id.tv_tab_left);
        mRightTab = (TextView) findViewById(R.id.tv_tab_right);
        mBackIv = (ImageView) findViewById(R.id.iv_back);
        mHeaderFl = (FrameLayout) findViewById(R.id.fl_header);

    }

    @Override
    protected void setListeners() {
        mLeftTab.setOnClickListener(this);
        mRightTab.setOnClickListener(this);
        mBackIv.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initViews() {
        View view = new View(this);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, getStatusBarHeight());
        view.setLayoutParams(layoutParams);
        mHeaderFl.addView(view);
        mBgLeftSelect = getResources().getDrawable(R.drawable.shape_bg_trade_tab_normal_select);
        mBgRightSelect = getResources().getDrawable(R.drawable.shape_bg_trade_tab_credit_select);
        fragment1 = new HistoryTradeFragment();
        fragment1.setName(this.getResources().getString(R.string.history_trade_title));
        fragment2 = new HistoryEntrustFragment();
        fragment2.setName(this.getResources().getString(R.string.history_entrust_title));
        getSupportFragmentManager().beginTransaction().add(R.id.fl_container, fragment1).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.fl_container, fragment2).commit();
        Intent intent = getIntent();
        int childePos = 0;
        if (intent != null) {
            childePos = intent.getIntExtra("childePos", 0);
        }
        if (childePos == 0) {
            getSupportFragmentManager().beginTransaction().hide(fragment2).commit();
            mRightTab.setTextColor(Color.WHITE);
            mLeftTab.setTextColor(getResources().getColor(R.color.statusbar_main));
            mLeftTab.setBackgroundDrawable(mBgLeftSelect);
        }
        if (childePos == 1) {
            getSupportFragmentManager().beginTransaction().hide(fragment1).commit();
            mLeftTab.setTextColor(Color.WHITE);
            mRightTab.setTextColor(getResources().getColor(R.color.statusbar_main));
            mRightTab.setBackgroundDrawable(mBgRightSelect);
        }

    }

    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public boolean isLeft() {
        return isLeft;
    }


    private void showRight() {
        isLeft = false;
        mRightTab.setBackgroundDrawable(mBgRightSelect);
        mLeftTab.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_bg_trade_tab_normal));
        mRightTab.setTextColor(getResources().getColor(R.color.statusbar_main));
        mLeftTab.setTextColor(Color.WHITE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            getSupportFragmentManager().beginTransaction().show(fragment2).commit();
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            getSupportFragmentManager().beginTransaction().hide(fragment1).commit();
        }
    }

    private void showLeft() {
        isLeft = true;
        mLeftTab.setBackgroundDrawable(mBgLeftSelect);
        mRightTab.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_bg_trade_tab_credit));
        mLeftTab.setTextColor(getResources().getColor(R.color.statusbar_main));
        mRightTab.setTextColor(Color.WHITE);
        getSupportFragmentManager().beginTransaction().show(fragment1).commit();
        getSupportFragmentManager().beginTransaction().hide(fragment2).commit();
    }

}
