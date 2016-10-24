package com.android.thinkive.invest_sd.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.thinkive.framework.config.ConfigManager;
import com.android.thinkive.invest_sd.R;
import com.android.thinkive.invest_sd.beans.FristPageCicrleItemBean;
import com.android.thinkive.invest_sd.util.ImageUtil;


/**
 * 快捷菜单GridView适配器
 * Announcements：
 *
 * @author 王志鸿
 * @company Thinkive
 * @date 2015/8/7
 */

public class FastMenuAdapter extends AbsBaseAdapter<FristPageCicrleItemBean> {

//    private Context mContext;

    public FastMenuAdapter(Context context) {
        super(context, R.layout.item_fast_menu_trade);
//        mContext = context;
    }

    @Override
    protected void onFillComponentData(ViewHolder holder, FristPageCicrleItemBean bean) {
        TextView tvMenuItem = holder.getComponentById(R.id.tv_menu_item);

//        Drawable drawable = new BitmapDrawable(mContext.getResources(), bean.getImage_bmp());
//        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
//        tvMenuItem.setCompoundDrawables(null, drawable, null, null);
        ImageView imgview = holder.getComponentById(R.id.iv_fast_menu_icon);
       String url =  ConfigManager.getInstance(getContext()).getAddressConfigValue("URL_FRIST_IMAGEGD") + bean.getImage_url();
        if(bean.getJump_type()==4){
            tvMenuItem.setText("配置");
            imgview.setImageResource(R.drawable.ic_menu_set);
        }else{
            tvMenuItem.setText(bean.getImage_name());
            ImageUtil.setImage(imgview,url,getContext());
        }
    }
}
