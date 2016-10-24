package com.android.thinkive.invest_sd.beans;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * App版本信息实体类
 *
 * Announcements：
 *
 * @author 王志鸿
 * @corporation Thinkive
 * @date 2016/6/16
 */
public class AppVersionBean implements Serializable, Parcelable {

    /**
     * 版本名称
     */
    private String version_name;
    /**
     * 版本描述
     */
    private String description;
    /**
     * 版本安装包下载地址
     */
    private String download_url;
    /**
     * 是否强制更新(0:否，1：是)
     */
    private String update_flag;
    /**
     * 版本的内部版本号
     */
    private String version_code;
    /**
     * 版本的应用程序包名
     */
    private String soft_no;
    /**
     * 版本所属应用名称
     */
    private String soft_name;
    /**
     * 这个版本是否是一个H5版本，如果是H5版本，则不进行覆盖安装，只是对H5代码进行（增量）更新
     * （0：原生，1：h5）
     */
    private String isH5;
    /**
     * 是否是增量更新。(0:非增量更新 1：是增量更新)
     */
    private String incremental_update;

    public String getVersion_name() {
        return version_name;
    }

    public void setVersion_name(String version_name) {
        this.version_name = version_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDownload_url() {
        return download_url;
    }

    public void setDownload_url(String download_url) {
        this.download_url = download_url;
    }

    public String getUpdate_flag() {
        return update_flag;
    }

    public void setUpdate_flag(String update_flag) {
        this.update_flag = update_flag;
    }

    public String getVersion_code() {
        return version_code;
    }

    public void setVersion_code(String version_code) {
        this.version_code = version_code;
    }

    public String getSoft_no() {
        return soft_no;
    }

    public void setSoft_no(String soft_no) {
        this.soft_no = soft_no;
    }

    public String getSoft_name() {
        return soft_name;
    }

    public void setSoft_name(String soft_name) {
        this.soft_name = soft_name;
    }

    public String getIsH5() {
        return isH5;
    }

    public void setIsH5(String isH5) {
        this.isH5 = isH5;
    }

    public String getIncremental_update() {
        return incremental_update;
    }

    public void setIncremental_update(String incremental_update) {
        this.incremental_update = incremental_update;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.version_name);
        dest.writeString(this.description);
        dest.writeString(this.download_url);
        dest.writeString(this.update_flag);
        dest.writeString(this.version_code);
        dest.writeString(this.soft_no);
        dest.writeString(this.soft_name);
        dest.writeString(this.isH5);
        dest.writeString(this.incremental_update);
    }

    public AppVersionBean() {
    }

    protected AppVersionBean(Parcel in) {
        this.version_name = in.readString();
        this.description = in.readString();
        this.download_url = in.readString();
        this.update_flag = in.readString();
        this.version_code = in.readString();
        this.soft_no = in.readString();
        this.soft_name = in.readString();
        this.isH5 = in.readString();
        this.incremental_update = in.readString();
    }

    public static final Creator<AppVersionBean> CREATOR = new Creator<AppVersionBean>() {
        @Override
        public AppVersionBean createFromParcel(Parcel source) {
            return new AppVersionBean(source);
        }

        @Override
        public AppVersionBean[] newArray(int size) {
            return new AppVersionBean[size];
        }
    };
}
