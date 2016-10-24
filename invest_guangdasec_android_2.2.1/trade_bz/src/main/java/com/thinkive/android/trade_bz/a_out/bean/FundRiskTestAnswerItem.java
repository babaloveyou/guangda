package com.thinkive.android.trade_bz.a_out.bean;

import com.thinkive.android.trade_bz.a_stock.bean.BaseBean;
import com.thinkive.android.trade_bz.others.JsonKey;

/**
 * 风险测评问卷中，问题答案选项的实体类
 * @author 王志鸿
 * @company Thinkive
 * @date 2015/8/7
 */
public class FundRiskTestAnswerItem extends BaseBean {

    @JsonKey("answer_no")
    private String answer_no = "";
    @JsonKey("answer_content")
    private String answer_content = "";
    @JsonKey("question_no")
    private String question_no = "";
    @JsonKey("score")
    private String score = "";

    public String getAnswer_no() {
        return answer_no;
    }

    public void setAnswer_no(String answer_no) {
        this.answer_no = answer_no;
    }

    public String getQuestion_no() {
        return question_no;
    }

    public void setQuestion_no(String question_no) {
        this.question_no = question_no;
    }

    public String getAnswer_content() {
        return answer_content;
    }

    public void setAnswer_content(String answer_content) {
        this.answer_content = answer_content;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
