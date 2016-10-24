package com.android.thinkive.invest_sd.jsonbean;

import java.util.List;

/**
 * Created by xiangfan on 2015/11/5.
 */
public class NewsJsonResultBean  extends BaseJsonbean {
    private List<NewsJsonBean> results;

    public List<NewsJsonBean> getResults() {
        return results;
    }

    public void setResults(List<NewsJsonBean> results) {
        this.results = results;
    }
}
