package com.thinkive.android.trade_bz.a_net.adapter;

import android.content.Context;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_net.bean.NetVoteEntrustSelectBean;
import com.thinkive.android.trade_bz.a_stock.adapter.AbsBaseAdapter;
import com.thinkive.android.trade_bz.others.tools.FontManager;


/**
 * 网络投票查询页面
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/8/2
 */

public class NetVoteEntrustSelectAdapter extends AbsBaseAdapter<NetVoteEntrustSelectBean> {

    private Context mContext;
    private FontManager mFontManager;
    public NetVoteEntrustSelectAdapter(Context context) {
        super(context, R.layout.item_net_vote_entrust);
        mContext = context;
        mFontManager = FontManager.getInstance(mContext);
    }

    @Override
    protected void onFillComponentData(ViewHolder holder, NetVoteEntrustSelectBean bean) {
        TextView tvStockCode = (TextView) holder.getComponentById(R.id.tv_stock_code);
        tvStockCode.setText(bean.getStock_code());

        TextView tvStockName = (TextView) holder.getComponentById(R.id.tv_stock_name);
        tvStockName.setText(bean.getStock_name());

        TextView tvOrderSno = (TextView) holder.getComponentById(R.id.tv_order_sno);
        tvOrderSno.setText(bean.getOrder_sno());

        TextView tvOrderGroup = (TextView) holder.getComponentById(R.id.tv_order_group);
        tvOrderGroup.setText(bean.getOrder_group());

        TextView tvOrderDate = (TextView) holder.getComponentById(R.id.tv_order_date);
        tvOrderDate.setText(bean.getOrder_date());
        //todo:该页面多处却字段，目前是随意取值
        TextView tvEntrustTime = (TextView) holder.getComponentById(R.id.tv_entrust_time);
        tvEntrustTime.setText("却字段");

        TextView tvBsflag = (TextView) holder.getComponentById(R.id.tv_bsflag);
        tvBsflag.setText(bean.getBsflag());

        TextView tvFundAccount = (TextView) holder.getComponentById(R.id.tv_fund_account);
        tvFundAccount.setText(bean.getFund_account());

        TextView tvExchangeType = (TextView) holder.getComponentById(R.id.tv_exchange_type);
        tvExchangeType.setText(bean.getExchange_type());

        TextView tvStockAccount = (TextView) holder.getComponentById(R.id.tv_stock_account);
        tvStockAccount.setText(bean.getStock_account());

        TextView tvVoteType = (TextView) holder.getComponentById(R.id.tv_vote_type);
        tvVoteType.setText(bean.getVote_type());

        TextView tvVoteQty = (TextView) holder.getComponentById(R.id.tv_vote_qty);
        tvVoteQty.setText(bean.getVote_qty());

        TextView tvVId = (TextView) holder.getComponentById(R.id.tv_v_id);
        tvVId.setText(bean.getV_id());

        TextView tvReportTime = (TextView) holder.getComponentById(R.id.tv_report_time);
        tvReportTime.setText(bean.getReport_time());

        TextView tvOrderStatus = (TextView) holder.getComponentById(R.id.tv_order_status);
        tvOrderStatus.setText(bean.getOrder_status_name());

        mFontManager.setTextFont(tvStockCode, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvStockName, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvOrderSno, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvOrderGroup, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvOrderDate, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvEntrustTime, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvBsflag, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvFundAccount, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvExchangeType, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvStockAccount, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvVoteType, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvVoteQty, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvVId, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvReportTime, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(tvOrderStatus, FontManager.FONT_DINPRO_MEDIUM);
    }
}
