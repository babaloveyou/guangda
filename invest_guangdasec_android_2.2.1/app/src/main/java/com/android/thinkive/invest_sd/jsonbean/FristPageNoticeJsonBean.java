package com.android.thinkive.invest_sd.jsonbean;

import com.android.thinkive.invest_sd.beans.FristPageNoticeBean;
import com.android.thinkive.invest_sd.jsonbean.BaseJsonbean;

import java.util.List;

/**
 * Created by xiangfan on 2015/11/10.
 */
public class FristPageNoticeJsonBean extends BaseJsonbean {

    List<FristPageNoticeBean> results;

    public List<FristPageNoticeBean> getResults() {
        return results;
    }

    public void setResults(List<FristPageNoticeBean> results) {
        this.results = results;
    }
}
