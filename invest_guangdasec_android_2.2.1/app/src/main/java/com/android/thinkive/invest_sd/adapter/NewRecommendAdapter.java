package com.android.thinkive.invest_sd.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.thinkive.invest_sd.R;
import com.android.thinkive.invest_sd.beans.RecommendBean;

import java.util.List;

/**
 * Created by xiangfan on 2015/9/21.
 */
public class NewRecommendAdapter extends BaseAdapter {
    private LayoutInflater minflater;
    private List<RecommendBean> list;
    private OnRecommendItenClick listener;
    public NewRecommendAdapter(Context context,List<RecommendBean> list){
        this.minflater = LayoutInflater.from(context);
        this.list = list;

    }
    public void setListener(OnRecommendItenClick listener){
        this.listener= listener;

    }
    public void setList(List<RecommendBean> list){
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
      return   list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder ;
        if(convertView == null){
            holder= new Holder();
            convertView = minflater.inflate(R.layout.item_new_recommend,null);
            holder.tv_recommendna_name = (TextView) convertView.findViewById(R.id.tv_recommendna_name);
            holder.tv_recommend_risk_level = (TextView) convertView.findViewById(R.id.tv_recommend_risk_level);
            holder.tv_recommend_time_increase = (TextView) convertView.findViewById(R.id.tv_recommend_time_increase);
            holder.tv_recommend_buy_money = (TextView) convertView.findViewById(R.id.tv_recommend_buy_money);
            holder.tv_recommend_increase_decade = (TextView) convertView.findViewById(R.id.tv_recommend_increase_decade);
            holder.tv_recommend_increase_unit = (TextView) convertView.findViewById(R.id.tv_recommend_increase_unit);
            holder.but_recommend_buy = convertView.findViewById(R.id.but_recommend_buy);
            convertView.setTag(holder);
        }else{
            holder = (Holder) convertView.getTag();
        }

        final RecommendBean bean = list.get(position);
        holder.tv_recommendna_name.setText(bean.getProduct_code()+bean.getProduct_name());
        holder.tv_recommend_buy_money.setText(bean.getPer_buy_limit());
        holder.tv_recommend_time_increase.setText(bean.getYield_desc());

        String risk ="";
        int tv_color =  0x00BB00;
        switch (bean.getRisk_level()){
            case 1:risk="低"; tv_color =0x00BB00;break;
            case 2:risk="中低";  tv_color =0x1098ED; break;
            case 3:risk="中";  tv_color =0x1098ED;break;
            case 4:risk="中高"; tv_color =0xFF0000;break;
            case 5:risk= "高"; tv_color =0xFF0000;break;
        }
        holder.tv_recommend_risk_level.setText(risk);
        holder.tv_recommend_risk_level.setTextColor(tv_color);
        String increase=bean.getCurrent_price();

//        switch (bean.getYield_typ()){
//            case 0:increase=bean.getYieldrate1d();break;
//            case 1:increase=bean.getYieldrate3m();break;
//            case 2:increase=bean.getYieldrate6m();break;
//            case 3:increase=bean.getYieldrate1y();break;
//            case 4:increase=bean.getYieldrate1d();break;
//            case 5:increase=bean.getAnnual_profit();break;//年化收益率
//            case 6:increase=bean.getProfit_of_10_thousands();break;//每万份收益
//            case 7:increase=bean.getYield_type_input();break;
//            case 8:increase=bean.getSeven_days_annual_profit();break;//七日年化收益率
//        }
        if(!TextUtils.isEmpty(increase)) {
            String a[] = increase.split("\\.");
            if (a.length >= 2) {
                holder.tv_recommend_increase_decade.setText(a[0]);
                holder.tv_recommend_increase_unit.setText("."+a[1]+"%");
            } else {
                holder.tv_recommend_increase_decade.setText(increase);
                holder.tv_recommend_increase_unit.setText("");
            }
        }
        holder.but_recommend_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null){
                    listener.onclick(bean);
                }
            }
        });
        return convertView;
    }

    class Holder {
        public TextView tv_recommendna_name;
        public TextView tv_recommend_risk_level;
        public TextView tv_recommend_time_increase;
        public TextView tv_recommend_buy_money;
        public TextView tv_recommend_increase_decade;
        public TextView tv_recommend_increase_unit;
        public View but_recommend_buy;

    }

    public interface OnRecommendItenClick{
        public void onclick(RecommendBean bean);
    }
}
