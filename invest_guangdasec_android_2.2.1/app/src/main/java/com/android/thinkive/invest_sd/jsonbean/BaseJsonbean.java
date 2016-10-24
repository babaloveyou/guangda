package com.android.thinkive.invest_sd.jsonbean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xiangfan on 2015/7/14.
 */
public class BaseJsonbean implements Serializable {
    private String error_info;
    private int error_no;
    private List<String> dsName;

    public List<String> getDsName() {
        return dsName;
    }

    public void setDsName(List<String> dsName) {
        this.dsName = dsName;
    }

    public String getError_info() {
        return error_info;
    }

    public void setError_info(String error_info) {
        this.error_info = error_info;
    }

    public int getError_no() {
        return error_no;
    }

    public void setError_no(int error_no) {
        this.error_no = error_no;
    }
}
