package com.thinkive.android.trade_bz.views;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 *
 * Created by xuemei on 2016/3/22.
 * 可以控制输入的位数
 */
public class EditTextLimit extends EditText implements TextWatcher {

    private int mLeftNum = 0;
    private int mRightNum = 0;

    public EditTextLimit(Context context) {
        super(context);
    }

    public EditTextLimit(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EditTextLimit(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void afterTextChanged(Editable s) {


    }
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if(mLeftNum > 0) {
            if (!s.toString().contains(".")) {
                if (s.length() > mLeftNum) {
                    s = s.subSequence(0, mLeftNum);
                    this.setText(s);
                    this.setSelection(s.length());
                }
            }
        }
        //限制小数位的位数
        if(mRightNum > 0) {
            if (s.toString().contains(".")) {
                if (s.length() - 1 - s.toString().indexOf(".") > mRightNum) {
                    s = s.toString().subSequence(0,
                            s.toString().indexOf(".") + mRightNum + 1);
                    this.setText(s);
                    this.setSelection(s.length());
                }
            }
        }
        //当输入的第一位是小数点，前方自动补“0”
        if (s.toString().trim().substring(0).equals(".")) {
            s = "0" + s;
            this.setText(s);
            this.setSelection(2);
        }
        //当输入的第一位是0，限制整数位只能输入一位
        if (s.toString().startsWith("0") && s.toString().trim().length() > 1){
            if (!s.toString().substring(1, 2).equals(".")) {
                this.setText(s.subSequence(0, 1));
                this.setSelection(1);
                return;
            }
        }

    }

    /**
     * 设置保留位数
     * @param left  整数位
     * @param right 小数位
     */
    public void setLimitNum(int left ,int right){
        mLeftNum = left;
        mRightNum = right;
    }
}
