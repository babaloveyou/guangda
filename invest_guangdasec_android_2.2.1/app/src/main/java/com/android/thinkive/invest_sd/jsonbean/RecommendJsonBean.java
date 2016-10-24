package com.android.thinkive.invest_sd.jsonbean;

import java.util.List;

/**
 * Created by xiangfan on 2015/9/22.
 */
public class RecommendJsonBean extends BaseJsonbean {

   private  List<RecommendResultBean> results;

    public List<RecommendResultBean>  getResults() {
        return results;
    }

    public void setResults(List<RecommendResultBean>  results) {
        this.results = results;
    }
}
