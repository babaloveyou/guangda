package com.android.thinkive.invest_sd.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ThumbnailUtils;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.android.thinkive.framework.CoreApplication;
import com.android.thinkive.framework.config.ConfigManager;
import com.android.thinkive.framework.network.NetWorkService;
import com.android.thinkive.framework.network.RequestBean;
import com.android.thinkive.framework.network.ResponseListener;
import com.android.thinkive.invest_sd.R;
import com.android.thinkive.invest_sd.util.BitmapCache;
import com.android.thinkive.invest_sd.util.FileCacheToSdcardUtil;
import com.android.thinkive.invest_sd.util.ImageUtil;
import com.android.thinkive.invest_sd.widget.LoadingDialog;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.umeng.analytics.MobclickAgent;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;


/**
 * Created by xiangfan on 2015/8/26.
 */
public class NoticeInfoDetailActivity extends Activity {
    private TextView tv_notice_title;
    private TextView tv_notice_time;
    private TextView tv_notice_content;
    LoadingDialog loadingDialog;
    String id;
    private  boolean isFaild =false;
    private int srennwidth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notidetail);

        WindowManager wm = this.getWindowManager();

         srennwidth = wm.getDefaultDisplay().getWidth();
        tv_notice_title = (TextView) findViewById(R.id.tv_notice_title);
        tv_notice_time = (TextView) findViewById(R.id.tv_notice_time);
        tv_notice_content = (TextView) findViewById(R.id.tv_notice_content);
        loadingDialog = new LoadingDialog(this);
        loadingDialog.setCanceledOnTouchOutside(false);
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String time = intent.getStringExtra("time");
        id= intent.getStringExtra("id");
        findViewById(R.id.but_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        if(TextUtils.isEmpty(title)||TextUtils.isEmpty(time)){
            return;
        }
        tv_notice_title.setText(title);
        tv_notice_time.setText(time);
        initData();
        tv_notice_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isFaild){
                    initData();
                }
            }
        });
    }

    private void initData(){
        loadingDialog.show("正在加载");
        RequestBean request = new RequestBean();
        HashMap<String,String> params = new HashMap();
        params.put("funcNo", "9405073");
        params.put("articleId",id);
        request.setParam(params);
        request.setUrlName("URL_FRIST_DATAGD");
        NetWorkService.getInstance().request(request, new ResponseListener<JSONObject>() {

            @Override
            public void onResponse(JSONObject jsonObject) {
                loadingDialog.dismiss();
                int errorno = jsonObject.optInt("error_no");
                if (errorno == 0) {
                    JSONArray results = jsonObject.optJSONArray("results");
                    try {
                        isFaild = false;
                        JSONObject data = (JSONObject) results.get(0);
                        String content = data.optString("content");
                        Spanned text = Html.fromHtml(content, new URLImageParser(tv_notice_content), null);
                        tv_notice_content.setText(text);
                    } catch (Exception e) {
                        e.printStackTrace();
                        isFaild = true;
                        tv_notice_content.setText("加载失败点击重新加载");
                    }
                } else {
                    isFaild = true;
                    tv_notice_content.setText("加载失败点击重新加载");
                }
            }

            @Override
            public void onErrorResponse(Exception e) {
                loadingDialog.dismiss();
                tv_notice_content.setText("加载失败点击重新加载");
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }


    public class URLImageParser implements Html.ImageGetter {
        TextView mTextView;

        public URLImageParser(TextView textView) {
            this.mTextView = textView;
        }

        @Override
        public Drawable getDrawable(String source) {
            final URLDrawable urlDrawable = new URLDrawable();
            source = ConfigManager.getInstance(NoticeInfoDetailActivity.this).getAddressConfigValue("URL_FRIST_IMAGEGD")+ source;
            final Bitmap bitmap = FileCacheToSdcardUtil.getBitmap(source, NoticeInfoDetailActivity.this);
            if(bitmap!=null){
                Bitmap resizeBmp;
                if(bitmap.getWidth()>srennwidth){
                    int width = srennwidth;
                    int height =srennwidth*bitmap.getHeight()/bitmap.getWidth();
                    resizeBmp = ThumbnailUtils.extractThumbnail(bitmap, width, height);
                }else{
                    resizeBmp = bitmap;
                }
                urlDrawable.bitmap = resizeBmp;
                urlDrawable.setBounds(0, 0, bitmap.getWidth(), bitmap.getHeight());
                mTextView.invalidate();
                mTextView.setText(mTextView.getText()); // 解决图文重叠
                return urlDrawable ;
            }else{
                ImageUtil.getImage(source, NoticeInfoDetailActivity.this, new ImageLoader.ImageListener() {
                    @Override
                    public void onResponse(ImageLoader.ImageContainer imageContainer, boolean b) {
                        Bitmap bitmap1 = imageContainer.getBitmap();
                        if(bitmap1!=null) {
                            Bitmap resizeBmp1;
                            if(bitmap1.getWidth()>srennwidth){
                                int width = srennwidth;
                                int height =srennwidth*bitmap1.getHeight()/bitmap1.getWidth();
                                 resizeBmp1 = ThumbnailUtils.extractThumbnail(bitmap1, width, height);
                            }else{
                                resizeBmp1 = bitmap1;
                            }
                            urlDrawable.bitmap = resizeBmp1;
                            urlDrawable.setBounds(0, 0, resizeBmp1.getWidth(), bitmap1.getHeight());
                            mTextView.invalidate();
                            mTextView.setText(mTextView.getText()); // 解决图文重叠
                        }
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                });
//                ImageLoader imageLoader = new ImageLoader(CoreApplication.getInstance().getRequestQueue(), BitmapCache.getInstance(NoticeInfoDetailActivity.this.getApplicationContext()));
//                ImageLoader.ImageListener listener = new ImageLoader.ImageListener() {
//                    @Override
//                    public void onResponse(ImageLoader.ImageContainer imageContainer, boolean b) {
//                       Bitmap bitmap1 = imageContainer.getBitmap();
//                        if(bitmap1!=null) {
//                            urlDrawable.bitmap = bitmap1;
//                            urlDrawable.setBounds(0, 0, bitmap1.getWidth(), bitmap1.getHeight());
//                            mTextView.invalidate();
//                            mTextView.setText(mTextView.getText()); // 解决图文重叠
//                        }
//                    }
//                    @Override
//                    public void onErrorResponse(VolleyError volleyError) {
//                    }
//                };
//                imageLoader.get(source, listener);
            }
            return urlDrawable;
        }
    }

    public class URLDrawable extends BitmapDrawable {
        protected Bitmap bitmap;

        @Override
        public void draw(Canvas canvas) {
            if (bitmap != null) {
                canvas.drawBitmap(bitmap, 0, 0, getPaint());
            }
        }
    }

//    /**
//     *  处理图片
//     * @param bm 所要转换的bitmap
//     * @return 指定宽高的bitmap
//     */
//    public static Bitmap zoomImg(Bitmap bm, int newWidth ,int newHeight){
//        // 获得图片的宽高
//        int width = bm.getWidth();
//        int height = bm.getHeight();
//        // 计算缩放比例
//        float scaleWidth = ((float) newWidth) / width;
//        float scaleHeight = ((float) newHeight) / height;
//        // 取得想要缩放的matrix参数
//        Matrix matrix = new Matrix();
//        matrix.postScale(scaleWidth, scaleHeight);
//        // 得到新的图片   www.2cto.com
//        Bitmap newbm = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, true);
//        return newbm;
//    }

}
