package com.android.thinkive.invest_sd.beans;

import java.io.Serializable;

/**
 * Created by xiangfan on 2015/11/10.
 */
public class FristPageNoticeBean implements Serializable {
    private String content;
    private String author;
    private int article_id;
    private String title;
    private String create_date;
    private String publish_date;
    private String create_by;
    private String rownum_;
    private String link_url;
    private String state;
    private String brief;
    private String rel_keyword;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getArticle_id() {
        return article_id;
    }

    public void setArticle_id(int article_id) {
        this.article_id = article_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public String getPublish_date() {
        return publish_date;
    }

    public void setPublish_date(String publish_date) {
        this.publish_date = publish_date;
    }

    public String getCreate_by() {
        return create_by;
    }

    public void setCreate_by(String create_by) {
        this.create_by = create_by;
    }

    public String getRownum_() {
        return rownum_;
    }

    public void setRownum_(String rownum_) {
        this.rownum_ = rownum_;
    }

    public String getLink_url() {
        return link_url;
    }

    public void setLink_url(String link_url) {
        this.link_url = link_url;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getRel_keyword() {
        return rel_keyword;
    }

    public void setRel_keyword(String rel_keyword) {
        this.rel_keyword = rel_keyword;
    }
}
