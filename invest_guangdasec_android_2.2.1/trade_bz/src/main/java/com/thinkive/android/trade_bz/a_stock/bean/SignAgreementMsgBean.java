package com.thinkive.android.trade_bz.a_stock.bean;


import com.thinkive.android.trade_bz.others.JsonKey;


/**
 *  退市板块协议查询(300116)
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/8/1
 */

public class SignAgreementMsgBean extends BaseBean {
    @JsonKey("id")
    private String id = ""; //协议id
    @JsonKey("title")
    private String title = ""; //协议标题
    @JsonKey("type")
    private String type = ""; //协议类型
    @JsonKey("content")
    private String content = ""; //协议内容

    public SignAgreementMsgBean() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
