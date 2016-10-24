package com.android.thinkive.invest_sd.activities;

import android.app.Activity;
import android.os.Bundle;

import com.android.thinkive.invest_sd.R;
//import com.joanzapata.pdfview.PDFView;

import java.io.File;

/**
 * Created by 胡结才 on 2016/6/23 13:21.
 */
public class PDFActivity extends Activity{
//    private PDFView mPDFView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);
        initViews();
        initData();
    }


    protected void initViews() {
//        mPDFView= (PDFView) findViewById(   R.id.pdfview);
    }

    protected void initData() {
        try{
//            File file=new File(getIntent().getStringExtra("filePath"));
//            mPDFView.fromFile(file)
////                .pages(0, 2, 1, 3, 3, 3)
//                    .defaultPage(1)
//                    .showMinimap(false)
//                    .enableSwipe(true)
////                .onDraw(onDrawListener)
////                .onLoad(onLoadCompleteListener)
////                .onPageChange(onPageChangeListener)
//                    .load();
        }catch (Exception e){

        }

    }

    protected void findViews() {

    }

    protected void setListeners() {

    }

    protected void setTheme() {

    }
}
