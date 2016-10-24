package com.thinkive.android.trade_bz.a_option.bean;

import android.os.Parcel;

import com.thinkive.android.trade_bz.a_stock.bean.BaseBean;
import com.thinkive.android.trade_bz.others.JsonKey;

/**
 *
 * Description：期权代码信息查询(305001) 行权价格 <br>
 * Author：晏政清 <br>
 * Corporation：深圳市思迪信息技术股份有限公司 <br>
 * Date：2016/7/19 <br>
 */
public class OptionExercisePriceBean extends BaseBean{
    @JsonKey("exercise_price")
    private String exercise_price="";//行权价格

    public String getExercise_price() {
        return exercise_price;
    }

    public void setExercise_price(String exercise_price) {
        this.exercise_price = exercise_price;
    }

    public OptionExercisePriceBean() {
    }

    public OptionExercisePriceBean(Parcel in) {
        this.exercise_price = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.exercise_price);
    }

    public static final Creator<OptionExercisePriceBean> CREATOR = new Creator<OptionExercisePriceBean>() {
        @Override
        public OptionExercisePriceBean createFromParcel(Parcel parcel) {
            return new OptionExercisePriceBean(parcel);
        }

        @Override
        public OptionExercisePriceBean[] newArray(int i) {
            return new OptionExercisePriceBean[i];
        }
    };
}
