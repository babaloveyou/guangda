package com.android.thinkive.invest_sd.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.android.thinkive.invest_sd.R;
import com.android.thinkive.invest_sd.beans.RecommendBean;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Created by xuemei on 2015/9/22.
 */
public class RecommendMainPageAdapter extends AbsBaseAdapter<RecommendBean> {

    private Context mContext;

    public RecommendMainPageAdapter(Context context) {
        super(context, R.layout.item_main_pager_recommend);
        mContext = context;
    }

    @Override
    protected void onFillComponentData(ViewHolder holder, RecommendBean bean) {

        TextView mTvRecommendName = (TextView) holder.getComponentById(R.id.tv_recommend_name);
        mTvRecommendName.setText(bean.getProduct_name());

        TextView mTvRecommendRisk = (TextView) holder.getComponentById(R.id.tv_recommend_risk);
        mTvRecommendRisk.setText(returnRiskLevel(bean.getRisk_level(), mTvRecommendRisk));

        TextView mTvUnitNet = (TextView) holder.getComponentById(R.id.tv_recomend_unit_net);
        mTvUnitNet.setText(bean.getCumulative_net());



        TextView tv_recommend_yield_desc = (TextView) holder.getComponentById(R.id.tv_recommend_yield_desc);
        tv_recommend_yield_desc.setText(bean.getYield_desc());

        View tv_recommend_profit_end =  holder.getComponentById(R.id.tv_recommend_profit_end);
        TextView mTvRecommendProfit = (TextView) holder.getComponentById(R.id.tv_recommend_profit);
//        if (TextUtils.isEmpty(bean.getCurrent_price())) {
//            mTvRecommendProfit.setText(mContext.getResources().getString(R.string.no_text_set));
//        } else {
//            mTvRecommendProfit.setText(bean.getCurrent_price());
//        }
        String Profit = null;
        switch (bean.getYield_type()){
            case 0:
                Profit = bean.getCumulative_net();
//                mTvRecommendProfit.setText(bean.getCumulative_net());
                tv_recommend_profit_end.setVisibility(View.GONE);
                break;
            case 1:
                Profit = bean.getCurrent_price();
//                mTvRecommendProfit.setText(bean.getCurrent_price());
                tv_recommend_profit_end.setVisibility(View.GONE);
                break;
            case 2:
                Profit = bean.getEarnings();
//                mTvRecommendProfit.setText(bean.getEarnings());
                tv_recommend_profit_end.setVisibility(View.VISIBLE);
                break;
            case 3:
                Profit = bean.getIncomeunit();
//                mTvRecommendProfit.setText(bean.getIncomeunit());
                tv_recommend_profit_end.setVisibility(View.VISIBLE);
                break;
            case 4:
                Profit = bean.getYield_type_input();
//                mTvRecommendProfit.setText(bean.getYield_type_input());
                tv_recommend_profit_end.setVisibility(View.GONE);
                break;
            default:
                Profit =mContext.getResources().getString(R.string.no_text_set);
//                mTvRecommendProfit.setText(mContext.getResources().getString(R.string.no_text_set));
                tv_recommend_profit_end.setVisibility(View.GONE);
        }
        try{
            Double profit_num = Double.valueOf(Profit);
            BigDecimal   b   =   new BigDecimal(profit_num);
            profit_num  =  b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
            DecimalFormat df = new DecimalFormat("######0.00");
            Profit = df.format(profit_num);
            mTvRecommendProfit.setText(Profit);
        }catch (Exception e){
            mTvRecommendProfit.setText(Profit);
        }
    }

    /**
     * 风险等级
     *
     * @param textView
     * @return
     */
    private String returnRiskLevel(int level, TextView textView) {
        String risk ="";
        switch (level){
            case 1:risk="低风险";
                textView.setTextColor(mContext.getResources().getColor(R.color.recommend_risk1));
                break;
            case 2:risk="中低风险";
                textView.setTextColor(mContext.getResources().getColor(R.color.recommend_risk2));
                break;
            case 3:risk="中风险";
                textView.setTextColor(mContext.getResources().getColor(R.color.recommend_risk3));
                break;
            case 4:risk="中高风险";
                textView.setTextColor(mContext.getResources().getColor(R.color.recommend_risk4));
                break;
            case 5:risk= "高风险";
                textView.setTextColor(mContext.getResources().getColor(R.color.recommend_risk5));
                break;
        }
        return risk;
    }
}
