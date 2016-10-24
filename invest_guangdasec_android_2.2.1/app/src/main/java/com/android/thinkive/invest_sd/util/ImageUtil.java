package com.android.thinkive.invest_sd.util;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.android.thinkive.framework.CoreApplication;
import com.android.thinkive.framework.config.ConfigManager;
import com.android.thinkive.invest_sd.R;
import com.android.thinkive.invest_sd.constants.Constant;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;

/**
 * Created by xiangfan on 2015/11/5.
 */
public class ImageUtil {

    public static void setImage(final ImageView imageView, String url,Context context) {
        if (url == null) {
            imageView.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_menu_test));
            return;
        }
//        if (FileCacheToSdcardUtil.getBitmap(url,context) != null) {
//            imageView.setImageBitmap(FileCacheToSdcardUtil.getBitmap(url,context));
//        } else {
            ImageLoader imageLoader = new ImageLoader(CoreApplication.getInstance().getRequestQueue(), BitmapCache.getInstance(context));
            ImageLoader.ImageListener listener = ImageLoader.getImageListener(imageView,
                    0, R.drawable.circular_corner_white);
            imageLoader.get(url, listener);
//        }
    }

    public static void getImage(String url,Context context, ImageLoader.ImageListener listener) {
            ImageLoader imageLoader = new ImageLoader(CoreApplication.getInstance().getRequestQueue(), BitmapCache.getInstance(context));
            imageLoader.get(url, listener);
    }


    public static void saveLauncherImage(String url,Context context){
        if (url == null) {
            return;
        }
        url = ConfigManager.getInstance(context).getAddressConfigValue("URL_FRIST_IMAGEGD")+ url;
            ImageLoader imageLoader = new ImageLoader(CoreApplication.getInstance().getRequestQueue(), launcherBitmapCache.getInstance(context));
            ImageLoader.ImageListener listener = new ImageLoader.ImageListener() {
                @Override
                public void onResponse(ImageLoader.ImageContainer imageContainer, boolean b) {
                }

                @Override
                public void onErrorResponse(VolleyError volleyError) {
                }
            };
            imageLoader.get(url, listener);
    }


}
