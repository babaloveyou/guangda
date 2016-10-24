package com.android.thinkive.invest_sd.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

/**
 * 数据的硬盘缓存
 * Created by WELL on 2015/7/6.
 */
public class FileCacheToSdcardUtil {
    //数据缓存路径
    static String SAVEPATH= Environment.getExternalStorageDirectory() + "/thinkive/";
    public static void saveBitmap(Bitmap bitmap, String content,Context context) {
        SAVEPATH = context.getApplicationContext().getFilesDir().getAbsolutePath()+ "/files/";
        String path ;
        try {
            path = content.substring(content.lastIndexOf("/"), content.length());
        }catch (Exception e){
            path = content;
        }
        String filePath = SAVEPATH + path;

        if (bitmap != null) {
            File file = new File(filePath.substring(0,
                    filePath.lastIndexOf(File.separator)));
            if (!file.exists()) {
                file.mkdirs();
            }try {
                FileOutputStream fos = new FileOutputStream(filePath);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                fos.write(stream.toByteArray());
                fos.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


    //获得硬盘中的图片
    public static Bitmap getBitmap(String content,Context context){
        SAVEPATH = context.getApplicationContext().getFilesDir().getAbsolutePath()+ "/files/";
        String path ;
        try {
             path = content.substring(content.lastIndexOf("/"), content.length());
        }catch (Exception e){
             path = content;
        }
        String filePath =SAVEPATH + path;
        if(!new File(filePath).exists())
            return null ;
        Bitmap bitmap =null;
        try {
             bitmap = BitmapFactory.decodeFile(filePath);
        }catch (Exception e){
        }
        return bitmap;
    }
    public static void saveImageResponseJson(String jsonName,JSONObject jsonObject,Context context){
//        if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){//判断是否存在SD卡
//            return ;
//        }
        SAVEPATH = context.getApplicationContext().getFilesDir().getAbsolutePath()+ "/files/";
        File file=new File(SAVEPATH+jsonName+".txt");
        if(!file.getParentFile().exists()){//判断父文件是否存在，如果不存在则创建
            file.getParentFile().mkdirs();
        }
        PrintStream out=null; //打印流
        try {
            out=new PrintStream(new FileOutputStream(file)); //实例化打印流对象
            out.print(jsonObject.toString()); //输出数据
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally{
            if(out!=null){ //如果打印流不为空，则关闭打印流
                out.close();
            }
        }
    }
    public static JSONObject getImageResponseJson(String jsonName,Context context) {
        SAVEPATH = context.getApplicationContext().getFilesDir().getAbsolutePath()+ "/files/";
        String path=SAVEPATH+jsonName+".txt";
        File file = new File(path);
        BufferedReader reader = null;
        JSONObject jsonObject=null;
        String laststr = "";
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                laststr = laststr + tempString;
            }
            reader.close();
            jsonObject=new JSONObject(laststr);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }

        return jsonObject;
    }


    public static void saveResponseBean(String jsonName,Context context,Object o){
        if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){//判断是否存在SD卡
            return ;
        }
        SAVEPATH = context.getApplicationContext().getFilesDir().getAbsolutePath()+ "/files/cache/";
        File file=new File(SAVEPATH+jsonName+".txt");
        if(!file.getParentFile().exists()){//判断父文件是否存在，如果不存在则创建
            file.getParentFile().mkdirs();
        }
        PrintStream out=null; //打印流
        String value=null;
        ObjectMapper m = new ObjectMapper();
        try {
            value = m.writeValueAsString(o);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(value==null){
            return;
        }
        try {
            out=new PrintStream(new FileOutputStream(file)); //实例化打印流对象
            out.print(value.toString()); //输出数据
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally{
            if(out!=null){ //如果打印流不为空，则关闭打印流
                out.close();
            }
        }
    }

      public static <T> T getResponsebean(String jsonName,Context context,Class<T> beanclass) {
        T result = null;
        SAVEPATH = context.getApplicationContext().getFilesDir().getAbsolutePath()+ "/files/cache/";
        String path=SAVEPATH+jsonName+".txt";
        File file = new File(path);
        BufferedReader reader = null;
        JSONObject jsonObject=null;
        String laststr = "";
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                laststr = laststr + tempString;
            }
            reader.close();
            ObjectMapper m = new ObjectMapper();
            result = m.readValue(laststr,beanclass);
        } catch (IOException e) {
            e.printStackTrace();
        }  finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }

        return result;
    }

    public static <T> T getResponsebean(String jsonName,Context context,TypeReference<T> valueTypeRef) {
        T result = null;
        SAVEPATH = context.getApplicationContext().getFilesDir().getAbsolutePath()+ "/files/cache/";
        String path=SAVEPATH+jsonName+".txt";
        File file = new File(path);
        BufferedReader reader = null;
        JSONObject jsonObject=null;
        String laststr = "";
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                laststr = laststr + tempString;
            }
            reader.close();
            ObjectMapper m = new ObjectMapper();
            result = m.readValue(laststr,valueTypeRef);
        } catch (IOException e) {
            e.printStackTrace();
        }  finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }

        return result;
    }

}
