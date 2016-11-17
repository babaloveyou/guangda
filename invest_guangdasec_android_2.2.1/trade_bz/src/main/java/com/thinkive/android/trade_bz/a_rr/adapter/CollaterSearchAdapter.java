package com.thinkive.android.trade_bz.a_rr.adapter;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_stock.adapter.AbsBaseAdapter;
import com.thinkive.android.trade_bz.a_stock.bean.RCollaterSearchBean;
import com.thinkive.android.trade_bz.utils.StringUtils;

/**
 * Created by Administrator on 2016/11/16.
 */
public class CollaterSearchAdapter extends AbsBaseAdapter<RCollaterSearchBean> {

    private Context mSubContext;

    public CollaterSearchAdapter(Context context) {
        super(context, R.layout.item_a_collater_search);
        setIsReuseView(true);
        mSubContext = context;
    }

    @Override
    protected void onFillComponentData(ViewHolder holder, RCollaterSearchBean bean) {
        TextView titleTv = (TextView) holder.getComponentById(R.id.tv_title);
        TextView timeTv = (TextView) holder.getComponentById(R.id.tv_time);
        titleTv.setText(bean.getEntrust_type_name());
        timeTv.setText(bean.getEntrust_date() + " " + bean.getEntrust_time());
        String entrust_state = bean.getEntrust_state();
            switch (entrust_state) {
                case "0":
                    entrust_state = "未报";
                    break;
                case "1":
                    entrust_state = "待报";
                    break;
                case "2":
                    entrust_state = "正报";
                    break;
                case "3":
                    entrust_state = "已报";
                    break;
                case "4":
                    entrust_state = "已报待撤";
                    break;
                case "5":
                    entrust_state = "部成待撤";
                    break;
                case "6":
                    entrust_state = "部撤";
                    break;
                case "7":
                    entrust_state = "已撤";
                    break;
                case "8":
                    entrust_state = "部成";
                    break;
                case "9":
                    entrust_state = "已成";
                    break;
                case "10":
                    entrust_state = "废单";
                    break;

        }

            String entrust_no = "编        号 " + bean.getEntrust_no();
        String entrust_amount = "委托数量 " + bean.getEntrust_amount();
        String entrust_price = "委托价格 " + bean.getEntrust_price();
        entrust_state = "状态说明 " + entrust_state;

        TextView noTv = (TextView) holder.getComponentById(R.id.tv_no);
        TextView amountTv = (TextView) holder.getComponentById(R.id.tv_amount);
        TextView priceTv = (TextView) holder.getComponentById(R.id.tv_price);
        TextView stateTv = (TextView) holder.getComponentById(R.id.tv_state);

        setText(noTv, entrust_no);
        setText(amountTv, entrust_amount);
        setText(priceTv, entrust_price);
        setText(stateTv, entrust_state);
    }

    private void setText(TextView view, String textString) {
        int numOfInnerString = StringUtils.getNumOfInnerString(textString, " ");
        System.out.println("空格个数   ==" + numOfInnerString);
        if (numOfInnerString == 1) {
            SpannableStringBuilder styleEnable = new SpannableStringBuilder(textString);
            styleEnable.setSpan(new ForegroundColorSpan(mSubContext.getResources().getColor(R.color.text_reming)), 0, 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            styleEnable.setSpan(new ForegroundColorSpan(mSubContext.getResources().getColor(R.color.trade_text_color2)), 5, textString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            view.setText(styleEnable);
        }
        if (numOfInnerString == 9) {
            SpannableStringBuilder styleEnable = new SpannableStringBuilder(textString);
            styleEnable.setSpan(new ForegroundColorSpan(mSubContext.getResources().getColor(R.color.text_reming)), 0, 10, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            styleEnable.setSpan(new ForegroundColorSpan(mSubContext.getResources().getColor(R.color.trade_text_color2)), 11, textString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            view.setText(styleEnable);
        }
    }
}
