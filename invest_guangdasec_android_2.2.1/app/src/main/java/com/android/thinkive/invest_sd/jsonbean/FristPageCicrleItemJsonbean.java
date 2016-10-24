package com.android.thinkive.invest_sd.jsonbean;


import com.android.thinkive.invest_sd.beans.FristPageCicrleItemBean;

import java.util.List;

/**
 * Created by xiangfan on 2015/7/14.
 */
public class FristPageCicrleItemJsonbean extends BaseJsonbean {

    private List<FristPageCicrleItemBean> results;
    public List<FristPageCicrleItemBean> getResults() {
        return results;
    }

    public void setResults(List<FristPageCicrleItemBean> results) {
        this.results = results;
    }

    public boolean mathcingnewvalue(FristPageCicrleItemJsonbean newbean){
        try {
            if (newbean == null || newbean.getResults() == null || newbean.getResults().size() <= 0 || newbean.getResults().size() != this.results.size()) {
                return false;
            }
            for (int i = 0; i < newbean.getResults().size(); i++) {
                if (!this.getResults().get(i).mathcingnewvalue(newbean.getResults().get(i))) {
                    return false;
                }
            }
        }catch (Exception e){
            return true;
        }
        return true;
    }
}
