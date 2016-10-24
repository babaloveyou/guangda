package com.thinkive.android.trade_bz.request;

import android.os.Bundle;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_out.bean.FundRiskTestAnswerItem;
import com.thinkive.android.trade_bz.a_out.bean.FundRiskTestQuestion;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.others.constants.Constants;
import com.thinkive.android.trade_bz.utils.JsonParseUtils;
import com.thinkive.android.trade_bz.utils.LogUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 基金查询-风险测评-风险测评问卷查询
 * @author 王志鸿
 * @company Thinkive
 * @date 2015/8/7
 */
public class Fund300105 extends BaseNormalRequest {

    public static final String BUNDLE_KEY_300105 = "bundle_key_300105";

    public Fund300105(HashMap<String, String> paramMap, IRequestAction action) {
        super(action);
        paramMap.put("funcNo", "300105");
        setParamHashMap(paramMap);
        setUrlName(Constants.URL_TRADE);
    }

    @Override
    void getJsonDataWithoutError(JSONObject jsonObject) {
        LogUtil.printLog("d","Fund300105"+jsonObject.toString());
        Bundle bundle = new Bundle();
        try {
            // 获取存放结果集的json key
            String resultTarget = jsonObject.getJSONArray("dsName").get(0).toString();
            // 获取结果集
            JSONArray jsonArray = jsonObject.getJSONArray(resultTarget);
            // 解析出所有的答案选项
            ArrayList<FundRiskTestAnswerItem> allAnswers =
                    JsonParseUtils.createBeanList(FundRiskTestAnswerItem.class, jsonArray);
            // 解析出所有的问题
            ArrayList<FundRiskTestQuestion> allQuestions =
                    JsonParseUtils.createBeanList(FundRiskTestQuestion.class, jsonArray);
            // 如果并没有解析到数据
            if (allQuestions==null||allAnswers==null
                    ||allQuestions.size()==0||allAnswers.size()==0) {
                bundle.putString(ERROR_INFO, mContext.getString(R.string.data_error));
                transferAction(REQUEST_FAILED, bundle);
            }
            // 循环去掉重复的问题
            // 设置循环临时变量
            int allQuestionsLength = allQuestions.size();
            FundRiskTestQuestion tempQuestion = null;
            for (int i=0; i<allQuestionsLength; i++) {
                if (tempQuestion!=null && tempQuestion.getQuestion_no().equals(allQuestions.get(i).getQuestion_no())) {
                    allQuestions.remove(i);
                    i--;
                    allQuestionsLength--;
                }
                tempQuestion = allQuestions.get(i);
            }
            // 设置循环临时变量
            int allAnswersLength = allAnswers.size();
            FundRiskTestAnswerItem tempAnswer;
            // 循环所有问题，设置每个问题的答案选项到问题实体类中
            for (FundRiskTestQuestion question : allQuestions) {
                ArrayList<FundRiskTestAnswerItem> answers = new ArrayList<FundRiskTestAnswerItem>();
                question.setAnswers(answers);
                // 遍历找到属于当前问题的所有答案选项
                for (int i = 0; i < allAnswersLength; i++) {
                    tempAnswer = allAnswers.get(i);
                    // 如果找到了一个答案选项属于当前问题，那么将它从所有答案集合中移除，
                    // 以防下次再次遍历到它，导致多余的遍历。
                    // 因为已经属于一个问题的答案选项的答案选项，不会再属于另一个问题
                    if (question.getQuestion_no().equals(tempAnswer.getQuestion_no())) {
                        answers.add(allAnswers.get(i));
                        allAnswers.remove(i);
                        i--;
                        allAnswersLength--;
                    }
                }
            }
            bundle.putParcelableArrayList(BUNDLE_KEY_300105, allQuestions);
            transferAction(REQUEST_SUCCESS, bundle);
        } catch (JSONException je) {
            je.printStackTrace();
        }
    }
}
