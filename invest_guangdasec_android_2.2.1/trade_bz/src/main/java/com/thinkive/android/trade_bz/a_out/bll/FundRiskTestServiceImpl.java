package com.thinkive.android.trade_bz.a_out.bll;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_out.bean.FundRiskTestQuestion;
import com.thinkive.android.trade_bz.a_out.bean.FundRiskTestResultBean;
import com.thinkive.android.trade_bz.interfaces.IRequestAction;
import com.thinkive.android.trade_bz.request.Fund300104;
import com.thinkive.android.trade_bz.request.Fund300105;
import com.thinkive.android.trade_bz.request.Fund300106;
import com.thinkive.android.trade_bz.a_out.fragment.FundRiskFragment;
import com.thinkive.android.trade_bz.utils.LoadingDialogUtil;
import com.thinkive.android.trade_bz.utils.LogUtil;
import com.thinkive.android.trade_bz.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 相关注释待本类代码稳定后添加，敬请期待。
 *
 * @author 王志鸿
 * @company Thinkive
 * @date 2015/8/7
 */
public class FundRiskTestServiceImpl {

    private FundRiskFragment mFragment;
    private LoadingDialogUtil loadingDialogUtil;//请求数据状态框 工具类

    public FundRiskTestServiceImpl(FundRiskFragment fragment) {
        mFragment = fragment;
        loadingDialogUtil = new LoadingDialogUtil(fragment.getContext());
    }

    /**
     * 请求服务器，获取风险测评问卷
     */
    public void requestTestPaper() {
        HashMap<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("risk_kind", "1");
        new Fund300105(paramMap, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                ArrayList<FundRiskTestQuestion> questions =
                        bundle.getParcelableArrayList(Fund300105.BUNDLE_KEY_300105);
                if (questions != null && questions.size() > 0) {
                    mFragment.onGetTestPaper(questions);
                }
            }

            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(Fund300105.ERROR_INFO));
            }
        }).request();
    }

    /**
     * 请求服务器，获取风险测评结果
     */
    public void requestTestResult(final String useType) {
        HashMap<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("risk_kind", "1");
        new Fund300104(paramMap, new IRequestAction() {
            @Override
            public void onSuccess(Context context, Bundle bundle) {
                FundRiskTestResultBean data = (FundRiskTestResultBean) bundle.getSerializable(Fund300104.BUNDLE_KEY_300104);
                if (useType.equals("isOk")) {
                    mFragment.onGetIsCheckOk(data);
                } else if (useType.equals("select")) {
                    mFragment.onGetIsOutResult(data);
                }
            }

            @Override
            public void onFailed(Context context, Bundle bundle) {
                ToastUtils.toast(context, bundle.getString(Fund300104.ERROR_INFO));
            }
        }).request();
    }

    /**
     * 请求服务器，提交风险测评问卷
     */
    public void requestTestSubmit(ArrayList<FundRiskTestQuestion> questions) {
        loadingDialogUtil.showLoadingDialog(0);
        try {
        /*
        格式如下：
        问题编号|问题答案,问题答案|答案分数,答案分数;
        问题编号|问题答案,问题答案|答案分数,答案分数;
         */
            char tempSelectAnswerChar;
            String tempSelectAnswerStr;
            String tempScore;
            StringBuilder stringBuilder = new StringBuilder();
            /*for (FundRiskTestQuestion question : questions) {
                // 这里取出的已选答案，格式是：0、1、2、3，而不是A、B、C、D
                tempSelectAnswerChar = (char) Integer.parseInt(question.getSelect_answer());
                // 获取已选答案的分数
                tempScore = question.getAnswers().get(question.getChecked_answer()).getScore();
                // 将0~4的数字转化为A~E的字母
                tempSelectAnswerChar += 65;
                tempSelectAnswerStr = String.valueOf(tempSelectAnswerChar);
                stringBuilder.append(question.getQuestion_no());
                stringBuilder.append("|");
                // 将取出的答案+65，也就是转为A、B、C、D后，拼装进去。
                stringBuilder.append(tempSelectAnswerStr);
                stringBuilder.append(",");
                stringBuilder.append(tempSelectAnswerStr);
                stringBuilder.append("|");
                stringBuilder.append(tempScore);
                stringBuilder.append(",");
                stringBuilder.append(tempScore);
                stringBuilder.append(";");
            }*/

            /**
             *
             * 修改答题结果的提交格式为：
             * 问题编号1|问题答案|问题分数;问题编号2|问题答案|问题分数;问题编号3|问题答案|问题分数;
             *
             */

            for (FundRiskTestQuestion question : questions) {
                tempSelectAnswerStr = question.getSelect_answer();
                // 获取已选答案的分数
                tempScore = question.getAnswers().get(question.getChecked_answer()).getScore();
                stringBuilder.append(question.getQuestion_no());
                stringBuilder.append("|");
                stringBuilder.append(tempSelectAnswerStr);
                stringBuilder.append("|");
                stringBuilder.append(tempScore);
                stringBuilder.append(";");
            }

            HashMap<String, String> paramMap = new HashMap<String, String>();
            paramMap.put("risk_kind", "1");
            paramMap.put("paper_answer", stringBuilder.toString());
            new Fund300106(paramMap, new IRequestAction() {
                @Override
                public void onSuccess(Context context,Bundle bundle) {
                    final Bundle tempBundle = bundle;
                    loadingDialogUtil.hideDialog();
                    ToastUtils.toast(context, R.string.fund_risk4);//这里显示时间设置的LENGTH_LONG 3.5s
                    //下面使用延时，是为了，让提交成功的信息，不在测评结果页面显示
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mFragment.onGetTestResult((FundRiskTestResultBean) tempBundle.getSerializable(Fund300106.BUNDLE_KEY_300106));
                        }
                    },3500);
                }

                @Override
                public void onFailed(Context context, Bundle bundle) {
                    loadingDialogUtil.hideDialog();
                    ToastUtils.toast(context, bundle.getString(Fund300106.ERROR_INFO));
                }
            }).request();
        } catch (NumberFormatException nfe) {
            LogUtil.printLog("e", "将已选答案转化为数字时报错，错误信息如下：");
            nfe.printStackTrace();
        }
    }
}
