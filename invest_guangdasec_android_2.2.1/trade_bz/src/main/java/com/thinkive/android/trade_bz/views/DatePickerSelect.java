package com.thinkive.android.trade_bz.views;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.TextView;

import com.thinkive.android.trade_bz.utils.TradeUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by xuemei on 2015/11/23.
 *  日期选择器
 */
public class DatePickerSelect {
    private Context mContext;
    private View mView;

    public DatePickerSelect(Context context) {
        mContext=context;
    }

    /**
     * 设置显示日期对话框
     */
    public void showDateDialog(View view,String initDate) {
        if (TradeUtils.isFastClick()) {
            return;
        }
        mView=view;
        Calendar c=Calendar.getInstance();
        int year=c.get(Calendar.YEAR);
        int mouth=c.get(Calendar.MONTH);
        int day=c.get(Calendar.DAY_OF_MONTH);
        if(!TextUtils.isEmpty(initDate)){
            String[] subString = initDate.split("-"); //将initDate分解成几个小的字符串,分隔符是"-";
            year=Integer.parseInt(subString[0]);
            mouth=Integer.parseInt(subString[1])-1;
            day=Integer.parseInt(subString[2]);
        }
        final DatePickerDialog dialog=new DatePickerDialog(mContext, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                ((TextView)mView).setText(year+"-"+values(monthOfYear+1)+"-"+values(dayOfMonth));
            }
        }, year, mouth, day);
        dialog.show();

        //下面是禁止在弹出日期选择框的时候，自动弹出键盘
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
                View v = dialog.getWindow().getCurrentFocus();
                if (null != v && null != v.getWindowToken()) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }
            }
        });
    }

    public String values(int n){
        return n<10 ? "0"+n : String.valueOf(n);
    }

    /**
     * 获取系统当前时间
     */
    public String onGetCurDate(){
        SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        return sDateFormat.format(new java.util.Date());
    }
}
