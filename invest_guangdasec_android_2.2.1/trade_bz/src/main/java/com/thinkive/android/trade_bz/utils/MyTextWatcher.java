package com.thinkive.android.trade_bz.utils;

import android.text.Editable;
import android.text.TextWatcher;

/**
 * Created by Administrator on 2016/12/1.
 */

public abstract class MyTextWatcher implements TextWatcher {

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        doAfterChange(s);
    }

    public abstract void doAfterChange(Editable s);
}
