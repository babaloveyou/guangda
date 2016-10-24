package com.thinkive.android.trade_bz.a_net.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_net.bean.NetVoteEntrustBean;
import com.thinkive.android.trade_bz.a_stock.adapter.AbsBaseAdapter;
import com.thinkive.android.trade_bz.dialog.NetVoteInputNumDialog;
import com.thinkive.android.trade_bz.others.tools.FontManager;
import com.thinkive.android.trade_bz.utils.TradeUtils;

/**
 * 网络投票-累计议案投票
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/8/3
 */

public class NetAddUpVoteAdapter extends AbsBaseAdapter<NetVoteEntrustBean> {

    private Context mContext;
    private FontManager mFontManager;

    public NetAddUpVoteAdapter(Context context) {
        super(context, R.layout.item_net_add_up);
        mContext = context;
        mFontManager = FontManager.getInstance(mContext);
    }

    @Override
    protected void onFillComponentData(ViewHolder holder, final NetVoteEntrustBean bean) {
        TextView vDivide = (TextView) holder.getComponentById(R.id.tv_divide_line);
        TextView vLeijino = (TextView) holder.getComponentById(R.id.tv_vote_leijino);
        TextView vId = (TextView) holder.getComponentById(R.id.tv_v_id);
        TextView vInfo = (TextView) holder.getComponentById(R.id.tv_vote_info);
        TextView inputNum = (TextView) holder.getComponentById(R.id.tv_input_num);
        inputNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TradeUtils.isFastClick()) {
                    return;
                }
                NetVoteInputNumDialog dialog = new NetVoteInputNumDialog(mContext);
                dialog.setMaxNum(bean);
                dialog.show();
            }
        });

        String leiji = bean.getVote_leijino();

        if(TextUtils.isEmpty(leiji)){   // 说明是分议案
            vDivide.setVisibility(View.GONE);
            vLeijino.setVisibility(View.GONE);
            inputNum.setVisibility(View.VISIBLE);
            inputNum.setText(bean.getVote_number());
        }else if(!TextUtils.isEmpty(leiji)){  //主议案
            vDivide.setVisibility(View.VISIBLE);
            vLeijino.setVisibility(View.VISIBLE);
            inputNum.setVisibility(View.GONE);
            vLeijino.setText(leiji);
        }
        vId.setText(bean.getV_id());
        vInfo.setText(bean.getVote_info());

        mFontManager.setTextFont(vId, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(vInfo, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(vLeijino, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(inputNum, FontManager.FONT_DINPRO_MEDIUM);
    }
}
