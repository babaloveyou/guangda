package com.thinkive.android.trade_bz.a_net.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_net.bean.NetVoteStockMsgBean;
import com.thinkive.android.trade_bz.a_net.fragment.NetVoteFragment;
import com.thinkive.android.trade_bz.a_stock.adapter.AbsBaseAdapter;
import com.thinkive.android.trade_bz.others.tools.FontManager;

/**
 * 网络投票的投票页面
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/8/2
 */

public class NetVoteMsgAdapter extends AbsBaseAdapter<NetVoteStockMsgBean> {

    private Context mContext;
    private NetVoteFragment mFragment;
    private FontManager mFontManager;

    public NetVoteMsgAdapter(Context context,NetVoteFragment fragment) {
        super(context, R.layout.item_net_vote_msg);
        mContext = context;
        mFragment = fragment;
        mFontManager = FontManager.getInstance(mContext);
    }

    @Override
    protected void onFillComponentData(ViewHolder holder, final NetVoteStockMsgBean bean) {
        TextView tvClickVote = (TextView) holder.getComponentById(R.id.tv_click_vote);
        tvClickVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              mFragment.onClickAdapter(bean);
            }
        });

        TextView tvStockCode = (TextView) holder.getComponentById(R.id.tv_stock_code);
        tvStockCode.setText(bean.getVote_code());

        TextView tvStockName = (TextView) holder.getComponentById(R.id.tv_stock_name);
        tvStockName.setText(bean.getStock_abbr());

        TextView tvVoteInfo = (TextView) holder.getComponentById(R.id.tv_vote_info);
        tvVoteInfo.setText(bean.getMeeting_desc());

        TextView tvVoteType = (TextView) holder.getComponentById(R.id.tv_vote_type);
        tvVoteType.setText(bean.getMeeting_type());

        TextView tvBeginDate = (TextView) holder.getComponentById(R.id.tv_begin_date);
        tvBeginDate.setText(bean.getMeeting_begin());

        TextView tvEndDate = (TextView) holder.getComponentById(R.id.tv_end_date);
        tvEndDate.setText(bean.getMeeting_end());

        mFontManager.setTextFont(tvStockCode, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvStockName, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvVoteInfo, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvVoteType, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvBeginDate, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvEndDate, FontManager.FONT_DINPRO_MEDIUM);

    }
}
