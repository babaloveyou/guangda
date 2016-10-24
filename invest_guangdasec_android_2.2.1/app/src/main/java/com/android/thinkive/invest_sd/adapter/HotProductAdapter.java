package com.android.thinkive.invest_sd.adapter;

import android.content.Context;
import android.text.AndroidCharacter;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.thinkive.invest_sd.R;
import com.android.thinkive.invest_sd.beans.HotProductBean;
import com.android.thinkive.invest_sd.beans.RecommendBean;
import com.android.thinkive.invest_sd.util.ThinkiveUtil;
import com.android.thinkive.invest_sd.widget.RotateTextView;

import java.util.List;

/**
 * Created by xiangfan on 2016/7/12.
 * 因为是在首页展示，listview为了能在scrollview中撑开本来就是没有复用的，所以这里不复用了
 */
public class HotProductAdapter extends BaseAdapter {
    private LayoutInflater minflater;
    private List<HotProductBean> list;
    private OnRecommendItenClick listener;
    private Context context;
    public HotProductAdapter(Context context,List<HotProductBean> list){
        this.minflater = LayoutInflater.from(context);
        this.context =context;
        this.list = list;

    }
    public void setListener(OnRecommendItenClick listener){
        this.listener= listener;

    }
    public void setList(List<HotProductBean> list){
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public HotProductBean getItem(int position) {
        return   list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final HotProductBean bean = list.get(position);
        if(bean.getUi_type() == 1){
            convertView = minflater.inflate(R.layout.item_hot_product,null);
            TextView tv_product_price_tens =  (TextView) convertView.findViewById(R.id.tv_product_price_tens);
            TextView tv_product_price_units =  (TextView) convertView.findViewById(R.id.tv_product_price_units);
            TextView tv_product_price_sign =  (TextView) convertView.findViewById(R.id.tv_product_price_sign);
            TextView tv_product_price_depict =  (TextView) convertView.findViewById(R.id.tv_product_price_depict);
            TextView tv_product_depict =  (TextView) convertView.findViewById(R.id.tv_product_depict);
            TextView tv_product_name =  (TextView) convertView.findViewById(R.id.tv_product_name);
            TextView tv_product_sell_date =  (TextView) convertView.findViewById(R.id.tv_product_sell_date);
            TextView tv_product_profit =  (TextView) convertView.findViewById(R.id.tv_product_profit);
//            RotateTextView tv_hot_product_sellstatu =  (RotateTextView) convertView.findViewById(R.id.tv_hot_product_sellstatu);
//            ImageView iv_hot_product_selling =  (ImageView) convertView.findViewById(R.id.iv_hot_product_selling);
//            LinearLayout ll_hot_product_sellbg =  (LinearLayout) convertView.findViewById(R.id.ll_hot_product_sellbg);

            tv_product_sell_date.setText(bean.getProductSellDate());
            tv_product_depict.setText(bean.getProductDepict());
            tv_product_name.setText(bean.getProductName());
            tv_product_profit.setText(bean.getProductProfit());

//            if(bean.getSellType()==0){
//                tv_hot_product_sellstatu.setVisibility(View.VISIBLE);
//                iv_hot_product_selling.setVisibility(View.GONE);
//                ll_hot_product_sellbg.setBackgroundResource(R.drawable.ic_hotproduct_inged);
//                tv_hot_product_sellstatu.setText("售馨");
//                tv_hot_product_sellstatu.setTextSize(16);
//            }else if(bean.getSellType()==2){
//                tv_hot_product_sellstatu.setVisibility(View.VISIBLE);
//                iv_hot_product_selling.setVisibility(View.GONE);
//                ll_hot_product_sellbg.setBackgroundResource(R.drawable.ic_hotproduct_ing);
//                tv_hot_product_sellstatu.setTextColor(android.graphics.Color.RED);
//                tv_hot_product_sellstatu.setText(bean.getSellTime());
//                tv_hot_product_sellstatu.setTextSize(10);
//            }else{
//                ll_hot_product_sellbg.setBackgroundDrawable(null);
//                tv_hot_product_sellstatu.setVisibility(View.GONE);
//                iv_hot_product_selling.setVisibility(View.VISIBLE);
//            }

            String show_name="";
            if(bean.getEstType()==1){
                show_name="";
            }else if(bean.getEstType()==0){
                show_name="参考年化收益率";
            }else if(bean.getEstType()==2){
                show_name="近3个月涨幅";
            }else if(bean.getEstType()==3){
                show_name="近一年涨幅";
            }else if(bean.getEstType()==4){
                show_name="当前净值";
            }else if(bean.getEstType()==5){
                show_name="七日年化收益率";
            }

            String price = bean.getEstYield();
            if(price.contains("\\%")||price.contains("%")){
                price = price.substring(0,price.length()-1);
                tv_product_price_sign.setVisibility(View.VISIBLE);
            }else{
                tv_product_price_sign.setVisibility(View.GONE);
            }
            if(price.contains(".")){
                String a[] = price.split("\\.");
                tv_product_price_tens.setText(a[0]+".");
                tv_product_price_units.setText(a[1]);
            }else{
                tv_product_price_tens.setText(price);
                tv_product_price_units.setVisibility(View.GONE);
            }
            if(TextUtils.isEmpty(show_name)){
                tv_product_price_tens.setTextSize(22);
                tv_product_price_depict.setVisibility(View.GONE);
            }else{
                tv_product_price_tens.setTextSize(34);
                tv_product_price_depict.setText(show_name);
                tv_product_price_depict.setVisibility(View.VISIBLE);
            }
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null){
                        listener.onclick(bean);
                    }
                }
            });
            return  convertView;
        }
        else{
            convertView = minflater.inflate(R.layout.item_main_pager_product_title,null);
            TextView product_title_name =  (TextView) convertView.findViewById(R.id.product_title_name);
            TextView product_title_depict =  (TextView) convertView.findViewById(R.id.product_title_depict);
            View title_drive_line =   convertView.findViewById(R.id.title_drive_line);
            product_title_name.setText(bean.getAssortment());
            product_title_depict.setText(bean.getSlogan());
//            if(position==0){
//                title_drive_line.setVisibility(View.GONE);
//            }else{
//                title_drive_line.setVisibility(View.VISIBLE);
//            }
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
            return convertView;
        }

    }


    public interface OnRecommendItenClick{
        public void onclick(HotProductBean bean);
    }
}
