package com.android.thinkive.invest_sd.jsonbean;

import com.android.thinkive.invest_sd.beans.RecommendBean;

import java.util.List;

/**
 * Created by xiangfan on 2015/9/22.
 */
public class RecommendResultBean {
    private List<RecommendBean> data;

    public List<RecommendBean> getData() {
        return data;
    }

    public void setData(List<RecommendBean> data) {
        this.data = data;
    }
}
