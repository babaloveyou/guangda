package com.thinkive.android.trade_bz.a_option.bean;

import android.os.Parcel;

import com.thinkive.android.trade_bz.a_stock.bean.BaseBean;
import com.thinkive.android.trade_bz.others.JsonKey;

/**
 *
 * Description： 期权代码信息查询(305001) 行权到期月份 <br>
 * Author：晏政清 <br>
 * Corporation：深圳市思迪信息技术股份有限公司 <br>
 * Date：2016/7/19 <br>
 */
public class OptionExerciseEndDateBean extends BaseBean{
    @JsonKey("exe_end_date")
    private String exe_end_date="";//行权到期月份


    public String getExe_end_date() {
        return exe_end_date;
    }

    public void setExe_end_date(String exe_end_date) {
        this.exe_end_date = exe_end_date;
    }

    public OptionExerciseEndDateBean() {
    }

    public OptionExerciseEndDateBean(Parcel in) {
        this.exe_end_date = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.exe_end_date);
    }

    public static final Creator<OptionExerciseEndDateBean> CREATOR = new Creator<OptionExerciseEndDateBean>() {
        @Override
        public OptionExerciseEndDateBean createFromParcel(Parcel parcel) {
            return new OptionExerciseEndDateBean(parcel);
        }

        @Override
        public OptionExerciseEndDateBean[] newArray(int i) {
            return new OptionExerciseEndDateBean[i];
        }
    };
}
