package com.android.thinkive.invest_sd.jsonbean;

import com.android.thinkive.invest_sd.beans.AppVersionBean;

import java.util.List;

/**
 * Created by xiangfan on 2016/8/15.
 */
public class AppVersionJsonBean extends BaseJsonbean {

    public List<AppVersionBean> getResults() {
        return results;
    }

    public void setResults(List<AppVersionBean> results) {
        this.results = results;
    }

    List<AppVersionBean> results;

 }
