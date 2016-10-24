/*
 * 文 件    名:  WeiZiXunAdapter.java
 * 创建日期:  2014-10-11下午2:39:07
 *
 * 作          者:  ---余巍---
 */

package com.android.thinkive.invest_sd.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.thinkive.invest_sd.R;
import com.android.thinkive.invest_sd.beans.NewsBean;

import java.util.List;

/**
 * @Description TODO 简要说明该类所实现的功能
 * @version [版本号, 2014-10-11]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 * @author ---余巍---
 */
public class NewsAdapter extends BaseAdapter {
    private List<NewsBean> list;
    private Context context;

    public NewsAdapter(List<NewsBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(
                R.layout.item_news, null);

        TextView title = (TextView) convertView.findViewById(R.id.tv_title);
        TextView publish_date = (TextView) convertView.findViewById(R.id.tv_publish_date);
        title.setText(list.get(position).getTitle());
        publish_date.setText(list.get(position).getPublish_date());
    
        return convertView;
    }

}
