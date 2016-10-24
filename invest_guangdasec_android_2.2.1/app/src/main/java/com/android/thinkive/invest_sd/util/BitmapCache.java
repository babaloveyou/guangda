package com.android.thinkive.invest_sd.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

/**
 * Created by xiangfan on 2015/7/14.
 */
public class BitmapCache implements ImageLoader.ImageCache {

    private LruCache<String, Bitmap> mCache;

    private static BitmapCache bitmapCache;
    private Context context;
    public static BitmapCache getInstance(Context context){
        if(bitmapCache == null){
            bitmapCache =   new BitmapCache();
        }
        bitmapCache.context = context;
        return bitmapCache;
    }
    private BitmapCache() {
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
        Bitmap bitmap = null;
        bitmap = mCache.get(url);
        if(bitmap==null){
            bitmap =  FileCacheToSdcardUtil.getBitmap(url,context);
            if(bitmap!=null){
                mCache.put(url,bitmap);
            }
        }
        return bitmap;
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        FileCacheToSdcardUtil.saveBitmap(bitmap, url,context);
        mCache.put(url, bitmap);
    }

}
