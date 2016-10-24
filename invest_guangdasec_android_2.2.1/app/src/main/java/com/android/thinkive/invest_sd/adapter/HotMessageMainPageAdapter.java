package com.android.thinkive.invest_sd.adapter;

import android.content.Context;
import android.widget.TextView;

import com.android.thinkive.invest_sd.R;
import com.android.thinkive.invest_sd.beans.NewsBean;


/**
 * Created by xuemei on 2015/9/22.
 */
public class HotMessageMainPageAdapter extends AbsBaseAdapter<NewsBean> {

//    private Context mContext;

    public HotMessageMainPageAdapter(Context context) {
        super(context, R.layout.item_main_pager_hot_message);
//        mContext = context;
    }

    @Override
    protected void onFillComponentData(ViewHolder holder, NewsBean bean) {

        TextView mTvMessageTitle = (TextView) holder.getComponentById(R.id.tv_hot_one_title);
        mTvMessageTitle.setText(bean.getTitle());

        TextView mTvMessageTime = (TextView) holder.getComponentById(R.id.tv_hot_one_time);
        mTvMessageTime.setText(bean.getPublish_date());
    }
}
