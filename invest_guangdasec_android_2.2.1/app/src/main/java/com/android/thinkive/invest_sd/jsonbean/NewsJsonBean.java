package com.android.thinkive.invest_sd.jsonbean;


import com.android.thinkive.invest_sd.beans.NewsBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiangfan on 2015/11/5.
 */
public class NewsJsonBean  {
    public int getNumPerPage() {
        return numPerPage;
    }

    public void setNumPerPage(int numPerPage) {
        this.numPerPage = numPerPage;
    }

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getLastIndex() {
        return lastIndex;
    }

    public void setLastIndex(int lastIndex) {
        this.lastIndex = lastIndex;
    }

    public List<NewsBean> getData() {
        return data;
    }

    public void setData(List<NewsBean> data) {
        this.data = data;
    }

    private int numPerPage;
    private int totalRows;
    private int totalPages;
    private int currentPage;
    private int startIndex;
    private int lastIndex;
    private List<NewsBean> data;
}
