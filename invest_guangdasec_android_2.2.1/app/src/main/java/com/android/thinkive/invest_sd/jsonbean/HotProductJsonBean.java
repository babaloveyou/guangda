package com.android.thinkive.invest_sd.jsonbean;

import com.android.thinkive.invest_sd.beans.HotProductBean;

import java.util.List;

/**
 * Created by xiangfan on 2016/7/12.
 */
public class HotProductJsonBean extends BaseJsonbean {

    public List<HotProductBean> getResults() {
        return results;
    }

    public void setResults(List<HotProductBean> results) {
        this.results = results;
    }

    private List<HotProductBean> results;
}
