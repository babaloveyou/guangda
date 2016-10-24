package com.thinkive.android.trade_bz.a_net.adapter;

import android.content.Context;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.thinkive.android.trade_bz.R;
import com.thinkive.android.trade_bz.a_net.bean.NetVoteEntrustBean;
import com.thinkive.android.trade_bz.a_net.fragment.NetNotAddUpVoteFragment;
import com.thinkive.android.trade_bz.a_stock.adapter.AbsBaseAdapter;
import com.thinkive.android.trade_bz.others.tools.FontManager;

/**
 * 网络投票-非累计议案投票
 * @author 张雪梅
 * @company Thinkive
 * @date 2016/8/5
 */
public class NetNotAddUpVoteAdapter extends AbsBaseAdapter<NetVoteEntrustBean> {

    private Context mContext;
    private FontManager mFontManager;
    private NetNotAddUpVoteFragment mFragment;

    public NetNotAddUpVoteAdapter(Context context,NetNotAddUpVoteFragment fragment) {
        super(context, R.layout.item_net_not_add_up);
        mContext = context;
        mFragment = fragment;
        mFontManager = FontManager.getInstance(mContext);
    }
    @Override
    protected void onFillComponentData(ViewHolder holder, final NetVoteEntrustBean bean) {
        RadioGroup rgAddVote = (RadioGroup) holder.getComponentById(R.id.rg_add_vote);
        RadioButton rbAddVoteAgree = (RadioButton) holder.getComponentById(R.id.rb_add_vote_agree);
        if(bean.isCheckAgree()){
            rbAddVoteAgree.setChecked(bean.isCheckAgree());
        }

        RadioButton rbAddVoteOpppse = (RadioButton) holder.getComponentById(R.id.rb_add_vote_opppse);
        if(bean.isCheckOppose()){
            rbAddVoteOpppse.setChecked(bean.isCheckOppose());
        }

        RadioButton rbAddVoteGiveUp = (RadioButton) holder.getComponentById(R.id.rb_add_vote_give_up);
        if(bean.isCheckGiveUp()){
            rbAddVoteGiveUp.setChecked(bean.isCheckGiveUp());
        }

        TextView tvAddVoteContent = (TextView) holder.getComponentById(R.id.tv_add_vote_content);
        rgAddVote.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.rb_add_vote_agree){  //同意
                    mFragment.onClickAdapterAgree(bean);
                }else if(checkedId == R.id.rb_add_vote_opppse){ //反对
                    mFragment.onClickAdapterOppose(bean);
                }else if(checkedId == R.id.rb_add_vote_give_up){ // 放弃
                    mFragment.onClickAdapterGiveUp(bean);
                }
            }
        });

        tvAddVoteContent.setText(bean.getVote_info());

        mFontManager.setTextFont(tvAddVoteContent, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(rbAddVoteAgree, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(rbAddVoteOpppse, FontManager.FONT_DINPRO_MEDIUM);
        mFontManager.setTextFont(rbAddVoteGiveUp, FontManager.FONT_DINPRO_MEDIUM);
    }
}
