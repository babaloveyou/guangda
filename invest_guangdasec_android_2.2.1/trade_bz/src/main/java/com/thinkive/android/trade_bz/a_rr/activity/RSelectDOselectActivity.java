package com.thinkive.android.trade_bz.a_rr.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_rr.fragment.RSelectDOselectFragment;
import com.thinkive.android.trade_bz.a_stock.activity.AbsNavBarActivity;
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
 * 融资融券--查询--交割单（303027）
 * Announcements：
 * @author 张雪梅
 * @company Thinkive
 * @date 2015/8/19
 */

public class RSelectDOselectActivity extends AbsNavBarActivity {
    private RSelectDOselectFragment mFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initViews();
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
                    Constants.CREDIT_BEGIN_DATE = beginTime;
                    Constants.CREDIT_TOTAY_DATE = date;
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
    protected void initViews() {
        addFragment(R.id.fl_container, mFragment);
        setTitleText(R.string.r_do_title);
        setBackBtnVisibility(View.VISIBLE);
    }

    @Override
    protected void initData() {
        mFragment = new RSelectDOselectFragment();
    }
}


