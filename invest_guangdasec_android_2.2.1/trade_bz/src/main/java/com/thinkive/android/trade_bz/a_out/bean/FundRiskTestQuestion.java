package com.thinkive.android.trade_bz.a_out.bean;

import com.thinkive.android.trade_bz.a_stock.bean.BaseBean;
import com.thinkive.android.trade_bz.others.JsonKey;

import java.util.ArrayList;

/**
 * 风险测评问卷中，问题实体类，附带问题的答案选项
 * @author 王志鸿
 * @company Thinkive
 * @date 2015/8/7
 */
public class FundRiskTestQuestion extends BaseBean {

    @JsonKey("question_no")
    private String question_no = "";
    @JsonKey("question_content")
    private String question_content = "";

    /**
     * 用户选择的答案
     */
    private String select_answer;

    /**
     * 用户选择的RadioButton的id
     */
    private int checked_answer;

    private ArrayList<FundRiskTestAnswerItem> answers;

    public String getQuestion_no() {
        return question_no;
    }

    public void setQuestion_no(String question_no) {
        this.question_no = question_no;
    }

    public String getQuestion_content() {
        return question_content;
    }

    public void setQuestion_content(String question_content) {
        this.question_content = question_content;
    }

    public ArrayList<FundRiskTestAnswerItem> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<FundRiskTestAnswerItem> answers) {
        this.answers = answers;
    }

    public String getSelect_answer() {
        return select_answer;
    }

    public void setSelect_answer(String select_answer) {
        this.select_answer = select_answer;
    }

    public int getChecked_answer() {
        return checked_answer;
    }

    public void setChecked_answer(int checked_answer) {
        this.checked_answer = checked_answer;
    }
}
