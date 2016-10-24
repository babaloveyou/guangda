package com.android.thinkive.invest_sd.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.android.thinkive.framework.CoreApplication;
import com.android.thinkive.framework.config.ConfigManager;
import com.android.thinkive.invest_sd.R;
import com.android.thinkive.invest_sd.beans.FristPageCicrleItemBean;
import com.android.thinkive.invest_sd.util.BitmapCache;
import com.android.thinkive.invest_sd.util.FileCacheToSdcardUtil;
import com.android.volley.toolbox.ImageLoader;

import java.util.List;

/**
 * Created by xiangfan on 2015/7/15.
 */
public class HeadImagePagerAdapter extends PagerAdapter {
    private Context context;
    List<FristPageCicrleItemBean> list;
    private LayoutInflater minflater;
    private OnItemClickListener listener;
    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public HeadImagePagerAdapter(List<FristPageCicrleItemBean> list, Context context) {
        this.list = list;
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
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        final FristPageCicrleItemBean bean = list.get(position);
        ImageView v = (ImageView) minflater.inflate(
                R.layout.item_headimage, null);
        setImage(v, bean.getImage_url());
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onclick(bean);
                }
            }
        });
        ((ViewPager) container).addView(v);
        return v;
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
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


    public interface OnItemClickListener {
        public void onclick( FristPageCicrleItemBean imgItemBean);
    }
}
