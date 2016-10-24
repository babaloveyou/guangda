package com.android.thinkive.invest_sd.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.thinkive.framework.CoreApplication;
import com.android.thinkive.framework.config.ConfigManager;
import com.android.thinkive.invest_sd.R;
import com.android.thinkive.invest_sd.beans.FristPageCicrleItemBean;
import com.android.thinkive.invest_sd.util.BitmapCache;
import com.android.thinkive.invest_sd.util.FileCacheToSdcardUtil;
import com.android.volley.toolbox.ImageLoader;

import java.util.List;

/**
 * Created by xiangfan on 2015/9/23.
 */
public class FristPageMenuAdapter extends BaseAdapter {
    List<FristPageCicrleItemBean> list;
    private LayoutInflater minflater;
    private Context context;
   public FristPageMenuAdapter(Context context){
       minflater = LayoutInflater.from(context);
       this.context = context;
    }
    public void setlist(List<FristPageCicrleItemBean> list){
        this.list = list;
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
        Holder holder;
        if(convertView == null){
            convertView = minflater.inflate(R.layout.item_menu_frist_page,null);
            holder = new Holder();
            holder.tv_menu_name = (TextView) convertView.findViewById(R.id.tv_menu_name);
            holder.tv_menu_img = (ImageView) convertView.findViewById(R.id.tv_menu_img);
            convertView.setTag(holder);
        }else{
            holder = (Holder) convertView.getTag();
        }
        if(position == list.size()-1){
            holder.tv_menu_name.setText("配置");
            holder.tv_menu_img.setImageResource(R.drawable.ic_menu_set);
        }
        else {
            FristPageCicrleItemBean bean = list.get(position);
            holder.tv_menu_name.setText(bean.getImage_name());
            setImage(holder.tv_menu_img, bean.getImage_url());
        }
        return convertView;
    }
    class Holder{
        public TextView tv_menu_name;
        public ImageView tv_menu_img;
    }

    public void setImage(final ImageView imageView, String url) {
        if (url == null) {
            imageView.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_menu_test));
            return;
        }
        url = ConfigManager.getInstance(context).getAddressConfigValue("URL_FRIST_IMAGEGD")+ url;
//        if (FileCacheToSdcardUtil.getBitmap(url,context) != null) {
//            imageView.setImageBitmap(FileCacheToSdcardUtil.getBitmap(url,context));
//        } else {
            ImageLoader imageLoader = new ImageLoader(CoreApplication.getInstance().getRequestQueue(), BitmapCache.getInstance(context));
            ImageLoader.ImageListener listener = ImageLoader.getImageListener(imageView,
                    0, R.drawable.circular_corner_white);
            imageLoader.get(url, listener);
//        }
    }

}
