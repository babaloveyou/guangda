package com.thinkive.android.trade_bz.utils;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class ImageUtils {
    public ImageUtils() {
    }

    public static String uriToFilePath(Context context, Uri uri) {
        if(uri == null) {
            return null;
        } else {
//            Object file = null;
            String[] projection = new String[]{"_data"};
            Cursor cursor = ((Activity)context).managedQuery(uri, projection, (String)null, (String[])null, (String)null);
            int indexOrThrow = cursor.getColumnIndexOrThrow("_data");
            String imgPath = "";
            if(cursor.moveToFirst()) {
                imgPath = cursor.getString(indexOrThrow);
            }

            return imgPath;
        }
    }

    public static byte[] bitmapToByte(Bitmap b) {
        if(b == null) {
            return null;
        } else {
            ByteArrayOutputStream o = new ByteArrayOutputStream();
            b.compress(CompressFormat.PNG, 100, o);
            return o.toByteArray();
        }
    }

    public static Bitmap byteToBitmap(byte[] b) {
        return b != null && b.length != 0?BitmapFactory.decodeByteArray(b, 0, b.length):null;
    }

    public static Bitmap drawableToBitmap(Drawable d) {
        return d == null?null:((BitmapDrawable)d).getBitmap();
    }

    public static Drawable bitmapToDrawable(Bitmap b) {
        return b == null?null:new BitmapDrawable(b);
    }

    public static byte[] drawableToByte(Drawable d) {
        return bitmapToByte(drawableToBitmap(d));
    }

    public static Drawable byteToDrawable(byte[] b) {
        return bitmapToDrawable(byteToBitmap(b));
    }

    public static InputStream getInputStreamFromUrl(String imageUrl, int readTimeOutMillis) {
        return getInputStreamFromUrl(imageUrl, readTimeOutMillis, (Map)null);
    }

    public static InputStream getInputStreamFromUrl(String imageUrl, int readTimeOutMillis, Map<String, String> requestProperties) {
        InputStream stream = null;
        try {
            URL e = new URL(imageUrl);
            HttpURLConnection con = (HttpURLConnection)e.openConnection();
            setURLConnection(requestProperties, con);
            if(readTimeOutMillis > 0) {
                con.setReadTimeout(readTimeOutMillis);
            }

            stream = con.getInputStream();
            return stream;
        } catch (MalformedURLException var6) {
            closeInputStream(stream);
            throw new RuntimeException("MalformedURLException occurred. ", var6);
        } catch (IOException var7) {
            closeInputStream(stream);
            throw new RuntimeException("IOException occurred. ", var7);
        }
    }

    private static void setURLConnection(Map<String, String> requestProperties, HttpURLConnection urlConnection) {
        if(!MapUtils.isEmpty(requestProperties) && urlConnection != null) {
            Iterator var3 = requestProperties.entrySet().iterator();

            while(var3.hasNext()) {
                Entry entry = (Entry)var3.next();
                if(!StringUtils.isEmpty((String)entry.getKey())) {
                    urlConnection.setRequestProperty((String)entry.getKey(), (String)entry.getValue());
                }
            }

        }
    }

    public static Drawable getDrawableFromUrl(String imageUrl, int readTimeOutMillis) {
        return getDrawableFromUrl(imageUrl, readTimeOutMillis, (Map)null);
    }

    public static Drawable getDrawableFromUrl(String imageUrl, int readTimeOutMillis, Map<String, String> requestProperties) {
        InputStream stream = getInputStreamFromUrl(imageUrl, readTimeOutMillis, requestProperties);
        Drawable d = Drawable.createFromStream(stream, "src");
        closeInputStream(stream);
        return d;
    }

    public static Bitmap getBitmapFromUrl(String imageUrl, int readTimeOut) {
        return getBitmapFromUrl(imageUrl, readTimeOut, (Map)null);
    }

    public static Bitmap getBitmapFromUrl(String imageUrl, int readTimeOut, Map<String, String> requestProperties) {
        InputStream stream = getInputStreamFromUrl(imageUrl, readTimeOut, requestProperties);
        Bitmap b = BitmapFactory.decodeStream(stream);
        closeInputStream(stream);
        return b;
    }

    public static Bitmap scaleImageTo(Bitmap bitmap, int newWidth, int newHeight) {
        return scaleImage(bitmap, (float)newWidth / (float)bitmap.getWidth(), (float)newHeight / (float)bitmap.getHeight());
    }

    public static Bitmap scaleImage(Bitmap bitmap, float scaleWidth, float scaleHeight) {
        if(bitmap == null) {
            return null;
        } else {
            Matrix matrix = new Matrix();
            matrix.postScale(scaleWidth, scaleHeight);
            return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        }
    }

    private static void closeInputStream(InputStream s) {
        if(s != null) {
            try {
                s.close();
            } catch (IOException var2) {
                throw new RuntimeException("IOException occurred. ", var2);
            }
        }
    }
}
