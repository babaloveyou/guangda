package com.android.thinkive.invest_sd.beans;

/**
 * <p>
 * 描述：
 * </p>
 *
 * @author 叶青
 * @version 1.0
 * @corporation 深圳市思迪信息科技有限公司
 * @date 2015/4/21
 */
public class NewsBean {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    //文章id
    private String id;

    //文章标题
    private String title;

    //文章内容
    private String content;

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    //图片url
    private String image_path;


    //发行日期
    private String publish_date;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublish_date() {
        return publish_date;
    }

    public void setPublish_date(String publish_date) {
        this.publish_date = publish_date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
