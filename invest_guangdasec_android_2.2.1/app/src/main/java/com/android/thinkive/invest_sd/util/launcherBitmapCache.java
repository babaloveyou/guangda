package com.android.thinkive.invest_sd.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.thinkive.invest_sd.constants.Constant;
import com.android.volley.toolbox.ImageLoader;

/**
 * Created by xiangfan on 2015/11/9.
 */
public class launcherBitmapCache implements ImageLoader.ImageCache {

    private LruCache<String, Bitmap> mCache;

    private static launcherBitmapCache bitmapCache;
    private Context context;
    public static launcherBitmapCache getInstance(Context context){
        if(bitmapCache == null){
            bitmapCache =   new launcherBitmapCache();
        }
        bitmapCache.context =context;
        return bitmapCache;
    }
    private launcherBitmapCache() {
        int maxSize = 10 * 1024 * 1024;
        mCache = new LruCache<String, Bitmap>(maxSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                return bitmap.getRowBytes() * bitmap.getHeight();
            }
        };
    }

    @Override
    public Bitmap getBitmap(String url) {
        return mCache.get(url);
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        FileCacheToSdcardUtil.saveBitmap(bitmap, Constant.LAUNCHER_PIC,context);
        mCache.put(url, bitmap);
    }

}