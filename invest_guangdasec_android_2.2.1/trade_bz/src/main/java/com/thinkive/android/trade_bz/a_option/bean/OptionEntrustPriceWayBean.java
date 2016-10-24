package com.thinkive.android.trade_bz.a_option.bean;

import android.os.Parcel;

import com.thinkive.android.trade_bz.a_stock.bean.BaseBean;
import com.thinkive.android.trade_bz.others.JsonKey;

/**
 *
 * Description：该实体用于存放 委托报价方式的相关信息 <br>
 * Author：晏政清 <br>
 * Corporation：深圳市思迪信息技术股份有限公司 <br>
 * Date：2016/7/22 <br>
 */
public class OptionEntrustPriceWayBean extends BaseBean{
    @JsonKey("entrust_way_code")
    private String entrust_way_code = "";//报价方式字典代码
    @JsonKey("entrust_way_name")
    private String entrust_way_name = "";//报价方式名称
    @JsonKey("entrust_way_type")
    private int entrust_way_type = 0;//报价方式的类型，因为限价和定价的价格输入方式不同,0为限价输入，1为市价输入，-1为类型不明

    public String getEntrust_way_code() {
        return entrust_way_code;
    }

    public void setEntrust_way_code(String entrust_way_code) {
        this.entrust_way_code = entrust_way_code;
    }

    public String getEntrust_way_name() {
        return entrust_way_name;
    }

    public void setEntrust_way_name(String entrust_way_name) {
        this.entrust_way_name = entrust_way_name;
    }

    public int getEntrust_way_type() {
        return entrust_way_type;
    }

    public void setEntrust_way_type(int entrust_way_type) {
        this.entrust_way_type = entrust_way_type;
    }

    public OptionEntrustPriceWayBean() {
    }

    public OptionEntrustPriceWayBean(String entrust_way_code, String entrust_way_name, int entrust_way_type) {
        this.entrust_way_code = entrust_way_code;
        this.entrust_way_name = entrust_way_name;
        this.entrust_way_type = entrust_way_type;
    }

    public OptionEntrustPriceWayBean(Parcel in) {
        this.entrust_way_code=in.readString();
        this.entrust_way_name=in.readString();
        this.entrust_way_type=in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.entrust_way_code);
        dest.writeString(this.entrust_way_name);
        dest.writeInt(this.entrust_way_type);
    }

    public static final Creator<OptionEntrustPriceWayBean> CREATOR =  new Creator<OptionEntrustPriceWayBean>() {
        @Override
        public OptionEntrustPriceWayBean createFromParcel(Parcel parcel) {
            return new OptionEntrustPriceWayBean(parcel);
        }

        @Override
        public OptionEntrustPriceWayBean[] newArray(int i) {
            return new OptionEntrustPriceWayBean[1];
        }
    };
}
