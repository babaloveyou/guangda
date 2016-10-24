package com.thinkive.android.trade_bz.a_out.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_out.bean.FundRiskTestAnswerItem;
import com.thinkive.android.trade_bz.a_out.bean.FundRiskTestQuestion;
import com.thinkive.android.trade_bz.a_stock.adapter.AbsBaseAdapter;

import java.util.ArrayList;

/**
 *  基金交易--风险测评问卷
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/5/26
 */

public class FundRiskQuestionAdapter extends AbsBaseAdapter<FundRiskTestQuestion> {

    private Context mContext;

    public FundRiskQuestionAdapter(Context context) {
        super(context, R.layout.item_fund_risk_question);
        mContext = context;
    }

    @Override
    protected void onFillComponentData(ViewHolder holder, final FundRiskTestQuestion bean) {
        TextView tvContent = (TextView) holder.getComponentById(R.id.tv_fund_risk_question);
        final RadioGroup radioGroup = (RadioGroup) holder.getComponentById(R.id.rg_fund_risk_question);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams( ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        //监听选择，所选项的id，即为答案编号
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId < 0) {
                    return;
                }
                bean.setChecked_answer(checkedId);
                bean.setSelect_answer(bean.getAnswers().get(checkedId).getAnswer_no());
            }
        });

        //设置序号
        ArrayList<FundRiskTestQuestion> dataList = getListData();
        int position = dataList.indexOf(bean);
        //前方加序号  设置问题
        tvContent.setText(String.format("%s、%s", String.valueOf(position + 1), bean.getQuestion_content()));

        ArrayList<FundRiskTestAnswerItem> answers = bean.getAnswers();
        int answerSize = answers.size();
        RadioButton radioButton;
        FundRiskTestAnswerItem answer;
        //清空所有button
        radioGroup.removeAllViews();
        radioGroup.clearCheck();

        // 如果这道题的答案选项不为空，那么表示之前已经选择过，所以设为选中状态
        // 记录上次选择的答案
        int hasSelected = -1;
        if(!TextUtils.isEmpty(bean.getSelect_answer())){
            hasSelected = bean.getChecked_answer();
        }

        // 根据服务器返回的数据，给问题设置答案RadioButton
        for (int i = 0; i < answerSize; i++) {
            answer = answers.get(i);
            radioButton = new RadioButton(mContext);
            radioButton.setText(answer.getAnswer_content());
            radioButton.setTextColor(mContext.getResources().getColor(R.color.trade_text_color6));
            radioButton.setTextSize(16.0f);
            radioButton.setLayoutParams(params);
            radioButton.setId(i);
            // 判断目前循环到的答案，是否是上次选中的。
            radioButton.setChecked(i == hasSelected);
            radioGroup.addView(radioButton);
        }
    }
}
