package com.thinkive.android.trade_bz.a_stock.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class BaseBean implements Serializable, Parcelable {
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
